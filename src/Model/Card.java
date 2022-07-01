package Model;

public class Card {
	protected String type;
	protected String typeEn;
	protected String message;
	protected String icon;
	
	/**
	 * <b>Constructor</b>:constructs a new card with the given parameters<br>
	 * @param type the type of the card
	 * @param typeEn the typeEn of the card
	 * @param message the message of the card
	 * @param icon the path of the icon
	 */
	public Card(String type,String typeEn,String message,String icon) {
		this.type=type;
		this.typeEn=typeEn;
		this.message=message;
		this.icon=icon;
	}
	
	
	//Setters
	public void setType(String type) {this.type=type;}
	public void setTypeEn(String typeEn) {this.typeEn=typeEn;}
	public void setMessage(String message) {this.message=message;}
	public void setIcon(String icon) {this.icon=icon;}
	
	//Getters
	public String getType() {return this.type;}
	public String getTypeEn() {return this.typeEn;}
	public String getMessage() {return this.message;}
	public String getIcon() {return this.icon;}
	
}
