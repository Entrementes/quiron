package org.entrementes.quiron.configuration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class QuironHttpConfiguration {
	
	private String filteredResponseFormat;
	
	private String httpControlRequestHeader;
	
	private String httpExpectedStatusHeader;

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

	public String buildControlResponse(String requestUuid) {
		return this.filteredResponseFormat.replace("UUID", requestUuid);
	}

}
