package br.com.immersionalura.me.omdbapi.stickergenerator.exceptions;

public class CreatingImageException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public CreatingImageException(){}
	
	public CreatingImageException(String messagem){
		super(messagem);
	}
	
	public CreatingImageException(Throwable throwable){
		super(throwable);
	}
	
	@Override
	public String getMessage() {
		return "Creating image ERROR";
	}
}
