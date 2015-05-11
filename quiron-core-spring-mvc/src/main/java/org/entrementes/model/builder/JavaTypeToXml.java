package org.entrementes.model.builder;

import java.lang.reflect.Parameter;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class JavaTypeToXml {

	public String parse(Parameter parameter) {
		Class<?> classe = parameter.getType();
		if(classe.isAssignableFrom(String.class)){
			return "string";
		}
		if(classe.isAssignableFrom(Integer.class)){
			return "int";
		}
		if(classe.isAssignableFrom(Long.class) || classe.isAssignableFrom(BigDecimal.class)){
			return "long";
		}
		if(classe.isAssignableFrom(Float.class) || classe.isAssignableFrom(Double.class)){
			return "double";
		}
		if(classe.isAssignableFrom(Date.class) || classe.isAssignableFrom(Calendar.class)){
			return "date";
		}
		return "UNDEFINED";
	}

}
