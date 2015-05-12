package org.entrementes.quiron.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.entrementes.quiron.model.constants.QuironHttpStatus;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ApiResponse {
	
	public QuironHttpStatus code();
	
	public String body() default "";
	
	public String description() default "";
	
	public String requestBody() default "";

	public String requestParams() default "";

}
