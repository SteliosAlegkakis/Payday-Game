package View;

import java.awt.Image;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.Controller;

public class View extends JFrame{
	private JLabel[] board;
	private JLabel[] dates;
	private JLabel pawn1,pawn2,jackpot,info;
	private JPanel infoBox;
	private JButton deal,mail;
	private PlayerPanel p1,p2;
	private JLayeredPane pane;
	private String[] boardIcons;
	
	/**
	 * <b>Constructor</b>:Initialises some private fields 
	 * @param name1 the name of the first player
	 * @param name2 the name of the second player
	 */
	public View(String name1,String name2) {
		pane=new JLayeredPane();
		pane.setBounds(0,0,1000,1000);
		board = new JLabel[32];
		dates = new JLabel[32];
		for(int i=0; i<32; i++) {
			board[i] = new JLabel();
			dates[i] = new JLabel();
		}
		pawn1 = new JLabel();
		pawn2 = new JLabel();
		jackpot = new JLabel();
		info = new JLabel();
		infoBox = new JPanel();
		deal = new JButton();
		mail = new JButton();
		p1 = new PlayerPanel(name1,20);
		p2 = new PlayerPanel(name2,470);
		boardIcons = new String[32];
		
		setLayeredPane(pane);
		setTitle("PAY DAY");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(0,0,1000,1000);
		setResizable(false);
		setVisible(true);
	}
	
	/**
	 * Sets controller c as the action listener for all buttons in the window
	 * @param c the actionListener for the buttons
	 */
	public void setActionListeners(Controller c) {
		mail.addActionListener(c);
		deal.addActionListener(c);
		p1.setActionListeners(c);
		p2.setActionListeners(c);
	}
	
