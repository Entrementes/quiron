package org.entrementes.quiron.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="dependency")
@XmlAccessorType(XmlAccessType.FIELD)
public class RestMethodDependency {
	
	@XmlElement(name="id", required=true)
	private String id;
	
	@XmlElement(name="host", required=true)
	private String host;
	
	@XmlElement(name="port", required=true, defaultValue="80")
	private Integer port;
	
	@XmlElement(name="context", required=false)
	private String context;
	
	@XmlElement(name="version", required=false)
	private String version;
	
	@XmlElement(name="path", required=true)
	private String path;
	
	@XmlElement(name="type", required=true)
	private String type;
	
	public RestMethodDependency() {}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
