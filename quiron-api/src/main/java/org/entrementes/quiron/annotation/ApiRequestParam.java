package org.entrementes.quiron.annotation;

import org.entrementes.quiron.model.constants.QuironParamType;

public @interface ApiRequestParam {

	String name() default "";

	QuironParamType type();

	String value();

}
