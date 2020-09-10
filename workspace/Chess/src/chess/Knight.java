package chess;

import java.util.ArrayList;
import java.util.List;

public class Knight extends GamePiece {

	/**
	 * Constructor for Knight class
	 * @param right If true, the piece is initialized to the right side of the board. Piece is initialized to the left side if false.
	 * @param team The team which the piece belongs to
	 */
	public Knight(boolean right, boolean team) {
		super(right ? 6 : 1, team ? 7 : 0, team);
		setValue(team ? -3 : 3);
	}
	
	/**
	 * Constructor for Knight class
	 * @param x The x-coordinate the piece is initialized to
	 * @param y The y-coordinate the piece is initialized to
	 * @param team The team which the piece belongs to
	 */
	public Knight(int x, int y, boolean team) {
		super(x, y, team);
		setValue(team ? -3 : 3);
	}
	
	@Override
	public List<GameMove> getMoves() {
		List<GameMove> moves = new ArrayList<GameMove>();		
		List<GameMove> potenMoves = new ArrayList<GameMove>();
		
		for (int i = -1; i <= 1; i += 2) {
			for (int j = -2; j <= 2; j += 4) {
				potenMoves.add(new GameMove(i, j));
				potenMoves.add(new GameMove(j, i));
			}
		}
		
		for (GameMove move : potenMoves) {
			if (move.getX(getX()) <= 7 && move.getX(getX()) >= 0 && move.getY(getY()) >= 0 && move.getY(getY()) <= 7)
				addMove(moves, move);
		}
		
		return moves;
	}

	@Override
	public String getChar() {
		return getTeam() ? "\u2658" : "\u265e";
	}

}
