package org.entrementes.quiron.model;

import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="request", namespace="org.entrmentes.quiron")
@XmlAccessorType(XmlAccessType.FIELD)
public class Request {
	
	@XmlElementWrapper(name="parameters", required=false)
	private Map<String, Parameter> parameters;

	public Request() {}
	
	public Map<String, Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, Parameter> parameters) {
		this.parameters = parameters;
	}

}
