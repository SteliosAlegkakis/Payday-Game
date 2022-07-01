package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Game {
	private int months,jackpot,mail2;
	private boolean dice1rolled,dice2rolled;
	private Player p1,p2;
	private ArrayList<Position> board;
	private ArrayList<MailCard> mailCards;
	private ArrayList<DealCard> dealCards;
	private Turn turn;
	
	/**
	 * <b>Constructor</b>:constructs a new game with the given parameters<br>
	 * <b>Postcondition</b>:Creates and initialises a game with the given names for
	 * players and given months
	 * @param m is the months of the game
	 * @param name1 is the name for first player
	 * @param name2 is the name for second player
	 */
	public Game(int m,String name1,String name2) {
		months=m;
		jackpot=0;
		mail2=0;
		p1=new Player(name1,months);
		p2=new Player(name2,months);
		mailCards=new ArrayList<MailCard>();
		dealCards=new ArrayList<DealCard>();
		board=new ArrayList<Position>();
		Player[] p= {p1,p2};//To start the game choosing randomly the first player 
		turn=new Turn(p[(int) ((Math.random() * (2 - 0)) + 0)]);
		initCards();
		initBoard();
	}
	
	/**
	 * <b>Transformer</b>:reads some values from resources,initialises mailCards and dealCards
	 * and shuffles them so that the cards are in random positions in each array list<br>
	 * <b>Postcondition</b>:mailCards and dealCards are initialised
	 */
	private void initCards() {
		CardsReader c = new CardsReader();
		for(int i=0;i<48;i++) this.mailCards.add(c.getMailCard(i));
		for(int i=0;i<20;i++) this.dealCards.add(c.getDealCard(i));
		Collections.shuffle(mailCards, new Random());
		Collections.shuffle(dealCards,new Random());
	}
	
	/**
	 * <b>Transformer</b>:initialises board with new Positions,then calls shuffle so the Positions 
	 * are in random positions in the array list and sets each Players boardPosition
	 * in the first of the array list which is always the start<br>
	 * <b>PostCondition</b>:board is initialised
	 */
	private void initBoard() {
		for(int i=0;i<4;i++) board.add(new Position("get 1 mail card","resources/images/mc1.png"));
		for(int i=0;i<4;i++) board.add(new Position("get 2 mail cards","resources/images/mc2.png"));
		for(int i=0;i<5;i++) board.add(new Position("get 1 deal card","resources/images/deal.png"));
		for(int i=0;i<2;i++) board.add(new Position("win 1000 x roll of the dice","resources/images/sweep.png"));
		for(int i=0;i<3;i++) board.add(new Position("unavailable function","resources/images/lottery.png"));
		for(int i=0;i<2;i++) board.add(new Position("highest roll wins 1000$","resources/images/radio.png"));
		for(int i=0;i<5;i++) board.add(new Position("Sell a card","resources/images/buyer.png"));
		for(int i=0;i<2;i++) board.add(new Position("casino","resources/images/casino.png"));
		for(int i=0;i<2;i++) board.add(new Position("yard sale","resources/images/yard.png"));
		Collections.shuffle(board, new Random());
		board.add(0,new Position("start","resources/images/start.png"));
		board.add(30,new Position("Sell a card","resources/images/buyer.png"));
		board.add(31,new Position("payday","resources/images/pay.png"));
		p1.setBoardPosition(board.get(0));
		p2.setBoardPosition(board.get(0));
	}
	
	/**
	 * <b>Transformer</b>:Takes the card on the top of mailCards array list,performs some actions 
	 * depending on the positions of the player who has turn,calls doMailCardAction(card),puts the
	 * card at the bottom of mailCards removes it from the top and returns the card
	 * @return the card at the top of mailCards array list
	 */
	public MailCard getMail() {
		MailCard card=mailCards.get(mailCards.size()-1);
		if(turn.getPlayer().getBoardPosition().getAction().equals("get 1 mail card")){
			turn.setActionFinished(true);
			doMailCardAction(card);
			mailCards.remove(mailCards.size()-1);
			addMail(card);
			return card;
		}
		if(turn.getPlayer().getBoardPosition().getAction().equals("get 2 mail cards")){
			mail2++;
			mailCards.remove(mailCards.size()-1);
			addMail(card);
			//if first card is MoveToDealBuyer
			if(card.getTypeEn().equals("MoveToDealBuyer")&&mail2==1) {
				return card;
			}
			//if both cards are MoveToDealBuyer
			if(mail2==2&&mailCards.get(1).getTypeEn().equals("MoveToDealBuyer")&&card.getTypeEn().equals("MoveToDealBuyer")) {
				mail2=0;
				turn.setActionFinished(true);
				doMailCardAction(card);
				return card;
			}
			//if first card is MoveToDealBuyer and second isn't
			if(mail2==2&&mailCards.get(1).getTypeEn().equals("MoveToDealBuyer")&&!card.getTypeEn().equals("MoveToDealBuyer")) {
				turn.setActionFinished(true);
				doMailCardAction(card);
				doMailCardAction(mailCards.get(1));
				mail2=0;
				return card;
			}
			//if second card is MoveToDealBuyer 
			if(mail2==2&&card.getTypeEn().equals("MoveToDealBuyer")&&!mailCards.get(1).getTypeEn().equals("MoveToDealBuyer")) {
				turn.setActionFinished(true);
				doMailCardAction(card);
				mail2=0;
				return card;
			}	
			//if second card is MoveToDealBuyer
			if(mail2==2&&mailCards.get(1).getTypeEn().equals("MoveToDealBuyer")) {
				turn.setActionFinished(true);
				doMailCardAction(mailCards.get(1));
				mail2=0;
				return card;
			}
			if(mail2==2) {
				turn.setActionFinished(true);
				mail2=0;
			}
			doMailCardAction(card);
			return card;
		}
		return null;
	}
	
	/**
	 * <b>Transformer</b>:Performs some actions depending on the cards TypeEn
	 * ("Advertisement","Bill",...)
	 * @param c the card of which the action is performed
	 */
	public void doMailCardAction(MailCard c) {
		if(c.getTypeEn().equals("Advertisement")) {
			turn.getPlayer().addMoney(c.getEuro());
		}
			
		if(c.getTypeEn().equals("Bill")) {
			turn.getPlayer().addBill(c.getEuro());
		}	
		if(c.getTypeEn().equals("Charity")) {
			addToJackpot(c.getEuro());
			turn.getPlayer().pay(c.getEuro());
		}
		if(c.getTypeEn().equals("PayTheNeighbor")) {
			turn.getPlayer().pay(c.getEuro());
			if(turn.getPlayer().equals(p1)) p2.addMoney(c.getEuro());
			else p1.addMoney(c.getEuro());
		}
		if(c.getTypeEn().equals("MadMoney")) {
			turn.getPlayer().addMoney(c.getEuro());
			if(turn.getPlayer().equals(p1)) p2.pay(c.getEuro());
			else p1.pay(c.getEuro());
		}
		if(c.getTypeEn().equals("MoveToDealBuyer")) {
			do{
				turn.getPlayer().move(1);
				turn.getPlayer().setBoardPosition(board.get(turn.getPlayer().getPosition()));
			}while(!(turn.getPlayer().getBoardPosition().getAction().equals("Sell a card")));
			if(!turn.getPlayer().getCards().isEmpty()) turn.setActionFinished(false);
		}
	}
	
	/**
	 * <b>Transformer</b>:Takes the card at the top of dealCards and moves it to the bottom
	 * ,then returns the card
	 * @return the card at the top of dealCards array list
	 */
	public DealCard getDeal() {
		DealCard c = dealCards.get(dealCards.size()-1);
		dealCards.remove(dealCards.size()-1);
		addDeal(c);
		turn.setActionFinished(true);
		return c;
	}
	
	/**
	 * <b>Transformer</b>:if choice is 1 the player who has turn pays the cost of the 
	 * deal card and the deal card is added to his cards array list
	 * @param choice 
	 * @param c
	 */
	public void dealCardAction(int choice,DealCard c) {
		if(choice==1) {
			turn.getPlayer().pay(c.getCost());
			turn.getPlayer().addCard(c);
		}
	}
	
	/**
	 * <b>Transformer</b>:Rolls the dice of the player 
	 * who has turn and dds 1000*dice to players money 
	 */
	public void sweepstakes() {
		turn.getPlayer().rollDice();
		turn.getPlayer().addMoney(1000*turn.getPlayer().getDice());
		turn.setActionFinished(true);
	}
	
	/**
	 * <b>Transformer</b>Adds or removes money from the player who has turn depending 
	 * on if his dice was odd or even
	 */
	public void casino() {
		if(turn.getPlayer().getDice()%2==0) turn.getPlayer().addMoney(500);
		if(turn.getPlayer().getDice()%2!=0) {
			turn.getPlayer().pay(500);
			addToJackpot(500);
		}
		turn.setActionFinished(true);
	}
	
	/**
	 * <b>Transformer</b>:Adds value of the card to player p money and removes 
	 * the card from player p cards array list
	 * @param card
	 * @param p
	 */
	public void buyer(DealCard card,Player p) {
		if(turn.getActionFinished()==true) return;
		p.addMoney(card.getValue());
		p.removeCard(card);
		turn.setActionFinished(true);
	}
	
	/**
	 * <b>Transformer</b>:Rolls the dice of the player who has turn ,pays 100*dice
	 * from his money and adds the card at the top of dealCards array list to his cards
	 * @return the card that was added to players cards
	 */
	public DealCard yardSale() {
		turn.getPlayer().rollDice();
		turn.getPlayer().pay(100*turn.getPlayer().getDice());
		DealCard c = getDeal();
		turn.getPlayer().addCard(c);
		turn.setActionFinished(true);
		return c;
	}
	
	/**
	 * <b>Transformer</b>:checks if both players have rolled their dice and adds 1000
	 * to the players money who had the highest dice
	 * @param p the player who rolled the dice
	 */
	public void radio(Player p) {
		if(p.equals(p1)&&dice1rolled==false) {
			p1.rollDice();
			dice1rolled=true;
		}
		if(p.equals(p2)&&dice2rolled==false) {
			p2.rollDice();
			dice2rolled=true;
		}
		if(!(dice1rolled==true&&dice2rolled==true)) return;
		if(p1.getDice()>p2.getDice()) {
			p1.addMoney(1000);
			dice1rolled=false;
			dice2rolled=false;
			turn.setActionFinished(true);
		}
		if(p1.getDice()<p2.getDice()) {
			p2.addMoney(1000);
			dice1rolled=false;
			dice2rolled=false;
			turn.setActionFinished(true);
		}
		if(p1.getDice()==p2.getDice()) {
			dice1rolled=false;
			dice2rolled=false;
			turn.setActionFinished(false);
		}
	}
	
	/**
	 * <b>Transformer</b>:Adds 'card' to first position of 'mailCards'
	 * @param card
	 */
	public void addMail(MailCard card) {this.mailCards.add(0, card);}
	
	/**
	 * <b>Transformer</b>:Adds 'card' to first position of 'dealCards'
	 * @param card
	 */
	public void addDeal(DealCard card) {this.dealCards.add(0, card);}
	
	
	/**
	 * <b>Transformer</b>:Adds amount to Game 'jackpot'
	 * @param amount
	 */
	public void addToJackpot(int amount) {this.jackpot+=amount;}
	
	/**
	 * <b>Transformer</b>Returns the value of jackpot and resets it to 0
	 * @return the value of jackpot before it was reseted
	 */
	public int takeJackpot() {
		int tmp=jackpot;
		jackpot=0;
		return tmp;
	}
	
	/**
	 * <b>Transformer</b>:It moves player p if he has turn or if he is moved
	 * already calls a function depending on the position action of the player
	 * who has turn
	 * @param p the player who anted to roll the dice
	 */
	public void rollDice(Player p) {
		if(turn.getPlayer().getBoardPosition().getAction().equals("highest roll wins 1000$")&&!turn.getActionFinished())
			radio(p);
		
		if(!turn.getPlayer().equals(p)) return;
		if(p.isFinished()) return;
		
		if(p.getBoardPosition().getAction().equals("win 1000 x roll of the dice")&&turn.getDiceRolled()&&!turn.getActionFinished())
			sweepstakes();
	
		if(turn.getDiceRolled()==true) return;
		
		//what happens when dice is rolled to move the player who has turn
		p.rollDice();
		p.move(p.getDice());
		turn.setDiceRolled(true);
		if(p.getDice()==6) p.addMoney(takeJackpot());
		p.setBoardPosition(board.get(p.getPosition()));
		
		//some special cases 
		if(p.getBoardPosition().getAction().equals("unavailable function")||p.getBoardPosition().getAction().equals("payday"))
			turn.setActionFinished(true);
		if(p.getBoardPosition().getAction().equals("Sell a card")&&p.getCards().isEmpty())	
			turn.setActionFinished(true);
		if(p.getBoardPosition().getAction().equals("casino")&&!turn.getActionFinished())
			casino();
			
	}
	
	/**
	 * <b>Transformer</b>:adds loan to player p
	 * @param p
	 */
	public void loan(Player p) {
		if(turn.getPlayer().equals(p)) {
			p.addLoan();
		}
	}
	
	/**
	 * <b>Transformer</b>:checks is all the actions in a turn are done and if the right 
	 * player wants to end the turn and then sets the other player to get turn 
	 * @param p the player who wants to end turn
	 */
	public void endTurn(Player p) {
		if(p.equals(turn.getPlayer())&&!turn.getDiceRolled()||!turn.getActionFinished()) return;
		if(!p.equals(turn.getPlayer())) return;
		if(p.equals(p1) && !p2.isFinished() || p.equals(p2) && p1.isFinished()) {
			turn.setPlayer(p2);
			turn.setDiceRolled(false);
			turn.setActionFinished(false);
		}
		if(p.equals(p2) && !p1.isFinished() || p.equals(p1) && p2.isFinished()) {
			turn.setPlayer(p1);
			turn.setDiceRolled(false);
			turn.setActionFinished(false);
		}
	}
	
	/**
	 * <b>Observer</b>:Returns true if both players reached position 31 of the last month
	 * @return
	 */
	public boolean isFinished() {
		if(p1.isFinished()&&p2.isFinished()) return true;
		return false;
	}
	
	//Getters
	/**
	 * <b>Observer</b>:Returns the Position in the given 'i' position of the board
	 * @param i
	 * @return Position i of the board
	 */
	public Position getPosition(int i) {return this.board.get(i);}
	/**
	 * <b>Observer</b>:Returns months of the game
	 * @return months
	 */
	public int getMonths() {return this.months;}
	/**
	 * <b>Observer</b>:Returns jackpot
	 * @return jackpot
	 */
	public int getjackpot() {return this.jackpot;}
	/**
	 * <b>Observer</b>:Returns p1
	 * @return p1
	 */
	public Player getP1() {return this.p1;}
	/**
	 * <b>Observer</b>:Returns p2
	 * @return p2
	 */
	public Player getP2() {return this.p2;}
	/**
	 * <b>Observer</b>:Returns winner
	 * @return winner
	 */
	public Player getWinner() {
		if(p1.score()>p2.score()) return p1;
		return p2;
	}
	/**
	 * <b>Observer</b>:Returns turn
	 * @return turn
	 */
	public Turn getTurn() {return this.turn;}
}
