package gridworld;

import java.awt.Color;

import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

public class ZBug_DanielGerblick extends Bug {

	private int zSize;
	private int maxSteps;
	private int steps;
	private boolean needTurn;
	
	public ZBug_DanielGerblick(int zSize) {
		super();
		this.zSize = zSize;
		maxSteps = zSize * 3;
		steps = 0;
		needTurn = false;
		setDirection(Location.EAST);
	}

	public ZBug_DanielGerblick(int zSize, Color bugColor) {
		super(bugColor);
		this.zSize = zSize;
		maxSteps = zSize * 3;
		steps = 0;
		needTurn = false;
		setDirection(Location.EAST);
	}
	
	public void act() {
		if (steps < maxSteps) {
			if (steps == zSize && !needTurn) {
				setDirection(getDirection() + Location.RIGHT + Location.HALF_RIGHT);
				needTurn = true;
				return;
			} else if (steps == zSize * 2 && !needTurn) {
				setDirection(getDirection() + Location.LEFT + Location.HALF_LEFT);
				needTurn = true;
				return;
			}
			needTurn = false;
			move();
			steps++;

		}
	}
	
}
