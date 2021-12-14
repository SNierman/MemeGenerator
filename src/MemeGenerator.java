import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.Scanner;
import java.awt.*;

public class MemeGenerator {
		
		private String fileURL;
		private String text;
		private String font;
		private int fontSize;
		private Color color;

		public String drawImages(String data) {
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
		
		public MemeGenerator(String fileURL, String text, String font, int fontSize, Color color) {
			
			this.fileURL = fileURL;
			this.text = text;
			this.font = font;
			this.fontSize = fontSize;
			this.color = color;
		
			//imput validation
		try {
			BufferedImage image = ImageIO.read(new File(fileURL));
			Scanner keyboard = new Scanner(System.in);
			
			File [] list = new File [6];
			
			Font fontObject = new Font(font, Font.BOLD, fontSize);
			
			
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
				
				//pic.drawString(text, x, y);
				pic.dispose();

				pic.drawString(drawImages("😁"), x, y);
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



			
	
}

