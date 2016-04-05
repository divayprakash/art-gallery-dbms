package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

/**
 * Main class
 * @author Anant Mittal, Divay Prakash
 */
public class Main extends Application {
	private Stage primaryStage;
	private AnchorPane pane;
	@Override
	/**
	 * @param primaryStage stage on which all content is displayed
	 * @throws exception
	 */
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			this.primaryStage.setTitle("Art Gallery DBMS");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./FrontEnd.fxml"));
			pane = (AnchorPane)loader.load();
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setWidth(600);
			primaryStage.setHeight(600);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Main method, start of execution
	 * @param args Command-line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
