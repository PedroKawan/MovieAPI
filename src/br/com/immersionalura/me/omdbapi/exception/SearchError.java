package br.com.immersionalura.me.omdbapi.exception;

public class SearchError extends Error{
	private static final long serialVersionUID = 1L;

	public SearchError(){}
	
	public SearchError(String messagem){
		super(messagem);
	}
	
	@Override
	public String getMessage() {
		return "Error in Search Movie";
	}
}
