package org.entrementes.quiron.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.entrementes.quiron.model.RestAPI;
import org.entrementes.quiron.model.RestAPIHealth;
import org.entrementes.quiron.model.RestMethod;
import org.entrementes.quiron.model.RestMethodAssertion;
import org.entrementes.quiron.model.RestMethodHealth;
import org.entrementes.quiron.model.RestResource;
import org.entrementes.quiron.model.RestResourceHealth;
import org.entrementes.quiron.model.RestResponse;
import org.entrementes.quiron.model.constants.ConstantsParser;
import org.entrementes.quiron.model.constants.QuironConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SpringDrivenHealthTester {

	private RestTemplate rest;
	
	private ConstantsParser parser;
	
	private PayloadCatalog referencedPayloads;

	@Autowired
	public SpringDrivenHealthTester(ConstantsParser parser, PayloadCatalog referencedPayloads) {
		this.rest = new RestTemplate();
		this.parser = parser;
		this.referencedPayloads = referencedPayloads;
	}
	
	public RestAPIHealth test(String rootPath, RestAPI api) {
		RestAPIHealth result = new RestAPIHealth();
		result.setVersion(api.getVersion());
		List<RestResourceHealth> resources = checkResources(rootPath ,api.getResources());
		result.setResources(resources);
		return result;
	}

	private List<RestResourceHealth> checkResources(String rootPath, List<RestResource> resources) {
		List<RestResourceHealth> result = new ArrayList<RestResourceHealth>();
		for(RestResource resource : resources){
			RestResourceHealth healthCheck = new RestResourceHealth();
			healthCheck.setId(resource.getId());
			List<RestMethodHealth> methodsHealth = checkMethods(rootPath, resource.getMethods());
			healthCheck.setMethods(methodsHealth);
			result.add(healthCheck);
		}
		return result;
	}

	private List<RestMethodHealth> checkMethods(String rootPath, List<RestMethod> methods) {
		List<RestMethodHealth> result = new ArrayList<RestMethodHealth>();
		for(RestMethod method : methods){
			RestMethodHealth healthCheck = new RestMethodHealth();
			healthCheck.setMethodType(method.getMethodType());
			healthCheck.setId(method.getId());
			healthCheck.setPath(method.getPath());
			healthCheck.setParameters(method.getParameters());
			healthCheck.setDependencies(method.getDependencies());
			List<RestMethodAssertion> assertions = checkAssertions(rootPath, method);
			healthCheck.setResponses(assertions);
			result.add(healthCheck);
		}
		return result;
	}

	private List<RestMethodAssertion> checkAssertions(String rootPath,RestMethod method) {
		List<RestMethodAssertion> result = new ArrayList<RestMethodAssertion>();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set(QuironConstants.QUIRON_HTTP_HEADER, "true");
		for(RestResponse templateResponse : method.getResponses()){
			headers.set(QuironConstants.QUIRON_EXPECTED_STATUS, templateResponse.getCode().value().toString());
			HttpEntity<String> request = new HttpEntity<String>("");
			HttpMethod chosenMethod = (HttpMethod) this.parser.parseMethod(method.getMethodType());
			HttpStatus chosenStatus = (HttpStatus) this.parser.parseStatus(templateResponse.getCode());
			ResponseEntity<String> response = this.rest.exchange(rootPath + method.getPath(), chosenMethod, request, String.class);
			boolean assertResponseCode = chosenStatus.equals(response.getStatusCode());
			boolean assertResponseBody = this.referencedPayloads.checkAssertion(templateResponse.getBody(), response.getBody());
			System.out.println(assertResponseBody);
			System.out.println(assertResponseCode);
		}
		return result;
	}

}
