package chess;

import java.util.List;

public class DeadPiece extends GamePiece {

	/**
	 * Constructor for the DeadPiece class
	 * @param piece The piece that this object will replace
	 */
	public DeadPiece(GamePiece piece) {
		super(-1, -1, piece.getTeam());
		setValue(0);
	}

	@Override
	public String getChar() {
		return "";
	}

	@Override
	public List<GameMove> getMoves() {
		return null;
	}

}
