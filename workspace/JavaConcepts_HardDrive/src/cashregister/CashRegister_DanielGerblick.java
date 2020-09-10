package cashregister;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.text.*; //Needed to format double numbers to 2 places

// Create a CashRegister class that represents the cash register and its contents.  The CashRegister class 
// will keep track of the different currency and coins in the cash register.  You need a method to 
// tell you what money is in the cash register.
//
public class CashRegister_DanielGerblick {
	// Create instance variables to keep track of the money (each currency/coin) in
	// the cash register,
	// the money (each currency/coin) received,
	// and the purchase amount

	private ArrayList<Money_DanielGerblick> money = new ArrayList<Money_DanielGerblick>(); // This keeps track of all
																							// the money in the register

	private DecimalFormat moneyFormat = new DecimalFormat("$0.00"); // Money format: 2 decimals with $
	private DecimalFormat decFormat = new DecimalFormat("#.##"); // Decimal format: 2 decimals

	public CashRegister_DanielGerblick(ArrayList<Money_DanielGerblick> money) {
		this.money = money; 
	}

	public double getTotalMoneyInRegister() {
		double total = 0;
		for (Money_DanielGerblick moneyObj : money)
			total += moneyObj.getValue();
		return total;
	}

	public void printAmountInRegister() {
		Collections.sort(money);
		double total = money.get(0).getValue();
		int count = 1;
		for (int i = 1; i < money.size(); i++) {
			if (money.get(i).equals(money.get(i - 1))) {
				total += money.get(i).getValue();
				count++;
			} else {
				System.out.printf("%s amount (x%d):\t$%.2f%n", money.get(i - 1).getType(), count, total);
				count = 1;
				total = money.get(i).getValue();
			}
		}
		System.out.printf("%s amount (x%d):\t$%.2f%n", money.get(money.size() - 1).getType(), count, total);

	}
}