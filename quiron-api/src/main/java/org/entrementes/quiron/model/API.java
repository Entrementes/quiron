package org.entrementes.quiron.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="api", namespace="org.entrmentes.exu")
@XmlAccessorType(XmlAccessType.FIELD)
public class API {
	
	@XmlElement(name="version", required=false)
	private String version;
	
	@XmlElementWrapper(name="resources", required=true)
	@XmlElement(name="resource")
	private List<Resource> resources;
	
	@XmlElement(name="deprecated",required=true, defaultValue="false")
	private Boolean deprecated;
	
	public API() {}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public Boolean getDeprecated() {
		return deprecated;
	}

	public void setDeprecated(Boolean deprecated) {
		this.deprecated = deprecated;
	}

}
