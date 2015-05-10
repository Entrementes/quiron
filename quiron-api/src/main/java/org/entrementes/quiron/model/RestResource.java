package org.entrementes.quiron.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="api", namespace="org.entrmentes.quiron")
@XmlAccessorType(XmlAccessType.FIELD)
public class RestResource {
	
	@XmlElement(name="name", required=false)
	private String name;
	
	@XmlElementWrapper(name="methods", required=true)
	@XmlElement(name="method", required=true)
	private List<RestMethod> methods;
	
	public RestResource() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<RestMethod> getMethods() {
		return methods;
	}

	public void setMethods(List<RestMethod> methods) {
		this.methods = methods;
	}

}
