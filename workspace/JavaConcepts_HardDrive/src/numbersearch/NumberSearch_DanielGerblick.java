package numbersearch;

import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import static java.lang.System.*;

public class NumberSearch_DanielGerblick {
	private int[] numArray;

	public NumberSearch_DanielGerblick(String[] list) {
		numArray = new int[list.length];
		for (int i = 0; i < numArray.length; i++)
			numArray[i] = Integer.parseInt(list[i]);
	}

	public void sortNumArray() {
		Arrays.sort(numArray);
	}

	/*
	public int findNumber(int searchNum, String direction) {
		sortNumArray();
		
		int returnVal = searchNum;
		
		switch (direction) {
		case "-":
			for (int i = 1; i < numArray.length; i++) {
				if (numArray[i] >= searchNum) {
					System.out.println(i);
					returnVal = numArray[i - 1];
					break;
				}
			}
			break;
			
		case "=":
			int minDistance = Math.abs(numArray[0] - searchNum); 
			int index = 0;
			for (int i = 1; i < numArray.length; i++) {
				int newDistance = Math.abs(numArray[i] - searchNum);
				if (newDistance < minDistance) {
					minDistance = newDistance;
					index = i;
				}
			}
			returnVal = numArray[index];
			break;
			
		case "+":
			for (int i = numArray.length - 2; i >=0; i--) {
				if (numArray[i] < searchNum) {
					returnVal = numArray[i + 1];
					break;
				}
			}
			break;
			
		default:
			System.out.println("findNumber: direction not set to +, -, or =");
			break;
		}
		return returnVal;
	}
	*/
	
	public int findNumber(int searchNum, String direction) {
		sortNumArray();

		
		switch (direction) {
		case "-":
			if (numArray[0] > searchNum)
				return searchNum;
			for (int i = 1; i < numArray.length; i++)
				if (numArray[i] >= searchNum)
					return numArray[i - 1];
			return numArray[numArray.length - 1];
			
		case "=":
			int minDistance = Math.abs(numArray[0] - searchNum); 
			int index = 0;
			for (int i = 1; i < numArray.length; i++) {
				int newDistance = Math.abs(numArray[i] - searchNum);
				if (newDistance < minDistance) {
					minDistance = newDistance;
					index = i;
				}
			}
			return numArray[index];
			
		case "+":
			if (numArray[numArray.length - 1] < searchNum)
				return searchNum;
			for (int i = numArray.length - 1; i >= 0; i--)
				if (numArray[i] <= searchNum)
					return numArray[i + 1];
			return numArray[0];
			
		default:
			System.out.println("findNumber: direction not set to +, -, or =");
			return 0;
		}
	}
	
	public String toString() {
		String output = "";
		for (int num : numArray) {
			output += num + " ";
		}
		return output;
	}
}