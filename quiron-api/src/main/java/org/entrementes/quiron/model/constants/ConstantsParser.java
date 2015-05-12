package org.entrementes.quiron.model.constants;


public interface ConstantsParser {

	Object parseMethod(String methodName);
	
	Object parseStatus(QuironHttpStatus status);

}
