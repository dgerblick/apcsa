package gridworld;

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

public class MoveLeftActorRunner_DanielGerblick {

	public static void main(String[] args) {
		ActorWorld world = new ActorWorld();
		world.add(new Location(0, 0), new MoveLeftActor_DanielGerblick());
		world.show();
	}

}
