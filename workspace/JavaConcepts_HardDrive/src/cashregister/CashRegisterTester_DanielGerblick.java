package cashregister;

import java.util.ArrayList;
import java.util.Random;
import java.text.*;   //Needed to format double numbers to 2 places

// This driver program should create a new Money object for each currency or coin
// in a cash register (from a penny to a twenty dollar bill).
// It should then initialize the cash register with some money and print out the 
// contents of the cash register.
//
public class CashRegisterTester_DanielGerblick
{
	public static void main( String[] args )
	{
		DecimalFormat decFormat = new DecimalFormat("$0.00");

		// Set up all possible types of money from a penny to a twenty dollar bill - here are a few examples:
		Money_DanielGerblick twentyDollars = new Money_DanielGerblick("20 dollar bill",20.00);
		Money_DanielGerblick tenDollars = new Money_DanielGerblick("10 dollar bill",10.00);
		//.......

		Money_DanielGerblick nickel = new Money_DanielGerblick("nickel",.05);
		Money_DanielGerblick penny = new Money_DanielGerblick("penny",.01);

		// Initialize the cash register by sending it an array list of money (currency/coins)
		// You can set up a loop and populate the array list that way (possibly 10 of each currency or coin)

		ArrayList<Money_DanielGerblick>  cash = new ArrayList<Money_DanielGerblick>();  //This is the array list of money that needs to be populated
		Random rand = new Random();
		for (int i = 0; i < rand.nextInt(100); i++)
			cash.add(new Money_DanielGerblick("$1 Coin", 1));
		for (int i = 0; i < rand.nextInt(100); i++)
			cash.add(new Money_DanielGerblick("$1 Bill", 1));
		for (int i = 0; i < rand.nextInt(100); i++)
			cash.add(new Money_DanielGerblick("$5 Bill", 5));
		for (int i = 0; i < rand.nextInt(100); i++)
			cash.add(new Money_DanielGerblick("$10 Bill", 10));
		for (int i = 0; i < rand.nextInt(100); i++)
			cash.add(new Money_DanielGerblick("$20 Bill", 20));
		for (int i = 0; i < rand.nextInt(100); i++)
			cash.add(new Money_DanielGerblick("$50 Bill", 50));
		for (int i = 0; i < rand.nextInt(100); i++)
			cash.add(new Money_DanielGerblick("$100 Bill", 100));
		for (int i = 0; i < rand.nextInt(100); i++)
			cash.add(new Money_DanielGerblick("Penny", 0.01));
		for (int i = 0; i < rand.nextInt(100); i++)
			cash.add(new Money_DanielGerblick("Nickel", 0.05));
		for (int i = 0; i < rand.nextInt(100); i++)
			cash.add(new Money_DanielGerblick("Dime", 0.10));
		for (int i = 0; i < rand.nextInt(100); i++)
			cash.add(new Money_DanielGerblick("Quarter", 0.25));
		for (int i = 0; i < rand.nextInt(100); i++)
			cash.add(new Money_DanielGerblick("50¢ Coin", 0.50));	

		//System.out.println(cash);

		CashRegister_DanielGerblick register = new CashRegister_DanielGerblick(cash);  //Instantiates the cash register with money.

		// Show what is in cash register
		System.out.printf("%nThe cash register contains $%.2f :%n", register.getTotalMoneyInRegister());
		register.printAmountInRegister();	//Shows the amount of each currency
	}
}