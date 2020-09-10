package chess;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends GamePiece {
	
	/**
	 * Constructor for Bishop class
	 * @param right If true, the piece is initialized to the right side of the board. Piece is initialized to the left side if false.
	 * @param team The team which the piece belongs to
	 */
	public Bishop(boolean right, boolean team) {
		super(right ? 5 : 2, team ? 7 : 0, team);
		setValue(team ? -3 : 3);
	}
	
	/**
	 * Constructor for Bishop class
	 * @param x The x-coordinate the piece is initialized to
	 * @param y The y-coordinate the piece is initialized to
	 * @param team The team which the piece belongs to
	 */
	public Bishop(int x, int y, boolean team) {
		super(x, y, team);
		setValue(team ? -3 : 3);
	}

	@Override
	public List<GameMove> getMoves() {
		List<GameMove> moves = new ArrayList<GameMove>();		
		GameMove move;
		int c;
		
		c = 1;
		while (getX() + c < 8 && getY() + c < 8) {
			move = new GameMove(c, c);
			addMove(moves, move);
			if (board.getPieceRelTo(this, move) != -1)
				break;
			c++;
		}
		
		c = 1;
		while (getX() + c < 8 && getY() - c >= 0) {
			move = new GameMove(c, -c);
			addMove(moves, move);
			if (board.getPieceRelTo(this, move) != -1)
				break;
			c++;
		}
		
		c = 1;
		while (getX() - c >= 0 && getY() + c < 8) {
			move = new GameMove(-c, c);
			addMove(moves, move);
			if (board.getPieceRelTo(this, move) != -1)
				break;
			c++;
		}
		
		c = 1;
		while (getX() - c >= 0 && getY() - c >= 0) {
			move = new GameMove(-c, -c);
			addMove(moves, move);
			if (board.getPieceRelTo(this, move) != -1)
				break;
			c++;
		}
		
		return moves;
	}

	@Override
	public String getChar() {
		return getTeam() ? "\u2657" : "\u265d";
	}

}
