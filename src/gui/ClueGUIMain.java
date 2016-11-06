package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class ClueGUIMain extends JFrame {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Popelar-Tobiason Clue"); 
		frame.setSize(500, 200); //WRONG. Needs to be bigger
		
		ControlGUI cont = new ControlGUI();
		frame.add(cont, BorderLayout.SOUTH);
		
		BoardGUI board = new BoardGUI();
		frame.add(board, BorderLayout.CENTER);
		frame.setVisible(true);
	}


}
