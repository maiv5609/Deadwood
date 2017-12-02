import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

//This is mostly an example class

public class View extends JFrame{
	private Toolkit toolkit;
	
	public View() {
		setSize(300, 200);
		setTitle("Deadwood");
		toolkit = getToolkit();		

		//Sets size to the size of screen and opens in center of screen
		Dimension size = toolkit.getScreenSize();
		setLocation((size.width - getWidth())/2, (size.height - getHeight())/2);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);
		JButton who = new JButton("Who");
		who.setBounds(150, 60, 80, 30);
		who.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				sendEvent("who",new String[0] );
			}
		});
		
		JButton close = new JButton("Close");
		close.setBounds(50, 60, 80, 30);
		close.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				System.exit(0);
			}
		});
		
		//adding buttons
		panel.add(who);
		panel.add(close);
		

		FrameBorder frameBorder = new FrameBorder();
		frameBorder.setVisible(true);

		
		//Closes window when user presses exit
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public int getPlayers(){
		Dialog dialog = new Dialog();
		dialog.setVisible(true);
		List<Object> options = new ArrayList<Object>();
		for (int i = 2; i < 9; i++){
			options.add(i);
		}
		String message =  "Please, select the number of employees between 2 and 8:\n";
		String dialogName = "Game setup";
		return (Integer)dialog.getResult(options, message, dialogName);
	}
	
	public void sendEvent(String action, String[] params ){
		Game.handleEvent(action,params);
	}
	
}
