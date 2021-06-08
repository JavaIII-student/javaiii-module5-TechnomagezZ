package module5;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ListView;

public class MovieDBController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button displayAllButton;

    @FXML
    private Button addMovieButton;

    @FXML
    private TextField movieIDTextField;

    @FXML
    private TextField movieNameTextField;

    @FXML
    private TextField ratingTextField;

    @FXML
    private TextField descriptionTextField;
    
    @FXML
    private ListView<Movies> movieListView;

    @FXML
    private Label movieIDLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label movieNameLabel;

    @FXML
    private Label ratingLabel;

    @FXML
    private Label titleLabel;
    
    private final MovieQueries movieQueries = new MovieQueries();
    
    private final ObservableList<Movies> movieList = FXCollections.observableArrayList();
    
    public void initialize() {
    	movieListView.setItems(movieList);
    	getAllEntries();
    	
    	movieListView.getSelectionModel().selectedItemProperty().addListener(
    			(observableValue, oldValue, newValue) -> {
    				displayMovie(newValue);
    			}
    	);
	}

	private void getAllEntries() {
		movieList.setAll(movieQueries.getAllMovies());
		selectFirstEntry();
	}
	
	private void selectFirstEntry() {
		movieListView.getSelectionModel().selectFirst();
	}
	
	private void displayMovie(Movies movies) {
		if (movies != null) {
			movieIDTextField.setText(String.valueOf(movies.getMovieID()));
			movieNameTextField.setText(movies.getMovieName());
			ratingTextField.setText(String.valueOf(movies.getRating()));
			descriptionTextField.setText(movies.getDescription());
		}
		else {
			movieIDTextField.clear();
			movieNameTextField.clear();
			ratingTextField.clear();
			descriptionTextField.clear();			
		}
	}
	
	@FXML
	void addMovieButtonPressed(ActionEvent e) {
		String movieIDText = movieIDTextField.getText();
		int movieID = Integer.parseInt(movieIDText);
		String ratingText = ratingTextField.getText();
		int rating = Integer.parseInt(ratingText);
		
		int result = movieQueries.addMovies(
				movieID , movieNameTextField.getText(),
				rating , descriptionTextField.getText());
		
		if (result == 1) {
			displayAlert(AlertType.INFORMATION, "Entry Added",
					"New Entry Successfully added.");
		}
		else {
			displayAlert(AlertType.ERROR, "Entry Not Added",
					"Unable to Add Entry");
		}
		
		getAllEntries();
	}
	
	@FXML
	void displayAllButtonPressed(ActionEvent e) {
		getAllEntries();
	}
	
	private void displayAlert(AlertType type, String title, String message) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setContentText(message);
		alert.showAndWait();
	}
}

