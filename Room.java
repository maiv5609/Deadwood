import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Room {
    
    private String name;
    private int maxShots;
    private int currentShots;
    private List<Integer> playersInTheRoom;
    private List<Role> roles;
    private Scene scene;
    private List<String> connectedRooms;
    private Integer[] areaXY;
    private Integer[] areaHW;

    public Room(){
    	name = null;
    	maxShots = 0;
    	currentShots = 0;
    	playersInTheRoom = new ArrayList<Integer>();
    	roles = null;
    	scene = null;
    	connectedRooms = new ArrayList<String>();
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getMaxShots() {
        return maxShots;
    }
    
    public void setMaxShots(int maxShots) {
        this.maxShots = maxShots;
    }
    
    public int getCurrentShots() {
        return currentShots;
    }
    
    public void setCurrentShots(int currentShots) {
        this.currentShots = currentShots;
    }
    
    public List<Integer> getPlayersInTheRoom() {
        return playersInTheRoom;
    }
    
    public void setPlayersInTheRoom(List<Integer> playersInTheRoom) {
        this.playersInTheRoom = playersInTheRoom;
    }
    
    public List<Role> getRoles() {
        return roles;
    }
    
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    
    public Scene getScene() {
        return scene;
    }
    
    public void setScene(Scene scene) {
        this.scene = scene;
    }
    public List<String> getConnectedRooms() {
        return connectedRooms;
    }
    
    public void setConnectedRooms(List<String> connectedRooms) {
        this.connectedRooms = connectedRooms;
    }

    public Integer[] getAreaXY(){
	return areaXY;
    }

    public void setAreaXY(Integer[] areaXY){
	this.areaXY = areaXY;
    }

    public Integer[] getAreaHW(){
	return areaHW;
    }

    public void setAreaHW(Integer[] areaHW){
	this.areaHW = areaHW;
    }
    
    
    
    /* addPlayer
     * adds player to the room
     * params : playerNumber : int
     */
    public void addPlayer(int playerNumber) {
        this.playersInTheRoom.add(playerNumber);
    }
    
    /* removePlayer
     * remove Player from the room
     * params : playerNumber : int
     */
    public void removePlayer(int playerNumber) {
        this.playersInTheRoom.remove(this.playersInTheRoom.indexOf(playerNumber));
    }

    public void closeScene() {
	boolean payout = false;
	// Check to see if extra payment is necessary
	for (Role curr: scene.getRole()){
	    if (curr.getHeldBy() != null){
		payout = true;
	    }
	}
	// remove roles that are on the card from the room's list of roles
	for (Role curr: scene.getRole()){
	    roles.remove(curr);
	}
	// pay extras if necessary
	for (Role curr: roles){
	    if (curr.getHeldBy() != null){
		if (payout){
		    curr.payOut(curr.getRank(),0);
		}
		curr.getHeldBy().setRehearsalNum(0);
		curr.getHeldBy().setCurrentRole(null);
	    }
	    curr.setHeldBy(null);
	}
	// pay people on the card 
	int budget = scene.getBudget();
	List<Integer> rolls = new ArrayList<Integer>();
	for (int i = 0; i < budget; i++){
	    rolls.add(ThreadLocalRandom.current().nextInt(1,7));
	}
	Collections.sort(rolls);
	int length = rolls.size();
	int startValue = scene.getRole().size()-1;
	if (payout){
	    for (int i = 0; i < length; i++){
		for (int j = startValue; j >= 0; j--){
		    Role curr = scene.getRole().get(j);
		    if (curr.getHeldBy() != null){
			curr.payOut(rolls.get(i),0);
			startValue = j-1;
			j = -1;
		    }
		    if (startValue < 0) {
			startValue = (scene.getRole().size()-1);
		    }
		    
		}
	    }
	}
	// free players from roles and vice versa
	for (Role curr: scene.getRole()){
		if (curr.getHeldBy() != null){
		curr.getHeldBy().setRehearsalNum(0);
		curr.getHeldBy().setCurrentRole(null);
		}
	}
	// remove scene from room
	scene = null;
    }
    
    
}
