package Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CardsReader {
	int mailCardCount = 0, dealCardCount = 0;
	String[][] mailCards = new String[48][4];
	String[][] dealCards = new String[20][8];
	
	/**
	 * <b>Constructor</b>:Constructs a new cardReader
	 */
	public CardsReader() {
		readFile("resources/dealCards_greeklish.csv","Deal");
		readFile("resources/mailCards_greeklish.csv","Mail");
	}
	
	/**
	 * <b>Transformer</b>:Reads cards from the file with the given path and
	 * stores them to mailCards or dealCards depending on the given type
	 * @param path the path of the file
	 * @param type the type of the cards "Deal" or "Mail"
	 */
	public void readFile(String path, String type) {
		BufferedReader br = null;
		String sCurrentLine;
		try {
			br = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException ex) {
			Logger.getLogger(CardsReader.class.getName()).log(Level.SEVERE, null, ex);
		}
		int count = 0;
		try {
			br.readLine();
			while ((sCurrentLine = br.readLine()) != null) {
				if (type.equals("Mail")) {
					mailCards[count++] = sCurrentLine.split(",");
				} else {
					dealCards[count++] = sCurrentLine.split(",");
				}
			}
			br.close();
		} catch (IOException ex) {
			Logger.getLogger(CardsReader.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	/**
	 * <b>Observer</b>:Returns the card in the i index of mailCards
	 * @param i
	 * @return
	 */
	public MailCard getMailCard(int i) {
		return new MailCard(mailCards[i][0],mailCards[i][1],mailCards[i][2],mailCards[i][3],Integer.parseInt(mailCards[i][4]),"resources/cardImages/" + mailCards[i][5]);
	}
	
	/**
	 * <b>Observer</b>:Returns the card in the i index of dealCards
	 * @param i
	 * @return
	 */
	public DealCard getDealCard(int i) {
		return new DealCard(dealCards[i][0],dealCards[i][1],dealCards[i][2],Integer.parseInt(dealCards[i][3]),Integer.parseInt(dealCards[i][4]),dealCards[i][6],dealCards[i][7],"resources/cardImages/" + dealCards[i][5]);
	}	
	
	
}
