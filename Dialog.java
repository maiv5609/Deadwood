import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Dialog extends JFrame{

	public Dialog(){
		Image image = null;
		try {
			image = ImageIO.read(new File("shot.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Icon icon = new ImageIcon(image);
		
		JOptionPane optionPane = new JOptionPane();
		 
		  Object[] possibilities = {2, 3, 4 , 5, 6, 7, 8};
		  Integer numberOfPlayers = (Integer)optionPane.showInputDialog(
		                    null,
		                    "Please, select the number of employees between 2 and 8:\n",
		                    "Game setup",
		                    JOptionPane.PLAIN_MESSAGE,
		                    icon,
		                    possibilities,
		                    "2");
			MyEvent myEvent = new MyEvent();
			myEvent.setActionName(Constants.SET_NUMBER_OF_PLAYERS);
			List<String> params = new ArrayList<String>();
			params.set(0, numberOfPlayers.toString());
			Game.receiveEvent(myEvent);
	}
	
}
