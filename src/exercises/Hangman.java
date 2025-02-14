package exercises;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import examples.FileHelper;

public class Hangman extends KeyAdapter {

	Stack<String> puzzles = new Stack<String>();
	ArrayList<JLabel> boxes = new ArrayList<JLabel>();
	int lives = 9;
	JLabel livesLabel = new JLabel("" + lives);

	public static void main(String[] args) {
		Hangman hangman = new Hangman();
		hangman.addPuzzles();
		hangman.createUI();
	}

	private void addPuzzles() {
//		puzzles.push("defenestrate");
//		puzzles.push("fancypants");
//		puzzles.push("elements");
		List<String> listOfWords = FileHelper.loadFileContentsIntoArrayList("resource/words.txt");
		Collections.shuffle(listOfWords);
		for (int i = 0; i < listOfWords.size(); i++) {
			puzzles.push(listOfWords.get(i));
		}
	}

	JPanel panel = new JPanel();
	private String puzzle;

	private void createUI() {
		playDeathKnell();
		JFrame frame = new JFrame("June's Hangman");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.add(livesLabel);
		loadNextPuzzle();
		frame.add(panel);
		frame.setVisible(true);
		frame.pack();
		frame.addKeyListener(this);
	}

	private void loadNextPuzzle() {
		removeBoxes();
		lives = 9;
		livesLabel.setText("" + lives);

		do {
			try {
				puzzle = puzzles.pop();

				if (!puzzle.matches("[a-zA-Z]+")) {
					throw new Exception("Non-valid word. It contains a special character");
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		} while (!puzzle.matches("[a-zA-Z]+"));
	
		System.out.println("puzzle is now " + puzzle);
		createBoxes();
	}

	public void keyTyped(KeyEvent arg0) {
		System.out.println(arg0.getKeyChar());
		updateBoxesWithUserInput(arg0.getKeyChar());

//		if (lives == 0) {
//			playDeathKnell();
//			loadNextPuzzle();
//		}
		if(puzzleSolved()) {
			System.out.println("You won! Try next puzzle.");		
			loadNextPuzzle();
		}
		if (lives == 0 && !puzzleSolved()) {
			System.out.println("You lose all lives. GAME OVER!");
			System.exit(0);
		}
	}

	private void updateBoxesWithUserInput(char keyChar) {
		boolean gotOne = false;
	
		for (int i = 0; i < puzzle.length(); i++) {
			if (puzzle.charAt(i) == keyChar) {
				boxes.get(i).setText("" + keyChar);
				gotOne = true;
			}
		}
		if (!gotOne)
			livesLabel.setText("" + --lives);
	}

	void createBoxes() {
		for (int i = 0; i < puzzle.length(); i++) {
			JLabel textField = new JLabel("_");
			boxes.add(textField);
			panel.add(textField);
		}
	}

	void removeBoxes() {
		for (JLabel box : boxes) {
			panel.remove(box);
		}
		boxes.clear();
	}
	
	public void playDeathKnell() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("resource/funeral-march.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
			Thread.sleep(8400);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	boolean puzzleSolved() {
		StringBuilder sb = new StringBuilder();
		for (JLabel jLabel : boxes) {
			sb.append(jLabel.getText());
		}
		return sb.toString().equals(puzzle);
		
	}

}




