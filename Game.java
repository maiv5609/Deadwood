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

	}

	private void startGame() {

	}

	private void endGame() {

	}

	public void handleInput(String action, String parameters) {
		// Player currentPlayer = getPlayers().get(getTurn());
		// currentPlayer.handleAction(action, parameters);
	}

	public static void main(String[] args) {

		System.out.print("It works!");
	}

}
