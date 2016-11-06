package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import clueGame.Board;

public class ClueGUIMain extends JFrame {
	private DetNotesDialog detNotes;
	Board board = Board.getInstance();
	
	public ClueGUIMain() {
		board.setConfigFiles("TCJPClueLayout.csv", "TCJPClueLayoutLegend.txt");		
		board.initialize();

		board.setPeopleFile("TCJPSuspects.txt");
		board.loadPeopleConfig();

		board.setWeaponFile("TCJPWeapons.txt");
		board.loadWeaponConfig();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Popelar-Tobiason Clue"); 
		setSize(1000, 400); //WRONG. Needs to be bigger
		detNotes = new DetNotesDialog();
		
		BoardGUI board = new BoardGUI();
		board.setSize(new Dimension(500,500));
		add(board, BorderLayout.CENTER);
		
		ControlGUI cont = new ControlGUI();
		add(cont, BorderLayout.SOUTH);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(createFileMenu());			
		
	}
	
	
	private JMenu createFileMenu() {
		JMenu menu = new JMenu("File");
		menu.add(createDetectiveNotes());
		menu.add(createFileExitItem());
		return menu;
	}
	
	private JMenuItem createFileExitItem() {
		JMenuItem item = new JMenuItem("Exit");
		class MenuItemListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}
		item.addActionListener(new MenuItemListener());
		return item;
	}
	
	private JMenuItem createDetectiveNotes() {
		JMenuItem item = new JMenuItem("View Notes...");
		class MenuItemListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				detNotes.setVisible(true);
			}
		}
		item.addActionListener(new MenuItemListener());
		return item;
	}
	
	public static void main(String[] args) {
		ClueGUIMain frame = new ClueGUIMain();
		frame.setVisible(true);
	}
	
}
