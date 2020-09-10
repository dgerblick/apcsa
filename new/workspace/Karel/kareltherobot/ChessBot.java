package kareltherobot;

import java.awt.Color;

public class ChessBot extends SmartBot_DanielGerblick {

	public enum PIECE_TYPE {
		PAWN, ROOK, BISHOP, KNIGHT, QUEEN, KING
	}
	
	public enum TEAM {
		DARK, LIGHT
	}
	
	public ChessBot(int street, int avenue, Direction direction, int beepers, PIECE_TYPE type, TEAM team) {
		super(street, avenue, direction, beepers, getColor(type, team));
	}
	
	private static Color getColor(PIECE_TYPE type, TEAM team) {
		int r = 0;
		int g = 0;
		int b = 0;
		int a = (team == TEAM.DARK) ? 255 : 128;
		
		switch (type) {
		case PAWN:
			r = 255;
			g = 255;
			break;
			
		case ROOK:
			r = 255;
			b = 255;
			break;
			
		case BISHOP:
			g = 255;
			b = 255;
			break;
			
		case KNIGHT:
			g = 255;
			break;
			
		case QUEEN:
			b = 255;
			break;
			
		case KING:
			r = 255;
			break;
			
		default:
			break;
		}
		return new Color(r, g, b, a);
	}

}
