package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ClueGUIMain extends JFrame {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Popelar-Tobiason Clue"); 
		frame.setSize(500, 200); //WRONG. Needs to be bigger
		
		ControlGUI cont = new ControlGUI();
		frame.add(cont, BorderLayout.SOUTH);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		
		BoardGUI board = new BoardGUI();
		frame.add(board, BorderLayout.CENTER);
		frame.setVisible(true);
	}
	private JMenu createFileMenu() {
		JMenu menu = new JMenu("File");
		menu.add(createDectiveNotes());
		menu.add(createFileExitItem());
		return menu;
	}
	private JMenuItem createFileExitItem() {
		JMenuItem item = new JMenuItem("Exit");
		return item;
	}

}
