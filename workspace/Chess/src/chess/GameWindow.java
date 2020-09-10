package chess;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.Box.Filler;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class GameWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Entry point for the application
	 */
	public static void main(String[] args) {
		GameWindow gw = new GameWindow();
		
		gw.setVisible(true);
		gw.pack();
		gw.setResizable(false);
		gw.setLocationRelativeTo(null);
	}
	
	JPanel mainPanel;
	JPanel sidePanel;
	JPanel buttonPanel;
	JLabel label;
	Board board;
	JButton newGame;
	JButton settings;
	JButton help;
	JButton quit;
	
	public static final int SPACE_SIZE = 92;
	
	/**
	 * Constructor for the GameWindow class
	 */
	public GameWindow() {
		super("Chess");
		mainPanel = new JPanel();
		sidePanel = new JPanel();
		buttonPanel = new JPanel();
		label = new JLabel();
		newGame = new JButton("New Game");
		settings = new JButton("Settings");
		help = new JButton("Help");
		quit = new JButton("Quit");
		newGame();
		resize();
	}
	
	/**
	 * Resizes all of the components to the correct scale.
	 */
	private void resize() {
		mainPanel.removeAll();
		sidePanel.removeAll();
		buttonPanel.removeAll();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		label.setFont(new Font(label.getFont().getName(), Font.BOLD, board.spaceSize / 2));
		
		Font buttonFont = new Font(label.getFont().getName(), Font.BOLD, board.spaceSize / 4);
		newGame.setFont(buttonFont);
		settings.setFont(buttonFont);
		help.setFont(buttonFont);
		quit.setFont(buttonFont);

		
		newGame.addActionListener(e -> newGameButton());
		settings.addActionListener(e -> settingsButton());
		help.addActionListener(e -> helpButton());
		quit.addActionListener(e -> quitButton());
		
		sidePanel.setPreferredSize(new Dimension(board.spaceSize * 4, board.spaceSize * 8));
		buttonPanel.setPreferredSize(new Dimension(board.spaceSize * 12, board.spaceSize / 2));
		mainPanel.setPreferredSize(new Dimension(board.spaceSize * 12, board.spaceSize * 9));
		label.setPreferredSize(new Dimension(board.spaceSize * 12, board.spaceSize / 2));
		
		buttonPanel.setLayout(new GridLayout(1, 3));
		buttonPanel.add(newGame);
		buttonPanel.add(settings);
		buttonPanel.add(help);
		buttonPanel.add(quit);
		
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
		sidePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(board, BorderLayout.CENTER);
		mainPanel.add(label, BorderLayout.SOUTH);
		mainPanel.add(buttonPanel, BorderLayout.NORTH);
		mainPanel.add(sidePanel, BorderLayout.EAST);
		add(mainPanel);
		
		mainPanel.revalidate();
		sidePanel.revalidate();
		buttonPanel.revalidate();
		pack();
	}
	
	/**
	 * Starts a new game
	 */
	private void newGame() {
		int spaceSize = SPACE_SIZE;
		if (board != null)
			spaceSize = board.spaceSize;
		board = new Board(label, spaceSize);
		//board.setDisplayMode(3);
		List<GamePiece> pieces = new ArrayList<GamePiece>();
		boolean[] tf = { true, false };
		
		GamePiece.setMasterPanel(mainPanel);
		
		for (boolean team : tf) {
			for (int i = 0; i < 8; i++)
				pieces.add(new Pawn(i, team));
			for (boolean right : tf) {
				pieces.add(new Rook(right, team));
				pieces.add(new Knight(right, team));
				pieces.add(new Bishop(right, team));
			}
			pieces.add(new Queen(team));
			pieces.add(new King(team));
		}
		
		GamePiece[] gamePieces = pieces.toArray(new GamePiece[0]);
		board.pieces = gamePieces;
	}
	
	/**
	 * Method to run when the New Game Button is pressed
	 */
	private void newGameButton() {
		sidePanel.removeAll();
		
		JTextArea txt = new JTextArea("Are you sure you want to start a new game?");
		JButton confirm = new JButton("Yes");
		JButton cancel = new JButton("Cancel");
		
		txt.setFont(label.getFont());
		confirm.setFont(label.getFont());
		cancel.setFont(label.getFont());
		
		txt.setSize(sidePanel.getSize());
		txt.setLineWrap(true);
		txt.setBorder(null);
		txt.setOpaque(false);
		txt.setWrapStyleWord(true);
		txt.setEditable(false);
		
		confirm.addActionListener(e -> {
			mainPanel.remove(board);
			sidePanel.removeAll();
			newGame();
			mainPanel.add(board, BorderLayout.CENTER);
			validate();
		});
		cancel.addActionListener(e -> {
			sidePanel.removeAll();
			sidePanel.revalidate();
			//repaint();
		});
		
		txt.setAlignmentX(Component.CENTER_ALIGNMENT);
		confirm.setAlignmentX(Component.CENTER_ALIGNMENT);
		cancel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		sidePanel.add(txt);
		sidePanel.add(confirm);
		sidePanel.add(cancel);
		
		sidePanel.revalidate();
		repaint();
	}
	
	/**
	 * Method to run when the Settings Button is pressed
	 */
	private void settingsButton() {
		sidePanel.removeAll();
		JSlider size = new JSlider(32, Toolkit.getDefaultToolkit().getScreenSize().height / 9, board.spaceSize);
		String str = size.getValue() + "";
		while (str.length() < 3)
			str += " ";
		JLabel sizeVal = new JLabel(str);
		JTextArea sizeLabel = new JTextArea("Change Size");
		Font f = new Font(label.getFont().getName(), Font.BOLD, board.spaceSize / 4);
		sizeLabel.setFont(f);
		sizeVal.setFont(f);
		sizeVal.setFont(f);
		size.setSize(sidePanel.getWidth() / 2, sidePanel.getWidth() / 2);
		size.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				sizeVal.setText(size.getValue() + "");
			}

		});
		sizeLabel.setSize(sidePanel.getSize());
		sizeLabel.setLineWrap(true);
		sizeLabel.setBorder(null);
		sizeLabel.setOpaque(false);
		sizeLabel.setWrapStyleWord(true);
		sizeLabel.setEditable(false);
		
		JSlider styleSlider = new JSlider(0, 7, board.getDisplayMode());
		JLabel styleVal = new JLabel(board.getDisplayText(board.getDisplayMode()));
		JTextArea styleLabel = new JTextArea("Change Game Piece Style");
		styleSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				styleVal.setText(board.getDisplayText(styleSlider.getValue()));
			}

		});
		styleSlider.setSnapToTicks(true);
		styleSlider.setPaintTicks(true);
		styleLabel.setFont(f);
		styleVal.setFont(f);
		styleLabel.setSize(sidePanel.getSize());
		styleLabel.setLineWrap(true);
		styleLabel.setBorder(null);
		styleLabel.setOpaque(false);
		styleLabel.setWrapStyleWord(true);
		styleLabel.setEditable(false);
		
		JButton confirm = new JButton("Apply");
		JButton cancel = new JButton("Cancel");
		confirm.setFont(label.getFont());
		cancel.setFont(label.getFont());
		confirm.addActionListener(e -> {
			board.spaceSize = size.getValue();
			board.setDisplayMode(styleSlider.getValue());
			resize();
			settingsButton();
			repaint();
		});
		cancel.addActionListener(e -> {
			sidePanel.removeAll();
			sidePanel.revalidate();
			repaint();
		});
		
		
		sizeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		size.setAlignmentX(Component.CENTER_ALIGNMENT);
		sizeVal.setAlignmentX(Component.CENTER_ALIGNMENT);
		styleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		styleSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
		styleVal.setAlignmentX(Component.CENTER_ALIGNMENT);
		confirm.setAlignmentX(Component.CENTER_ALIGNMENT);
		cancel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		sidePanel.add(sizeLabel);
		sidePanel.add(size);
		sidePanel.add(sizeVal);
		sidePanel.add(Box.createRigidArea(new Dimension(sidePanel.getWidth(), sidePanel.getHeight() / 6)));
		sidePanel.add(styleLabel);
		sidePanel.add(styleSlider);
		sidePanel.add(styleVal);
		sidePanel.add(confirm);
		sidePanel.add(cancel);
		sidePanel.revalidate();
		repaint();
	}
	
	/**
	 * Method to run when the Quit Button is pressed
	 */
	private void quitButton() {
		sidePanel.removeAll();
		
		JTextArea txt = new JTextArea("Are you sure you want to Quit?");
		JButton confirm = new JButton("Yes");
		JButton cancel = new JButton("Cancel");
		
		txt.setFont(label.getFont());
		confirm.setFont(label.getFont());
		cancel.setFont(label.getFont());
		
		txt.setSize(sidePanel.getSize());
		txt.setLineWrap(true);
		txt.setBorder(null);
		txt.setOpaque(false);
		txt.setWrapStyleWord(true);
		txt.setEditable(false);
		
		confirm.addActionListener(e -> dispose());
		cancel.addActionListener(e -> {
			sidePanel.removeAll();
			repaint();
		});
		
		txt.setAlignmentX(Component.CENTER_ALIGNMENT);
		confirm.setAlignmentX(Component.CENTER_ALIGNMENT);
		cancel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		sidePanel.add(txt);
		sidePanel.add(confirm);
		sidePanel.add(cancel);
		
		sidePanel.revalidate();
	}
	
	/**
	 * Method to run when the Help Button is pressed
	 */
	private void helpButton() {
		sidePanel.removeAll();
		try {
			JEditorPane editorPane = new JEditorPane("text/html", new String(Files.readAllBytes(Paths.get("resources/help.html")), StandardCharsets.UTF_8));
			editorPane.setEditable(false);
			editorPane.setSize(sidePanel.getSize());
			JScrollPane scrollPane = new JScrollPane(editorPane);
			scrollPane.setSize(sidePanel.getSize());
			
			sidePanel.add(scrollPane);
			sidePanel.revalidate();
			
			editorPane.addHyperlinkListener(new HyperlinkListener() {
				@Override
				public void hyperlinkUpdate(HyperlinkEvent he) {
					 if (HyperlinkEvent.EventType.ACTIVATED.equals(he.getEventType()))
                         editorPane.scrollToReference(he.getDescription());
                 }
			});
			editorPane.scrollToReference("top");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
