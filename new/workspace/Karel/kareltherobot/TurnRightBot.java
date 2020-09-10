package kareltherobot;

import java.awt.Color;
import java.lang.reflect.Field; 
import kareltherobot.*;

public class TurnRightBot extends Robot implements Directions {

	public TurnRightBot(int street, int avenue, Direction direction, int beepers) {
		super(street, avenue, direction, beepers);
	}

	public TurnRightBot(int street, int avenue, Direction direction, int beepers, Color badgeColor) {
		super(street, avenue, direction, beepers, badgeColor);
	}

	public void turnRight() {
		Class<?> c = getClass().getSuperclass().getSuperclass();
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
		Class<?> c = getClass().getSuperclass().getSuperclass();
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
	
	public void faceDir(Direction dir) {
		Class<?> c = getClass().getSuperclass().getSuperclass();
		try {
			Field field = c.getDeclaredField("direction");
			field.setAccessible(true);
			field.set(this, dir);
			update();
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public void teleport(int street, int avenue) {
		Class<?> c = getClass().getSuperclass().getSuperclass();
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
	
	public static void main(String[] args) {
		World.setVisible(true);
		TurnRightBot trb = new TurnRightBot(1, 1, North, infinity);
		trb.turnRight();
		trb.teleport(9, 9);
		trb.move();
	}
}
