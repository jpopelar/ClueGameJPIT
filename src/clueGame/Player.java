package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.JPanel;

import sun.java2d.pipe.DrawImagePipe;

public abstract class Player extends JPanel{
	private String playerName;
	private int row;
	private int col;
	private Color color;
	private Set<Card> hand;
	private int boardSize;
	
	public Player(String name, int row, int col, Color color) {
		super();
		this.playerName = name;
		this.row = row;
		this.col = col;
		this.color = color;
		this.boardSize = Board.SQUARE_SIZE;
		hand = new HashSet<Card>();
	}
	
	public Card disproveSuggestion(Solution suggestion) {
		ArrayList<Card> matchCards = new ArrayList<Card>(); //Set up a list of cards that match the suggestion
		
		for (Card c : hand) if (c.getName().equals(suggestion.person) || //If the player's holding one
							    c.getName().equals(suggestion.weapon) || 
							    c.getName().equals(suggestion.room)) 
			matchCards.add(c); //Add it to the list
		
		if (matchCards.size() == 0) return null; //Return null if no response
		
		Random rand = new Random(); //Otherwise, randomly grab a card out of the list and return
		return matchCards.get(rand.nextInt(matchCards.size()));
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval((col)*boardSize, (row)*boardSize , boardSize, boardSize);
		g.setColor(Color.black);
		g.drawOval((col)*boardSize, (row)*boardSize , boardSize, boardSize);
	}
	
	public abstract void makeMove();
	//SETTERS AND GETTERS ARE FOR TESTING PURPOSES ONLY!!!!
	
	public void setName(String name) {
		this.playerName = name;
	}

	public void setLocation(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getName() {
		return playerName;
	}

	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}

	public Color getColor() {
		return color;
	}

	public Set<Card> getHand() {
		return hand;
	}

	public void giveCard(Card targetCard) {
		hand.add(targetCard);
	} 	
}
