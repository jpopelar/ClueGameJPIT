package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.swing.JOptionPane;

public class ComputerPlayer extends Player {
	private String lastRoom;
	private static Board board;
	private Set<Card> detNotes;
	private Solution suggestion, lastGuess;
	private boolean accReady = false;
	
	public ComputerPlayer(String name, int row, int col, Color color) {
		super(name, row, col, color);
		board = Board.getInstance();
		detNotes= new HashSet<Card>(); //Stores unseen cards
		detNotes = board.getDeck();
	}

	public BoardCell pickLocation(Set<BoardCell> targets) {
		Map<Character, String> legend = board.getLegend(); //We'll need access to the board legend, containing room names
		
		Set<BoardCell> cat1 = new HashSet<BoardCell>();
		Set<BoardCell> cat2 = new HashSet<BoardCell>(); 
		
		for (BoardCell c : targets) { //Sort the target cells into two categories
			if (!legend.get(c.getInitial()).equals(lastRoom) && !c.isWalkway()) cat1.add(c); //Category 1 is all targets that lead to a new room
			else cat2.add(c); //Category 2 is everything else
		}		
		
		Random rand = new Random();
		
		if (!cat1.isEmpty()) { //The targets in category 1 get priority
			int selection = rand.nextInt(cat1.size());
			int i = 0; //Unofficial index for cycling through cat1 set
			for (BoardCell c : cat1) { //Return the cell specified by selection
				if (i == selection) return c;
				i++;
			}		
		}
		
		int selection = rand.nextInt(cat2.size()); //If there are no category 1 targets, then pick a category 2
		int i = 0; //Unofficial index for cycling through cat2 set
		for (BoardCell c : cat2) {
			if (i == selection) return c;
			i++;
		}
		
		return board.getCellAt(getRow(), getCol()); //If somehow we don't return any good targets, just return the current location
	}
	
	public void makeAccusation() {
		accReady = false;
		if (board.checkAccusation(lastGuess)) {
			JOptionPane.showMessageDialog(null, getName() + " accused " + lastGuess.person + " in the " + lastGuess.room + " with the " + lastGuess.weapon + " and was correct! They win!");
			board.setLive(false);
		}
		
		else {
			JOptionPane.showMessageDialog(null, getName() + " falsely accused " + lastGuess.person + " in the " + lastGuess.room + " with the " + lastGuess.weapon + ".");
		}
	}

	public Solution createSuggestion() {
		String room, weapon, person; 
		room = getRoom(); //Room is always whatever room the player is in
		lastRoom = getRoom();
		
		ArrayList<Card> weapons = new ArrayList<Card>(); 
		ArrayList<Card> people = new ArrayList<Card>(); //Make lists of weapons and people the CPU hasn't seen yet
		
		for (Card c : detNotes) { //The set detNotes holds cards the CPU hasn't seen
			switch (c.getType()) { //Sort based on card type
			case PERSON:
				people.add(c); 
				break;
			case WEAPON:
				weapons.add(c);
				break;
			default:
			}
		}
		
		Random rand = new Random();	
		
		int selection = rand.nextInt(people.size()); //Randomly pick a card from seen people
		person = people.get(selection).getName();
		
		selection = rand.nextInt(weapons.size()); //Randomly pick a card from seen weapons
		weapon = weapons.get(selection).getName();
		
		suggestion = new Solution(person, weapon, room); //Prepare the suggestion and send it
		return suggestion;
	}
	
	public void makeMove() {
		if (accReady) {
			makeAccusation();
		}
		
		else {
			//System.out.println("Made it into computer: " + board.getPlayers().get(board.whoseTurn()).getName());
			board.calcTargets(getRow(), getCol(), board.getDiceRoll());
			Set<BoardCell> targets = board.getTargets();

			//System.out.println(targets); //for testing

			BoardCell chosenBoardCell = pickLocation(targets);

			//System.out.println(chosenBoardCell);

			setLocation(chosenBoardCell.getRow(), chosenBoardCell.getColumn());
			if(chosenBoardCell.isRoom()) {
				Solution guess = createSuggestion();
				Card evidence = board.handleSuggestion(guess);
				if ((evidence == null) && (roomCheck(guess.room))) {
					accReady = true;
					lastGuess = guess;
				}
				
				else detNotes.remove(evidence);
				board.suggMade = true; //DO THIS FOR HOOMAN
			}

			board.repaint();
		}
		board.getTargets().clear();
		board.repaint();
		board.advanceTurn();
	}
	
	public void setLastRoom(String lastRoom) {
		this.lastRoom = lastRoom;
	}
	
	public void addToNotes(Card theCard) {
		detNotes.add(theCard);
	}
	
	public void clearNotes() {
		detNotes.clear();
	}
	
	public Set<Card> getNotes() {
		return detNotes;
	}
	
	public String getRoom() {
		Map<Character,String> legend = board.getLegend();
		
		return legend.get(board.getCellAt(getRow(), getCol()).getInitial());
	}

	private boolean roomCheck(String room) {
		for (Card c : getHand()) if (c.getName().equals(room)) return false;
		return true;
	}
}
