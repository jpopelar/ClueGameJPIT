package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clueGame.Board;
import clueGame.Card;
import clueGame.HumanPlayer;
import clueGame.Solution;

public class AccDialog extends JDialog {
	Board board = Board.getInstance();
	ClueGUIMain mainGui = ClueGUIMain.getInstance();
	private Set<Card> weapons, people, rooms;
	public JComboBox<String> thePeople, theWeapons, theRooms;
	

	private boolean submit = false;
	public AccDialog() {
		super();

		Set<Card> deck = board.getDeck();
		weapons = new HashSet<Card>();
		people = new HashSet<Card>();
		rooms = new HashSet<Card>();

		for (Card c : deck) {
			switch (c.getType()) {
			case WEAPON:
				weapons.add(c);
				break;
			case PERSON:
				people.add(c);
				break;
			case ROOM:
				rooms.add(c);
				break;
			default:
			}
		}
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(4,2));
		mainPanel.add(roomSelect());
		mainPanel.add(personSelect());
		mainPanel.add(weaponSelect());
		
		JButton button1 = new JButton("Submit");
		JButton button2 = new JButton("Cancel");
		button1.addActionListener(new SubmitListener());
		button2.addActionListener(new CancelListener());
		
		add(mainPanel, BorderLayout.CENTER);
		
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new GridLayout(1,2));
		
		southPanel.add(button1);
		southPanel.add(button2);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		
		setTitle("Detective Notes");
		setSize(600, 475);
	}	
	
	private JPanel personSelect() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel();
		thePeople = new JComboBox<String>();
		
		label.setText("Suspect");
		for (Card c : people) thePeople.addItem(c.getName());
		
		panel.add(label, BorderLayout.WEST);
		panel.add(thePeople, BorderLayout.EAST);
		return panel;
	}

	private JPanel weaponSelect() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel();
		theWeapons = new JComboBox<String>(); 
		
		label.setText("Murder weapon");
		for (Card c : weapons) theWeapons.addItem(c.getName());
		
		panel.add(label, BorderLayout.WEST);
		panel.add(theWeapons, BorderLayout.EAST);
		return panel;
	}

	public JPanel roomSelect() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel();
		theRooms = new JComboBox<String>();
		
		label.setText("Crime scene");
		for (Card c : rooms) theRooms.addItem(c.getName());
		
		panel.add(label, BorderLayout.WEST);
		panel.add(theRooms, BorderLayout.EAST);
		return panel;
	}

	public boolean wasSubmitted() {
		return submit;
	}
	
	private class SubmitListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			submit = true;
			String weaponSugg = (String)theWeapons.getSelectedItem();
			String personSugg = (String)thePeople.getSelectedItem();
			String roomSugg = (String)theRooms.getSelectedItem();
			
			Solution finalAnswer = new Solution(personSugg, weaponSugg, roomSugg);
			
			if (board.checkAccusation(finalAnswer)) {
				JOptionPane.showMessageDialog(null, "Well done! You win!!");
				setVisible(false);
				mainGui.shutDown();
			}
			
			else {
				JOptionPane.showMessageDialog(null, "You falsely accused " + personSugg + " in the " + roomSugg + " with the " + weaponSugg + ".");
				setVisible(false);
				HumanPlayer.playerMustFinish = false;
				board.advanceTurn();
				board.getTargets().clear();
				board.repaint();
			}
		}
		
	}
	
	private class CancelListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			setVisible(false);
		}
	}
	
	public JComboBox<String> getThePeople() {
		return thePeople;
	}

	public JComboBox<String> getTheWeapons() {
		return theWeapons;
	}
	
}