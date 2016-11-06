package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

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
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(3,2));
		mainPanel.add(makePeopleNotes());
		mainPanel.add(makeSuspectPerson());
		mainPanel.add(makeRoomNotes());
		mainPanel.add(makeSuspectRoom());
		mainPanel.add(makeWeaponNotes());
		mainPanel.add(makeSuspectWeapon());
		
		JButton button = new JButton("Close Notes");
		button.addActionListener(e -> setVisible(false));
		
		add(mainPanel, BorderLayout.CENTER);
		add(button, BorderLayout.SOUTH);
		
		setTitle("Detective Notes");
		setSize(600, 475);
	}
	
	public JPanel makePeopleNotes() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,2));
		
		for (Card c: people) {
			String name = c.getName();
			JCheckBox box = new JCheckBox(name);
			panel.add(box);
		}
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Suspects"));
		
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
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Murder Weapons"));
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
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Crime Scenes"));
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
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Prime Suspect"));
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
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Potential Weapon"));
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
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Murder Room"));
		return panel;
	}

}
