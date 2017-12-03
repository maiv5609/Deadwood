import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
	 		    cardlabel.setIcon(cIcon); 
	 		    cardlabel.setBounds(room.getAreaXY()[0],room.getAreaXY()[1],cIcon.getIconWidth(),cIcon.getIconHeight());
	 		    cardlabel.setOpaque(true);
	 		    boardPane.add(cardlabel, new Integer(1));
	        }
	    }

	    JTextArea area = new JTextArea();
	    area.setEditable(false);
	    area.setLineWrap(true);
	    area.append(updateScoreboard());
	    area.setVisible(true);
	    area.setBounds(boardImage.getIconWidth(),300,200,180);
	    boardPane.add(area, new Integer(3));
	
		
		// Add a dice to represent a player.
		// Role for Crusty the prospector. The x and y co-ordiantes are 
		//taken from Board.xml file
		
		List<Player> players = Game.players;
		for(int i = 0; i < players.size();i++) {
		 JLabel playerlabel = new JLabel();
		 Player player = players.get(i);
		 updatePlayersDice(player);
		 playerlabel.setIcon(player.getIcon());
		 playerlabel.setBounds(114,227,player.getIcon().getIconWidth(),player.getIcon().getIconHeight());
		 boardlabel.add(playerlabel,new Integer(3));
		}

		// Create the Menu for action buttons
		JLabel mLabel = new JLabel("MENU");
		mLabel.setBounds(boardImage.getIconWidth()+40,0,100,20);
		boardPane.add(mLabel,new Integer(2));
		

		// Create Scoreboard for action buttons
		JLabel scoreLabel = new JLabel("SCOREBOARD");
		scoreLabel.setBounds(boardImage.getIconWidth()+20,270,100,20);
		boardPane.add(scoreLabel,new Integer(2));


		// Create Buttons for actions
		JButton moveBtn = new JButton(Constants.MOVE);
		moveBtn.setBackground(Color.white);
		moveBtn.setBounds(boardImage.getIconWidth()+10, 30,100, 20);
		
		JButton workBtn = new JButton(Constants.WORK);
		workBtn.setBackground(Color.white);
		workBtn.setBounds(boardImage.getIconWidth()+10, 60,100, 20);
		
		JButton upgradeBtn = new JButton(Constants.UPGRADE);
		upgradeBtn.setBackground(Color.white);
		upgradeBtn.setBounds(boardImage.getIconWidth()+10, 90,100, 20);
		
		JButton rehearseBtn = new JButton(Constants.REHEARSE);
		rehearseBtn.setBackground(Color.white);
		rehearseBtn.setBounds(boardImage.getIconWidth()+10, 120,100, 20);
		
		JButton actBtn = new JButton(Constants.ACT);
		actBtn.setBackground(Color.white);
		actBtn.setBounds(boardImage.getIconWidth()+10, 150,100, 20);
		
		JButton whoBtn = new JButton(Constants.WHO);
		whoBtn.setBackground(Color.white);
		whoBtn.setBounds(boardImage.getIconWidth()+10, 180,100, 20);
		
		JButton whereBtn = new JButton(Constants.WHERE);
		whereBtn.setBackground(Color.white);
		whereBtn.setBounds(boardImage.getIconWidth()+10, 210,100, 20);
		
		JButton endBtn = new JButton(Constants.END_TURN);
		endBtn.setBackground(Color.white);
		endBtn.setBounds(boardImage.getIconWidth()+10, 240,100, 20);
		
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


}

