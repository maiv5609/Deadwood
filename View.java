import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextArea;

//This is mostly an example class

public class View extends JFrame{
	private Toolkit toolkit;
	  // JLayered Pane
	  JLayeredPane boardPane;
	  
	  // JLabels
	  JLabel boardlabel;
	  JLabel cardlabel;

	
	public View() {

		super("Deadwood");
		 boardPane = getLayeredPane();
		//Closes window when user presses exit
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		toolkit = getToolkit();
		
		//Sets size to the size of screen and opens in center of screen
		Dimension size = toolkit.getScreenSize();
		setLocation((size.width - getWidth())/2, (size.height - getHeight())/2);
	
		//Image of board
		ImageIcon boardImage = new ImageIcon("board.jpg");
	    boardlabel = new JLabel();//("", boardImage, JLabel.CENTER);
		boardlabel.setIcon(boardImage);
		boardlabel.setBounds(0,0, boardImage.getIconWidth(),boardImage.getIconHeight());
		
		boardPane.add(boardlabel, new Integer(0));
		
		//Set size of GUI
		setSize(boardImage.getIconWidth()+200,boardImage.getIconHeight());
		
	
		// Add a scene card to this room
		Map<Room> scenes = Board.getRoomMap();
		for(Scene scene: scenes) {
			String cardPath = scene.getImg();
		    cardlabel = new JLabel();
		    ImageIcon cIcon =  new ImageIcon(cardPath);
		    cardlabel.setIcon(cIcon); 
		    cardlabel.setBounds(card.x,card.y,cIcon.getIconWidth(),cIcon.getIconHeight());
		    cardlabel.setOpaque(true);
		    // Add the card to the lower layer
		    boardPane.add(cardlabel, new Integer(1));
		}
		
		
		//ScoreBoard
		JTextArea scoreboard = new JTextArea("Scoreboard");
		scoreboard.setLineWrap(true);
		scoreboard.setEditable(false);
		scoreboard.setMinimumSize(new Dimension(300, 600));
		scoreboard.setAlignmentX(CENTER_ALIGNMENT);
		
		
		// Add a dice to represent a player.
		// Role for Crusty the prospector. The x and y co-ordiantes are 
		//taken from Board.xml file
		
		List<Player> players = Game.players;
		for(int i = 0; i < players.size();i++) {
		 JLabel playerlabel = new JLabel();
		 Player player = players.get(i);
		 updatePlayersDice(player);
		 playerlabel.setIcon(player.getIcon());
		 playerlabel.setBounds(114,227,46,46);
		 boardlabel.add(playerlabel,new Integer(3));
		}
//		
//		//Button Panel
//		JPanel controls = new JPanel();
//		
//		
//		
//		// Console
//		JTextArea console = new JTextArea("Console");
//		console.setEditable(false);
//
//		add(console, c);


		
		JButton moveBtn = new JButton(Constants.MOVE);
		moveBtn.setBackground(Color.white);
		moveBtn.setBounds(boardImage.getIconWidth()+10, 30,100, 20);
		
		JButton workBtn = new JButton(Constants.WORK);
		workBtn.setBackground(Color.white);
		workBtn.setBounds(boardImage.getIconWidth()+10, 30,100, 20);
		
		JButton upgradeBtn = new JButton(Constants.UPGRADE);
		upgradeBtn.setBackground(Color.white);
		upgradeBtn.setBounds(boardImage.getIconWidth()+10, 30,100, 20);
		
		JButton rehearseBtn = new JButton(Constants.REHEARSE);
		rehearseBtn.setBackground(Color.white);
		rehearseBtn.setBounds(boardImage.getIconWidth()+10, 30,100, 20);
		
		JButton actBtn = new JButton(Constants.ACT);
		actBtn.setBackground(Color.white);
		actBtn.setBounds(boardImage.getIconWidth()+10, 30,100, 20);
		
		JButton whoBtn = new JButton(Constants.WHO);
		whoBtn.setBackground(Color.white);
		whoBtn.setBounds(boardImage.getIconWidth()+10, 30,100, 20);
		
		JButton whereBtn = new JButton(Constants.WHERE);
		whereBtn.setBackground(Color.white);
		whereBtn.setBounds(boardImage.getIconWidth()+10, 30,100, 20);
		
		JButton endBtn = new JButton(Constants.END_TURN);
		endBtn.setBackground(Color.white);
		endBtn.setBounds(boardImage.getIconWidth()+10, 30,100, 20);
		
		
		moveBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sendEvent(e.getActionCommand(), new ArrayList<String>());
			}
		});
		
		workBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sendEvent(e.getActionCommand(), new ArrayList<String>());
			}
		});
		
		upgradeBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sendEvent(e.getActionCommand(), new ArrayList<String>());
			}
		});
	
		rehearseBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sendEvent(e.getActionCommand(), new ArrayList<String>());
			}
		});

		actBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sendEvent(e.getActionCommand(), new ArrayList<String>());
			}
		});

		whoBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sendEvent(e.getActionCommand(), new ArrayList<String>());
			}
		});

		whereBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sendEvent(e.getActionCommand(), new ArrayList<String>());
			}
		});
		
		endBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sendEvent(e.getActionCommand(), new ArrayList<String>());
			}
		});

		
