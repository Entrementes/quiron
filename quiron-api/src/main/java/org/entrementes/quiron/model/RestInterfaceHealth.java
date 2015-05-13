package org.entrementes.quiron.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="rest-interface-health")
@XmlAccessorType(XmlAccessType.FIELD)
public class RestInterfaceHealth {
	
	@XmlElement(name="host", required=true)
	private String host;
	
	@XmlElement(name="port", required=true, defaultValue="80")
	private Integer port;
	
	@XmlElement(name="context", required=false)
	private String context;
	
	@XmlElement(name="api")
	private RestAPIHealth api;
	
	public RestInterfaceHealth() {}

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

	public RestAPIHealth getApi() {
		return api;
	}

	public void setApi(RestAPIHealth api) {
		this.api = api;
	}

}
