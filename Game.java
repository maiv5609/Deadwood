import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
    }entry.getValue().

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

    private void nextDay(Map roomMap) {
        if (roomsRemaining == 1){
          Scene nextScene;
          Room currRoom;
          for (roomMap.Entry<Integer, Room> entry : map.entrySet()){
            //Currently having placeholder for scene until pool of scenes is implemented
            nextScene = new Scene();
            currRoom = entry.getValue();
            //Iterate through map and populate scenes if there is none
            map.put(entry.getKey(), currRoom.setScene(Scene));
          }
          this.currDay++;
        }
    }

    private void scoring() {
        List<Player> players = this.players;
        if(players != null & !players.isEmpty()){
            for (Player player : players){
                player.setTotal(player.getCredits() + player.getMoney() + 5*player.getRank());
            }
        }
    }

    private void startGame(int currentDay, int turn, int maxDay, int playersNum, int maxDays, int credits, int rank) {
        //create a Trailer
        Room currentRoom = new Room();
        currentRoom.setTrailer(true);

        //add players
        List<Player> players = new ArrayList<Player>();
        this.setPlayers(players);

        for (int i = 0; i < 3; i++){
            Player player = new Player(rank,i,0,credits,currentRoom);
            this.players.add(player);
        }

        //set Current day to 1
        this.currDay = currentDay;
        //set Current turn to 0
        this.turn = turn;
        //set Max Day to 0
        this.maxDay = 4;
        //set totalRooms
        this.totalRooms = 12;
    }

    private void endGame() {
        this.scoring();
    }

    public void handleUserInput(String action, String parameters) {
        Player currentPlayer = getPlayers().get(getTurn());
        currentPlayer.handleAction(action, parameters);
    }



    public void updateView(){

    }


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
        game.startGame(1, 1, 4, playersNum, maxDays, rank, credits);
        //print players
        System.out.println("The game has just started!!");
        for(int i = 0; i < playersNum; i++){
            System.out.println("Player"+ game.getPlayers().get(i).getPlayerNUm());
        }


        //		String input = null;
        //		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //		try {
        //			input = br.readLine();
        //		} catch (IOException e) {
        //			// TODO Auto-generated catch block
        //			e.printStackTrace();
        //		}
        //		String action = null;
        //		String parameters = null;
        //		while(input != null){
        //			action = utility.inputReader();
        //			System.out.println(action);
        //			if(action == Constants.TAKE_ROLE ||
        //			   action == Constants.UPGRADE_RANK ||
        //			   action == Constants.MOVE){
        //
        //			}
        //				parameters = utility.parseParams(parameters);
        //				game.handleUserInput(action, parameters);
        //update view
        //set turn ++
        //



    }

}
