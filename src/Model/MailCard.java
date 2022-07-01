package Model;

public class MailCard extends Card{
	private String choice;
	private int euro;
	
	/**
	 * <b>Constructor</b>Constructs a new MailCard with the given values
	 * @param type
	 * @param typeen
	 * @param message
	 * @param choice
	 * @param euro
	 * @param icon
	 */
	public MailCard(String type, String typeen, 
			String message,String choice,int euro, String icon) {
		super(type, typeen, message, icon);
		this.choice=choice;
		this.euro=euro;
	}

	//Setters
	public void setChoice(String choice) {this.choice=choice;}
	public void setEuro(int euro) {this.euro=euro;}
	
	//Getters
	public String getChoice() {return this.choice;}
	public int getEuro() {return this.euro;}
}
