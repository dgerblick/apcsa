package kareltherobot;

import java.awt.Color;

public class DoubleFigureEights_DanielGerblick implements Directions {
	public static void main(String[] args) {
		World.setVisible(true);
		World.readWorld("worldFiles\\doubleFigureEightsWorld.txt");
		World.showSpeedControl(true);
		//World.setBeeperColor(Color.RED);
		World.setWorldColor(Color.WHITE);
		
		UrRobot fender = new UrRobot(5, 3, North, 0, Color.red);
		UrRobot rodney = new UrRobot(5, 8, North, 0, Color.blue);

		fender.pickBeeper();
		rodney.pickBeeper();
		
		fender.turnLeft();
		for (int i = 0; i < 3; i++)
			rodney.turnLeft();
		
		fender.move();
		rodney.move();
		fender.pickBeeper();
		rodney.pickBeeper();
		
		for (int i = 0; i < 3; i++)
			fender.turnLeft();
		rodney.turnLeft();
		
		for (int i = 0; i < 2; i++) {
			fender.move();
			rodney.move();
			
			fender.pickBeeper();
			rodney.pickBeeper();
		}
	
		for (int i = 0; i < 3; i++)
			fender.turnLeft();
		rodney.turnLeft();
		
		for (int i = 0; i < 2; i++) {
			fender.move();
			rodney.move();
			
			fender.pickBeeper();
			rodney.pickBeeper();
		}
		
		fender.turnLeft();
		for (int i = 0; i < 3; i++)
			rodney.turnLeft();
		
		for (int i = 0; i < 2; i++) {
			fender.move();
			rodney.move();
			
			fender.pickBeeper();
			rodney.pickBeeper();
		}
		
		for (int i = 0; i < 3; i++) {
			fender.turnLeft();
			for (int j = 0; j < 3; j++)
				rodney.turnLeft();
			
			for (int j = 0; j < 2; j++) {
				fender.move();
				rodney.move();
				
				fender.pickBeeper();
				rodney.pickBeeper();
			}
		}
		
		for (int i = 0; i < 3; i++)
			fender.turnLeft();
		rodney.turnLeft();
		
		for (int i = 0; i < 2; i++) {
			fender.move();
			rodney.move();
			
			fender.pickBeeper();
			rodney.pickBeeper();
		}
		
		for (int i = 0; i < 3; i++)
			fender.turnLeft();
		rodney.turnLeft();
		
		fender.move();
		rodney.move();
		fender.pickBeeper();
		rodney.pickBeeper();
		
		for (int i = 0; i < 3; i++)
			fender.turnLeft();
		rodney.turnLeft();
		
	}
}
