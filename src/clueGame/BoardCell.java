package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;

import javax.swing.JPanel;

public class BoardCell extends JPanel {
	// Integer variable to store row number
	private int row;
	// Integer variable to store column number
	private int column;
	// Character variable to store identifying character of cell
	private char initial;
	// DoorDirection variable to store the direction the door opens
	private DoorDirection opensWhichWay;
	private String roomName = null;
	private boolean isNameLocation = false;
	private static final int SQUARE_SIZE = 20;



	// constructor to set the initial variables
	public BoardCell(int row, int column, char initial, DoorDirection opensWhichWay) {
		super();
		this.row = row;
		this.column = column;
		this.initial = initial;
		this.opensWhichWay = opensWhichWay;
	}

	// determine if cell is a walkway
	public boolean isWalkway(){
		if (Character.toLowerCase(initial) == 'w') return true;
		return false;
	}

	// Determine if the cell is a room
	public boolean isRoom(){
		return !isWalkway();
	}

	// Determine if the cell is a Doorway
	public boolean isDoorway(){
		return opensWhichWay != DoorDirection.NONE;
	}

	// Return the row index for the cell
	public int getRow() {
		return row;
	}

	// Return the column index for the cell
	public int getColumn() {
		return column;
	}

	// returns the direction the door opens, if it opens
	public DoorDirection getDoorDirection() {
		// TODO Auto-generated method stub
		return opensWhichWay;
	}

	// Return the identifying character for the cell
	public char getInitial() {
		// TODO Auto-generated method stub
		return initial;
	}

	public boolean getNameLocation() {
		return isNameLocation;
	}

	public void setNameLocation(boolean isNameLocation) {
		this.isNameLocation = isNameLocation;
	}
	public void setRoomName(Map<Character,String> rooms) {
		this.roomName = rooms.get(initial);
	}

	// toString function used to print the information for the cell
	@Override
	public String toString() {
		return "BoardCell [row=" + row + ", column=" + column + ", initial=" + initial + ", opensWhichWay="
				+ opensWhichWay + "]";
	}

	public boolean equals(BoardCell cell) {
		return ((this.getRow() == cell.getRow()) && (this.getColumn() == cell.getColumn()));
	}
	
	public void draw(Graphics g) {
		if (isWalkway()) {
			System.out.println("Good 1");
			g.setColor(Color.yellow);
			g.fillRect(row*SQUARE_SIZE,column*SQUARE_SIZE,SQUARE_SIZE,SQUARE_SIZE);
		}
		else {
			System.out.println("Good 2");
			g.setColor(Color.gray);
			g.fillRect(row*SQUARE_SIZE,column*SQUARE_SIZE,SQUARE_SIZE,SQUARE_SIZE);
			if(isDoorway()) {
				g.setColor(Color.blue);
				switch (opensWhichWay) {
				case UP:
					g.fillRect(row*SQUARE_SIZE,column*SQUARE_SIZE,SQUARE_SIZE,SQUARE_SIZE/4);
					break;
				case DOWN:
					g.fillRect(row*SQUARE_SIZE,(column+1)*SQUARE_SIZE,SQUARE_SIZE,SQUARE_SIZE/4);
					break;
				case LEFT:
					g.fillRect(row*SQUARE_SIZE,(column)*SQUARE_SIZE,SQUARE_SIZE/4,SQUARE_SIZE);
					break;
				case RIGHT:
					g.fillRect((row + 1)*SQUARE_SIZE,(column)*SQUARE_SIZE,SQUARE_SIZE/4,SQUARE_SIZE);
					break;
				default:
					break;
				}
			}
			if(isNameLocation){
				
				System.out.println("-----------------------------------------------------------------");
				
				g.setColor(Color.blue);
				g.drawString(roomName, row*20, column*20);
			}
		}
	}
}
