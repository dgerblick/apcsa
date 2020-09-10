package gridworld;

import java.awt.Color;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;

public class Ant extends Bug {

	private boolean[] rule;
	private int currentVal;
	private Marker last;
	
	public Ant(boolean[] rule) {
		this.rule = rule;
		Marker.maxSize = rule.length;
		currentVal = 0;
		System.out.println(Marker.maxSize);

	}

	public void turn(int value) {
		if (rule[value])
			setDirection(getDirection() + Location.RIGHT);
		else
			setDirection(getDirection() + Location.LEFT);
	}
	
	public void act() {
		//boolean occupied = this.getGrid().get
		Location currentLoc = getLocation();
		Marker next = (Marker) getGrid().get(currentLoc.getAdjacentLocation(getDirection()));
		int val = 0;
		if (next != null) {
			next.addValue();
			val = next.getValue();
		} else {
			next = new Marker(0); 
		}
		move();
		turn(val);
		getGrid().remove(currentLoc);
		if (last != null)
			last.putSelfInGrid(getGrid(), currentLoc);
		else
			(new Marker(1)).putSelfInGrid(getGrid(), currentLoc);
		last = next;
	}
	
	
	
	public static void main(String[] args) {
		Ant ant = new Ant(new boolean[]{ false, false, true, true });
		ActorWorld world = new ActorWorld(new UnboundedGrid());
		world.add(new Location(2, 2), ant);
		world.show();
	}
	
}


class Marker extends Rock {
	public static int maxSize = 2;
	private int value; 
	
	public Marker(int value) {
		this.value = value;
		setColor(new Color(Color.HSBtoRGB((float) value / maxSize, 1, 1)));
	}
	
	public void checkSelf() {
		if (value == 0)
			removeSelfFromGrid();
	}
	
	public void addValue() {
		value = (value + 1) % maxSize;
		setColor(new Color(Color.HSBtoRGB((float) value / maxSize, 1, 1)));
	}
	
	public int getValue() { return value; }
}