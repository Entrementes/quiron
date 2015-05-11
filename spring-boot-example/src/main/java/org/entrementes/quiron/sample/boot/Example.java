package org.entrementes.quiron.sample.boot;

import javax.servlet.http.HttpServletRequest;

import org.entrementes.quiron.annotation.ApiAssertionTest;
import org.entrementes.quiron.annotation.ApiDependency;
import org.entrementes.quiron.annotation.ApiMethod;
import org.entrementes.quiron.annotation.ApiResource;
import org.entrementes.quiron.annotation.ApiResponse;
import org.entrementes.quiron.model.RestAPI;
import org.entrementes.quiron.model.RestInterface;
import org.entrementes.quiron.model.RestMethod;
import org.entrementes.quiron.model.RestMethodDependency;
import org.entrementes.quiron.model.RestResource;
import org.entrementes.quiron.service.InspectionServce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ApiResource(id="example")
@RestController
@RequestMapping("/quiron")
public class Example {
	
	@Autowired
	private InspectionServce service;
	
	@ApiMethod(id="api", responses={
			@ApiResponse(code=200, body="test body", description="example WADL", assertionTest=@ApiAssertionTest()),
			@ApiResponse(code=502, body="error message", description="dependencies failed")
		},
		dependencies={
			@ApiDependency(host="http://google.com/", id="google search", methodType="GET")
	})
	@RequestMapping(value="/",method=RequestMethod.GET)
	@ResponseBody
	public RestInterface api( HttpServletRequest request ) {
		return this.service.getApi(request);
	}
	
	@ApiMethod(id="resource-search")
	@RequestMapping(value="/{resource-id}",method=RequestMethod.GET)
	@ResponseBody
	public RestResource resource(@PathVariable("resource-id") String resourceId) {
		return this.service.getResource(resourceId);
	}
	
	@ApiMethod(id="method-search")
	@RequestMapping(value="/{resource-id}/{method-id}",method=RequestMethod.GET)
	@ResponseBody
	public RestMethod method(@PathVariable("resource-id") String resourceId, @PathVariable("method-id") String methodId) {
		return this.service.getMethod(resourceId, methodId);
	}
	
	@ApiMethod(id="dependency-search")
	@RequestMapping(value="/{resource-id}/{method-id}/{dependency-id}",method=RequestMethod.GET)
	@ResponseBody
	public RestMethodDependency dependency(@PathVariable("resource-id") String resourceId, @PathVariable("method-id") String methodId, @PathVariable("dependency-id") String dependencyId) {
		return this.service.getMethodDependency(resourceId, methodId, dependencyId);
	}
	
	@ApiMethod(id="mappping")
	@RequestMapping(value="/mappping/{path-param}/",method=RequestMethod.PUT)
	@ResponseBody
	public RestAPI mappping( @PathVariable("path-param") String path, 
							@RequestParam(value="query-param",required=false) Integer queryParam, 
							@RequestBody RestAPI body, 
							HttpServletRequest request ) {
		return body;
	}

}
