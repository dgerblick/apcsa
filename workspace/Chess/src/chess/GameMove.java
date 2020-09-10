package chess;

public class GameMove {

	public int deltaX;
	public int deltaY;
	
	public GamePiece target;
	public GamePiece specialTarget;
	
	/**
	 * Constructor for the GameMove class
	 * @param deltaX The amount in the x-direction by which a piece would be moved 
	 * @param deltaY The amount in the y-direction by which a piece would be moved 
	 */
	public GameMove(int deltaX, int deltaY) {
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}
	
	/**
	 * Constructor for the GameMove class. Use this Constructor exclusively for castling.
	 * @param king The King to be castled
	 * @param piece The piece which the King will castle with 
	 */
	public GameMove(King king, GamePiece piece) {
		this.deltaX = piece.getX() > king.getX() ? 2 : -2;
		this.deltaY = 0;
	}
	
	/**
	 * Gives the new x-position of a piece that starts with a given x-position x
	 * @param x The starting x-position of a piece
	 * @return The new x-position for a piece
	 */
	public int getX(int x) {
		return x + deltaX;
	}
	
	/**
	 * Gives the new y-position of a piece that starts with a given x-position x
	 * @param y The starting y-position of a piece
	 * @return The new y-position for a piece
	 */
	public int getY(int y) {
		return y + deltaY;
	}
	
	@Override
	public String toString() {
		return String.format("(%+d, %+d)", deltaX, deltaY);
	}
}
