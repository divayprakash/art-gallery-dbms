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

/**
 * Controller class for FrontEnd.fxml
 * @author Anant Mittal, Divay Prakash
 */
public class FrontEndController implements Initializable {
	/**
	 * Called to initialize a controller after its root element has been completely processed
	 * @param location The location used to resolve relative paths for the root object, or null if the location is not known
	 * @param resources The resources used to localize the root object, or null if the root object was not localized
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//initialize function
	}
	/**
	 * Boolean variable for tab selection
	 */
	private boolean tabSelection = false;
	/**
	 * Checks tab position and sets boolean variable for tab switching
	 * @return tabSelection
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
