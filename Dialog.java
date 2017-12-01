import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
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
		Integer s = (Integer)optionPane.showInputDialog(
		                    null,
		                    "Please, select the number of employees between 2 and 8:\n",
		                    "Game setup",
		                    JOptionPane.PLAIN_MESSAGE,
		                    icon,
		                    possibilities,
		                    "2");
			System.out.println(s);
			Game.receiveEvent(s.toString());
//			if(s == JOptionPane.OK_OPTION){
//				final int numberOfPlayers = s;
//				JButton jButton = getButton(optionPane, "OK", icon);
//				jButton.addActionListener(new ActionListener(){
//					public void actionPerformed(ActionEvent event){
//						System.out.println(numberOfPlayers);
//						Game.setName(numberOfPlayers + "");
//					}
//				});
			//}
	}

	
//	  public JButton getButton(final JOptionPane optionPane, String text, Icon icon) {
//		    final JButton button = new JButton(text, icon);
//		    ActionListener actionListener = new ActionListener() {
//		      public void actionPerformed(ActionEvent actionEvent) {
//		        // Return current text label, instead of argument to method
//		        optionPane.setValue(button.getText());
//		        System.out.println(button.getText());
//		      }
//		    };
//		    button.addActionListener(actionListener);
//		    return button;
//		  }
	
}
