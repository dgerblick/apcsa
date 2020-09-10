package cashregister;

public class Money_DanielGerblick implements Comparable {
	private String type; // type of currency
	private double value; // value of money

	public double getValue() { return value; }
	public String getType() { return type; }
	
	public Money_DanielGerblick(String type, double value) {
		this.type = type;
		this.value = value;
	}

	public int compareTo(Object obj) {
		// Create a compareTo() method to determine how to compare a Money object.
		// Use the value of the money to determine whether it is less than, equal, or
		// greater than the other money

		Money_DanielGerblick other = (Money_DanielGerblick) obj;
		
		if (value > other.value)
			return 1;
		else if (value < other.value)
			return -1;
		else
			return type.compareTo(other.type);
	}

	public String toString() {
		return String.format("%s amount: %f", type, value);
	}

	public boolean equals(Object obj) {
		Money_DanielGerblick other = (Money_DanielGerblick) obj;
		if (value == other.value && type.equals(other.type))
			return true;
		return false;
	}
}