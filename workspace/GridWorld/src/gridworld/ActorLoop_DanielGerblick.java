package gridworld;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

public class ActorLoop_DanielGerblick {

	public static void main(String[] args) {
		actorLoop(4);
	}
	
	public static void actorLoop(int row) {
		ActorWorld world = new ActorWorld();
		for (int col = 0; col < 5; col++)
			world.add(new Location(row, col), new Actor());
		world.show();
	}
	

}
