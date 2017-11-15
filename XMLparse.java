import java.util.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class XMLparse {
    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder dBuilder;
    private Document doc;
    //    private Element root;

    XMLparse() {
	dbFactory = DocumentBuilderFactory.newInstance();
	dBuilder = dbFactory.newDocumentBuilder();
    }

    public void load (File XMLfile) {
	doc = dBuilder.parse(XMLfile);
	doc.getDocumentElement().normalize();
    }

    public List<Scene> getScenesAsList() {
	List<Scene> listOfScenes = new ArrayList<Scene>();
	NodeList nList = doc.getElementsByTagName("card");
	int length = nList.getLength();
	for (int i = 0; i < length; i++){
	    Node nNode = nList.item(i);
	    Scene curr = new Scene();
	    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		Element eElement = (Element) nNode;
		// Grab and set non-role data
		curr.setName(nNode.getAttribute("name"));
		curr.setBudget(nNode.getAttribute("budget"));
		curr.setDescription(curr.getElementsByTagName("scene").item(0).getTextContent());
		// Aggregate role data and add it to scene
		List<Role> roles = new ArrayList<Role>();
		NodeList rList = curr.getElementsByTagName("part");
		int numOfRoles = rList.getLength();
		for (int j = 0; j < numOfRoles; j++){
		    Role currRole = new Role();
		    Node rNode = rList.item(j);
		    if (rNode.getNodeType() == Node.ELEMENT_NODE) {
			Element rElement = (Element) rNode;
			// Grabs and sets relevant role data
			currRole.setName(rElement.getAttribute("name"));
			currRole.setRank(rElement.getAttribute("level"));
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


}
