package kareltherobot;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class RobotWorldWindow extends java.awt.Frame
{
  public java.awt.Dimension getPreferredSize()
  {
    return new java.awt.Dimension(worldWidth + 10, worldHeight + 20);
  }
  
  public java.awt.Dimension innerDimension() {
    java.awt.Dimension base = getSize();
    height -= 20;
    width -= 10;
    return base;
  }
  
  public RobotWorldWindow()
  {
    this(getDefaultToolkitgetScreenSizeheight - 20);
  }
  


  public RobotWorldWindow(int height)
  {
    super("Karel J. Robot");
    worldHeight = height;
    worldWidth = height + 80;
    setSize(worldWidth + 10, worldHeight + 20);
    setLocation(5, 10);
    java.awt.ScrollPane scroller = new java.awt.ScrollPane();
    worldView = new ViewCanvas();
    scroller.add(worldView);
    
    add("Center", scroller);
    
    addWindowListener(closer);
    setBackground(java.awt.Color.white);
    repaint();
    controlThread.start();
    




    toolkit = getToolkit();
    String suffix = ".png";
    karelImageNOnBase = toolkit.getImage(RobotWorldWindow.class.getResource("kareln" + suffix));
    karelImageSOnBase = toolkit.getImage(RobotWorldWindow.class.getResource("karels" + suffix));
    karelImageEOnBase = toolkit.getImage(RobotWorldWindow.class.getResource("karele" + suffix));
    karelImageWOnBase = toolkit.getImage(RobotWorldWindow.class.getResource("karelw" + suffix));
    
    java.awt.MediaTracker track = new java.awt.MediaTracker(this);
    track.addImage(karelImageNOnBase, 0);
    track.addImage(karelImageSOnBase, 1);
    track.addImage(karelImageEOnBase, 2);
    track.addImage(karelImageWOnBase, 3);
    try
    {
      track.waitForID(0);
      track.waitForID(1);
      track.waitForID(2);
      track.waitForID(3);
    }
    catch (InterruptedException localInterruptedException) {}
    




    karelImageNOffBase = toolkit.getImage(RobotWorldWindow.class.getResource("karelnOff" + suffix));
    karelImageSOffBase = toolkit.getImage(RobotWorldWindow.class.getResource("karelsOff" + suffix));
    karelImageEOffBase = toolkit.getImage(RobotWorldWindow.class.getResource("kareleOff" + suffix));
    karelImageWOffBase = toolkit.getImage(RobotWorldWindow.class.getResource("karelwOff" + suffix));
    

    track.addImage(karelImageNOffBase, 0);
    track.addImage(karelImageSOffBase, 1);
    track.addImage(karelImageEOffBase, 2);
    track.addImage(karelImageWOffBase, 3);
    try
    {
      track.waitForID(0);
      track.waitForID(1);
      track.waitForID(2);
      track.waitForID(3);
    }
    catch (InterruptedException localInterruptedException1) {}
    




    scaleAllRobotImages();
    




    addComponentListener(
      new java.awt.event.ComponentAdapter()
      {
        public void componentResized(java.awt.event.ComponentEvent evt) {
          scaleAllRobotImages();
          repaint();







        }
        







      });
    defaultCursor = getCursor();
    
    nsCursor = defaultCursor;
    ewCursor = defaultCursor;
    beeperCursor = defaultCursor;
    nsDelete = defaultCursor;
    ewDelete = defaultCursor;
    beeperDelete = defaultCursor;
    



    try
    {
      nsCursorImage = toolkit.getImage(RobotWorldWindow.class.getResource("nscursor.gif"));
      ewCursorImage = toolkit.getImage(RobotWorldWindow.class.getResource("ewcursor.gif"));
      beeperCursorImage = toolkit.getImage(RobotWorldWindow.class.getResource("beepercursor.gif"));
      nsDeleteImage = toolkit.getImage(RobotWorldWindow.class.getResource("nsdeletecursor.gif"));
      ewDeleteImage = toolkit.getImage(RobotWorldWindow.class.getResource("ewdeletecursor.gif"));
      beeperDeleteImage = toolkit.getImage(RobotWorldWindow.class.getResource("beeperdeletecursor.gif"));
      
      track = new java.awt.MediaTracker(this);
      track.addImage(nsCursorImage, 0);
      track.addImage(ewCursorImage, 1);
      track.addImage(beeperCursorImage, 2);
      track.addImage(nsDeleteImage, 3);
      track.addImage(ewDeleteImage, 4);
      track.addImage(beeperDeleteImage, 5);
      try
      {
        track.waitForID(0);
        track.waitForID(1);
        track.waitForID(2);
        track.waitForID(3);
        track.waitForID(4);
        track.waitForID(5);
      }
      catch (InterruptedException localInterruptedException2) {}
      ewCursor = toolkit.createCustomCursor(ewCursorImage, new java.awt.Point(8, 8), "ewWall");
      nsCursor = toolkit.createCustomCursor(nsCursorImage, new java.awt.Point(8, 8), "nsWall");
      beeperCursor = toolkit.createCustomCursor(beeperCursorImage, new java.awt.Point(8, 8), "beeper");
      ewDelete = toolkit.createCustomCursor(ewDeleteImage, new java.awt.Point(8, 8), "ewDelete");
      nsDelete = toolkit.createCustomCursor(nsDeleteImage, new java.awt.Point(8, 8), "nsDelete");
      beeperDelete = toolkit.createCustomCursor(beeperDeleteImage, new java.awt.Point(8, 8), "beeperDelete");

    }
    catch (Throwable e1)
    {

      System.out.println("Failed to create the custom cursors.");
    }
    
    setVisible(false);
  }
  
  public final void showControlDialog(boolean show) {
    controlThread.showDialog(show);
  }
  






  public synchronized void scaleAllRobotImages()
  {
    int scale = (bottom() - 10) / World.numberOfStreets();
    if (scale == 0) scale = 1;
    int scaleVal = scale * 7 / 9;
    if (scaleVal < 10) scaleVal = 10;
    if ((oldScale > 0) && (4 * oldScale < 5 * scaleVal) && (5 * scaleVal < 6 * oldScale)) return;
    scaleInset = scaleVal;
    robotsOn[3] = karelImageNOnBase.getScaledInstance(scaleVal, -1, 4);
    
    robotsOn[1] = karelImageSOnBase.getScaledInstance(scaleVal, -1, 4);
    
    robotsOn[0] = karelImageEOnBase.getScaledInstance(scaleVal, -1, 4);
    
    robotsOn[2] = karelImageWOnBase.getScaledInstance(scaleVal, -1, 4);
    

    robotsOff[3] = karelImageNOffBase.getScaledInstance(scaleVal, -1, 4);
    
    robotsOff[1] = karelImageSOffBase.getScaledInstance(scaleVal, -1, 4);
    
    robotsOff[0] = karelImageEOffBase.getScaledInstance(scaleVal, -1, 4);
    
    robotsOff[2] = karelImageWOffBase.getScaledInstance(scaleVal, -1, 4);
    

    java.awt.MediaTracker track = new java.awt.MediaTracker(this);
    for (int i = 0; i < 4; i++)
    {
      track.addImage(robotsOn[i], 2 * i);
      track.addImage(robotsOff[i], 2 * i + 1);
    }
    try
    {
      for (int i = 0; i < 8; i++) {
        track.waitForID(i);
      }
    }
    catch (InterruptedException localInterruptedException) {}
    

    oldScale = scaleVal;
    
    repaint();
  }
  
  public void addMouseListener(java.awt.event.MouseListener m)
  {
    worldView.addMouseListener(m);
  }
  
  public void attachMouseMotionListener(java.awt.event.MouseMotionListener m) {
    worldView.addMouseMotionListener(m);
  }
  
  void defaultCursor() {
    toFront();
    worldView.setCursor(defaultCursor);
    
    worldView.requestFocus();
  }
  
  void ewCursor() {
    toFront();
    worldView.setCursor(ewCursor);
    
    worldView.setVisible(true);
    worldView.requestFocus();
  }
  
  void nsCursor() {
    toFront();
    worldView.setCursor(nsCursor);
    
    worldView.requestFocus();
  }
  
  void beeperCursor() {
    toFront();
    worldView.setCursor(beeperCursor);
    
    worldView.requestFocus();
  }
  































  public final void setMac(boolean mac)
  {
    isMac = mac;
  }
  


  public void enableStop() { controlThread.stop.setEnabled(true); }
  
  private static int scaleInset = 0;
  private static int oldScale = 0;
  
  public static int scaleInset()
  {
    return scaleInset;
  }
  








  public Image scale(Image aRobot, int scaleVal, int hint)
  {
    return aRobot.getScaledInstance(scaleVal, -1, hint);
  }
  


  private static java.awt.Toolkit toolkit = null;
  private static boolean isMac = false;
  private static Image karelImageNOnBase;
  private static Image karelImageSOnBase;
  private static Image karelImageEOnBase;
  private static Image karelImageWOnBase;
  private static Image karelImageNOffBase;
  private static Image karelImageSOffBase;
  private static Image karelImageEOffBase;
  private static Image karelImageWOffBase;
  private static Image nsCursorImage;
  private static Image ewCursorImage;
  private static Image beeperCursorImage; private static Image nsDeleteImage; private static Image ewDeleteImage; private static Image beeperDeleteImage; private static java.awt.Cursor defaultCursor; private static java.awt.Cursor nsCursor; private static java.awt.Cursor ewCursor; private static java.awt.Cursor beeperCursor; private static java.awt.Cursor nsDelete; private static java.awt.Cursor ewDelete; private static java.awt.Cursor beeperDelete; private java.awt.event.WindowListener closer = new CloseListener();
  private static Image[] robotsOn = new Image[4];
  private static Image[] robotsOff = new Image[4];
  
  void replaceCloser(java.awt.event.WindowListener other) {
    removeWindowListener(closer);
    closer = other;
    addWindowListener(closer);
  }
  
  private java.awt.Label whereBar = new java.awt.Label("");
  private MouseWatcher mouser = new MouseWatcher();
  
  private class CornerScaler implements WorldBuilder.MouseScaler
  {
    CornerScaler() {}
    
    public void scale(int rawx, int rawy, java.awt.Point result) {
      RobotWorldWindow view = World.view();
      
      int scale = (view.bottom() - 10) / World.numberOfStreets();
      if (scale == 0) { scale = 1;
      }
      x = ((rawx - view.left() + scale / 2) / scale);
      y = ((view.bottom() - rawy + scale / 2) / scale);
    }
    
    public void dropItem(java.awt.event.MouseEvent evt) {}
  }
  
  private class MouseWatcher extends java.awt.event.MouseMotionAdapter
  {
    MouseWatcher() {}
    
    public void mouseMoved(java.awt.event.MouseEvent evt) {
      scaler.scale(evt.getX(), evt.getY(), where);
      int avenue = where.x;
      int street = where.y;
      if (avenue < 1) avenue = 1;
      if (street < 1) street = 1;
      whereBar.setText(street + ", " + avenue);
      whereBar.repaint();
    }
    
    private WorldBuilder.MouseScaler scaler = new RobotWorldWindow.CornerScaler(RobotWorldWindow.this);
    private java.awt.Point where = new java.awt.Point();
  }
  
  public void resetControl() {
    controlThread.reset();
  }
  
  class ControlThread extends Thread
  {
    public ControlThread() {
      stop.setEnabled(false);
      







      frame.setResizable(false);
    }
    
    public void run() {
      SpeedListener speedListener = new SpeedListener();
      speed.addAdjustmentListener(speedListener);
      frame.setLocation(450, 400);
      frame.setSize(210, 90);
      controls.setSize(200, 80);
      controls.setLayout(new java.awt.GridLayout(2, 2));
      frame.add(controls);
      speed.setValues(0, 0, 0, 101);
      speed.setSize(300, speed.getSize().height);
      speed.setValue(100 - World.delay());
      stop.addActionListener(new StopListener());
      whereBar.setBackground(java.awt.Color.white);
      controls.add(stop);
      whereBar.setFont(displayFont);
      controls.add(whereBar);
      java.awt.Label speedLabel = new java.awt.Label("Set Speed", 2);
      speedLabel.setFont(displayFont);
      controls.add(speedLabel);
      java.awt.Panel spacer = new java.awt.Panel();
      spacer.setLayout(new java.awt.BorderLayout());
      
      spacer.add(new java.awt.Panel(), "North");
      spacer.add(speed);
      spacer.add(new java.awt.Panel(), "South");
      controls.add(spacer);
      


      frame.addWindowListener(new DialogHider());
      frame.setVisible(false);
    }
    
    public final void showDialog(boolean show) {
      controls.setVisible(show);
      controls.repaint();
      frame.setVisible(show);
    }
    
    public final void doStop()
    {
      frame.setVisible(false);
      stop();
    }
    
    private java.awt.Dialog frame = new java.awt.Dialog(new java.awt.Frame("empty"), "Control");
    private java.awt.Button stop = new java.awt.Button("Resume");
    private java.awt.Panel controls = new java.awt.Panel();
    private java.awt.Scrollbar speed = new java.awt.Scrollbar(0);
    
    private boolean started = false;
    
    private class DialogHider extends java.awt.event.WindowAdapter { DialogHider() {}
      
      public void windowClosing(java.awt.event.WindowEvent e) { showDialog(false); }
    }
    
    private class SpeedListener implements java.awt.event.AdjustmentListener { SpeedListener() {}
      
      public void adjustmentValueChanged(java.awt.event.AdjustmentEvent e) { delay = speed.getValue();
        World.setDelay(100 - delay);
        worldView.repaint();
      }
    }
    








    public void reset() { started = false; }
    
    private class StopListener implements java.awt.event.ActionListener {
      StopListener() {}
      
      public void actionPerformed(java.awt.event.ActionEvent e) { if (e.getActionCommand().equals("Stop")) {
          stop.setLabel("Resume");
          World.stop();
        }
        else {
          stop.setLabel("Stop");
          if (started) {
            World.resume();
          }
          else {
            started = true;
            World.startThreads();
          }
        }
      }
    }
  }
  
  public void reset() {
    controlThread.stop.setLabel("Resume");
    World.stop();
    worldView.repaint();
  }
  
  private Rectangle getClip(UrRobot.StateObject so)
  {
    Rectangle r = new Rectangle();
    int street = so.street();
    int avenue = so.avenue();
    int adjust = scaleInset() / 2;
    int logicalBottomEdge = logicalBottomEdge();
    




    int scale = getScale();
    x = (15 + (avenue - 1) * scale - adjust);
    y = (logicalBottomEdge - (street + 1) * scale - adjust);
    int cells = 2;
    if (so.lastAction() == 0) cells = 3;
    height = (cells * scale);
    width = (cells * scale);
    

    return r;
  }
  

  public synchronized void prepareToDraw(UrRobot.StateObject so)
  {
    Rectangle r = getClip(so);
    worldView.repaint(x, y, width, height);
  }
  
  public synchronized void repaint() { worldView.repaint(); }
  
  public int getScale()
  {
    int worldHeight = innerDimensionheight;
    int streets = World.numberOfStreets();
    if (streets <= 0) streets = 10;
    int result = (worldHeight - 15 - 10) / streets;
    if (result <= 0) { result = 1;
    }
    

    return result;
  }
  
  private int logicalBottomEdge() {
    return innerDimensionheight - 15 - 10;
  }
  

  class ViewCanvas
    extends java.awt.Canvas
    implements Directions
  {
    class ControlKeyListener
      implements java.awt.event.KeyListener
    {
      ControlKeyListener() {}
      
      public void keyTyped(java.awt.event.KeyEvent e) {}
      
      public void keyPressed(java.awt.event.KeyEvent e)
      {
        java.awt.Cursor cursor = getCursor();
        
        boolean shift = e.isShiftDown();
        boolean control = e.isControlDown();
        boolean shiftOrControl = (shift) || (control);
        if (!shiftOrControl) return;
        if ((control) && (cursor.equals(RobotWorldWindow.ewCursor))) {
          setCursor(RobotWorldWindow.ewDelete);
        }
        else if ((control) && (cursor.equals(RobotWorldWindow.nsCursor))) {
          setCursor(RobotWorldWindow.nsDelete);
        }
        else if ((shiftOrControl) && (cursor.equals(RobotWorldWindow.beeperCursor))) {
          setCursor(RobotWorldWindow.beeperDelete);
        }
      }
      
      public void keyReleased(java.awt.event.KeyEvent e)
      {
        java.awt.Cursor cursor = getCursor();
        
        int code = e.getKeyCode();
        boolean control = code == 17;
        boolean shift = code == 16;
        boolean shiftOrControl = (shift) || (control);
        if (!shiftOrControl) return;
        if ((control) && (cursor.equals(RobotWorldWindow.ewDelete))) {
          setCursor(RobotWorldWindow.ewCursor);
        }
        else if ((control) && (cursor.equals(RobotWorldWindow.nsDelete))) {
          setCursor(RobotWorldWindow.nsCursor);
        }
        else if ((shiftOrControl) && (cursor.equals(RobotWorldWindow.beeperDelete))) {
          setCursor(RobotWorldWindow.beeperCursor);
        }
      }
    }
    
    public ViewCanvas()
    {
      addMouseMotionListener(mouser);
      addKeyListener(new ControlKeyListener());
    }
    
    public boolean isDoubleBuffered() {
      return true;
    }
    






    public java.awt.Dimension getPreferredSize()
    {
      java.awt.Dimension world = innerDimension();
      

      int width = width;
      

      int height = height - 30;
      int bigWidth = getScale() * (World.numberOfAvenues() + 1);
      width = bigWidth;
      return new java.awt.Dimension(width, height);
    }
    




    public synchronized void paint(Graphics g)
    {
      int streets = World.numberOfStreets();
      int avenues = World.numberOfAvenues();
      int wallSize = RobotWorldWindow.this.wallSize();
      RobotWorldWindow.lastSize = innerDimension();
      if (streets <= 0) streets = 10;
      if (avenues <= 0) avenues = 10;
      int bottomEdge = RobotWorldWindow.this.logicalBottomEdge();
      

      scale = getScale();
      setBackground(RobotWorldWindow.backgroundColor);
      g.setColor(RobotWorldWindow.streetColor);
      for (int i = 1; i <= streets; i++)
        g.drawLine(15 + scale / 2, bottomEdge - i * scale, 15 + avenues * scale, bottomEdge - i * scale);
      for (int i = 1; i <= avenues; i++)
        g.drawLine(15 + i * scale, bottomEdge - scale / 2, 15 + i * scale, bottomEdge - streets * scale);
      g.setColor(RobotWorldWindow.wallColor);
      g.fillRect(15 - wallSize + scale / 2, bottomEdge - streets * scale, wallSize, streets * scale - scale / 2 + wallSize);
      g.fillRect(15 + scale / 2, bottomEdge - scale / 2, avenues * scale - scale / 2, wallSize);
      g.setColor(java.awt.Color.black);
      if (streets <= 50)
      {
        int i = 0;
        int x = 0;
        int y = bottomEdge - scale / 2 + 15;
        for (i = 1; i <= avenues; i++)
        {
          g.drawString(String.valueOf(i), 15 + i * scale - 5, y);
        }
        x = 15 - wallSize + scale / 2 - 15;
        for (i = 1; i <= streets; i++) {
          g.drawString(String.valueOf(i), x, bottomEdge - i * scale + 5);
        }
      }
      
      g.setColor(RobotWorldWindow.wallColor);
      java.util.Enumeration e = World.ewWalls();
      while (e.hasMoreElements()) {
        World.IntPair p = (World.IntPair)e.nextElement();
        drawHWall(p.street(), p.avenue(), g);
      }
      e = World.nsWalls();
      while (e.hasMoreElements()) {
        World.IntPair p = (World.IntPair)e.nextElement();
        drawVWall(p.street(), p.avenue(), g);
      }
      e = World.beepers();
      g.setColor(RobotWorldWindow.beeperColor);
      while (e.hasMoreElements()) {
        World.BeeperCell p = (World.BeeperCell)e.nextElement();
        drawBeeper(p.street(), p.avenue(), p.number(), g, g.getColor());
      }
      e = World.robots();
      while (e.hasMoreElements()) {
        UrRobot p = (UrRobot)e.nextElement();
        drawRobot(p, g);
      }
    }
    

































































    private int x1 = 0; private int y1 = 0; private int x2 = 0; private int y2 = 0;
    private int oldStreet = 0; private int oldAvenue = 0; private int oldFacing = 3;
    private boolean moved = false; private boolean turned = false; private boolean picked = false;
    
    private Image offScreen = null;
    private Graphics osg = null;
    private boolean resized = false;
    


    private int scale;
    


    public final synchronized void update(Graphics g)
    {
      super.update(g);
      

      resized = (!innerDimension().equals(RobotWorldWindow.lastSize));
      int worldHeight = innerDimension().height;
      int worldWidth = 
        15 + (World.numberOfAvenues() + 1) * getScale();
      


      if (resized) {
        offScreen = null;
        resized = false;
      }
      if ((resized) || (offScreen == null)) {
        offScreen = createImage(worldWidth, worldHeight);
        resized = false;
      }
      if (offScreen != null) {
        if (osg != null) osg.dispose();
        osg = offScreen.getGraphics();
        
        if (RobotWorldWindow.isMac) {
          osg.setClip(x1, y1, x2 - x1, y2 - y1);
          if ((moved) || (turned)) drawOldRobot(oldStreet, oldAvenue, oldFacing, true, osg, getBackground());
          if (picked) drawBeeper(oldStreet, oldAvenue, 1, osg, getBackground());
        }
        paint(osg);
        if (RobotWorldWindow.isMac) {
          g.drawImage(offScreen, x1, y1, x2, y2, x1, y1, x2, y2, null);
          moved = false;
          turned = false;
          picked = false;
        }
        else {
          g.drawImage(offScreen, 0, 0, null);
          offScreen.flush();
          offScreen = null;
        }
      }
    }
    









    private void drawHWall(int s, int a, Graphics g)
    {
      g.fillRect(15 + a * scale - scale / 2, RobotWorldWindow.this.logicalBottomEdge() - s * scale - scale / 2, scale, RobotWorldWindow.this.wallSize());
    }
    
    private void drawVWall(int s, int a, Graphics g) { g.fillRect(15 + a * scale + scale / 2, RobotWorldWindow.this.logicalBottomEdge() - s * scale - scale / 2, RobotWorldWindow.this.wallSize(), scale + RobotWorldWindow.this.wallSize()); }
    
    private synchronized void drawBeeper(int s, int a, int howMany, Graphics g, java.awt.Color c) {
      java.awt.Color oldColor = g.getColor();
      g.setColor(c);
      g.fillOval(15 + a * scale - scale / 4, RobotWorldWindow.this.logicalBottomEdge() - s * scale - scale / 4, scale / 2, scale / 2);
      g.setColor(java.awt.Color.white);
      String name = howMany > 0 ? howMany : "oo";
      g.drawString(name, 15 + a * scale - scale / 8, RobotWorldWindow.this.logicalBottomEdge() - s * scale + scale / 8);
      g.setColor(oldColor);
    }
    
    private synchronized java.awt.Point locationToPixels(int street, int avenue) {
      int scale = getScale();
      int x = 15 + scale * avenue;
      int y = RobotWorldWindow.this.logicalBottomEdge() - scale * street;
      return new java.awt.Point(x, y);
    }
    
    private synchronized Rectangle robotCenter(UrRobot karel) {
      int s = karel.street();
      int a = karel.avenue();
      java.awt.Point center = locationToPixels(s, a);
      
      int size = getScale() / 12;
      if (size < 2) size = 2;
      if (karel.crashed()) size++;
      return new Rectangle(x - size, y - size, 2 * size, 2 * size);
    }
    
    private synchronized void drawRobot(UrRobot karel, Graphics g) {
      if (!karel.isVisible()) return;
      int s = karel.street();
      int a = karel.avenue();
      

      Image[] robots = RobotWorldWindow.robotsOn;
      if (!karel.running()) robots = RobotWorldWindow.robotsOff;
      Image karelImage = robots[karel.direction().points()];
      























      int adjust = RobotWorldWindow.scaleInset() / 2;
      g.drawImage(karelImage, 15 + a * scale - adjust, RobotWorldWindow.this.logicalBottomEdge() - s * scale - adjust, this);
      if (karel.badgeColor() != null) {
        g.setColor(karel.badgeColor());
        
        Rectangle center = robotCenter(karel);
        g.fillRect(x, y, width, height);
        g.setColor(java.awt.Color.black);
        if (karel.badgeColor().equals(java.awt.Color.black)) g.setColor(java.awt.Color.red);
        g.drawRect(x, y, width, height);
        if (karel.crashed())
        {
          drawCrashed(center, g);
        }
        

      }
      else if (karel.crashed()) {
        g.setColor(java.awt.Color.white);
        Rectangle center = robotCenter(karel);
        g.drawRect(x, y, width, height);
        drawCrashed(center, g);
      }
    }
    


    private void drawCrashed(Rectangle center, Graphics g)
    {
      g.drawLine(x, y, x + width, y + width);
      g.drawLine(x, y + width, x + width, y);
    }
    



    private synchronized void drawOldRobot(int s, int a, int facing, boolean running, Graphics g, java.awt.Color c)
    {
      Image[] robots = RobotWorldWindow.robotsOn;
      if (!running) robots = RobotWorldWindow.robotsOff;
      Image karelImage = robots[facing];
      


























      g.drawImage(karelImage, 15 + a * scale - 12, RobotWorldWindow.this.logicalBottomEdge() - s * scale - 12, null);
    }
  }
  







  public int bottom()
  {
    return logicalBottomEdge() + 5;
  }
  
  public int left() {
    return 15;
  }
  
  private int wallSize() {
    int result = 4;
    int streets = World.numberOfStreets();
    if (streets > 20) result /= 2;
    if (streets > 60) result /= 2;
    if (streets == 0) result = 1;
    return result;
  }
  
  static final void setStreetColor(java.awt.Color c) {
    if (c != null) streetColor = c;
  }
  
  static final void setWallColor(java.awt.Color c) {
    if (c != null) wallColor = c;
  }
  
  static final void setBeeperColor(java.awt.Color c) {
    if (c != null) beeperColor = c;
  }
  
  final void setBackgroundColor(java.awt.Color c) {
    worldView.setBackground(c);
    backgroundColor = c;
  }
  

  private static int worldHeight = 0;
  private static int worldWidth = worldHeight + 80;
  private static java.awt.Dimension lastSize = new java.awt.Dimension(0, 0);
  private static final int fromTop = 10;
  private static final int leftEdge = 15;
  private static final int delta = 5;
  private static java.awt.Color streetColor = java.awt.Color.red.darker();
  private static java.awt.Color wallColor = java.awt.Color.black;
  private static java.awt.Color beeperColor = java.awt.Color.black;
  private static java.awt.Color backgroundColor = java.awt.Color.white;
  private static int bottomEdge = worldHeight - 15 - 5;
  
  private static final int wallSize = 4;
  private ViewCanvas worldView = null;
  private int delay = 100;
  private ControlThread controlThread = new ControlThread();
  private java.awt.Font displayFont = new java.awt.Font("Dialog", 0, 14);
  
  java.awt.Canvas worldCanvas() {
    return worldView;
  }
  
  private class CloseListener extends java.awt.event.WindowAdapter {
    CloseListener() {}
    
    public void windowClosing(java.awt.event.WindowEvent e) {
      e.getWindow().setVisible(false);
      controlThread.doStop();
      e.getWindow().dispose();
      World.killBuilder();
      World.stop();
      System.exit(0);
    }
  }
}
