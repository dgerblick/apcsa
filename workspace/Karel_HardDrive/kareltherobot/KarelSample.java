package kareltherobot;
import kareltherobot.*;

public class KarelSample implements Directions
{
    public static void main(String args[])  
    {
        World.setVisible(true);
        World.showSpeedControl(true);

	UrRobot karel = new UrRobot(2, 2, North, 1);  
	karel.move();  
	karel.move();  
	karel.turnLeft(); 
    karel.putBeeper();
	karel.move();  
	karel.turnOff();  
    } 
}