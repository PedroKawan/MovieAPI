package br.com.immersionalura.me.omdbapi.helper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Map;
import java.util.Scanner;

import br.com.immersionalura.me.omdbapi.exception.ClientRequestException;
import br.com.immersionalura.me.omdbapi.exception.FilterException;
import br.com.immersionalura.me.omdbapi.exception.ResponseFalueException;
import br.com.immersionalura.me.omdbapi.exception.SearchError;
import br.com.immersionalura.me.omdbapi.movie.MovieOMDB;

public class SearchMovie {
	Scanner reader = new Scanner(System.in);
	// TOPIC (PTBR) -> Fazer a comunicação HTTP com a OMDB e buscar filmes.
	
	private String url = "https://www.omdbapi.com/?t=";
	private String apikey = "&apikey=1869aa3e";
	
	// Client to request and pick up the information
	private HttpClient client = HttpClient.newHttpClient(); 
	
	// Class MovieOMDB
	private MovieOMDB movie;
	
	// Search -> client -> request -> (infos of site) <- [URL]
	public MovieOMDB searchMovieOMDB(String parameter) {
		
		try {
			requestWebInfos(url+parameter+apikey);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SearchError();
		}
		
		return movie;
	}
	
	// Client -> request -> infos
	private void requestWebInfos(String newUrl)  {
		URI adressOfWebsite = URI.create(newUrl);
		HttpRequest request =
				HttpRequest.newBuilder(adressOfWebsite).GET().build(); //cria uma requisição
		
		
		try {
			HttpResponse<String> responseOfClient = client.send(request, BodyHandlers.ofString()); //cria a resposta
			try {
				filterAttributes(responseOfClient);
			} catch (Exception e) {
				throw new ResponseFalueException();
			}
		} catch(Exception e) {
			throw new ClientRequestException();
		}

	}
	
	
	// Filter infos e put in this.movie(MovieOMDB)
	private void filterAttributes(HttpResponse<String> resp) {
		try {
			String responseOfClient = resp.body();
			Map<String, String> attributesOfMovie = JsonFilter.parse(responseOfClient);
			movie = new MovieOMDB();
			movie.setTitle(attributesOfMovie.get("Title"));
			movie.setPoster(attributesOfMovie.get("Poster"));
			movie.setRating(Double.parseDouble(attributesOfMovie.get("imdbRating")));
		} catch (ResponseFalueException e) {
			throw new FilterException(e.getMessage());
		}
		
	}
	
	// GetMovie: type (MovieOMDB)
	public MovieOMDB getMovie() {
		return this.movie;
	}
}
