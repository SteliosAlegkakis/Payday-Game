package Model;

public class DealCard extends Card{
	private int cost,value;
	private String choice1,choice2;
	
	/**
	 * <b>Constructor</b>:Creates a new DealCard with the given values
	 * @param type
	 * @param typeen
	 * @param message
	 * @param cost
	 * @param value
	 * @param icon
	 * @param choice1
	 * @param choice2
	 */
	public DealCard(String type,String typeen,String message,int cost,
			int value,String choice1,String choice2,String icon) {
		super(type, typeen, message, icon);
		this.cost=cost;
		this.value=value;
		this.choice1=choice1;
		this.choice2=choice2;
	}
	
	//Setters
	public void setCost(int cost) {this.cost=cost;}
	public void setValue(int value) {this.value=value;}
	public void setChoice1(String choice1) {this.choice1=choice1;}
	public void setChoice2(String choice2) {this.choice2=choice2;}
	
	//Getters
	public int getCost() {return this.cost;}
	public int getValue() {return this.value;}
	public String getChoice1() {return this.choice1;}
	public String getChoice2() {return this.choice2;}
}
