package movieassignment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JRadioButtonMenuItem;

public class MovieTester_DanielGerblick extends JFrame implements ActionListener {

	List<JCheckBoxMenuItem> buttons;
	MovieBox_DanielGerblick box;
	List<Movie_DanielGerblick> moviesByRating;
	
	public MovieTester_DanielGerblick() {
		List<Movie_DanielGerblick> movies = new ArrayList<Movie_DanielGerblick>();
		
		// Add at least 5 movies to the array list
		//movies.add(new Movie_DanielGerblick("title", Rating.PG, year, total, out));
		movies.add(new Movie_DanielGerblick("Iron Man", Rating.PG_13, 2008, 10, 5));		
		movies.add(new Movie_DanielGerblick("The Incredible Hulk", Rating.PG_13, 2008, 4, 0));
		movies.add(new Movie_DanielGerblick("Iron Man 2", Rating.PG_13, 2010, 8, 3));
		movies.add(new Movie_DanielGerblick("Thor", Rating.PG_13, 2011, 5, 1));
		movies.add(new Movie_DanielGerblick("Captain America: The First Avenger", Rating.PG_13, 2011, 7, 6));
		movies.add(new Movie_DanielGerblick("The Avengers", Rating.PG_13, 2012, 12, 7));
		movies.add(new Movie_DanielGerblick("Iron Man 3", Rating.PG_13, 2013, 9, 4));
		movies.add(new Movie_DanielGerblick("Thor: The Dark World", Rating.PG_13, 2013, 7, 1));
		movies.add(new Movie_DanielGerblick("Captain America: The Winter Soldier", Rating.PG_13, 2014, 9, 9));
		movies.add(new Movie_DanielGerblick("Guardians of the Galaxy", Rating.PG_13, 2014, 10, 5));
		movies.add(new Movie_DanielGerblick("Avengers: Age of Ultron", Rating.PG_13, 2015, 7, 2));
		movies.add(new Movie_DanielGerblick("Ant-Man", Rating.PG_13, 2015, 8, 4));
		movies.add(new Movie_DanielGerblick("Captain America: Civil War", Rating.PG_13, 2016, 8, 7));
		movies.add(new Movie_DanielGerblick("Doctor Strange", Rating.PG_13, 2016, 4, 1));
		movies.add(new Movie_DanielGerblick("Guardians of the Galaxy Vol. 2", Rating.PG_13, 2017, 9, 2));
		movies.add(new Movie_DanielGerblick("Spider-Man: Homecoming", Rating.PG_13, 2017, 8, 2));
		movies.add(new Movie_DanielGerblick("Thor: Rangnarok", Rating.PG_13, 2017, 10, 9));
		movies.add(new Movie_DanielGerblick("Black Panther", Rating.PG_13, 2018, 15, 8));
		movies.add(new Movie_DanielGerblick("Avengers: Infinity War", Rating.PG_13, 2018, 20, 10));
		movies.add(new Movie_DanielGerblick("Ant-Man and the Wasp", Rating.PG_13, 2018, 3, 1));
		
		movies.add(new Movie_DanielGerblick("X-Men", Rating.PG_13, 2000, 4, 3));
		movies.add(new Movie_DanielGerblick("X-2: X-Men United", Rating.PG_13, 2003, 2, 2));
		movies.add(new Movie_DanielGerblick("X-Men: The Last Stand", Rating.PG_13, 2006, 1, 0));
		movies.add(new Movie_DanielGerblick("X-Men Origins: Wolverine", Rating.PG_13, 2009, 3, 0));
		movies.add(new Movie_DanielGerblick("X-Men: First Class", Rating.PG_13, 2011, 5, 4));
		movies.add(new Movie_DanielGerblick("The Wolverine", Rating.PG_13, 2013, 1, 1));
		movies.add(new Movie_DanielGerblick("X-Men: Days of Future Past", Rating.PG_13, 2014, 5, 4));
		movies.add(new Movie_DanielGerblick("Deadpool", Rating.R, 2016, 5, 4));
		movies.add(new Movie_DanielGerblick("X-Men: Apocalypse", Rating.PG_13, 2016, 5, 1));
		movies.add(new Movie_DanielGerblick("Logan", Rating.R, 2017, 8, 8));
		movies.add(new Movie_DanielGerblick("Deadpool 2", Rating.R, 2018, 4, 3));
		
		movies.add(new Movie_DanielGerblick("Shrek", Rating.PG, 2001, 5, 1));
		movies.add(new Movie_DanielGerblick("Shrek 2", Rating.PG, 2004, 2, 0));
		movies.add(new Movie_DanielGerblick("Shrek the Third", Rating.PG, 2007, 4, 4));
		movies.add(new Movie_DanielGerblick("Shrek Forever After", Rating.PG, 2010, 2, 0));
		movies.add(new Movie_DanielGerblick("Puss in Boots", Rating.PG, 2011, 1, 1));
		
		movies.add(new Movie_DanielGerblick("The Good Dinosaur", Rating.PG, 2015, 1, 0));
		movies.add(new Movie_DanielGerblick("Brave", Rating.PG, 2002, 5, 2));
		movies.add(new Movie_DanielGerblick("The Incredibles", Rating.PG, 2004, 7, 6));
		movies.add(new Movie_DanielGerblick("The Incredibles 2", Rating.PG, 2018, 6, 1));
		movies.add(new Movie_DanielGerblick("Toy Story", Rating.G, 1995, 3, 1));
		movies.add(new Movie_DanielGerblick("Toy Story 2", Rating.G, 1999, 5, 6));
		movies.add(new Movie_DanielGerblick("Finding Nemo", Rating.G, 2003, 5, 2));
		movies.add(new Movie_DanielGerblick("Finding Dory", Rating.PG, 2016, 1, 1));
		movies.add(new Movie_DanielGerblick("Ratatouille", Rating.G, 2007, 10, 8));
		movies.add(new Movie_DanielGerblick("Toy Story 3", Rating.G, 2010, 5, 3));
		movies.add(new Movie_DanielGerblick("Up", Rating.PG, 2009, 4, 4));
		movies.add(new Movie_DanielGerblick("Inside Out", Rating.PG, 2015, 2, 2));
		movies.add(new Movie_DanielGerblick("Coco", Rating.PG, 2017, 8, 8));
		movies.add(new Movie_DanielGerblick("WALL-E", Rating.G, 2008, 10, 9));
		movies.add(new Movie_DanielGerblick("Cars", Rating.G, 2006, 7, 2));
		movies.add(new Movie_DanielGerblick("Cars 2", Rating.G, 2011, 3, 0));
		movies.add(new Movie_DanielGerblick("Cars 3", Rating.G, 2017, 5, 1));
		movies.add(new Movie_DanielGerblick("A Bug's Life", Rating.G, 1998, 8, 0));
		movies.add(new Movie_DanielGerblick("Monsters University", Rating.G, 2013, 5, 4));
		movies.add(new Movie_DanielGerblick("Monsters, Inc.", Rating.G, 2001, 5, 2));
		
		// Instantiate a MovieBox object and pass it the list of movies
		box = new MovieBox_DanielGerblick(movies);
		moviesByRating = new ArrayList<Movie_DanielGerblick>();
		buttons = new ArrayList<JCheckBoxMenuItem>();
		
		Rating[] ratings = Rating.values();
		for (Rating rating : ratings) {
			JCheckBoxMenuItem button = new JCheckBoxMenuItem(rating.toString());
			buttons.add(button);
			add(button);
		}
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(this);
		add(submit);
		
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.print("\n\n\n\n\n");
		Rating[] rating = Rating.values();
		List<Rating> currentRatings = new ArrayList<Rating>();
		for (int i = 0; i < rating.length; i++) {
			if(buttons.get(i).getState())
				currentRatings.add(rating[i]);
		}
		moviesByRating = box.findMoviesByRating(currentRatings.toArray(new Rating[0]));
		for (Movie_DanielGerblick movie : moviesByRating)
			System.out.println(movie);
	}
	
	
	public static void main(String[] args) {
		MovieTester_DanielGerblick tester = new MovieTester_DanielGerblick();
	}

	
	
}
