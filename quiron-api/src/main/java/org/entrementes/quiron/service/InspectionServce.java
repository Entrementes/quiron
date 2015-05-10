package org.entrementes.quiron.service;

import org.entrementes.quiron.model.WebApplication;
import org.entrementes.quiron.model.WebApplicationHealth;

public interface InspectionServce {
	
	WebApplication getApi();
	
	WebApplicationHealth getStatus();

}
