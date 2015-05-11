package org.entrementes.quiron.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ApiMethod {
	
	public String id();
	
	public ApiDependency[] dependencies() default {};

	public ApiResponse[] responses() default {};

}
