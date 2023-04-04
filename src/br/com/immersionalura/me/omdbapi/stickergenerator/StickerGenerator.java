package br.com.immersionalura.me.omdbapi.stickergenerator;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import br.com.immersionalura.me.omdbapi.movie.MovieOMDB;
import br.com.immersionalura.me.omdbapi.stickergenerator.exceptions.CreatingImageException;
import br.com.immersionalura.me.omdbapi.stickergenerator.exceptions.ImageExportException;


public class StickerGenerator {
	
	private MovieOMDB movieGenerator;
	private Integer width;
	private Integer heigth;
	
	private BufferedImage imageURL;
	private BufferedImage imageToSticker;
	private BufferedImage trofy;
	
	private Font font = new Font(Font.SANS_SERIF, Font.BOLD, 35);
	private Font fontTitle = new Font(Font.SANS_SERIF, Font.BOLD, 25);
	private Color color = new Color(255, 255, 0);
	
	public void creatingImage(MovieOMDB movie) {
		// Title, Poster and Movie
		this.movieGenerator = movie;
		String url = movie.getPoster().substring(movie.getPoster().indexOf("http"));
		
		// Limit of size: 0 - 23 (24)
		if (movieGenerator.getTitle().length() >= 24)
			movieGenerator.setTitle(movieGenerator.getTitle().substring(0,23)); 
		
		// Creating Image with URL
		
		try {
		InputStream inputTrofy =
				new FileInputStream("C:/Users/PedroKawan/Documents/Projects/ImmersionJava/MovieAPI/src/br/com/immersionalura/me/omdbapi/stickergenerator/imagens/trophy.png");
		trofy = ImageIO.read(inputTrofy);
		
		//imageURL = ImageIO.read(URI.create(url).toURL());
		InputStream inputImageUrl = new URL(url).openStream();
		imageURL = ImageIO.read(inputImageUrl);
		
		} catch(Exception e) {
			throw new CreatingImageException(e.fillInStackTrace());
		}
		// New Width and new Height to 'new Image'
		width = imageURL.getWidth();
		heigth = imageURL.getWidth() + 200;
		
		// 'new Image'
		imageToSticker = new BufferedImage(width, heigth, BufferedImage.TRANSLUCENT);
		
		// 'Paint process' -> graphicProcess
		try {
			graphicProcess(imageToSticker);
		} catch(RuntimeException e) {
			new RuntimeException("graphicProcess ERROR!");
		}
		
	}
	
	
	public void graphicProcess(BufferedImage image) {
		// Paint process with graphic2D
		Graphics2D g2D = (Graphics2D) imageToSticker.getGraphics();
		g2D.drawImage(imageURL, 0, 0, null);
		g2D.drawImage(trofy, 6, heigth - 48, null);
		
		g2D.setColor(new Color(0, 0, 0));
		g2D.fillRect(0, heigth - 116, 60, 60);
		
		// Paint (Rating IMDB)
		g2D.setFont(font);
		g2D.setColor(color);
		g2D.drawString(movieGenerator.getRating().toString(), 6, heigth - 73);
		
		g2D.setFont(fontTitle);
		String text = "Imdb Classification";
		
		// CenterAlignment
		FontMetrics metrics = g2D.getFontMetrics();
		Rectangle2D rectangle = metrics.getStringBounds(text, g2D);
		double recWidth = rectangle.getWidth();
		double positionText = (width/2) - (recWidth/2);
		
		g2D.drawString(text, (int)positionText + 10, heigth - 20);

		// Export process to address of Computer
		try {
			exportProcess(movieGenerator.getTitle());
		} catch (Exception e) {
			throw new ImageExportException(e.fillInStackTrace());
		}
		
	}
	
	
	public void exportProcess(String titleMovie) {
		// Export the image to directory in computer with parameter 'address'
		try {
			new File ("C:\\Users\\PedroKawan\\Documents\\Projects\\ImmersionJava\\MovieAPI\\stickers").mkdir();
			ImageIO.write(imageToSticker, "png", (new File("C:\\Users\\PedroKawan\\Documents\\Projects\\ImmersionJava\\MovieAPI\\stickers\\" + titleMovie + ".png")));
		} catch(Exception e) {
			throw new RuntimeException("Export Failured");
		}
	}
	
}
