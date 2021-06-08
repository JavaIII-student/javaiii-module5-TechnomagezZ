package module5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class MovieQueries {
	private static final String URL = "jdbc:derby:movieDB;create=true";

	private Connection connection;
	private PreparedStatement selectAllMovies;
	private PreparedStatement selectMoviesByName;
	private PreparedStatement insertNewMovie;

	public MovieQueries() {
		try {
			connection = DriverManager.getConnection(URL);

			selectAllMovies = connection.prepareStatement("SELECT * FROM movieDB ORDER by MovieName");
			
			selectMoviesByName = connection.prepareStatement("SELECT * FROM movieDB WHERE MovieName LIKE ? " +
			"ORDER BY movieName");
			
			selectMoviesByRating = connection.prepareStatement("SELECT * FROM movieDB WHERE Rating LIKE ? "  +
			"ORDER BY Rating DSC");
			
			insertNewMovie = connection.prepareStatement("INSERT INTO Movies " +
			"ID, MovieName, Rating, Description) " +
			"VALUES (?, ?, ?, ?)");

		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			System.exit(1);
		}
	}
	
	public List<Movies> getAllMovies(){
		try (ResultSet resultSet = selectAllMovies.executeQuery()) {
			List<Movies> results = new ArrayList<Movies>();
			
			while (resultSet.next()) {
				results.add(new Movies(
					resultSet.getInt("movieID"),
					resultSet.getString("MovieName"),
					resultSet.getInt("Rating"),
					resultSet.getString("Description")));
			}
			
			return results;
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		return null;
	}
	
	public List<Movies> getMoviesByName(String movieName) {
		try {
			selectMoviesByName.setString(1, movieName);
		}
		catch (SQLException sqlException){
			sqlException.printStackTrace();
			return null;
		}
		
		try (ResultSet resultSet = selectMoviesByName.executeQuery()) {
			List<Movies> results = new ArrayList<Movies>();
			
			while (resultSet.next()) {
				results.add(new Movies(
					resultSet.getInt("movieID"),
					resultSet.getString("MovieName"),
					resultSet.getInt("Rating"),
					resultSet.getString("Description")));
			}
			
			return results;
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		return null;
	}
	
	public int addMovies(int movieID, String movieName, int rating, String description) {
		try {
			insertNewMovie.setInt(1, movieID);
			insertNewMovie.setString(2, movieName);
			insertNewMovie.setInt(3, rating);
			insertNewMovie.setString(4, description);
			
			return insertNewMovie.executeUpdate();	
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			return 0;
		}
	}

	public void close() {
		try {
			connection.close();
		} 
		catch (SQLException sqlExeption) {
			sqlExeption.printStackTrace();
		}
	}
}
