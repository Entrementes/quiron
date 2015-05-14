package org.entrementes.quiron.model.constants;


public interface HttpProtocolParser {

	Object parseMethod(String methodName);
	
	Object parseStatus(QuironHttpStatus status);
	
	QuironHttpStatus parseStatus(Object status);

}
