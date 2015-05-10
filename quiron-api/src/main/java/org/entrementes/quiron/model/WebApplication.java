package org.entrementes.quiron.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="web-application", namespace="org.entrmentes.quiron")
@XmlAccessorType(XmlAccessType.FIELD)
public class WebApplication {
	
	@XmlElement(name="host", required=true)
	private String host;
	
	@XmlElement(name="port", required=true, defaultValue="80")
	private Integer port;
	
	@XmlElement(name="context", required=false)
	private String context;
	
	@XmlElementWrapper(name="apis", required=true)
	@XmlElement(name="api")
	private List<API> apis;

	public WebApplication() {}
	
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

	public List<API> getApis() {
		return apis;
	}

	public void setApis(List<API> apis) {
		this.apis = apis;
	}

}
