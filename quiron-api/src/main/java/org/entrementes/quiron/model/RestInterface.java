package org.entrementes.quiron.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="rest-interface")
@XmlAccessorType(XmlAccessType.FIELD)
public class RestInterface {
	
	@XmlElement(name="host", required=true)
	private String host;
	
	@XmlElement(name="port", required=true, defaultValue="80")
	private Integer port;
	
	@XmlElement(name="context", required=false)
	private String context;
	
	@XmlElement(name="api", required=true)
	private RestAPI api;
	
	@XmlElement(name="health-monitoring-uri", required=true)
	private String healthMonitoringUri;

	public RestInterface() {}
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public RestAPI getApi() {
		return api;
	}

	public void setApi(RestAPI api) {
		this.api = api;
	}

	public String getHealthMonitoringUri() {
		return healthMonitoringUri;
	}
	
	public void setHealthMonitoringUri(String healthMonitoringUri) {
		this.healthMonitoringUri = healthMonitoringUri;
	}

}
