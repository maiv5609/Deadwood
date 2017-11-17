import java.util.List;
import java.util.Map;

public class Room {
    
    private String name;
    private int maxShots;
    private int currentShots;
    private List<Integer> playersInTheRoom;
    private List<Role> roles;
    private Scene scene;
    
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
    
    public Map<Integer, Room> getConnectedNodes() {
        return connectedNodes;
    }
    
    public void setConnectedNodes(Map<Integer, Room> connectedNodes) {
        this.connectedNodes = connectedNodes;
    }
    
    public String getRoomType() {
        return roomType;
    }
    
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
    public void addPlayer(int playerNumber) {
        this.playersInTheRoom.add(playerNumber);
    }
    
    public void removePlayer(int playerNumber) {
        this.playersInTheRoom.remove(this.playersInTheRoom.indexOf(playerNumber));
    }
    
    
    
}
