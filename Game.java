import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;


public class Game{

    static int turn;
    static int currDay;
    static int maxDay;
    static List<Player> players;
    static int roomsRemaining;
    static Board board;
    static int currentPlayerNum = 0;
    static boolean isEndTurn = false;
    static int numberOfPlayers = 0;
    
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
        if(Game.players != null & !Game.players.isEmpty()){
            for (Player player : Game.players){
                player.setTotal(player.getCredits() + player.getMoney() + 5*player.getRank());
                System.out.println("Player " + player.getPlayerNum() + " has " + player.getTotal());
            }
        }
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
    
    /**
     * handleUserInput
     * handles user's input (gets the name of the action from the input and
     * specific parameters, needed for this action)
     * params:  action: String
     * 			parameters: Array String
     */
//    public void handleUserInput(ActionEvent e) {
//    	String action = e.getActionCommand();
//    	if(!action.equals(Constants.END_TURN)){
//    		Player currentPlayer = players.get(currentPlayerNum);
//    		String[] parameters = {"par_A", "par_B"};
//            currentPlayer.handleAction(action, parameters);
//            isEndTurn = false;
//    	}
//    	else{
//    		isEndTurn = true;
//    	}
//    }
    
    
    // to be implemented
    public void updateView(){
        
    }
    
    
    /**
     * Controller for the "Deadwood" game
     */
//    public static void main(String[] args) {
//        int playersNum = 0;
//        int maxDays = 4;
//        int credits = 0;
//        int rank = 1;
//        
//       // Utility utility = new Utility();
//        
//        while(true){
//	        
//	        
////	        String regex = "[2-8]";
////	        String playersNumStr = "3";
////	        if (playersNumStr.matches(regex)){
////	        	playersNum = Integer.parseInt(playersNumStr);
////	        	break;
////	        }
////        }
////
////        //set maxDays and credits
////        if(playersNum >=2 && playersNum <=3){
////            maxDays = 3;
////        }
////        
////        switch (playersNum){
////            case 5:
////                credits = 2;
////                break;
////            case 6:
////                credits = 4;
////                break;
////            case 7:
////            case 8:
////                rank = 2;
////        }
//        
//        //start game with initial params
//        Game game = new Game();
//
//        
//        
//       // create View
//        View view = new View();
//        view.setVisible(true);
//        
//        
//        
//        String boardXml = Constants.BOARD_XML;
//        String cardsXml = Constants.CARDS_XML;
//        
//        game.startGame(1, 1, 4, playersNum, maxDays, credits, rank, boardXml,cardsXml);
//        
//        
//        
//        
//        //print players
//        System.out.println("The game has just started!!");
//        System.out.println("Players:");
//        for(int i = 0; i < playersNum; i++){
//            System.out.println("Player "+ (game.getPlayers().get(i).getPlayerNum() + 1));
//        }      
//        System.out.println();
//        /** Parse params (action name + additional parameters for action)
//         *   from user input and pass it to Player to handle
//         */
//        int turn = 1;
//        game.currentPlayerNum = 0;
//        
//        System.out.println("Please type your next action");
//        System.out.println("Player: " + (game.currentPlayerNum+1));
//        System.out.println("Turn: " + game.getTurn());
//        
//        //General Game Loop
//        while(game.currDay <= game.maxDay){
//        	//Day Loop 
//            while(roomsRemaining != 1){
//            System.out.println("Rooms left: " + roomsRemaining);
//    	    if(game.getPlayers().get(game.currentPlayerNum).getCurrentRole() != null){
//    	    	game.getPlayers().get(game.currentPlayerNum).getCurrentRole().setWorkable(true);
//    	    	game.getPlayers().get(game.currentPlayerNum).setCanMove(false);
//    	    } else{
//    	    	game.getPlayers().get(game.currentPlayerNum).setCanMove(true);
//    	    }
//    	    	//Turn loop
//              //  while(!input.equals(Constants.END_TURN)){
//    	    while(!isEndTurn){
    			
//    	       view.waitForEvent()
    //        recieveEvent
////                    input = utility.inputReader();
////                    String[] parameters = utility.parseParams(input);
////                    String action = parameters[0];
////                    game.handleUserInput(action, parameters, player);
////                    
////                    System.out.println(input);
////                    System.out.print("Next command: ");
////                    //update view
//               }
// 
//                if(game.currentPlayerNum == game.getPlayers().size()-1){
//                	game.currentPlayerNum = 0;
//                } else {
//                	game.currentPlayerNum++;
//                }
//                turn++;
//                game.setTurn(turn);
//                System.out.println("Please type your next action");
//                System.out.println("Player: " + (game.currentPlayerNum+1));
//                System.out.println("Turn: " + game.getTurn());
//            }
//            game.nextDay();
//        }
//        //Last day has ended, start scoring
//        game.endGame();
//       }
//    }


    public static void endTurn(){
	if (Game.players.get(currentPlayerNum).getCurrentRole() != null){
	    Game.players.get(currentPlayerNum).getCurrentRole().setWorkable(true);
	    Game.players.get(currentPlayerNum).setCanMove(false);
	} else {
	    Game.players.get(currentPlayerNum).setCanMove(true);
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
	/**
	 * handleUserInput
	 * handles user's input (gets the name of the action from the input and
	 * specific parameters, needed for this action)
	 * params:  action: String
	 * 			parameters: Array String
	 */
	if(Constants.END_TURN.equals(action)){
	    Game.endTurn();
	} else {
	    String[] parameters = new String[params.size()];
	    for (int i = 0; i < params.size(); i++){
		parameters[i] = params.get(i);
		i++;
	    }
	    
	    Player currentPlayer = players.get(currentPlayerNum);
	    currentPlayer.handleAction(action, parameters);
	}
	System.out.println("turn: "+turn);
	System.out.println("player: "+currentPlayerNum);
    }
}
