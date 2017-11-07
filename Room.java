import java.util.List;
import java.util.Map;

public class Room {

	boolean isCastingOffice;
	boolean isTrailers;
	int maxShots;
	int currentShots;
	List<Integer> playersInTheRoom;
	List<Role> roles;
	Scene scene;
	int roomType;
	Map<Integer, Room> connectedNodes;
	String orientation; // horizontal or vertical

	public boolean isCastingOffice() {
		return isCastingOffice;
	}

	public void setCastingOffice(boolean isCastingOffice) {
		this.isCastingOffice = isCastingOffice;
	}

	public boolean isTrailers() {
		return isTrailers;
	}

	public void setTrailers(boolean isTrailers) {
		this.isTrailers = isTrailers;
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

	public int getRoomType() {
		return roomType;
	}

	public void setRoomType(int roomType) {
		this.roomType = roomType;
	}

	public Map<Integer, Room> getConnectedNodes() {
		return connectedNodes;
	}

	public void setConnectedNodes(Map<Integer, Room> connectedNodes) {
		this.connectedNodes = connectedNodes;
	}

	public void addPlayer() {

	}

	public void removePlayer() {

	}

	public List<Room> listAdjacentRooms() {
		List<Room> room = null;
		;
		return room;
	}

	public void updateShots() {

	}

}
