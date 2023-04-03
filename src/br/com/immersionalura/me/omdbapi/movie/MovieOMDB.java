package br.com.immersionalura.me.omdbapi.movie;

public class MovieOMDB {
	
	//Attributes 
	private String title;
	private String poster;
	private Double rating;
	
	//Constructor
	public MovieOMDB() {
		this("TitleVoid", "PosterVoid", 0.0);
	}
	
	
	public MovieOMDB(String title, String poster, Double rating) {
		this.setTitle(title);
		this.setPoster(poster);
		this.setRating(rating);
	}
	
	//Getters an Setters methods 
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}
	
	
	//I created a toString to Movies :: Style -> {"Attribute":"info";...}
	@Override
	public String toString() {
		String s = "";
		try {
			s +="{Title=" + this.getTitle() +";Poster=" + this.getPoster() + ";imdbRating=" + this.getRating() + "}";
		} catch (NullPointerException e) {
			throw new NullPointerException("The movie has no attributes");
		}
		
		return s;
	}
	
	//Print Status with color and Stickers
	public void printMovieStatus() {
		String s = "";
		s +="\u001b[33m\u001b[1mTitle: \u001b[m" + "\u001b[36m" + this.getTitle() + "\u001b[m\n";
		
		s +="\u001b[33m\u001b[1mPoster: \u001b[m" + "\u001b[36m" + this.getPoster() + "\u001b[m\n";
		s +="\u001b[33m\u001b[1mImdbRating ("+ this.getRating() + "):\u001b[m \u001b[38;2;255;255;0m ";
		
		for(int i = 0; i < (int)Double.parseDouble(this.getRating().toString()); i++) {
			s += "🖤";
		}
		
		System.out.println(s);
	}
	
}
