package kareltherobot;

import java.awt.Color;

import kareltherobot.ChessBot.*;

public class ChessBotDriver implements Directions {

	public static void main(String[] args) {
		World.setVisible(true);
		World.showSpeedControl(true);
		
		ChessBot c = new ChessBot(1, 1, North, 0, PIECE_TYPE.PAWN, TEAM.LIGHT);
	}

}
