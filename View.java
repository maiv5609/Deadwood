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
import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

//WORKNOTE: 
//Error moving to saloon and trying to take Town drunk role

public class View extends JFrame{
    private Toolkit toolkit;
    // JLayered Pane
    JLayeredPane boardPane;  
    // JLabels
    JLabel boardlabel;
    JLabel cardlabel;
    List<JLabel> playerLabels;
    List<JLabel> cardLabels;
    List<JLabel> shotLabels;
    ImageIcon boardImage;
    final static ImageIcon imageIcon = new ImageIcon("src/stickynote.png");
    static JTextArea console = new JTextArea() {
	    Image background = imageIcon.getImage();
	    {
		setOpaque(false);
	    }
	    public void paint(Graphics g) {
		g.drawImage(background, 0, 0, this);
		super.paint(g);
	    }
	    	
	};
	  
    public View() {

	super("Deadwood");
	boardPane = getLayeredPane();
	//Closes window when user presses exit
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	toolkit = getToolkit();
		
	//Make frame fullscreenint x = shotsXY[i][0];
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
	boardImage = new ImageIcon("board.jpg");
	boardlabel = new JLabel();
	boardlabel.setIcon(boardImage);
	boardlabel.setBounds(0,0, boardImage.getIconWidth(),boardImage.getIconHeight());
	boardPane.add(boardlabel, new Integer(0));
		
	//Set size of GUI
	setSize(boardImage.getIconWidth()+200,boardImage.getIconHeight());
		
	
	// Add a scene card to this room
	cardLabels = new ArrayList<JLabel>();
	shotLabels = new ArrayList<JLabel>();
	Map<String,Room> rooms = Board.getRoomMap();
	Iterator it = rooms.entrySet().iterator();
	while (it.hasNext()) {
	    Map.Entry pair = (Map.Entry)it.next();
	    Room room = ((Room)pair.getValue());
	    if(!room.getName().equals(Constants.CASTING_OFFICE) && !room.getName().equals(Constants.TRAILER)){
		Integer[][] shotsXY = room.getShotXY();
		for(int i = 0; i < room.getMaxShots();i++) {
		    int x = shotsXY[i][0];
		    int y = shotsXY[i][1];
		    JLabel shotLabel = new JLabel();
		    ImageIcon cIcon =  new ImageIcon(getClass().getResource("shot.png"));
		    shotLabel.setIcon(cIcon);
		    shotLabel.setBounds(x,y,cIcon.getIconWidth(),cIcon.getIconHeight());
		    shotLabels.add(shotLabel);
		    boardPane.add(shotLabel, new Integer(1));
		}
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
	scoreLabel.setBounds(boardImage.getIconWidth()+ 45, 310, 300, 45);
	scoreLabel.setOpaque(false);
	scoreLabel.setVisible(true);
	boardPane.add(scoreLabel, new Integer(3));
	    
	//add scoreboard with players current status
	    
	//Adding stickynote background to text area
	JTextArea scoreboard = new JTextArea() {
	    	Image background = imageIcon.getImage();
	    	{
		    setOpaque(false);
	    	}
	    	public void paint(Graphics g) {
		    g.drawImage(background, 0, 0, this);
		    super.paint(g);
	    	}
	    	
	    };
	scoreboard.setEditable(false);
	scoreboard.setLineWrap(true);
	scoreboard.setFont(scoreboard.getFont().deriveFont(Font.BOLD));
	scoreboard.append(updateScoreboard());
	scoreboard.setVisible(true);
	scoreboard.setMargin(new Insets(100, 45, 10, 10));
	scoreboard.setBounds(boardImage.getIconWidth()+ 20, 250, 400, 300);
	boardPane.add(scoreboard, new Integer(3));
	
	JLabel consoleLabel = new JLabel("Console", SwingConstants.CENTER);
	consoleLabel.setBounds(boardImage.getIconWidth()+ 75,615,220,45);
	consoleLabel.setOpaque(false);
	consoleLabel.setVisible(true);
	boardPane.add(consoleLabel, new Integer(3));
	    
	//add console to display feedback
	console.setEditable(false);
	console.setLineWrap(true);
	console.setFont(console.getFont().deriveFont(Font.BOLD));
	console.setVisible(true);
	console.setMargin(new Insets(105, 60, 10, 10));
	console.setBounds(boardImage.getIconWidth()+ 75,550,310, 350);
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
		
		
		
		


		
	// Create Buttons for actions
	final ImageIcon woodButton= new ImageIcon("src/woodbutton.png");
	JButton moveBtn = new JButton(Constants.MOVE) {
	    	Image background = woodButton.getImage();
	    	{
		    setOpaque(false);
	    	}
	    	public void paint(Graphics g) {
		    g.drawImage(background, 0, 0, this);
		    super.paint(g);
	    	}
	    	
	    };
	moveBtn.setBorderPainted(false);
	moveBtn.setFocusPainted(false);
	moveBtn.setBackground(Color.white);
	moveBtn.setForeground(Color.white);
	moveBtn.setBounds(boardImage.getIconWidth()+75, 15, 100, 37);
		
	JButton workBtn = new JButton(Constants.WORK) {
	    	Image background = woodButton.getImage();
	    	{
		    setOpaque(false);
	    	}
	    	public void paint(Graphics g) {
		    g.drawImage(background, 0, 0, this);
		    super.paint(g);
	    	}
	    	
	    };
	workBtn.setBorderPainted(false);
	workBtn.setFocusPainted(false);
	workBtn.setBackground(Color.white);
	workBtn.setForeground(Color.white);
	workBtn.setBounds(boardImage.getIconWidth()+75, 65, 100, 37);
		
	JButton upgradeBtn = new JButton(Constants.UPGRADE) {
	    	Image background = woodButton.getImage();
	    	{
		    setOpaque(false);
	    	}
	    	public void paint(Graphics g) {
		    g.drawImage(background, 0, 0, this);
		    super.paint(g);
	    	}
	    	
	    };
	upgradeBtn.setBorderPainted(false);
	upgradeBtn.setFocusPainted(false);
	upgradeBtn.setBackground(Color.white);
	upgradeBtn.setForeground(Color.white);
	upgradeBtn.setBounds(boardImage.getIconWidth()+75, 115, 100, 37);
		
	JButton rehearseBtn = new JButton(Constants.REHEARSE) {
	    	Image background = woodButton.getImage();
	    	{
		    setOpaque(false);
	    	}
	    	public void paint(Graphics g) {
		    g.drawImage(background, 0, 0, this);
		    super.paint(g);
	    	}
	    	
	    };
	rehearseBtn.setBorderPainted(false);
	rehearseBtn.setFocusPainted(false);
	rehearseBtn.setBackground(Color.white);
	rehearseBtn.setForeground(Color.white);
	rehearseBtn.setBounds(boardImage.getIconWidth()+75, 165, 100, 37);
		
	JButton actBtn = new JButton(Constants.ACT) {
	    	Image background = woodButton.getImage();
	    	{
		    setOpaque(false);
	    	}
	    	public void paint(Graphics g) {
		    g.drawImage(background, 0, 0, this);
		    super.paint(g);
	    	}
	    	
	    };
	actBtn.setBorderPainted(false);
	actBtn.setFocusPainted(false);
	actBtn.setBackground(Color.white);
	actBtn.setForeground(Color.white);
	actBtn.setBounds(boardImage.getIconWidth()+210, 15 ,100, 37);
		
	JButton whoBtn = new JButton(Constants.WHO) {
	    	Image background = woodButton.getImage();
	    	{
		    setOpaque(false);
	    	}
	    	public void paint(Graphics g) {
		    g.drawImage(background, 0, 0, this);
		    super.paint(g);
	    	}
	    	
	    };
	whoBtn.setBorderPainted(false);
	whoBtn.setFocusPainted(false);
	whoBtn.setBackground(Color.white);
	whoBtn.setForeground(Color.white);
	whoBtn.setBounds(boardImage.getIconWidth()+210, 65, 100, 37);
		
	JButton whereBtn = new JButton(Constants.WHERE) {
	    	Image background = woodButton.getImage();
	    	{
		    setOpaque(false);
	    	}
	    	public void paint(Graphics g) {
		    g.drawImage(background, 0, 0, this);
		    super.paint(g);
	    	}
	    	
	    };
	whereBtn.setBorderPainted(false);
	whereBtn.setFocusPainted(false);
	whereBtn.setBackground(Color.white);
	whereBtn.setForeground(Color.white);
	whereBtn.setBounds(boardImage.getIconWidth()+210, 115, 100, 37);
		
	JButton endBtn = new JButton(Constants.END_TURN) {
	    	Image background = woodButton.getImage();
	    	{
		    setOpaque(false);
	    	}
	    	public void paint(Graphics g) {
		    g.drawImage(background, 0, 0, this);
		    super.paint(g);
	    	}
	    	
	    };
	endBtn.setBorderPainted(false);
	endBtn.setFocusPainted(false);
	endBtn.setBackground(Color.white);
	endBtn.setForeground(Color.white);
	endBtn.setBounds(boardImage.getIconWidth()+210, 165, 100, 37);
		
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
    //newPlayer is a player after action was sent to model
	
    //WORKNOTE:
    //The reason there is no movement is because there is an error in XML parse.
    //All roles in the room are given the room's cord that they are located in
    //This has been fixed, Jason copy and pasted and forgot to change variable names LUL
    //Also when you try to take a role on the card after just moving to the room, the role at this point is NULL
    //This has been fixed
    //Unfixed error, When you try to take any role that is not the first one listed on the role list you get a NULL list.
    //I looked through XML parse and the roles are being parsed correctly so it must be when we actually pass the role
	
    public void updateDicePosition(Player oldPlayer, Player newPlayer, String action){
	int x = 0;
	int y = 0;
	//find label from the list of all labels and remove it from the board, than change the position
	if (newPlayer != null){
	    for(JLabel playerLabel : playerLabels){	 
		if(playerLabel.getIcon().equals(oldPlayer.getIcon())){
		    Room currRoom = newPlayer.getCurrentRoom();
		    // get coordinates
		    if(!currRoom.equals(Constants.CASTING_OFFICE) 
		       && !currRoom.equals(Constants.TRAILER)){
					
					 // if player has taken a role, place him on the card
					 if(Constants.WORK.equals(action) | 
							 Constants.ACT.equals(action) | 
							 Constants.REHEARSE.equals(action) | 
							 Constants.WHO.equals(action) | 
							 Constants.WHERE.equals(action)){	 
						 Role currentRole = newPlayer.getCurrentRole();
						 if(currentRole != null) {
							 if(currentRole.onCard) {
								 x = currentRole.getAreaXY()[0] + currRoom.getAreaXY()[0];
								 y = currentRole.getAreaXY()[1] + currRoom.getAreaXY()[1];
							 }else {
								 x = currentRole.getAreaXY()[0];
								 y = currentRole.getAreaXY()[1]; 
							 }
							 if(newPlayer.getCurrentRoom().getScene().getIsClosed()){
//								 int card_x = newPlayer.getCurrentRoom().getAreaXY()[0];
//								 int card_y = newPlayer.getCurrentRoom().getAreaXY()[0];
								 for(JLabel cardLabel : cardLabels){
									 if(cardLabel.getIcon().equals(newPlayer.getCurrentRoom().getScene().getIcon())){
										 //close scene (set background black or set "CLOSED")
										 cardLabel.setText("CLOSED");
									 }
								 }
							 }
							 playerLabel.setBounds(x, y, newPlayer.getIcon().getIconWidth(),newPlayer.getIcon().getIconHeight());
						 }
					 }else{
						 x = currRoom.getAreaXY()[0];
						 y = currRoom.getAreaXY()[1];  
						 playerLabel.setBounds(x, y, newPlayer.getIcon().getIconWidth(),newPlayer.getIcon().getIconHeight());
					 }
				 } else{
					 x = currRoom.getAreaXY()[0] + 50;
					 y = currRoom.getAreaXY()[1] + 80;
					 playerLabel.setBounds(x, y, newPlayer.getIcon().getIconWidth(),newPlayer.getIcon().getIconHeight());
				 }

				 
		    boardPane.add(playerLabel, new Integer(2));
		    updateDiceTurn(newPlayer);
		}
	    }
	}
    }
	
    public void declareWinner(String winner) {
	//JLabel winnerLabel = new JLabel(winner);
	ImageIcon winnerImage = new ImageIcon("src/youwin.jpg");
	//JOptionPane.showMessageDialog(getParent(), winner + " has won!", winnerImage);
	JOptionPane.showMessageDialog(
				      null,
				      winner + " has won!",
				      "Congratulations", JOptionPane.INFORMATION_MESSAGE,
				      winnerImage);
		
    }
	
    public void updateCardPosition(){
		
	}
	
	public void updateDiceTurn(Player newPlayer){
		ImageIcon imageIcon = new ImageIcon(getClass().getResource("/dice/" + newPlayer.getDiceColor() + newPlayer.getRank() + ".png"));
		JLabel label = new JLabel(imageIcon);
		label.setIcon(imageIcon);
		label.setBounds(boardImage.getIconWidth()+ 20,30, newPlayer.getIcon().getIconWidth(),newPlayer.getIcon().getIconHeight());
		
		boardPane.add(label, new Integer(3));
	}
	
	public void removeShot(Room currentRoom){
		Integer[][] shotsXY = currentRoom.getShotXY();
		if(currentRoom.getCurrentShots() != 0){
			int x = shotsXY[currentRoom.getCurrentShots() - 1][0];
			int y = shotsXY[currentRoom.getCurrentShots() - 1][1];
			for(JLabel shotLabel : shotLabels){
				if((shotLabel.getBounds().x == x)
				    && (shotLabel.getBounds().y == y)){
					// shotLabels.remove(shotLabel);
					 boardPane.remove(shotLabel);
				}
			}
		}
	}
	
	
}
