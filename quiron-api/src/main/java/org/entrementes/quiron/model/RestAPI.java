package org.entrementes.quiron.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="api")
@XmlAccessorType(XmlAccessType.FIELD)
public class RestAPI {
	
	@XmlElement(name="version", required=false)
	private String version;
	
	@XmlElement(name="id", required=true)
	private String id;
	
	@XmlElementWrapper(name="resources", required=true)
	@XmlElement(name="resource")
	private List<RestResource> resources;
	
	@XmlElement(name="deprecated",required=true, defaultValue="false")
	private Boolean deprecated;
	
	public RestAPI() {}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<RestResource> getResources() {
		return resources;
	}

	public void setResources(List<RestResource> resources) {
		this.resources = resources;
	}

	public Boolean getDeprecated() {
		return deprecated;
	}

	public void setDeprecated(Boolean deprecated) {
		this.deprecated = deprecated;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

}
