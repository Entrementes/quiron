package org.entrementes.quiron.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="request", namespace="org.entrmentes.quiron")
@XmlAccessorType(XmlAccessType.FIELD)
public class Parameter {
	
	@XmlElement(name="name", required=false)
	private String name;
	
	@XmlElement(name="type", required=true, defaultValue="string")
	private String type;
	
	@XmlElement(name="style", required=true, defaultValue="query")
	private String style;
	
	@XmlElement(name="required", required=true, defaultValue="false")
	private Boolean required;
	
	public Parameter() {}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public Boolean getRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

}
