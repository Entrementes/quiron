package org.entrementes.quiron.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.entrementes.quiron.model.constants.QuironParamType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RestResponseAssertionParam {

	@XmlElement
	private QuironParamType type;
	
	@XmlElement
	private String name;
	
	@XmlElement
	private String value;
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public QuironParamType getType() {
		return type;
	}
	
	public void setType(QuironParamType type) {
		this.type = type;
	}

}
