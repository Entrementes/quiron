package org.entrementes.quiron.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="resource-health")
@XmlAccessorType(XmlAccessType.FIELD)
public class RestResourceHealth {
	
	@XmlElement(name="name", required=false)
	private String name;
	
	@XmlElementWrapper(name="methods", required=true)
	@XmlElement(name="method", required=true)
	private List<RestMethodHealth> methods;
	
	public RestResourceHealth() {}

	public String getName() {
		return name;
	}

	public void setId(String name) {
		this.name = name;
	}

	public List<RestMethodHealth> getMethods() {
		return methods;
	}

	public void setMethods(List<RestMethodHealth> methods) {
		this.methods = methods;
	}

}
