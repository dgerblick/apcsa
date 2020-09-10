package movieassignment;

import java.util.ArrayList;
import java.util.List;

public class MovieBox_DanielGerblick {

	private List<Movie_DanielGerblick> movieList = new ArrayList<Movie_DanielGerblick>();
	
	// Create a constructor method that accepts an array list of movies	
	public MovieBox_DanielGerblick(List<Movie_DanielGerblick> movieList) {
		this.movieList = movieList;
	}
	
	// Create a method that accepts a rating and returns all of the movies that have that rating.
	// If the rating parameter is ALL then return all of the movies
	public List<Movie_DanielGerblick> findMoviesByRating(Rating... ratings) {
		List<Movie_DanielGerblick> movies = new ArrayList<Movie_DanielGerblick>();
		for (Movie_DanielGerblick movie : movieList) {
			for (Rating rating : ratings) {
				if (movie.getRating() == rating)
					movies.add(movie);
			}
		}
		return movies;
	}
	
}