	/**
	 * <b>Transformer</b>:Creates the graphics of the game and adds all the 
	 * components to the layered pane of the jFrame
	 */
	public void initGraphics() {
		//put background
		Image bg = new ImageIcon("resources/images/bg_green.png").getImage();
		bg=bg.getScaledInstance(1000,1000,Image.SCALE_SMOOTH);
		JLabel background = new JLabel(new ImageIcon(bg));
		background.setBounds(0,0,1000,1000);
		pane.add(background,Integer.valueOf(0));
		
		//put logo
		Image lg = new ImageIcon("resources/images/logo.png").getImage();
		lg=lg.getScaledInstance(700,100,Image.SCALE_SMOOTH);
		JLabel logo = new JLabel(new ImageIcon(lg));
		logo.setBounds(0,0,700,100);
		pane.add(logo,Integer.valueOf(1));
		
		//put the icons of the board and the JLabels of the dates
		int pos=0;
		Image image;
		for(int i=0;i<4;i++) {
			for(int j=0;j<7;j++) {
				image = new ImageIcon(boardIcons[pos]).getImage();
				image=image.getScaledInstance(100,100,Image.SCALE_SMOOTH);
				board[pos].setIcon(new ImageIcon(image));
				board[pos].setBounds(j*100,i*117+117,100,100);
				pane.add(board[pos],Integer.valueOf(1));
				dates[pos].setBounds(j*100,i*117+100,100,17);
				dates[pos].setBackground(Color.YELLOW);
				dates[pos].setOpaque(true);
				pane.add(dates[pos],Integer.valueOf(2));
				pos++;
			}	
		}
		
		//put icons on the board
		for(int i=0;i<4;i++) {
			image = new ImageIcon(boardIcons[pos]).getImage();
			image=image.getScaledInstance(100,100,Image.SCALE_SMOOTH);
			board[pos].setIcon(new ImageIcon(image));
			board[pos].setBounds(i*100,590,100,100);
			pane.add(board[pos],Integer.valueOf(1));
			dates[pos].setBounds(i*100,569,100,21);
			dates[pos].setBackground(Color.YELLOW);
			dates[pos].setOpaque(true);
			pane.add(dates[pos],Integer.valueOf(2));
			pos++;
		}
		
		//put jackpot
		Image j = new ImageIcon("resources/images/jackpot.png").getImage();
		j=j.getScaledInstance(200,80,Image.SCALE_SMOOTH);
		JLabel jackpot = new JLabel();
		jackpot.setBounds(450,590,200,80);
		jackpot.setIcon(new ImageIcon(j));
		pane.add(jackpot,Integer.valueOf(1));
		this.jackpot.setBounds(450,670,200,15);
		this.jackpot.setForeground(Color.WHITE);
		pane.add(this.jackpot,Integer.valueOf(1));
		
		//put days and dates on the dates JLabels
		dates[0].setText("Start");
		pos=1;
		for(int i=0;i<4;i++) {
			dates[pos].setText("Monday "+pos);pos++;
			dates[pos].setText("Tuesday "+pos);pos++;
			dates[pos].setText("Wednesday "+pos);pos++;
			dates[pos].setText("Thursday "+pos);pos++;
			dates[pos].setText("Friday "+pos);pos++;
			dates[pos].setText("Saturday "+pos);pos++;
			dates[pos].setText("Sunday "+pos);pos++;
		}
		dates[29].setText("Monday "+29);
		dates[30].setText("Tuesday "+30);
		dates[31].setText("Wednesday "+31);
		
		//put the pawns
		
		Image pl1 = new ImageIcon("resources/images/pawn_blue.png").getImage();
		pl1=pl1.getScaledInstance(50,50,Image.SCALE_SMOOTH);
		pawn1.setBounds(0,130,50,50);
		pawn1.setIcon(new ImageIcon(pl1));
		pane.add(pawn1,Integer.valueOf(2));
		
		Image pl2 = new ImageIcon("resources/images/pawn_yellow.png").getImage();
		pl2=pl2.getScaledInstance(50,50,Image.SCALE_SMOOTH);
		pawn2.setBounds(50,130,50,50);
		pawn2.setIcon(new ImageIcon(pl2));
		pane.add(pawn2,Integer.valueOf(2));
		
		//put mail and deal buttons
		
		Image m = new ImageIcon("resources/images/mailCard.png").getImage();
		m=m.getScaledInstance(110,60,Image.SCALE_SMOOTH);
		mail.setBounds(720,390,110,60);
		mail.setIcon(new ImageIcon(m));
		pane.add(mail,Integer.valueOf(1));
		
		Image d = new ImageIcon("resources/images/dealCard.png").getImage();
		d=d.getScaledInstance(110,60,Image.SCALE_SMOOTH);
		deal.setBounds(860,390,110,60);
		deal.setIcon(new ImageIcon(d));
		pane.add(deal,Integer.valueOf(1));
		
		//put player panels and info box
		
		JLabel bg1 = new JLabel();
		bg1.setBounds(718,18,256,206);
		bg1.setOpaque(true);
		bg1.setBackground(Color.BLUE);
		pane.add(bg1,Integer.valueOf(1));
		pane.add(p1,Integer.valueOf(2));
		
		JLabel bg2 = new JLabel();
		bg2.setBounds(718,468,256,206);
		bg2.setBackground(Color.YELLOW);
		bg2.setOpaque(true);
		pane.add(bg2,Integer.valueOf(1));
		pane.add(p2,Integer.valueOf(2));
		
		JLabel bg3 = new JLabel();
		bg3.setBounds(718,238,256,136);
		bg3.setOpaque(true);
		bg3.setBackground(Color.BLACK);
		pane.add(bg3,Integer.valueOf(1));
		infoBox.setBounds(720,240,250,130);
		infoBox.add(new JLabel("Info Box"));
		info.setBounds(720,220,250,120);
		pane.add(info,Integer.valueOf(3));
		pane.add(infoBox,Integer.valueOf(2));
	}
	
	/**
	 * <b>Transformer</b>:Moves Player's(1 or 2) pawn to the given position in the board
	 * @param player the player to be moved (1 or 2)
	 * @param position the position that he is moved
	 */
	public void move(int player,int position) {
		if(player==1) 
			pawn1.setBounds(board[position].getX(),board[position].getY(),50,50);
		if(player==2)
			pawn2.setBounds(board[position].getX()+50,board[position].getY(),50,50);
	}
	
	/**
	 * Shows a JOptionPane that represents a mail card with the given parameters
	 * @param typeen the typeen of the card
	 * @param message the message of the card
	 * @param choice the choice of the card
	 * @param icon the path for the icon of the card
	 */
	public void showMailCard(String typeen,String message,String choice,String icon) {
		Image i = new ImageIcon(icon).getImage();
		i=i.getScaledInstance(200,200,Image.SCALE_SMOOTH);
		Object[] options = {choice};
		JOptionPane.showOptionDialog(this,
			    message,
			    typeen,
			    JOptionPane.OK_OPTION,
			    0,
			    new ImageIcon(i),
			    options,
			    options[0]);
	}
	
