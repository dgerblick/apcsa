package javaconcepts;

import javax.swing.JOptionPane;

public class PrintAddress_DanielGerblick {

	public static void main(String[] args) {
		String fname = JOptionPane.showInputDialog("First Name:");
		String lname = JOptionPane.showInputDialog("Last Name:");
		String address = JOptionPane.showInputDialog("Address:");
		String city = JOptionPane.showInputDialog("City:");
		String state = JOptionPane.showInputDialog("State:");
		String zip = JOptionPane.showInputDialog("Zip Code:");

		String line1 = fname + " " + lname;
		String line2 = address;
		String line3 = city + ", " + state + " " + zip;
		
		System.out.println(line1);
		System.out.println(line2);
		System.out.println(line3);

		JOptionPane.showMessageDialog(null, "Thank you, " + line1 + ". Your order of 2,147,483,647 ebola-infected chinchillas has been processed and should arrive within 3-5 business days.\nPlease direct all inquires to unbgbbiivchidctiicbg@aol.com or call us at 1-800-867-5309");
	}
	
}
