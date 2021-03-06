package org.entrementes.quiron.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.entrementes.quiron.model.constants.QuironParamType;

@XmlRootElement(name="request")
@XmlAccessorType(XmlAccessType.FIELD)
public class RestParameter {
	
	@XmlElement(name="name", required=false)
	private String name;
	
	@XmlElement(name="type", required=true, defaultValue="string")
	private String type;
	
	@XmlElement(name="style", required=true, defaultValue="QUERY")
	private QuironParamType style;
	
	@XmlElement(name="required", required=true, defaultValue="false")
	private Boolean required;
	
	public RestParameter() {}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public QuironParamType getStyle() {
		return style;
	}

	public void setStyle(QuironParamType query) {
		this.style = query;
	}

	public Boolean getRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
