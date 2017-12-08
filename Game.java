import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;


public class Game{

    static View Window;
    static int turn;
    static int currDay;
    static int maxDay;
    static List<Player> players;
    static int roomsRemaining;
    static Board board;
    static int currentPlayerNum = 0;
    static boolean isEndTurn = false;
    static int numberOfPlayers = 0;

    public View getWindow(){
    	return Game.Window;
    }

    public void setWindow(View Window){
    	Game.Window = Window;
    }
    
    public int getTurn() {
        return Game.turn;
    }
    
    public void setTurn(int turn) {
        Game.turn = turn;
    }
    
    public int getCurrDay() {
        return Game.currDay;
    }
    
    public void setCurrDay(int currDay) {
        Game.currDay = currDay;
    }
    
    public int getMaxDay() {
        return Game.maxDay;
    }
    
    public void setMaxDay(int maxDay) {
        Game.maxDay = maxDay;
    }
    
    public List<Player> getPlayers() {
        return Game.players;
    }
    
    public void setPlayers(List<Player> players) {
        Game.players = Game.players;
    }
    
    public int getRoomsRemaining() {
        return Game.roomsRemaining;
    }
    
    public void setRoomsRemaining(int roomsRemaining) {
        Game.roomsRemaining = roomsRemaining;
    }
    
//    public Game() {
//    	
//    	//Property Listener
//        pcs.addPropertyChangeListener(this);
//    }

    
    
    /** nextDay
     *  sets necessary configuration for the next day
     */
    public static void nextDay() {
	if (Game.currDay >= Game.maxDay){
	    Game.endGame();
	} else {
	    Game.board.populateRooms();
	    for (Player curr: Game.players){
    		curr.getCurrentRoom().removePlayer(curr.getPlayerNum());
    		curr.setCurrentRoom(Board.getRoomNode("trailer"));
    		Board.getRoomNode("trailer").addPlayer(curr.getPlayerNum());
	    }
	    Game.roomsRemaining = Board.getRoomMap().size();
	    Game.currDay++;
	}
    }
    
    /** scoring
     *  sets total amount of money, credits and fame to each player in the game
     */
    public static void scoring() {
    	String winner = null;
    	int highest = 0;
        if(Game.players != null & !Game.players.isEmpty()){
            for (Player player : Game.players){
                player.setTotal(player.getCredits() + player.getMoney() + 5*player.getRank());
                if (player.getTotal() > highest) {
                	winner = "Player " + player.getPlayerNum()+1;
                	highest = player.getTotal();
                }
                System.out.println("Player " + player.getPlayerNum() + " has " + player.getTotal());
            }
        }
       Window.declareWinner(winner);
    }
    
    /** startGame
     *  starts the game
     *  params: currentDay: int
     *  		turn : int
     *  		maxDay : int
     *  		playersNum: int
     *  		maxDays : int
     *  		credits : int
     *  		rank : int
     */
    public void startGame(int numberOfPlayers, String boardXml, String cardsXml) {
    	Game.maxDay = 4;
        int credits = 0;
        int rank = 1;
        
    //set maxDays and credits
    if(numberOfPlayers >=2 && numberOfPlayers <=3){
        Game.maxDay = 3;
    }
    
    switch (numberOfPlayers){
        case 5:
            credits = 2;
            break;
        case 6:
            credits = 4;
            break;
        case 7:
        case 8:
            rank = 2;
    }
    	Game.turn= 1;
    	Game.currentPlayerNum = 0;
    
        Game.board = Board.getInstance(boardXml, cardsXml);
        Game.board.populateRooms();
        Game.players = new ArrayList<Player>();
        
        for (int i = 0; i < numberOfPlayers; i++){
            Player player = new Player(rank,i,0,credits,Board.getRoomNode("trailer"));
            Game.players.add(player);
            Board.getRoomNode(Constants.TRAILER).addPlayer(i);
        }
 
        //set Current day to 1
        Game.currDay = 1;
        
        //set roomsRemaining with number of rooms
        Game.roomsRemaining = Board.getRoomMap().size();

    }
    

	/**
     * endGame
     * ends the game (== scoring)
     */
    public static void endGame() {
        Game.scoring();
    }
    
//    // to be implemented
//    public  void updateView(Player oldPlayer, Player newPlayer){
//    	
//    }

