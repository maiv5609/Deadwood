import java.util.List;
import java.util.Map;

public class Board {
	Map<Integer, Room> roomMap;
	List<Scene> scenes;

	public Map<Integer, Room> getRoomMap() {
		return roomMap;
	}

	public void setRoomMap(Map<Integer, Room> roomMap) {
		this.roomMap = roomMap;
	}

	public List<Scene> getScenes() {
		return scenes;
	}

	public void setScenes(List<Scene> scenes) {
		this.scenes = scenes;
	}
}
