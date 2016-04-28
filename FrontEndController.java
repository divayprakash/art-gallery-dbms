package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FrontEndController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    static final String DB_URL = "jdbc:mysql://localhost/artGallery";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";
    Connection conn = null;
	Statement stmt = null;
    public void db()
    {
    	   try {
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			ResultSet s=stmt.executeQuery("select * from test");
			while(s.next())
			{
				String name=s.getString("user");
				System.out.println(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    /*************************************************************************/
	// NEW ARTIST
    /*************************************************************************/
    @FXML
    private Button AddNewArtistButton;

    @FXML
    private Text LabelNewArtistAdded;

    @FXML
    private TextField NewArtistLastName;

    @FXML
    private Button NewArtistAdd;

    @FXML
    private Button NewArtistMainMenu;

    @FXML
    private Text LabelNewArtistMandatoryFields;

    @FXML
    private TextField NewArtistStyle;

    @FXML
    private TextField NewArtistBirthPlace;

    @FXML
    private DatePicker NewArtistDOB;

    @FXML
    private TextField NewArtistFirstName;

    @FXML
    void AddNewArtist(ActionEvent event) throws IOException
    {
        Parent parent = FXMLLoader.load(getClass().getResource("NewArtist.fxml"));
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    void NewArtistAdd(ActionEvent event)
    {
        if(NewArtistFirstName.getText()!=null && NewArtistLastName.getText()!=null && NewArtistDOB.getValue()!=null && NewArtistBirthPlace.getText()!=null && NewArtistStyle.getText()!=null)
        {

        	String uniqueID="A";
            String labelText="Successfully added entry " + uniqueID;
            try {
            	BufferedReader inputFile = new BufferedReader(new FileReader("./src/application/ArtistID.txt"));
            	String input = inputFile.readLine();
            	inputFile.close();
            	int nextID = Integer.parseInt(input) + 1;
            	uniqueID += nextID;
            	BufferedWriter outputFile = new BufferedWriter(new FileWriter("./src/application/ArtistID.txt"));
            	outputFile.write(nextID+"");
            	outputFile.close();
            	conn = DriverManager.getConnection(DB_URL,USER,PASS);
    			stmt = conn.createStatement();
    			String sql = "insert into artist values ('" +
    			uniqueID + "','" +
    			NewArtistFirstName.getText() + "','" +
    			NewArtistLastName.getText() + "','" +
    			NewArtistDOB.getValue() + "','" +
    			NewArtistBirthPlace.getText() + "','" +
    			NewArtistStyle.getText() + "');" ;
    			stmt.executeUpdate(sql);
    		}
            catch (Exception e) {
    			e.printStackTrace();
    		}
            LabelNewArtistAdded.setText(labelText);
            NewArtistFirstName.setText(null);
			NewArtistLastName.setText(null);
			NewArtistDOB.setValue(null);
			NewArtistBirthPlace.setText(null);
			NewArtistStyle.setText(null);
            LabelNewArtistAdded.setVisible(true);
            LabelNewArtistMandatoryFields.setVisible(false);
        }
        else
        {
            LabelNewArtistMandatoryFields.setVisible(true);
            LabelNewArtistAdded.setVisible(false);
        }
    }

    @FXML
    void NewArtistMainMenu(ActionEvent event) throws IOException
    {
        Parent parent = FXMLLoader.load(getClass().getResource("FrontEnd.fxml"));
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }

    /*************************************************************************/
	// UPDATE ARTIST
    /*************************************************************************/
    @FXML
    private TextField UpdateArtistUniqueID;

    @FXML
    private Button ArtistDelete;

    @FXML
    private TextField UpdateArtistBirthPlace;

    @FXML
    private Button UpdateArtistBirthPlaceButton;

    @FXML
    private TextField UpdateArtistStyle;

    @FXML
    private Button UpdateArtistStyleButton;

    @FXML
    private TextField UpdateArtistLastName;

    @FXML
    private Button UpdateArtistLastNameButton;

    @FXML
    private Text LabelUpdateArtistAlreadyExist;

    @FXML
    private Button ArtistUpdate;

    @FXML
    private Text LabelUpdateArtistMandatoryFields;

    @FXML
    private TextField UpdateArtistFirstName;

    @FXML
    private Button UpdateArtistFirstNameButton;

    @FXML
    private TextField UpdateArtistDOB;

    @FXML
    private Button UpdateArtistDOBButton;

    @FXML
    private Text LabelUpdateArtistAdded;

    @FXML
    private Button UpdateArtistMainMenu;

    @FXML
    void UpdateArtistDetails(ActionEvent event) throws IOException
    {
        Parent parent = FXMLLoader.load(getClass().getResource("ArtistUpdate.fxml"));
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    void UpdateArtistBirthPlace(ActionEvent event)
    {
        UpdateArtistBirthPlace.setDisable(false);
        UpdateArtistBirthPlace.requestFocus();
    }

    @FXML
    void UpdateArtistStyle(ActionEvent event)
    {
        UpdateArtistStyle.setDisable(false);
        UpdateArtistStyle.requestFocus();
    }

    @FXML
    void UpdateArtistDOB(ActionEvent event)
    {
        UpdateArtistDOB.setDisable(false);
        UpdateArtistDOB.requestFocus();
    }

    @FXML
    void UpdateArtistLastName(ActionEvent event)
    {
        UpdateArtistLastName.setDisable(false);
        UpdateArtistLastName.requestFocus();
    }

    @FXML
    void UpdateArtistFirstName(ActionEvent event)
    {
        UpdateArtistFirstName.setDisable(false);
        UpdateArtistFirstName.requestFocus();
    }

    @FXML
    void ArtistUpdate(ActionEvent event)
    {
    	 try {
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String sql = "update artist set fn='" +
				UpdateArtistFirstName.getText() + "', ln='" +
				UpdateArtistLastName.getText() + "',dob='" +
				UpdateArtistDOB.getText() + "',s='" +
				UpdateArtistStyle.getText() + "',bp='" +
				UpdateArtistBirthPlace.getText() + "' where id='" +
		    	UpdateArtistUniqueID.getText() + "';" ;
			stmt.executeUpdate(sql);
			LabelUpdateArtistAdded.setText("Record updated!");
			LabelUpdateArtistAdded.setFill(Color.RED);
			LabelUpdateArtistAdded.setVisible(true);
			UpdateArtistUniqueID.setText(null);
			UpdateArtistFirstName.setText(null);
	        UpdateArtistLastName.setText(null);
	        UpdateArtistDOB.setText(null);
	        UpdateArtistStyle.setText(null);
	        UpdateArtistBirthPlace.setText(null);
	     }
	     catch (Exception e) {
	    	e.printStackTrace();
	     }
    }

    @FXML
    void ArtistDelete(ActionEvent event) {
    	try {
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String sql = "delete from artist where id='" +
				UpdateArtistUniqueID.getText() + "';" ;
			stmt.executeUpdate(sql);
			LabelUpdateArtistAdded.setText("Record deleted!");
			LabelUpdateArtistAdded.setFill(Color.RED);
			LabelUpdateArtistAdded.setVisible(true);
			UpdateArtistUniqueID.setText(null);
			UpdateArtistFirstName.setText(null);
	        UpdateArtistLastName.setText(null);
	        UpdateArtistDOB.setText(null);
	        UpdateArtistStyle.setText(null);
	        UpdateArtistBirthPlace.setText(null);
	     }
	     catch (Exception e) {
	    	e.printStackTrace();
	     }
    }

    @FXML
    void ArtistUpdateIDEnter(ActionEvent event)
    {
        ResultSet rs=checkArtist(UpdateArtistUniqueID);
        try {
			if(rs.next())
			{
			     	UpdateArtistFirstNameButton.setDisable(false);
			        UpdateArtistFirstName.setText(rs.getString("fn"));
			        //UpdateArtistLastNameButton.setDisable(false);
			        UpdateArtistLastName.setText(rs.getString("ln"));
			        UpdateArtistDOBButton.setDisable(false);
			        UpdateArtistDOB.setText(rs.getString("dob"));
			        UpdateArtistStyleButton.setDisable(false);
			        UpdateArtistStyle.setText(rs.getString("s"));
			        UpdateArtistBirthPlaceButton.setDisable(false);
			        UpdateArtistBirthPlace.setText(rs.getString("bp"));
			        ArtistUpdate.setDisable(false);
			        ArtistDelete.setDisable(false);
			        LabelUpdateArtistAdded.setVisible(false);
			}
			else {
				LabelUpdateArtistAdded.setText("No record found!");
				UpdateArtistUniqueID.setText(null);
				UpdateArtistFirstName.setText(null);
		        UpdateArtistLastName.setText(null);
		        UpdateArtistDOB.setText(null);
		        UpdateArtistStyle.setText(null);
		        UpdateArtistBirthPlace.setText(null);
				LabelUpdateArtistAdded.setVisible(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void UpdateArtistMainMenu(ActionEvent event) throws IOException
    {
        Parent parent = FXMLLoader.load(getClass().getResource("FrontEnd.fxml"));
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }

    private ResultSet checkArtist(TextField ArtistID)
    {
    	String id = ArtistID.getText();
        ResultSet rs=null;
        try {
        	conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String sql = "select * from artist where id='" + id +"';";
			rs = stmt.executeQuery(sql);
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
        return rs;
    }

    /*************************************************************************/
	// EXPLORE ARTIST
    /*************************************************************************/
    @FXML
    private TextField ExploreArtistStyle;

    @FXML
    private TextField ExploreArtistLastName;

    @FXML
    private TextField ExploreArtistBirthPlace;

    @FXML
    private TextField ExploreArtistFirstName;

    @FXML
    private Button ExploreArtistMainMenu;

    @FXML
    private TextField ExploreArtistUniqueID;

    @FXML
    private Button ArtistExploreAnd;

    @FXML
    private Button ArtistExploreOr;

    @FXML
    private TextField ExploreArtistDOB;

    @FXML
    private CheckBox ExploreArtistDOBMoreThan;

    @FXML
    private CheckBox ExploreArtistDOBLessThan;

    @FXML
    private CheckBox ExploreArtistDOBOn;

    @FXML
    void ExploreArtistMainMenu(ActionEvent event) throws IOException
    {
        Parent parent = FXMLLoader.load(getClass().getResource("FrontEnd.fxml"));
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    void ExploreArtists(ActionEvent event) throws IOException
    {
        Parent parent = FXMLLoader.load(getClass().getResource("ArtistExplore.fxml"));
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    void ArtistExploreAnd(ActionEvent event) {

    }

    @FXML
    void ArtistExploreOr(ActionEvent event) {

    }

    /*************************************************************************/
	// NEW ARTWORK
    /*************************************************************************/

    @FXML
    private Text LabelNewArtworkMandatoryFields;

    @FXML
    private TextField NewArtworkArtistFirstName;

    @FXML
    private Text LabelNewArtworkAdded;

    @FXML
    private TextField NewArtworkYear;

    @FXML
    private TextField NewArtworkGroups;

    @FXML
    private TextField NewArtworkTitle;

    @FXML
    private TextField NewArtworkType;

    @FXML
    private Button NewArtworkMainMenu;

    @FXML
    private Button NewArtworkAdd;

    @FXML
    private TextField NewArtworkPrice;

    @FXML
    private TextField NewArtworkArtistLastName;

    @FXML
    void NewArtworkAdd(ActionEvent event)
    {
    	if (NewArtworkTitle.getText()!=null && NewArtworkArtistLastName.getText()!=null && NewArtworkArtistFirstName.getText()!=null && NewArtworkYear.getText()!=null && NewArtworkType.getText()!=null && NewArtworkPrice.getText()!=null) {
    		String UniqueID=null;
            UniqueID="Successfully added entry"+UniqueID;
            LabelNewArtworkAdded.setText(UniqueID);
            LabelNewArtworkAdded.setVisible(true);
            LabelNewArtworkMandatoryFields.setVisible(false);
    	}
    	else {
    		LabelNewArtworkMandatoryFields.setVisible(true);
    		LabelNewArtworkAdded.setVisible(false);
    	}
    }

    @FXML
    void NewArtworkMainMenu(ActionEvent event)
    {
        Parent parent;
		try {
			parent = FXMLLoader.load(getClass().getResource("FrontEnd.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

    @FXML
    void AddNewArtwork(ActionEvent event) throws IOException
    {
        Parent parent = FXMLLoader.load(getClass().getResource("NewArtwork.fxml"));
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }

    /*************************************************************************/
	// UPDATE ARTWORK
    /*************************************************************************/

    @FXML
    private Text LabelUpdateArtworkAdded;

    @FXML
    private TextField UpdateArtworkYear;

    @FXML
    private TextField UpdateArtworkType;

    @FXML
    private Button ArtworkDelete;

    @FXML
    private Button UpdateArtworkArtistLastNameButton;

    @FXML
    private TextField UpdateArtworkTitle;

    @FXML
    private Button UpdateArtworkYearButton;

    @FXML
    private Button UpdateArtworkTypeButton;

    @FXML
    private Button ArtworkUpdate;

    @FXML
    private TextField UpdateArtworkPrice;

    @FXML
    private TextField UpdateArtworkArtistFirstName;

    @FXML
    private TextField UpdateArtworkArtistLastName;

    @FXML
    private Button UpdateArtworkMainMenu;

    @FXML
    private TextField UpdateArtworkUniqueID;

    @FXML
    private Text LabelUpdateArtworkMandatoryField;

    @FXML
    private Button UpdateArtworkTitleButton;

    @FXML
    private Button UpdateArtworkArtistFirstNameButton;

    @FXML
    private TextField UpdateArtworkGroups;

    @FXML
    private Button UpdateArtworkPriceButton;

    @FXML
    private Button UpdateArtworkGroupButton;

    @FXML
    void UpdateArtworkMainMenu(ActionEvent event) {
    	Parent parent;
		try {
			parent = FXMLLoader.load(getClass().getResource("FrontEnd.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

    @FXML
    void UpdateArtworkType(ActionEvent event) {
    	UpdateArtworkType.setDisable(false);
        UpdateArtworkType.requestFocus();
    }

    @FXML
    void UpdateArtworkYear(ActionEvent event) {
    	UpdateArtworkYear.setDisable(false);
    	UpdateArtworkYear.requestFocus();
    }

    @FXML
    void UpdateArtworkArtistFirstName(ActionEvent event) {
    	UpdateArtworkArtistFirstName.setDisable(false);
    	UpdateArtworkArtistFirstName.requestFocus();
    }

    @FXML
    void UpdateArtworkArtistLastName(ActionEvent event) {
    	UpdateArtworkArtistLastName.setDisable(false);
    	UpdateArtworkArtistLastName.requestFocus();
    }

    @FXML
    void UpdateArtworkTitle(ActionEvent event) {
    	UpdateArtworkTitle.setDisable(false);
    	UpdateArtworkTitle.requestFocus();
    }

    @FXML
    void UpdateArtworkPrice(ActionEvent event) {
    	UpdateArtworkPrice.setDisable(false);
    	UpdateArtworkPrice.requestFocus();
    }

    @FXML
    void UpdateArtworkGroup(ActionEvent event) {
    	UpdateArtworkGroups.setDisable(false);
    	UpdateArtworkGroups.requestFocus();
    }

    @FXML
    void ArtworkUpdateIDEnter(ActionEvent event) {

    }

    @FXML
    void ArtworkDelete(ActionEvent event) {

    }

    @FXML
    void ArtworkUpdate(ActionEvent event) {

    }

    @FXML
    void UpdateArtworkDetails(ActionEvent event)
    {
    	Parent parent;
		try {
			parent = FXMLLoader.load(getClass().getResource("ArtworkUpdate.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

    /*************************************************************************/
	// EXPLORE ARTWORK
    /*************************************************************************/

    @FXML
    void ExploreArtwork(ActionEvent event)
    {

    }

    /*************************************************************************/
	// NEW CUSTOMER
    /*************************************************************************/
    @FXML
    void AddNewCustomer(ActionEvent event)
    {

    }

    /*************************************************************************/
	// UPDATE CUSTOMER
    /*************************************************************************/
    @FXML
    void UpdateCustomerDetails(ActionEvent event)
    {

    }

    /*************************************************************************/
	// EXPLORE CUSTOMER
    /*************************************************************************/
    @FXML
    void ViewCustomers(ActionEvent event)
    {

    }

    /*************************************************************************/
	// INITIAL
    /*************************************************************************/

    @FXML
    void initialize()
    {


    }


}
