package org.entrementes.quiron.annotation;


public @interface ApiDependency {
	
	public String id();
	
	public String host() default "";
	
	public int port() default 80;
	
	public String context() default "";
	
	public String path() default "";

	public String methodType() default "GET";

}
