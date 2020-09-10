package kareltherobot;

import java.util.Enumeration;
import junit.framework.TestCase;





























































public class KJRTest
  extends TestCase
  implements Directions
{
  public KJRTest(String name)
  {
    super(name);
  }
  
  private boolean frontIsClear(UrRobot robot) {
    int street = robot.street();
    int avenue = robot.avenue();
    switch (robot.direction().points()) {
    case 3: 
      if (World.checkEWWall(street, avenue))
        return false;
      break;
    case 0: 
      if (World.checkNSWall(street, avenue))
        return false;
      break;
    case 1: 
      if (World.checkEWWall(street - 1, avenue))
        return false;
      break;
    case 2: 
      if (World.checkNSWall(street, avenue - 1))
        return false;
      break;
    }
    return true;
  }
  



  public final void assertFacingNorth(UrRobot robot)
  {
    assertTrue("Not facing North", robot.direction() == North);
  }
  



  public final void assertNotFacingNorth(UrRobot robot)
  {
    assertTrue("Facing North", robot.direction() != North);
  }
  



  public final void assertFacingEast(UrRobot robot)
  {
    assertTrue("Not facing East", robot.direction() == East);
  }
  



  public final void assertNotFacingEast(UrRobot robot)
  {
    assertTrue("Facing East", robot.direction() != East);
  }
  



  public final void assertFacingSouth(UrRobot robot)
  {
    assertTrue("Not facing South", robot.direction() == South);
  }
  



  public final void assertNotFacingSouth(UrRobot robot)
  {
    assertTrue("Facing South", robot.direction() != South);
  }
  



  public final void assertFacingWest(UrRobot robot)
  {
    assertTrue("Not facing West", robot.direction() == West);
  }
  



  public final void assertNotFacingWest(UrRobot robot)
  {
    assertTrue("Facing West", robot.direction() != West);
  }
  







  public final void assertAt(UrRobot robot, int street, int avenue)
  {
    assertOnStreet(robot, street);
    assertOnAvenue(robot, avenue);
  }
  





  public final void assertNotAt(UrRobot robot, int street, int avenue)
  {
    assertTrue("At " + street + " street and " + avenue + " avenue.", 
      (robot.street() != street) || (robot.avenue() != avenue));
  }
  




  public final void assertOnStreet(UrRobot robot, int street)
  {
    assertTrue("Not on " + street + " street.", 
      robot.street() == street);
  }
  




  public final void assertNotOnStreet(UrRobot robot, int street)
  {
    assertTrue("On " + street + " street.", 
      robot.street() != street);
  }
  




  public final void assertOnAvenue(UrRobot robot, int avenue)
  {
    assertTrue("Not on " + avenue + " avenue.", 
      robot.avenue() == avenue);
  }
  




  public final void assertNotOnAvenue(UrRobot robot, int avenue)
  {
    assertTrue("On " + avenue + " avenue.", 
      robot.avenue() != avenue);
  }
  



  public final void assertHasNeighbor(UrRobot robot)
  {
    assertTrue("No neighbors present.", World.checkRobot(robot, robot.street(), robot.avenue()));
  }
  



  public final void assertHasNoNeighbor(UrRobot robot)
  {
    assertTrue("Neighbors present.", !World.checkRobot(robot, robot.street(), robot.avenue()));
  }
  



  public final void assertNextToABeeper(UrRobot robot)
  {
    assertTrue("Not next to a beeper.", World.checkBeeper(robot.street(), robot.avenue()));
  }
  



  public final void assertNotNextToABeeper(UrRobot robot)
  {
    assertTrue("Next to a beeper.", !World.checkBeeper(robot.street(), robot.avenue()));
  }
  



  public final void assertBeepersInBeeperBag(UrRobot robot)
  {
    assertTrue("No beepers in bag.", (robot.beepers() > 0) || (robot.beepers() == -1));
  }
  



  public final void assertNoBeepersInBeeperBag(UrRobot robot)
  {
    assertTrue("Beepers in bag.", robot.beepers() == 0);
  }
  



  public final void assertFrontIsClear(UrRobot robot)
  {
    assertTrue("Front is blocked.", frontIsClear(robot));
  }
  



  public final void assertFrontIsBlocked(UrRobot robot)
  {
    assertTrue("Front is clear.", !frontIsClear(robot));
  }
  




  public final void assertRunning(UrRobot robot)
  {
    assertTrue("Not running.", robot.running());
  }
  




  public final void assertNotRunning(UrRobot robot)
  {
    assertTrue("Still running.", !robot.running());
  }
  


  public final void assertBeepersInWorld(int n)
  {
    int totalBeepers = totalBeepers();
    assertEquals("Wrong number of beepers in world.", n, totalBeepers);
  }
  


  public final void assertBeepersInWorld()
  {
    int totalBeepers = totalBeepers();
    assertTrue("No beepers in world.", totalBeepers > 0);
  }
  
  private int totalBeepers() {
    int totalBeepers = 0;
    Enumeration allBeepers = World.beepers();
    while (allBeepers.hasMoreElements()) {
      totalBeepers += ((World.BeeperCell)allBeepers.nextElement()).number();
    }
    return totalBeepers;
  }
  
  private int totalBeepers(int street, int avenue) {
    int totalBeepers = 0;
    Enumeration allBeepers = World.beepers();
    while (allBeepers.hasMoreElements()) {
      World.BeeperCell cell = (World.BeeperCell)allBeepers.nextElement();
      if ((street == cell.street()) && (avenue == cell.avenue())) {
        totalBeepers = cell.number();
        break;
      }
    }
    return totalBeepers;
  }
  

  public final void assertRobotsInWorld(int n)
  {
    assertEquals("Wrong number of robots in world.", n, World.numberOfRobots());
  }
  

  public final void assertRobotsInWorld()
  {
    assertTrue("No robots in world.", World.numberOfRobots() > 0);
  }
  




  public final void assertBeepersAt(int street, int avenue, int n)
  {
    assertEquals("Wrong number of beepers on corner.", n, totalBeepers(street, avenue));
  }
  



  public final void assertBeepersAt(int street, int avenue)
  {
    assertTrue("No beepers on corner.", totalBeepers(street, avenue) > 0);
  }
  
  private final int robotsOnCorner(int street, int avenue) {
    int totalRobots = 0;
    Enumeration robots = World.robots();
    while (robots.hasMoreElements()) {
      UrRobot karel = (UrRobot)robots.nextElement();
      if (karel.areYouHere(street, avenue)) {
        totalRobots++;
      }
    }
    return totalRobots;
  }
  




  public final void assertRobotsAt(int street, int avenue, int n)
  {
    assertEquals("Wrong number of robots on corner.", n, robotsOnCorner(street, avenue));
  }
  



  public final void assertRobotsAt(int street, int avenue)
  {
    assertTrue("No robots on corner.", robotsOnCorner(street, avenue) > 0);
  }
}
