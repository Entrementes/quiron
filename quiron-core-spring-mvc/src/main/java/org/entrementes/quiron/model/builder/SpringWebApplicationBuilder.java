package org.entrementes.quiron.model.builder;

import javax.servlet.http.HttpServletRequest;

import org.entrementes.quiron.model.RestAPI;
import org.entrementes.quiron.model.RestInterface;

public class SpringWebApplicationBuilder {

	private RestAPI api;

	private HttpServletRequest request;

	public SpringWebApplicationBuilder api(RestAPI api) {
		this.api = api;
		return this;
	}

	public SpringWebApplicationBuilder request(HttpServletRequest request) {
		this.request = request;
		return this;
	}

	public RestInterface build() {
		RestInterface result = new RestInterface();
		result.setContext(this.request.getContextPath());
		result.setPort(request.getServerPort());
		StringBuilder builder = new StringBuilder();
		builder.append(request.getScheme()).append("://")
				.append(request.getServerName());
		result.setHost(builder.toString());
		result.setApi(this.api );
		return result;
	}

}
