package org.entrementes.quiron.model.constants;

import java.util.regex.Pattern;

public class QuironELParser {
	
	private static final Pattern EL_REGEX = Pattern.compile(QuironConstants.QUIRON_EL);
	
	public boolean isQuironEL(String id) {
		return EL_REGEX.matcher(id).matches();
	}
	
	public String unwrap(String elWrapped) {
		return elWrapped.substring(2,elWrapped.length()-1);
	}

}
