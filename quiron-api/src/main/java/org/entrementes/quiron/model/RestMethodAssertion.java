package org.entrementes.quiron.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="response")
@XmlAccessorType(XmlAccessType.FIELD)
public class RestMethodAssertion {
	
	@XmlElement(name="code", required=true)
	private Integer code;
	
	@XmlElement(name="body", required=true)
	private String body;
	
	@XmlElement(name="description", required=false)
	private String description;
	
	@XmlElement(name="assertion-payload", required=false)
	private String assertionPayload;
	
	@XmlElement(name="passed", required=true)
	private Boolean passed;
	
	public RestMethodAssertion() {
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAssertionPayload() {
		return assertionPayload;
	}

	public void setAssertionPayload(String assertionPayload) {
		this.assertionPayload = assertionPayload;
	}

	public Boolean getPassed() {
		return passed;
	}

	public void setPassed(Boolean passed) {
		this.passed = passed;
	}

}
