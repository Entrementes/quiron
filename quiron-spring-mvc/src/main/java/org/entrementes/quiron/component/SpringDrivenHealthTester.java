package org.entrementes.quiron.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.entrementes.quiron.configuration.QuironHttpConfiguration;
import org.entrementes.quiron.model.RestAPI;
import org.entrementes.quiron.model.RestAPIHealth;
import org.entrementes.quiron.model.RestMethod;
import org.entrementes.quiron.model.RestMethodAssertion;
import org.entrementes.quiron.model.RestMethodHealth;
import org.entrementes.quiron.model.RestResource;
import org.entrementes.quiron.model.RestResourceHealth;
import org.entrementes.quiron.model.RestResponse;
import org.entrementes.quiron.model.RestResponseAssertionParam;
import org.entrementes.quiron.model.constants.HttpProtocolParser;
import org.entrementes.quiron.model.constants.QuironParamType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class SpringDrivenHealthTester {

	private RestTemplate rest;
	
	private HttpProtocolParser parser;
	
	private QuironHttpConfiguration configuration;

	private JsonCatalog catalog;

	@Autowired
	public SpringDrivenHealthTester(HttpProtocolParser parser, QuironHttpConfiguration configuration, JsonCatalog catalog) {
		this.rest = new RestTemplate();
		this.parser = parser;
		this.configuration = configuration;
		this.catalog = catalog;
	}
	
	public RestAPIHealth test(UriComponentsBuilder builder, RestAPI api, boolean failuresOnly) {
		RestAPIHealth result = new RestAPIHealth();
		List<RestResourceHealth> resources = checkResources(builder ,api.getResources(), failuresOnly);
		if(!failuresOnly || ( resources.size() > 0 && failuresOnly )){
			result.setVersion(api.getVersion());
			result.setResources(resources);
		}
		return result;
	}

	private List<RestResourceHealth> checkResources(UriComponentsBuilder builder, List<RestResource> resources, boolean failuresOnly) {
		List<RestResourceHealth> result = new ArrayList<RestResourceHealth>();
		for(RestResource resource : resources){
			List<RestMethodHealth> methodsHealth = checkMethods(builder, resource.getMethods(), failuresOnly);
			if(!failuresOnly || ( methodsHealth.size() > 0 && failuresOnly )){
				RestResourceHealth healthCheck = new RestResourceHealth();
				healthCheck.setId(resource.getId());
				healthCheck.setMethods(methodsHealth);
				result.add(healthCheck);
			}
		}
		return result;
	}

	private List<RestMethodHealth> checkMethods(UriComponentsBuilder builder, List<RestMethod> methods, boolean failuresOnly) {
		List<RestMethodHealth> result = new ArrayList<RestMethodHealth>();
		for(RestMethod method : methods){
			List<RestMethodAssertion> assertions = checkAssertions(builder, method, failuresOnly);
			if(!failuresOnly || ( assertions.size() > 0 && failuresOnly )){
				RestMethodHealth healthCheck = new RestMethodHealth();
				healthCheck.setMethodType(method.getMethodType());
				healthCheck.setId(method.getId());
				healthCheck.setPath(method.getPath());
				healthCheck.setParameters(method.getParameters());
				healthCheck.setDependencies(method.getDependencies());
				healthCheck.setDescription(method.getDescription());
				healthCheck.setResponses(assertions);
				result.add(healthCheck);
			}
		}
		return result;
	}

	private List<RestMethodAssertion> checkAssertions(UriComponentsBuilder builder,RestMethod method, boolean failuresOnly) {
		List<RestMethodAssertion> result = new ArrayList<RestMethodAssertion>();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set(this.configuration.getHttpControlRequestHeader(), "true");
		for(RestResponse templateResponse : method.getResponses()){
			String requestUuid = UUID.randomUUID().toString();
			headers.set(this.configuration.getHttpControlRequestHeader(), requestUuid);
			UriComponentsBuilder requestBuilder = UriComponentsBuilder.fromUri(builder.build().toUri());
			headers.set(this.configuration.getHttpExpectedStatusHeader(), templateResponse.getCode().value().toString());
			String requestBody = templateResponse.getRequestBody();
			HttpEntity<String> request = new HttpEntity<String>(requestBody,headers);
			HttpMethod chosenMethod = (HttpMethod) this.parser.parseMethod(method.getMethodType());
			HttpStatus chosenStatus = (HttpStatus) this.parser.parseStatus(templateResponse.getCode());
			requestBuilder.path(expandURI(method.getPath(), templateResponse.getAssertionParameters())); 
			extractQueryParameters(requestBuilder, templateResponse.getAssertionParameters());
			boolean testPassed = false;
			String apiReturned;
			HttpStatus recievedStatus;
			try{
				ResponseEntity<String> response = this.rest.exchange(requestBuilder.build().toUri(), chosenMethod, request, String.class);
				recievedStatus = response.getStatusCode();
				testPassed = chosenStatus.equals(recievedStatus);
				if(templateResponse.getBody() != null && !templateResponse.getBody().isEmpty()){
					testPassed = testPassed && this.catalog.checkAssertion(templateResponse.getBody(), response.getBody(), this.configuration.buildControlResponse(requestUuid));
				}
				apiReturned = response.getBody();
			}catch(HttpClientErrorException ex){
				recievedStatus = ex.getStatusCode();
				testPassed = recievedStatus.equals(chosenStatus);
				apiReturned = ex.getResponseBodyAsString();
			}catch(Exception ex){
				testPassed = false;
				recievedStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				apiReturned = ExceptionUtils.getStackTrace(ex);
			}
			if(!failuresOnly ||(failuresOnly && !testPassed)){
				RestMethodAssertion mappedAssertion = new RestMethodAssertion();
				mappedAssertion.setExpectedCode(templateResponse.getCode());
				mappedAssertion.setRecievedCode(this.parser.parseStatus(recievedStatus));
				mappedAssertion.setAssertionParameters(templateResponse.getAssertionParameters());
				mappedAssertion.setDescription(templateResponse.getDescription());
				mappedAssertion.setPassed(testPassed);
				mappedAssertion.setBody(apiReturned);
				result.add(mappedAssertion);
			}
		}
		return result;
	}

	private void extractQueryParameters( UriComponentsBuilder builder, List<RestResponseAssertionParam> assertionParameters) {
		for(RestResponseAssertionParam param : assertionParameters){
			if(QuironParamType.QUERY.equals(param.getType())){
				builder.queryParam(param.getName(),param.getValue());
			}
		}
	}

	private String expandURI(String templateUri, List<RestResponseAssertionParam> assertionParameters) {
		String result = templateUri;
		for(RestResponseAssertionParam param : assertionParameters){
			if(QuironParamType.URI.equals(param.getType())){
				result = result.replace("{"+param.getName()+"}", param.getValue());
			}
		}
		return result;
	}
}
