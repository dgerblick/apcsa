package chess;

import java.util.ArrayList;
import java.util.List;

public class Queen extends GamePiece {

	/**
	 * Constructor for Queen class
	 * @param team The team which the piece belongs to
	 */
	public Queen(boolean team) {
		super(3, team ? 7 : 0, team);
		setValue(team ? -9 : 9);
	}
	
	/**
	 * Constructor for Pawn class
	 * @param x The x-coordinate the piece is initialized to
	 * @param y The y-coordinate the piece is initialized to
	 * @param team The team which the piece belongs to
	 */
	public Queen(int x, int y, boolean team) {
		super(x, y, team);
		setValue(team ? -9 : 9);
	}
	
	@Override
	public List<GameMove> getMoves() {
		List<GameMove> moves = new ArrayList<GameMove>();		
		GameMove move;
		int c;
		
		c = 1;
		while (getX() + c < 8 && getY() + c < 8) {
			move = new GameMove(c, c);
			int target = board.getPieceRelTo(this, move);
			if (target != -1) {
				if (board.pieces[target].getTeam() != getTeam())
					moves.add(move);
				break;
			}
			moves.add(move);
			c++;
		}
		
		c = 1;
		while (getX() + c < 8 && getY() - c >= 0) {
			move = new GameMove(c, -c);
			int target = board.getPieceRelTo(this, move);
			if (target != -1) {
				if (board.pieces[target].getTeam() != getTeam())
					moves.add(move);
				break;
			}
			moves.add(move);
			c++;
		}
		
		c = 1;
		while (getX() - c >= 0 && getY() + c < 8) {
			move = new GameMove(-c, c);
			int target = board.getPieceRelTo(this, move);
			if (target != -1) {
				if (board.pieces[target].getTeam() != getTeam())
					moves.add(move);
				break;
			}
			moves.add(move);
			c++;
		}
		
		c = 1;
		while (getX() - c >= 0 && getY() - c >= 0) {
			move = new GameMove(-c, -c);
			int target = board.getPieceRelTo(this, move);
			if (target != -1) {
				if (board.pieces[target].getTeam() != getTeam())
					moves.add(move);
				break;
			}
			moves.add(move);
			c++;
		}
		
		for (int i = getX() + 1; i < 8; i++) {
			move = new GameMove(i - getX(), 0);
			int target = board.getPieceRelTo(this, move);
			if (target != -1) {
				if (board.pieces[target].getTeam() != getTeam())
					moves.add(move);
				break;
			}
			moves.add(move);
		}	
		
		for (int i = getY() + 1; i < 8; i++) {
			move = new GameMove(0, i - getY());
			int target = board.getPieceRelTo(this, move);
			if (target != -1) {
				if (board.pieces[target].getTeam() != getTeam())
					moves.add(move);
				break;
			}
			moves.add(move);
		}
		
		for (int i = getX() - 1; i >= 0; i--) {
			move = new GameMove(i - getX(), 0);
			int target = board.getPieceRelTo(this, move);
			if (target != -1) {
				if (board.pieces[target].getTeam() != getTeam())
					moves.add(move);
				break;
			}
			moves.add(move);
		}
		
		for (int i = getY() - 1; i >= 0; i--) {
			move = new GameMove(0, i - getY());
			int target = board.getPieceRelTo(this, move);
			if (target != -1) {
				if (board.pieces[target].getTeam() != getTeam())
					moves.add(move);
				break;
			}
			moves.add(move);
		}
		
		return moves;
	}

	@Override
	public String getChar() {
		return getTeam() ? "\u2655" : "\u265b";
	}

}