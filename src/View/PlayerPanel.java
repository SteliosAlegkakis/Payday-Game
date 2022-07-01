package View;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.text.AttributeSet.ColorAttribute;

import Controller.Controller;

public class PlayerPanel extends JPanel{
	private JButton dice,loanButton,showCards,endTurn;
	private JLabel money,loan,bill,name;
	private String diceIcons[]= {
			"resources/images/dice-1.jpg",
			"resources/images/dice-2.jpg",
			"resources/images/dice-3.jpg",
			"resources/images/dice-4.jpg",
			"resources/images/dice-5.jpg",
			"resources/images/dice-6.jpg"
	};
	
	/**
	 * <b>Constructor</b>Initialises private fields and calls setActionListeners()
	 * @param name
	 */
	public PlayerPanel(String name,int y) {
		dice = new JButton();
		loanButton=new JButton();
		showCards=new JButton();
		endTurn=new JButton();
		money=new JLabel("Money: 3500 $");
		loan=new JLabel("Loan: 0 $");
		bill=new JLabel("Bills: 0 $");
		this.name=new JLabel(name);
		setBounds(720,y,250,200);
		setVisible(true);
		initGraphics();
	}
	
	/**
	 * Sets controller c as the action listener for all of the panels buttons
	 * @param c
	 */
	public void setActionListeners(Controller c) {
		loanButton.addActionListener(c);
		showCards.addActionListener(c);
		dice.addActionListener(c);
		endTurn.addActionListener(c);
		showCards.addActionListener(c);
	}
	
	/**
	 * <b>Transformer</b>:Initialises all the graphics and adds all the components
	 * to the pane
	 */
	public void initGraphics() {
		JLayeredPane pane = new JLayeredPane();
		pane.setPreferredSize(new Dimension(250,200));
		Image d = new ImageIcon(diceIcons[(int)(Math.random() * (6 - 1)) + 1]).getImage();
		d=d.getScaledInstance(100,100,Image.SCALE_SMOOTH);
		dice.setBounds(140,90,100,100);
		dice.setIcon(new ImageIcon(d));
		endTurn.setBounds(10,125,100,25);
		endTurn.setText("End Turn");
		showCards.setBounds(10,90,100,25);
		showCards.setText("Show Cards");
		loanButton.setBounds(10,160,100,25);
		loanButton.setText("Get Loan");
		name.setBounds(0,0,250,15);
		money.setBounds(0,30,250,15);
		loan.setBounds(0,45,250,15);
		bill.setBounds(0,60,250,15);
		pane.add(showCards);
		pane.add(endTurn);
		pane.add(loanButton);
		pane.add(dice);
		pane.add(name);
		pane.add(money);
		pane.add(loan);
		pane.add(bill);
		add(pane);
	}
	
	/**
	 * <b>Transformer</b>:updates the imageIcon of the dice to diceIcos[i-1]
	 * @param i the number that will be displayed on the dice
	 */
	public void updateDice(int i) {
		i--;
		Image d = new ImageIcon(diceIcons[i]).getImage();
		d=d.getScaledInstance(100,100,Image.SCALE_SMOOTH);
		dice.setIcon(new ImageIcon(d));
	}
	
	//Getters
	public JButton getDice() {return this.dice;}
	public JButton getLoanButton() {return this.loanButton;}
	public JButton getDealCards() {return this.showCards;}
	public JButton getEndTurn() {return this.endTurn;}
	public JLabel getMoney() {return this.money;}
	public JLabel getBill() {return this.bill;}
	public JLabel getLoan() {return this.loan;}
}
