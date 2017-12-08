import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

public class Player {
	private int rank;
	private int playerNum;
	private int money;
	private int credits;
	private int rehearsalNum;
	private int total;
	private Room currentRoom;
	private Role currentRole;
	private boolean canMove;
	private String diceColor;
	private ImageIcon icon;
	private Integer[] playerXY;

	/*
	 * Constructors
	 */
	public Player(int rank, int playerNumber, int money, int credits, Room currentRoom) {
		this.rank = rank;
		this.playerNum = playerNumber;
		this.money = money;
		this.credits = credits;
		this.rehearsalNum = 0;
		this.total = 0;
		this.canMove = true;
		this.currentRoom = currentRoom;
		this.currentRole = null;
		this.diceColor = new Utility().getColors().get(playerNumber);
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getPlayerNum() {
		return playerNum;
	}

	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public int getRehearsalNum() {
		return rehearsalNum;
	}

	public void setRehearsalNum(int rehearsalNum) {
		this.rehearsalNum = rehearsalNum;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}

	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}

	public Role getCurrentRole() {
		return currentRole;
	}

	public void setCurrentRole(Role currentRole) {
		this.currentRole = currentRole;
	}

	public boolean isCanMove() {
		return canMove;
	}

	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}

	public String getDiceColor() {
		return diceColor;
	}

	public void setDiceColor(String diceColor) {
		this.diceColor = diceColor;
	}

	public ImageIcon getIcon() {
		return icon;
	}

	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}

	public Integer[] getPlayerXY() {
		return playerXY;
	}

	public void setPlayerXY(Integer[] playerXY) {
		this.playerXY = playerXY;
	}

	/*
	 ** Upgrades rank of the player params: rank : int currency : String
	 */
	private void upgradeRank(int rank, String currency) {
		this.rank = rank;
		currency = currency.toUpperCase();
		if (currentRoom.getName().toLowerCase().equals(Constants.CASTING_OFFICE)) {
			int moneyCost = 0;
			int creditCost = 0;
			switch (rank) {
			case 2:
				creditCost = 5;
				moneyCost = 5;
				break;
			case 3:
				creditCost = 10;
				moneyCost = 10;
				break;
			case 4:
				creditCost = 15;
				moneyCost = 18;
				break;
			case 5:
				creditCost = 20;
				moneyCost = 28;
				break;
			case 6:
				creditCost = 25;
				moneyCost = 40;
				break;
			}
			if (currency.equals("$") && (this.money >= moneyCost)) {
				this.money -= moneyCost;
			} else if (currency.equals("CR") && (this.credits >= creditCost)) {
				this.credits -= creditCost;
			} else {
				View.updateConsole("You do not have enough credits or money.");
			}
		} else {
			View.updateConsole("You are not in the casting office. Move to the casting office first.");
		}
	}

	/*
	 * Move cycles through connected nodes and sets appropriate data if it finds the
	 * correct room then returns true. It returns false if the room isn't connected
	 */
	private boolean move(String roomName) {
		if (canMove == false) {
			View.updateConsole("You are unable to move at this time");
			return false;
		} else {
			List<String> connectedRooms = currentRoom.getConnectedRooms();
			for (String connectedRoomName : connectedRooms) {
				if (connectedRoomName.toLowerCase().equals(roomName)) {
					Room connectedRoom = Board.getRoomNode(connectedRoomName);
					currentRoom.removePlayer(playerNum);
					this.currentRoom = connectedRoom;
					currentRoom.addPlayer(playerNum);
					View.updateConsole("Successful move.");
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * Work Takes in roleName and checked it vs the roles in the room that the
	 * player is in If found and is empty it assigns the role to the player and
	 * returns true If fails, return false
	 */
	private boolean work(String roleName) {
		List<Role> roomRoles = this.currentRoom.getRoles();
		if (currentRoom.getScene() == null) {
			return false;
		}
		for (Role element : roomRoles) {
			if ((element.getName().toLowerCase()).equals(roleName.toLowerCase()) && (element.getRank() <= this.rank)) {
				if (element.heldBy != null || this.currentRole != null) {
					View.updateConsole("You failed taking the role " + roleName);
					return false;
				} else {
					element.setHeldBy(this);
					this.setCurrentRole(element);
					element.setWorkable(false);
					View.updateConsole("You have taken the role " + roleName);
					return true;
				}
			}
		}
		View.updateConsole("Unable to take role");
		return false;
	}

	/*
	 * Rehearse(int budget) Takes in budget number and increments player's
	 * rehearsalNum if doing so would not put them over 100% of success at working
	 * Returns true is rehearsal was successfully done, false otherwise
	 */
	private boolean rehearse() {
		if (currentRole != null) {
			int budget = this.currentRoom.getScene().getBudget();
			if (!currentRole.getWorkable()) {
				View.updateConsole("Unable to rehearse.");
				return false;
			}

			int currentRehearsalNum;
			currentRehearsalNum = this.getRehearsalNum();
			// Lowest roll is 1 + rehearsalNum
			currentRehearsalNum++;

			if (currentRehearsalNum == budget) {
				// Working roll is already 100% of success
				View.updateConsole("Rehearsal failed, already at 100% success rate.");
				return false;
			} else if (currentRehearsalNum < budget) {
				// Successful rehearsal
				this.setRehearsalNum(currentRehearsalNum);
				View.updateConsole("Budget for role: " + budget + "\n" + "Rehearsal count increased by 1" + "\n"
						+ "Total Number of Rehearsals: " + currentRehearsalNum);
				currentRole.setWorkable(false);
				return true;
			} else {
				// Error budget is less then rehearsal
				return false;
			}
		}
		View.updateConsole("Rehearsal Failed");
		return false;
	}

	/*
	 * Act Takes in room, scene, and role that player is working in returns true
	 * acting was attempted, false otherwise
	 */
	private boolean act(Room currentRoom, Scene currentScene, Role currentRole) {
		if (currentRole == null) {
			View.updateConsole("The player does not have a role");
			
			return false;
		} else {
			if (!currentRole.getWorkable()) {
				View.updateConsole("Unable to act");
				return false;
			}
			int diceRoll, currentShots, budget, money, credits = 0;
			boolean onCard = false;

			budget = currentScene.getBudget();
			onCard = currentRole.getOnCard();
			currentRole.setWorkable(false);
			// Roll dice
			diceRoll = new Random().nextInt(6);
			// Increment because nextInt returns from range starting at 0
			diceRoll++;
			diceRoll = diceRoll + this.getRehearsalNum();

			if (diceRoll >= budget) {
				// Success, update shots
				currentShots = currentRoom.getCurrentShots();
				currentShots--;
				Game.removeShot(currentRoom);
				currentRoom.setCurrentShots(currentShots);
				
				View.updateConsole("Shots for scene to close: " + currentRoom.getMaxShots());
				View.updateConsole("Current shots: " + currentShots);

				if (onCard == true) {
					credits = this.getCredits();
					credits = credits + 2;
					this.setCredits(credits);
					View.updateConsole("You have succeded in the role. You've gained two credits!");
				} else {
					credits = this.getCredits();
					credits++;
					money = this.getMoney();
					money++;
					this.setCredits(credits);
					this.setMoney(money);
					View.updateConsole("You have succeded in the role. You've gained one credit and one dollar!");
				}

				// Check if scene is finished
				if (currentShots == 0) {
					currentRoom.closeScene();
					Game.roomsRemaining--;
				}
				return true;
			} else {
				// Failure
				if (onCard == false) {
					money = this.getMoney();
					money++;
					this.setMoney(money);
					View.updateConsole("You failed acting!");
				}
				return true;
			}

		}

	}

	/**
	 * handleAction Handles action of the player depending on name of the action.
	 * params: action : String parameters: String[]
	 */
	public Player handleAction(String action, String[] parameters) {
		String consoleOutput = null;
		if (Constants.MOVE.equals(action)) {
			if (parameters.length == 0) {
				//WORKNOTE: Probably dont need this anymore
				//System.out.println("Please also input the room you would like to move to.");
			} else {
				String direction = "";
				for (String par : parameters) {
					direction += " " + par;
				}
				direction = direction.trim();
				//WORKNOTE: Can probably delete
				//System.out.println(direction);
				/*
				 * String direction = parameters[1]; if(parameters.length > 2){ direction =
				 * direction + " " + parameters[2]; }
				 */
				// direction.replace(direction.substring(direction.length()-1), "");

				if ((!this.move(direction)) && canMove) {
					View.updateConsole("Please select a valid room to move to.");
				} else {
					canMove = false;
				}
			}
		} else if (Constants.WORK.equals(action)) {
			if (parameters.length < 1) {
				//WORKNOTE: probably dont need this anymore
				//System.out.println("Please, specify the role");
			} else {
				String roleName = "";
				for (String par : parameters) {
					roleName += " " + par;
				}
				roleName = roleName.trim();

				if (this.work(roleName) == false) {
					View.updateConsole("Unable to work role");
				}
			}

		} else if (Constants.UPGRADE.equals(action)) {
			if (parameters.length == 2) {
				int rank = Integer.parseInt(parameters[1]);
				String currency = parameters[0];
				this.upgradeRank(rank, currency);
			}
		} else if (Constants.REHEARSE.equals(action)) {
			this.rehearse();
		} else if (Constants.ACT.equals(action)) {
			this.act(currentRoom, currentRoom.getScene(), currentRole);
		} else if (Constants.WHO.equals(action)) {
			consoleOutput = "Current player is player #" + (this.playerNum + 1) + "\n"
					+ "Current player has dice color: " + (this.diceColor) + "\n" + "Current number of rehearsals: "
					+ (this.rehearsalNum) + "\n";
			
			if (this.currentRole != null) {
				consoleOutput = consoleOutput + "Current player is working this role: " + (this.currentRole.getName() + "\n");
			} else {
				consoleOutput = consoleOutput + "Player does not have a role yet" + "\n";
			}
			View.updateConsole(consoleOutput);
		} else if (Constants.WHERE.equals(action)) {
			consoleOutput = "Current player is in the room: " + this.currentRoom.getName() + "\n";
			String adjacentRooms = "";
			int i = 0;
			for (String rName : currentRoom.getConnectedRooms()) {
				if (i > 0) {
					adjacentRooms += ", ";
				}
				adjacentRooms += rName;
				i++;
			}
			consoleOutput = consoleOutput + "Adjacent rooms are " + adjacentRooms + "\n\n";

			if (currentRoom.getScene() != null) {
				consoleOutput = consoleOutput + "The Scene for this room is " + currentRoom.getScene().getName()
						+ ". It has a budget of " + currentRoom.getScene().getBudget() + "." + "\n";
				String rolesOnCard = "";
				String roles = " ";
				List<Role> allRoles = currentRoom.getRoles();
				for (Role role : allRoles) {
					if (role.onCard) {
						System.out.println(role.getName());
						rolesOnCard += role.getName() + " [rank:" + role.getRank() + "], ";
					}
					roles += role.getName() + " [rank:" + role.getRank() + "], ";
				}
				consoleOutput = consoleOutput + "All roles:" + roles + "\n";
				if (!rolesOnCard.equals("")) {
					consoleOutput = consoleOutput + "On card roles: " + rolesOnCard;
				}
			}
			View.updateConsole(consoleOutput);
		} else {
			System.out.println("Command not found please reinput");
		}
		return this;
	}

}
