package gridworld;

import java.awt.Color;
import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class CrazyCritter_DanielGerblick extends Critter {
	public ArrayList<Actor> getActors() {
		ArrayList<Actor> list = new ArrayList<Actor>();
		Grid grid = getGrid();
		Location loc = getLocation();

		for (int dir = getDirection(); dir < getDirection() + 360; dir += 90) {
			Location targetLoc = loc.getAdjacentLocation(dir);
			if (grid.isValid(targetLoc)) {
				Actor actor = (Actor) grid.get(targetLoc);
				if (actor != null)
					list.add(actor);
			}
		}
		return list;
	}
	
	public void processActors(ArrayList<Actor> actors) {
		Location loc = getLocation();
		for (Actor actor : actors) {
			Location aLoc = actor.getLocation();
			int dir = Location.RIGHT + (int) Math.toDegrees(Math.atan2(aLoc.getRow() - loc.getRow(), aLoc.getCol() - loc.getCol()));
			
			if (getColor().equals(actor.getColor())) {
				actor.setDirection(actor.getDirection() + Location.RIGHT);
			} else {
				Location targetLoc = actor.getLocation().getAdjacentLocation(dir);
				if (getGrid().isValid(targetLoc) && getGrid().get(targetLoc) == null)
					actor.moveTo(targetLoc);
				else
					actor.setColor(Color.ORANGE);
			}
		}
	}
}
