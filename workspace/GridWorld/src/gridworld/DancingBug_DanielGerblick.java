package gridworld;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;

public class DancingBug_DanielGerblick extends Bug {

	private int index;
	private int[] turns;
	
	public DancingBug_DanielGerblick(int[] turns) {
		this.turns = turns;
		index = 0;
	}
	
	public void act() {
		for (int i = 0; i < turns[index]; i++)
			turn();
		super.act();
		index = index == turns.length - 1 ? 0 : index + 1; 
	}
	
	

}
