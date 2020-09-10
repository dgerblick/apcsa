package kareltherobot;

import java.awt.Color;

public class DiagonalRight_DanielGerblick extends BetterRobot_DanielGerblick {
	public DiagonalRight_DanielGerblick(int street, int avenue, Direction direction, int beepers, Color color) {
		super(street, avenue, direction, beepers, color);
	}
	public DiagonalRight_DanielGerblick(int street, int avenue, Direction direction, int beepers) {
		super(street, avenue, direction, beepers);
	}
	
	public void move() {
		super.move();
		super.turnRight();
		super.move();
		super.turnLeft();
	}
}
