import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	/**
	 * Initialize the variable
	 * e.g. buttons, label, interger
	 */
	JFrame frame;
	JLabel label_Image1, label_Image2, label_Image3, label_Image4, label_Image5, label_Image6;
	JButton btn_rpcard1, btn_rpcard2, btn_rpcard3, btn_start, btn_result;
	JLabel label_bet, label_info, label_money;
	JTextField txt_inputbet;
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem menuItem;
	int totalMoney = 100;
	int replaceChance = 2;
	private ArrayList<Integer> deck;
	private ArrayList<Integer> dealerCards;
	private ArrayList<Integer> playerCards;
	private ArrayList<Integer> specialCards;
	int dealerSpecial;
	int playerSpecial;
	int dealerScore;
	int playerScore;
	
	public static void main(String[] args) {  
		/**
		 * Run the GUI
		 */
		Main gui = new Main(); 
		gui.go();
	}
	
	public void go() {
		/**
		 * Initialize the deck, which is an ArrayList and shuffle the cards
		 * Create an ArrayList that contain the Special Cards
		 * Initialize the setting of the game
		 */
		deck = new ArrayList<Integer>();
		Collections.addAll(deck, 11, 12, 13, 24, 25, 26, 37, 38, 39, 50, 51, 52, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 401, 402, 403, 404, 405, 406, 407, 408, 409, 410);
		Collections.shuffle(deck);
		
		specialCards = new ArrayList<Integer>();
		Collections.addAll(specialCards, 11, 12, 13, 24, 25, 26, 37, 38, 39, 50, 51, 52);
		
		/**
		 * Create the 5 button and add ActionListener to each of them
		 * Initialize the frame and UI
		 * Devide the frame into 5 panel
		 */
		btn_rpcard1 = new JButton("Replace Card 1"); 
		btn_rpcard1.addActionListener(new btn_rpcard1_listener());
		btn_rpcard2 = new JButton("Replace Card 2"); 
		btn_rpcard2.addActionListener(new btn_rpcard2_listener());
		btn_rpcard3 = new JButton("Replace Card 3"); 
		btn_rpcard3.addActionListener(new btn_rpcard3_listener());
		btn_start = new JButton("Start"); 
		btn_start.addActionListener(new btn_start_listener());
		btn_result = new JButton("Result"); 
		btn_result.addActionListener(new btn_result_listener());
		label_Image1 = new JLabel();
		label_Image2 = new JLabel();
		label_Image3 = new JLabel();
		label_Image4 = new JLabel();
		label_Image5 = new JLabel();
		label_Image6 = new JLabel();
		txt_inputbet = new JTextField(10);
		label_bet = new JLabel("Bet: $");
		label_info = new JLabel("Please place your bet! Amount of money you have:$");
		label_money = new JLabel(String.valueOf(totalMoney));
		Image Image1 = new ImageIcon(this.getClass().getResource("/card_back.gif")).getImage();
		label_Image1.setIcon(new ImageIcon(Image1));
		label_Image2.setIcon(new ImageIcon(Image1));
		label_Image3.setIcon(new ImageIcon(Image1));
		label_Image4.setIcon(new ImageIcon(Image1));
		label_Image5.setIcon(new ImageIcon(Image1));
		label_Image6.setIcon(new ImageIcon(Image1));
		
		JPanel MainPanel = new JPanel();
		JPanel DealerPanel = new JPanel();
		JPanel PlayerPanel = new JPanel();
		JPanel RpCardBtnPanel = new JPanel();
		JPanel ButtonPanel = new JPanel();
		JPanel InfoPanel = new JPanel();
		
		DealerPanel.add(label_Image1);
		DealerPanel.add(label_Image2);
		DealerPanel.add(label_Image3);
		
		PlayerPanel.add(label_Image4);
		PlayerPanel.add(label_Image5);
		PlayerPanel.add(label_Image6);
		
		RpCardBtnPanel.add(btn_rpcard1);
		RpCardBtnPanel.add(btn_rpcard2);
		RpCardBtnPanel.add(btn_rpcard3);
		
		ButtonPanel.add(label_bet);
		ButtonPanel.add(txt_inputbet);
		ButtonPanel.add(btn_start);
		ButtonPanel.add(btn_result);
		
		InfoPanel.add(label_info);
		InfoPanel.add(label_money);
		
		MainPanel.setLayout(new GridLayout(5,1));
		MainPanel.add(DealerPanel);
		MainPanel.add(PlayerPanel);
		MainPanel.add(RpCardBtnPanel);
		MainPanel.add(ButtonPanel);
		MainPanel.add(InfoPanel);
		
		DealerPanel.setBackground(Color.green);
		PlayerPanel.setBackground(Color.green);
		RpCardBtnPanel.setBackground(Color.green);
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuBar = new JMenuBar();
		menu = new JMenu("Control");
		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(new menu_exit());
		menu.add(menuItem);
		menuBar.add(menu);
		frame.setJMenuBar(menuBar);
		frame.getContentPane().add(MainPanel);
		frame.setTitle("A Simple Card Game");
		frame.setSize(400, 700);
		frame.setVisible(true);
		
		btn_start.setEnabled(true);
		btn_result.setEnabled(false);
		btn_rpcard1.setEnabled(false);
		btn_rpcard2.setEnabled(false);
		btn_rpcard3.setEnabled(false);
	}
	
	class btn_rpcard1_listener implements ActionListener {
		/**
		 * Action Listener of the button used for replacing the first card
		 * Replace the first card of the player after pressing the button
		 */
		public void actionPerformed(ActionEvent event) {
			btn_rpcard1.setEnabled(false); // Once the button is pressed, it will be disbled for that round of game
			playerCards.set(0, deck.get(0));
			deck.remove(0);
			Image playerFirstCardRp = new ImageIcon(this.getClass().getResource("/"+String.valueOf(playerCards.get(0))+".gif")).getImage();
			label_Image4.setIcon(new ImageIcon(playerFirstCardRp));
			replaceChance -= 1;
			if (replaceChance == 0) {
				/**
				 * Only get 2 chances to replace the card, once the chances is used up, all replacing button will be disabled
				 * Same for the replacing button of the second and third cards of player
				 */
				btn_rpcard1.setEnabled(false);
				btn_rpcard2.setEnabled(false);
				btn_rpcard3.setEnabled(false);
			}
		}
	}
	
	class btn_rpcard2_listener implements ActionListener {
		/**
		 * Action Listener of the button used for replacing the first card
		 * Replace the first card of the player after pressing the button
		 */
		public void actionPerformed(ActionEvent event) {
			btn_rpcard2.setEnabled(false);
			playerCards.set(1, deck.get(0));
			deck.remove(0);
			Image playerSecondCardRp = new ImageIcon(this.getClass().getResource("/"+String.valueOf(playerCards.get(1))+".gif")).getImage();
			label_Image5.setIcon(new ImageIcon(playerSecondCardRp));
			replaceChance -= 1;
			if (replaceChance == 0) {
				btn_rpcard1.setEnabled(false);
				btn_rpcard2.setEnabled(false);
				btn_rpcard3.setEnabled(false);
			}
		}
	}
	
	class btn_rpcard3_listener implements ActionListener {
		/**
		 * Action Listener of the button used for replacing the first card
		 * Replace the first card of the player after pressing the button
		 */
		public void actionPerformed(ActionEvent event) {
			btn_rpcard3.setEnabled(false);
			playerCards.set(2, deck.get(0));
			deck.remove(0);
			Image playerThirdCardRp = new ImageIcon(this.getClass().getResource("/"+String.valueOf(playerCards.get(2))+".gif")).getImage();
			label_Image6.setIcon(new ImageIcon(playerThirdCardRp));
			replaceChance -= 1;
			if (replaceChance == 0) {
				btn_rpcard1.setEnabled(false);
				btn_rpcard2.setEnabled(false);
				btn_rpcard3.setEnabled(false);
			}
		}
	}
	
	class btn_start_listener implements ActionListener {
		/**
		 * Action Listener of the button used for placing the bet and starting the game
		 * After the start button is pressed, the 3 cards of player will be shown
		 * And the player has 2 chances to replace their card
		 * And the start button will be disabled until that round of game is ended
		 */
		public void actionPerformed(ActionEvent event) {
			if (Integer.valueOf(txt_inputbet.getText()) > totalMoney) {
				JOptionPane.showMessageDialog(null,"Oops! You don't have enough money!","Message",JOptionPane.INFORMATION_MESSAGE);
			}
			
			if (Integer.valueOf(txt_inputbet.getText()) <= totalMoney) {
				label_info.setText("Your current bet is:$" + txt_inputbet.getText() + " Amount of money you have:$");
				
				btn_start.setEnabled(false);
				btn_result.setEnabled(true);
				btn_rpcard1.setEnabled(true);
				btn_rpcard2.setEnabled(true);
				btn_rpcard3.setEnabled(true);
				
				dealerCards = new ArrayList<Integer>();
				playerCards = new ArrayList<Integer>();
				
				dealerCards.add(deck.get(0));
				dealerCards.add(deck.get(1));
				dealerCards.add(deck.get(2));
				playerCards.add(deck.get(3));
				playerCards.add(deck.get(4));
				playerCards.add(deck.get(5));
				
				for (int i=0; i<6; i++) {
					deck.remove(0);
				}
				
				Image playerFirstCard = new ImageIcon(this.getClass().getResource("/"+String.valueOf(playerCards.get(0))+".gif")).getImage();
				Image playerSecondCard = new ImageIcon(this.getClass().getResource("/"+String.valueOf(playerCards.get(1))+".gif")).getImage();
				Image playerThirdCard = new ImageIcon(this.getClass().getResource("/"+String.valueOf(playerCards.get(2))+".gif")).getImage();
				Image Image1 = new ImageIcon(this.getClass().getResource("/card_back.gif")).getImage();
				label_Image1.setIcon(new ImageIcon(Image1));
				label_Image2.setIcon(new ImageIcon(Image1));
				label_Image3.setIcon(new ImageIcon(Image1));
				label_Image4.setIcon(new ImageIcon(playerFirstCard));
				label_Image5.setIcon(new ImageIcon(playerSecondCard));
				label_Image6.setIcon(new ImageIcon(playerThirdCard));
			}
			
			}
			
	}

	
	class btn_result_listener implements ActionListener {
		/**
		 * Action Listener of the button used for getting the result of that round of game
		 * Once the result button is pressed
		 * all cards will be shown, including the cards of dealer and the player
		 */
		public void actionPerformed(ActionEvent event) {
			dealerSpecial = 0;
			playerSpecial = 0;
			dealerScore = 0;
			playerScore = 0;
			replaceChance = 2;
			
			Image dealerFirstCard = new ImageIcon(this.getClass().getResource("/"+String.valueOf(dealerCards.get(0))+".gif")).getImage();
			Image dealerSecondCard = new ImageIcon(this.getClass().getResource("/"+String.valueOf(dealerCards.get(1))+".gif")).getImage();
			Image dealerThirdCard = new ImageIcon(this.getClass().getResource("/"+String.valueOf(dealerCards.get(2))+".gif")).getImage();
			label_Image1.setIcon(new ImageIcon(dealerFirstCard));
			label_Image2.setIcon(new ImageIcon(dealerSecondCard));
			label_Image3.setIcon(new ImageIcon(dealerThirdCard));
			
			for (int i=0; i<3; i++) {
				if (specialCards.contains(dealerCards.get(i))) {
					dealerSpecial += 1; //Counting the number of special cards of dealer
				}
			}
			
			for (int j=0; j<3; j++) {
				if (specialCards.contains(playerCards.get(j))) {
					playerSpecial += 1; //Counting the number of special cards of dealer
				}
			}
			
			if (dealerSpecial > playerSpecial) {
				/**
				 * If dealer have more special cards, dealer win the game
				 * Bet is lost
				 * Initialize the setting and start a new round of game if player still have money
				 * If not, disable all button and end the game
				 */
				totalMoney -= Integer.valueOf(txt_inputbet.getText());
				if (totalMoney == 0) {
					label_info.setText("You have no money! Please start a new game!");
					label_money.setText("");
					JOptionPane.showMessageDialog(null,"Game over!\nYou have no more money!\nPlease start a new game!","Message",JOptionPane.INFORMATION_MESSAGE);
					btn_start.setEnabled(false);
					btn_result.setEnabled(false);
					btn_rpcard1.setEnabled(false);
					btn_rpcard2.setEnabled(false);
					btn_rpcard3.setEnabled(false);
				}
				else {
					JOptionPane.showMessageDialog(null,"Sorry! The Dealer wins this round!","Message",JOptionPane.INFORMATION_MESSAGE);
					btn_start.setEnabled(true);
					btn_result.setEnabled(false);
					btn_rpcard1.setEnabled(false);
					btn_rpcard2.setEnabled(false);
					btn_rpcard3.setEnabled(false);
					label_info.setText("Please place your bet! Amount of money you have:$");
					label_money.setText(String.valueOf(totalMoney));
					playerCards.clear();
					dealerCards.clear();
				}
			}
			
			if (dealerSpecial < playerSpecial) { // Case that player have more special cards
				JOptionPane.showMessageDialog(null,"Congratulations! You win this round","Message",JOptionPane.INFORMATION_MESSAGE);
				btn_start.setEnabled(true);
				btn_result.setEnabled(false);
				btn_rpcard1.setEnabled(false);
				btn_rpcard2.setEnabled(false);
				btn_rpcard3.setEnabled(false);
				totalMoney += Integer.valueOf(txt_inputbet.getText());
				label_info.setText("Please place your bet! Amount of money you have:$");
				label_money.setText(String.valueOf(totalMoney));
				playerCards.clear();
				dealerCards.clear();
			}
			
			if (dealerSpecial == playerSpecial) { // Case that player and dealer have the same amount of special cards
				for (int a=0; a<3; a++) {
					if (specialCards.contains(dealerCards.get(a))) {
						dealerScore = dealerScore;
					}
					else {
						dealerScore += dealerCards.get(a)%100;
					}
				}
				
				for (int b=0; b<3; b++) {
					if (specialCards.contains(playerCards.get(b))) {
						playerScore = playerScore;
					}
					else {
						playerScore += playerCards.get(b)%100;
					}
				}
				
				if (dealerScore%10 > playerScore%10) { //Rule 2
					totalMoney -= Integer.valueOf(txt_inputbet.getText());
					if (totalMoney == 0) {
						label_info.setText("You have no money! Please start a new game!");
						label_money.setText("");
						JOptionPane.showMessageDialog(null,"Game over!\nYou have no more money!\nPlease start a new game!","Message",JOptionPane.INFORMATION_MESSAGE);
						btn_start.setEnabled(false);
						btn_result.setEnabled(false);
						btn_rpcard1.setEnabled(false);
						btn_rpcard2.setEnabled(false);
						btn_rpcard3.setEnabled(false);
					}
					else {
						JOptionPane.showMessageDialog(null,"Sorry! The Dealer wins this round!","Message",JOptionPane.INFORMATION_MESSAGE);
						btn_start.setEnabled(true);
						btn_result.setEnabled(false);
						btn_rpcard1.setEnabled(false);
						btn_rpcard2.setEnabled(false);
						btn_rpcard3.setEnabled(false);
						label_info.setText("Please place your bet! Amount of money you have:$");
						label_money.setText(String.valueOf(totalMoney));
						playerCards.clear();
						dealerCards.clear();
					}
				}
				
				if (dealerScore%10 < playerScore%10) { //Rule 2
					JOptionPane.showMessageDialog(null,"Congratulations! You win this round","Message",JOptionPane.INFORMATION_MESSAGE);
					btn_start.setEnabled(true);
					btn_result.setEnabled(false);
					btn_rpcard1.setEnabled(false);
					btn_rpcard2.setEnabled(false);
					btn_rpcard3.setEnabled(false);
					totalMoney += Integer.valueOf(txt_inputbet.getText());
					label_info.setText("Please place your bet! Amount of money you have:$");
					label_money.setText(String.valueOf(totalMoney));
					playerCards.clear();
					dealerCards.clear();
				}
				
				if (dealerScore%10 == playerScore%10) { //Rule 3
					totalMoney -= Integer.valueOf(txt_inputbet.getText());
					if (totalMoney == 0) {
						JOptionPane.showMessageDialog(null,"Sorry! The Dealer wins this round!","Message",JOptionPane.INFORMATION_MESSAGE);
						label_info.setText("You have no money! Please start a new game!");
						label_money.setText("");
						JOptionPane.showMessageDialog(null,"Game over!\nYou have no more money!\nPlease start a new game!","Message",JOptionPane.INFORMATION_MESSAGE);
						btn_start.setEnabled(false);
						btn_result.setEnabled(false);
						btn_rpcard1.setEnabled(false);
						btn_rpcard2.setEnabled(false);
						btn_rpcard3.setEnabled(false);
					}
					else {
						btn_start.setEnabled(true);
						btn_result.setEnabled(false);
						btn_rpcard1.setEnabled(false);
						btn_rpcard2.setEnabled(false);
						btn_rpcard3.setEnabled(false);
						label_info.setText("Please place your bet! Amount of money you have:$");
						label_money.setText(String.valueOf(totalMoney));
						playerCards.clear();
						dealerCards.clear();
					}
				}
				
			}	
		}
	}
	
	class menu_exit implements ActionListener {
		/**
		 * Close the frame and end the game when the Exit button inside the control menu is pressed
		 */
		public void actionPerformed(ActionEvent event) {
			System.exit(0);
		}
	}
	
}