package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import clueGame.Board;
import clueGame.HumanPlayer;

public class ControlGUI extends JPanel {
	private static final long serialVersionUID = 1L;
	Board board = Board.getInstance();
	private JTextField turn, rollField, guessField, resultField;


	public ControlGUI() {
		//2 row layout
		setLayout(new GridLayout(2,0));
		JPanel panel = topRowPanel();
		add(panel);
		panel = bottomRowPanel();
		add(panel);
	}

	public JPanel topRowPanel() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1,0));

		//Setup for the turn indicator
		JPanel textPanel = new JPanel();
		turn = new JTextField(15);
		turn.setEditable(false);		
		JLabel textLabel1 = new JLabel("Acitve Player");
		textPanel.add(textLabel1, BorderLayout.NORTH);
		textPanel.add(turn, BorderLayout.CENTER);

		JButton passTurn = new JButton("Next Player");
		passTurn.addActionListener(new TurnListener());
		JButton accuse = new JButton("Accuse a Player");
		accuse.addActionListener(new AccuseListener());

		mainPanel.add(textPanel, BorderLayout.WEST);
		mainPanel.add(passTurn, BorderLayout.EAST);
		mainPanel.add(accuse, BorderLayout.EAST);

		return mainPanel;
	}

	public JPanel bottomRowPanel() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new FlowLayout());

		JPanel roll = new JPanel();
		JPanel guess = new JPanel();
		JPanel result = new JPanel();

		roll.setLayout(new GridLayout(2,0));
		rollField = new JTextField(3);
		rollField.setEditable(false);		
		JLabel textLabel1 = new JLabel("Roll");
		roll.add(textLabel1, BorderLayout.WEST);
		roll.add(rollField, BorderLayout.CENTER);
		roll.setBorder(new TitledBorder(new EtchedBorder(), "Dice"));

		guess.setLayout(new GridLayout(2,0));
		guessField = new JTextField(30);
		guessField.setEditable(false);
		JLabel textLabel2 = new JLabel("Sugestion");
		guess.add(textLabel2, BorderLayout.NORTH);
		guess.add(guessField, BorderLayout.CENTER);
		guess.setBorder(new TitledBorder(new EtchedBorder(), "Insinuations"));

		result.setLayout(new GridLayout(2,0));
		resultField = new JTextField(15);
		resultField.setEditable(false);
		JLabel textLabel3 = new JLabel("Evidence");
		result.add(textLabel3, BorderLayout.NORTH);
		result.add(resultField, BorderLayout.CENTER);
		result.setBorder(new TitledBorder(new EtchedBorder(), "Proof"));

		mainPanel.add(roll);
		mainPanel.add(guess);
		mainPanel.add(result);
		return mainPanel;
	}

	private class TurnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (!HumanPlayer.playerMustFinish){
				//Cleanup
				board.getTargets().clear();
				board.suggMade = false;
				board.lastSuggestion = null;
				
				//Action
				board.rollDice();
				rollField.setText(Integer.toString(board.getDiceRoll()));
				turn.setText(board.getPlayers().get(board.whoseTurn()).getName());
				board.getPlayers().get(board.whoseTurn()).makeMove();
				
				if (board.suggMade) {
					guessField.setText(board.lastSuggestion.person + " in the " + board.lastSuggestion.room + " with a " + board.lastSuggestion.weapon);
					if (board.getEvidence() != null) resultField.setText(board.getEvidence().getName());
					else resultField.setText("No new evidence");
				}
				else {
					guessField.setText(" ");
					resultField.setText(" ");
				}
				
				if (!board.isLive()) setVisible(false);
				board.repaint();
			}
			else {
				JOptionPane.showMessageDialog(null, "You need to complete your turn first.");
			}
		}
	}
	
	private class AccuseListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (HumanPlayer.playerMustFinish) {
				
			}
			
			else {
				JOptionPane.showMessageDialog(null, "You can only accuse a player on your own turn before you move.");
			}
		}
	}
}
