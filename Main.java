
public class Main {

	public static void main(String[] args) {
        
        //start game with initial params
        Game game = new Game();

       // create View and get the number of players from the view
        View view = new View();
        view.setVisible(true);
        game.setView(view);
        Integer numberOfPlayers = view.getPlayers();
        
        game.startGame(numberOfPlayers,Constants.BOARD_XML,Constants.CARDS_XML);
        //print players
        for(int i = 0; i < numberOfPlayers; i++){
            System.out.println("Player "+ (game.getPlayers().get(i).getPlayerNum() + 1));
        }      
        
        System.out.println("Please type your next action");
        System.out.println("Player: " + (game.currentPlayerNum+1));
        System.out.println("Turn: " + game.getTurn());
        
        //General Game Loop
        while(game.currDay <= game.maxDay){
        	//Day Loop 
            while(game.roomsRemaining != 1){
            System.out.println("Rooms left: " + game.roomsRemaining);
    	    if(game.getPlayers().get(game.currentPlayerNum).getCurrentRole() != null){
    	    	game.getPlayers().get(game.currentPlayerNum).getCurrentRole().setWorkable(true);
    	    	game.getPlayers().get(game.currentPlayerNum).setCanMove(false);
    	    } else{
    	    	game.getPlayers().get(game.currentPlayerNum).setCanMove(true);
    	    }
    	      //Turn loop
              //  while(!input.equals(Constants.END_TURN)){
    	       while(!game.isEndTurn){
    	    	  // Game.updateBuffer();
    	    	   
    	    	   
    	    	   
//                    input = utility.inputReader();
//                    String[] parameters = utility.parseParams(input);
//                    String action = parameters[0];
//                    game.handleUserInput(action, parameters, player);
//                    
//                    System.out.println(input);
//                    System.out.print("Next command: ");
//                    //update view
               }
 
                if(game.currentPlayerNum == game.getPlayers().size()-1){
                	game.currentPlayerNum = 0;
                } else {
                	game.currentPlayerNum++;
                }
                game.turn++;
                game.setTurn(game.turn);
                System.out.println("Please type your next action");
                System.out.println("Player: " + (game.currentPlayerNum+1));
                System.out.println("Turn: " + game.getTurn());
            }
            game.nextDay();
        }
        //Last day has ended, start scoring
        game.endGame();
    }
	
	
	
	
}
