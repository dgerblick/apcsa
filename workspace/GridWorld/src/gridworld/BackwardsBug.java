package gridworld;

import java.awt.Color;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

public class BackwardsBug extends Bug {

	public BackwardsBug() {
		super();
	}

	public BackwardsBug(Color bugColor) {
		super(bugColor);
	}
	
	public void act() {
		setDirection(getDirection() + Location.HALF_CIRCLE);
		super.act();
		setDirection(getDirection() + Location.HALF_CIRCLE);
	}

	public static void main(String[] args) {
		ActorWorld world = new ActorWorld();
		world.add(new Location(0, 0), new BackwardsBug());
		world.add(new Rock());
		//world.add(new Critter());
		world.show();
	}

}
