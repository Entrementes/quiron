package org.entrementes.quiron.sample.boot;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.entrementes.quiron.annotation.ApiMethod;
import org.entrementes.quiron.annotation.ApiResource;
import org.entrementes.quiron.model.RestInterface;
import org.entrementes.quiron.model.RestInterfaceHealth;
import org.entrementes.quiron.model.RestMethod;
import org.entrementes.quiron.model.RestMethodDependency;
import org.entrementes.quiron.model.RestResource;
import org.entrementes.quiron.service.InspectionServce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ApiResource(id="api")
@RestController
@RequestMapping("/api")
public class ApiResources {
	
	@Autowired
	private InspectionServce service;
	
	@ApiMethod(id="map", description="map web-application WADL.")
	@RequestMapping(value="/map",method=RequestMethod.GET)
	@ResponseBody
	public RestInterface map( HttpServletRequest request ) {
		return this.service.getApi(request);
	}
	
	@ApiMethod(id="dependencies", description="list web-application external dependencies.")
	@RequestMapping(value="/dependencies",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,RestMethodDependency> dependencies() {
		return this.service.listApiDependencies();
	}
	
	@ApiMethod(id="health-check", description="check api responses based onf supplied request templates.")
	@RequestMapping(value="/health",method=RequestMethod.GET)
	@ResponseBody
	public RestInterfaceHealth health( @RequestParam(value="failuresOnly", required=false, defaultValue="false") Boolean failuresOnly , HttpServletRequest request ) {
		return this.service.getStatus(request,failuresOnly);
	}
	
	@ApiMethod(id="resource-search", description="look for a specific resource on the api's structure.")
	@RequestMapping(value="/query/{resource-id}",method=RequestMethod.GET)
	@ResponseBody
	public RestResource resource(@PathVariable("resource-id") String resourceId) {
		return this.service.getResource(resourceId);
	}
	
	@ApiMethod(id="method-search", description="look for a specific method on the api's structure.")
	@RequestMapping(value="/query/{resource-id}/{method-id}",method=RequestMethod.GET)
	@ResponseBody
	public RestMethod method(@PathVariable("resource-id") String resourceId, @PathVariable("method-id") String methodId) {
		return this.service.getMethod(resourceId, methodId);
	}
	
	@ApiMethod(id="dependency-search", description="look for a specific dependency on the api's structure.")
	@RequestMapping(value="/query/{resource-id}/{method-id}/{dependency-id}",method=RequestMethod.GET)
	@ResponseBody
	public RestMethodDependency dependency(@PathVariable("resource-id") String resourceId, @PathVariable("method-id") String methodId, @PathVariable("dependency-id") String dependencyId) {
		return this.service.getMethodDependency(resourceId, methodId, dependencyId);
	}

}
