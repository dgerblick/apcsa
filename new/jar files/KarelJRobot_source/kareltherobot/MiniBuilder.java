package kareltherobot;

import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class MiniBuilder extends java.awt.Frame implements Directions, WorldBuilderInterface
{
  public void cleanUp() {}
  
  public MiniBuilder(boolean showSpeedControl)
  {
    super("World Builder");
    
    if (!showSpeedControl)
      showSpeed.setLabel("Show Speedcontrol");
    setSize(120, 380);
    setLocation(560, 30);
    
    Panel toolPanel = new Panel(new GridLayout(3, 1, 0, 20));
    add("Center", toolPanel);
    










    showSpeed.addActionListener(new SpeedListener());
    Panel spacerPanel = new Panel(new GridLayout(2, 1, 0, 5));
    
    Panel sizePanel = new Panel(new GridLayout(2, 2, 0, 5));
    Label filler1 = new Label();
    filler1.setVisible(false);
    Label filler2 = new Label();
    filler2.setVisible(false);
    Panel innerSpacerPanel = new Panel(new GridLayout(2, 1, 0, 5));
    innerSpacerPanel.add(new Label("Controller"));
    innerSpacerPanel.add(showSpeed);
    spacerPanel.add(innerSpacerPanel);
    spacerPanel.add(sizePanel);
    toolPanel.add("North", spacerPanel);
    
    streets.setFont(displayFont);
    avenues.setFont(displayFont);
    
    sizePanel.add(streets);
    sizePanel.add(new Label("Streets"));
    sizePanel.add(avenues);
    sizePanel.add(new Label("Avenues"));
    
    Panel buttonPanel = new Panel(new GridLayout(5, 1, 0, 5));
    toolPanel.add("Center", buttonPanel);
    
    whereBar.setAlignment(1);
    statusBar.setAlignment(1);
    whereBar.setFont(displayFont);
    statusBar.setFont(displayFont);
    buttonPanel.add(statusBar);
    buttonPanel.add(whereBar);
    buttonPanel.add(horizontalWall);
    buttonPanel.add(verticalWall);
    buttonPanel.add(beeper);
    
    Panel savePanel = new Panel(new GridLayout(5, 1, 0, 5));
    toolPanel.add("South", savePanel);
    

    savePanel.add(clearAll);
    
    streets.addActionListener(new RowListener());
    avenues.addActionListener(new ColumnListener());
    horizontalWall.addActionListener(new HorizontalListener());
    verticalWall.addActionListener(new VerticalListener());
    beeper.addActionListener(new BeeperListener());
    clearAll.addActionListener(new ClearListener());
    
    buttonColor = beeper.getBackground();
    
    view.addMouseListener(itemDropper);
    
    streets.setText(World.numberOfStreets());
    avenues.setText(World.numberOfAvenues());
    
    view.attachMouseMotionListener(mouseWatcher);
    if (!showSpeedControl) { view.showControlDialog(false);
    }
    pack();
    setVisible(true);
    World.registerBuilder(this);
    
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent e) {
        setVisible(false);
      }
    });
  }
  







  private TextField streets = new TextField(5);
  private TextField avenues = new TextField(5);
  private Button horizontalWall = new Button("Horizontal Wall");
  private Button verticalWall = new Button("Vertical Wall");
  private Button beeper = new Button("Beeper");
  private Button selectedButton = null;
  private Button showSpeed = new Button("Hide Speedcontrol");
  
  private boolean isDirty = false;
  
  private Color buttonColor = null;
  private Color selectedColor = new Color(192, 255, 192);
  private Label statusBar = new Label("");
  private Label whereBar = new Label("");
  private Button clearAll = new Button("Clear World");
  
  private ItemDropper itemDropper = new ItemDropper();
  
  private RobotWorldWindow view = World.view();
  
  private java.awt.Font displayFont = new java.awt.Font("Dialog", 0, 14);
  
  public void updateStreetsAvenues(int streets, int avenues) {
    this.streets.setText(streets);
    this.avenues.setText(avenues);
  }
  
  private class ClearListener implements ActionListener {
    ClearListener() {}
    
    public void actionPerformed(ActionEvent e) { World.reset();
      isDirty = true;
      streets.setText(World.numberOfStreets());
      avenues.setText(World.numberOfAvenues());
      repaint();
      view.repaint();
    }
  }
  
  private class HorizontalListener implements ActionListener {
    HorizontalListener() {}
    
    public void actionPerformed(ActionEvent e) { Button thisButton = (Button)e.getSource();
      if (thisButton != selectedButton) {
        ((Button)e.getSource()).setBackground(selectedColor);
        beeper.setBackground(Color.lightGray);
        verticalWall.setBackground(Color.lightGray);
        selectedButton = thisButton;
        statusBar.setText("Horizontal Wall");
        mouseWatcher.setScaler(horizontalWallScaler);
        itemDropper.setScaler(horizontalWallScaler);
        view.ewCursor();
      }
      else {
        thisButton.setBackground(Color.lightGray);
        selectedButton = null;
        statusBar.setText("");
        whereBar.setText("");
        mouseWatcher.setScaler(null);
        itemDropper.setScaler(null);
        view.defaultCursor();
      }
    }
  }
  
  private class VerticalListener implements ActionListener {
    VerticalListener() {}
    
    public void actionPerformed(ActionEvent e) { Button thisButton = (Button)e.getSource();
      if (thisButton != selectedButton) {
        ((Button)e.getSource()).setBackground(selectedColor);
        beeper.setBackground(Color.lightGray);
        horizontalWall.setBackground(Color.lightGray);
        selectedButton = thisButton;
        statusBar.setText("Vertical Wall");
        mouseWatcher.setScaler(verticalWallScaler);
        itemDropper.setScaler(verticalWallScaler);
        view.nsCursor();
      }
      else {
        thisButton.setBackground(Color.lightGray);
        selectedButton = null;
        statusBar.setText("");
        whereBar.setText("");
        mouseWatcher.setScaler(null);
        itemDropper.setScaler(null);
        view.defaultCursor();
      }
    }
  }
  
  private class BeeperListener implements ActionListener {
    BeeperListener() {}
    
    public void actionPerformed(ActionEvent e) { Button thisButton = (Button)e.getSource();
      if (thisButton != selectedButton) {
        ((Button)e.getSource()).setBackground(selectedColor);
        horizontalWall.setBackground(Color.lightGray);
        verticalWall.setBackground(Color.lightGray);
        selectedButton = thisButton;
        statusBar.setText("Beeper");
        mouseWatcher.setScaler(beeperScaler);
        itemDropper.setScaler(beeperScaler);
        view.beeperCursor();
      }
      else {
        thisButton.setBackground(Color.lightGray);
        selectedButton = null;
        statusBar.setText("");
        whereBar.setText("");
        mouseWatcher.setScaler(null);
        itemDropper.setScaler(null);
        view.defaultCursor();
      }
    }
  }
  
  private class RowListener implements ActionListener {
    RowListener() {}
    
    public void actionPerformed(ActionEvent e) {
      try { World.setSize(Integer.parseInt(streets.getText()), World.numberOfAvenues());
        view.setVisible(false);
        view.repaint();
        view.setVisible(true);
        isDirty = true;
      }
      catch (NumberFormatException localNumberFormatException) {}
    }
  }
  
  private class ColumnListener implements ActionListener
  {
    ColumnListener() {}
    
    public void actionPerformed(ActionEvent e) {
      try {
        World.setSize(World.numberOfStreets(), Integer.parseInt(avenues.getText()));
        view.setVisible(false);
        view.repaint();
        view.setVisible(true);
        isDirty = true;
      }
      catch (NumberFormatException localNumberFormatException) {}
    }
  }
  
  private class SpeedListener implements ActionListener
  {
    SpeedListener() {}
    
    public void actionPerformed(ActionEvent e)
    {
      if (e.getActionCommand().indexOf("Show") > -1)
      {
        showSpeed.setLabel("Hide Speedcontrol");
        view.showControlDialog(true);
      }
      else
      {
        showSpeed.setLabel("Show Speedcontrol");
        view.showControlDialog(false);
      }
    }
  }
  

  static abstract interface MouseScaler
  {
    public abstract void scale(int paramInt1, int paramInt2, Point paramPoint);
    
    public abstract void dropItem(MouseEvent paramMouseEvent);
  }
  
  private BeeperScaler beeperScaler = new BeeperScaler();
  private HorizontalWallScaler horizontalWallScaler = new HorizontalWallScaler();
  private VerticalWallScaler verticalWallScaler = new VerticalWallScaler();
  private MouseWatcher mouseWatcher = new MouseWatcher();
  
  private class BeeperScaler implements MiniBuilder.MouseScaler {
    BeeperScaler() {}
    
    public void scale(int rawx, int rawy, Point result) { int scale = (view.bottom() - 10) / World.numberOfStreets();
      if (scale == 0) scale = 1;
      x = ((rawx - view.left() + scale / 2) / scale);
      y = ((view.bottom() - rawy + scale / 2) / scale);
    }
    
    public void dropItem(MouseEvent evt)
    {
      scale(evt.getX(), evt.getY(), where);
      int street = where.y;
      int avenue = where.x;
      if ((street < 1) || (avenue < 1) || 
        (street > World.numberOfStreets()) || (avenue > World.numberOfAvenues())) return;
      int keyMask = 2;
      if (System.getProperty("os.name").indexOf("Mac") > -1)
        keyMask = 8;
      boolean add = (evt.getModifiers() & keyMask) == 0;
      
      if ((evt.getModifiers() & 0x1) != 0) {
        World.clearBeepers(street, avenue);
      }
      else
        World.placeBeepers(street, avenue, add ? 1 : -1);
      view.setVisible(true);
    }
    
    private Point where = new Point();
  }
  
  private static final int fromTop = 10;
  private class HorizontalWallScaler implements MiniBuilder.MouseScaler { HorizontalWallScaler() {}
    
    public void scale(int rawx, int rawy, Point result) { int scale = (view.bottom() - 10) / World.numberOfStreets();
      if (scale == 0) scale = 1;
      x = ((rawx - view.left() + scale / 2) / scale);
      y = ((view.bottom() - rawy) / scale);
    }
    
    public void dropItem(MouseEvent evt)
    {
      scale(evt.getX(), evt.getY(), where);
      int street = where.y;
      int avenue = where.x;
      if ((street < 1) || (avenue < 1) || 
        (street > World.numberOfStreets() - 1) || (avenue > World.numberOfAvenues())) return;
      int keyMask = 2;
      if (System.getProperty("os.name").indexOf("Mac") > -1)
        keyMask = 8;
      boolean add = (evt.getModifiers() & keyMask) == 0;
      
      if (add)
      {
        World.placeEWWall(street, avenue, 1);
      }
      else {
        World.removeEWWall(street, avenue);
      }
      view.setVisible(true);
    }
    

    private Point where = new Point();
  }
  
  private class VerticalWallScaler implements MiniBuilder.MouseScaler {
    VerticalWallScaler() {}
    
    public void scale(int rawx, int rawy, Point result) { int scale = (view.bottom() - 10) / World.numberOfStreets();
      if (scale == 0) scale = 1;
      x = ((rawx - view.left()) / scale);
      y = ((view.bottom() - rawy + scale / 2) / scale);
    }
    
    public void dropItem(MouseEvent evt)
    {
      scale(evt.getX(), evt.getY(), where);
      int street = where.y;
      int avenue = where.x;
      if ((street < 1) || (avenue < 1) || 
        (street > World.numberOfStreets()) || (avenue > World.numberOfAvenues() - 1)) return;
      int keyMask = 2;
      if (System.getProperty("os.name").indexOf("Mac") > -1)
        keyMask = 8;
      boolean add = (evt.getModifiers() & keyMask) == 0;
      
      if (add) {
        World.placeNSWall(street, avenue, 1);
      } else {
        World.removeNSWall(street, avenue);
      }
      view.setVisible(true);
    }
    

    private Point where = new Point();
  }
  
  private class MouseWatcher extends java.awt.event.MouseMotionAdapter { MouseWatcher() {}
    
    public void mouseMoved(MouseEvent evt) { if (scaler != null) {
        scaler.scale(evt.getX(), evt.getY(), where);
        int avenue = where.x;
        int street = where.y;
        if (avenue < 1) avenue = 1;
        if (street < 1) street = 1;
        whereBar.setText(street + ", " + avenue);
        repaint();
      }
    }
    
    public void setScaler(MiniBuilder.MouseScaler scaler) {
      this.scaler = scaler;
    }
    
    private MiniBuilder.MouseScaler scaler = null;
    private Point where = new Point();
  }
  
  private class ItemDropper extends java.awt.event.MouseAdapter {
    ItemDropper() {}
    
    public void mouseClicked(MouseEvent evt) {
      if (scaler != null)
      {
        scaler.dropItem(evt);
        
        view.repaint();
        isDirty = true;
      }
    }
    
    public void setScaler(MiniBuilder.MouseScaler scaler)
    {
      this.scaler = scaler;
    }
    
    private MiniBuilder.MouseScaler scaler = null;
  }
  
  static WorldBuilder wb = null;
  
  public static void main(String[] args) {
    wb = new WorldBuilder(false);
  }
}
