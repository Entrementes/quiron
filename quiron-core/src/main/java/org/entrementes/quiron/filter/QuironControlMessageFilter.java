package org.entrementes.quiron.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.entrementes.quiron.configuration.QuironHttpConfiguration;

public class QuironControlMessageFilter implements Filter{

	private QuironHttpConfiguration configuration;

	public QuironControlMessageFilter(QuironHttpConfiguration configuration) {
		this.configuration = configuration;
	}
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String controlPermission = httpRequest.getHeader(configuration.getHttpControlRequestHeader());
		try{
			if(controlPermission != null){
				try{
					String statusControl = httpRequest.getHeader(configuration.getHttpExpectedStatusHeader());
					HttpServletResponse httpResponse = (HttpServletResponse) response;
					httpResponse.setStatus(Integer.parseInt(statusControl));
					httpResponse.setContentType("application/json");
					String responseBody = this.configuration.getFilteredResponseFormat().replace("UUID", controlPermission);
					httpResponse.getWriter().print(responseBody);
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}else{
				chain.doFilter(request, response);
			}
		}catch(NullPointerException ex){
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig configuration) throws ServletException {
	}
}
