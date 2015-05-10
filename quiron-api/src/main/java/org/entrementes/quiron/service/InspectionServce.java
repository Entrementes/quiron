package org.entrementes.quiron.service;

import javax.servlet.http.HttpServletRequest;

import org.entrementes.quiron.model.WebApplication;
import org.entrementes.quiron.model.WebApplicationHealth;

public interface InspectionServce {
	
	WebApplication getApi(HttpServletRequest request);
	
	WebApplicationHealth getStatus();

}
