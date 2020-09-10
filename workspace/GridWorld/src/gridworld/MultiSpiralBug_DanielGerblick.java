package gridworld;

import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

public class MultiSpiralBug_DanielGerblick extends Bug {

	private int steps;
	private int startLength;
	private int endLength;
	private int currentLength;
	private int rowOffset;
	private int colOffset;

	public MultiSpiralBug_DanielGerblick(int startLength, int endLength, int rowOffset, int colOffset) {
		this.steps = 0;
		this.startLength = startLength;
		this.endLength = endLength;
		this.currentLength = startLength;
		this.rowOffset = rowOffset;
		this.colOffset = colOffset;
	}

	public void act() {
		if (steps == currentLength) {
			currentLength++;
			if (currentLength > endLength) {
				moveTo(new Location(this.getLocation().getRow() + rowOffset, getLocation().getCol() + colOffset));
				currentLength = startLength;
			} else {
				setDirection(getDirection() + Location.RIGHT);
			}
			steps = 0;
		} else {
			super.act();
			
			steps++;
		}
	}
}
