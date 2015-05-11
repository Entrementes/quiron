package org.entrementes.quiron.model;

import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="response-health")
@XmlAccessorType(XmlAccessType.FIELD)
public class RestResponseHealth {
	
	@XmlElement(name="code", required=true)
	private Integer code;
	
	@XmlElement(name="body", required=true)
	private String body;
	
	@XmlElement(name="description", required=false)
	private String description;
	
	@XmlElementWrapper(name="assertion-test", required=false)
	private Map<String, Object> assertionTest;

	@XmlElement(name="passed", required=false)
	private Boolean passed;
	
	public RestResponseHealth() {}

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

	public Map<String, Object> getAssertionTest() {
		return assertionTest;
	}

	public void setAssertionTest(Map<String, Object> assertionTest) {
		this.assertionTest = assertionTest;
	}

	public Boolean getPassed() {
		return passed;
	}

	public void setPassed(Boolean passed) {
		this.passed = passed;
	}
	
}