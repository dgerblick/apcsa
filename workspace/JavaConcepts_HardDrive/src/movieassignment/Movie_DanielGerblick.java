package movieassignment;

public class Movie_DanielGerblick {
	
	// Create private instance variables to store the information for a Movie
	private String name;
	private Rating rating;
	private int year;
	private int copiesTotal;
	private int copiesOut;
	
	
	// Create a constructor with paramters: movie name, rating, year, total quantity, and checkout quantity
	public Movie_DanielGerblick(String name, Rating rating, int year, int copiesTotal, int copiesOut) {
		this.name = name;
		this.rating = rating;
		this.year = year;
		this.copiesTotal = copiesTotal;
		this.copiesOut = copiesOut;
		
	}

	// Setters
	public void setName(String name) { this.name = name; }
	public void setRating(Rating rating) { this.rating = rating; }
	public void setYear(int year) { this.year = year; }
	public void setTotalCopies(int copiesTotal) { this.copiesTotal = copiesTotal; }
	public void setCopiesCheckedOut(int copiesOut) { this.copiesOut = copiesOut; }
	
	// Getters
	public String getName() { return name; }
	public Rating getRating() { return rating; }
	public int getYear() { return year; }
	public int getTotalCopies() { return copiesTotal; }
	public int getCopiesCheckedOut() { return copiesOut; }
	public int getTotalAvailable() { return copiesTotal - copiesOut; }

	// Create a toString() method to return the string version of the movie.
	public String toString() {
		return String.format("\"%s\" was released in %d with a rating of %s. There are %d/%d available.", name, year, rating, getTotalAvailable(), copiesTotal);
	}

	public static void main(String[] args) {
		Movie_DanielGerblick movie = new Movie_DanielGerblick("Shrek", Rating.G, 2001, 10, 6);
		System.out.println(movie);
	}
	
}

enum Rating {
	G,
	PG,
	PG_13,
	R,
	NR,
	UR,
	THIS_FILM_IS_NOT_YET_RATED
}