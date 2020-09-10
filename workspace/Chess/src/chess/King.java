package chess;

import java.util.ArrayList;
import java.util.List;

public class King extends GamePiece {
	
	/**
	 * Constructor for King class
	 * @param team The team which the piece belongs to
	 */
	public King(boolean team) {
		super(4, team ? 7 : 0, team);
		setValue(0);
	}
	
	/**
	 * Constructor for King class
	 * @param x The x-coordinate the piece is initialized to
	 * @param y The y-coordinate the piece is initialized to
	 * @param team The team which the piece belongs to
	 */
	public King(int x, int y, boolean team) {
		super(x, y, team);
		setValue(0);
	}
	
	@Override
	public List<GameMove> getMoves() {
		List<GameMove> moves = new ArrayList<GameMove>();		
		GameMove move;
		
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i != 0 || j != 0) {
					move = new GameMove(i, j);
					int newX = move.getX(getX());
					int newY = move.getY(getY());
					int target = board.getPieceAtPos(newX, newY);
					if (newX <= 7 && newX >= 0 && newY >= 0 && newY <= 7 && (target == -1 || board.pieces[target].getTeam() != getTeam()))
						moves.add(move);
				}
			}
		}
		
		if (getMoved() == 0) {
			List<GamePiece> castlePieces = new ArrayList<GamePiece>();
			
			int[] xPos = { 0, 7 };
			GamePiece piece;
			for (int x : xPos) {
				int target = board.getPieceAtPos(x, getY());
				if (target != -1 && board.pieces[target].getMoved() == 0)
					castlePieces.add(board.pieces[target]);
			}			
			
			for (int i = 0; i < castlePieces.size(); i++) {
				if (castlePieces.get(i) instanceof Rook && isClear(castlePieces.get(i))) {
					GameMove castle = new GameMove(this, castlePieces.get(i));
					castle.specialTarget = castlePieces.get(i);
					moves.add(castle);
				}
			}
		}
		
		return moves;
	}
	
	private boolean isClear(GamePiece target) {
		if (getX() < target.getX()) {
			for (int i = getX() + 1; i < target.getX(); i++)
				if (board.getPieceAtPos(i, getY()) != -1)
					return false;
		} else {
			for (int i = getX() - 1; i > target.getX(); i--)
				if (board.getPieceAtPos(i, getY()) != -1)
					return false;
		}
		return true;
	}
	
	@Override
	public String getChar() {
		return getTeam() ? "\u2654" : "\u265a";
	}
}