package kareltherobot;

public class MyRobot extends UrRobot {

	public MyRobot(int street, int avenue, Direction direction, int beepers) {
		super(street, avenue, direction, beepers);
	}

	public void turnRight() {
		for (int i = 0; i < 3; i++)
			turnLeft();
	}

	public void turnAround() {
		turnLeft();
		turnLeft();
	}

	public void moveX(int distance) {
		for (int i = 0; i < distance; i++)
			move();
	}

	public void climbStair(int stairNumber, int stairWidth, int stairHeight) {
		for (int i = 0; i < stairNumber; i++) {
			moveX(stairHeight);
			if (stairWidth > 0) {
				turnRight();
				moveX(stairWidth);
				turnLeft();
			} else {
				turnLeft();
				moveX(-stairWidth);
				turnRight();
			}
		}
	}

	public void climbStair(int stairNumber) {
		climbStair(stairNumber, 1, 1);
	}

	public static void main(String[] args) {
		World.setVisible(true);
		World.showSpeedControl(true);

		MyRobot r = new MyRobot(1, 10, North, 0);
		r.climbStair(3, -3, 2);
	}
}