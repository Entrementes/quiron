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
	
	private String filteredResponseFormat;
	
	private String httpControlRequestHeader;
	
	private String httpExpectedStatusHeader;
	
	private String expressionLanguageDefinitionFile;
	
	private String dependenciesCatalogFile;
	
	private QuironExpressionLanguage expressionLanguage;
	
	private JsonCatalog jsonCatalog;

	public String getFilteredResponseFormat() {
		return filteredResponseFormat;
	}

	public void setFilteredResponseFormat(String filteredResponseFormat) {
		this.filteredResponseFormat = filteredResponseFormat;
	}

	public String getHttpControlRequestHeader() {
		return httpControlRequestHeader;
	}

	public void setHttpControlRequestHeader(String httpControlRequestHeader) {
		this.httpControlRequestHeader = httpControlRequestHeader;
	}

	public String getHttpExpectedStatusHeader() {
		return httpExpectedStatusHeader;
	}

	public void setHttpExpectedStatusHeader(String httpExpectedStatusHeader) {
		this.httpExpectedStatusHeader = httpExpectedStatusHeader;
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

	
	
}
