
// This client program should print out the following to the console:
// your answers to the assigned review exercies, print a face, and print a tic tac toe board. 
package javaconcepts;

public class JavaConceptsCh1Tester_DanielGerblick 
{

	public static void main(String[] args) 
	{
		// Static methods are methods that are not sent to an object.
		// Since the following methods are static methods, we have to prefix them with the name
		// of the class they are in (instead of an object that has been instantiated
		JavaConceptsCh1_DanielGerblick.answersToReviewExercises();
		System.out.println();
		JavaConceptsCh1_DanielGerblick.facePainter();
		System.out.println();
		JavaConceptsCh1_DanielGerblick.ticTacToeBoard();
	}
}