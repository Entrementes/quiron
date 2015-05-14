package org.entrementes.quiron.sample.bus;

import java.util.Collection;
import java.util.List;

import org.entrementes.quiron.model.RestInterface;
import org.entrementes.quiron.model.RestInterfaceHealth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MonitoringResource {
	
	private MonitoringWebHook registry;

	@Autowired
	public MonitoringResource(MonitoringWebHook registry) {
		this.registry = registry;
	}
	
	@RequestMapping(value="/monitor",method=RequestMethod.POST, consumes="application/json")
	@ResponseBody
	public ResponseEntity<String> postApi(@RequestBody RestInterface newSubscriber){
		this.registry.registerSubscriber(newSubscriber);
		return new ResponseEntity<String>(newSubscriber.getHealthMonitoringUri(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/monitor",method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public ResponseEntity<Collection<RestInterfaceHealth>> monitorSubscribers(){
		Collection<RestInterfaceHealth> result = this.registry.monitorSubscribers();
		return new ResponseEntity<Collection<RestInterfaceHealth>>(result,HttpStatus.OK);
	}
	
	@RequestMapping(value="/interfaces",method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public ResponseEntity<Collection<RestInterface>> listSubscribers(){
		return new ResponseEntity<Collection<RestInterface>>(this.registry.getSubscribers().values(),HttpStatus.OK);
	}

}
