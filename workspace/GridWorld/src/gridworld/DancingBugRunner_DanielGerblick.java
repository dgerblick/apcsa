package gridworld;

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;

public class DancingBugRunner_DanielGerblick {

	public static void main(String[] args) {
		ActorWorld world = new ActorWorld(new BoundedGrid(20, 20));
		int[] turns = { 1, 2, 0, 0, 4, 0 };
		//int[] turns = { 3, 1, 2, 0, 5, 2, 3, 1, 0 };
		world.add(new Location(9, 9), new DancingBug_DanielGerblick(turns));
		//world.add(new Critter());
		world.show();
	}
}
