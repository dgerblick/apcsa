package chess;

import java.util.ArrayList;
import java.util.List;

public class Rook extends GamePiece {
	
	/**
	 * Constructor for Bishop class
	 * @param right If true, the piece is initialized to the right side of the board. Piece is initialized to the left side if false.
	 * @param team The team which the piece belongs to
	 */
	public Rook(boolean right, boolean team) {
		super(right ? 7 : 0, team ? 7 : 0, team);
		setValue(team ? -5 : 5);
	}
	
	/**
	 * Constructor for Rook class
	 * @param x The x-coordinate the piece is initialized to
	 * @param y The y-coordinate the piece is initialized to
	 * @param team The team which the piece belongs to
	 */
	public Rook(int x, int y, boolean team) {
		super(x, y, team);
		setValue(team ? -5 : 5);
	}
	
	@Override
	public List<GameMove> getMoves() {
		List<GameMove> moves = new ArrayList<GameMove>();		
		GameMove move;
		
		for (int i = getX() + 1; i < 8; i++) {
			move = new GameMove(i - getX(), 0);
			addMove(moves, move);
			if (board.getPieceRelTo(this, move) != -1)
				break;
		}	
		
		for (int i = getY() + 1; i < 8; i++) {
			move = new GameMove(0, i - getY());
			addMove(moves, move);
			if (board.getPieceRelTo(this, move) != -1)
				break;
		}
		
		for (int i = getX() - 1; i >= 0; i--) {
			move = new GameMove(i - getX(), 0);
			addMove(moves, move);
			if (board.getPieceRelTo(this, move) != -1)
				break;
		}
		
		for (int i = getY() - 1; i >= 0; i--) {
			move = new GameMove(0, i - getY());
			addMove(moves, move);
			if (board.getPieceRelTo(this, move) != -1)
				break;
		}
		
		return moves;
	}

	@Override
	public String getChar() {
		return getTeam() ? "\u2656" : "\u265c";
	}

}
