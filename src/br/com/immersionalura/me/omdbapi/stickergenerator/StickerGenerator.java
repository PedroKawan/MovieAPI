package br.com.immersionalura.me.omdbapi.stickergenerator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URI;

import javax.imageio.ImageIO;

import br.com.immersionalura.me.omdbapi.movie.MovieOMDB;
import br.com.immersionalura.me.omdbapi.stickergenerator.exceptions.CreatingImageException;
import br.com.immersionalura.me.omdbapi.stickergenerator.exceptions.ImageExportException;


public class StickerGenerator {
	private BufferedImage imageURL;
	private BufferedImage imageToSticker;
	private Font font = new Font(Font.SANS_SERIF, Font.BOLD, 18);
	
	public void creatingImage(MovieOMDB movie) {
		// Title end Poster of Movie
		String titleMovie = movie.getTitle();
		String url = movie.getPoster().substring(movie.getPoster().indexOf("http"));
		
		// Limit of size: 0 - 23 (24)
		if (titleMovie.length() >= 24)
			titleMovie = titleMovie.substring(0,23);
		
		// Creating Image with URL
		try {
		imageURL = ImageIO.read(URI.create(url).toURL());
		//  InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BNDE3ODcxYzMtY2YzZC00NmNlLWJiNDMtZDViZWM2MzIxZDYwXkEyXkFqcGdeQXVyNjAwNDUxODI@._V1_SX300.jpg\"").openStream();
		} catch(Exception e) {
			throw new CreatingImageException(e.fillInStackTrace());
		}
		// New Width and new Height to 'new Image'
		int newWidth = imageURL.getWidth();
		int newHeigth = imageURL.getWidth() + 200;
		
		// 'new Image'
		imageToSticker = new BufferedImage(newWidth, newHeigth, BufferedImage.TRANSLUCENT);
		
		// 'Paint process' -> graphicProcess
		try {
			graphicProcess(imageToSticker, titleMovie, newHeigth);
		} catch(RuntimeException e) {
			new RuntimeException("graphicProcess ERROR!");
		}
		
	}
	
	
	public void graphicProcess(BufferedImage image, String titleMovie, int newHeigth) {
		// Paint process with graphic2D
		Graphics2D g2D = (Graphics2D) imageToSticker.getGraphics();
		g2D.drawImage(imageURL, 0, 0, null);
		g2D.setFont(font);
		g2D.setColor(new Color(0, 0, 0));
		g2D.drawString(titleMovie, 0, newHeigth - 15);
		 
		// Export process to address of Computer
		try {
			exportProcess(titleMovie);
		} catch (Exception e) {
			throw new ImageExportException(e.fillInStackTrace());
		}
		
	}
	
	
	public void exportProcess(String titleMovie) {
		// Export the image to directory in computer with parameter 'address'
		try {
			ImageIO.write(imageToSticker, "png", (new File("C:\\Users\\PedroKawan\\Documents\\Projects\\ImmersionJava\\MovieAPI\\Imagens\\" + titleMovie + ".png")));
		} catch(Exception e) {
			throw new RuntimeException("Export Failured");
		}
	}
	
}
