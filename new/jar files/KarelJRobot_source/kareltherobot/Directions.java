package kareltherobot;

import java.util.Hashtable;













public abstract interface Directions
{
  public static final int infinity = -1;
  public static final Direction North = Direction.North;
  public static final Direction East = Direction.East;
  public static final Direction South = Direction.South;
  public static final Direction West = Direction.West;
  
  public static class Direction
  {
    static final int NorthVal = 3;
    static final int WestVal = 2;
    static final int SouthVal = 1;
    static final int EastVal = 0;
    
    private Direction(int which) {
      where = which;
      repository.put(new Integer(which), this);
    }
    
    Direction rotate(int rotateBy)
    {
      int newDirection = (where + rotateBy) % 4;
      if (newDirection < 0) { newDirection += 4;
      }
      return (Direction)repository.get(new Integer(newDirection));
    }
    
    int points() {
      return where;
    }
    
    public String toString() {
      String result = "Error";
      switch (where) {
      case 3:  result = "North"; break;
      case 2:  result = "West"; break;
      case 1:  result = "South"; break;
      case 0:  result = "East";
      }
      return result;
    }
    
    static Direction select(int which) {
      return (Direction)repository.get(new Integer(which % 4));
    }
    
    private int where = 0;
    private static Hashtable repository = new Hashtable(4);
    
    private static final Direction North = new Direction(3);
    private static final Direction West = new Direction(2);
    private static final Direction South = new Direction(1);
    private static final Direction East = new Direction(0);
  }
}
