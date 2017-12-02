import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
//		ImageIcon boardImage = new ImageIcon("board.jpg");
//		JLabel boardLabel = new JLabel("", boardImage, JLabel.CENTER);
		c.fill = GridBagConstraints.BOTH;
		c.ipady = 40;
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 0;
		//add(boardLabel, c);
		
		//ScoreBoard
		JLabel scoreboard = new JLabel("Scoreboard");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 2.0;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		//Button Panel
		
		JPanel controls = new JPanel();
		
		
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.gridx = 4;
		c.gridy = 0;
		add(scoreboard, c);
		
		//Controls
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 2.0;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		
		JButton moveBtn = new JButton(Constants.MOVE);
		JButton workBtn = new JButton(Constants.WORK);
		JButton upgradeBtn = new JButton(Constants.UPGRADE);
		JButton rehearseBtn = new JButton(Constants.REHEARSE);
		JButton actBtn = new JButton(Constants.ACT);
		JButton whoBtn = new JButton(Constants.WHO);
		JButton whereBtn = new JButton(Constants.WHERE);
		JButton endBtn = new JButton(Constants.END_TURN);
		
		moveBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sendEvent(e.getActionCommand(), new String[0]);
			}
		});
		
		workBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sendEvent(e.getActionCommand(), new String[0]);
			}
		});
		
		upgradeBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sendEvent(e.getActionCommand(), new String[0]);
			}
		});
	
		rehearseBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sendEvent(e.getActionCommand(), new String[0]);
			}
		});

		actBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sendEvent(e.getActionCommand(), new String[0]);
			}
		});

		whoBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sendEvent(e.getActionCommand(), new String[0]);
			}
		});

		whereBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sendEvent(e.getActionCommand(), new String[0]);
			}
		});
		
		endBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sendEvent(e.getActionCommand(), new String[0]);
			}
		});

		
		controls.add(moveBtn);
		controls.add(workBtn);
		controls.add(upgradeBtn);
		controls.add(rehearseBtn);
		controls.add(actBtn);
		controls.add(whoBtn);
		controls.add(whereBtn);
		controls.add(endBtn);
		
		add(controls,c);


		
		
		
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
	public static int getPlayers(){
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
	    System.out.println(action);
	    Game.handleEvent(action,params);
	}
	
}

