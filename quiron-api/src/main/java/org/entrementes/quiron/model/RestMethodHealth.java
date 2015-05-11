package org.entrementes.quiron.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="method-health")
@XmlAccessorType(XmlAccessType.FIELD)
public class RestMethodHealth {
	
	@XmlElement(name="path", required=false)
	private String path;
	
	@XmlElement(name="parameter", required=true)
	@XmlElementWrapper(name="parameters", required=false)
	private List<RestParameter> parameters;
	
	@XmlElement(name="response", required=true)
	private RestResponseHealth response;
	
	@XmlElement(name="type", required=true)
	private String type;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public RestResponseHealth getResponse() {
		return response;
	}

	public void setResponse(RestResponseHealth response) {
		this.response = response;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<RestParameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<RestParameter> parameters) {
		this.parameters = parameters;
	}

}
