package org.entrementes.quiron.sample.boot;

import javax.servlet.http.HttpServletRequest;

import org.entrementes.quiron.annotation.ApiMethod;
import org.entrementes.quiron.annotation.ApiResource;
import org.entrementes.quiron.annotation.ApiResponse;
import org.entrementes.quiron.model.RestInterface;
import org.entrementes.quiron.service.InspectionServce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
			@ApiResponse(code=200, body="test body", description="example WADL"),
			@ApiResponse(code=502, body="error message", description="dependencies failed")
	})
	@RequestMapping(value="/",method=RequestMethod.GET)
	@ResponseBody
	public RestInterface api( HttpServletRequest request ) {
		return this.service.getApi(request);
	}
	
	@ApiMethod(id="mappping")
	@RequestMapping(value="/mappping/{path-param}/",method=RequestMethod.PUT)
	@ResponseBody
	public String mappping( @PathVariable("path-param") String path, 
							@RequestParam(value="query-param",required=false) String queryParam, 
							@RequestBody String body, 
							HttpServletRequest request ) {
		return body;
	}

}
