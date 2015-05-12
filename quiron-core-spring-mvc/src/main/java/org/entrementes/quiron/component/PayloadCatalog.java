package org.entrementes.quiron.component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.entrementes.quiron.model.constants.QuironConstants;
import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@Component
public class PayloadCatalog {

	private static final Pattern EL_REGEX = Pattern.compile(QuironConstants.QUIRON_EL);
	
	private Map<String, String> externalPayloadReferences;
	
	private JsonParser parser;
	
	public PayloadCatalog() throws FileNotFoundException, IOException {
		this.externalPayloadReferences = new HashMap<String, String>();
		
		this.parser = new JsonParser();
		
		ClassLoader classLoader = getClass().getClassLoader();
		File catalogDirectory = new File(classLoader.getResource(".").getFile());
		String[] listedJson = catalogDirectory.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".json");
			}
		});
		for(String filename : listedJson){
			String jsonBody = IOUtils.toString(classLoader.getResource(filename).openStream());
			this.externalPayloadReferences.put(filename, jsonBody);
		}
	}
	
	public boolean checkAssertion(String assertionPayload, String responseBody) {
		if(isReferencedPayload(assertionPayload)){
			JsonElement responseJson = parser.parse(responseBody);
			JsonElement expectedJson = parser.parse(this.externalPayloadReferences.get(extractPayloadCode(assertionPayload)));
			return responseJson.toString().equals(expectedJson.toString());
		}else{
			return assertionPayload.equals(responseBody);
		}
	}

	private String extractPayloadCode(String assertionPayload) {
		return assertionPayload.substring(2,assertionPayload.length()-1);
	}

	private boolean isReferencedPayload(String assertionPayload) {
		return EL_REGEX.matcher(assertionPayload).matches();
	}
	
	public String getPayload(String request){
		String key;
		if(isReferencedPayload(request)){
			key = extractPayloadCode(request);
		}else{
			key = request;
		}
		return this.externalPayloadReferences.get(key);
	}

}