//		controls.add(moveBtn);
//		controls.add(workBtn);
//		controls.add(upgradeBtn);
//		controls.add(rehearseBtn);
//		controls.add(actBtn);
//		controls.add(whoBtn);
//		controls.add(whereBtn);
//		controls.add(endBtn);
//		
//		add(controls,c);

//		// Add a scene card to this room
//		JLabel cardlabel = new JLabel();
//		ImageIcon cIcon =  new ImageIcon("01.png");
//		cardlabel.setIcon(cIcon);
//		cardlabel.setBounds(20,60,cIcon.getIconWidth(),cIcon.getIconHeight(
//		));
//		cardlabel.setOpaque(true);
//		// Add the card to the lower layer
//		add(cardlabel, new Integer(1));
		
		
		
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
//		close.setBoundsplayer.getDiceColor()(50, 60, 80, 30);
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
    public static Object getDialogResult(String dialogName, String message, List<Object> options){
		Dialog dialog = new Dialog();
		dialog.setVisible(true);
		
		return dialog.getResult(options, message, dialogName);
	}
	
	public void sendEvent(String action, ArrayList<String> params ){
	    System.out.println(action);
	    Game.handleEvent(action,params);
	}
	
	
//	public static String updateScoreboard(){
//		String playersStatus ="Scoreboard\n\n";
//		List<Player> players = Game.players;
//		for(int i = 0 ; i < players.size(); i++) {
//			String playerStatus = "Player " + (i + 1) + ": "
//								   +"Rank:" + players.get(i).getRank() + ", "
//								   +"Money:" + players.get(i).getMoney() + ", "
//								   +"Credits:" + players.get(i).getCredits() + "\n";	
//			playersStatus+=playerStatus + "\n";
//		}
//		return playersStatus;
//	}
	
	public void updatePlayersDice(Player player){
		ImageIcon pIcon = new ImageIcon(getClass().getResource("/dice/" + player.getDiceColor() + player.getRank() + ".png"));
		player.setIcon(pIcon);
	}
//	String playersStatus ="Scoreboard\n\n";
//	List<Player> players = Game.players;
//	for(int i = 0 ; i < players.size(); i++) {
//		String playerStatus = "Player " + (i + 1) + ": "
//							   +"Rank:" + players.get(i).getRank() + ", "
//							   +"Money:" + players.get(i).getMoney() + ", "
//							   +"Credits:" + players.get(i).getCredits() + "\n";	
//		playersStatus+=playerStatus + "\n";
//	}
//	return playersStatus;

}

