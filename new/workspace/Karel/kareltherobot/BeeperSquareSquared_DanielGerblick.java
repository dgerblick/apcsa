package kareltherobot;

import java.awt.Color;

public class BeeperSquareSquared_DanielGerblick extends BeeperSquare_DanielGerblick {

	public BeeperSquareSquared_DanielGerblick(int street, int avenue, Direction direction, int beepers) {
		super(street, avenue, direction, beepers);
	}

	public BeeperSquareSquared_DanielGerblick(int street, int avenue, Direction direction, int beepers, Color color) {
		super(street, avenue, direction, beepers, color);
	}
	
	public void drawBigSquare() {
		drawBigSquare(5, 6);
	}
	
	public void drawBigSquare(int bigSize, int smallSize) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < bigSize; j++) {
				move(smallSize + 1);
				drawSquare(smallSize);
			}
			move(smallSize);
			turnLeft();
		}
	}

}
