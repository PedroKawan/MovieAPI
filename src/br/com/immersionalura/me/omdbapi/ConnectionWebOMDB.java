package br.com.immersionalura.me.omdbapi;

import java.util.Scanner;

import br.com.immersionalura.me.omdbapi.helper.SearchMovie;
import br.com.immersionalura.me.omdbapi.movie.MovieOMDB;
import br.com.immersionalura.me.omdbapi.stickergenerator.StickerGenerator;

/**
 * @author Pedro Kauã Silva dos Santos <pedrokauass71@gmail.com>
 * @version 1.0
 * @category APIConnection
 * @ApiUsed OMDB
 * @since 02/04/2023
 */
public class ConnectionWebOMDB {
	/**
	 * @Font immersionJava
	 * @AuthorFont Alura
	 * @Email <https://www.alura.com.br/imersao-java>
	 */

	public static void main(String[] args) {
		
		// Read the movie title
		print("Movie Title: ");
		Scanner reader = new Scanner(System.in);
		String parameter = reader.nextLine().replaceAll(" ", "+");
		reader.close();
		
		// Add parameter to MovieOMDB 'movie' of Class 'SeacherMovie' with 'request'
		MovieOMDB movie = new SearchMovie().searchMovieOMDB(parameter);
		
		println("\n\u001b[32m\u001b[1m- MOVIESTATUS -\u001b[m ");
		
		// Method created to print movie status with color and stickers
		movie.printMovieStatus();
		
		
		StickerGenerator generator = new StickerGenerator();
		generator.creatingImage(movie);
	}
	
	// Shortcut to better understand
	private static void println(Object o) {
		System.out.println(o);
	}
	private static void print(Object o) {
		System.out.print(o);
	}
}
