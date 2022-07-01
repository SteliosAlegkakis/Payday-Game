package Model;

public class Position {
	private String day,action,icon;
	
	/**
	 * <b>Constructor</b>Creates a new Position with 'action' action and 'icon' icon
	 * @param action
	 * @param icon
	 */
	public Position(String action,String icon) {
		this.action=action;
		this.icon=icon;
	}
	
	//Setters
	public void setDay(String day) {this.day=day;}
	public void setAction(String action) {this.action=action;}
	public void seticon(String icon) {this.icon=icon;}
	
	//Getters
	public String getDay() {return this.day;}
	public String getAction() {return this.action;}
	public String getURL() {return this.icon;}
}
