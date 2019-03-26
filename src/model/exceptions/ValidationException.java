package model.exceptions;

import java.util.HashMap;
import java.util.Map;

public class ValidationException extends RuntimeException {

	private Map<String, String> erros = new HashMap<>();
	
	private static final long serialVersionUID = 1L;
	
	public ValidationException(String msg) {
		super(msg);
	}
	
	public  Map<String, String> getErros(){
		return erros;
	}
	
	public void addError(String fildName, String errorMessage) {
		erros.put(fildName, errorMessage);

	}	
}
