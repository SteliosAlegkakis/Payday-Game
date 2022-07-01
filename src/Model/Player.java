package Model;

import java.util.ArrayList;

public class Player {
	private int money,bill,loan,month,position,dice;
	private Position boardPosition;
	private String name;
	private ArrayList<DealCard> cards;
	
	/**
	 * <b>Constructor</b>:Constructs a new Player with 'name' name,'money' 3500,'bills' 0,
	 * 'loan' 0,'position' 0
	 * @param name the name of the player
	 * @param month the amount of months that the player will play
	 */
	public Player(String name,int month) {
		this.name=name;
		this.money=3500;
		this.position=0;
		this.month=month;
		this.bill=0;
		this.loan=0;
		boardPosition = new Position("start","recourses/images/start.png");
		dice=(int)(Math.random() * (7 - 1)) + 1;
		cards=new ArrayList<DealCard>();
	}
	
	/**
	 * <b>Transformer</b>:Adds money to Player 'money'<br>
	 * <b>PostCondittion</b>:money is added to players money
	 * @param money
	 */
	public void addMoney(int money) {this.money += money;}
	
	/**
	 * <b>Transformer</b>:Sets dice to a random number from 1 to 6
	 * <b>PostCondition</b>:Dice is changed to a random number from 1 to 6
	 */
	public void rollDice() {dice=(int) ((Math.random() * (7 - 1)) + 1);}
	
	/**
	 * <b>Transformer</b>:Adds loan to Player 'loan' <br>
	 * <b>PostCondition</b>:1000 has been added to players loan and money
	 */
	public void addLoan() {
		loan += 1000;
		money+=1000;
	}
	
	/**
	 * <b>Transformer</b>:Adds bill to Player bill<br>
	 * <b>PostCondition</b>:bill has been added to players bill
	 * @param bill
	 */
	public void addBill(int bill) {this.bill += bill;}
	
	/**
	 * <b>Transformer</b>:Pays an amount of money for player<br>
	 * <b>PostCondirion</b>:if a is greater than players money player gets loan
	 * and then pays else a is removed from players money
	 * @param a the amount of money the player has to pay
	 */
	public void pay(int a) {
		if(money>=a) {
			money = money - a;
			return;
		}
		while(a>money) addLoan();
		money = money -a;
	}
	
	/**
	 * <b>Transformer</b>:Adds positions to Player 'position'<br>
	 * <b>PostCondition</b>:positions is added to player position,players 
	 * position will be a number from 1 to 31 
	 * @param positions the positions that the player has to move
	 */
	public void move(int positions) {
		if(position==31) {
			position=positions;
			return;
		}
		if(position<31) position=position+positions;
		if(position>31) position=31;
		if(position==31) {
			addMoney(3500);
			pay(bill);
			bill=0;
			pay(-(int)(loan-loan*0.1));
			loan=(int) (loan-loan*0.1);
			month--;
		}
	}
	
	/**
	 * <b>Transformer</b>:Adds card to Player cards ArrayList<br>
	 * <b>PostCondition</b>:Card is added to players cards
	 * @param card the card that is added
	 */
	public void addCard(DealCard card) {this.cards.add(card);}
	
	/**
	 * <b>Transformer</b>:Removes card from players cards<br>
	 * <b>PostCondition</b>:Card is removed from players cards array list
	 * @param card
	 */
	public void removeCard(DealCard card) {cards.remove(card);}
	
	/**
	 * <b>Observer</b>:Gets the score of the player
	 * @return players score
	 */
	public int score() {return money-loan-bill;}
	
	/**
	 * <b>Observer</b>:Returns true if Player has reached the end of the game 
	 * and false if not
	 * @return players finished state 
	 */
	public boolean isFinished() {
		if(month==0) return true;
		return false;
	}
	
	/**
	 * <b>Transformer</b>:Sets p as the players boardPosition<br>
	 * <b>PostCondition</b>:p is the players boardPosition
	 * @param p
	 */
	public void setBoardPosition(Position p) {this.boardPosition=p;}
	
	//Getters
	public int getDice() {return this.dice;}
	public String getName() {return this.name;}
	public int getMoney() {return this.money;}
	public int getBill() {return this.bill;}
	public int getLoan() {return this.loan;}
	public int getMonth() {return this.month;}
	public int getPosition() {return this.position;}
	public Position getBoardPosition() {return this.boardPosition;}
	public ArrayList<DealCard> getCards() {return this.cards;}
}
