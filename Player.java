
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
    
    private void move(String direction) {
        
    }
    
    private void takeRole(String roleName) { // work
        
    }
    
    private void rehearse() {
        
    }
    
    private void work() { //act() act
        
    }
    
    
    
    
    /** Handles action of the player depending on name of the action.
     */ 
    public void handleAction(String action, String[] parameters) {
        if(Constants.MOVE.equals(action)){
            String direction = parameters[1];
            this.move(direction);	
        } else if(Constants.TAKE_ROLE.equals(action)){
            String roleName = parameters[1];
            this.takeRole(roleName);
        } else if(Constants.UPGRADE.equals(action)){
            int rank = Integer.parseInt(parameters[1]);
            String currency = parameters[2];
            this.upgradeRank(rank, currency);
        } else if(Constants.REHEARSE.equals(action)){
            this.rehearse();
        } else if(Constants.WORK.equals(action)){
            this.work();
        } else if(Constants.WHO.equals(action)){
            System.out.println("Current player is :" + this.playerNum);
        }
        
    }
    
    
}
