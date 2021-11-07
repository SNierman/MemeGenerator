import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class DisplayImage {
	
		private String fileToDisplay;
	
	    public DisplayImage(String fileToDisplay) throws IOException
	    {
	    	
	    	this.fileToDisplay = fileToDisplay;
	    	
	        BufferedImage img=ImageIO.read(new File(fileToDisplay));
	        ImageIcon icon=new ImageIcon(img);
	        JFrame frame=new JFrame();
	        frame.setLayout(new FlowLayout());
	        frame.setSize(200,300);
	        JLabel lbl=new JLabel();
	        lbl.setIcon(icon);
	        frame.add(lbl);
	        frame.setVisible(true);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    }
	
}


