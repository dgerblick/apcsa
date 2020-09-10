package numberbug;

import java.awt.Color;

import info.gridworld.actor.Actor;

public class Number_DanielGerblick extends Actor {
	
	private int number;
	private Color textColor;
	
	public int getNum() { return number; }
	public String getText() { return "" + number; }
	public Color getTextColor() { return textColor; }

	public Number_DanielGerblick(int number, Color color, Color textColor) {
		this.number = number;
		this.textColor = textColor;
		setColor(color);
	}
	
	public Number_DanielGerblick(Number_DanielGerblick other) {
		this.number = other.number + 1;
		this.textColor = other.textColor;
		setColor(other.getColor());
	}

	
}
