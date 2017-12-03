import java.util.*;

public class Main {

    public static void main(String[] args) {
        
        //start game with initial params
        Game game = new Game();


	// Create option list for dialog box
	List<Object> options = new ArrayList<Object>();
	for (Integer i = 2; i < 9; i++){
	    options.add((Object)i);
	}
	// create View and get the number of players from the view
	Integer numberOfPlayers = (Integer)View.getDialogResult("Num of Players","Please, select the number of employees between 2 and 8:\n", options);
	 game.startGame(numberOfPlayers,Constants.BOARD_XML,Constants.CARDS_XML);    
	View view = new View();
        view.setVisible(true);
       
    }
}
