package org.entrementes.quiron.component;

import org.entrementes.quiron.model.MethodDependency;

public class UriBuilder {

	public String getDependencyUri(MethodDependency dependency){
		return buildUri(dependency.getHost(), 
				        dependency.getPort(), 
				        dependency.getContext(), 
				        dependency.getVersion(), 
				        dependency.getPath()) ;
	}
	
	private String buildUri(String host, Integer port, String context, String version, String path){
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
		if(version != null){
			builder.append(version);
			builder.append("/");
		}
		if(path != null){
			builder.append(path);
		}
		return builder.toString();
	}
	
}
