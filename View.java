import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
		TextArea test = new TextArea();
		//Closure that performs an action, can probably use this to call our other commands
		JButton who = new JButton("Who");
		//Setting size of button
		who.setBounds(150, 60, 80, 30);
		who.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
//				who.addPropertyChangeListener(new );
//				who.firePropertyChange("who", false, true);
				System.out.println("who fired");
				Game.setName("who");
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
		
		
		
		//Closes window when user presses exit
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public class TextArea extends JFrame{
		
		private JLabel hint;
		
		public TextArea(){
			JPanel panel = new JPanel();
			panel.setLayout(new BorderLayout());
			panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
			JScrollPane pane = new JScrollPane();
			JTextArea area = new JTextArea();
			area.setLineWrap(true);
			area.setWrapStyleWord(true);
			area.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
			pane.getViewport().add(area);
			panel.add(pane);
			add(panel);
			hint = new JLabel("Please, set number of players between 2 and 8:");
			panel.add(hint);
			setSize(new Dimension(350, 300));
			setLocationRelativeTo(null);
			setVisible(true);
		}
		
		public void changeText(String changeTo, JPanel panel) {
			hint = new JLabel(changeTo);
			panel.add(hint);
		}
	}
	
}

