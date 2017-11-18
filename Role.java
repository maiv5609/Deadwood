
public class Role {
    
    String name;
    String line;
    int workable;
    int rank;
    Boolean onCard;
    int heldBy;
    
    public Role(){
        name = null;
        line = null;
        workable = 0;
        rank = 0;
        onCard = false;
        heldBy = 0;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getLine() {
        return line;
    }
    
    public void setLine(String line) {
        this.line = line;
    }
    
    public int getWorkable() {
        return workable;
    }
    
    public void setWorkable(int workable) {
        this.workable = workable;
    }
    
    public int getRank() {
        return rank;
    }
    
    public void setRank(int rank) {
        this.rank = rank;
    }
    
    public Boolean getOnCard() {
        return onCard;
    }
    
    public void setOnCard(Boolean onCard) {
        this.onCard = onCard;
    }
    
    public int getHeldBy() {
        return heldBy;
    }
    
    public void setHeldBy(int heldBy) {
        this.heldBy = heldBy;
    }
    
    
    /* PayOut
     * params : currentPlayer : Player
     * 			money : int
     * 			credits : int
     */
    public void payOut(Player currentPlayer, int money, int credits) {
        int currMoney = currentPlayer.getMoney();
        int currCredit = currentPlayer.getCredits();
        currentPlayer.setMoney(currMoney + money);
        currentPlayer.setCredits(currCredit + credits);
    }
}
