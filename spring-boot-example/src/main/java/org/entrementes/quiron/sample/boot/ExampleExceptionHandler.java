package org.entrementes.quiron.sample.boot;

import javax.servlet.http.HttpServletRequest;

import org.entrementes.quiron.exception.ExceptionCode;
import org.entrementes.quiron.exception.QuironException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExampleExceptionHandler {

    public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value={QuironException.class})
    public ResponseEntity<?> defaultErrorHandler(HttpServletRequest request, QuironException qe) {
        if(qe.getCode().equals(ExceptionCode.NOT_FOUND)){
        	return new ResponseEntity<String>(qe.getMessage(), HttpStatus.NOT_FOUND);
        }else{
        	return new ResponseEntity<String>(qe.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ExceptionHandler(value={Exception.class})
    public ResponseEntity<?> defaultErrorHandler(HttpServletRequest request, Exception qe) {
        return new ResponseEntity<String>(qe.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}