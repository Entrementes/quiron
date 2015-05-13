package org.entrementes.quiron.component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.entrementes.quiron.model.RestMethodDependency;
import org.entrementes.quiron.model.constants.QuironELParser;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@Component
public class JsonCatalog {
	
	private Map<String, String> externalPayloadReferences;
	
	private Map<String, RestMethodDependency> dependencies;
	
	private JsonParser parser;
	
	private QuironELParser quironParser;
	
	public JsonCatalog() throws FileNotFoundException, IOException {
		this.externalPayloadReferences = new HashMap<String, String>();
		
		this.parser = new JsonParser();
		
		this.quironParser = new QuironELParser();
		
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
		JsonElement responseJson = parser.parse(responseBody);
		JsonElement expectedJson = parser.parse(assertionPayload);
		return responseJson.toString().equals(expectedJson.toString());
	}
	
	public String getJsonBody(String request){
		String key;
		if(quironParser.isQuironEL(request)){
			key = quironParser.unwrap(request);
		}else{
			key = request;
		}
		return this.externalPayloadReferences.get(key);
	}

	public Map<String,RestMethodDependency> listApiDependencies() {
		if(this.dependencies == null){
			RestMethodDependency[] dependencies = new Gson().fromJson(this.externalPayloadReferences.get("quiron-dependencies.json"),RestMethodDependency[].class);
			this.dependencies = Arrays.asList(dependencies).stream().collect(Collectors.toMap(d->d.getId(), Function.identity()));
		}
		return this.dependencies;
	}

}
