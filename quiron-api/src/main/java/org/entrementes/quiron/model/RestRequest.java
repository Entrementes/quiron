package org.entrementes.quiron.model;

import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="request")
@XmlAccessorType(XmlAccessType.FIELD)
public class RestRequest {
	
	@XmlElementWrapper(name="parameters", required=false)
	private Map<String, RestParameter> parameters;

	public RestRequest() {}
	
	public Map<String, RestParameter> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, RestParameter> parameters) {
		this.parameters = parameters;
	}

}