    public static void endTurn(){
    	//WORKNOTE: remove this
        //scoring();
	if (Game.players.get(currentPlayerNum).getCurrentRole() != null){
	    Game.players.get(currentPlayerNum).getCurrentRole().setWorkable(true);
	    Game.players.get(currentPlayerNum).setCanMove(false);
	} else {
	    Game.players.get(Game.currentPlayerNum).setCanMove(true);
	}
	if (Game.currentPlayerNum == Game.players.size()-1){
	    Game.currentPlayerNum = 0;
	} else {
	    Game.currentPlayerNum++;
	}
	Game.turn++;
	if (Game.roomsRemaining <= 1){
	    Game.nextDay();
	}
	// updateView?
    }
	
	
	/* Event listener for game, will get events fired from View
	 * 
	 */
    public static void handleEvent(String action, ArrayList<String> params) {   
    	Player currPlayer = Game.players.get(Game.currentPlayerNum);
	/**
	 * handleUserInput
	 * handles user's input (gets the name of the action from the input and
	 * specific parameters, needed for this action)
	 * params:  action: String
	 * 			parameters: Array String
	 */
	if(Constants.END_TURN.equals(action)){
		View.updateConsole("Turn ended");
	    Game.endTurn();
	} else {
	    String[] parameters = new String[params.size()];
	    for (int i = 0; i < params.size(); i++){
		parameters[i] = params.get(i);
		i++;
	    }

	    if(Constants.MOVE.equals(action)){
				List<Object> options = new ArrayList<Object>();
				List<String> rooms = currPlayer.getCurrentRoom().getConnectedRooms();
				for (String room : rooms) {
					options.add((Object) room);
				}
				String Destination = (String) View.getDialogResult("Which room?",
						"Please choose one of the following connected rooms to move to\n", options);
				if (Destination != null) {
					parameters = Destination.toLowerCase().split(" ");
				} else {
					parameters = null;
				}
	    } else if (Constants.UPGRADE.equals(action)) {
				List<Object> options = new ArrayList<Object>();
				List<Object> options2 = new ArrayList<Object>();
				Integer current = currPlayer.getRank();
				for (Integer i = current + 1; i <= 6; i++) {
					options.add((Object) i);
				}
				options2.add((Object) "Dollars");
				options2.add((Object) "Credits");
				String desiredCurrency = null;
				Integer desiredRank = null;
				if (current < 6) {
					desiredRank = (Integer) View.getDialogResult("What rank?", "Please choose a rank to upgrade to\n",
							options);
					if (desiredRank != null) {
						desiredCurrency = (String) View.getDialogResult("What currency?",
								"Please choose a currency to use\n", options2);
					}
				}
				if ((desiredRank == null) || (desiredCurrency == null) || (current == 6)) {
					parameters = null;
				} else {
					parameters = new String[2];
					parameters[1] = desiredRank.toString();
					if (desiredCurrency.equals("Dollars")) {
						parameters[0] = "$";
					} else if (desiredCurrency.equals("Credits")) {
						parameters[0] = "cr";
					}
				}
	    }  else if (Constants.WORK.equals(action)) {
				String roomName = currPlayer.getCurrentRoom().getName();
				String name = null;
				if (!roomName.equals(Constants.TRAILER) && !roomName.equals(Constants.CASTING_OFFICE)) {
					List<Role> roles = currPlayer.getCurrentRoom().getRoles();
					List<Object> options = new ArrayList<Object>();
					for (Role role : roles) {
						options.add((Object) role.getName());
					}
					name = (String) View.getDialogResult("What role?", "Please choose a role to work\n", options);
				}
				if (name == null) {
					parameters = null;
				} else {
					parameters = new String[] { name };
				}
	    }

	    if(parameters != null | Constants.REHEARSE.equals(action) | Constants.ACT.equals(action)){
	    	Player currentPlayer = players.get(currentPlayerNum);
	    	Player newPlayer = currentPlayer.handleAction(action, parameters);
	    	if(newPlayer != null){
	    		Window.updateDicePosition(currentPlayer,newPlayer, action);
	    	}  
	    }
	}
		//View.updateConsole("turn: "+turn);
		//View.updateConsole("player: "+currentPlayerNum);
    }
    
    public static void removeShot(Room currentRoom){
    	Window.removeShot(currentRoom);
    }
}
