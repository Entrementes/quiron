package org.entrementes.quiron.service;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.entrementes.quiron.annotation.ApiMethod;
import org.entrementes.quiron.annotation.ApiResource;
import org.entrementes.quiron.component.SpringWebApplicationBuilder;
import org.entrementes.quiron.model.RestAPI;
import org.entrementes.quiron.model.RestInterface;
import org.entrementes.quiron.model.RestInterfaceHealth;
import org.entrementes.quiron.model.RestMethod;
import org.entrementes.quiron.model.RestParameter;
import org.entrementes.quiron.model.RestRequest;
import org.entrementes.quiron.model.RestResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class SpringMvcInspectionService implements InspectionServce {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringMvcInspectionService.class.getCanonicalName());
	
	private RestAPI api;
	
	@Autowired
	public SpringMvcInspectionService(ApplicationContext springContext,@Value("${api.version}") String version, @Value("${api.id}") String id) {
		Map<String,Object> controllers = springContext.getBeansWithAnnotation(ApiResource.class);
		LOGGER.debug("{} controllers found.", controllers.keySet().size());
		List<RestResource> resources = new ArrayList<>();
		this.api = new RestAPI();
		this.api.setVersion(version);
		this.api.setId(id);
		for(Entry<String,Object> controllerEntry : controllers.entrySet()){
			RestResource mappedResource = extractResource(controllerEntry);
			resources.add(mappedResource);
		}
		api.setResources(resources);
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
				RestRequest request = extractRequest(method);
				mappedMethod.setType(parseMethodType(methodMapping.method()));
				mappedMethod.setRequest(request);
				result.add(mappedMethod);
			}
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

	private RestRequest extractRequest(Method method) {
		Parameter[] parameters = method.getParameters();
		RestRequest result = new RestRequest();
		List<RestParameter> mappedParameters = extractParameters(parameters);
		Map<String,RestParameter> map = mappedParameters.stream().collect(Collectors.toMap(param -> param.getName(), param -> param));
		result.setParameters(map);
		return result;
	}

	private List<RestParameter> extractParameters(Parameter[] parameters) {
		List<RestParameter> result = new ArrayList<RestParameter>();
		for(Parameter parameter : parameters){
			RestParameter mappedParam = null;
			if(parameter.isAnnotationPresent(RequestParam.class)){
				mappedParam = new RestParameter();
				RequestParam requestParam = parameter.getAnnotation(RequestParam.class);
				mappedParam.setStyle("query");
				mappedParam.setName(requestParam.value());
				mappedParam.setRequired(requestParam.required());
			}
			if(parameter.isAnnotationPresent(PathVariable.class)){
				mappedParam = new RestParameter();
				PathVariable pathVariable = parameter.getAnnotation(PathVariable.class);
				mappedParam.setStyle("uri");
				mappedParam.setName(pathVariable.value());
				mappedParam.setRequired(true);
			}
			if(parameter.isAnnotationPresent(RequestBody.class)){
				mappedParam = new RestParameter();
				mappedParam.setStyle("body");
				mappedParam.setName(null);
				mappedParam.setRequired(true);
			}
			if(mappedParam != null){
				result.add(mappedParam);
			}
		}
		return result;
	}

	@Override
	public RestInterface getApi(HttpServletRequest request) {
		return new SpringWebApplicationBuilder().api(this.api)
												.request(request)
												.build();
	}

	@Override
	public RestInterfaceHealth getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

}
