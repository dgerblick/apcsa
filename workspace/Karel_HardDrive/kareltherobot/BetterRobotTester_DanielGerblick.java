package kareltherobot;

import kareltherobot.*;
import java.awt.Color;

public class BetterRobotTester_DanielGerblick implements Directions {

	public static void main(String[] args) {
		World.setVisible(true);
		World.showSpeedControl(true);
		World.setSize(100, 100);
		
		// whoa
		BetterRobot_DanielGerblick betty = new BetterRobot_DanielGerblick(1, 1, North, 1, Color.BLACK);
		// bam ba lam
		
		betty.turnRight();
		betty.move(10);
		betty.moveBackwards(4);
		betty.moveMile();
		betty.turnLeft();
		betty.moveDecaMile();
		betty.turnAround();
		betty.moveMile(7);
		betty.putBeeper();
		betty.moveBackwards(8);
		betty.move(8);
		betty.pickBeeper();
		betty.turnOff();
	}

}
