package org.entrementes.quiron.exception;

public class QuironException extends RuntimeException{

	private static final long serialVersionUID = -4752848278696741957L;
	
	private final ExceptionCode code;
	
	public QuironException() {
		this.code = ExceptionCode.UNMAPPED;
	}
	
	public QuironException(Exception ex) {
		super(ex);
		this.code = ExceptionCode.WRAPPED;
	}
	
	public QuironException(ExceptionCode code) {
		this.code = code;
	}
	
	public QuironException(ExceptionCode code, String message) {
		super(message);
		this.code = code;
	}

	public ExceptionCode getCode() {
		return code;
	}

}
