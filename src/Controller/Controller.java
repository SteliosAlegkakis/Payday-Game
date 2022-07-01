package Controller;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import Model.DealCard;
import Model.Game;
import Model.MailCard;
import Model.Player;
import View.View;

public class Controller implements ActionListener{
	Game game;
	View view;
	
	/**
	 * <b>Constructor</b>Creates new Controller,initialises private fields
	 * calls some functions from view and game to initialise graphics and set Action Listeners
	 */
	public Controller() {
		String[] name = View.namesPanel();
		game = new Game(View.monthPanel(),name[0],name[1]);
		view = new View(name[0],name[1]);
		String[] icons = new String[32];
		for(int i=0;i<32;i++) {
			icons[i]=game.getPosition(i).getURL();
		}
		view.setBoardIcons(icons);
		view.initGraphics();
		view.setActionListeners(this);
		updateView();
	}
	
	/**
	 * Calls a function depending on which button in the View is pressed 
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(view.getP1().getDice())) dicePressed(game.getP1());
		if(e.getSource().equals(view.getP2().getDice())) dicePressed(game.getP2());
		if(e.getSource().equals(view.getP1().getEndTurn())) endTurnPressed(game.getP1());
		if(e.getSource().equals(view.getP2().getEndTurn())) endTurnPressed(game.getP2());
		if(e.getSource().equals(view.getMail())) mailPressed();
		if(e.getSource().equals(view.getDeal())) dealPressed();
		if(e.getSource().equals(view.getP1().getLoanButton())) loanPressed(game.getP1());
		if(e.getSource().equals(view.getP2().getLoanButton())) loanPressed(game.getP2());
		if(e.getSource().equals(view.getP1().getDealCards())) showCardsPressed(game.getP1());
		if(e.getSource().equals(view.getP2().getDealCards())) showCardsPressed(game.getP2());
	}
	
	/**
	 * Updates the view 
	 */
	public void updateView() {
		String infoBox="<html>Turn: "+game.getTurn().getPlayer().getName()+
				"<br>Months left: ";
		if(game.getP1().getMonth()<game.getP2().getMonth()) 
			infoBox=infoBox+game.getP2().getMonth();
		else
			infoBox=infoBox+game.getP1().getMonth();
		if(game.getTurn().getDiceRolled())
			infoBox=infoBox+"<br>"+game.getTurn().getPlayer().getBoardPosition().getAction();
		else
			infoBox=infoBox+"<br>roll the dice";
		view.getInfo().setText(infoBox);
		view.getP1().getMoney().setText("Money: "+game.getP1().getMoney()+" Euro");
		view.getP2().getMoney().setText("Money: "+game.getP2().getMoney()+" Euro");
		view.getP1().getBill().setText("Bills: "+game.getP1().getBill()+" Euro");
		view.getP2().getBill().setText("Bills: "+game.getP2().getBill()+" Euro");
		view.getP1().getLoan().setText("Loan: "+game.getP1().getLoan()+" Euro");
		view.getP2().getLoan().setText("Loan "+game.getP2().getLoan()+" Euro");
		view.getJackpot().setText("Jackpot: "+game.getjackpot()+" Euro");
		view.move(1,game.getP1().getPosition());
		view.move(2,game.getP2().getPosition());
		if(game.getTurn().getPlayer().getBoardPosition().getAction().equals("start")) return;
		if(!game.getP1().getBoardPosition().getAction().equals("start")) 
			view.getP1().updateDice(game.getP1().getDice());
		if(!game.getP2().getBoardPosition().getAction().equals("start"))
			view.getP2().updateDice(game.getP2().getDice());
	}
	
	/**
	 * Calls some functions from model to perform some actions when deal button is pressed
	 */
	public void dealPressed() {
		if(game.getTurn().getPlayer().getBoardPosition().getAction().equals("get 1 deal card")&&!game.getTurn().getActionFinished()&&game.getTurn().getDiceRolled()) {
			DealCard c = game.getDeal();
			int choice = view.showDealCard(c.getTypeEn(),c.getMessage(),c.getChoice1(),c.getChoice2(),c.getValue(),c.getCost(),c.getIcon());
			game.dealCardAction(choice, c);
			updateView();
		}
	}
	
	/**
	 * Calls some functions from model to perform some actions when mail button is pressed
	 */
	public void mailPressed() { 
		if(game.isFinished()) return;
		if((game.getTurn().getPlayer().getBoardPosition().getAction().equals("get 1 mail card")||game.getTurn().getPlayer().getBoardPosition().getAction().equals("get 2 mail cards"))&&!game.getTurn().getActionFinished()&&game.getTurn().getDiceRolled()) {
			MailCard c = game.getMail();
			view.showMailCard(c.getTypeEn(),c.getMessage(),c.getChoice(),c.getIcon());
		}
		updateView();
	}
	
	/**
	 * Calls some functions from model to perform some actions when dice button is pressed
	 * @param p the player who pressed dice
	 */
	public void dicePressed(Player p) {
		if(game.isFinished()) return;
		if(game.getTurn().getPlayer().getBoardPosition().getAction().equals("yard sale")&&!game.getTurn().getActionFinished()&&game.getTurn().getPlayer().equals(p)&&game.getTurn().getDiceRolled()) {
			DealCard c = game.yardSale();
			updateView();
			view.showDealCard(c.getTypeEn(), c.getMessage(), "free", "free", c.getValue(), c.getCost(), c.getIcon());
		}	
		game.rollDice(p);
		updateView();
	}
	
	/**
	 * Calls some functions from model to perform some actions when loan Button is pressed
	 * @param p the player who pressed loan
	 */
	public void loanPressed(Player p) {
		if(game.isFinished()) return;
		game.loan(p);
		updateView();
	}
	
	/**
	 * Calls some functions from model to perform some actions when showCards button is pressed 
	 * @param p the player who pressed show cards
	 */
	public void showCardsPressed(Player p) {
		if(!game.getTurn().getPlayer().equals(p)||!p.getBoardPosition().getAction().equals("Sell a card")||game.getTurn().getActionFinished())
			return;
		ArrayList<DealCard> c = p.getCards();
		for(int i=0;i<c.size();i++) {
			DealCard card = c.get(i);
			int s = view.showDealCard(card.getTypeEn(),card.getMessage(), "Sell:"+card.getValue()+" Euro","next",card.getValue(),card.getCost(),card.getIcon());
			if(s==1) {
				game.buyer(card,p);
				break;
			}
		}
		updateView();
	}
	
	/**
	 * Calls some functions from model to perform some actions when endTurn button is pressed
	 * @param p the player who pressed end turn
	 */
	public void endTurnPressed(Player p) {
		game.endTurn(p);
		updateView();
		if(game.isFinished())
			view.getInfo().setText("<html><br>Game has finished!<br>Winner: "+game.getWinner().getName());
	}
	
}
