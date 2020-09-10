package kareltherobot;

import java.awt.Color;

public class GardenerTester_DanielGerblick implements Directions {

	private static final int NUM_BEEPERS = 7;
	
	public static void main(String args[]) {
		World.setVisible(true);
		World.showSpeedControl(true);
		World.readWorld("worldFiles\\gardenerWorld.txt");
		
		Gardener_DanielGerblick[] gardeners = new Gardener_DanielGerblick[3];
		gardeners[0] = new Gardener_DanielGerblick(5, 9, West, NUM_BEEPERS);
		gardeners[1] = new Gardener_DanielGerblick(9, 6, South, NUM_BEEPERS);
		gardeners[2] = new Gardener_DanielGerblick(6, 2, East, NUM_BEEPERS);
		
		Supervisor_DanielGerblick ratchet = new Supervisor_DanielGerblick(2, 5, North, NUM_BEEPERS, Color.DARK_GRAY, gardeners);
		
		ratchet.placeCorners(3);
	}
}
