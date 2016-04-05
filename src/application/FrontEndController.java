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
	@FXML void handleArtistSubmit(ActionEvent event) {
		//handle artist data submit
	}
	/*
	 * Artwork Tab
	 */
	@FXML private TextField artworkTitle;
	@FXML private TextField artworkArtistFirstName;
	@FXML private TextField artworkArtistLastName;
	@FXML private TextField artworkYear;
	@FXML private TextField artworkType;
	@FXML private TextArea artworkThemes;
	@FXML private TextField artworkPrice;
	@FXML void handleArtworkTab(Event event) {
		if (checkTab()) {
			//handle initialization for artwork tab
		}
	}
	@FXML void handleArtworkSubmit(ActionEvent event) {
		//handle artwork data submit
	}
	/*
	 * Customer Tab
	 */
	@FXML private TextField customerFirstName;
	@FXML private TextField customerLastName;
	@FXML private TextArea customerAddress;
	@FXML private TextField customerMoney;
	@FXML private TextArea cutomerArtists;
	@FXML private TextArea customerArt;
	@FXML void handleCustomerTab(Event event) {
		if (checkTab()) {
			//handle initialization for customer tab
		}
	}
	@FXML void handleCustomerSubmit(ActionEvent event) {
		//handle customer data submit
	}
	/*
	 * Queries Tab
	 */
	@FXML void handleQueriesTab(Event event) {
		if (checkTab()) {
			//handle initialization for Queries tab
		}
	}
}
