// This assignment will give you practice with printing text to the console.  You will need to know
// how to use the System.out.print() and println() methods.
package javaconcepts;

public class JavaConceptsCh1_DanielGerblick {
	
	// Print out YOUR answers to the following review exercises on page 28 of the
	// Java Concepts book:
	// R1.2, R1.4, R1.7, R1.12
	public static void answersToReviewExercises() {
		System.out.println("R1.2: What distinquishes a computer from a typical household appliance?");
		System.out.println("Answer: Most household appliance can't run general-purpose programs");
		System.out.println();
		System.out.println("R1.4: What is a Java Virtual Machine?");
		System.out.println("Answer: type your answer here.");
		System.out.println();
		System.out.println("R1.7: What is a console window?");
		System.out.println("Answer: type your answer here.");
		System.out.println();
		System.out.println("R1.12: What do the following programs print?  Don't guess.  Write a program to find out.");
		System.out.println("a) 3 + 4");
		System.out.println("b) 7");
		System.out.println("c) 34");
		System.out.println();
	}

	// Exercise P1.2 facePainter method (page 29 in Java Concepts book)
	// Write a method that prints a face using text characters, hopefully looking
	// better than the one in the textbook
	public static void facePainter() {
		System.out.println(
				"                                      `....```````````````````````````````````````````````````````````````.......`                                    \n" + 
				"                                     `yddddhhhhhhhhhhhhhhhhyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyhhhhhhhhhhhhdddh:                                    \n" + 
				"                                     `dmmmmmdddddddddmddmddddddddhhhhhhhhhhhhhhhhhhhhhhhhhhhdddddddmddddddddmmmmm:                                    \n" + 
				"                           -::::-----:dmmmmdddddddhhhhhhhhhhyyyyysssyyyyyyyyyyyysyyyyyyyyyyyhhhhhhddddddmdddmmmmm+-----::::-`                         \n" + 
				"                           dNNNNmdddddmmmmmhhhhhyyyyyssooooo+++++///////////////++++++ooooossssssyhhhhddmmddmmmmmmddddmNNNNm:                         \n" + 
				"                           dNNNNmmdddmmmmmmhhhhhyyyyyysooooo++++++/+///++//+++++++++++ooooossysssyhhhhdddmddmmmmmmddddmNNNNm:                         \n" + 
				"                     .+oooodmmmmddddddhhhhhysssssoooooo++++++++++++++++++++ooooooooooosssssssyysyyhyyyhdddddddddddmmmmmmNNNmyoooo:                    \n" + 
				"                     :mNNNNddddddhhhhhyyyyysooooo++++++++++++///++++++ooooossyssssssssssssssyyyyyyyyyyyhhhhhhhhhhdmmmmmmmmmmNNNNNo                    \n" + 
				"                     :mNNNNmdddddhhhhhyyyyysooooo+++++++++++++++++++++oooooosysssssssssssssyyyyyyyyyyyhhhhhhhhhhhdmmmmmmmmmNNNNNNo                    \n" + 
				"                     :mNNNNdhhhhhyhhyyhhhhhyysyysssssso+ooosyyyyyooooosyssyssssssyhhhhhhhhhhhhhhhhhhhhhddddddmmdmmNNNNNNNNNNNNNNMs                    \n" + 
				"                     :mNNNNdhhhhhyyyyhhhhhhyyyyyysssssooooosyyyyyooooosyyyysosssshhhhhhhhhhhhhhhhhhhhhddddddmmmmmNNNNNNNNNNNNNNNMs                    \n" + 
				"                `````/mNNNNdhhhhhyhhyhhhhdhyyyyyysssssooooosyyyyysssossyyyyyssssshhhhhhhhhhhhhdddhhhhhdmddmmmmmmmNNNNNNNNNNNNNNNMs```.`               \n" + 
				"                smmmmmNNNNNmdddddhhhhddddddhyyyyysssssyyyyyyyyyyyyyyyyyyyyyhmmmmdhyyyyhddddmmmmmmmddddmmmmmmNNNNNNNNNNNNNNNNNNNNNNmmmmh.              \n" + 
				"                yMNNNNNNNNmmdddddhhhhddddddyyyyyssssssyyyyyyyyyyyyyyyyyyyyshmmmmmhyyyyhddddmmmmmmdddddmmmmmmNNNNNNNNNNNNNNNNNNNNNNNNNMd-              \n" + 
				"                yMNNNNNNNNmmmmmmdddddddddddyyyyyyhhyyyyyyyyyhhhhhhhhyyyyyyshmmmmdhhhhhddddddmmmmmmmmmmmmmmmmNNNNNNNNNNNNNNNNNNNNNNNNNNd.              \n" + 
				"                yMNNNNNNNNNmmmmmmmmmdddddddhyyyshmmmmdysssyhddddddddddhyyyyhdddddmmmmmmdddddmmmmmNNNNNmddddmNNNNNNNNNNNNNNNNNNNNNNNNNNd.              \n" + 
				"                yMNNNNNNNNNmmmmmmmmmmmdddddhyyyshmmmmdysssshddddddddddhyyysydddddmmmmmddddddmmmmmNNNNNmddddmNNNNNNNNNNNNNNNNNNNNNNNNNNd-              \n" + 
				"                yMNNNNNmmmmmmmmmmddddddddddhhdddhhhhhhddhhhdddddddhhhhhhhhhhdddddhhhhhhhhhhhhhhhhdddddhhhhhhmmmmmmNNNNNNNNNNNmmmmNNNNNd-              \n" + 
				"                yMNNNNmmmmmmmmmmdhhhhhhhhhhdmmmmdyyysydmmmmddddddhhhhhddddddddddhysyyyyyyyyyyyyyyyyyyyyyyyyydddddmmmmmNNNNNNmmmmmNNNNNd.              \n" + 
				"                yMNNNNmmmmmmmmmmdhhhhhhhhhhdmmmmdyyyyydmmmmddddddhhhhhddddddddddhyyyyyyyyyyyyyyyyyyyyyyyyhyhhddddmmmmmmNNNNNmmmmmNNNNNd.              \n" + 
				"                yMNNNNmmmmmmmmmmdhhhhhyyyyyhdhhdddddddysyyyhdmmmdhhhhhhyyyyyyssysooooosssssssssssyyyyyyyyyyyyyyyyhdddddmmmmmmmmmmNNNNNd.              \n" + 
				"                yMNNNNmmmmmmmmmmdhhhhhyyyyyhhhhhddmmmdyossshmmmmddhhhhyyyyyssosssooooossssssssssssyyyyyyyyyyyyyyyhdddddmmmmmmmmmmNNNNNd.              \n" + 
				"                yMNNNNmmmmmmmmmmdhhhhhyyyssyhhhhhdddmdyossshdddddhyhhyyssyyssossooooooossssssssossyyyyyssssysyssyhhddddmmmmmmmmmmNNNNNd.              \n" + 
				"                yMNNNNmmmmmmddddhyyyyssosooooooosssssssossssooooooooooooooooooooooooooooooooosssssssssooooooooooosyyyyhdmmmmmmmmmNNNNNd.              \n" + 
				"                yMNNNNmmmmmmddddhyyyysoooooooooooosssssssssoooooooooooooooooooooooooooooooooosssssssssooooooooooosyyyyhdmmmmmmmmmNNNNNd.              \n" + 
				"                yMNNNNmmmmmdddddhyyyysooooo+++oooooooooooooooooooooooooooooooooooooooooooooosooooooooooo+ooooooo+syyyyydmmmmmmmmmNNNNNd.              \n" + 
				"                yMNNNNmmmmmdhhhhysssso+//++++++++++++++ooooosssssoooooooooooooooooooooossssoooooo++++++++++++++//ossssydddddmmmmmNNNNNd.              \n" + 
				"                yMNNNNmmmmmdhhhhysssyo+//+++++++++++++oooooossssooooooooooooooooooooooossssoooooo++++++++++++////ossssydddddmmmmmNNNNNd.              \n" + 
				"                yMNNNNmmmmmdhhhhysssyo++++//:::://////++++++ooooosssoossssssssssssossooooooo+++++////////:/:///+/osssyyhddddmmmmmNNNNNd.              \n" + 
				"                yMNNNNmmmmmdhhhhysssyo++++/:----:::::://///++++++osssssyyyyyyyyysssssoo+++++////+/:::::-----/++//ossssyhhhhhmmmmmNNNNNd.              \n" + 
				"                yMNNNNmmmmmdhhhhysssso+//+/:----::::::/+///++++++osoossyyyyyyyyysssssoo+++++/++///::::::::::/++++ossyyyhhhhhmmmmmNNNNNd.              \n" + 
				"                yNNNNNmmmmmdhhhhyoooo+:::::-.....-...-::::::::::/+++++++++++++++++++++/:///::::::+oooosyhhhhmmmmmmmmmmddhhhhmmmmmNNNNNd.              \n" + 
				"                yNNNNNmmmmmdhhhhyoooo+:::::...........:::::::::::/+++/////////////+/+//::::::::::ossssymmmmmMMMMMMNNNNmdhhhhmmmmmNNNNNd.              \n" + 
				"            `` `yNNNNNmmmmmdhhhhysssso/////:----------:::::::::-:/+++/////////////+++//:-:::::::/oyyyyhmmmmmNMMMMNmmmmmdhhhhmmmmmNNNNNd- ```          \n" + 
				"          `:++++yddddmmmmmmdhhhhdmmmmmmmmmmdddddhsoooo/::::-....-+++++:----------/++++:....-ossssmMMMMNNNNNmdddddhyyhyhhhhhhmmmmmmddddho/+++`         \n" + 
				"          `/oooohddddmmmmmmdhhhhmNNNNNNNNNNmmmmmhsosso/:://-.....+++++:----------/++o+:....-osssyNMMMMMNNNNmdhhhhhyhhyyhdhhhmmmmmmddddhsoooo`         \n" + 
				"          `/oooohddddmmmmmmhyyyydmmmmmmmmmmmmmmmdyyyyyooooo++++/+ossss/:::::::::/ossoso+++++hhhhdNMMMMMNNNNmdhdddyyyyyyhhhhhmmmmmmddddhsoooo`         \n" + 
				"          `/oooohddddmmNmNmsooooshyyyhdddddmNNNNNNNNNNNNNNNMMMMMMmddddyosooooooosdmdddNMMMMMMMMMMMMMMMMNNNNNmddddyooooosyyyydmmmmmddddhsoooo`         \n" + 
				"          `/ooooyddddmmmmNmsoooosyyyyhddddmmNNNNNNNNNNNNNNNMMMMMMmddddyoooooooooshddddNMMMMMMMMMMMMMMMMNNNNNdddddyoooooyyyyydmmmmmddddds+ooo`         \n" + 
				"          `+sssshddddddddmdsooooshhhhhyyyyyysyyshdddddNMMMMMMMMMMmmmmmhyyhhhhhhhhdmmmmNMMMMMNmmmmdyyyyhdddhdNNNNNdysyyyssssshdmddddddddyssss.         \n" + 
				"          `oyyyyhdddddddddhsooooyhhhhhooooo/::::+yyyshMMMMMMMMMMMNmmmmmmmmmmmmmmmmmmmmNMMMMMdhhhh+::::+yyyshNMMMMNhhhhhsooooyhddhhdddddyyyyy.         \n" + 
				"          `oyyyyhdddddhhhhhsooooyhhhhyooooo/:/::+yyyshMMMMMMMMMMMNmmmmmmmmmmmmmmmmmNmmNMMMMMdhhhho::::+syyshNMMMMNhhhhysooooydddhddddddyyyyy.         \n" + 
				"          `shhhdddddddhhhhyooooosyyyyyooooo/-:::/ossosdmmmmmmmmmmhyyhysooooooooooyhhyhdmmmmmdddddso+oo+:/::/syyyyyyyyyyoooooyhhhhhdmddddhhhh-         \n" + 
				"          `yddddddddddyyyhyooooosyyyysooooo/----/ooooshddddddddddyyyyyo/+///////+syyyyhddddddddddsooos+----:+oooosyyyyyoooooyyyyyhdddddddddd-         \n" + 
				"          `yddddddddddyyyyyooooossssysooooo/:::-/ooooshdhhddddddhysyyy+/////////+syysyhddddddhddhyosos+:::::+sooosyysysooooosyyyyhddddddhddd-         \n" + 
				"          `shhyhddddddyyyhy+/////////++++++ooooosyyyyyssoooo+++++ososo:---------:+soooo+++++ooosssyyyysooooo++++++//////////shyyyhdddddhhyyh.         \n" + 
				"          `oyyyydmddddyyyhy+//////////+++++ooooossyyyyooooo+//+++ooooo:----------+sooo+/++/+ooooosyyyyssoooo++++++//////////shyyyhdddmdhyhyy.         \n" + 
				"          `oyyyydddddhyyyyy+//////////////++o+ooossssso++++////:/+++++:----------/o++o/::/:/+++++osssso+++o++///////////////syyyyhdddddhyyyy.         \n" + 
				"          `oyyyydddddhyyyys+//+//:///:-----:::/:/+++++:::::-.....:::::-..........:::::-.....:::::/++++/::::::----::::://++//oyyyyhdddddhsyyy.         \n" + 
				"          `oyyyydddddhyyyys+/////:::::-----:/:///+++++:::::-.....:::::-..........:::::-.....:::::/++++//:::::----::::///++//oyyyyhdddddysyyy.         \n" + 
				"           .----smdddhyyyyyoooo+++++++///////////////::---------:::://-.....````.:/::::----------://::////////////++++++oooosyyyyhdddmh:----`         \n" + 
				"                +mddddyyyyysossssooooo+++++/////:--------..:::/:://///-----.`````://///:///:-...--:---://///+++++oooooosssssyyyyyhdddmy.              \n" + 
				"                +dddddyyyhysossssooooo+++++/////::-----.---:/:/://////:----.`````://///:/:/:------:::-://///+o+++oooooosssssyyyyyhdddmy`              \n" + 
				"                +mdmddhhhhhsssssssosso+++++/::::-.....:::::/ooo+oooooo+//////////oooooo++oo+:::::-....-:::::+++++ossssssssssyhhhhhdddmy`              \n" + 
				"                +mddddhhhhhyssssssssso+++++:--::-.....:////+ooooossssso+++++++++ossssssoosoo/////-.....-:--:+++++osssssssssshhhhhddddmy.              \n" + 
				"                /yyyyhhhhhhyssssssssso+++++:-----...../////ossssssyyysooooooooooosyyyyssssso/////-.....----:/++++ossssssssssyhhhhhyyyho`              \n" + 
				"                 `` `-yhhhhsoooossssso/////-....-:::-:+soooydddddhhhhhyyyyyyyyyyyhhhhhddddddsooso/::::-....-:////osssssoooooyhhhh/````                \n" + 
				"                     .yhhhhooooossssss/////-....-:::::osssshmmmmmdhhhhhhyyyyyyyyyhhhhhdmmmmdsssso/::::-.....:////+sssssoooooyhhhd:                    \n" + 
				"                     .ydddho++o+osssyo/////-....-::/:/oooooshhhhhyyyyyyyyyyyyyyyyyyyyyyhhhhysoooo/:/:/:....-:///:+ssssso+++oydddd/                    \n" + 
				"                     -hddmdo++++osssyo:::::-----:++++++/++//::::/+++++osssssssssso++++//::::/+//++++++/-----:::::+sysss+++++ydddm/                    \n" + 
				"                     -hddmdo/+++osssyo:::::-----:++++++//++/:/::/+o+++osssssssssso++++//:::/++//++++++/------::::+sysss+++++ydddm/                    \n" + 
				"                     -hmmmdo+oooosssso///:::--:-:+++++/////:::::::::::/++++++++++/:::::::::://///+++++/----::///:+ssssso+o+ohmmmm/                    \n" + 
				"                     -dmmmdsoooossssso/////::::::/++///////:-----.....://///////:......-----//////++++/::::://///+sssssooooohmmmm+                    \n" + 
				"                     -hmmmdsosoossssso/////:::::/++////////:-----.....://///////:......-----///////////::::://///+sssssooooohmddm+                    \n" + 
				"                     `.....oyyyysooooo+/+++////:+sssssyyyyysooooooooooossssssssssoooooooooooyyyyyssssso/:////++/+ooooossyyys-....`                    \n" + 
				"                           ohyyysooooo+/+++/////+yyyyyhhhhhhyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyhhhhhyyyyyo/////+++++ooooosyyyys.                         \n" + 
				"                           ohhyhsooooo+++++/////oyyyyyyhhhhyyysyyssyyyysssssssssssyyyyyyyyyyhhhhhyysyyo/////+++++ooooosyhhhs.                         \n" + 
				"                           smmmmhyyyysooooo+++++++++++++++++//////:::::::::::::::::::://////+++++++++++++++++oooosyyyyydmmmh.                         \n" + 
				"                           ymmmmhsyyysooooo++++++++++++++++//////:::::::::::::::::::::://///++++++++++++++++ooooosyyyyydmmmh-                         \n" + 
				"                           +sssssyyyyyooooo+/++++//++/++++++/+//////////////////////////////+++++//////+++++ooooosyyyyysssso.                         \n" + 
				"                                :hdddhyssss+/+++/::////+++++oooosyyyyyyhhhhhhhhhyyyyyysooooo++++//::/://++++osssshddddo`                              \n" + 
				"                                :hdddhsssss+/////:/:://+++++osoosyyyyyhhhhhhhhhhhyyyyysoooso+/+++/::/:///+//oyssshddddo                               \n" + 
				"                                .::::/yhhyyooooo+//////////+o++oooooooooooooooooooooooooooo+///////////++o+oyhyyy+::::-                               \n" + 
				"                                     `ydddhysssso//++//::::/+++++++++/:::::::::::/+++++++++///:::/+///+sssoshdddh-                                    \n" + 
				"                                     `yhhdhssssso+/++///:://+++++++++/:::::::::::/+++++++++//::://++//+sssoshdhhh-                                    \n" + 
				"                                      .....ohhhhhssssso+++++//////::/:-.........-:///://////+++++ossssyhdhdy-....`                                    \n" + 
				"                                           ommmmdyyhhyooooo+/////:::::...........-::::://///oooooyhyhyhmmmmy.                                         \n" + 
				"                                           +hhhhyssssoooooo+/////:::::-..........::::://////oooooossssyhhhhs`                                         \n" + 
				"                                           ```````````odhhhysssssooooooooooooooooooooosssssyhdhhy-``````````                                          \n" + 
				"                                                      sddddhyyyyyssssssssssssssssssssssyyyyyhdddy.                                                    \n" + 
				"                                                      /oooo+////////////////////////////++/+oooo+`");
	}

	// Exercise P1.3 ticTacToeBoard method (page 29 in Java Concepts book)
	// Write a method that prints the tic-tac-toe board. Use looping to get full
	// credit.
	public static void ticTacToeBoard() {
		for (int i = 0; i < 2; i++) {
			printBorder();
			printRow();
		}
		printBorder();
		printRow();
		printBorder();
	}
	
	private static void printBorder() {
		for (int j = 0; j < 3; j++)
			System.out.print("+---");
		System.out.print("+\n");
	}
	
	private static void printRow() {
		for (int j = 0; j < 3; j++)
			System.out.print("|   ");
		System.out.print("|\n");
	}

}
