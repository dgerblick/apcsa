package kareltherobot;

import java.awt.Color;

public class BeeperSquareSquaredTester_DanielGerblick implements Directions {

	public static void main(String args[]) {
		World.setVisible(true);
		World.setSize(50, 50);
		World.setStreetColor(Color.WHITE);
		World.showSpeedControl(true);
		
		BeeperSquareSquared_DanielGerblick toto = new BeeperSquareSquared_DanielGerblick(1, 1, East, infinity);
		toto.drawBigSquare();
	}	

}
