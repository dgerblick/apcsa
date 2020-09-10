package kareltherobot;

//Ch6 Exercise - for loops - create square of beepers
public class BeeperSquareTester_DanielGerblick implements Directions
{
	public static void main(String args[])  
	{
		World.setVisible(true);
		World.showSpeedControl(true);
		
		BeeperSquare_DanielGerblick squareMaker;
		squareMaker = new BeeperSquare_DanielGerblick(2, 2, East, infinity);
		squareMaker.drawSquare();
		squareMaker.setVisible(false);

		squareMaker = new BeeperSquare_DanielGerblick(4, 4, East, infinity);
		squareMaker.drawSquare();
		squareMaker.setVisible(false);
		
		World.repaint();
	}	
}

