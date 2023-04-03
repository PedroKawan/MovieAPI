package br.com.immersionalura.me.omdbapi.exception;

public class ClientRequestException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ClientRequestException(){}
	
	public ClientRequestException(String messagem){
		super(messagem);
	}
	
	@Override
	public String getMessage() {
		return "Client error";
	}
}
