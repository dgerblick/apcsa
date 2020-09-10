package kareltherobot;

import java.awt.Color;












public class Robot
  extends UrRobot
  implements Directions
{
  public Robot(int street, int avenue, Directions.Direction direction, int beepers)
  {
    super(street, avenue, direction, beepers);
  }
  
  public Robot(int street, int avenue, Directions.Direction direction, int beepers, Color badge) {
    super(street, avenue, direction, beepers, badge);
  }
  


  public boolean frontIsClear()
  {
    pause("say if its frontIsClear");
    switch (direction().points()) {
    case 3: 
      if (World.checkEWWall(street(), avenue()))
        return false;
      break;
    case 0: 
      if (World.checkNSWall(street(), avenue()))
        return false;
      break;
    case 1: 
      if (World.checkEWWall(street() - 1, avenue()))
        return false;
      break;
    case 2: 
      if (World.checkNSWall(street(), avenue() - 1))
        return false;
      break;
    }
    return true;
  }
  


  public boolean nextToABeeper()
  {
    sleep();
    pause("say if it is nextToABeeper");
    return World.checkBeeper(street(), avenue());
  }
  


  public boolean nextToARobot()
  {
    sleep();
    pause("say if it is nextToARobot");
    return World.checkRobot(this, street(), avenue());
  }
  


  public boolean facingNorth()
  {
    pause("say if it is facingNorth");
    return direction() == North;
  }
  


  public boolean facingSouth()
  {
    pause("say if it is facingSouth");
    return direction() == South;
  }
  


  public boolean facingEast()
  {
    pause("say if it is facingEast");
    return direction() == East;
  }
  


  public boolean facingWest()
  {
    pause("say if it is facingWest");
    return direction() == West;
  }
  


  public boolean anyBeepersInBeeperBag()
  {
    pause("say if it has anyBeepersInBeeperBag");
    return (beepers() > 0) || (beepers() == -1);
  }
}
