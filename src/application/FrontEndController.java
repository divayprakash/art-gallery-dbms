package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FrontEndController implements Initializable {
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//initialize function
	}
	//boolean variable to enable switching tabs
	private boolean tabSelection = false;
	/*
	 * Function to check tab and set bool variable
	 */
	private boolean checkTab() {
	    tabSelection = !tabSelection;
	    return tabSelection;
	}
	/*
	 * Artist Tab
	 */
	@FXML private TextField artistFirstName;
	@FXML private TextField artistLastName;
	@FXML private TextField artistCity;
	@FXML private TextField artistCountry;
	@FXML private DatePicker artistDOB;
	@FXML private TextArea artistStyles;
	@FXML private Button artistData;
	@FXML void handleArtistTab(Event event) {
		if (checkTab()) {
			//handle initialization for artist tab
		}
	}
	@FXML void handleUpdateButtonAction(ActionEvent event) {
		//handle artist data submit
	}
}
