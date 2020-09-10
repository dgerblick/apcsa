package javaconcepts;

import java.util.Random;
import java.util.Scanner;

public class DiceRoller_DanielGerblick {
	final int NUM_DICE = 5;
	
	int[] dice;
	boolean[] locked;
	Random rand;
	Scanner scan;
	
	public DiceRoller_DanielGerblick() {
		rand = new Random();
		scan = new Scanner(System.in);
	}
	
	public void playGame() {
		boolean stillPlaying = true;
		
		while (stillPlaying) {
			dice = new int[NUM_DICE];
			locked = new boolean[NUM_DICE];
			
			roll();
			lockDice();
			roll();
			lockDice();
			roll();
			
			System.out.print("\nPlay again? (Y/N)");
			stillPlaying = scan.nextLine().toLowerCase().contains("y");
		}
	}
	
	private void roll() {
		System.out.print("Dice Values:");
		for (int i = 0; i < NUM_DICE; i++) {
			if (!locked[i])
				dice[i] = rand.nextInt(6) + 1;
			System.out.print(" " + dice[i]);
		}
	}
	
	private void lockDice() {
		System.out.print("\nWhich dice would you like to lock? Use 0 and 1 to denote unlocked and locked, respectively: ");
		String in = scan.nextLine();
		for (int i = 0; i < locked.length; i++) {
			locked[i] = in.charAt(i) == '1';
		}
	}
}
