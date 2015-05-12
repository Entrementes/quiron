package org.entrementes.quiron.component;

import java.util.ArrayList;
import java.util.List;

import org.entrementes.quiron.model.RestAPI;
import org.entrementes.quiron.model.RestAPIHealth;
import org.entrementes.quiron.model.RestMethod;
import org.entrementes.quiron.model.RestMethodHealth;
import org.entrementes.quiron.model.RestResource;
import org.entrementes.quiron.model.RestResourceHealth;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SpringDrivenHealthTester {

	private RestTemplate rest;

	public SpringDrivenHealthTester() {
		this.rest = new RestTemplate();
	}
	
	public RestAPIHealth test(RestAPI api) {
		RestAPIHealth result = new RestAPIHealth();
		result.setVersion(api.getVersion());
		List<RestResourceHealth> resources = checkResources(api.getResources());
		result.setResources(resources);
		return result;
	}

	private List<RestResourceHealth> checkResources(List<RestResource> resources) {
		List<RestResourceHealth> result = new ArrayList<RestResourceHealth>();
		for(RestResource resource : resources){
			RestResourceHealth healthCheck = new RestResourceHealth();
			healthCheck.setId(resource.getId());
			List<RestMethodHealth> methodsHealth = checkMethods(resource.getMethods());
			healthCheck.setMethods(methodsHealth);
			result.add(healthCheck);
		}
		return result;
	}

	private List<RestMethodHealth> checkMethods(List<RestMethod> methods) {
		List<RestMethodHealth> result = new ArrayList<RestMethodHealth>();
		for(RestMethod method : methods){
			RestMethodHealth healthCheck = new RestMethodHealth();
			healthCheck.setMethodType(method.getMethodType());
			healthCheck.setId(method.getId());
			healthCheck.setPath(method.getPath());
			healthCheck.setParameters(method.getParameters());
			healthCheck.setDependencies(method.getDependencies());
			//System.out.println(method.getPath());
			result.add(healthCheck);
		}
		return result;
	}

}
