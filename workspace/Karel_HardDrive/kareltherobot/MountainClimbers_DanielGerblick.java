package kareltherobot;

import kareltherobot.*;
import java.awt.Color;

public class MountainClimbers_DanielGerblick implements Directions {
	public static void main(String[] args) {
		World.setVisible(true);
		World.readWorld("worldFiles\\mountainPeakWorld.txt");
		World.setSize(20, 20);
		World.showSpeedControl(true);
		// World.setBeeperColor(Color.RED);
		// World.setNeutroniumColor(Color.GRAY);
		World.setWorldColor(Color.WHITE);

		UrRobot bigweld = new UrRobot(1, 1, North, 1);
		UrRobot gasket = new UrRobot(1, 16, North, 1);
		
		// bigweld
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++)
				bigweld.turnLeft();
			bigweld.move();
			bigweld.turnLeft();
			bigweld.move();
		}
		bigweld.move();
		for (int i = 0; i < 3; i++)
			bigweld.turnLeft();
		bigweld.move();
		
		bigweld.turnLeft();
		for (int i = 0; i < 3; i++)
			bigweld.move();
		for (int i = 0; i < 3; i++)
			bigweld.turnLeft();
		
		bigweld.move();
		bigweld.putBeeper();
		bigweld.move();
		for (int i = 0; i < 3; i++)
			bigweld.turnLeft();
		
		bigweld.move();
		bigweld.move();
		bigweld.turnLeft();
		
		bigweld.move();
		bigweld.turnLeft();
		bigweld.move();
		
		for (int i = 0; i < 3; i++)
			bigweld.turnLeft();
		bigweld.move();
		bigweld.turnLeft();
		bigweld.turnOff();

		// gasket
		gasket.move();
		gasket.turnLeft();
		gasket.move();
		gasket.move();
		for (int i = 0; i < 3; i++)
			gasket.turnLeft();
		
		gasket.move();
		gasket.move();
		gasket.turnLeft();
		gasket.move();
		gasket.putBeeper();
		gasket.move();
		gasket.turnLeft();
		gasket.move();
		gasket.move();
		
		gasket.turnLeft();
		gasket.turnLeft();
		gasket.move();
		gasket.turnLeft();
		gasket.move();
		for (int i = 0; i < 3; i++)
			gasket.turnLeft();
		gasket.move();
		gasket.turnLeft();
		gasket.move();
		for (int i = 0; i < 3; i++)
			gasket.turnLeft();
		gasket.turnOff();
		
	}
}
