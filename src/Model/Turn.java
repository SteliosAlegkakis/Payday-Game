package Model;

public class Turn {
	private Player p;
	private boolean diceRolled,actionFinished;
	
	/**
	 * <b>Constructor</b>:Constructs a new Turn with given Player p 
	 * and initialises private fields
	 * @param p
	 */
	public Turn(Player p) {
		this.p=p;
		diceRolled=false;
		actionFinished=false;
	}
	
	//Setters
	public void setDiceRolled(boolean b) {this.diceRolled=b;}
	public void setActionFinished(boolean b) {this.actionFinished=b;}
	public void setPlayer(Player p) {this.p=p;}
	
	//Getters
	public boolean getDiceRolled() {return this.diceRolled;}
	public boolean getActionFinished() {return this.actionFinished;}
	public Player getPlayer() {return this.p;}
}
