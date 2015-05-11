package org.entrementes.quiron.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="resource")
@XmlAccessorType(XmlAccessType.FIELD)
public class RestResource {
	
	@XmlElement(name="id", required=false)
	private String id;
	
	@XmlElementWrapper(name="methods", required=true)
	@XmlElement(name="method", required=true)
	private List<RestMethod> methods;
	
	public RestResource() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<RestMethod> getMethods() {
		return methods;
	}

	public void setMethods(List<RestMethod> methods) {
		this.methods = methods;
	}

}
