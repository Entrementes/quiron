package org.entrementes.quiron.component;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.entrementes.quiron.model.RestAPI;
import org.entrementes.quiron.model.RestInterface;

public class SpringWebApplicationBuilder {

	private List<RestAPI> apis;

	private HttpServletRequest request;

	public SpringWebApplicationBuilder apis(List<RestAPI> apis) {
		this.apis = apis;
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
		result.setApis(this.apis);
		return result;
	}

}
