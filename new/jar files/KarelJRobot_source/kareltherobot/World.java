package kareltherobot;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;
import java.util.StringTokenizer;
import java.util.Vector;














public class World
  implements Directions
{
  public static final World asObject = new World();
  

  private World() {}
  

  public static final World asObject() { return asObject; }
  
  private static Hashtable beepers = new Hashtable();
  private static Hashtable ewWalls = new Hashtable();
  private static Hashtable nsWalls = new Hashtable();
  private static final Integer one = new Integer(1);
  private static Vector robots = new Vector();
  private static RobotObserver myObserver = new RobotObserver();
  private static int delay = 100;
  private static int streets = 10; private static int avenues = 10;
  
  private static ThreadGroup threads = new ThreadGroup("RobotThread0");
  private static int threadGroupNumber = 0;
  private static boolean tracing = true;
  private static Hashtable checkpointBeepers = null;
  
  static final int numberOfStreets() { return streets; }
  
  static final int numberOfAvenues() { return avenues; }
  
  static final void setOS9Mac(boolean mac) {
    if (view == null) view = new RobotWorldWindow();
    view.setMac(mac);
  }
  




  public static synchronized void placeBeepers(int Street, int Avenue, int howMany)
  {
    IntPair where = new IntPair(Street, Avenue);
    Integer p = (Integer)beepers.get(where);
    if (p == null) {
      if ((howMany > 0) || (howMany == -1)) beepers.put(where, new Integer(howMany));
    } else {
      if (p.intValue() == -1) {
        return;
      }
      

      int newval = p.intValue() + howMany;
      

      beepers.remove(where);
      if ((newval > 0) || (newval == -1)) {
        beepers.put(where, new Integer(newval));
      }
    }
  }
  


  public static synchronized void clearBeepers(int Street, int Avenue)
  {
    IntPair where = new IntPair(Street, Avenue);
    beepers.remove(where);
  }
  
  static synchronized boolean decreaseBeeperIfPossible(int Street, int Avenue) {
    boolean result = false;
    if (checkBeeper(Street, Avenue)) {
      result = true;
      placeBeepers(Street, Avenue, -1);
    }
    return result;
  }
  
  static synchronized boolean checkBeeper(int Street, int Avenue) {
    IntPair p = new IntPair(Street, Avenue);
    return beepers.get(p) != null;
  }
  









  private static RobotWorldWindow view = null;
  
  public static void smallView(int height)
  {
    view = new RobotWorldWindow(height);
  }
  
  static RobotWorldWindow view() {
    if (view == null) view = new RobotWorldWindow();
    return view;
  }
  




  public static Canvas worldCanvas()
  {
    if (view == null) view = new RobotWorldWindow();
    return view.worldCanvas();
  }
  
  static void addRobot(UrRobot k) {
    robots.addElement(k);
    k.addObserver(myObserver);
    if (tracing) System.out.println(k);
    if (view != null) view.repaint();
  }
  
  static synchronized boolean checkRobot(UrRobot k, int street, int avenue) {
    Enumeration e = robots.elements();
    while (e.hasMoreElements()) {
      UrRobot o = (UrRobot)e.nextElement();
      if ((o != k) && (o.areYouHere(street, avenue)))
        return true;
    }
    return false;
  }
  
  static Enumeration ewWalls() { return ewWalls.keys(); }
  
  static Enumeration nsWalls() { return nsWalls.keys(); }
  
  static Enumeration robots() { return robots.elements(); }
  
  static int numberOfRobots() { return robots.size(); }
  

  static Enumeration beepers() { return new BeeperEnum(); }
  
  static class BeeperEnum implements Enumeration { BeeperEnum() {}
    
    public boolean hasMoreElements() { return k.hasMoreElements(); }
    
    public Object nextElement()
    {
      World.IntPair p = (World.IntPair)k.nextElement();
      Integer val = (Integer)v.nextElement();
      return new World.BeeperCell(World.IntPair.access$0(p), World.IntPair.access$1(p), val.intValue());
    }
    
    private Enumeration k = World.beepers.keys();
    private Enumeration v = World.beepers.elements();
  }
  
  static class BeeperCell { private int street;
    
    public BeeperCell(int s, int a, int n) { street = s;
      avenue = a;
      number = n;
    }
    
    public int street() { return street; }
    public int avenue() { return avenue; }
    public int number() { return number; }
    
    private int avenue;
    private int number;
  }
  
  static final boolean checkEWWall(int NorthOfStreet, int atAvenue) { if (NorthOfStreet == 0) return true;
    IntPair p = new IntPair(NorthOfStreet, atAvenue);
    return ewWalls.get(p) != null;
  }
  
  static final boolean checkNSWall(int atStreet, int EastOfAvenue) {
    if (EastOfAvenue == 0) return true;
    IntPair p = new IntPair(atStreet, EastOfAvenue);
    return nsWalls.get(p) != null;
  }
  





  public static final void placeEWWall(int NorthOfStreet, int atAvenue, int lengthTowardEast)
  {
    for (int i = 0; i < lengthTowardEast; i++) {
      ewWalls.put(new IntPair(NorthOfStreet, atAvenue + i), one);
    }
  }
  





  public static final void placeNSWall(int atStreet, int EastOfAvenue, int lengthTowardNorth)
  {
    for (int i = 0; i < lengthTowardNorth; i++) {
      nsWalls.put(new IntPair(atStreet + i, EastOfAvenue), one);
    }
  }
  



  public static final void removeEWWall(int NorthOfStreet, int atAvenue)
  {
    IntPair where = new IntPair(NorthOfStreet, atAvenue);
    ewWalls.remove(where);
  }
  



  public static final void removeNSWall(int atStreet, int EastOfAvenue)
  {
    IntPair where = new IntPair(atStreet, EastOfAvenue);
    nsWalls.remove(where);
  }
  


  public static final void saveWorld(String filename)
  {
    saveWorld(null, filename);
  }
  





  public static final void saveXMLWorld(String filename)
  {
    saveXMLWorld(null, filename);
  }
  



  public static final String asText(String sep)
  {
    String result = "KarelWorld" + sep;
    result = result + "streets " + numberOfStreets() + sep;
    result = result + "avenues " + numberOfAvenues() + sep;
    Enumeration e = beepers.keys();
    while (e.hasMoreElements()) {
      IntPair p = (IntPair)e.nextElement();
      result = result + "beepers " + s + " " + a + " " + beepers.get(p) + sep;
    }
    e = ewWalls.keys();
    while (e.hasMoreElements()) {
      IntPair p = (IntPair)e.nextElement();
      result = result + "eastwestwalls " + s + " " + a + " " + a + sep;
    }
    e = nsWalls.keys();
    while (e.hasMoreElements()) {
      IntPair p = (IntPair)e.nextElement();
      result = result + "northsouthwalls " + a + " " + s + " " + s + sep;
    }
    return result;
  }
  


  public static final void saveWorld(String directoryPath, String filename)
  {
    try
    {
      BufferedWriter w = new BufferedWriter(new FileWriter(new File(directoryPath, filename)));
      w.write("KarelWorld");
      w.newLine();
      w.write("streets " + numberOfStreets());
      w.newLine();
      w.write("avenues " + numberOfAvenues());
      w.newLine();
      Enumeration e = beepers.keys();
      while (e.hasMoreElements()) {
        IntPair p = (IntPair)e.nextElement();
        w.write("beepers " + s + " " + a + " " + beepers.get(p));
        w.newLine();
      }
      e = ewWalls.keys();
      while (e.hasMoreElements()) {
        IntPair p = (IntPair)e.nextElement();
        w.write("eastwestwalls " + s + " " + a + " " + a);
        w.newLine();
      }
      e = nsWalls.keys();
      while (e.hasMoreElements()) {
        IntPair p = (IntPair)e.nextElement();
        w.write("northsouthwalls " + a + " " + s + " " + s);
        w.newLine();
      }
      w.close();
    } catch (IOException e) {
      System.out.println("Can't save world.");
    }
  }
  

  public static void saveXMLWorld(String directoryPath, String filename)
  {
    try
    {
      BufferedWriter w = new BufferedWriter(new FileWriter(new File(directoryPath, filename)));
      w.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
      w.newLine();
      w.write("<karelworld ");
      w.write("streets=\"" + numberOfStreets() + "\" ");
      w.write("avenues=\"" + numberOfAvenues() + "\" >");
      w.newLine();
      w.write("\t<beepers>");
      w.newLine();
      Enumeration e = beepers.keys();
      while (e.hasMoreElements()) {
        IntPair p = (IntPair)e.nextElement();
        w.write("\t\t<where street=\"" + s + "\" avenue=\"" + a + "\" howmany=\"" + beepers.get(p) + "\" />");
        w.newLine();
      }
      w.write("\t</beepers>");
      w.newLine();
      w.write("\t<eastwestwalls>");
      w.newLine();
      e = ewWalls.keys();
      while (e.hasMoreElements()) {
        IntPair p = (IntPair)e.nextElement();
        w.write("\t\t<where northof=\"" + s + "\" firstavenue=\"" + a + "\" lastavenue=\"" + a + "\" />");
        w.newLine();
      }
      w.write("\t</eastwestwalls>");
      w.newLine();
      w.write("\t<northsouthwalls>");
      w.newLine();
      e = nsWalls.keys();
      while (e.hasMoreElements()) {
        IntPair p = (IntPair)e.nextElement();
        w.write("\t\t<where eastof=\"" + a + "\" firststreet=\"" + s + "\" laststreet=\"" + s + "\" />");
        w.newLine();
      }
      w.write("\t</northsouthwalls>");
      w.newLine();
      w.write("</karelworld>");
      w.newLine();
      w.close();
    } catch (IOException e) {
      System.out.println("Can't save world.");
    }
  }
  




  public static final void showBeepers()
  {
    Enumeration e = beepers.keys();
    while (e.hasMoreElements()) {
      IntPair p = (IntPair)e.nextElement();
      int b = ((Integer)beepers.get(p)).intValue();
      System.out.println((b >= 0 ? b : "infinite number of") + " beepers at " + s + " street and " + a + " avenue");
    }
  }
  




  public static final void showWorld()
  {
    System.out.println();
    showBeepers();
    




    Enumeration e = ewWalls.keys();
    while (e.hasMoreElements()) {
      IntPair p = (IntPair)e.nextElement();
      System.out.println("east west wall above " + s + " street crossing " + a + " avenue");
    }
    
    e = nsWalls.keys();
    while (e.hasMoreElements()) {
      IntPair p = (IntPair)e.nextElement();
      System.out.println("north south wall east of " + a + " avenue crossing " + s + " street");
    }
    System.out.println();
  }
  



  public static final void readWorld(String filename)
  {
    readWorld(null, filename);
  }
  


  public static final void readWorld(String directoryPath, String filename)
  {
    try
    {
      FileInputStream f = new FileInputStream(new File(directoryPath, filename));
      InputStreamReader r = new InputStreamReader(f);
      int size = f.available();
      char[] buf = new char[size];
      r.read(buf, 0, size);
      String commands = new String(buf);
      getWorld(commands);
      





























      r.close();
    } catch (IOException e) {
      System.out.println("Can't read world.");
    }
  }
  


  public static void getWorld(String commands)
  {
    if (commands == null) return;
    StringTokenizer t = new StringTokenizer(commands);
    while (t.hasMoreTokens()) {
      try {
        String token = t.nextToken();
        
        if (token.equalsIgnoreCase("streets")) {
          setSize(Integer.parseInt(t.nextToken()), numberOfAvenues());
        }
        else if (token.equalsIgnoreCase("avenues")) {
          setSize(numberOfStreets(), Integer.parseInt(t.nextToken()));
        }
        else if (token.equalsIgnoreCase("beepers")) {
          int s = Integer.parseInt(t.nextToken());
          int a = Integer.parseInt(t.nextToken());
          int n = Integer.parseInt(t.nextToken());
          placeBeepers(s, a, n);
        }
        else if (token.equalsIgnoreCase("eastwestwalls")) {
          int s = Integer.parseInt(t.nextToken());
          int a = Integer.parseInt(t.nextToken());
          int n = Integer.parseInt(t.nextToken());
          placeEWWall(s, a, n - a + 1);
        }
        else if (token.equalsIgnoreCase("northsouthwalls")) {
          int a = Integer.parseInt(t.nextToken());
          int s = Integer.parseInt(t.nextToken());
          int n = Integer.parseInt(t.nextToken());
          placeNSWall(s, a, n - s + 1);
        }
      }
      catch (Exception localException) {}
    }
    

    if (view != null) view.repaint(); }
  
  static class IntPair { private int s;
    private int a;
    
    IntPair(int s, int a) { this.s = s;this.a = a; }
    
    public boolean equals(Object o) { if (!(o instanceof IntPair)) return false;
      return (s == s) && (a == a);
    }
    
    public String toString() { return "<" + s + "," + a + ">"; }
    
    public int hashCode() { return a + 1001 * s; }
    
    public int street() { return s; }
    public int avenue() { return a; }
  }
  



  public static final void resetRobots()
  {
    Enumeration e = robots.elements();
    while (e.hasMoreElements())
      ((UrRobot)e.nextElement()).deleteObserver(myObserver);
    robots.setSize(0);
    
    threads.stop();
    if (view != null) { view.resetControl();
    }
    threads = new ThreadGroup("RobotThread" + ++threadGroupNumber);
    threadVector.setSize(0);
  }
  
  public static final void reset()
  {
    beepers.clear();
    ewWalls.clear();
    nsWalls.clear();
    streets = 10;
    avenues = 10;
    Enumeration e = robots.elements();
    while (e.hasMoreElements())
      ((UrRobot)e.nextElement()).deleteObserver(myObserver);
    robots.setSize(0);
    
    threads.stop();
    if (view != null) { view.resetControl();
    }
    threads = new ThreadGroup("RobotThread" + ++threadGroupNumber);
    threadVector.setSize(0);
  }
  
  static class RobotObserver implements Observer { RobotObserver() {}
    
    public synchronized void update(Observable o, Object s) { if (World.tracing) System.out.println(o);
      if (World.view != null) { World.view.prepareToDraw((UrRobot.StateObject)s);
      }
    }
  }
  


  public static final void setDelay(int d)
  {
    if (d < 0) d = 0; delay = d;
  }
  
  public static final int delay() { return delay; }
  
  public static final void resume() {
    threads.resume();
  }
  
  public static final void stop() { threads.suspend(); }
  
  private static WorldBuilderInterface worldBuilder = null;
  





  public static final void setSize(int numberOfStreets, int numberOfAvenues)
  {
    streets = numberOfStreets > 0 ? numberOfStreets : 10;
    avenues = numberOfAvenues > 0 ? numberOfAvenues : 10;
    if (worldBuilder != null)
      worldBuilder.updateStreetsAvenues(streets, avenues);
    if (view != null)
    {
      view.scaleAllRobotImages();
      view.repaint();
    }
  }
  



  public static final void setTrace(boolean t) { tracing = t; }
  
  private static Vector threadVector = new Vector();
  






  public static final void setupThread(Runnable r)
  {
    if (view == null) { view = new RobotWorldWindow();
    }
    view.enableStop();
    Thread t = new Thread(threads, r);
    threadVector.addElement(t);
  }
  
  static void killBuilder()
  {
    if (worldBuilder != null) worldBuilder.cleanUp();
  }
  
  static void registerBuilder(WorldBuilderInterface wb) {
    worldBuilder = wb;
  }
  



  public static final void showSpeedControl(boolean show)
  {
    if (view == null) view = new RobotWorldWindow();
    view.showControlDialog(show);
  }
  


  public static final void setVisible(boolean show)
  {
    if ((show) && (view == null)) view = new RobotWorldWindow();
    if (view != null) view.setVisible(show);
    isVisible = show;
  }
  








  public static final void setVisible(boolean show, int width, int height)
  {
    setVisible(false);
    view.setSize(width, height);
    setVisible(show);
  }
  
  private static boolean isVisible = false;
  




  public static final void setVisible()
  {
    isVisible = !isVisible;
    setVisible(isVisible);
  }
  




  public static final void replaceCloser(WindowListener w)
  {
    if (w == null) { w = new WindowAdapter() {};
    }
    
    if (view != null) { view.replaceCloser(w);
    }
  }
  






  public static final void setStreetColor(Color color)
  {
    RobotWorldWindow.setStreetColor(color);
  }
  







  public static final void setNeutroniumColor(Color color)
  {
    RobotWorldWindow.setWallColor(color);
  }
  








  public static final void setBeeperColor(Color color)
  {
    RobotWorldWindow.setBeeperColor(color);
  }
  








  public static final void setWorldColor(Color color)
  {
    if (view != null) { view.setBackgroundColor(color);
    }
  }
  

  public static final void repaint()
  {
    if (view != null) { view.repaint();
    }
  }
  






  static final void startThreads()
  {
    Enumeration e = threadVector.elements();
    while (e.hasMoreElements()) {
      ((Thread)e.nextElement()).start();
    }
  }
  





  public static final void makeView()
  {
    view = new RobotWorldWindow();
  }
}
