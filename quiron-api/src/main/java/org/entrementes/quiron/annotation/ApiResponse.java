package org.entrementes.quiron.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ApiResponse {
	
	public int code();
	
	public String body() default "";
	
	public String description() default "";
	
	public ApiAssertionTest[] assertionTest() default {};

}
