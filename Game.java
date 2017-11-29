import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;


public class Game {
    int turn;
    int currDay;
    int maxDay;
    static List<Player> players;
    static int roomsRemaining;
    Board board;
    static int currentPlayerNum = 0;
    static boolean isEndTurn = false;
    

    
    public int getTurn() {
        return turn;
    }
    
    public void setTurn(int turn) {
        this.turn = turn;
    }
    
    public int getCurrDay() {
        return currDay;
    }
    
    public void setCurrDay(int currDay) {
        this.currDay = currDay;
    }
    
    public int getMaxDay() {
        return maxDay;
    }
    
    public void setMaxDay(int maxDay) {
        this.maxDay = maxDay;
    }
    
    public List<Player> getPlayers() {
        return players;
    }
    
    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    
    public int getRoomsRemaining() {
        return roomsRemaining;
    }
    
    public void setRoomsRemaining(int roomsRemaining) {
        Game.roomsRemaining = roomsRemaining;
    }

    
    
    /** nextDay
     *  sets necessary configuration for the next day
     */
    private void nextDay() {
    	board.populateRooms();
    	for (Player curr: players){
    		curr.getCurrentRoom().removePlayer(curr.getPlayerNum());
    		curr.setCurrentRoom(Board.getRoomNode("trailer"));
    		Board.getRoomNode("trailer").addPlayer(curr.getPlayerNum());
    	}
        roomsRemaining = Board.getRoomMap().size();
        this.currDay++;
     }
    
    /** scoring
     *  sets total amount of money, credits and fame to each player in the game
     */
    private void scoring() {
        List<Player> players = this.players;
        if(players != null & !players.isEmpty()){
            for (Player player : players){
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
    private void startGame(int currentDay, int turn, int maxDay, int playersNum, int maxDays, int credits, int rank, String boardXml, String cardsXml) {
        
        board = Board.getInstance(boardXml, cardsXml);
        board.populateRooms();
        List<Player> players = new ArrayList<Player>();
        this.setPlayers(players);
        
        for (int i = 0; i < playersNum; i++){
            Player player = new Player(rank,i,0,credits,Board.getRoomNode("trailer"));
            this.players.add(player);
            Board.getRoomNode(Constants.TRAILER).addPlayer(i);
        }
 
        //set Current day to 1
        this.currDay = currentDay;
        //set Current turn to 0
        this.turn = turn;
        
        //set Max Day
        if(playersNum <= 3){
        	this.maxDay = 3;
        }else{
        	this.maxDay = 4;
        }
        
        //set roomsRemaining with number of rooms
        Game.roomsRemaining = Board.getRoomMap().size();

    }
    

	/**
     * endGame
     * ends the game (== scoring)
     */
    private void endGame() {
        this.scoring();
    }
    
    /**
     * handleUserInput
     * handles user's input (gets the name of the action from the input and
     * specific parameters, needed for this action)
     * params:  action: String
     * 			parameters: Array String
     */
    public static void handleUserInput(ActionEvent e) {
    	String action = e.getActionCommand();
    	if(!action.equals(Constants.END_TURN)){
    		Player currentPlayer = players.get(currentPlayerNum);
    		String[] parameters = {"par_A", "par_B"};
            currentPlayer.handleAction(action, parameters);
            isEndTurn = false;
    	}
    	else{
    		isEndTurn = true;
    	}
    }
    
    
    // to be implemented
    public void updateView(){
        
    }
    
    
    /**
     * Controller for the "Deadwood" game
     */
    public static void main(String[] args) {
        int playersNum = 0;
        int maxDays = 4;
        int credits = 0;
        int rank = 1;
        
        Utility utility = new Utility();
        
        /* GUI Testing
         * 
         */
        /* This example uses the View class
        View board = new View();
		board.setVisible(true);
		*/
        FrameBorder GUI = new FrameBorder();
        GUI.setVisible(true);
        

        
        while(true){
        	CustomDialog dialog = new CustomDialog();
        	dialog.setVisible(true);
        	//System.out.println("Please, set number of players between 2 and 8:");
	        //String playersNumStr = utility.inputReader();
	        
	        
	        String regex = "[2-8]";
	        String playersNumStr = "3";
	        if (playersNumStr.matches(regex)){
	        	playersNum = Integer.parseInt(playersNumStr);
	        	break;
	        }
        }

        //set maxDays and credits
        if(playersNum >=2 && playersNum <=3){
            maxDays = 3;
        }
        
        switch (playersNum){
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
        
        //start game with initial params
        Game game = new Game();
        String boardXml = Constants.BOARD_XML;
        String cardsXml = Constants.CARDS_XML;
        game.startGame(1, 1, 4, playersNum, maxDays, credits, rank, boardXml,cardsXml);
        //print players
        System.out.println("The game has just started!!");
        System.out.println("Players:");
        for(int i = 0; i < playersNum; i++){
            System.out.println("Player "+ (game.getPlayers().get(i).getPlayerNum() + 1));
        }      
        System.out.println();
        /** Parse params (action name + additional parameters for action)
         *   from user input and pass it to Player to handle
         */
        int turn = 1;
        currentPlayerNum = 0;
        
        System.out.println("Please type your next action");
        System.out.println("Player: " + (currentPlayerNum+1));
        System.out.println("Turn: " + game.getTurn());
        
        //General Game Loop
        while(game.currDay <= game.maxDay){
        	//Day Loop 
            while(roomsRemaining != 1){
            System.out.println("Rooms left: " + roomsRemaining);
    	    if(game.getPlayers().get(currentPlayerNum).getCurrentRole() != null){
    	    	game.getPlayers().get(currentPlayerNum).getCurrentRole().setWorkable(true);
    	    	game.getPlayers().get(currentPlayerNum).setCanMove(false);
    	    } else{
    	    	game.getPlayers().get(currentPlayerNum).setCanMove(true);
    	    }
    	    	//Turn loop
              //  while(!input.equals(Constants.END_TURN)){
    	    while(!isEndTurn){
//                    input = utility.inputReader();
//                    String[] parameters = utility.parseParams(input);
//                    String action = parameters[0];
//                    game.handleUserInput(action, parameters, player);
//                    
//                    System.out.println(input);
//                    System.out.print("Next command: ");
//                    //update view
               }
 
                if(currentPlayerNum == game.getPlayers().size()-1){
                    currentPlayerNum = 0;
                } else {
                    currentPlayerNum++;
                }
                turn++;
                game.setTurn(turn);
                System.out.println("Please type your next action");
                System.out.println("Player: " + (currentPlayerNum+1));
                System.out.println("Turn: " + game.getTurn());
            }
            game.nextDay();
        }
        //Last day has ended, start scoring
        game.endGame();
    }
    
}
