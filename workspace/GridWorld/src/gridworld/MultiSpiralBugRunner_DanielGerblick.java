package gridworld;

import java.awt.Color;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;

public class MultiSpiralBugRunner_DanielGerblick {

	public static void main(String[] args) {
		ActorWorld world = new ActorWorld(new UnboundedGrid());

		// original sidelength, finish length, row increment, column increment
		MultiSpiralBug_DanielGerblick alice = new MultiSpiralBug_DanielGerblick(3, 9, 10, -10);

		alice.setColor(Color.ORANGE);
		world.add(new Location(7, 8), alice);
		world.show();
	}
}
