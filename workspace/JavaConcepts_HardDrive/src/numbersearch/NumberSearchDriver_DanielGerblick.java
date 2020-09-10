
package numbersearch;

import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import static java.lang.System.*;

public class NumberSearchDriver_DanielGerblick {
	public static void main(String args[]) throws IOException {
		Scanner file = new Scanner(new File("NumberSearchData.txt")); // Put file in the project folder
		int size = file.nextInt(); // Reads the first line for the number of data sets in the file
		file.nextLine(); // Necessary to read the linefeed

		for (int i = 0; i < size; i++) // Loop through each data set in the file
		{
			String line = file.nextLine(); // Reads the line of integer values
			String[] listOfNumbers = line.split(" "); // The split method will create a String array
			NumberSearch_DanielGerblick ns = new NumberSearch_DanielGerblick(listOfNumbers); // Construct a new
																								// NumberSearch object
			int findNum = file.nextInt(); // Read the following line for the number to search for

			System.out.println("Unsorted array: " + ns); // Print unsorted array
			ns.sortNumArray(); // Sort the array
			System.out.println("Sorted array:   " + ns); // Print the sorted array

			int answer = ns.findNumber(findNum, "+");
			System.out.println("Searching for number: " + findNum + " with direction: +, Answer: " + answer);
			answer = ns.findNumber(findNum, "=");
			System.out.println("Searching for number: " + findNum + " with direction: =, Answer: " + answer);
			answer = ns.findNumber(findNum, "-");
			System.out.println("Searching for number: " + findNum + " with direction: -, Answer: " + answer);
			System.out.println();

			file.nextLine(); // Necessary to read the linefeed
		}
	}
}
