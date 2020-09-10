package kareltherobot;

import java.awt.Color;

public class MoveRightRobot_DanielGerblick extends BetterRobot_DanielGerblick {

	public MoveRightRobot_DanielGerblick(int street, int avenue, Direction direction, int beepers, Color color) {
		super(street, avenue, direction, beepers, color);
	}
	public MoveRightRobot_DanielGerblick(int street, int avenue, Direction direction, int beepers) {
		super(street, avenue, direction, beepers);
	}
	
	public void move() {
		super.turnRight();
		super.move();
		super.turnLeft();
	}
}
