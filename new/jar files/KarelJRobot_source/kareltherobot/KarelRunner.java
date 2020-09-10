package kareltherobot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;


public class KarelRunner
{
  public KarelRunner() {}
  
  public static void main(String[] args)
  {
    String className = null;
    String worldOption = "";
    int i;
    if (args.length < 1)
    {
      className = getName();

    }
    else
    {
      className = args[0];
      

      for (i = 1; i < args.length; i++) {
        if (((args[i].charAt(0) == '/') || (args[i].charAt(0) == '-')) && (args[i].length() > 1)) {
          switch (Character.toUpperCase(args[i].charAt(1))) {
          case 'B':  worldOption = "b"; break;
          
          case 'W': 
            if (args.length > i + 1) {
              filename = args[(i + 1)];
              i++;
            }
            else
            {
              System.out.println("No world filename");
              System.exit(1);
            }
            break;
          default: 
            System.out.println("Invalid option " + args[i] + ": Only BW allowed\n");
            System.exit(1);
          }
        }
      }
    }
    try {
      if (worldOption.equalsIgnoreCase("b"))
      {
        i = new WorldBuilder(true);
      }
      Class robotClass = Class.forName(className);
      RobotTask robotInstance = (RobotTask)robotClass.newInstance();
      String answer = prompt();
      while (ok(answer)) {
        if (clearing) { World.reset();
        }
        if ((filename != null) && (filename != "")) {
          World.readWorld(filename);
        }
        World.setVisible(true);
        robotInstance.task();
        
        answer = prompt();
      }
      System.exit(0);
    }
    catch (ClassCastException classcast) {
      System.out.println("Your class does not implement RobotTester.");
      System.exit(3);
    }
    catch (ClassNotFoundException noClass) {
      System.out.println("No such class.");
      System.exit(2);
    }
    catch (IllegalAccessException illegalClass) {
      System.out.println("Can't access that class.");
      System.exit(4);
    }
    catch (InstantiationException illegalClass) {
      System.out.println("Can't instantiate that class.");
      System.exit(5);
    }
    catch (Throwable other) {
      System.out.println("Unexplained Error.");
      System.exit(6);
    }
  }
  
  private static String getName() {
    String result = null;
    System.out.print("Name of class to test: ");
    try {
      result = in.readLine();
    }
    catch (IOException e) {
      System.out.println("No such class");
      System.exit(1);
    }
    return result;
  }
  
  private static String getWorldBuilderOption() {
    String result = "x";
    System.out.print("Would you like the world builder? Y/n: ");
    try {
      result = in.readLine();
      if (ok(result)) {
        return "b";
      }
    } catch (IOException e) {
      System.out.println("Error");
      System.exit(1);
    }
    return result;
  }
  
  private static boolean ok(String answer) {
    if ((answer.length() > 0) && ((answer.charAt(0) == 'n') || (answer.charAt(0) == 'N'))) {
      return false;
    }
    clearing = (answer.length() > 0) && ((answer.charAt(0) == 'c') || (answer.charAt(0) == 'C'));
    boolean worldfile = (answer.length() > 0) && ((answer.charAt(0) == 'w') || (answer.charAt(0) == 'W'));
    if (worldfile)
    {
      filename = answer.substring(1).trim();
    }
    return true;
  }
  
  private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
  private static boolean clearing = true;
  private static String filename = "";
  
  private static String prompt()
  {
    System.out.print("Run robot task? Y/n/c/w ");
    String result = "no";
    try {
      result = in.readLine();
      if (result == null) {
        break label33;
      }
    }
    catch (Exception localException) {}finally
    {
      label33:
      return result.trim();
    }
  }
}
