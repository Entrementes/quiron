package org.entrementes.quiron.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="api-health", namespace="org.entrmentes.quiron")
@XmlAccessorType(XmlAccessType.FIELD)
public class RestAPIHealth {
	
	@XmlElement(name="version", required=false)
	private String version;
	
	@XmlElementWrapper(name="resources")
	@XmlElement(name="resource")
	private List<RestResourceHealth> resources;

	public RestAPIHealth() {}
	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<RestResourceHealth> getResources() {
		return resources;
	}

	public void setResources(List<RestResourceHealth> resources) {
		this.resources = resources;
	}
	
}
