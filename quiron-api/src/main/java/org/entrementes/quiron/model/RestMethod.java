package org.entrementes.quiron.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "method")
@XmlAccessorType(XmlAccessType.FIELD)
public class RestMethod {

	@XmlElement(name = "path", required = false)
	private String path;

	@XmlElement(name = "request", required = false)
	private RestRequest request;

	@XmlElementWrapper(name = "responses", required = true)
	@XmlElement(name = "response", required = true)
	private List<RestResponse> responses;

	@XmlElementWrapper(name = "dependencies", required = false)
	@XmlElement(name = "dependency", required = true)
	private List<RestMethodDependency> dependencies;

	@XmlElement(name = "type", required = true)
	private String type;

	@XmlElement(name = "id", required = true)
	private String id;

	public RestRequest getRequest() {
		return request;
	}

	public void setRequest(RestRequest request) {
		this.request = request;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<RestResponse> getResponses() {
		return responses;
	}

	public void setResponses(List<RestResponse> responses) {
		this.responses = responses;
	}

	public List<RestMethodDependency> getDependencies() {
		return dependencies;
	}

	public void setDependencies(List<RestMethodDependency> dependencies) {
		this.dependencies = dependencies;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
