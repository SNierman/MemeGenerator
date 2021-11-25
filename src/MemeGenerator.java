import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.*;

public class MemeGenerator {
		
		private String fileURL;
		private String text;
		private String font;
		private int fontSize;
		private Color color;

		
		public MemeGenerator(String fileURL, String text, String font, int fontSize, Color color) {
			
			this.fileURL = fileURL;
			this.text = text;
			this.font = font;
			this.fontSize = fontSize;
			this.color = color;
		
			//imput validation
		try {
			BufferedImage image = ImageIO.read(new File(fileURL));
			
			Font fontObject = new Font(font, Font.BOLD, fontSize);

			Graphics g = image.getGraphics();
			g.setFont(fontObject);
			g.setColor(color);
			g.drawString(text, 100, 100);
			
			
			ImageIO.write(image, "png", new File(fileURL));
		}

		catch (IOException exc) {
			exc.getMessage();

		}
		
		}
	
}
