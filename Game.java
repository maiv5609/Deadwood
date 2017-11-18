import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Game {
    int turn;
    int currDay;
    int maxDay;
    List<Player> players;
    //int roomsRemaining;
    //int totalRooms;
    
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
        this.roomsRemaining = roomsRemaining;
    }
    
    public int getTotalRooms() {
        return totalRooms;
    }
    
    public void setTotalRooms(int totalRooms) {
        this.totalRooms = totalRooms;
    }
    
    
    /** nextDay
     *  sets necessary configuration for the next day
     */
    private void nextDay(Map<Integer, Room> roomMap) {
        if (roomsRemaining == 1){
            Scene nextScene;
            Room currRoom;
            for (Map.Entry<Integer, Room> entry : roomMap.entrySet()){
                //Currently having placeholder for scene until pool of scenes is implemented
                //Iterate through map and populate scenes if there is none
                nextScene = new Scene();
                currRoom = entry.getValue();
                currRoom.setScene(nextScene);
                
            }
            this.currDay++;
        }
    }
    
    
    
    
    /** scoring
     *  sets total amount of money, credits and fame to each player in the game
     */
    private void scoring() {
        List<Player> players = this.players;
        if(players != null & !players.isEmpty()){
            for (Player player : players){
                player.setTotal(player.getCredits() + player.getMoney() + 5*player.getRank());
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
        
        Board board = Board.getInstance(boardXml, cardsXml);
	board.populateRooms();
        List<Player> players = new ArrayList<Player>();
        this.setPlayers(players);
        
        for (int i = 0; i < 3; i++){
            Player player = new Player(rank,i,0,credits,board.getRoomNode("trailer"));
            this.players.add(player);
            board.getRoomNode("trailer").addPlayer(i);
        }
        
        //set Current day to 1
        this.currDay = currentDay;
        //set Current turn to 0
        this.turn = turn;
        //set Max Day to 0
        this.maxDay = 4;
        //set totalRooms
        //this.totalRooms = 12;
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
    public void handleUserInput(String action, String[] parameters, int playerNum) {
        Player currentPlayer = this.getPlayers().get(playerNum);
        currentPlayer.handleAction(action, parameters);
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
        int rank = 0;
        
        Utility utility = new Utility();
        
        System.out.println("Please, set number of players between 2 and 8:");
        String playersNumStr = utility.inputReader();
        if(playersNumStr != null && !playersNumStr.isEmpty()){
            playersNum = Integer.parseInt(playersNumStr);
            while(playersNum < 2 || playersNum > 8){
                System.out.println("Please, set number of players between 2 and 8:");
            }
        } else{
            System.out.println("Please, set number of players:");
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
        String cardsXml = Constants.BOARD_XML;
        game.startGame(1, 1, 4, playersNum, maxDays, rank, credits,boardXml,cardsXml);
        //print players
        System.out.println("The game has just started!!");
        for(int i = 0; i < playersNum; i++){
            System.out.println("Player"+ (game.getPlayers().get(i).getPlayerNum() + 1));
        }
        
        
        /** Parse params (action name + additional parameters for action)
         *   from user input and pass it to Player to handle
         */
        String input = "";
        int turn = 1;
        int player = 0;
        while(true){
	    if (players.get(player).getCurrentRole() != null){
		players.get(player).getCurrentRole().setWorkable(true);
	    }
            while(!input.equals(Constants.END_TURN)){
                input = utility.inputReader();
                String[] parameters = utility.parseParams(input);
                String action = parameters[0];
                game.handleUserInput(action, parameters, player);
                
                System.out.println(input);
                //update view
            }
            input = "";
            if(player == game.getPlayers().size()-1){
                player = 0;
            } else {
                player++;
            }
            turn++;
            game.setTurn(turn);
            System.out.println("Player: " + (player+1));
            System.out.println("Turn: " + game.getTurn());
        }
        
        
    }
    
}
