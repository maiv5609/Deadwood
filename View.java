import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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
		setSize(1500, 1000);
		setTitle(Constants.DEADWOOD);
		setLayout(new GridBagLayout());
		
		//Constants for Layout
		GridBagConstraints c = new GridBagConstraints();
		
		//Closes window when user presses exit
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		c.weightx = 0.5;
		
		
		
		toolkit = getToolkit();
		
		//JFrame frame = new JFrame("Deadwood");
		
		//Sets size to the size of screen and opens in center of screen
		Dimension size = toolkit.getScreenSize();
		setLocation((size.width - getWidth())/2, (size.height - getHeight())/2);
	
//		getContentPane().add(mainPanel);
		//GridLayout(row, col)
		
//		JPanel mainPanel = new JPanel();
//		JPanel controls = new JPanel();
//		JPanel Scoreboard = new JPanel();
//		JLabel console = new JLabel();
		
		//Setting up buttons preferred size
//		JButton b = new JButton("fake button");
//		Dimension buttonSize = b.getPreferredSize();
//		
//		mainPanel.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 5)+20,
//	                (int)(buttonSize.getHeight() * 3.5)+20 * 2));
		
		//Image of board
		ImageIcon boardImage = new ImageIcon("board.jpg");
		JLabel boardLabel = new JLabel("", boardImage, JLabel.CENTER);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 40;
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 0;
		add(boardLabel);
		
		//ScoreBoard
		JLabel scoreboard = new JLabel("Scoreboard");
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 0;
		add(scoreboard);
		
		//Controls

		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		
		add(new JButton(Constants.MOVE));
		add(new JButton(Constants.UPGRADE));
		add(new JButton(Constants.REHEARSE));
		add(new JButton(Constants.ACT));
		add(new JButton(Constants.WHO));
		add(new JButton(Constants.WHERE));
		add(new JButton(Constants.END_TURN));
		
		
		//Creating Scoreboard
		//Placeholder
//		Scoreboard.add(new JLabel("Scoreboard"));
		
		//Populating console initally
//		console.setText("Console");
		
		//Setting sizes
//		mainPanel.setPreferredSize(new Dimension(500, 400));
//		Scoreboard.setPreferredSize(new Dimension(200, 400));
		//controls.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 5)+20, (int)(buttonSize.getHeight() * 3.5)+20 * 2));
		
		
		//Adding panels to frame
//		add(mainPanel);
//		add(Scoreboard);
//		add(controls);
//		add(console);
		
		setVisible(true);
		
//		TextArea test = new TextArea();
//		//Closure that performs an action, can probably use this to call our other commands
//		JButton who = new JButton("Who");
//		//Setting size of button
//		who.setBounds(150, 60, 80, 30);
//		who.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent event){
//				//who.addPropertyChangeListener(new );
//				//who.firePropertyChange("who", false, true);
//				System.out.println("who fired");
//				Game.receiveEvent("who");
//			}
//		});
//		
//		JButton close = new JButton("Close");
//		close.setBounds(50, 60, 80, 30);
//		close.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent event){
//				System.exit(0);
//			}
//		});
//		
		//adding buttons
//		panel.add(who);
//		panel.add(close);
		

//		FrameBorder frameBorder = new FrameBorder();
//		frameBorder.setVisible(true);

		
		
	}

	
}

