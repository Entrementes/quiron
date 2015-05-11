package org.entrementes.quiron.component;

import org.entrementes.quiron.model.RestMethodDependency;

public class UriBuilder {

	public String getDependencyUri(RestMethodDependency dependency){
		return buildUri(dependency.getHost(), 
				        dependency.getPort(), 
				        dependency.getContext(), 
				        dependency.getPath()) ;
	}
	
	private String buildUri(String host, Integer port, String context, String path){
		StringBuilder builder = new StringBuilder();
		if(host != null){
			builder.append("http://");
			builder.append(host);
			if(port != null){
				builder.append(":");
				builder.append(port);
			}
		}
		if(context != null){
			builder.append("/");
			builder.append(context);
			builder.append("/");
		}else{
			if(builder.length() > 0){
				builder.append("/");
			}
		}
		if(path != null){
			builder.append(path);
		}
		return builder.toString();
	}
	
}
