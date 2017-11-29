import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextArea extends JFrame{
	
	public TextArea(){
		
		Image image = null;
		try {
			image = ImageIO.read(new File("shot.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Icon icon = new ImageIcon(image);
		
		
		Object[] possibilities = {2, 3, 4 , 5, 6, 7, 8};
		Integer s = (Integer)JOptionPane.showInputDialog(
		                    null,
		                    "Please, select the number of employees between 2 and 8:\n",
		                    "Game setup",
		                    JOptionPane.PLAIN_MESSAGE,
		                    icon,
		                    possibilities,
		                    "2");

		    return;
	}
}
