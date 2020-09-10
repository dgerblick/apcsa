package chess;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends GamePiece {

	/**
	 * Constructor for Pawn class
	 * @param x The x-coordinate the piece is initialized to
	 * @param team The team which the piece belongs to
	 */
	public Pawn(int x, boolean team) {
		super(x, team ? 6 : 1, team);
		setValue(team ? -1 : 1);
	}
	
	/**
	 * Constructor for Pawn class
	 * @param x The x-coordinate the piece is initialized to
	 * @param y The y-coordinate the piece is initialized to
	 * @param team The team which the piece belongs to
	 */
	public Pawn(int x, int y, boolean team) {
		super(x, y, team);
		setValue(team ? -1 : 1);
	}
	
	@Override
	public List<GameMove> getMoves() {
		List<GameMove> moves = new ArrayList<GameMove>();		
		GameMove move;
		int target;
		
		move = new GameMove(0, getTeam() ? -1 : 1); 
		target = board.getPieceRelTo(this, move);
		if (target == -1) {
			moves.add(move);
			move = new GameMove(0, getTeam() ? -2 : 2);
			target = board.getPieceRelTo(this, move);
			if (target == -1 && getY() == (getTeam() ? 6 : 1))
				moves.add(move);
		}
		
		move = new GameMove(-1, getTeam() ? -1 : 1);
		target = board.getPieceRelTo(this, move);
		if (target != -1 && board.pieces[target].getTeam() != getTeam())
			moves.add(move);
		
		move = new GameMove(1, getTeam() ? -1 : 1);
		target = board.getPieceRelTo(this, move);
		if (target != -1 && board.pieces[target].getTeam() != getTeam())
			moves.add(move);
		
		move = new GameMove(-1, getTeam() ? -1 : 1);
		target = board.getPieceAtPos(getX() - 1, getY());
		if (target != -1 && board.pieces[target].getTeam() != getTeam() && board.pieces[target].getMoved() == 1 && board.pieces[target].isLastMoved() && board.pieces[target] instanceof Pawn && board.pieces[target].getY() == (board.pieces[target].getTeam() ? 4 : 3)) {
			move.specialTarget = board.pieces[target];
			moves.add(move);
		}
		
		move = new GameMove(1, getTeam() ? -1 : 1);
		target = board.getPieceAtPos(getX() + 1, getY());
		if (target != -1 && board.pieces[target].getTeam() != getTeam() && board.pieces[target].getMoved() == 1 && board.pieces[target].isLastMoved() && board.pieces[target] instanceof Pawn && board.pieces[target].getY() == (board.pieces[target].getTeam() ? 4 : 3)) {
			move.specialTarget = board.pieces[target];
			moves.add(move);
		}
		
		return moves;
	}

	@Override
	public String getChar() {
		return getTeam() ? "\u2659" : "\u265f";
	}

}
