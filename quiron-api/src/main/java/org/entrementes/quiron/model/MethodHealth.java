package org.entrementes.quiron.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="method", namespace="org.entrmentes.exu")
@XmlAccessorType(XmlAccessType.FIELD)
public class MethodHealth {
	
	@XmlElement(name="path", required=false)
	private String path;
	
	@XmlElement(name="request", required=false)
	private Request request;
	
	@XmlElement(name="response", required=true)
	private ResponseHealth response;
	
	@XmlElement(name="type", required=true)
	private String type;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public ResponseHealth getResponse() {
		return response;
	}

	public void setResponse(ResponseHealth response) {
		this.response = response;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
