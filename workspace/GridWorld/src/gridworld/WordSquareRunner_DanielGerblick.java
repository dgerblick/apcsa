package gridworld;

public class WordSquareRunner_DanielGerblick {

	public static void main(String[] args) {
		WordSquare_DanielGerblick words = new WordSquare_DanielGerblick("Word square");
		//WordSquare_DanielGerblick words = new WordSquare_DanielGerblick("123456789");
		words.show();

		words = new WordSquare_DanielGerblick("AP Computer Science is fun");
		words.show();
        
		words = new WordSquare_DanielGerblick("Spring Break is here");
		words.show();
        
		words = new WordSquare_DanielGerblick("HELP");
		words.show();
        
		words = new WordSquare_DanielGerblick("10SNE1");
		words.show();
        
		words = new WordSquare_DanielGerblick("1 2 3 4 5 6 7 8 9 10 11");
		words.show();
		
		words = new WordSquare_DanielGerblick("Did you ever hear the tragedy of Darth Plagueis the Wise? I thought not. It's not a story the Jedi would tell you. It's a Sith legend. Darth Plagueis was a Dark Lord of the Sith, so powerful and so wise he could use the Force to influence the midi-chlorians to create life. ... He had such a knowledge of the dark side that he could even keep the ones he cared about from dying. The dark side of the Force is a pathway to many abilities some consider to be unnatural. He became so powerful... the only thing he was afraid of was losing his power, which eventually, of course, he did. Unfortunately, he taught his apprentice everything he knew, then his apprentice killed him in his sleep. (smiles) Plagueis never saw it coming. It's ironic he could save others from death but not himself. ");
		words.show();
		// Add more test cases to test strings of different lengths
	}

}
