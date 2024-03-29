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
import javax.swing.JPanel;
import javax.swing.JTextField;

import clueGame.Board;
import clueGame.Card;
import clueGame.HumanPlayer;
import clueGame.Solution;

public class HumSuggDialog extends JDialog{
	
	Board board = Board.getInstance();
	private Set<Card> weapons, people;
	public JComboBox<String> thePeople, theWeapons;
	

	private boolean submit = false;

	public HumSuggDialog() {
		super();

		Set<Card> deck = board.getDeck();
		weapons = new HashSet<Card>();
		people = new HashSet<Card>();

		for (Card c : deck) {
			switch (c.getType()) {
			case WEAPON:
				weapons.add(c);
				break;
			case PERSON:
				people.add(c);
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
		button1.addActionListener(new SubmitListener());
		
		add(mainPanel, BorderLayout.CENTER);
		
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new GridLayout(1,2));
		
		southPanel.add(button1);
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
		JTextField location = new JTextField(15);
		
		label.setText("Your room");
		location.setEditable(false);
		location.setText(board.translate(board.getCellAt(board.getPlayers().get(0).getRow(), board.getPlayers().get(0).getCol()).getInitial()));
		
		panel.add(label, BorderLayout.WEST);
		panel.add(location, BorderLayout.EAST);
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
						
			board.suggMade = true;
			HumanPlayer.madeSugg = true;
			
			Solution sugg = new Solution(personSugg, weaponSugg, board.translate(board.getCellAt(board.getPlayers().get(0).getRow(), board.getPlayers().get(0).getCol()).getInitial()));
			Card evidence = board.handleSuggestion(sugg);			
			setVisible(false);
			ControlGUI.updateBoxes(sugg, evidence);			
			board.advanceTurn();
		}
		
	}
	public JComboBox<String> getThePeople() {
		return thePeople;
	}

	public JComboBox<String> getTheWeapons() {
		return theWeapons;
	}
	
}
