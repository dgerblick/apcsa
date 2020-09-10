package chess;

import java.awt.Image;
import java.awt.Point;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

public abstract class GamePiece {
	
	private boolean team; // true: Black, false: White
	protected int x;
	protected int y;
	private int value;
	private int moved;
	private boolean lastMoved;
	private boolean takenTesting;
	protected static JPanel masterPanel;
	public static Board board;
	
	// Getters and Setters
	
	public boolean getTeam() { return team; }
	
	public int getX() { return x; }
	public int getY() { return y; }
	
	public int getValue() { return value; }
	public void setValue(int value) { this.value = value; }
	
	public int getMoved() { return moved; }
	public void setMoved(int moved) { this.moved = moved; }
	public void incrementMoved() { moved++; }
	
	public boolean isLastMoved() { return lastMoved; }
	public void setLastMoved(boolean lastMoved) { this.lastMoved = lastMoved; }
	
	public boolean isTakenTesting() { return takenTesting; }
	public void setTakenTesting(boolean takenTesting) { this.takenTesting = takenTesting; }

	public static void setMasterPanel(JPanel panel) { masterPanel = panel; }
	
	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Constructor for the GamePiece class
	 * @param x The x-coordinate the piece is initialized to
	 * @param y The y-coordinate the piece is initialized to
	 * @param team The team which the piece belongs to
	 */
	public GamePiece(int x, int y, boolean team) {
		this.x = x;
		this.y = y;
		this.team = team;
		takenTesting = false;
		lastMoved = false;
		moved = 0;
	}
	
	/**
	 * Gets the Unicode character that represents this GamePiece. To be overridden in sub-classes. 
	 * @return The Unicode character that represents this GamePiece
	 */
	public abstract String getChar();
	
	/**
	 * Gets all of the GameMoves that this GamePiece can make. To be overridden in sub-classes. Note that this returns GameMoves that may cause a player to put themselves in check (see getLegalMoves).  
	 * @return All of the GameMoves that this GamePiece can make.
	 */
	public abstract List<GameMove> getMoves();
	
	/**
	 * Gets all of the GameMoves that this GamePiece can make that does not result in the player putting themselves in check. 
	 * @return All of the legal GameMoves that this GamePiece can make.
	 */
	public List<GameMove> getLegalMoves() {
		int startX = x;
		int startY = y;
		List<GameMove> moves = getMoves();
		List<GameMove> legalMoves = new ArrayList<GameMove>();

		if (moves != null) {
			for (GameMove move : moves) {
				board.testTransform(this, move);
				board.repaint();
				for (int i = Integer.MIN_VALUE; i < Integer.MAX_VALUE; i++) ;
				if (!board.testCheck(team))
					legalMoves.add(move);
				x = startX;
				y = startY;
			}
		}
		return legalMoves;
	}

	/**
	 * Gets the position on the screen to draw this piece using the given spaceSize parameter
	 * @param spaceSize the size in pixels of each space
	 * @return The Point at which to draw this GamePiece on screen
	 */
	public Point getBoardPos(int spaceSize) {
		Point p = new Point (0, 0);
		p.x = x * spaceSize;
		p.y = y * spaceSize + spaceSize;
		return p;
	}
	
	public boolean posEquals(int x, int y) {
		return x == this.x && y == this.y;
	}
	
	public boolean posEquals(GamePiece other) {
		return other.x == this.x && other.y == this.y;
	}
	
	public List<GameMove> addMove(List<GameMove> moves, GameMove move) {
		int target = board.getPieceRelTo(this, move);
		if (target == -1 || board.pieces[target].team != team)
			moves.add(move);
		return moves;
	}
	
	@Override
	public String toString() {
		return String.format("%s at (%d, %d)", getClass().getSimpleName(), x, y);
	}
}