package kareltherobot;

import java.awt.Color;

public class Supervisor_DanielGerblick extends Gardener_DanielGerblick {

	private Gardener_DanielGerblick[] gardeners;
	
	public Supervisor_DanielGerblick(int street, int avenue, Direction direction, int beepers, Gardener_DanielGerblick[] gardeners) {
		super(street, avenue, direction, beepers);
		this.gardeners = addGardener(gardeners);
	}
	public Supervisor_DanielGerblick(int street, int avenue, Direction direction, int beepers, Color color, Gardener_DanielGerblick[] gardeners) {
		super(street, avenue, direction, beepers, color);
		this.gardeners = addGardener(gardeners);
	}
	
	private Gardener_DanielGerblick[] addGardener(Gardener_DanielGerblick[] gardeners) {
		Gardener_DanielGerblick[] newGardeners = new Gardener_DanielGerblick[gardeners.length + 1];
		
		for (int i = 0; i < gardeners.length; i++)
			newGardeners[i] = gardeners[i];
		newGardeners[gardeners.length] = this;
		
		return newGardeners;
	}
	
	public void placeCorners(int size) {
		for (Gardener_DanielGerblick robot : gardeners)
			robot.plantL(size);
	}
}
