import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane; 
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

//WORKNOTE: 
//Should we be using Dialog or TextArea? They both seem to do the same thing
//Error when canceling number of players

public class View extends JFrame{
	private Toolkit toolkit;
	  // JLayered Pane
	  JLayeredPane boardPane;  
	  // JLabels
	  JLabel boardlabel;
	  JLabel cardlabel;
	  List<JLabel> playerLabels;
	  List<JLabel> cardLabels;
	  static JTextArea console = new JTextArea();
	  
	public View() {

		super("Deadwood");
		boardPane = getLayeredPane();
		//Closes window when user presses exit
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		toolkit = getToolkit();
		
		//Make frame fullscreen
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		//Adding image to background
		BufferedImage background = null;
		try {
			background = ImageIO.read(new File("src/wood.jpeg"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("Unable to get background image");
		}
		JLabel backgroundLabel = new JLabel(new ImageIcon(background));
		add(backgroundLabel);
		
		//Image of board
		ImageIcon boardImage = new ImageIcon("board.jpg");
	    boardlabel = new JLabel();
		boardlabel.setIcon(boardImage);
		boardlabel.setBounds(0,0, boardImage.getIconWidth(),boardImage.getIconHeight());
		boardPane.add(boardlabel, new Integer(0));
		
		//Set size of GUI
		setSize(boardImage.getIconWidth()+200,boardImage.getIconHeight());
		
	
	// Add a scene card to this room
	   cardLabels = new ArrayList<JLabel>();
	   Map<String,Room> rooms = Board.getRoomMap();
	   Iterator it = rooms.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        Room room = ((Room)pair.getValue());
	        if(!room.getName().equals(Constants.CASTING_OFFICE) && !room.getName().equals(Constants.TRAILER)){
	        	Scene scene = room.getScene();
	 	        String cardPath = scene.getImg();
	 	        JLabel cardlabel = new JLabel();
	 	        ImageIcon cIcon =  new ImageIcon(getClass().getResource("/cards/" + cardPath));
	 	        scene.setIcon(cIcon);
	 		    cardlabel.setIcon(cIcon); 
	 		    cardlabel.setBounds(room.getAreaXY()[0],room.getAreaXY()[1],cIcon.getIconWidth(),cIcon.getIconHeight());
	 		    cardlabel.setOpaque(true);
	 		    cardLabels.add(cardlabel);
	 		    boardPane.add(cardlabel, new Integer(1));
	        }
	    }

	    JLabel scoreLabel = new JLabel("Scoreboard", SwingConstants.CENTER);
	    scoreLabel.setBounds(boardImage.getIconWidth()+ 50,300,220, 45);
	    scoreLabel.setOpaque(true);
	    scoreLabel.setBackground(Color.white);
	    scoreLabel.setVisible(true);
	    boardPane.add(scoreLabel, new Integer(3));
	    
	    //add scoreboard with players current status
	    JTextArea scoreboard = new JTextArea();
	    scoreboard.setEditable(false);
	    scoreboard.setLineWrap(true);
	    scoreboard.append(updateScoreboard());
	    scoreboard.setVisible(true);
	    scoreboard.setMargin(new Insets(10, 10, 10, 10));
	    scoreboard.setBounds(boardImage.getIconWidth()+ 50,350,220,190);
	    boardPane.add(scoreboard, new Integer(3));
	
	    JLabel consoleLabel = new JLabel("Console", SwingConstants.CENTER);
	    consoleLabel.setBounds(boardImage.getIconWidth()+ 50,550,220,45);
	    consoleLabel.setOpaque(true);
	    consoleLabel.setBackground(Color.white);
	    consoleLabel.setVisible(true);
	    boardPane.add(consoleLabel, new Integer(3));
	    
		//add console to display feedback
	    console.setEditable(false);
	    console.setLineWrap(true);
	    console.setVisible(true);
	    console.setMargin(new Insets(10, 10, 10, 10));
	    console.setBounds(boardImage.getIconWidth()+ 50,600,220,300);
	    boardPane.add(console, new Integer(3));
	    
		// Add a dice to represent a player.
		List<Player> players = Game.players;
		playerLabels = new ArrayList<JLabel>();
		for(int i = 0; i < players.size();i++) {
		 JLabel playerlabel = new JLabel();
		 Player player = players.get(i);
		 updatePlayersDice(player);
		 Room currRoom = player.getCurrentRoom();
		 if(!currRoom.getName().equals(Constants.TRAILER) && !currRoom.getName().equals(Constants.CASTING_OFFICE)){
			 int x = player.getCurrentRole().getAreaXY()[0];
			 int y = player.getCurrentRole().getAreaXY()[1];
			 playerlabel.setIcon(player.getIcon());
			 playerlabel.setBounds(x,y,player.getIcon().getIconWidth(),player.getIcon().getIconHeight());
			 boardlabel.add(playerlabel,new Integer(3));
		 }else{
			 int x = player.getCurrentRoom().getAreaXY()[0];
			 int y = player.getCurrentRoom().getAreaXY()[1];
			 playerlabel.setIcon(player.getIcon());
			 if(i <= 3){
				 playerlabel.setBounds(x + (i*50),y+80,player.getIcon().getIconWidth(),player.getIcon().getIconHeight());
			 } else{
				 int k = i - 4;
				 playerlabel.setBounds(x + (k*50),y + 130,
						 player.getIcon().getIconWidth(),player.getIcon().getIconHeight());
			 }
			 playerLabels.add(playerlabel);
			 boardPane.add(playerlabel, new Integer(2));
		 }
		}

		// Create the Menu for action buttons
		JLabel mLabel = new JLabel("MENU");
		mLabel.setBounds(boardImage.getIconWidth()+40,0,100,20);
		boardPane.add(mLabel,new Integer(2));
		
		// Create Buttons for actions
		
		JButton moveBtn = new JButton(Constants.MOVE);
		moveBtn.setBackground(Color.white);
		moveBtn.setBounds(boardImage.getIconWidth()+110, 30,100, 20);
		
		JButton workBtn = new JButton(Constants.WORK);
		workBtn.setBackground(Color.white);
		workBtn.setBounds(boardImage.getIconWidth()+110, 60,100, 20);
		
		JButton upgradeBtn = new JButton(Constants.UPGRADE);
		upgradeBtn.setBackground(Color.white);
		upgradeBtn.setBounds(boardImage.getIconWidth()+110, 90,100, 20);
		
		JButton rehearseBtn = new JButton(Constants.REHEARSE);
		rehearseBtn.setBackground(Color.white);
		rehearseBtn.setBounds(boardImage.getIconWidth()+110, 120,100, 20);
		
		JButton actBtn = new JButton(Constants.ACT);
		actBtn.setBackground(Color.white);
		actBtn.setBounds(boardImage.getIconWidth()+110, 150,100, 20);
		
		JButton whoBtn = new JButton(Constants.WHO);
		whoBtn.setBackground(Color.white);
		whoBtn.setBounds(boardImage.getIconWidth()+110, 180,100, 20);
		
		JButton whereBtn = new JButton(Constants.WHERE);
		whereBtn.setBackground(Color.white);
		whereBtn.setBounds(boardImage.getIconWidth()+110, 210,100, 20);
		
		JButton endBtn = new JButton(Constants.END_TURN);
		endBtn.setBackground(Color.white);
		endBtn.setBounds(boardImage.getIconWidth()+110, 240,100, 20);
		
		// Add buttons to the board
		boardPane.add(moveBtn, new Integer(2));
		boardPane.add(workBtn, new Integer(2));
		boardPane.add(upgradeBtn, new Integer(2));
		boardPane.add(rehearseBtn, new Integer(2));
		boardPane.add(actBtn, new Integer(2));
		boardPane.add(whoBtn, new Integer(2));
		boardPane.add(whereBtn, new Integer(2));
		boardPane.add(endBtn, new Integer(2));
		
		//Event listeners for buttons
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
		setVisible(true);
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
	
	
	public static String updateScoreboard(){
		String playersStatus ="";
		List<Player> players = Game.players;
		for(int i = 0 ; i < players.size(); i++) {
			String playerStatus = "Player " + (i + 1) + ": "
								   +"Rank:" + players.get(i).getRank() + ", "
								   +"Money:" + players.get(i).getMoney() + ", "
								   +"Credits:" + players.get(i).getCredits() + "\n";	
			playersStatus+=playerStatus + "\n";
		}
		return playersStatus;
	}
	
	public void updatePlayersDice(Player player){
		ImageIcon pIcon = new ImageIcon(getClass().getResource("/dice/" + player.getDiceColor() + player.getRank() + ".png"));
		player.setIcon(pIcon);
	}

	public static void updateConsole(String newText){
		console.setText(newText);
	}
	
	//oldPlayer is a player before action was sent to model
	// newPlayer is a player after action was sent to model
	public void updateDicePosition(Player oldPlayer, Player newPlayer, String action){
		
		//find label from the list of all labels and remove it from the board, than change the position
		if (newPlayer != null){
		 for(JLabel playerLabel : playerLabels){	 
			 if(playerLabel.getIcon().equals(oldPlayer.getIcon())){
				 
				 int x = 0;
				 int y = 0;
				 Room currRoom = newPlayer.getCurrentRoom();
				 // get coordinates
				 if(!currRoom.equals(Constants.CASTING_OFFICE) 
						 && !currRoom.equals(Constants.TRAILER)){
					
					 // if player has taken a role, place him on the card
					 if(Constants.WORK.equals(action)){	 
						 x = newPlayer.getCurrentRole().getAreaXY()[0];
						 y = newPlayer.getCurrentRole().getAreaXY()[1]; 
						 if(newPlayer.getCurrentRoom().getScene().getIsClosed()){
//							 int card_x = newPlayer.getCurrentRoom().getAreaXY()[0];
//							 int card_y = newPlayer.getCurrentRoom().getAreaXY()[0];
							 for(JLabel cardLabel : cardLabels){
								 if(cardLabel.getIcon().equals(newPlayer.getCurrentRoom().getScene().getIcon())){
									 //close scene (set background black or set "CLOSED")
									 cardLabel.setText("CLOSED");
								 }
							 }
						 }
					 }else{
						 x = currRoom.getAreaXY()[0];
						 y = currRoom.getAreaXY()[1];  
					 }
				 } else{
					 x = currRoom.getAreaXY()[0] + 50;
					 y = currRoom.getAreaXY()[1] + 80;
				 }
				 playerLabel.setBounds(x, y, newPlayer.getIcon().getIconWidth(),newPlayer.getIcon().getIconHeight());
				 boardPane.add(playerLabel, new Integer(2));
			 }
		   }
		}
	}
	
	
	public void updateCardPosition(){
		
	}
	
	
}
