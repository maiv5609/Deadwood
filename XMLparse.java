import java.util.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;

public class XMLparse {
    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder dBuilder;
    private Document doc;

    XMLparse() {
    	dbFactory = DocumentBuilderFactory.newInstance();
    	try {
    		dBuilder = dbFactory.newDocumentBuilder();
    	} catch (ParserConfigurationException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }

    
    
    
    
    public String getTagName(File XMLfile) {
    	try {
			doc = dBuilder.parse(XMLfile);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	doc.getDocumentElement().normalize();
    	String root = doc.getDocumentElement().getTagName();
    	return root;
    }
    
    /**
     * getScenesAsList
     * 		
     */
    public List<Scene> getScenesAsList() {
	List<Scene> listOfScenes = new ArrayList<Scene>();
	NodeList nList = doc.getElementsByTagName("card");
	int length = nList.getLength();
	for (int i = 0; i < length; i++){
	    Node nNode = nList.item(i);
	    Scene curr = new Scene();
	    int budget;
	    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		Element eElement = (Element) nNode;
		budget = Integer.parseInt( ((Element) nNode).getAttribute("budget"));
		// Grab and set non-role data
		curr.setName( ((Element) nNode).getAttribute("name"));
		curr.setBudget(budget);
		curr.setDescription(((Element) eElement.getElementsByTagName("scene").item(0)).getTextContent());
		// Aggregate role data and add it to scene
		List<Role> roles = new ArrayList<Role>();
		NodeList rList = eElement.getElementsByTagName("part");
		int numOfRoles = rList.getLength();
		int rank;
		for (int j = 0; j < numOfRoles; j++){
		    Role currRole = new Role();
		    currRole.setOnCard(true);
		    Node rNode = rList.item(j);
		    if (rNode.getNodeType() == Node.ELEMENT_NODE) {
			Element rElement = (Element) rNode;
			rank = Integer.parseInt(rElement.getAttribute("level"));
			// Grabs and sets relevant role data
			currRole.setName(rElement.getAttribute("name"));
			currRole.setRank(rank);
			currRole.setLine(rElement.getElementsByTagName("line").item(0).getTextContent());
			roles.add(currRole);
		    }
		}
		curr.setRole(roles);
		// Add filled out scene to list
		listOfScenes.add(curr);
	    }
	}
	return listOfScenes;
    }
    
    /**
     * getAllRooms
     * 		
     */
    public Map<String,Room> getAllRooms(){
	Map<String,Room> rooms = new HashMap<String,Room>();
	// Read and add all normal rooms
	NodeList roomList = doc.getElementsByTagName("set");
	int length = roomList.getLength();
	for (int i =0; i < length; i++){
	    Room curr = new Room();
	    String name="";
	    int maxShots;
	    Node roomNode = roomList.item(i);
	    if (roomNode.getNodeType() == Node.ELEMENT_NODE) {
		Element room = (Element) roomNode;
		// get and set shots and name
		maxShots = Integer.parseInt( ((Element) ((Element) room.getElementsByTagName("takes").item(0)).getElementsByTagName("take").item(0)).getAttribute("number"));
		curr.setMaxShots(maxShots);
		curr.setCurrentShots(curr.getMaxShots());
		name = room.getAttribute("name");
		curr.setName(name);
		// get and set neighboring rooms
		List<String> connectedRooms = new ArrayList<String>();
		NodeList neighbors = ((Element) room.getElementsByTagName("neighbors").item(0)).getElementsByTagName("neighbor");
		int numOfNeighbors = neighbors.getLength();
		for (int j = 0; j < numOfNeighbors; j++){
		    Node neighborNode = neighbors.item(j);
		    if (neighborNode.getNodeType() == Node.ELEMENT_NODE) {
			Element nElement = (Element) neighborNode;
			connectedRooms.add(nElement.getAttribute("name"));
		    }
		}
		curr.setConnectedRooms(connectedRooms);
		// get and set all roles
		List<Role> roles = new ArrayList<Role>();
		NodeList parts = room.getElementsByTagName("part");
		int numOfParts = parts.getLength();
		int rank;
		for (int j = 0; j < numOfParts; j++) {
		    Role newRole = new Role();
		    Node partNode = parts.item(j);
		    if (partNode.getNodeType() == Node.ELEMENT_NODE){
			Element pElement = (Element) partNode;
			rank = Integer.parseInt(pElement.getAttribute("level"));
			
			newRole.setName(pElement.getAttribute("name"));
			newRole.setRank(rank);
			newRole.setLine(pElement.getElementsByTagName("line").item(0).getTextContent());
		    }
		    roles.add(newRole);
		}
		curr.setRoles(roles);
	    }
	    rooms.put(name,curr);
	}
	// read and get the casting office
	Room castingOffice = new Room();
	Node castingNode = doc.getElementsByTagName("office").item(0);
	if (castingNode.getNodeType() == Node.ELEMENT_NODE) {
	    Element castingElement = (Element) castingNode;
	    castingOffice.setName("office");
	    List<String> connRooms = new ArrayList<String>();
	    NodeList neighbors = ((Element) castingElement.getElementsByTagName("neighbors").item(0)).getElementsByTagName("neighbor");
	    length = neighbors.getLength();
	    for (int i = 0; i < length; i++){
		Node neighborNode = neighbors.item(i);
		if (neighborNode.getNodeType() == Node.ELEMENT_NODE){
		    Element nElement = (Element) neighborNode;
		    connRooms.add(nElement.getAttribute("name"));
		}
	    }
	    castingOffice.setConnectedRooms(connRooms);
	}
	rooms.put("office",castingOffice);
	//read and get the trailer
	Room trailer = new Room();
	Node trailNode = doc.getElementsByTagName("trailer").item(0);
	if (trailNode.getNodeType() == Node.ELEMENT_NODE) {
	    Element trailElement = (Element) trailNode;
	    trailer.setName("trailer");
	    List<String> connRooms = new ArrayList<String>();
	    NodeList neighbors = ((Element) trailElement.getElementsByTagName("neighbors").item(0)).getElementsByTagName("neighbor");
	    length = neighbors.getLength();
	    for (int i = 0; i < length; i++) {
		Node neighborNode = neighbors.item(i);
		if (neighborNode.getNodeType() == Node.ELEMENT_NODE) {
		    Element nElement = (Element) neighborNode;
		    connRooms.add(nElement.getAttribute("name"));
		}
	    }
	    trailer.setConnectedRooms(connRooms);
	}
	rooms.put("trailer",trailer);
	return rooms;
    } // end function

}
