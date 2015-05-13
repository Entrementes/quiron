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
	
	@XmlElement(name="expected-code", required=true)
	private QuironHttpStatus expectedCode;
	
	@XmlElement(name="recieved-code", required=true)
	private QuironHttpStatus recievedCode;
	
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

	public QuironHttpStatus getExpectedCode() {
		return expectedCode;
	}

	public void setExpectedCode(QuironHttpStatus expectedCode) {
		this.expectedCode = expectedCode;
	}

	public QuironHttpStatus getRecievedCode() {
		return recievedCode;
	}

	public void setRecievedCode(QuironHttpStatus recievedCode) {
		this.recievedCode = recievedCode;
	}

	public List<RestResponseAssertionParam> getAssertionParameters() {
		return assertionParameters;
	}

}
