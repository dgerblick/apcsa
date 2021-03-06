package kareltherobot;

import java.awt.Color;

public class CarpetLayer_DanielGerblick extends SmartBot_DanielGerblick {

	public CarpetLayer_DanielGerblick(int street, int avenue, Direction direction, int beepers) {
		super(street, avenue, direction, beepers);
	}

	public CarpetLayer_DanielGerblick(int street, int avenue, Direction direction, int beepers, Color color) {
		super(street, avenue, direction, beepers, color);
	}

	
	public void layCarpet() {
		int roomLen = 1;
		boolean room = false;
		
		move();
		turnLeft();
		move();
		while (!leftIsClear() && !rightIsClear()) {
			roomLen++;
			if (frontIsClear()) {
				move();
			} else {
				roomLen--;
				room = true;
				break;
			}
		}
		turnAround();
		for (int i = 0; i < roomLen; i++) {
			if (room)
				putBeeper();
			move();
		}
		turnLeft();
	}
}
