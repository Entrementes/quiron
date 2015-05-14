package org.entrementes.quiron.sample.bus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.entrementes.quiron.model.RestInterface;
import org.entrementes.quiron.model.RestInterfaceHealth;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MonitoringWebHook {
	
	private Map<String,RestInterface> subscribersMap = new HashMap<String, RestInterface>();
	
	private RestTemplate rest;
	
	public MonitoringWebHook() {
		this.rest = new RestTemplate();
	}
	
	public void registerSubscriber(RestInterface newSubscriber){
		this.subscribersMap.put(newSubscriber.getHealthMonitoringUri(), newSubscriber);
	}

	public List<RestInterfaceHealth> monitorSubscribers() {
		List<RestInterfaceHealth> result = new ArrayList<RestInterfaceHealth>();
		for(String subscriber : this.subscribersMap.keySet()){
			RestInterfaceHealth subscriberHealth = this.rest.getForObject(subscriber, RestInterfaceHealth.class);
			result.add(subscriberHealth);
		}
		return result;
	}
	
	public Map<String,RestInterface> getSubscribers(){
		return this.subscribersMap;
	}

}
