package gridworld;

import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.world.World;

public class WordSquare_DanielGerblick {

	World<Character> world;
	Grid<Character> grid;
	
	public WordSquare_DanielGerblick(String str) {
		int size = (int) Math.ceil(Math.sqrt(str.length()));
		grid = new BoundedGrid<Character>(size, size);
		int index = 0;
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				grid.put(new Location(row, col), str.charAt(index));
				index++;
				if (index == str.length())
					index = str.length() - 1;
			}
		}
		
		world = new World<Character>(grid);
	}
	
	public void show() {
		world.setMessage("Word Square World!");   	  			
		world.show();
	}

}
