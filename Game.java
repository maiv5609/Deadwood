import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Game{

    int turn;
    int currDay;
    int maxDay;
    static List<Player> players;
    static int roomsRemaining;
    Board board;
    static int currentPlayerNum = 0;
    static boolean isEndTurn = false;
    int numberOfPlayers = 0;
    static LinkedList<MyEvent> inputBuffer = new LinkedList<MyEvent>();
    
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
    public void nextDay() {
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
    public void scoring() {
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
    public void startGame(int numberOfPlayers, String boardXml, String cardsXml) {
    	int maxDays = 4;
        int credits = 0;
        int rank = 1;

    //set maxDays and credits
    if(numberOfPlayers >=2 && numberOfPlayers <=3){
        maxDays = 3;
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
    	this.turn= 1;
    	currentPlayerNum = 0;
    
        board = Board.getInstance(boardXml, cardsXml);
        board.populateRooms();
        List<Player> players = new ArrayList<Player>();
        this.setPlayers(players);
        
        for (int i = 0; i < numberOfPlayers; i++){
            Player player = new Player(rank,i,0,credits,Board.getRoomNode("trailer"));
            this.players.add(player);
            Board.getRoomNode(Constants.TRAILER).addPlayer(i);
        }
 
        //set Current day to 1
        this.currDay = 1;
        
        //set Max Day
        if(numberOfPlayers <= 3){
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
    public void endGame() {
        this.scoring();
    }
    
    
    // to be implemented
    public void updateView(){
        
    }
    
    
    /**
     * Controller for the "Deadwood" game
     * @return 
     */
    public void runGame(){
        
        //General Game Loop
        while(currDay <= maxDay){
        	//Day Loop 
            while(roomsRemaining != 1){
            System.out.println("Rooms left: " + roomsRemaining);
    	    if(this.getPlayers().get(currentPlayerNum).getCurrentRole() != null){
    	    	this.getPlayers().get(currentPlayerNum).getCurrentRole().setWorkable(true);
    	    	this.getPlayers().get(currentPlayerNum).setCanMove(false);
    	    } else{
    	    	this.getPlayers().get(currentPlayerNum).setCanMove(true);
    	    }

    	       while(!isEndTurn){
    	    	if(inputBuffer != null && !inputBuffer.isEmpty()){
    	    		    MyEvent event = inputBuffer.pop();
    	    		    if(!event.getActionName().equals(Constants.SET_NUMBER_OF_PLAYERS) && !event.getActionName().equals(Constants.END_TURN)){
    	    		    	String actionName = event.getActionName();
            	    		List<String> params = event.getParameters();
            	    		String [] parameters = new String[params.size()];
            	    		int i = 0;
            	    		for(String param : params){
            	    			parameters[i] = param;
            	    			i++;
            	    		}
            	    		Player player = players.get(currentPlayerNum);
            	    		player.handleAction(actionName, parameters);
            	    		//View.update();
    	    		    } else if(event.getActionName().equals(Constants.END_TURN)){
    	    		    	isEndTurn = true;   	
    	    		    }
    	    	    }
    	       }

               if(currentPlayerNum == this.getPlayers().size()-1){
                		currentPlayerNum = 0;
                } else {
                	currentPlayerNum++;
                }
                turn++;
                this.setTurn(turn);
                System.out.println("Please type your next action");
                System.out.println("Player: " + (currentPlayerNum+1));
                System.out.println("Turn: " + this.getTurn());
                isEndTurn = false;
    	     }
    	    nextDay();
        }
            //Last day has ended, start scoring
            endGame();
        }
	
	
	/* Event listener for game, will get events fired from View
	 * 
	 */
	public static void receiveEvent(MyEvent myEvent) {   
			Player currentPlayer = players.get(currentPlayerNum);
			List<String> params = myEvent.getParameters();
			String [] parameters = new String [params.size()];
			int i = 0;
			for(String param : params){
				parameters[i] = param;
				i++;
			}
            currentPlayer.handleAction(myEvent.getActionName(), parameters);
		}
    
    
    public static void addToBuffer(MyEvent event){
    	inputBuffer.add(event);
    }
}
