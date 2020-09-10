package kareltherobot;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Vector;











public class UrRobot
  extends Observable
  implements Directions, Runnable
{
  public void turnLeft()
  {
    if (state == 2) {
      pause("turnLeft");
      direction = direction.rotate(-1);
      StateObject s = new StateObject(1);
      setChanged();
      notifyObservers(s);
      sleep();
    }
  }
  
  public void move() {
    if (state == 2) {
      pause("move");
      boolean crashed = false;
      normalize();
      switch (direction.points()) {
      case 3: 
        if (World.checkEWWall(loc[3], loc[0]))
          crashed = crash("Tried to walk through an East West wall");
        break;
      case 0: 
        if (World.checkNSWall(loc[3], loc[0]))
          crashed = crash("Tried to walk through a North South wall");
        break;
      case 1: 
        if (World.checkEWWall(loc[3] - 1, loc[0]))
          crashed = crash("Tried to walk through an East West wall");
        break;
      case 2: 
        if (World.checkNSWall(loc[3], loc[0] - 1))
          crashed = crash("Tried to walk through a North South wall");
        break;
      }
      if (!crashed) {
        loc[direction.points()] += 1;
        moves += 1;
        if (moves > 10) {
          normalize();
        }
      }
      validate();
      StateObject s = new StateObject(0);
      setChanged();
      notifyObservers(s);
      sleep();
    }
  }
  
  public void pickBeeper() {
    if (state == 2) {
      pause("pickBeeper");
      normalize();
      boolean crashed = false;
      if (!World.checkBeeper(loc[3], loc[0]))
        crashed = crash("No beepers to pick");
      if (!crashed) {
        if (beepers != -1) beepers += 1;
        World.placeBeepers(loc[3], loc[0], -1);
      }
      StateObject s = new StateObject(2);
      setChanged();
      notifyObservers(s);
      sleep();
    }
  }
  
  public void putBeeper() {
    if (state == 2) {
      pause("putBeeper");
      normalize();
      if (beepers == 0) {
        crash("No beepers to put.");
        StateObject s = new StateObject(3);
        setChanged();
        notifyObservers(s);
        return;
      }
      if (beepers != -1) beepers -= 1;
      if (!validate()) {
        if (beepers != -1) beepers += 1;
      }
      else {
        World.placeBeepers(loc[3], loc[0], 1);
        StateObject s = new StateObject(3);
        setChanged();
        notifyObservers(s);
      }
      sleep();
    }
  }
  
  public void turnOff() {
    pause("turnOff");
    if (state == 2) {
      System.out.println("Robot " + idNumber + ": Turning off");
      state = 1;
      StateObject s = new StateObject(4);
      setChanged();
      notifyObservers(s);
      sleep();
    }
  }
  







  public World world()
  {
    return World.asObject();
  }
  
  public Enumeration neighbors()
  {
    Vector v = new Vector();
    Enumeration all = World.robots();
    while (all.hasMoreElements()) {
      UrRobot r = (UrRobot)all.nextElement();
      if ((r != this) && (r.areYouHere(street(), avenue()))) {
        v.addElement(r);
      }
    }
    return v.elements();
  }
  








  public static abstract interface ConnectStrategy
  {
    public abstract void action(UrRobot paramUrRobot1, UrRobot paramUrRobot2, BufferedReader paramBufferedReader);
  }
  







  public String getNextCommunication()
  {
    if (senders.size() == 0) return null;
    int count = 0;
    for (;;) {
      if (count >= senders.size()) return null;
      if (nextSender >= senders.size()) nextSender = 0;
      BufferedReader in = (BufferedReader)senders.elementAt(nextSender);
      try {
        nextSender += 1;
        if (in.ready()) { return in.readLine();
        }
      }
      catch (IOException localIOException) {}
      count++;
      sleep();
    }
  }
  
  public String waitForCommunication()
  {
    if (senders.size() == 0) return null;
    for (;;) {
      if (nextSender >= senders.size()) nextSender = 0;
      BufferedReader in = (BufferedReader)senders.elementAt(nextSender);
      try {
        nextSender += 1;
        if (in.ready()) { return in.readLine();
        }
      }
      catch (IOException localIOException) {}
      sleep();
    }
  }
  
  public String waitForNextCommunication() {
    if (senders.size() == 0) return null;
    for (;;) {
      if (nextSender >= senders.size()) nextSender = 0;
      BufferedReader in = (BufferedReader)senders.elementAt(nextSender);
      try {
        nextSender += 1;
        return in.readLine();
      }
      catch (IOException localIOException) {}
    }
  }
  


  public BufferedWriter connectTo(UrRobot other, ConnectStrategy strat)
    throws IOException
  {
    PipedOutputStream out = new PipedOutputStream();
    BufferedWriter result = new BufferedWriter(new OutputStreamWriter(out));
    other.acceptConnectionFrom(this, out, strat);
    return result;
  }
  
  public synchronized void acceptConnectionFrom(UrRobot sender, PipedOutputStream s, ConnectStrategy strat) throws IOException
  {
    BufferedReader manager = new BufferedReader(new InputStreamReader(new PipedInputStream(s)));
    if (strat != null) {
      strat.action(sender, this, manager);
    } else {
      senders.addElement(manager);
    }
  }
  



  public synchronized void acceptConnection(PipedOutputStream s, ConnectStrategy strat)
    throws IOException
  {
    BufferedReader manager = new BufferedReader(new InputStreamReader(new PipedInputStream(s)));
    if (strat != null) {
      strat.action(null, this, manager);
    } else
      senders.addElement(manager);
  }
  
  private Vector senders = new Vector();
  private int nextSender = 0;
  

  static abstract interface Action
  {
    public static final int move = 0;
    
    public static final int turnLeft = 1;
    
    public static final int pickBeeper = 2;
    
    public static final int putBeeper = 3;
    
    public static final int turnOff = 4;
    public static final int initial = -1;
  }
  
  public void send(BufferedWriter other, String s)
    throws IOException
  {
    other.write(s + '\n');
    other.flush();
  }
  








  public void run() {}
  







  final void pause(String message)
  {
    if (pausing) {
      System.out.println("RobotID " + idNumber + " is about to " + message + ".");
      try
      {
        sysin.readLine();
      }
      catch (IOException localIOException) {}
    }
  }
  





  public void userPause(String message)
  {
    if (userLevelPausing) {
      System.out.println("RobotID " + idNumber + " is about to " + message + ".");
      try
      {
        sysin.readLine();
      }
      catch (IOException localIOException) {}
    }
  }
  







  public final void setPause(boolean pausing)
  {
    this.pausing = pausing;
  }
  





  public final void setUserPause(boolean pausing) { userLevelPausing = pausing; }
  
  private boolean pausing = false;
  private boolean userLevelPausing = false;
  
  public UrRobot(int street, int avenue, Directions.Direction direction, int beepers, Color badgeColor) {
    loc[3] = street;
    loc[1] = 0;
    loc[0] = avenue;
    loc[2] = 0;
    this.direction = direction;
    this.beepers = beepers;
    validate();
    idNumber = incrementRobots();
    state = 2;
    initialState = new StateObject(-1);
    this.badgeColor = badgeColor;
    World.addRobot(this);
    sleep();
    sleep();
  }
  




  public UrRobot(int street, int avenue, Directions.Direction direction, int beepers)
  {
    this(street, avenue, direction, beepers, null);
  }
  



  final Color badgeColor() { return badgeColor; }
  
  final void restoreInitialState() {
    loc[3] = initialState.street;
    loc[1] = 0;
    loc[0] = initialState.avenue;
    loc[2] = 0;
    direction = initialState.direction;
    beepers = initialState.beepers;
    state = 2;
    showState("Restoring ");
    setChanged();
    notifyObservers(initialState);
    sleep();
  }
  
  public final String toString() {
    normalize();
    return "RobotID " + idNumber + " at (street: " + loc[3] + ") (avenue: " + loc[0] + 
      ") (beepers: " + (beepers >= 0 ? beepers : "infinite") + ") ( direction: " + direction.toString() + (
      state == 2 ? ") on" : ") off");
  }
  
  private String direction(int d)
  {
    switch (d) {
    case 0:  return "East";
    case 1:  return "South";
    case 2:  return "West";
    case 3:  return "North"; }
    return "ERROR";
  }
  
  protected void sleep()
  {
    try {
      Thread.sleep(10 * World.delay());
    }
    catch (InterruptedException localInterruptedException) {}
  }
  
  public final void showState(String s)
  {
    normalize();
    System.out.println(s + this);
  }
  
  final boolean areYouHere(int street, int avenue) {
    normalize();
    return (loc[3] == street) && (loc[0] == avenue);
  }
  
  private boolean validate() {
    normalize();
    if (beepers < -1) return !crash("Robot has negative beepers");
    if (loc[3] < 1) return !crash("Robot tried to move through South boundary wall");
    if (loc[0] < 1) return !crash("Robot tried to move through West boundary wall");
    return true;
  }
  
  private boolean crash(String s) {
    state = 0;
    showState("Error shutoff: ");
    
    System.out.println(s);
    
    return true;
  }
  
  final boolean crashed() {
    return state == 0;
  }
  
  private void pauseExit() {
    try {
      sysin.readLine();
    } catch (IOException localIOException) {}
    System.exit(0);
  }
  
  private void normalize() {
    moves = 0;
    loc[3] -= loc[1];
    loc[1] = 0;
    loc[0] -= loc[2];
    loc[2] = 0;
  }
  
  final class StateObject implements Serializable {
    public StateObject(int lastAction) {
      street = (loc[3] - loc[1]);
      avenue = (loc[0] - loc[2]);
      direction = direction;
      beepers = beepers;
      this.lastAction = lastAction; }
    
    public int street() { return street; }
    public int avenue() { return avenue; }
    public Directions.Direction direction() { return direction; }
    public int beepers() { return beepers; }
    public int lastAction() { return lastAction; }
    
    private int street;
    private int avenue;
    private Directions.Direction direction;
    private int beepers;
    private int lastAction; }
  
  final int beepers() { return beepers; }
  final Directions.Direction direction() { return direction; }
  final int street() { return loc[3] - loc[1]; }
  final int avenue() { return loc[0] - loc[2]; }
  final boolean running() { return state == 2; }
  
  public final boolean isVisible() { return isVisible; }
  
  public final void setVisible(boolean visible) {
    if (visible != isVisible) {
      setChanged();
      isVisible = visible;
    } }
  
  BufferedReader sysin = new BufferedReader(new InputStreamReader(System.in));
  
  private static final int on = 2;
  private static final int off = 1;
  private static final int crashed = 0;
  private int[] loc = new int[4];
  private int beepers;
  private Color badgeColor = null;
  private Directions.Direction direction;
  private int moves = 0;
  private int state = 2;
  private boolean isVisible = true;
  private int idNumber;
  private static final int threshhold = 10;
  private static synchronized int incrementRobots() { return numberOfRobots++; }
  
  private static int numberOfRobots = 0;
  private StateObject initialState;
}
