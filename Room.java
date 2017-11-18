import java.util.List;

public class Room {
    
    private String name;
    private int maxShots;
    private int currentShots;
    private List<Integer> playersInTheRoom;
    private List<Role> roles;
    private Scene scene;
    private List<String> connectedRooms;

    public Room(){
	name = null;
	maxShots = 0;
	currentShots = 0;
	playersInTheRoom = null;
	rols = null;
	scene = null;
	connectedRoom = null;
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
    
    
    
}
