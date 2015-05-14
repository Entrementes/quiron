package org.entrementes.quiron.sample.crud;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BusAdapter {
	
	private RestTemplate rest;

	public BusAdapter(){
		this.rest = new RestTemplate();
	}

	
	public void contextInitialized() {
		this.rest.getForObject("http://localhost:7777/quiron/v1/api/monitor", String.class);	
	}
	
}
