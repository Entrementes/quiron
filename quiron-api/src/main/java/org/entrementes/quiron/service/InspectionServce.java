package org.entrementes.quiron.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.entrementes.quiron.model.RestInterface;
import org.entrementes.quiron.model.RestInterfaceHealth;
import org.entrementes.quiron.model.RestMethod;
import org.entrementes.quiron.model.RestMethodDependency;
import org.entrementes.quiron.model.RestResource;

public interface InspectionServce {
	
	RestInterface getApi(HttpServletRequest request);
	
	RestInterfaceHealth getStatus(HttpServletRequest request);
	
	RestResource getResource(String resourceId);
	
	RestMethod getMethod(String resourceId, String methodId);
	
	RestMethodDependency getMethodDependency(String resourceId, String methodId, String dependencyId);

	Map<String, RestMethodDependency> listApiDependencies();
	
}
