package kareltherobot;

import java.awt.Color;

public class DiamondPlanter_DanielGerblick extends BetterRobot_DanielGerblick {

	public DiamondPlanter_DanielGerblick(int street, int avenue, Direction direction, int beepers) {
		super(street, avenue, direction, beepers);
	}
	public DiamondPlanter_DanielGerblick(int street, int avenue, Direction direction, int beepers, Color color) {
		super(street, avenue, direction, beepers, color);
	}
	
	public void plantDiamond(int size) {
		turnRight();
		move(size + 1);
		
		for (int i = 1; i <= size; i++) {
			plantRow(i);
			turnAround();
			uTurn(i % 2);
			move();
		}
		move(2);
		for (int i = size - 1; i > 0; i--) {
			plantRow(i);
			turnAround();
			uTurn(i % 2);
			move(3);
		}
		
		turnLeft();
		move(size);
		turnLeft();
		move(size + 1);
		turnLeft();
	}
	
	private void uTurn(int dir) {
		if (dir == 1) {
			turnRight();
			move();
			turnLeft();
		} else {
			turnLeft();
			move();
			turnRight();
		}
	}
	
	public void plantRow(int length) {
		for (int i = 0; i < length; i++) {
			putBeeper();
			move(2);
		}
	}
}
