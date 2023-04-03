package br.com.immersionalura.me.omdbapi.exception;

public class FilterException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public FilterException(){}
	
	public FilterException(String messagem){
		super(messagem);
	}
	
	@Override
	public String getMessage() {
		return "Error in Filter";
	}
}
