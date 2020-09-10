package chess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.lang.invoke.SwitchPoint;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Board extends JPanel implements MouseListener {

	private static final long serialVersionUID = -1080752877644144455L;
	private final Color c1 = new Color(51, 204, 255);
	private final Color c2 = new Color(204, 255, 255);
	private final Color c3 = new Color(255, 255, 0, 100);
	
	public int spaceSize;
	public GamePiece[] pieces;
	private int active;
	private List<GameMove> moves;
	public JLabel display;
	public boolean turn;
	private int pawnUpgrade;
	private Image img;
	private int displaymode;
	
	/**
	 * Constructor for the Board class
	 * @param label
	 * @param spaceSize
	 */
	public Board(JLabel label, int spaceSize) {
		super();
		this.spaceSize = spaceSize;
		displaymode = 0;
		pawnUpgrade = -1;
		display = label;
		display.setText("White's turn");

		setPreferredSize(new Dimension(spaceSize * 8, spaceSize * 8));
		display.setSize(getWidth(), spaceSize);
		turn = true;
		active = -1;
		addMouseListener(this);
		GamePiece.board = this;
	}
	
	/**
	 * Gets the corresponding name for the given display value 
	 * @param display The display value to get the name for
	 * @return The same for the given display value
	 */
	public String getDisplayText(int display) {
		switch (display) {
		case 0:
			return "Standard";
		case 1:
			return "Fancy";
		case 2:
			return "\"Realistic\"";
		case 3:
			return "Invisible";
		case 4:
			return "Minecraft";
		case 5:
			return "Endgame Spoilers";
		case 6:
			return "This is where the fun begins";
		case 7:
			return "Smith";
		default:
			return "";
		}
	}
	
	public int getDisplayMode() { return displaymode; }
	public void setDisplayMode(int displaymode) {
		this.displaymode = displaymode;
		try {
			if (displaymode == 2)
				img = ImageIO.read(new File("resources/pieces.png"));
			else if (displaymode == 4)
				img = ImageIO.read(new File("resources/minecraft.png"));
			else if (displaymode == 5)
				img = ImageIO.read(new File("resources/endgame.png"));
			else if (displaymode == 6)
				img = ImageIO.read(new File("resources/watchthosewristrockets.png"));
			else if (displaymode == 7)
				img = ImageIO.read(new File("resources/totally_normal_pieces.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(c1);
		g.fillRect(0, 0, spaceSize * 8, spaceSize * 8);

		g.setColor(c2);
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j += 2)
				g.fillRect(spaceSize * (j + i % 2), spaceSize * i, spaceSize, spaceSize);

		if (active != -1) {
			g.setColor(c3);
			for (GameMove move : moves)
				g.fillRect(spaceSize * move.getX(pieces[active].getX()), spaceSize * move.getY(pieces[active].getY()), spaceSize, spaceSize);
			g.fillRect(spaceSize * pieces[active].getX(), spaceSize * pieces[active].getY(), spaceSize, spaceSize);
		}

		Point p;
		g.setColor(Color.BLACK);
		g.setFont(g.getFont().deriveFont((float) spaceSize));
		for (GamePiece piece : pieces) {
			p = piece.getBoardPos(spaceSize);
			//g.drawString(piece.getChar(), p.x, p.y);
			drawPieceImg(piece, g);
		}

		if (pawnUpgrade != -1) {
			p = pieces[pawnUpgrade].getBoardPos(spaceSize);
			g.setColor((pieces[pawnUpgrade].getX() + pieces[pawnUpgrade].getY()) % 2 == 0 ? c2 : c1);
			g.fillRect(p.x - spaceSize / 2, p.y - spaceSize * 5, spaceSize * 2, spaceSize * 9);
			g.setColor(c3);
			g.fillRect(p.x - spaceSize / 2, p.y - spaceSize * 5, spaceSize * 2, spaceSize * 9);
			g.setColor(Color.BLACK);
			
			int pX = pieces[pawnUpgrade].x;
			int pY = pieces[pawnUpgrade].y;
			boolean pTeam = pieces[pawnUpgrade].getTeam(); 
			drawPieceImg(pieces[pawnUpgrade], g);
			drawPieceImg(new Queen(pX, pTeam ? pY + 1 : pY - 1, pTeam), g);
			drawPieceImg(new Rook(pX, pTeam ? pY + 2 : pY - 2, pTeam), g);
			drawPieceImg(new Bishop(pX, pTeam ? pY + 3 : pY - 3, pTeam), g);
			drawPieceImg(new Knight(pX, pTeam ? pY + 4 : pY - 4, pTeam), g);
		}
	}

	/**
	 * Draws the given piece on the board using the current display mode
	 * @param piece The piece to draw
	 * @param g The graphics object to use to draw the piece
	 */
	private void drawPieceImg(GamePiece piece, Graphics g) {
		Point p = piece.getBoardPos(spaceSize);
		if (displaymode == 0) {
			g.setFont(new Font("Segoe UI Symbol", Font.PLAIN, spaceSize));
			g.drawString(piece.getChar(), p.x, p.y);
		} else if (displaymode == 1) {
			g.setFont(new Font("MS Gothic", Font.PLAIN, spaceSize));
			g.drawString(piece.getChar(), p.x, p.y - spaceSize / 8);
		} else if (displaymode == 3) {
		} else {
			int iWidth = img.getWidth(null) / 6;
			int iHeight = img.getHeight(null) / 2;
			int imgX = iWidth;
			int imgY = piece.getTeam() ? 0 : iHeight;
			if (piece instanceof DeadPiece)
				return;
			else if (piece instanceof King)
				imgX = 0;
			else if (piece instanceof Queen)
				imgX = iWidth;
			else if (piece instanceof Bishop)
				imgX = iWidth * 2;
			else if (piece instanceof Knight)
				imgX = iWidth * 3;
			else if (piece instanceof Rook)
				imgX = iWidth * 4;
			else if (piece instanceof Pawn)
				imgX = iWidth * 5;
			g.drawImage(img, p.x, p.y- spaceSize, p.x + spaceSize, p.y , imgX, imgY, imgX + iWidth, imgY + iHeight, null);
			//g.drawImage(img, spaceSize * i, spaceSize * j + spaceSize * 2, spaceSize * i + spaceSize, spaceSize * j + spaceSize * 3, pWidth * i, pHeight * j,  pWidth * i + pWidth, pHeight * j + pHeight, null);
		}
	}

	/**
	 * Gets the piece at the specified position
	 * @param x The x-coordinate of the piece
	 * @param y The x-coordinate of the piece
	 * @return The index of the piece at the specified position in the pieces array. If there is no piece at the specified position, a value of -1 is returned.
	 */
	public int getPieceAtPos(int x, int y) {
		for (int i = 0; i < pieces.length; i++)
			if (pieces[i].posEquals(x, y) && !pieces[i].isTakenTesting())
				return i;

		return -1;
	}

	/**
	 * Gets the piece at the specified location relative to another piece.
	 * @param piece The piece that serves at the starting point for the search
	 * @param move The move that would be made by the piece parameter to move it to the location that is being searched
	 * @return The index of the piece at the specified position in the pieces array. If there is no piece at the specified position, a value of -1 is returned.
	 */
	public int getPieceRelTo(GamePiece piece, GameMove move) {
		int x = move.getX(piece.getX());
		int y = move.getY(piece.getY());
		return getPieceAtPos(x, y);
	}

	/**
	 * Gets the index of a given piece in the pieces array
	 * @param piece The piece to find the index of in the pieces array 
	 * @return The index of the given piece in the pieces array. If the given piece is not in the array, a value of -1 is returned.
	 */
	public int getPieceIndex(GamePiece piece) {
		if (piece != null) {
			for (int i = 0; i < pieces.length; i++)
				if (pieces[i].posEquals(piece))
					return i;
		}
		return -1;
	}

	/**
	 * Checks to see if the current board layout has the specified player in check
	 * @param team The player that could be in check 
	 * @return True if player team is in check, False if they are not in check
	 */
	public boolean testCheck(boolean team) {
		for (GamePiece piece : pieces) {
			if (piece.getTeam() != team && !piece.isTakenTesting()) {
				List<GameMove> moves = piece.getMoves();
				if (moves != null) {
					for (GameMove move : moves) {
						int target = getPieceRelTo(piece, move);
						if (target != -1 && pieces[target].getTeam() == team && pieces[target] instanceof King)
							return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * Checks to see if the current board layout has the specified player in checkmate
	 * @param team The player that could be in checkmate
	 * @return True if player team is in checkmate, False if they are not in checkmate
	 */
	public boolean testCheckmate(boolean team) {
		for (GamePiece piece : pieces) {
			if (piece.getTeam() == team) {
				if (!piece.getLegalMoves().isEmpty())
					return false;
			}
		}

		return true;
	}
	
	/**
	 * Moves the given piece by the given move
	 * @param piece The piece to move
	 * @param move The move for the piece to take
	 */
	public void transform(GamePiece piece, GameMove move) {
		if (piece instanceof King && move.specialTarget != null) {
			piece.setPos(piece.getX() + move.deltaX, piece.getY());
			move.specialTarget.setPos(move.specialTarget.getX() + (move.deltaX > 0 ? -2 : 3),
					move.specialTarget.getY());

			piece.incrementMoved();
		} else {
			int index = getPieceRelTo(piece, move);
			if (index != -1 && pieces[index].getTeam() != piece.getTeam())
				pieces[index] = new DeadPiece(pieces[index]);

			piece.setPos(move.getX(piece.getX()), move.getY(piece.getY()));

			piece.incrementMoved();
			piece.setLastMoved(true);
		}

		if (piece instanceof Pawn) {
			if (move.specialTarget != null) {
				pieces[getPieceIndex(move.specialTarget)] = new DeadPiece(move.specialTarget);
				piece.incrementMoved();
			}
			if (piece.getY() == (piece.getTeam() ? 0 : 7)) {
				char c = piece.getTeam() ? '\u265b' : '\u2655';
				char[] options = new char[5];
				for (int i = 0; i < options.length; i++)
					options[i] = (char) (c + i);
				pawnUpgrade = getPieceIndex(piece);
			}
		}
	}

	/**
	 * Moves the given piece by the given move. If a piece is captured, the takenTesting flag is set to true for the taken piece. This should only be used for checking to see if certain moves cause a player to be put into check.
	 * @param piece The piece to move
	 * @param move The move for the piece to take
	 */
	public void testTransform(GamePiece piece, GameMove move) {
		int index = getPieceRelTo(piece, move);
		for (int i = 0; i < pieces.length; i++)
			pieces[i].setTakenTesting(i == index);
		piece.setPos(move.getX(piece.getX()), move.getY(piece.getY()));

	}

	/**
	 * Updates the text in the display label with information regarding whose turn it is and any action that need to be taken by the player
	 */
	private void updateText() {
		if (pawnUpgrade != -1) {
			display.setText("Please select Pawn Upgrade");
		} else {
			display.setText((turn ? "White" : "Black") + "'s turn");

			if (testCheck(turn)) {
				display.setText("Check! " + display.getText());
				if (testCheckmate(turn))
					display.setText(String.format("Checkmate! %s wins!", turn ? "Black" : "White"));
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent me) {
		int clickX = me.getX() / spaceSize;
		int clickY = me.getY() / spaceSize;

		if (pawnUpgrade != -1) {
			if (Math.abs(pieces[pawnUpgrade].getX() - clickX) <= 1) {
				switch (Math.abs(pieces[pawnUpgrade].getY() - clickY)) {
				case 1:
					pieces[pawnUpgrade] = new Queen(pieces[pawnUpgrade].getX(), pieces[pawnUpgrade].getY(), pieces[pawnUpgrade].getTeam());
					pawnUpgrade = -1;
					break;
				case 2:
					pieces[pawnUpgrade] = new Rook(pieces[pawnUpgrade].getX(), pieces[pawnUpgrade].getY(), pieces[pawnUpgrade].getTeam());
					pawnUpgrade = -1;
					break;
				case 3:
					pieces[pawnUpgrade] = new Bishop(pieces[pawnUpgrade].getX(), pieces[pawnUpgrade].getY(), pieces[pawnUpgrade].getTeam());
					pawnUpgrade = -1;
					break;
				case 4:
					pieces[pawnUpgrade] = new Knight(pieces[pawnUpgrade].getX(), pieces[pawnUpgrade].getY(), pieces[pawnUpgrade].getTeam());
					pawnUpgrade = -1;
					break;
				default:
					return;
				}
			}
			updateText();
		}
		for (int i = 0; i < pieces.length; i++) {
			pieces[i].setTakenTesting(false);
		}

		if (active == -1) {
			active = getPieceAtPos(clickX, clickY);
			if (active != -1 && pieces[active].getTeam() == turn)
				moves = pieces[active].getLegalMoves();
			else
				active = -1;
		} else {
			for (GameMove move : moves) {
				if (move.getX(pieces[active].getX()) == clickX && move.getY(pieces[active].getY()) == clickY) {
					for (int i = 0; i < pieces.length; i++)
						pieces[i].setLastMoved(false);
					transform(pieces[active], move);

					turn = !turn;
					updateText();
					if (testCheckmate(turn))
						this.removeMouseListener(this);
					break;
				}
			}
			active = getPieceAtPos(clickX, clickY);
			if (active != -1 && pieces[active].getTeam() == turn)
				moves = pieces[active].getLegalMoves();
			else
				active = -1;
		}
		repaint();
	}

	@Override public void mouseEntered(MouseEvent me) {}
	@Override public void mouseExited(MouseEvent me) { }
	@Override public void mousePressed(MouseEvent me) {}
	@Override public void mouseReleased(MouseEvent me) {}
}
