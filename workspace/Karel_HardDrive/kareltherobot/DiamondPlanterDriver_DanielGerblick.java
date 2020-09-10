package kareltherobot;

public class DiamondPlanterDriver_DanielGerblick implements Directions {
	public static void main(String[] args) {
		World.setVisible(true);
		World.showSpeedControl(true);
		World.setSize(25, 25);

		DiamondPlanter_DanielGerblick rihanna = new DiamondPlanter_DanielGerblick(1, 1, North, infinity);
		
		rihanna.plantDiamond(10);
	}
}
