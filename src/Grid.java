import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class Grid extends JFrame {

	public Grid(File [] list) {

		init(list);
		//super.setSize(1000, 1000);
		super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    super.setAlwaysOnTop(true);
		super.pack();
		super.setLocationRelativeTo(null);
		super.setVisible(true);
	
		// setting grid layout with rows, cols, hgap, and vgap
		super.setLayout(new GridLayout(2, 3, 1, 1));

		
	}

	private void init(File [] list) {

		File file = new File("images");

		// getting files name from folder
		for (int i = 0; i < list.length; i++) {

			JLabel label = new JLabel();

			// setting icon
			label.setIcon(new ImageIcon(
					new ImageIcon(list[i].getName()).getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT)));

			add(label);

		}

	}


}

