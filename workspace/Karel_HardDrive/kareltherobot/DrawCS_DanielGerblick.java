package kareltherobot;

import kareltherobot.*;

public class DrawCS_DanielGerblick implements Directions {
	public static void main(String[] args) {
		//Setup
		World.setVisible(true);
		World.showSpeedControl(true);
		boolean[][] goal = getGoal();
		UrRobot karel = new UrRobot(1, 1, North, infinity);
		
		int STREETS = goal.length;
		int AVENUES = goal[0].length;
		
		//Place Beepers
		for (int i = 0; i < AVENUES; i++) {
			for (int j = 0; j < STREETS; j++) {
				if (goal[karel.street() - 1][karel.avenue() - 1])
					karel.putBeeper();
				if (j != STREETS - 1)
					karel.move();
			}
			//Return
			karel.turnLeft();
			karel.turnLeft();
			for (int j = 0; j < STREETS - 1; j++)
				karel.move();
			karel.turnLeft();
			karel.move();
			karel.turnLeft();
		}
		
		//Return to start
		karel.turnLeft();
		for (int i = 0; i < AVENUES; i++)
			karel.move();
		for (int i = 0; i < 3; i++)
			karel.turnLeft();
		
		//Remove Beepers
		for (int i = 0; i < AVENUES; i++) {
			for (int j = 0; j < STREETS; j++) {
				if (goal[karel.street() - 1][karel.avenue() - 1])
					karel.pickBeeper();
				if (j != STREETS - 1)
					karel.move();
			}
			//Return
			karel.turnLeft();
			karel.turnLeft();
			for (int j = 0; j < STREETS - 1; j++)
				karel.move();
			karel.turnLeft();
			karel.move();
			karel.turnLeft();
		}
		//Return to start
		karel.turnLeft();
		for (int i = 0; i < AVENUES; i++)
			karel.move();
		for (int i = 0; i < 3; i++)
			karel.turnLeft();
		
		karel.turnOff();
	}
	
	private static boolean[][] getGoal() {
		String[] strGrid = {
			" ... ...",
			" .   .  ",
			" .   ...",
			" .     .",
			" ... ...",
			"        ",
		};
		
		boolean grid[][] = new boolean[strGrid.length][strGrid[0].length()];
		for (int i = 0; i < strGrid.length; i++)
			for (int j = 0; j < strGrid[0].length(); j++)
				grid[grid.length - i - 1][j] = strGrid[i].charAt(j) == '.';
		
		return grid;
	}
}
