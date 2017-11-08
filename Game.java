import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Game {
    int turn;
    int currDay;
    int maxDay;
    List<Player> players;
    int roomsRemaining;
    int totalRooms;
    
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
    
    private void nextDay() {
        
    }
    
    private void scoring() {
        List<Player> players = this.players;
        if(players != null & !players.isEmpty()){
            for (Player player : players){
                player.setTotal(player.getCredits() + player.getMoney() + 5*player.getRank());
            }
        }
    }
    
    private void startGame(boolean b, int currentDay, int turn, int maxDay, int playersNum, int maxDays) {
        //create a Trailer
        Room currentRoom = new Room();
        currentRoom.setTrailer(b);
        
        //add players
        for (int i = 0; i < 3; i++){
            Player player = new Player(0,i,0,0,currentRoom);
            this.players.add(player);
        }
        //set Current day to 1
        this.currDay = currentDay;
        //set Current turn to 0
        this.turn = turn;
        //set Max Day to 0
        this.maxDay = maxDay;
    }
    
    private void endGame() {
        
    }
    
    public void handleUserInput(String action, String parameters) {
        // Player currentPlayer = getPlayers().get(getTurn());
        // currentPlayer.handleAction(action, parameters);
    }
    
    
    
    public void updateView(){
        
    }
    
    
    private static String inputReader(){
        String input = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            input = br.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return input;
    }
    
    public static void main(String[] args) {
        //ask user for players number
        System.out.println("Please, set number of players:");
        int playersNum = Integer.parseInt(inputReader());
        //ask user for number of days
        System.out.println("Please, set number of days:");
        int maxDays = Integer.parseInt(inputReader());
        
        //start game with initial params
        Game game = new Game();
        game.startGame(true, 1, 1, 4, playersNum, maxDays);
        
        System.out.print("The game has just started!!");
        
        
        
    }
    
}
