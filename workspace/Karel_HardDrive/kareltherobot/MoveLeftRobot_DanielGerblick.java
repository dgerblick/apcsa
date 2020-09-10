package kareltherobot;

import java.awt.Color;

public class MoveLeftRobot_DanielGerblick extends BetterRobot_DanielGerblick {

	public MoveLeftRobot_DanielGerblick(int street, int avenue, Direction direction, int beepers, Color color) {
		super(street, avenue, direction, beepers, color);
	}
	public MoveLeftRobot_DanielGerblick(int street, int avenue, Direction direction, int beepers) {
		super(street, avenue, direction, beepers);
	}
	
	public void move() {
		super.turnLeft();
		super.move();
		super.turnRight();
	}
}
