package br.com.immersionalura.me.omdbapi.exception;

public class ResponseFalueException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ResponseFalueException(){}
	
	public ResponseFalueException(String messagem){
		super(messagem);
	}
	
	@Override
	public String getMessage() {
		return "Error in Response";
	}
}
