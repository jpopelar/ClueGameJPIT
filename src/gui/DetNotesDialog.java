package gui;

import java.awt.GridLayout;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

import clueGame.Board;
import clueGame.Card;

public class DetNotesDialog extends JDialog {
	
	Board board = Board.getInstance();
	Set<Card> weapons, rooms, people;
	
	public DetNotesDialog() {
		super();
		
		Set<Card> deck = board.getDeck();
		weapons = new HashSet<Card>();
		rooms = new HashSet<Card>();
		people = new HashSet<Card>();
		
		for (Card c : deck) {
			switch (c.getType()) {
			case WEAPON:
				weapons.add(c);
				break;
			case ROOM:
				rooms.add(c);
				break;
			case PERSON:
				people.add(c);
				break;
			default:
			}
		}
		
		setLayout(new GridLayout(3,2));
		add(makePeopleNotes());
		add(makeSuspectPerson());
		add(makeRoomNotes());
		add(makeSuspectRoom());
		add(makeWeaponNotes());
		add(makeSuspectWeapon());
	}
	
	public JPanel makePeopleNotes() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,2));
		
		for (Card c: people) {
			String name = c.getName();
			JCheckBox box = new JCheckBox(name);
			panel.add(box);
		}
		return panel;
	}
	
	public JPanel makeWeaponNotes() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,2));
		
		for (Card c: weapons) {
			String name = c.getName();
			JCheckBox box = new JCheckBox(name);
			panel.add(box);
		}
		return panel;
	}
	
	public JPanel makeRoomNotes() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,2));
		
		for (Card c: rooms) {
			String name = c.getName();
			JCheckBox box = new JCheckBox(name);
			panel.add(box);
		}
		return panel;
	}
	
	public JPanel makeSuspectPerson() {
		JPanel panel = new JPanel();
		JComboBox<String> suspect = new JComboBox<String>();
		
		for (Card c: people) {
			String name = c.getName();
			suspect.addItem(name);
		}
		panel.add(suspect);
		return panel;
	}
	
	public JPanel makeSuspectWeapon() {
		JPanel panel = new JPanel();
		JComboBox<String> suspect = new JComboBox<String>();
		
		for (Card c: weapons) {
			String name = c.getName();
			suspect.addItem(name);
		}
		panel.add(suspect);
		return panel;
	}
	
	public JPanel makeSuspectRoom() {
		JPanel panel = new JPanel();
		JComboBox<String> suspect = new JComboBox<String>();
		
		for (Card c: rooms) {
			String name = c.getName();
			suspect.addItem(name);
		}
		panel.add(suspect);
		return panel;
	}

}
