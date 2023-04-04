package br.com.immersionalura.me.omdbapi.stickergenerator.exceptions;

public class ImagePaintException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ImagePaintException(){}
	
	public ImagePaintException(String messagem){
		super(messagem);
	}
	
	public ImagePaintException(Throwable throwable){
		super(throwable);
	}
	
	@Override
	public String getMessage() {
		return "Paint image ERROR";
	}
}
