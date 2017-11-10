
public class Player {
    private int rank;
    private int playerNum;
    private int money;
    private int credits;
    private int rehearsalNum;
    private int total;
    private Room currentRoom;
	/* Constructors
	 *
	 */

	//Default Constructor
    public Player(int rank, int playerNumber, int money, int credits, Room currentRoom){
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
	this.rank = rank;
	currency = currency.toUpperCase();
	
	switch(rank){
	case rank == 2:
	    if(currency.equals("CREDITS"){
		    this.credit = this.credit - 5;
		    break;
		}else{
		    this.money = this.money - 5;
		    break;
		}
	    case rank == 3:
		if(currency.equals("CREDITS"){
			this.credit = this.credit - 10;
			break;
		    }else{
			this.money = this.money - 10;
			break;
		    }
		case rank == 4:
		    if(currency.equals("CREDITS"){
			    this.credit = this.credit - 15;
			    break;
			}else{
			    this.money = this.money - 18;
			    break;
			}
		    case rank == 5:
			if(currency.equals("CREDITS"){
				this.credit = this.credit - 20;
				break;
			    }else{
				this.money = this.money - 28;
				break;
			    }
			case rank == 6:
			    if(currency.equals("CREDITS"){
				    this.credit = this.credit - 25;
				    break;
				}else{
				    this.money = this.money - 40;
				    break;
				}
				}
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
