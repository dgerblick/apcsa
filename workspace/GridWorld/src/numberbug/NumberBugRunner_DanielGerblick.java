package numberbug;

import java.awt.Color;

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;

public class NumberBugRunner_DanielGerblick {

	public static void main(String[] args) {
		ActorWorld world = new ActorWorld(new BoundedGrid(25, 25));
		world.add(new NumberBug_DanielGerblick());
		world.add(new NumberBug_DanielGerblick(69));
		world.add(new NumberBug_DanielGerblick(8375309, Color.MAGENTA));
		world.add(new NumberBug_DanielGerblick(Color.CYAN));
		world.add(new NumberBug_DanielGerblick(Color.RED, Color.GREEN));
		world.add(new NumberBug_DanielGerblick(50, Color.ORANGE, Color.BLACK));
		world.show();
	}

}
