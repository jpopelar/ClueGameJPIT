package gui;

import java.awt.GridLayout;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import clueGame.Board;
import clueGame.Card;

public class HandGUI extends JPanel {
	private Set<Card> people, rooms, weapons;
	private Board board = Board.getInstance();

	public HandGUI() {
		Set<Card> hand = board.getPlayers().get(0).getHand();
		people = new HashSet<Card>();
		rooms = new HashSet<Card>();
		weapons = new HashSet<Card>();
		for(Card c : hand) {
			switch (c.getType()) {
			case PERSON:
				people.add(c);
				break;
			case ROOM:
				rooms.add(c);
				break;
			case WEAPON:
				weapons.add(c);
				break;
			default:
				break;
			}
		}
		
		setLayout(new GridLayout(3,1));
		setBorder(new TitledBorder(new EtchedBorder(), "My Cards"));
		
		add(peoplePanel());
		add(roomPanel());
		add(weaponPanel());
	}
	
	private JPanel peoplePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,1));
		panel.setBorder(new TitledBorder(new EtchedBorder(), "People"));
		
		for (Card c : people) {
			JTextField cardName = new JTextField(c.getName());
			cardName.setEditable(false);
			cardName.setSize(100, 500);
			panel.add(cardName);
		}
		
		return panel;
	}
		
	private JPanel roomPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,1));
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Rooms"));
		
		for (Card c : rooms) {
			JTextField cardName = new JTextField(c.getName());
			cardName.setEditable(false);
			panel.add(cardName);
		}

		return panel;
	}

	private JPanel weaponPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,1));
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Weapons"));
		
		for (Card c : weapons) {
			JTextField cardName = new JTextField(c.getName());
			cardName.setEditable(false);
			panel.add(cardName);
		}

		return panel;
	}

}
