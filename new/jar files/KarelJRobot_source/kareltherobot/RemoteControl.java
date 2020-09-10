package kareltherobot;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

















public class RemoteControl
  extends Frame
  implements Directions
{
  public RemoteControl(int street, int avenue, Directions.Direction direction, int beepers)
  {
    this(street, avenue, direction, beepers, null);
  }
  
  public RemoteControl(int street, int avenue, Directions.Direction direction) {
    this(street, avenue, direction, -1, null);
  }
  
  public RemoteControl() {
    this(1, 1, North, -1, null);
  }
  
  public RemoteControl(int street, int avenue, Directions.Direction direction, int beepers, Color color)
  {
    super("Robot " + getID() + " Controller");
    if (color != null) setBackground(color);
    karel = new UrRobot(street, avenue, direction, beepers, color);
    
    setLayout(new GridLayout(3, 2));
    Button move = new Button("Move");
    move.addActionListener(new Mover());
    add(move);
    Button turn = new Button("Turn Left");
    turn.addActionListener(new LeftTurner());
    add(turn);
    Button pick = new Button("Pick Beeper");
    pick.addActionListener(new Picker());
    add(pick);
    Button put = new Button("Put Beeper");
    put.addActionListener(new Putter());
    add(put);
    Button stop = new Button("Turn Off");
    stop.addActionListener(new Stopper());
    add(stop);
    
    addWindowListener(new Hider());
    setSize(300, 150);
    setLocation(560 + delta * (id - 1), 100 + delta * (id - 1));
    setVisible(true);
  }
  
  private static int getID() {
    return id++;
  }
  
  private static int id = 0;
  private static int delta = 10;
  private UrRobot karel = null;
  
  private class Hider extends WindowAdapter { Hider() {}
    
    public void windowClosing(WindowEvent e) { setVisible(false); }
  }
  
  private class Stopper implements ActionListener {
    Stopper() {}
    
    public void actionPerformed(ActionEvent e) { karel.turnOff(); }
  }
  
  private class Putter implements ActionListener {
    Putter() {}
    
    public void actionPerformed(ActionEvent e) { karel.putBeeper(); }
  }
  
  private class Picker implements ActionListener {
    Picker() {}
    
    public void actionPerformed(ActionEvent e) { karel.pickBeeper(); }
  }
  
  private class Mover implements ActionListener {
    Mover() {}
    
    public void actionPerformed(ActionEvent e) { karel.move(); }
  }
  
  private class LeftTurner implements ActionListener {
    LeftTurner() {}
    
    public void actionPerformed(ActionEvent e) { karel.turnLeft(); }
  }
}
