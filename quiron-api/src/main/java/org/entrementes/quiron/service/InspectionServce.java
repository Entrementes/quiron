package org.entrementes.quiron.service;

import javax.servlet.http.HttpServletRequest;

import org.entrementes.quiron.model.RestInterface;
import org.entrementes.quiron.model.RestInterfaceHealth;

public interface InspectionServce {
	
	RestInterface getApi(HttpServletRequest request);
	
	RestInterfaceHealth getStatus();

}
