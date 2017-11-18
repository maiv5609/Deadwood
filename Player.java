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
	private boolean canMove;
	private String diceColor;


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
        this.canMove = true;
        this.currentRoom = currentRoom;
        this.currentRole = null;
        this.diceColor = new Utility().getColorArray().get(playerNumber);
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
    public boolean isCanMove() {
		return canMove;
	}

	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}
    
    public String getDiceColor() {
		return diceColor;
	}

	public void setDiceColor(String diceColor) {
		this.diceColor = diceColor;
	}
    
    
    /*
     ** Upgrades rank of the player
     *	params: rank : int
     *			currency : String
     */
    private void upgradeRank(int rank, String currency) {
        this.rank = rank;
        currency = currency.toUpperCase();
        if(currentRoom.getName().toLowerCase().equals(Constants.CASTING_OFFICE)){
        	int moneyCost = 0;
        	int creditCost = 0;
        	switch(rank){
            case 2:
            	creditCost = 5;
            	moneyCost = 5;
            	break;
            case 3:
            	creditCost = 10;
            	moneyCost = 10;
            	break;
            case 4:
            	creditCost = 15;
            	moneyCost = 18;
            	break;
            case 5:
            	creditCost = 20;
            	moneyCost = 28;
            	break;
            case 6:
            	creditCost = 25;
            	moneyCost = 40;
            	break;
        	}
        	if(currency.equals("$") && (this.money >= moneyCost)){
        		this.money-=moneyCost;
        	} else if(currency.equals("CR") && (this.credits >= creditCost)){
        		this.credits-=creditCost;
        	} else{
        		System.out.println("You do not have enough credits or money.");
        	}
        } else{
        	System.out.println("You are not in the casting office. Move to the casting office first.");
        }
        
    }
    
    
    
    /* Move
     * cycles through connected nodes and sets appropriate data if it finds the correct room then returns true.
     * It returns false if the room isn't connected
     */
    private boolean move(String roomName) {
        if(canMove == false){
        	System.out.println("You are unable to move at this time");
        	return false;
        }else{
        	 List<String> connectedRooms = currentRoom.getConnectedRooms();
             for(String connectedRoomName : connectedRooms){
                 if(connectedRoomName.toLowerCase().equals(roomName)){
                     Room connectedRoom = Board.getRoomNode(connectedRoomName);
                     currentRoom.removePlayer(playerNum);
                     this.currentRoom = connectedRoom;
                     currentRoom.addPlayer(playerNum);
                     System.out.println("The player is in the " + roomName + " room");
                     return true;
                 }
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
            if ((element.getName().toLowerCase()).equals(roleName.toLowerCase())){
            	if(element.heldBy != null || this.currentRole != null){
            		System.out.println("You failed taking the role " + roleName);
                	return false;
                }else {
                	 element.setHeldBy(this);
                	 this.setCurrentRole(element);
                	 element.setWorkable(false);
                	 System.out.println("You have taken the role " + roleName);
                     return true;
                }
  
            }
        }
        System.out.println("The role " + roleName + " does not exist in this room.");
        return false;
    }
    
    /* Rehearse(int budget)
     * Takes in budget number and increments player's rehearsalNum if doing so would not put them over 100% of success at working
     * Returns true is rehearsal was successfully done, false otherwise
     */
    private boolean rehearse() {
    	int budget = this.currentRoom.getScene().getBudget();
    	if (currentRole != null){
    		if (!currentRole.getWorkable()){
    			System.out.println("Unable to rehearse");
    			return false;
    		}
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
	            System.out.println("Rehearsal counts increased by 1");
	            return true;
	        }else{
	            //Error budget is less then rehearsal
	            return false;
	        }
	    	}
    	return false;
    }
    
    
    
    /* Act
     * Takes in room, scene, and role that player is working in
     * returns true acting was attempted, false otherwise
     */
    private boolean act(Room currentRoom, Scene currentScene, Role currentRole) { 
    	
    	if(currentRole == null){
    		System.out.println("The player does not have a role");
    		return false;
    	} else{
    		if (!currentRole.getWorkable()){
    			System.out.println("Unable to act");
    			return false;
    		}
            int diceRoll, currentShots, budget, money, credits = 0;
            boolean onCard = false;
            
            budget = currentScene.getBudget();
            onCard = currentRole.getOnCard();
            //Roll dice
            diceRoll = new Random().nextInt(6);
            //Increment because nextInt returns from range starting at 0
            diceRoll++;
            diceRoll= diceRoll + this.getRehearsalNum();
            
            if(diceRoll >= budget){
            	
                //Success, update shots
                currentShots = currentRoom.getCurrentShots();
                currentShots--;
                currentRoom.setCurrentShots(currentShots);
                
                System.out.println("Shots for scene to close: " + currentRoom.getMaxShots());
                System.out.println("Current shots: " + currentShots);
                
                
                if(onCard == true){
                    credits = this.getCredits();
                    credits = credits + 2;
                    this.setCredits(credits);
                    System.out.println("You have succeded in the role. You've gained two credits!");
                }else{
                    credits = this.getCredits();
                    credits++;
                    money = this.getMoney();
                    money++; 
                    this.setCredits(credits);
                    this.setMoney(money);
                    System.out.println("You have succeded in the role. You've gained one credit and one dollar!");
                }
                
             	//Check if scene is finished
                if(currentShots == 0){
                	/* WORK NOTE: might need to change how payout works 
                	 * 
                	 */
                	//Start payouts
                	currentRole.payOut(this.money, credits);
                	
                	
                	Game.roomsRemaining--;
                }
                return true;
            }else{
                //Failure
                if(onCard == false){
                    money = this.getMoney();
                    money++;
                    this.setMoney(money);
                    System.out.println("You failed acting!");
                }
                return true;
            }
            
    	}
        
    }
    
    
    
    
    /** handleAction
     *  Handles action of the player depending on name of the action.
     *  params: action : String
     *  		parameters: String[]
     */
    public void handleAction(String action, String[] parameters) {
        if(Constants.MOVE.equals(action)){
        	if(parameters.length == 1){
        		System.out.println("Please also input the room you would like to move to.");
        	}else{
        		String direction = parameters[1];
                if(parameters.length > 2){
                	direction  = direction + " " + parameters[2];
                } 
                if ((!this.move(direction)) && canMove) {
                	System.out.println("Please select a valid room to move to.");
                } else {
                   canMove = false;
                }
        	}
        } else if(Constants.WORK.equals(action)){
            String roleName = parameters[1];
            if(parameters.length > 2){
            	for(int i = 2; i < parameters.length;i++){
            		roleName +=" " + parameters[i];
            	}
            }
            this.work(roleName);
        } else if(Constants.UPGRADE.equals(action)){
            int rank = Integer.parseInt(parameters[2]);
            String currency = parameters[1];
            this.upgradeRank(rank, currency);
        } else if(Constants.REHEARSE.equals(action)){
            this.rehearse();  
        } else if(Constants.ACT.equals(action)){
            this.act(currentRoom, currentRoom.getScene(), currentRole);
        } else if(Constants.WHO.equals(action)){
            System.out.println("Current player is player #" + (this.playerNum + 1));
            System.out.println("Current player has dice color: " + (this.diceColor));
            System.out.println("Current player has " + (this.money) + "$");
            System.out.println("Current player has " + (this.credits) + "cr");
            System.out.println("Current player has rank #" + (this.rank));
            if(this.currentRole != null){
            	System.out.println("Current player is working this role: " + (this.currentRole.getName()));
            }else{
            	System.out.println("Player does not have a role yet");
            }
            
        } else if(Constants.WHERE.equals(action)){
            System.out.println("Current player is in the room: " + this.currentRoom.getName());
            String adjacentRooms = "";
            int i = 0;
            for (String rName: currentRoom.getConnectedRooms()){
            	if (i > 0) {
            		adjacentRooms += ", ";
            	}
            	adjacentRooms += rName;
            	i++;
            }
            System.out.println("Adjacent rooms are " + adjacentRooms);
            System.out.println("The Scene for this room is " + currentRoom.getScene().getName() 
            		+ ". It has a bugdet of " + currentRoom.getScene().getBudget() + ".");
            
            String rolesOnCard = "";
            List<Role> roles = currentRoom.getRoles();
            for (Role role: roles){
            	if (role.onCard){
            		System.out.println(role);
            		rolesOnCard+= " " + role;
            	}
            }
            System.out.println("On card roles:  " + rolesOnCard);
            
            
            
            /* WORK NOTE: 
             * We might want to display what roles are available in the current room
             */
        }
        
    }
    
    
}
