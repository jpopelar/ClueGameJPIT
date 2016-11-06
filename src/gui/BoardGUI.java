package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import clueGame.Board;

public class BoardGUI extends JPanel {
	Board board = Board.getInstance();

	public BoardGUI() {
		add(board);
		
	}
	
	public void paintComponent(Graphics g) {
		board.repaint();
	}
}
