import java.util.List;
import java.util.Random;

public class Player {
    private int rank;
    private int playerNum;
    private int money;
    private int credits;
    private int rehearsalNum;
    private int total;
    private Room currentRoom;
    private Role currentRole;
    
    /*
     * Constructors
     */
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
    
    public int getPlayerNum() {
        return playerNum;
    }
    
    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
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
    
    
    public int getRehearsalNum() {
        return rehearsalNum;
    }
    
    public void setRehearsalNum(int rehearsalNum) {
        this.rehearsalNum = rehearsalNum;
    }
    
    
    public int getTotal() {
        return total;
    }
    
    public void setTotal(int total) {
        this.total = total;
    }
    
    public Room getCurrentRoom() {
        return currentRoom;
    }
    
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
    
    
    public Role getCurrentRole() {
        return currentRole;
    }
    
    public void setCurrentRole(Role currentRole) {
        this.currentRole = currentRole;
    }
    
    private void upgradeRank(int rank, String currency) {
        this.rank = rank;
        currency = currency.toUpperCase();
        
        switch(rank){
            case 2:
                if(currency.equals(Constants.CREDITS)){
                    this.credits = this.credits - 5;
                    break;
                }else{
                    this.money = this.money - 5;
                    break;
                }
            case 3:
                if(currency.equals(Constants.CREDITS)){
                    this.credits = this.credits - 10;
                    break;
                }else{
                    this.money = this.money - 10;
                    break;
                }
            case 4:
                if(currency.equals(Constants.CREDITS)){
                    this.credits = this.credits - 15;
                    break;
                }else{
                    this.money = this.money - 18;
                    break;
                }
            case 5:
                if(currency.equals(Constants.CREDITS)){
                    this.credits = this.credits - 20;
                    break;
                }else{
                    this.money = this.money - 28;
                    break;
                }
            case 6:
                if(currency.equals(Constants.CREDITS)){
                    this.credits = this.credits - 25;
                    break;
                }else{
                    this.money = this.money - 40;
                    break;
                }
        }
    }
    
    private boolean move(String roomName) {
        // cycles through connected nodes and sets appropriate data if it finds the correct room then returns true.
        // It returns false if the room isn't connected
        
        
        //	currentRoom.getConnectedNodes().forEach((k,v)->{
        //		if(v.getName().equals(roomName)){
        //		    currentRoom.removePlayer(playerNum);
        //		    this.currentRoom = v;
        //		    v.addPlayer(playerNum);
        //		    return true;
        //		}
        //	    });
        //	return false;
        
        Boolean found = false;
        List<String> connectedRooms = currentRoom.getConnectedRooms();
        for(String connectedRoomName : connectedRooms){
            if(connectedRoomName.equals(roomName)){
                Room connectedRoom = Board.getRoomNode(connectedRoomName);
                currentRoom.removePlayer(playerNum);
                this.currentRoom = connectedRoom;
                currentRoom.addPlayer(playerNum);
                return true;
            }
        }
        return false;
    }
    
    /* Work
     * Takes in roleName and checked it vs the roles in the room that the player is in
     * If found and is empty it assigns the role to the player and returns true
     * If fails, return false
     */
    private boolean work(String roleName) {
        List<Role> roomRoles = this.currentRoom.getRoles();
        
        for(Role element : roomRoles){
            if ((element.getName()).equals(roleName)){
                element.setHeldBy(this.playerNum);
                return true;
            }
        }
        System.out.println("Taking role failed, Role not found or filled");
        return false;
    }
    
    /* rehearse(int budget)
     * Takes in budget number and increments player's rehearsalNum if doing so would not put them over 100% of success at working
     * Returns true is rehearsal was successfully done, false otherwise
     */
    private boolean rehearse(int budget) {
        int currentRehearsalNum;
        
        currentRehearsalNum = this.getRehearsalNum();
        //Lowest roll is 1 + rehearsalNum
        currentRehearsalNum++;
        
        if (currentRehearsalNum == budget){
            //Working roll is already 100% of success
            System.out.println("Rehearsal failed, already 100% success rate");
            return false;
        }else if(currentRehearsalNum < budget){
            //Successful rehearsal
            this.setRehearsalNum(currentRehearsalNum);
            return true;
        }else{
            //Error budget is less then rehearsal
            return false;
        }
    }
    
    
    
    /* Act(Scene currScene)
     * Takes in room, scene, and role that player is working in
     */
    private void act(Room currentRoom,Scene currentScene, Role currentRole) { //act() act
        //Will need rehearsal num and if role is oncard or not
        int diceRoll, currentShots, budget, money, credits = 0;
        boolean onCard = false;
        
        budget = currentScene.getBudget();
        onCard = currentRole.getOnCard();
        //Roll dice, need to import Random class
        diceRoll = new Random().nextInt(6);
        //Increment because nextInt returns from range starting at 0
        diceRoll++;
        diceRoll= diceRoll + this.getRehearsalNum();
        
        if(diceRoll >= budget){
            //Success, update shots
            currentShots = currentRoom.getCurrentShots();
            currentShots++;
            currentRoom.setCurrentShots(currentShots);
            if(onCard == true){
                credits = this.getCredits();
                credits = credits + 2;
                this.setCredits(credits);
            }else{
                credits = this.getCredits();
                credits++;
                money = this.getMoney();
                money++;
                
                this.setCredits(credits);
                this.setMoney(money);
            }
        }else{
            //Failure
            if(onCard == false){
                money = this.getMoney();
                money++;
                this.setMoney(money);
            }
        }
    }
    
    
    
    
    /** Handles action of the player depending on name of the action.
     */
    public void handleAction(String action, String[] parameters) {
        if(Constants.MOVE.equals(action)){
            String direction = parameters[1];
            this.move(direction);
        } else if(Constants.WORK.equals(action)){
            String roleName = parameters[1];
            this.work(roleName);
        } else if(Constants.UPGRADE.equals(action)){
            int rank = Integer.parseInt(parameters[1]);
            String currency = parameters[2];
            this.upgradeRank(rank, currency);
        } else if(Constants.REHEARSE.equals(action)){
            int credits = Integer.parseInt(parameters[1]);
            this.rehearse(credits);
        } else if(Constants.ACT.equals(action)){
            this.act(currentRoom, currentRoom.getScene(), currentRole);
        } else if(Constants.WHO.equals(action)){
            System.out.println("Current player is :" + this.playerNum);
        }
        
    }
    
    
}
