
public class Role {
    
    String name;
    String line;
    boolean workable;
    int rank;
    Boolean onCard;
    Player heldBy;

   
	public Role(){
    	name = null;
    	line = null;
    	workable = false;
    	rank = 0;
		onCard = false;
		heldBy = null;
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
    
    public boolean getWorkable() {
        return workable;
    }
    
    public void setWorkable(boolean workable) {
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
    public Player getHeldBy() {
		return heldBy;
	}

	public void setHeldBy(Player heldBy) {
		this.heldBy = heldBy;
	}

    
    
    /* PayOut
     * params : currentPlayer : Player
     * 			money : int
     * 			credits : int
     */
	/* WORK NOTE: changed how payout works
	 * "Off-card roles receive money equal to the role"
	 * payout handles paying off role and room takes care of on-card payout?
	 */
    public void payOut(int money, int credits) {
        int currMoney = heldBy.getMoney();
        int currCredit = heldBy.getCredits();
        heldBy.setMoney(currMoney + money);
        heldBy.setCredits(currCredit + credits);
    }
}
