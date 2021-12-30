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


		
		public MemeGenerator(String fileURL, String text, String font, int style, int fontSize, Color color, String stamp) {
		
			//validate file
			try {
				this.fileURL = fileURL;
				new Scanner(new File(fileURL));
				
			} catch (FileNotFoundException e) {
				e.getMessage();
			}
		
			this.text = text;
			this.font = font;
			this.fontSize = fontSize;
			this.color = color;
			this.stamp = stamp;
			this.style = style;
			
		}
		
		public void create() {
		try {
			BufferedImage image = ImageIO.read(new File(fileURL));
			Scanner keyboard = new Scanner(System.in);
			
			//user chooses which meme they want based on the options in the grid
			String fileName = populateSampleMemeGrid(image, keyboard);
			
			BufferedImage chosen = ImageIO.read(new File(fileName));
			
			//create the chosen one
			ImageIO.write(chosen, "png", new File(fileURL));
			}	
				
		catch (IOException exc) {
			exc.getMessage();

		}
		
		}

		private String populateSampleMemeGrid(BufferedImage image, Scanner keyboard) throws IOException {
			File [] list = new File [6];
			
			Font fontObject = new Font(font, style, fontSize);
			
		    // Get the FontMetrics to place the text proportionately on the image
			BufferedImage imge = ImageIO.read(new File(fileURL));
			Graphics g = imge.getGraphics();
		    FontMetrics metrics = g.getFontMetrics(fontObject);

			int yInc = (image.getHeight()- metrics.getHeight()) / 4 + metrics.getAscent();
			int xInc = (image.getWidth() - metrics.stringWidth(text)) / 3;
			
			int y = yInc;
			int widthStart = 50;
			int x = widthStart ;
			
			for(int i = 0; i<6 ; i++) {
				BufferedImage newImage = ImageIO.read(new File(fileURL));
				Graphics pic = newImage.getGraphics();
				pic.setFont(fontObject);
				pic.setColor(color);
				
				//drop down a line and start again from the left
				if(i==3) {
					y+=(yInc*2);
					x= widthStart;
				}

				pic.drawString(text, x, y);
				
				//set the font to make sure stamp does not vary based on text font
				pic.setFont(new Font("Noto Color Emoji", 100, 100));
				pic.drawString(drawImages(stamp), x + metrics.stringWidth(text), y);
				x+=xInc;
				
				//name the file with it's index number
				File img = new File("image" + i);
				pic.dispose();
				list[i] = img;
				
				ImageIO.write(newImage, "png", img);
				pic.dispose();
				
							
			}
			
			//call the methdo to get the choice of font based on the grid images
			int choice = positionChoice(keyboard, list);
			return list[choice].getName();
		}

		private int positionChoice(Scanner keyboard, File[] list) {
			System.out.println("where would you like your text?");
			System.out.println("see pop-up");

			//display the grid
			new Grid(list);
			
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
			return choice;
		}

		//method to draw the stamp
		private String drawImages(String data) {
			try {
				byte[] utf8 = data.getBytes("UTF-8");
				data = new String(utf8);
				BufferedImage bufferedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
				
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

		
	
}

