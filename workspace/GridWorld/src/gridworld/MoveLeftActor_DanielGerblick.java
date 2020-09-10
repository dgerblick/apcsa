package gridworld;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

public class MoveLeftActor_DanielGerblick extends Actor {

	public void act() {
		Location loc = getLocation().getAdjacentLocation(Location.WEST);
		if (loc.getCol() < 0)
			loc = new Location(getLocation().getRow(), getGrid().getNumCols() - 1);
		moveTo(loc);
	}

}
