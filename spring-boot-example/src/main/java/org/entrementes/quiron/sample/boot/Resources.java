package org.entrementes.quiron.sample.boot;

import javax.servlet.http.HttpServletRequest;

import org.entrementes.quiron.annotation.ApiMethod;
import org.entrementes.quiron.annotation.ApiResource;
import org.entrementes.quiron.model.RestInterface;
import org.entrementes.quiron.service.InspectionServce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ApiResource(id="Exemplo")
@RestController
@RequestMapping("/exemplos")
public class Resources {
	
	@Autowired
	private InspectionServce service;
	
	@ApiMethod(id="api")
	@RequestMapping(value="/",method=RequestMethod.GET)
	@ResponseBody
	public RestInterface api( HttpServletRequest request ) {
		return this.service.getApi(request);
	}
	
	@ApiMethod(id="mapeamento")
	@RequestMapping(value="/mapeamento/{path}",method=RequestMethod.PUT)
	@ResponseBody
	public String mapeamento( 	@PathVariable("path-param") String path, 
								@RequestParam(value="query-param",required=false) String queryParam, 
								@RequestBody String body, 
								HttpServletRequest request ) {
		return body;
	}

}
