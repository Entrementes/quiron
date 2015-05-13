package org.entrementes.quiron.component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.annotation.XmlRootElement;

import org.entrementes.quiron.annotation.ApiDependency;
import org.entrementes.quiron.annotation.ApiMethod;
import org.entrementes.quiron.annotation.ApiRequestParam;
import org.entrementes.quiron.annotation.ApiResource;
import org.entrementes.quiron.annotation.ApiResponse;
import org.entrementes.quiron.model.RestAPI;
import org.entrementes.quiron.model.RestMethod;
import org.entrementes.quiron.model.RestMethodDependency;
import org.entrementes.quiron.model.RestParameter;
import org.entrementes.quiron.model.RestResource;
import org.entrementes.quiron.model.RestResponse;
import org.entrementes.quiron.model.RestResponseAssertionParam;
import org.entrementes.quiron.model.builder.JavaTypeToXml;
import org.entrementes.quiron.model.constants.QuironParamType;
import org.entrementes.quiron.model.constants.QuironELParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class SpringAnnotationParser {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringAnnotationParser.class.getCanonicalName());
	
	private JsonCatalog jsonCatalog;
	
	private QuironELParser parser;
	
	@Autowired
	public SpringAnnotationParser(JsonCatalog jsonCatalog) {
		this.jsonCatalog = jsonCatalog;
		this.parser = new QuironELParser();
	}
	
	public RestAPI buildApi(ApplicationContext springContext, String id, String version) {
		RestAPI result = new RestAPI();
		Map<String,Object> controllers = springContext.getBeansWithAnnotation(ApiResource.class);
		LOGGER.debug("{} controllers found.", controllers.keySet().size());
		List<RestResource> resources = new ArrayList<>();
		result.setVersion(version);
		result.setId(id);
		for(Entry<String,Object> controllerEntry : controllers.entrySet()){
			RestResource mappedResource = extractResource(controllerEntry);
			resources.add(mappedResource);
		}
		result.setResources(resources);
		return result;
	}
	
	private RestResource extractResource(Entry<String, Object> controllerEntry) {
		ApiResource apiResource = controllerEntry.getValue().getClass().getAnnotation(ApiResource.class);
		RequestMapping resourceMapping = controllerEntry.getValue().getClass().getAnnotation(RequestMapping.class);
		RestResource result = new RestResource();
		String[] resourcesPath = resourceMapping.value(); 
		Class<?> classe = controllerEntry.getValue().getClass();
		Method[] methods = classe.getDeclaredMethods();
		List<RestMethod> mappedMethods = extractMethods(methods, parsePath(resourcesPath));
		result.setMethods(mappedMethods);
		result.setId(apiResource.id());
		return result;
	}

	private List<RestMethod> extractMethods(Method[] methods, String resourcePath) {
		List<RestMethod> result = new ArrayList<RestMethod>();
		for(Method method : methods){
			if(method.isAnnotationPresent(ApiMethod.class)){
				RestMethod mappedMethod = new RestMethod();
				ApiMethod apiMethod = method.getAnnotation(ApiMethod.class);
				mappedMethod.setId(apiMethod.id());
				RequestMapping methodMapping = method.getAnnotation(RequestMapping.class);
				String[] methodPath = methodMapping.value();
				mappedMethod.setPath(resourcePath + parsePath(methodPath));
				List<RestParameter> mappedParameters = extractParameters(method.getParameters());
				mappedMethod.setMethodType(parseMethodType(methodMapping.method()));
				mappedMethod.setParameters(mappedParameters);
				List<RestResponse> mappedResponses = extractResponses(apiMethod);
				List<RestMethodDependency> mappedDependencies = extractDependencies(apiMethod);
				mappedMethod.setResponses(mappedResponses);
				mappedMethod.setDependencies(mappedDependencies);
				mappedMethod.setDescription(apiMethod.description());
				result.add(mappedMethod);
			}
		}
		return result;
	}

	private List<RestMethodDependency> extractDependencies(ApiMethod apiMethod) {
		List<RestMethodDependency> result = new ArrayList<RestMethodDependency>();
		ApiDependency[] dependencies = apiMethod.dependencies();
		for(ApiDependency dependency : dependencies){
			if(this.parser.isQuironEL(dependency.id())){
				RestMethodDependency cataloggedDependency = this.jsonCatalog.listApiDependencies().get(this.parser.unwrap(dependency.id()));
				cataloggedDependency.setUsed(true);
				result.add(cataloggedDependency);
			}else{
				RestMethodDependency mappedDependency = new RestMethodDependency();
				mappedDependency.setHost(dependency.host());
				mappedDependency.setContext(dependency.context());
				mappedDependency.setId(dependency.id());
				mappedDependency.setPath(dependency.path());
				mappedDependency.setPort(dependency.port());
				mappedDependency.setMethodType(dependency.methodType());
				result.add(mappedDependency);
			}
		}
		return result;
	}

	private List<RestResponse> extractResponses(ApiMethod apiMethod) {
		List<RestResponse> result = new ArrayList<RestResponse>();
		ApiResponse[] responses = apiMethod.responses();
		for(ApiResponse response : responses){
			RestResponse mappedResponse = new RestResponse();
			mappedResponse.setDescription(response.description());
			mappedResponse.setCode(response.code());
			if(this.parser.isQuironEL(response.body())){
				String key = this.parser.unwrap(response.body());
				mappedResponse.setBody(this.jsonCatalog.getJsonBody(key));
			}else{
				mappedResponse.setBody(response.body());
			}
			List<RestResponseAssertionParam> assertionParameters = new ArrayList<RestResponseAssertionParam>();
			for(ApiRequestParam param : response.requestParams()){
				RestResponseAssertionParam testParameter = new RestResponseAssertionParam();
				testParameter.setName(param.name());
				testParameter.setType(param.type());
				if(this.parser.isQuironEL(param.value())){
					String key = this.parser.unwrap(param.value());
					testParameter.setValue(this.jsonCatalog.getJsonBody(key));
				}else{
					testParameter.setValue(param.value());
				}
				assertionParameters.add(testParameter);
				
			}
			mappedResponse.setAssertionParameters(assertionParameters);
			result.add(mappedResponse);
		}
		return result;
	}

	private String parseMethodType(RequestMethod[] method) {
		if(method.length == 0){
			return RequestMethod.GET.name();
		}
		return method[0].name();
	}

	private String parsePath(String[] methodPath) {
		if(methodPath.length == 0){
			return "/";
		}
		return methodPath[0];
	}

	private List<RestParameter> extractParameters(Parameter[] parameters) {
		List<RestParameter> result = new ArrayList<RestParameter>();
		for(Parameter parameter : parameters){
			RestParameter mappedParam = null;
			if(parameter.isAnnotationPresent(RequestParam.class)){
				mappedParam = new RestParameter();
				RequestParam requestParam = parameter.getAnnotation(RequestParam.class);
				mappedParam.setStyle(QuironParamType.QUERY);
				mappedParam.setName(requestParam.value());
				mappedParam.setType(extractParameterType(parameter));
				mappedParam.setRequired(requestParam.required());
			}
			if(parameter.isAnnotationPresent(PathVariable.class)){
				mappedParam = new RestParameter();
				PathVariable pathVariable = parameter.getAnnotation(PathVariable.class);
				mappedParam.setStyle(QuironParamType.URI);
				mappedParam.setName(pathVariable.value());
				mappedParam.setType(extractParameterType(parameter));
				mappedParam.setRequired(true);
			}
			if(parameter.isAnnotationPresent(RequestBody.class)){
				mappedParam = new RestParameter();
				mappedParam.setStyle(QuironParamType.BODY);
				mappedParam.setName(null);
				mappedParam.setType(extractParameterType(parameter));
				mappedParam.setRequired(true);
			}
			if(mappedParam != null){
				result.add(mappedParam);
			}
		}
		return result;
	}

	private String extractParameterType(Parameter parameter) {
		if(parameter.getType().getTypeName().startsWith("java")){
			return new JavaTypeToXml().parse(parameter);
		}else{
			XmlRootElement element = parameter.getType().getAnnotation(XmlRootElement.class);
			if(element != null){
				return element.name();
			}else{
				return parameter.getType().getTypeName();
			}
		}
	}

}
