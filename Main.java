
public class Main {

	public static void main(String[] args) {
        
        //start game with initial params
        Game game = new Game();

       // create View and get the number of players from the view
	Integer numberOfPlayers = View.getPlayers();
        View view = new View();
        view.setVisible(true);
        game.startGame(numberOfPlayers,Constants.BOARD_XML,Constants.CARDS_XML);   
        game.runGame();
    }
}
