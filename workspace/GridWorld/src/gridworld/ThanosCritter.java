package gridworld;

import java.awt.Color;
import java.util.Random;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class ThanosCritter extends Critter {
	InfinityRock[] infintyRocks;
	
	public ThanosCritter() {
		setColor(Color.MAGENTA);
		
		infintyRocks = new InfinityRock[6];
		infintyRocks[0] = new InfinityRock(Color.ORANGE);
		infintyRocks[1] = new InfinityRock(Color.RED);
		infintyRocks[2] = new InfinityRock(Color.YELLOW);
		infintyRocks[3] = new InfinityRock(Color.BLUE);
		infintyRocks[4] = new InfinityRock(Color.GREEN);
		infintyRocks[5] = new InfinityRock(Color.MAGENTA);
	}
	
	public void putSelfInGrid(Grid<Actor> gr, Location loc) {
		super.putSelfInGrid(gr, loc);
		Random rand = new Random();
		for (int i = 0; i < infintyRocks.length; i++) {
			Location randLoc = null;
			while (randLoc == null) {
				randLoc = new Location(rand.nextInt(gr.getNumRows()), rand.nextInt(gr.getNumCols()));
				if (gr.get(randLoc) != null)
					randLoc = null;
			}
			infintyRocks[i].putSelfInGrid(gr, randLoc);
		}
	}
	
	public static void main(String[] args) {
		ActorWorld world = new ActorWorld(new BoundedGrid(10, 10));
		world.add(new ThanosCritter());
		world.show();
	}
}

class InfinityRock extends Rock {
	public InfinityRock(Color c) {
		super(c);
	}
}