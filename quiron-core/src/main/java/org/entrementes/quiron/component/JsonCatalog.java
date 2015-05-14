package org.entrementes.quiron.component;

import java.util.Map;

import org.entrementes.quiron.model.RestMethodDependency;


public class JsonCatalog {

	private Map<String, RestMethodDependency> listedDependencies;
	
	private Map<String, String> externalPayloadReferences;

	private QuironExpressionLanguage parser;

	public JsonCatalog(Map<String, RestMethodDependency> listedDependencies, Map<String, String> externalPayloadReferences, QuironExpressionLanguage parser) {
		this.listedDependencies = listedDependencies;
		this.externalPayloadReferences = externalPayloadReferences;
		this.parser = parser;
	}
	
	public boolean checkAssertion(String assertionPayload, String responseBody) {
		return parser.checkEquality(assertionPayload,responseBody);
	}
	
	public String getJsonBody(String request){
		String key;
		if(parser.isQuironEL(request)){
			key = parser.unwrap(request);
		}else{
			key = request;
		}
		return this.externalPayloadReferences.get(key);
	}

	public Map<String,RestMethodDependency> listApiDependencies() {
		return this.listedDependencies;
	}

}
