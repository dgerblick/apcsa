package kareltherobot;

import kareltherobot.World;

public class GroceryShopper_DanielGerblick implements Directions {
	
	public static void main(String[] args) {
		World.setVisible(true);
		World.readWorld("worldFiles\\groceries.txt");
		World.showSpeedControl(true);
		
		UrRobot phineas = new UrRobot(5, 7, East, 0);
		
		phineas.turnLeft();
		phineas.turnLeft();
		phineas.move();
		phineas.move();
		
		for (int i = 0; i < 2; i++) {
			phineas.pickBeeper();
			phineas.move();
			for (int j = 0; j < 3; j++)
				phineas.turnLeft();
			phineas.move();
			if (i != 1)
				phineas.turnLeft();
		}
		phineas.pickBeeper();
		phineas.move();
		phineas.pickBeeper();
		
		phineas.turnLeft();
		phineas.turnLeft();
		
		for (int i = 0; i < 3; i++)
			phineas.move();		
		phineas.turnLeft();
		for (int i = 0; i < 4; i++)
			phineas.move();
	}
}
