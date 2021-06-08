package module5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MovieDB extends Application{
	public void start(Stage primaryStage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("MovieDB.fxml"));
		primaryStage.setTitle("Movie Database");
		primaryStage.setScene(new Scene(root, 407, 600));
		primaryStage.show();
	}
	
	public static void main(String Args[]) {
		launch(Args);
	}
}
