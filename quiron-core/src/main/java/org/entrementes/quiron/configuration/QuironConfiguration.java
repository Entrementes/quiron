package org.entrementes.quiron.configuration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.entrementes.quiron.component.JsonCatalog;
import org.entrementes.quiron.component.QuironExpressionLanguage;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class QuironConfiguration {

	private String catalogLocation;
	
	private String expressionLanguageDefinitionFile;
	
	private String dependenciesCatalogFile;
	
	private String httpConfigurationFile;
	
	private QuironExpressionLanguage expressionLanguage;
	
	private QuironHttpConfiguration httpConfiguration;
	
	private JsonCatalog jsonCatalog;

	public QuironHttpConfiguration getHttpConfiguration() {
		return httpConfiguration;
	}

	public void setHttpConfiguration(QuironHttpConfiguration httpConfiguration) {
		this.httpConfiguration = httpConfiguration;
	}

	public String getExpressionLanguageDefinitionFile() {
		return expressionLanguageDefinitionFile;
	}
	
	public void setExpressionLanguageDefinitionFile(
			String expressionLanguageDefinitionFile) {
		this.expressionLanguageDefinitionFile = expressionLanguageDefinitionFile;
	}
	
	public QuironExpressionLanguage getExpressionLanguage() {
		return expressionLanguage;
	}

	public void setExpressionLanguage(QuironExpressionLanguage expressionLanguage) {
		this.expressionLanguage = expressionLanguage;
	}

	public String getCatalogLocation() {
		return catalogLocation;
	}

	public void setCatalogLocation(String catalogLocation) {
		this.catalogLocation = catalogLocation;
	}

	public String getDependenciesCatalogFile() {
		return dependenciesCatalogFile;
	}

	public void setDependenciesCatalogFile(String dependenciesCatalogFile) {
		this.dependenciesCatalogFile = dependenciesCatalogFile;
	}

	public JsonCatalog getJsonCatalog() {
		return jsonCatalog;
	}
	
	public void setJsonCatalog(JsonCatalog catalog) {
		this.jsonCatalog = catalog;
	}

	public String getHttpConfigurationFile() {
		return httpConfigurationFile;
	}

	public void setHttpConfigurationFile(String httpConfigurationFile) {
		this.httpConfigurationFile = httpConfigurationFile;
	}

}
