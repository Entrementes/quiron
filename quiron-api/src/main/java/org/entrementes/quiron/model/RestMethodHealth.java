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
	
	@XmlElementWrapper(name="responses", required=true)
	@XmlElement(name="response", required=true)
	private List<RestMethodAssertion> responses;
	
	@XmlElement(name="method-type", required=true)
	private String methodType;

	@XmlElement(name="id", required=true)
	private String id;

	@XmlElement(name="dependency", required=true)
	@XmlElementWrapper(name="dependencies", required=false)
	private List<RestMethodDependency> dependencies;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<RestMethodAssertion> getResponses() {
		return responses;
	}

	public void setResponses(List<RestMethodAssertion> responses) {
		this.responses = responses;
	}

	public String getMethodType() {
		return methodType;
	}

	public void setMethodType(String type) {
		this.methodType = type;
	}

	public List<RestParameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<RestParameter> parameters) {
		this.parameters = parameters;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public void setDependencies(List<RestMethodDependency> dependencies) {
		this.dependencies = dependencies;
		
	}

}
