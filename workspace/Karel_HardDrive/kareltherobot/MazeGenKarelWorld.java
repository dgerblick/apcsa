package kareltherobot;
import java.util.Random;
import java.util.Stack;
import java.util.ArrayList;
import java.util.List;

public final class MazeGenKarelWorld implements Directions {
	
	private int SIZE;
	private boolean[][] visited; 
	private Stack<Corner> stack;
	
	public static void main(String[] args) {
		World.setVisible(true);
		World.showSpeedControl(true);
		MazeGenKarelWorld gen = new MazeGenKarelWorld(69);
		
		MazeWalker_DanielGerblick mw = new MazeWalker_DanielGerblick(1, 1, North, 0);
		
		while(!mw.nextToABeeper())
			mw.followWallRight();
	}
	
	public MazeGenKarelWorld(int size) {
		SIZE = size;
		visited = new boolean[SIZE][SIZE];
		stack = new Stack<Corner>();
		
		World.setSize(SIZE + 1, SIZE + 1);
		
		for (int i = 0; i < SIZE; i++) {
			World.placeNSWall(1, i + 1, SIZE);
			World.placeEWWall(i + 1, 1, SIZE);
		}
		
		Random random = new Random();
		Corner last = new Corner(random.nextInt(SIZE) + 1, random.nextInt(SIZE) + 1);
		visited[last.street - 1][last.avenue - 1] = true;
		stack.push(last);
		World.placeBeepers(last.street, last.avenue, 1);
		
		Corner current;
		while(!stack.empty()) {
			current = getRandNeighbor(last);
			if (current != null) {
				visited[current.street - 1][current.avenue - 1] = true;
				stack.push(current);
				breakWallBetween(last, current);
				last = current;
			} else {
				last = stack.pop();
			}
		}
		
		World.setVisible(true);
	}
	
	private void breakWallBetween(Corner c1, Corner c2) {
		if (c1.street == c2.street) {
			if (c1.avenue > c2.avenue)
				World.removeNSWall(c2.street, c2.avenue);
			else if (c1.avenue < c2.avenue)
				World.removeNSWall(c1.street, c1.avenue);
		} else if (c1.avenue == c2.avenue)  {
			if (c1.street > c2.street)
				World.removeEWWall(c2.street, c2.avenue);
			else if (c1.street < c2.street)
				World.removeEWWall(c1.street, c1.avenue);
		}
	}
	
	private Corner getRandNeighbor(Corner c) {
		Random rand = new Random();
		List<Corner> neighbors = new ArrayList<Corner>();
		
		if (c.street < SIZE && !visited[c.street][c.avenue - 1])
			neighbors.add(new Corner(c.street + 1, c.avenue));
		if (c.street > 1 && !visited[c.street - 2][c.avenue - 1])
			neighbors.add(new Corner(c.street - 1, c.avenue));
		if (c.avenue < SIZE && !visited[c.street - 1][c.avenue])
			neighbors.add(new Corner(c.street, c.avenue + 1));
		if (c.avenue > 1 && !visited[c.street - 1][c.avenue - 2])
			neighbors.add(new Corner(c.street, c.avenue - 1));
		System.out.println(neighbors.size());
		if (neighbors.size() == 0)
			return null;
		else
			return neighbors.get(rand.nextInt(neighbors.size()));
	}

}

class Corner {
	public int street;
	public int avenue;
	
	public Corner(int street, int avenue) {
		this.street = street;
		this.avenue = avenue;
	}
	
	public boolean equals(Corner c) {
		return street == c.street && avenue == c.avenue;
	}
	
	public String toString() {
		return "(" + street + ", " + avenue + ")";
	}
	
	public boolean isVisited(boolean[][] visited) {
		return visited[street][avenue];
	}
}