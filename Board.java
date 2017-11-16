import java.util.List;
import java.util.Map;

public class Board {
    private static Board instance = null;
    Map<Integer, Room> roomMap;
    List<Scene> scenes;
    Integer topOfDeck;
    
    private Board() {
        topOfDeck = 0;
        for (Integer i = 0; i < 4; i++){
            Room curr = new Room();
            roomMap.put(i, curr);
        }
        // Setup scene list
    }
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
    
    /**
     * Makes sure that there is no more than one instance is created
     * 		returns : instance : Board
     */
    public static Board getInstance(){
        if(instance == null){
            instance = new Board();
        }
        return instance;
    }
    
    
    
}
