package kareltherobot;

import java.util.Random;



public class Die
{
  private int value;
  private Random r = new Random();
  
  public Die(int faces) {
    value = faces;
  }
  
  public int roll() {
    return Math.abs(r.nextInt()) % value + 1;
  }
  
  public int faces() {
    return value;
  }
}
