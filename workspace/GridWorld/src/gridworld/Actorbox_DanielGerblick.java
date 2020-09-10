package gridworld;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

public class Actorbox_DanielGerblick {

	public static void main(String[] args) {
		int size = 10;
		ActorWorld world = new ActorWorld();
		
		
		for (int i = 0; i < size; i++) {
			world.add(new Location(i, i), new Rock());
			world.add(new Location(i, size - 1 - i), new Rock());
			
			if (i != 0 && i != size - 1) {
				world.add(new Location(0, i), new Critter());
				world.add(new Location(size - 1, i), new Flower());
				world.add(new Location(i, 0), new Actor());
				world.add(new Location(i, size - 1), new Bug());
			}
		}
		
		world.show();
	}

}