	/**
	 * Shows a JOptionPane that represents a deal card with the given parameters
	 * @param typeen typeen of the card
	 * @param message message of the card
	 * @param choice1 choice1 of the card
	 * @param choice2 choice 2 of the card
	 * @param value value of the card
	 * @param cost cost of the card
	 * @param icon the path for the icon of the card
	 * @return 1 if yes option is pressed else 0
	 */
	public int showDealCard(String typeen,String message,String choice1,String choice2,int value,int cost,String icon) {
		Image i = new ImageIcon(icon).getImage();
		i=i.getScaledInstance(200,200,Image.SCALE_SMOOTH);
		Object[] options = {choice1,choice2};
		int b = JOptionPane.showOptionDialog(this, 
				message+"\nTimi agoras: "+cost+" Euro\nTimi pwlisis: "+value+" Euro"
				, typeen, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE
				, new ImageIcon(i),options,options[0]);
		if(b==JOptionPane.YES_OPTION) return 1;
		return 0;
	
	}
	
	/**
	 * Shows a JOptionPane that has 3 option to choose how many months 
	 * the game will last (from 1 to 3)
	 * @return a number from 1 to 3
	 */
	public static int monthPanel() {
		String[] option = {"1","2","3"};
		Image i = new ImageIcon("resources/images/logo.png").getImage();
		i=i.getScaledInstance(200,200,Image.SCALE_SMOOTH);
		int m = JOptionPane.showOptionDialog(new JFrame(),"Epilekste posous mhnes tha diarkesei to paixnidi"
				, "PAY DAY",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, 
				new ImageIcon(i), option, option[0]);
		if(m==JOptionPane.YES_OPTION) return 1;
		if(m==JOptionPane.NO_OPTION) return 2;
		if(m==JOptionPane.CANCEL_OPTION) return 3;
		if(m==JOptionPane.CLOSED_OPTION) System.exit(0);
		return 1;
	}
	
	/**
	 * Shows a JOptionPane that has two text fields to enter the names of the two 
	 * players
	 * @return a String[] containing two Strings with the names
	 */
	public static String[] namesPanel() {
		JTextField player1 = new JTextField();
		JTextField player2 = new JTextField();
		player1.setText("Player 1");
		player2.setText("Player 2");
		Object[] message = {
		    "Player 1 Name:", player1,
		    "Player 2 Name:", player2};
		int option = JOptionPane.showConfirmDialog(new JFrame(), message, "Choose Names", JOptionPane.OK_CANCEL_OPTION);
		String[] names = {"Player1","Player2"};
		if (option == JOptionPane.OK_OPTION) {
			if(!player1.getText().isEmpty()) names[0]=player1.getText();
			if(!player2.getText().isEmpty()) names[1]=player2.getText();
		}
		else System.exit(0);
		return names;
	}
	
	/**
	 * <b>Transformer</b>:Sets boardIcons to equal b 
	 * @param b a String[] with all the path for the icons of the board
	 */
	public void setBoardIcons(String[] b) {this.boardIcons=b;}
	
	//Getters
	
	/**
	 * <b>Observer</b>:gets the info
	 * @return info
	 */
	public JLabel getInfo() {return this.info;}
	
	/**
	 * <b>Observer</b>:gets jackpot
	 * @return jackpot
	 */
	public JLabel getJackpot() {return this.jackpot;}
	
	/**
	 * <b>Observer</b>:gets mail
	 * @return mail
	 */
	public JButton getMail() {return this.mail;}
	
	/**
	 * <b>Observer</b>:gets deal
	 * @return deal
	 */
	public JButton getDeal() {return this.deal;}
	
	/**
	 * <b>Observer</b>:gets p1
	 * @return p1
	 */
	public PlayerPanel getP1() {return this.p1;}
	
	/**
	 * <b>Observer</b>:gets p2
	 * @return p2
	 */
	public PlayerPanel getP2() {return this.p2;}
}
