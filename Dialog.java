import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Dialog extends JFrame{

	public Dialog(){
		
	}
	
	public Object getResult(List<Object> options, String message, String dialogTitle){
		Object result = 0;
		Image image = null;
		try {
			image = ImageIO.read(new File("shot.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Icon icon = new ImageIcon(image);
		
		JOptionPane optionPane = new JOptionPane();
		 Object[] possibilities = new Object[options.size()];
		 int i = 0;
		 for(Object obj : options){
			 possibilities[i] = obj;
			 i++;
		 }
		 
		   result = optionPane.showInputDialog(
		                    null,
		                    message,
		                    dialogTitle,
		                    JOptionPane.PLAIN_MESSAGE,
		                    icon,
		                    possibilities,
		                    "2");

			return result;
	}
	
	
	
}
