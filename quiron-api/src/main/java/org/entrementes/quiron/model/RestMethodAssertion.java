package org.entrementes.quiron.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.entrementes.quiron.model.constants.QuironHttpStatus;

@XmlRootElement(name="response")
@XmlAccessorType(XmlAccessType.FIELD)
public class RestMethodAssertion {
	
	@XmlElement(name="code", required=true)
	private QuironHttpStatus code;
	
	@XmlElement(name="body", required=true)
	private String body;
	
	@XmlElement(name="description", required=false)
	private String description;
	
	@XmlElement(name="passed", required=true)
	private Boolean passed;
	
	@XmlElement(name="assertion-parameter", required=false)
	@XmlElementWrapper(name="assertion-parameters", required=false)
	private List<RestResponseAssertionParam> assertionParameters;
	
	public RestMethodAssertion() {
	}

	public QuironHttpStatus getCode() {
		return code;
	}

	public void setCode(QuironHttpStatus quironHttpStatus) {
		this.code = quironHttpStatus;
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

	public Boolean getPassed() {
		return passed;
	}

	public void setPassed(Boolean passed) {
		this.passed = passed;
	}

	public void setAssertionParameters( List<RestResponseAssertionParam> assertionParameters) {
		this.assertionParameters = assertionParameters;
	}

}
