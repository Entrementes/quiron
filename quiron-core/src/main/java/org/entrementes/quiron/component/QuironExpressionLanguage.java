package org.entrementes.quiron.component;

import java.util.regex.Pattern;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class QuironExpressionLanguage {

	private String quironTokenFormat;

	private Integer substringPrefixLength;
	
	private Integer substringSuffixLength;
	
	private JsonParser parser; 
	
	public QuironExpressionLanguage() {
	}
	
	public String getQuironTokenFormat() {
		return quironTokenFormat;
	}

	public void setQuironTokenFormat(String quironTokenFormat) {
		this.quironTokenFormat = quironTokenFormat;
	}
	
	public Integer getSubstringPrefixLength() {
		return substringPrefixLength;
	}

	public void setSubstringPrefixLength(Integer substringPrefixLength) {
		this.substringPrefixLength = substringPrefixLength;
	}

	public Integer getSubstringSuffixLength() {
		return substringSuffixLength;
	}

	public void setSubstringSuffixLength(Integer substringSuffixLength) {
		this.substringSuffixLength = substringSuffixLength;
	}
	
	public JsonParser getParser() {
		return parser;
	}

	public void setParser(JsonParser parser) {
		this.parser = parser;
	}

	public boolean isQuironEL(String token) {
		if( token == null ) return false;
		return Pattern.matches(this.quironTokenFormat,token);
	}
	
	public String unwrap(String elWrapped) {
		return elWrapped.substring(this.substringPrefixLength,elWrapped.length()-this.substringSuffixLength);
	}
	
	public boolean checkEquality(String assertionPayload, String responseBody) {
		JsonElement responseJson = parser.parse(responseBody);
		JsonElement expectedJson = parser.parse(assertionPayload);
		return expectedJson.equals(responseJson);
	}
}
