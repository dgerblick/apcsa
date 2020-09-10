package gridworld;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

/**
 * This class runs a world that contains a bug and a rock, added at random
 * locations. Click on empty locations to add additional actors. Click on
 * populated locations to invoke methods on their occupants. <br />
 * To build your own worlds, define your own actors and a runner class. See the
 * BoxBugRunner (in the boxBug folder) for an example. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class BugRunner {
	public static void main(String[] args) {
		ActorWorld world = new ActorWorld();
		world.add(new Location(0, 0), new Bug());
		world.add(new Location(0, 0), new Actor());
		world.add(new Rock());
		//world.add(new Critter());
		world.show();
	}
}
