package kareltherobot;

import java.awt.Color;
import java.lang.reflect.Field;

public class SmartBot_DanielGerblick extends BetterRobot_DanielGerblick {

	private Class<?> c;
	
	public SmartBot_DanielGerblick(int street, int avenue, Direction direction, int beepers) {
		super(street, avenue, direction, beepers);
		try {
			c = Class.forName("kareltherobot.UrRobot");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public SmartBot_DanielGerblick(int street, int avenue, Direction direction, int beepers, Color color) {
		super(street, avenue, direction, beepers, color);
		c = getClass().getSuperclass().getSuperclass().getSuperclass().getSuperclass()	;
	}
	
	public void turnRight() {
		try {
			Field field = c.getDeclaredField("direction");
			field.setAccessible(true);
			Direction dir = (Direction) field.get(this);
			field.set(this, dir.rotate(1));
			update();
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public void turnAround() {
		try {
			Field field = c.getDeclaredField("direction");
			field.setAccessible(true);
			Direction dir = (Direction) field.get(this);
			field.set(this, dir.rotate(2));
			update();
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public void face(Direction dir) {
		try {
			Field field = c.getDeclaredField("direction");
			field.setAccessible(true);
			field.set(this, dir);
			update();
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			while (direction() != dir)
				turnLeft();
		}
	}
	
	public void teleport(int street, int avenue) {
		try {
			Field field = c.getDeclaredField("loc");
			field.setAccessible(true);
			int[] loc = (int[]) field.get(this);
			loc[3] = street;
			loc[0] = avenue;
			field.set(this, loc);
			update();
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	private void update() {
		StateObject s = new StateObject(1);
		setChanged();
		notifyObservers(s);
		sleep();
		World.repaint();
	}
	
	public boolean beeperIsToLeft() {
		turnLeft();
		move();
		boolean toLeft = nextToABeeper();
		turnAround();
		move();
		turnLeft();
		return toLeft;
	}

	public boolean beeperIsToRight() {
		turnRight();
		move();
		boolean toRight = nextToABeeper();	
		turnAround();
		move();
		turnRight();
		return toRight;
	}

	public int numBeepersOnCorner() {
		int numBeepers = 0;
		while (nextToABeeper()) {
			pickBeeper();
			numBeepers++;
		}
		for (int i = 0; i < numBeepers; i++)
			putBeeper();
		return numBeepers;
	}
	
	public boolean twoBeepersOrMoreOnCorner() {
		return numBeepersOnCorner() >= 2;
	}

	public boolean leftIsClear() {
		turnLeft();
		boolean b = frontIsClear();
		turnRight();
		return b;
	}
	
	public boolean rightIsClear() {
		turnRight();
		boolean b = frontIsClear();
		turnLeft();
		return b;
	}
	
	public boolean backIsClear() {
		turnAround();
		boolean b = frontIsClear();
		turnAround();
		return b;
	}
	
	public void faceEast() {
		face(East);
	}

	public void faceWest() {
		face(West);
	}

	public void faceSouth() {
		face(South);
	}

	public void faceNorth() {
		face(North);
	}
}