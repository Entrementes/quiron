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
	private Map<String, RestParameter> parameters;

	public Request() {}
	
	public Map<String, RestParameter> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, RestParameter> parameters) {
		this.parameters = parameters;
	}

}
