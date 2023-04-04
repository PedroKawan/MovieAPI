package br.com.immersionalura.me.omdbapi.stickergenerator.exceptions;

public class ImageExportException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ImageExportException(){}
	
	public ImageExportException(String messagem){
		super(messagem);
	}
	
	public ImageExportException(Throwable throwable){
		super(throwable);
	}
	
	@Override
	public String getMessage() {
		return "Image export ERROR";
	}
}
