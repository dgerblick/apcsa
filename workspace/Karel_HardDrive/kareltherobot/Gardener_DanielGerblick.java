package kareltherobot;

import java.awt.Color;

public class Gardener_DanielGerblick extends BetterRobot_DanielGerblick {

	public Gardener_DanielGerblick(int street, int avenue, Direction direction, int beepers) {
		super(street, avenue, direction, beepers);
	}
	public Gardener_DanielGerblick(int street, int avenue, Direction direction, int beepers, Color color) {
		super(street, avenue, direction, beepers, color);
	}
	
	public void plantL(int size) {
		plantRow(size);
		turnLeft();
		plantRow(size + 1);
	}
}
