package org.entrementes.quiron.configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.entrementes.quiron.component.JsonCatalog;
import org.entrementes.quiron.component.QuironExpressionLanguage;
import org.entrementes.quiron.model.RestMethodDependency;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

public class QuironConfigurationBuilder {

	private Gson jsonService = new Gson();
	
	public QuironConfiguration buildDefault(){
		try{
			ClassLoader classLoader = getClass().getClassLoader();
			File configurationFile = new File(classLoader.getResource("quiron-default-configuration.json").getFile());
			QuironConfiguration result = this.jsonService.fromJson(new FileReader(configurationFile), QuironConfiguration.class);
			result.setExpressionLanguage(buildExpressionLanguage(classLoader, result));
			result.setHttpConfiguration(buildHttpConfiguration(classLoader, result));
			result.setJsonCatalog(buildJsonCatalog(classLoader, result));
			return result;
		}catch( Exception ex){
			throw new RuntimeException(ex);
		}
	}

	private QuironExpressionLanguage buildExpressionLanguage(ClassLoader classLoader, QuironConfiguration result) throws FileNotFoundException {
		QuironExpressionLanguage el = null;
		if(result.getExpressionLanguageDefinitionFile() != null){
			File elDefinitionFile = new File(classLoader.getResource(result.getExpressionLanguageDefinitionFile()).getFile());
			el = this.jsonService.fromJson(new FileReader(elDefinitionFile),QuironExpressionLanguage.class);
			el.setParser(new JsonParser());
		}
		return el;
	}
	
	private QuironHttpConfiguration buildHttpConfiguration(ClassLoader classLoader, QuironConfiguration result) throws FileNotFoundException {
		QuironHttpConfiguration http = null;
		if(result.getHttpConfigurationFile() != null){
			File elDefinitionFile = new File(classLoader.getResource(result.getHttpConfigurationFile()).getFile());
			http = this.jsonService.fromJson(new FileReader(elDefinitionFile),QuironHttpConfiguration.class);
		}
		return http;
	}

	private JsonCatalog buildJsonCatalog(ClassLoader classLoader, QuironConfiguration result) throws IOException {
		Map<String,RestMethodDependency> mappedDependencies = null;
		URL dependenciesFileURL = classLoader.getResource(result.getDependenciesCatalogFile());
		if(dependenciesFileURL != null){
			File dependenciesFile = new File(dependenciesFileURL.getFile());
			RestMethodDependency[] dependencies = this.jsonService.fromJson(new FileReader(dependenciesFile),RestMethodDependency[].class);
			mappedDependencies = Arrays.asList(dependencies).stream().collect(Collectors.toMap(d->d.getId(), Function.identity()));
		}
		
		Map<String,String> mappenPayloadReference = null;
		URL jsonCatalogURL = classLoader.getResource(result.getCatalogLocation());
		if(jsonCatalogURL != null){
			File catalogDirectory = new File(jsonCatalogURL.getFile());
			String[] listedJson = catalogDirectory.list(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.endsWith(".json");
				}
			});
			mappenPayloadReference = new HashMap<String, String>();
			for(String filename : listedJson){
				String jsonBody = IOUtils.toString(classLoader.getResource(result.getCatalogLocation() + filename).openStream());
				mappenPayloadReference.put(filename, jsonBody);
			}
		}
		return new JsonCatalog(mappedDependencies, mappenPayloadReference, result.getExpressionLanguage());
	}
	
	
	
}
