package kareltherobot;

import java.awt.Button;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class WorldBuilder extends java.awt.Frame implements Directions, WorldBuilderInterface
{
  public void cleanUp()
  {
    if (isDirty)
    {
      saveFile();
    }
  }
  
  public WorldBuilder(boolean showSpeedControl) {
    super("World Builder");
    
    if (!showSpeedControl)
      showSpeed.setLabel("Show Speedcontrol");
    setSize(120, 380);
    setLocation(560, 30);
    
    closer = new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent e) {
        cleanUp();
        System.exit(0);
      }
      
    };
    addWindowListener(closer);
    view.replaceCloser(closer);
    
    Panel toolPanel = new Panel(new java.awt.GridLayout(3, 1, 0, 20));
    add("Center", toolPanel);
    
    Panel spacerPanel = new Panel(new java.awt.GridLayout(2, 1, 0, 5));
    
    Panel sizePanel = new Panel(new java.awt.GridLayout(2, 2, 0, 5));
    Label filler1 = new Label();
    filler1.setVisible(false);
    Label filler2 = new Label();
    filler2.setVisible(false);
    Panel innerSpacerPanel = new Panel(new java.awt.GridLayout(2, 1, 0, 5));
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
    
    Panel buttonPanel = new Panel(new java.awt.GridLayout(5, 1, 0, 5));
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
    
    Panel savePanel = new Panel(new java.awt.GridLayout(5, 1, 0, 5));
    toolPanel.add("South", savePanel);
    
    fileBar.setFont(displayFont);
    
    showSpeed.addActionListener(new SpeedListener());
    savePanel.add(save);
    savePanel.add(open);
    savePanel.add(fileBar);
    savePanel.add(clearAll);
    

    streets.addActionListener(new RowListener());
    avenues.addActionListener(new ColumnListener());
    horizontalWall.addActionListener(new HorizontalListener());
    verticalWall.addActionListener(new VerticalListener());
    beeper.addActionListener(new BeeperListener());
    save.addActionListener(new SaveListener());
    open.addActionListener(new OpenListener());
    clearAll.addActionListener(new ClearListener());
    
    buttonColor = beeper.getBackground();
    
    view.addMouseListener(itemDropper);
    
    streets.setText(World.numberOfStreets());
    avenues.setText(World.numberOfAvenues());
    
    view.attachMouseMotionListener(mouseWatcher);
    pack();
    
    setVisible(true);
    
    view.showControlDialog(showSpeedControl);
    

    World.registerBuilder(this);
  }
  






  private TextField streets = new TextField(5);
  private TextField avenues = new TextField(5);
  private Button horizontalWall = new Button("Horizontal Wall");
  private Button verticalWall = new Button("Vertical Wall");
  private Button beeper = new Button("Beeper");
  private Button selectedButton = null;
  private java.awt.event.WindowAdapter closer = null;
  
  private Button save = new Button("Save");
  private Button open = new Button("Open");
  private Button showSpeed = new Button("Hide Speedcontrol");
  
  private String filename = "untitled.kwld";
  private String directory = "";
  private boolean isDirty = false;
  private FileDialog openDialog = new FileDialog(this, null, 0);
  private FileDialog saveDialog = new FileDialog(this, null, 1);
  
  private Color buttonColor = null;
  private Color selectedColor = new Color(192, 255, 192);
  private Label statusBar = new Label("");
  private Label whereBar = new Label("");
  private Label fileBar = new Label(filename);
  private Button clearAll = new Button("Clear World");
  
  private ItemDropper itemDropper = new ItemDropper();
  
  private static final RobotWorldWindow view = ;
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
      WorldBuilder.view.repaint();
    }
  }
  
  private class SpeedListener implements ActionListener {
    SpeedListener() {}
    
    public void actionPerformed(ActionEvent e) {
      if (e.getActionCommand().indexOf("Show") > -1)
      {
        showSpeed.setLabel("Hide Speedcontrol");
        WorldBuilder.view.showControlDialog(true);
      }
      else
      {
        showSpeed.setLabel("Show Speedcontrol");
        WorldBuilder.view.showControlDialog(false);
      }
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
        WorldBuilder.view.ewCursor();
      }
      else {
        thisButton.setBackground(Color.lightGray);
        selectedButton = null;
        statusBar.setText("");
        whereBar.setText("");
        mouseWatcher.setScaler(null);
        itemDropper.setScaler(null);
        WorldBuilder.view.defaultCursor();
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
        WorldBuilder.view.nsCursor();
      }
      else {
        thisButton.setBackground(Color.lightGray);
        selectedButton = null;
        statusBar.setText("");
        whereBar.setText("");
        mouseWatcher.setScaler(null);
        itemDropper.setScaler(null);
        WorldBuilder.view.defaultCursor();
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
        WorldBuilder.view.beeperCursor();
      }
      else {
        thisButton.setBackground(Color.lightGray);
        selectedButton = null;
        statusBar.setText("");
        whereBar.setText("");
        mouseWatcher.setScaler(null);
        itemDropper.setScaler(null);
        WorldBuilder.view.defaultCursor();
      }
    }
  }
  
  private void saveFile() {
    saveDialog.setDirectory(directory);
    saveDialog.setFile(filename);
    saveDialog.show();
    String result = saveDialog.getFile();
    if (result != null) {
      filename = result;
      fileBar.setText(result);
      directory = saveDialog.getDirectory();
      
      World.saveWorld(directory, filename);
      isDirty = false;
    }
  }
  
  private class SaveListener implements ActionListener {
    SaveListener() {}
    
    public void actionPerformed(ActionEvent e) {
      WorldBuilder.this.saveFile();
      repaint();
    }
  }
  
  private class OpenListener implements ActionListener {
    OpenListener() {}
    
    public void actionPerformed(ActionEvent e) { openDialog.setDirectory(directory);
      openDialog.setFile(filename);
      openDialog.show();
      String result = openDialog.getFile();
      if (result != null)
      {
        filename = result;
        fileBar.setText(result);
        directory = openDialog.getDirectory();
        World.readWorld(directory, filename);
        streets.setText(World.numberOfStreets());
        avenues.setText(World.numberOfAvenues());
        WorldBuilder.view.repaint();
        isDirty = true;
      }
    }
  }
  
  private class RowListener implements ActionListener {
    RowListener() {}
    
    public void actionPerformed(ActionEvent e) {
      try {
        World.setSize(Integer.parseInt(streets.getText()), World.numberOfAvenues());
        WorldBuilder.view.setVisible(false);
        WorldBuilder.view.repaint();
        WorldBuilder.view.setVisible(true);
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
        WorldBuilder.view.setVisible(false);
        WorldBuilder.view.repaint();
        WorldBuilder.view.setVisible(true);
        isDirty = true;
      }
      catch (NumberFormatException localNumberFormatException) {}
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
  
  private class BeeperScaler implements WorldBuilder.MouseScaler {
    BeeperScaler() {}
    
    public void scale(int rawx, int rawy, Point result) {
      int scale = (WorldBuilder.view.bottom() - 10) / World.numberOfStreets();
      if (scale == 0) scale = 1;
      x = ((rawx - WorldBuilder.view.left() + scale / 2) / scale);
      y = ((WorldBuilder.view.bottom() - rawy + scale / 2) / scale);
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
      {

        World.placeBeepers(street, avenue, add ? 1 : -1);
      }
      







      WorldBuilder.view.setVisible(true);
    }
    
    private Point where = new Point();
  }
  
  private static final int fromTop = 10;
  private class HorizontalWallScaler implements WorldBuilder.MouseScaler { HorizontalWallScaler() {}
    
    public void scale(int rawx, int rawy, Point result) { int scale = (WorldBuilder.view.bottom() - 10) / World.numberOfStreets();
      if (scale == 0) scale = 1;
      x = ((rawx - WorldBuilder.view.left() + scale / 2) / scale);
      y = ((WorldBuilder.view.bottom() - rawy) / scale);
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
      




      WorldBuilder.view.setVisible(true);
    }
    

    private Point where = new Point();
  }
  
  private class VerticalWallScaler implements WorldBuilder.MouseScaler {
    VerticalWallScaler() {}
    
    public void scale(int rawx, int rawy, Point result) { int scale = (WorldBuilder.view.bottom() - 10) / World.numberOfStreets();
      if (scale == 0) scale = 1;
      x = ((rawx - WorldBuilder.view.left()) / scale);
      y = ((WorldBuilder.view.bottom() - rawy + scale / 2) / scale);
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
      




      WorldBuilder.view.setVisible(true);
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
    
    public void setScaler(WorldBuilder.MouseScaler scaler) {
      this.scaler = scaler;
    }
    
    private WorldBuilder.MouseScaler scaler = null;
    private Point where = new Point();
  }
  
  private class ItemDropper extends java.awt.event.MouseAdapter {
    ItemDropper() {}
    
    public void mouseClicked(MouseEvent evt) {
      if (scaler != null)
      {
        scaler.dropItem(evt);
        




















































        WorldBuilder.view.repaint();
        isDirty = true;
      }
    }
    

    public void setScaler(WorldBuilder.MouseScaler scaler)
    {
      this.scaler = scaler;
    }
    
    private WorldBuilder.MouseScaler scaler = null;
  }
  
  static WorldBuilder wb = null;
  
  public static void main(String[] args) {
    World.setVisible(true);
    wb = new WorldBuilder(false);
  }
}
