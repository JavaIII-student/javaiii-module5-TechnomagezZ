package module5;

public class Movies {
	private int movieID;
	private int rating;
	private String movieName;
	private String description;

	public Movies() {
	}
	
	public Movies(int movieId, String movieName, int rating, String description) {
		setMovieId(movieId);
		setMovieName(movieName);
		setRating(rating);
		setDescription(description);
	}
	
	public void setMovieId(int movieId) {this.movieID = movieId;}
	
	public int getMovieID() {return movieID;}
	
	public void setMovieName(String movieName) {this.movieName = movieName;}
	
	public String getMovieName() {return movieName;}
	
	public void setRating(int rating) {this.rating = rating;}
	
	public int getRating() {return rating;}
	
	public void setDescription(String description) {this.description = description;}
	
	public String getDescription() {return description;}
	
	@Override
	public String toString() {
		return getMovieID() + ", " + getMovieName();
	}

}
