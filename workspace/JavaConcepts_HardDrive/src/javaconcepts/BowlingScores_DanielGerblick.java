package javaconcepts;

import java.util.Random;

public class BowlingScores_DanielGerblick {
	
	String[] names;
	int[][] scores;
	
	public BowlingScores_DanielGerblick() {
		names = new String[] { "Rocky", "Striker", "Alley Cats", "Splitster", "Crusher", "Spare Bear" };
		scores = new int[names.length][4];
		Random rand = new Random();
		for (int i = 0; i < scores.length; i++)
			for (int j = 0; j < scores[i].length; j++)
				scores[i][j] = rand.nextInt(200);
	}
	
	public void printScores() {
		int lowest = 999;
		String lowestName = null;
		int highest = 0;
		String highestName = null;
		
		int bigSum = 0;
		int bigCount = 0;
		
		for (int i = 0; i < names.length; i++) {
			String strScores = "";
			int sum = 0;
			for (int j = 0; j < scores[i].length - 1; j++) {
				strScores += scores[i][j] + ", ";
				sum += scores[i][j];
				
				if (scores[i][j] > highest) {
					highest = scores[i][j];
					highestName = names[i];
				} else if (scores[i][j] < lowest) {
					lowest = scores[i][j];
					lowestName = names[i];
				}
				bigSum += scores[i][j];
				bigCount++;
			}
			strScores += "and " + scores[i][scores[i].length - 1];
			sum += scores[i][scores[i].length - 1];
			
			System.out.printf("%s bowled games of %s. %s's average is %.2f%n", names[i], strScores, names[i], (double) sum / scores[i].length);
		}
		
		System.out.printf("%nThe highest score was %d bowled by %s.%n", highest, highestName);
		System.out.printf("The lowest score was %d bowled by %s.%n", lowest, lowestName);
		System.out.printf("The average score was %.2f%n", (double) bigSum / bigCount);
	}
}
