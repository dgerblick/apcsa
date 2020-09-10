package kareltherobot;

public class DrawSquare_DanielGerblick implements Directions {
	
	public final static int SIZE = 5;
	
	public static void main(String[] args) {
		World.setVisible(true);
		World.showSpeedControl(true);
		
		BetterRobot_DanielGerblick piper;
		
		// Bottom Row
		piper = new MoveRightRobot_DanielGerblick(2, 2, North, SIZE);
		piper.plantRow(SIZE);
		piper.turnOff();
		
		// Left Row
		piper = new MoveRightRobot_DanielGerblick(SIZE + 1, 2, East, SIZE);
		piper.plantRow(SIZE);
		piper.turnOff();
		
		// Top Row
		piper = new MoveLeftRobot_DanielGerblick(SIZE + 1, SIZE + 1, North, SIZE);
		piper.plantRow(SIZE);
		piper.turnOff();
		
		// Left Row
		piper = new MoveLeftRobot_DanielGerblick(2, SIZE + 1, East, SIZE);
		piper.plantRow(SIZE);
		piper.turnOff();
		
		// Diagonal Right
		piper = new DiagonalRight_DanielGerblick(2, 2, North, SIZE);
		piper.plantRow(SIZE);
		piper.turnOff();
		
		// Diagonal Left
		piper = new DiagonalLeft_DanielGerblick(2, SIZE + 1, North, SIZE);
		piper.plantRow(SIZE);
		piper.turnOff();
	}
}
