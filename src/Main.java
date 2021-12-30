import java.awt.*;
import java.util.Locale;
import java.util.Scanner;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.awt.image.BufferedImage;

public class Main {

	public static void main(String[] args) {

		Scanner keyboard = new Scanner(System.in);
	
		System.out.println("\nWelcome to the wonderful Meme Generator, where all your " + "memes come true!");

		System.out.println("😁😁😁😁😁😁😁😁😁😁😁😁😁😁😁😁😁😁😁😁😁😁😁😁😁😁😁😁😁😁😁😁😁😁😁😁😁😁😁😁😁");
		System.out.println();
	
		//get user choices
		String fileURL = getFileURL(keyboard);
		String text = getMemeText(keyboard);
		String stamp = getStamp(keyboard);
		String font = getFont(keyboard);
		int style = getFontStyle(keyboard);
		Color color = getTextColor(keyboard);
		
		//fontSize set a medium size value
		int fontSize = 75;

		MemeGenerator mg = new MemeGenerator(fileURL, text, font, style, fontSize, color, stamp);
		mg.create();
		
		//display the meme upon completion
		try {
			new DisplayImage(fileURL).display();
			
		} 
		catch (IOException e) {
		
		}

		System.out.print("Check your image file to see your new meme!!");

	}
	
	public static int getFontStyle(Scanner keyboard) {
		int styleNum;
		int style;
		
		//bold, italic or regular
		System.out.println("Would you like your text to be- ");
		System.out.println("1. Bold");
		System.out.println("2. Italic");
		System.out.println("3. Plain");
		
		System.out.print("Enter your choice: ");

		styleNum = keyboard.nextInt();

		while (styleNum < 1 || styleNum > 4) {
			System.out.print("Please enter a choice 1 - 3:");
			styleNum = keyboard.nextInt();
		}

		switch (styleNum) {

		case 1:
			style = Font.BOLD;
			break;
		case 2:
			style = Font.ITALIC;
			break;
		default:
			style = Font.PLAIN ;
			break;
		}
		return style;
	}

	public static Color getTextColor(Scanner keyboard) {
		int colorNum;
		Color color;
		
		System.out.println("What color would you like the text to be? ");
		System.out.println("1. Red");
		System.out.println("2. Blue");
		System.out.println("3. Green");
		System.out.println("4. Black");
		System.out.println("5. Yellow");
		System.out.println("6. White");
		System.out.print("Enter your choice: ");

		colorNum = keyboard.nextInt();

		while (colorNum < 1 || colorNum > 6) {
			System.out.print("Please enter a choice 1 - 6:");
			colorNum = keyboard.nextInt();
		}

		switch (colorNum) {

		case 1:
			color = Color.RED;
			break;
		case 2:
			color = Color.BLUE;
			break;
		case 3:
			color = Color.GREEN;
			break;
		case 4:
			color = Color.BLACK;
			break;
		case 5:
			color = Color.YELLOW;
			break;
		default:
			color = Color.WHITE;
			break;
		}
		return color;
	}

	public static String getFont(Scanner keyboard) {
		int fontNum;
		String font;
		
		System.out.println("Please see pop up image to see the sample fonts");
		try {
			DisplayImage fontChoices = new DisplayImage("Font_Choices.PNG");
			fontChoices.display();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		System.out.print("Please enter a choice 1 -6:");
		fontNum = keyboard.nextInt();

		while (fontNum < 1 || fontNum > 6) {
			System.out.print("Please enter a choice 1 - 6:");
			fontNum = keyboard.nextInt();
		}

		switch (fontNum) {

		case 1:
			font = "Arial";
			break;
		case 2:
			font = "Forte";
			break;
		case 3:
			font = "Freestyle Script";
			break;
		case 4:
			font = "Georgia";
			break;
		case 5:
			font = "Rod";
			break;
		default:
			font = "Stencil";
			break;
		}
		return font;
	}

	public static String getStamp(Scanner keyboard) {
		
		int stampNum;
		String stamp;
		System.out.println("Which stamp would you like to add (if any)?");

		System.out.println("1. 🌟");
		System.out.println("2. ❤️");
		System.out.println("3. 😂");
		System.out.println("4. 🙂");
		System.out.println("5. 🙊");
		System.out.println("6. None");
		
		System.out.print("Please enter a choice 1 -6:");
		stampNum = keyboard.nextInt();
		
		while (stampNum < 1 || stampNum > 6) {
			System.out.print("Please enter a choice 1 - 6:");
			stampNum = keyboard.nextInt();
		}

		switch (stampNum) {

		case 1:
			stamp = "🌟";
			break;
		case 2:
			stamp = "❤️";
			break;
		case 3:
			stamp = "😂";
			break;
		case 4:
			stamp = "🙂";
			break;
		case 5:
			stamp = "🙊";
			break;
		default:
			stamp = "";
			break;
		}
		return stamp;
	}

	public static String getMemeText(Scanner keyboard) {
		String text;
		System.out.print("Enter your meme text: ");
		text = keyboard.nextLine();
		return text;
	}

	public static String getFileURL(Scanner keyboard) {
		String fileURL;
		System.out.println("What file image do you want to use?");
		System.out.println("Please enter the full URL");
		System.out.print("URL: ");
		fileURL = keyboard.nextLine();

		// validating URL and file size
		boolean notValid = true;
		while (notValid) {
			try {
				Scanner in = new Scanner(new File(fileURL));
				
				Path path = Paths.get(fileURL);
				long fileSize;
				
					fileSize = Files.size(path);

					while(fileSize < 130000 || fileSize > 2000000) {
						
						System.out.println("ERROR: Image size invalid. Please resize image or enter a new image URL. ");
						System.out.println("URL: ");
						fileURL = keyboard.nextLine();
						
						new Scanner(new File(fileURL));
						
						path = Paths.get(fileURL);
						fileSize = Files.size(path);

						notValid = true;
					}
				notValid = false;
				
			} catch (FileNotFoundException e) {
				// the URL is not in a valid form
				notValid = true;
				System.out.print("Invalid URL. Please try again.\nURL: ");
				fileURL = keyboard.nextLine();
			} catch (IOException e) {

				e.printStackTrace();
			} 
		}
		
		return fileURL;
		
		
	}
	

}
