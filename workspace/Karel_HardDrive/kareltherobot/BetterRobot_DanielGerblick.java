package kareltherobot;

import java.awt.Color;

public class BetterRobot_DanielGerblick extends Robot {

	public BetterRobot_DanielGerblick(int street, int avenue, Direction direction, int beepers) {
		super(street, avenue, direction, beepers);
	}
	
	public BetterRobot_DanielGerblick(int street, int avenue, Direction direction, int beepers, Color color) {
		super(street, avenue, direction, beepers, color);
	}
	
	// Required Methods
	public void turnRight() {
		for (int i = 0; i < 3; i++)
			turnLeft();
	}
	
	public void moveBackwards(int distance) {
		turnAround();
		move(distance);
		turnAround();
	}
	public void moveBackwards() {
		moveBackwards(1);
	}
	
	public void turnAround() {
		turnLeft();
		turnLeft();
	}
	
	public void moveMile() {
		move(8);
	}
	public void moveMile(int miles) {
		move(8 * miles);
	}
	
	public void moveDecaMile() {
		moveMile(10);
	}
	public void moveDecaMile(int decaMiles) {
		moveMile(10 * decaMiles);
	}

	public void plantRow(int size) {
		for (int i = 0; i < size; i++) {
			putBeeper();
			move();
		}
	}
	
	// Extra Methods
	public void move(int distance) {
		for (int i = 0; i < distance; i++)
			move();
	}
}
