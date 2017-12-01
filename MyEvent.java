import java.util.ArrayList;
import java.util.List;

public class MyEvent {

	private String actionName;
	private List<String> parameters = new ArrayList<String>();
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public List<String> getParameters() {
		return parameters;
	}
	public void setParameters(List<String> parameters) {
		this.parameters = parameters;
	}
	
	
}
