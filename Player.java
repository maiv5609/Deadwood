
public class Player {
	int rank;
	int playerNum;
	int money;
	int credits;
	int rehearsalNum;
	int total;
	Room currentRoom;
	/* Constructors
	 *
	 */

	//Default Constructor
	player(int rank, int playerNumber, int money, int credits, Room currentRoom){
		this.rank = rank;
		this.playerNum = playerNumber;
		this.money = money;
		this.credits = credits;
		this.rehearsalNum = 0;
		this.total = 0;
		this.currentRoom = currentRoom;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getPlayerNUm() {
		return playerNum;
	}

	public void setPlayerNUm(int playerNUm) {
		this.playerNum = playerNUm;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public int getSlot() {
		return slot;
	}

	public void setSlot(int slot) {
		this.slot = slot;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public int getRehearsalNum() {
		return rehearsalNum;
	}

	public void setRehearsalNum(int rehearsalNum) {
		this.rehearsalNum = rehearsalNum;
	}

	public Boolean getTurn() {
		return turn;
	}

	public void setTurn(Boolean turn) {
		this.turn = turn;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	private void upgradeRank(int rank, String currency) {

	}

	private void move(String direction) {

	}

	private void takeRole(String roleName) {

	}

	private void rehearse() {

	}

	private void work() {

	}

	public void handleAction(String action, String parameters) {

	}


}
