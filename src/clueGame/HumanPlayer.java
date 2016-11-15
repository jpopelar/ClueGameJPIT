package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Scanner;
import java.util.Set;

public class HumanPlayer extends Player {
	Board board = Board.getInstance();
	public static boolean playerMustFinish = false;

	public HumanPlayer(String name, int row, int col, Color color) {
		super(name, row, col, color);
	}
	
	public void makeMove() {
		//System.out.println("Made it into Human");
		board.calcTargets(getRow(), getCol(), board.getDiceRoll());
		Set<BoardCell> targets = board.getTargets();
		
		//System.out.println(targets);
		board.repaint();
		
		playerMustFinish = true;
		
		/*if(chosenBoardCell.isRoom()) {
			Solution guess = null;
			
			// get guess from user
			
			board.handleSuggestion(guess);
		}*/
		
		//board.getTargets().clear();
		
	}
	
}
