import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.awt.*;

public class MemeGenerator {
		
		private String fileURL;
		private String text;
		private String font;
		private int fontSize;
		private Color color;
		private String stamp;
		private int style;

		public String drawImages(String data) {
			try {
				byte[] utf8 = data.getBytes("UTF-8");
				data = new String(utf8);
				BufferedImage bufferedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
				
				Path path = Paths.get(fileURL);
				long fileSize = Files.size(path);
				
				//test this!!!!
				while(fileSize < 120000 || fileSize > 150000) {
					
					bufferedImage = resizeImage(bufferedImage, 1905, 1100);
				}
				
				
				Graphics2D g = bufferedImage.createGraphics();
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, 200, 200);
				g.setColor(Color.black);
				g.drawString(data, 20, 20);
				g.dispose();
				ImageIO.write(bufferedImage, "JPG", new File("test2.jpg"));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return data;
		}
		
		public MemeGenerator(String fileURL, String text, String font, int style, int fontSize, Color color, String stamp) {
			
			this.fileURL = fileURL;
			this.text = text;
			this.font = font;
			this.fontSize = fontSize;
			this.color = color;
			this.stamp = stamp;
			this.style = style;
			//imput validation
		try {
			BufferedImage image = ImageIO.read(new File(fileURL));
			Scanner keyboard = new Scanner(System.in);
			
			File [] list = new File [6];
			
			Font fontObject = new Font(font, style, fontSize);
			
			
			int heightInc = (image.getHeight(null)/5);
			int widthStart = 10;
			
			int y = heightInc;
			int x = widthStart;
			
			for(int i = 0; i<6 ; i++) {
				BufferedImage newImage = ImageIO.read(new File(fileURL));
				Graphics pic = newImage.getGraphics();
				pic.setFont(fontObject);
				pic.setColor(color);
				if(i==3) {
					y+=(heightInc*3);
					x=widthStart;
				}
				
				
			/**IF WE WANT TO RECENTER IN A BETTER WAY

				public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
				    // Get the FontMetrics
				    FontMetrics metrics = g.getFontMetrics(font);
				    // Determine the X coordinate for the text
				    int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
				    // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
				    int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
				    // Set the font
				    g.setFont(font);
				    // Draw the String
				    g.drawString(text, x, y);
				}*/

				
				
				pic.drawString(text, x, y);
				
				FontMetrics metrics = pic.getFontMetrics(fontObject);
				 
				pic.setFont(new Font("Noto Color Emoji", 100, 100));
				pic.drawString(drawImages(stamp), x + metrics.stringWidth(text), y);
				x+=(image.getWidth(null)/3)-10;
				
				File img = new File("image" + i);
				pic.dispose();
				list[i] = img;
				
				ImageIO.write(newImage, "png", img);
				pic.dispose();
				
							
			}
			System.out.println("where would you like your text?");
			System.out.println("see pop-up");

			Grid ourGrid = new Grid(list);
			
			System.out.println("1. top-left");
			System.out.println("2. top-center");
			System.out.println("3. top-right");
			System.out.println("4. bottom-left");
			System.out.println("5. bottom-center");
			System.out.println("6. bottom-right");
			System.out.print("Enter your choice: ");
			
			int choice = keyboard.nextInt() -1;
			while (choice < 0 || choice > 5) {
				System.out.print("Please enter a choice 1 - 6:");
				choice = keyboard.nextInt();
			}
			
			BufferedImage chosen = ImageIO.read(new File(list[choice].getName()));
			
			
			//when they actually choose which one to keep
			ImageIO.write(chosen, "png", new File(fileURL));
			}	
				/*
				public static void main(String[] args) throws Exception {
				    final BufferedImage image = ImageIO.read(new URL(
				        "http://upload.wikimedia.org/wikipedia/en/2/24/Lenna.png"));

				    Graphics g = image.getGraphics();
				    g.setFont(g.getFont().deriveFont(30f));
				    g.drawString("Hello World!", 100, 100);
				    g.dispose();

				    ImageIO.write(image, "png", new File("test.png"));
				}

			}
			
			ImageIO.write(image, "png", new File(fileURL));
		}*/

		catch (IOException exc) {
			exc.getMessage();

		}
		
		}


		public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
		    BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
		    Graphics2D graphics2D = resizedImage.createGraphics();
		    graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
		    graphics2D.dispose();
		    return resizedImage;
		}
			
	
}

