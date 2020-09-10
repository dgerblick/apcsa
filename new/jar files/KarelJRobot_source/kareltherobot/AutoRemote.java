package kareltherobot;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Vector;











public class AutoRemote
  extends Frame
  implements Directions
{
  public AutoRemote()
  {
    this("kareltherobot.UrRobot", 1, 1, North, 0, null);
  }
  
  public AutoRemote(String className)
  {
    this(className, 1, 1, North, 0, null);
  }
  
  public AutoRemote(String className, int street, int avenue, Directions.Direction direction, int beepers)
  {
    this(className, street, avenue, direction, beepers, null);
  }
  

  public AutoRemote(String className, int street, int avenue, Directions.Direction direction, int beepers, Color color)
  {
    super(className + " " + getID() + " control");
    try {
      addWindowListener(new Hider());
      setSize(340, 150);
      setLocation(560 + delta * (id - 1), 100 + delta * (id - 1));
      if (color != null) setBackground(color);
      Class robotClass = Class.forName(className);
      if (!UrRobot.class.isAssignableFrom(robotClass)) {
        add(new Label(className + " is not a robot class."));
        return;
      }
      Constructor ctor = robotClass.getDeclaredConstructor(argTypes);
      Object[] values = 
        { new Integer(street), 
        new Integer(avenue), 
        direction, 
        new Integer(beepers), 
        color };
      
      karel = ((UrRobot)ctor.newInstance(values));
      Method[] allMethods = robotClass.getMethods();
      Vector usefulMethods = new Vector();
      for (int i = 0; i < allMethods.length; i++)
      {
        if (isValid(allMethods[i]))
          usefulMethods.addElement(allMethods[i]);
      }
      int numMethods = usefulMethods.size();
      setSize(340, 30 * numMethods);
      setLayout(new GridLayout(numMethods, 2));
      Enumeration enum = usefulMethods.elements();
      while (enum.hasMoreElements()) {
        Method aMethod = (Method)enum.nextElement();
        Button aButton = new Button(aMethod.getName());
        add(aButton);
        if (isProc(aMethod)) {
          aButton.addActionListener(new ProcListener(aMethod));
          add(new Label("void"));
        }
        else {
          TextField aField = new TextField(20);
          

          aButton.addActionListener(new FuncListener(aMethod, aField));
          add(aField);
        }
      }
      setVisible(true);
    }
    catch (Exception e)
    {
      add(new Label(className + " does not seem to be the name of a robot class." + 
        e.toString()));
      setVisible(true);
    }
  }
  
  private static boolean isValid(Method m)
  {
    Class declaring = m.getDeclaringClass();
    if (declaring.equals(Object.class)) return false;
    if (declaring.equals(Observable.class)) return false;
    if (Modifier.isStatic(m.getModifiers())) return false;
    if (declaring.equals(UrRobot.class)) {
      String name = m.getName();
      
      if ((!name.equals("move")) && (!name.equals("turnLeft")) && 
        (!name.equals("turnOff")) && (!name.equals("putBeeper")) && 
        (!name.equals("pickBeeper")) && (!name.equals("toString")))
        return false;
    }
    if (m.getParameterTypes().length == 0) return true;
    return false;
  }
  
  private static boolean isProc(Method m)
  {
    return m.getReturnType().equals(Void.TYPE);
  }
  

  private static int getID() { return id++; }
  
  private class Hider extends WindowAdapter {
    Hider() {}
    
    public void windowClosing(WindowEvent e) { setVisible(false); }
  }
  

  private static int id = 0;
  private static int delta = 10;
  private UrRobot karel = null;
  
  Class[] argTypes = { Integer.TYPE, Integer.TYPE, Directions.Direction.class, Integer.TYPE, Color.class };
  static Class class$0;
  static Class class$6;
  
  private class ProcListener implements ActionListener {
    public ProcListener(Method method) { this.method = method; }
    
    public void actionPerformed(ActionEvent e)
    {
      try {
        method.invoke(karel, null);
      }
      catch (Exception localException) {}
    }
    



    Method method = null;
  }
  
  private class FuncListener implements ActionListener {
    public FuncListener(Method method, TextField field) {
      this.field = field;
      this.method = method;
    }
    
    public void actionPerformed(ActionEvent e) {
      try {
        Object result = method.invoke(karel, null);
        
        field.setText(result.toString());
      }
      catch (Exception localException) {}
    }
    


    Method method = null;
    TextField field = null;
  }
}
