package org.entrementes.quiron.component;

import org.entrementes.quiron.exception.ExceptionCode;
import org.entrementes.quiron.exception.QuironException;
import org.entrementes.quiron.model.constants.ConstantsParser;
import org.entrementes.quiron.model.constants.QuironHttpStatus;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class SpringConstatsParser implements ConstantsParser{

	@Override
	public Object parseMethod(String methodName) {
		if("GET".equalsIgnoreCase(methodName)) return HttpMethod.GET;
		if("POST".equalsIgnoreCase(methodName)) return HttpMethod.POST;
		if("PUT".equalsIgnoreCase(methodName)) return HttpMethod.PUT;
		if("DELETE".equalsIgnoreCase(methodName)) return HttpMethod.DELETE;
		if("OPTIONS".equalsIgnoreCase(methodName)) return HttpMethod.OPTIONS;
		if("PATCH".equalsIgnoreCase(methodName)) return HttpMethod.PATCH;
		if("HEAD".equalsIgnoreCase(methodName)) return HttpMethod.HEAD;
		if("TRACE".equalsIgnoreCase(methodName)) return HttpMethod.TRACE;
		throw new QuironException(ExceptionCode.BAD_REQUEST_BODY);
	}

	@Override
	public Object parseStatus(QuironHttpStatus status) {
		if(QuironHttpStatus.OK.equals(status)) return HttpStatus.OK;
		if(QuironHttpStatus.INTERNAL_SERVER_ERROR.equals(status)) return HttpStatus.INTERNAL_SERVER_ERROR;
		throw new QuironException(ExceptionCode.BAD_REQUEST_BODY);
	}

	
}
