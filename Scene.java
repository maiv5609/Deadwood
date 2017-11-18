import java.util.List;

public class Scene {

    String name;
    String description;
    int budget;
    List<Role> role;
    Boolean isClosed;
    Boolean isOccupied;

    public Scene(){
	name = null;
	description = null;
	budget = 0;
	role = null;
	isClosed = false;
	isOccupied = false;
    }
    
    public String getName() {
	return name;
    }
    
    public void setName(String name) {
	this.name = name;
    }
    
    public int getBudget() {
	return budget;
    }
    
    public void setBudget(int budget) {
	this.budget = budget;
    }
    
    public List<Role> getRole() {
	return role;
    }
    
    public void setRole(List<Role> role) {
	this.role = role;
    }
    
    public Boolean getIsClosed() {
	return isClosed;
    }
    
    public void setIsClosed(Boolean isClosed) {
	this.isClosed = isClosed;
    }
    
    public Boolean getIsOccupied() {
	return isOccupied;
    }
    
    public void setIsOccupied(Boolean isOccupied) {
	this.isOccupied = isOccupied;
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}    
}
