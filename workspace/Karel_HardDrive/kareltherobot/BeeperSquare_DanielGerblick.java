package kareltherobot;

import java.awt.Color;

public class BeeperSquare_DanielGerblick extends SmartBot_DanielGerblick{

	public BeeperSquare_DanielGerblick(int street, int avenue, Direction direction, int beepers) {
		super(street, avenue, direction, beepers);
	}

	public BeeperSquare_DanielGerblick(int street, int avenue, Direction direction, int beepers, Color color) {
		super(street, avenue, direction, beepers, color);
	}
	
	public void drawSquare() {
		drawSquare(6);
	}
	
	public void drawSquare(int size) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < size; j++) {
				move();
				putBeeper();
			}
			turnLeft();
		}
	}

}
