package kareltherobot;

// Ch5 ex. 11 (pages 133-134) - Carpet small rooms
public class CarpetLayerTester_DanielGerblick implements Directions
{
	public static void main(String args[])  
	{
		World.setVisible(true);
		World.showSpeedControl(true);
		//*
		World.readWorld("worldFiles\\carpetLayerWorld.txt");
		World.readWorld("carpetLayerWorld.txt");
		/*/
		World.readWorld("worldFiles\\advancedCarpetLayerWorld.txt");
		World.readWorld("advancedCarpetLayerWorld.txt");
		//*/

	   	CarpetLayer_DanielGerblick bob;
	   	bob = new CarpetLayer_DanielGerblick(1, 1, East, infinity);
	   	bob.layCarpet();
	   	bob.layCarpet();
	   	bob.layCarpet();
	   	bob.layCarpet();
	   	bob.layCarpet();
	   	bob.layCarpet();
	   	bob.layCarpet();
	   	bob.layCarpet();
	   	bob.turnOff();
	}	
}