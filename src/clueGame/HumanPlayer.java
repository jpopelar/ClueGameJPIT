package clueGame;

import java.awt.Color;
import java.util.Set;

public class HumanPlayer extends Player {
	Board board = Board.getInstance();


	public HumanPlayer(String name, int row, int col, Color color) {
		super(name, row, col, color);
	}
	
	public void makeMove() {
		board.calcTargets(getRow(), getCol(), board.getDiceRoll());
		Set<BoardCell> targets = board.getTargets();
		BoardCell chosenBoardCell = null;
		
		//get chosenBoardCell from user
		
		super.setLocation(chosenBoardCell.getRow(), chosenBoardCell.getColumn());
		if(chosenBoardCell.isRoom()) {
			Solution guess = null;
			
			// get guess from user
			
			board.handleSuggestion(guess);
		}
		
	}
	
}
