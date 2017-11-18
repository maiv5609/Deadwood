import java.io.File;
import java.util.List;
import java.util.Map;

public class Board {
    private static Board instance = null;
    private static Map<String, Room> roomMap;
    private List<Scene> scenes;
    private Integer topOfDeck;
    
    private Board(String boardXml, String cardsXML) {
        XMLparse xmlParse = new XMLparse();
        File file1 = new File(boardXml);
        File file2 = new File(cardsXML);
        String type1 = xmlParse.getTagName(file1);
       if(type1.equals("board")){
            roomMap = xmlParse.getAllRooms();
       } else {
            scenes = xmlParse.getScenesAsList();
        }
        
        String type2 = xmlParse.getTagName(file2);
        if(type2.equals("board")){
            roomMap = xmlParse.getAllRooms();
        } else {
            scenes = xmlParse.getScenesAsList();
        }
	topOfDeck = 0;
    }
    
    public Map<String, Room> getRoomMap() {
        return roomMap;
    }
    
    public void setRoomMap(Map<String, Room> roomMap) {
        this.roomMap = roomMap;
    }
    
    public List<Scene> getScenes() {
        return scenes;
    }
    
    public void setScenes(List<Scene> scenes) {
        this.scenes = scenes;
    }
    
    /*
     * Makes sure that there is no more than one instance is created
     * 		returns : instance : Board
     */
    public static Board getInstance(String boardXml, String cardsXML){
        if(instance == null){
            instance = new Board(boardXml, cardsXML);
        }
        return instance;
    }
    
    /* getRoom
     * takes in name of room and looks through hashmap to find room value
     */
    public static Room getRoomNode(String roomName){
        Room returnRoom = roomMap.get(roomName);
        return returnRoom;
    }

    public void populateRooms() {
	for (Room curr: roomMap.values()){
	    if (curr.getScene() != null){
		curr.setScene(scenes.get(topOfDeck));
		curr.addAll(curr.getScene().getRole());
		topOfDeck++;
	    }
	}
    }
    
    
}
