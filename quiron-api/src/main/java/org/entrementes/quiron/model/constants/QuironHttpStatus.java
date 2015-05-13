package org.entrementes.quiron.model.constants;

public enum QuironHttpStatus {
	
	OK(200), INTERNAL_SERVER_ERROR(500), CREATED(201), BAD_REQUEST(400), NOT_FOUND(404), UNAUTHORIZED(401), CONFLICT(409)
	;

	private final int value;
	
	private QuironHttpStatus(int value) {
		this.value = value;
	}
	
	public Integer value() {
		return value;
	}

}
