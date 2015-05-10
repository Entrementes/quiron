package org.entrementes.quiron.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="method", namespace="org.entrmentes.quiron")
@XmlAccessorType(XmlAccessType.FIELD)
public class Method {
	
	@XmlElement(name="path", required=false)
	private String path;
	
	@XmlElement(name="request", required=false)
	private Request request;
	
	@XmlElementWrapper(name="responses", required=true)
	@XmlElement(name="response", required=true)
	private List<Response> responses;
	
	@XmlElementWrapper(name="dependencies", required=false)
	@XmlElement(name="dependency", required=true)
	private List<MethodDependency> dependencies;
	
	@XmlElement(name="type", required=true)
	private String type;

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<Response> getResponses() {
		return responses;
	}

	public void setResponses(List<Response> responses) {
		this.responses = responses;
	}
	
	public List<MethodDependency> getDependencies() {
		return dependencies;
	}

	public void setDependencies(List<MethodDependency> dependencies) {
		this.dependencies = dependencies;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
