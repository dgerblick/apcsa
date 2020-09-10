package gridworld;

import java.awt.Color;

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;

public class ZBugRunner_DanielGerblick {

	public static void main(String[] args) {
		ActorWorld world = new ActorWorld(new BoundedGrid(20, 20));
		
		ZBug_DanielGerblick zBug = new ZBug_DanielGerblick(3, Color.GREEN);
		zBug.setDirection(Location.NORTH);
		world.add(new Location(6, 5), zBug);
		
		ZBug_DanielGerblick zBug1 = new ZBug_DanielGerblick(8, Color.CYAN);
		world.add(new Location(10, 0), zBug1);
		
		ZBug_DanielGerblick zBug2 = new ZBug_DanielGerblick(15);
		world.add(new Location(11, 11), zBug2);
		
		world.show();
	}

}
