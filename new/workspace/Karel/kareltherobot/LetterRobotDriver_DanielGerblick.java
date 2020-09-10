package kareltherobot;

public class LetterRobotDriver_DanielGerblick implements Directions {

	public static void main(String[] args) {
		World.setVisible(true);
		World.showSpeedControl(true);
		World.setSize(100, 100);
		
		LetterRobot_DanielGerblick lenny = new LetterRobot_DanielGerblick(1, 1, North, infinity);
		lenny.setVisible(false);
		
		lenny.move(6);
		lenny.drawString("abcdefghijklm");
		lenny.turnLeft();
		lenny.move(64);
		lenny.turnLeft();
		lenny.move(6);
		lenny.turnAround();
		lenny.drawString("nopqrstuvwxyz");
		
		lenny.move(10);
		lenny.turnRight();
		lenny.nextSpace();
		
		lenny.turnLeft();
		lenny.move(10);
		lenny.turnLeft();
		lenny.move(10);
		
		lenny.drawString("hello, world!");
		lenny.turnRight();
		lenny.move(10);
		
		lenny.drawE();
		lenny.nextSpace();
		lenny.drawH();
		lenny.nextSpace();
		lenny.drawLetter('l');
	
		lenny.turnAround();
		lenny.move(5);
		lenny.drawString("1234567890");
		
		lenny.move(10);
		lenny.turnLeft();
		
		lenny.drawString("(2+2)-1=3");
	}

}
