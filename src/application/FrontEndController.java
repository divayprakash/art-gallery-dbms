package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

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
			//e.printStackTrace();
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
    			//e.printStackTrace();
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
	    	//e.printStackTrace();
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
	    	//e.printStackTrace();
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
			//e.printStackTrace();
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
        	//e.printStackTrace();
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
    private DatePicker ExploreArtistDOB;

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
    TableView<ArtistDisplay> ArtistTable;

    @FXML
	private TableColumn<ArtistDisplay,String> ID;

    @FXML
	private TableColumn<ArtistDisplay,String> FN;

    @FXML
	private TableColumn<ArtistDisplay,String> LN;

    @FXML
	private TableColumn<ArtistDisplay,String> BP;

    @FXML
	private TableColumn<ArtistDisplay,String> S;

    @FXML
	private TableColumn<ArtistDisplay,Date> DOB;


    @FXML
    void ArtistExploreAnd(ActionEvent event) throws IOException {
    	ResultSet rs=null;
        try {
        	Parent secondaryParent = FXMLLoader.load(getClass().getResource("ArtistDisplayTable.fxml"));
   			Scene secondaryScene = new Scene(secondaryParent);
   			Stage secondaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
   			secondaryStage.setScene(secondaryScene);
        	conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String cond1;
			if (ExploreArtistUniqueID.getText() == null || ExploreArtistUniqueID.getText().trim().isEmpty()) cond1="";
			else cond1 = "id='"+ExploreArtistUniqueID.getText()+"'AND ";
			String cond2;
			if (ExploreArtistFirstName.getText() == null || ExploreArtistFirstName.getText().trim().isEmpty()) cond2="";
			else cond2 = "fn='"+ExploreArtistFirstName.getText()+"'AND ";
			String cond3;
			if (ExploreArtistLastName.getText() == null || ExploreArtistLastName.getText().trim().isEmpty()) cond3="";
			else cond3 = "ln='"+ExploreArtistLastName.getText()+"'AND ";
			String cond41, cond42, cond43;
			if (ExploreArtistDOB.getValue() == null) cond41=cond42=cond43="";
			else {
				if (ExploreArtistDOBLessThan.isSelected())
					cond41 = "dob<'"+ExploreArtistDOB.getValue()+"'AND ";
				else cond41="";
				if (ExploreArtistDOBMoreThan.isSelected())
					cond42 = "dob>'"+ExploreArtistDOB.getValue()+"'AND ";
				else cond42="";
				if (ExploreArtistDOBOn.isSelected())
					cond43 = "dob='"+ExploreArtistDOB.getValue()+"'AND ";
				else cond43="";
			}
			String cond5;
			if (ExploreArtistStyle.getText() == null || ExploreArtistStyle.getText().trim().isEmpty()) cond5="";
			else cond5 = "s='"+ExploreArtistStyle.getText()+"'AND ";
			String cond6;
			if (ExploreArtistBirthPlace.getText() == null || ExploreArtistBirthPlace.getText().trim().isEmpty()) cond6="";
			else cond6 = "bp='"+ExploreArtistBirthPlace.getText()+"'AND ";
			String condwhere;
			if (cond1=="" && cond2=="" && cond3=="" && cond41=="" && cond42=="" && cond43=="" && cond5=="" && cond6=="") condwhere = "";
			else condwhere = " where ";
			String sql = "select * from artist"+condwhere+cond1+cond2+cond3+cond41+cond42+cond43+cond5+cond6;
			int len = sql.length();
			if (condwhere == null || condwhere.trim().isEmpty()) {
				System.out.println(sql);
				rs = stmt.executeQuery(sql);
			}
			else {
				String sql2=sql.substring(0,(len-4));
				System.out.println(sql2);
				rs = stmt.executeQuery(sql2);
			}
			final ObservableList<ArtistDisplay> data = FXCollections.observableArrayList();
			while(rs.next())
			{
				String data1=rs.getString("id");
				System.out.print(data1+", ");
				String data2=rs.getString("fn");
				System.out.print(data2+", ");
				String data3=rs.getString("ln");
				System.out.print(data3+", ");
				String data4=rs.getString("bp");
				System.out.print(data4+", ");
				Date data5=rs.getDate("dob");
				System.out.print(data5+", ");
				String data6=rs.getString("s");
				System.out.println(data6);
				data.add(new ArtistDisplay(data1, data2, data3, data4, data5, data6));
			}
			System.out.println(data);
			ID.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ArtistDisplay,String>, ObservableValue<String>>() {
				public ObservableValue<String> call(CellDataFeatures<ArtistDisplay, String> p) {
					return p.getValue().IDproperty();
				}
			});
			FN.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ArtistDisplay,String>, ObservableValue<String>>() {
				public ObservableValue<String> call(CellDataFeatures<ArtistDisplay, String> p) {
					return p.getValue().FNproperty();
				}
			});
			LN.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ArtistDisplay,String>, ObservableValue<String>>() {
				public ObservableValue<String> call(CellDataFeatures<ArtistDisplay, String> p) {
					return p.getValue().LNproperty();
				}
			});
			BP.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ArtistDisplay,String>, ObservableValue<String>>() {
				public ObservableValue<String> call(CellDataFeatures<ArtistDisplay, String> p) {
					return p.getValue().BPproperty();
				}
			});
			S.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ArtistDisplay,String>, ObservableValue<String>>() {
				public ObservableValue<String> call(CellDataFeatures<ArtistDisplay, String> p) {
					return p.getValue().Sproperty();
				}
			});
			DOB.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ArtistDisplay,Date>, ObservableValue<Date>>() {
				public ObservableValue<Date> call(CellDataFeatures<ArtistDisplay, Date> p) {
					return p.getValue().DOBproperty();
				}
			});
			ArtistTable.setItems(data);
			/*
			ID.setCellValueFactory(cellData -> cellData.getValue().IDproperty());
			FN.setCellValueFactory(cellData -> cellData.getValue().FNproperty());
			LN.setCellValueFactory(cellData -> cellData.getValue().LNproperty());
			BP.setCellValueFactory(cellData -> cellData.getValue().BPproperty());
			S.setCellValueFactory(cellData -> cellData.getValue().Sproperty());
			DOB.setCellValueFactory(cellData -> cellData.getValue().DOBproperty());
			*/
   			secondaryStage.show();
        }
        catch (Exception e) {
        	//e.printStackTrace();
        }
    }
    @FXML
    void ArtistExploreOr(ActionEvent event) {
    	ResultSet rs=null;
        try {
        	Parent secondaryParent = FXMLLoader.load(getClass().getResource("ArtistDisplayTable.fxml"));
   			Scene secondaryScene = new Scene(secondaryParent);
   			Stage secondaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
   			secondaryStage.setScene(secondaryScene);


        	conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String cond1;
			if (ExploreArtistUniqueID.getText() == null || ExploreArtistUniqueID.getText().trim().isEmpty()) cond1="";
			else cond1 = "id='"+ExploreArtistUniqueID.getText()+"'OR ";
			String cond2;
			if (ExploreArtistFirstName.getText() == null || ExploreArtistFirstName.getText().trim().isEmpty()) cond2="";
			else cond2 = "fn='"+ExploreArtistFirstName.getText()+"'OR ";
			String cond3;
			if (ExploreArtistLastName.getText() == null || ExploreArtistLastName.getText().trim().isEmpty()) cond3="";
			else cond3 = "ln='"+ExploreArtistLastName.getText()+"'OR ";
			String cond41, cond42, cond43;
			if (ExploreArtistDOB.getValue() == null) cond41=cond42=cond43="";
			else {
				if (ExploreArtistDOBLessThan.isSelected())
					cond41 = "dob<'"+ExploreArtistDOB.getValue()+"'OR ";
				else cond41="";
				if (ExploreArtistDOBMoreThan.isSelected())
					cond42 = "dob>'"+ExploreArtistDOB.getValue()+"'OR ";
				else cond42="";
				if (ExploreArtistDOBOn.isSelected())
					cond43 = "dob='"+ExploreArtistDOB.getValue()+"'OR ";
				else cond43="";
			}
			String cond5;
			if (ExploreArtistStyle.getText() == null || ExploreArtistStyle.getText().trim().isEmpty()) cond5="";
			else cond5 = "s='"+ExploreArtistStyle.getText()+"'OR ";
			String cond6;
			if (ExploreArtistBirthPlace.getText() == null || ExploreArtistBirthPlace.getText().trim().isEmpty()) cond6="";
			else cond6 = "bp='"+ExploreArtistBirthPlace.getText()+"'OR ";
			String condwhere;
			if (cond1=="" && cond2=="" && cond3=="" && cond41=="" && cond42=="" && cond43=="" && cond5=="" && cond6=="") condwhere = "";
			else condwhere = " where ";
			String sql = "select * from artist"+condwhere+cond1+cond2+cond3+cond41+cond42+cond43+cond5+cond6;
			int len = sql.length();
			if (condwhere == null || condwhere.trim().isEmpty()) {
				System.out.println(sql);
				rs = stmt.executeQuery(sql);
			}
			else {
				String sql2=sql.substring(0,(len-3));
				System.out.println(sql2);
				rs = stmt.executeQuery(sql2);
			}
			final ObservableList<ArtistDisplay> data = FXCollections.observableArrayList();
			while(rs.next())
			{
				String data1=rs.getString("id");
				System.out.print(data1+", ");
				String data2=rs.getString("fn");
				System.out.print(data2+", ");
				String data3=rs.getString("ln");
				System.out.print(data3+", ");
				String data4=rs.getString("bp");
				System.out.print(data4+", ");
				Date data5=rs.getDate("dob");
				System.out.print(data5+", ");
				String data6=rs.getString("s");
				System.out.println(data6);
				data.add(new ArtistDisplay(data1, data2, data3, data4, data5, data6));
			}
			//System.out.println(data);
			ID.setCellValueFactory(
	      		      new PropertyValueFactory<ArtistDisplay,String>("ID")
	      		      );
	      	FN.setCellValueFactory(
	    		      new PropertyValueFactory<ArtistDisplay,String>("FN")
	    		      );
	      	LN.setCellValueFactory(
	    		      new PropertyValueFactory<ArtistDisplay,String>("LN")
	    		      );
	      	BP.setCellValueFactory(
	    		      new PropertyValueFactory<ArtistDisplay,String>("BP")
	    		      );
	      	S.setCellValueFactory(
	    		      new PropertyValueFactory<ArtistDisplay,String>("S")
	    		      );
	      	DOB.setCellValueFactory(
	    		      new PropertyValueFactory<ArtistDisplay,Date>("DOB")
	    		      );
			ArtistTable.setItems(data);
   			secondaryStage.show();
        }
        catch (Exception e) {
        	//e.printStackTrace();
        }
    }


    /*************************************************************************/
	// NEW ARTWORK
    /*************************************************************************/

    @FXML
    private Text LabelNewArtworkMandatoryFields;

    @FXML
    private TextField NewArtworkArtistID;

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
    private TextField NewArtworkLastName;

    @FXML
    void NewArtworkAdd(ActionEvent event) throws SQLException
    {
    	//System.out.println("blah");
    	if (!(NewArtworkTitle.getText()==null || NewArtworkTitle.getText().trim().isEmpty() || NewArtworkArtistID.getText()==null || NewArtworkArtistID.getText().trim().isEmpty() || NewArtworkYear.getText()==null || NewArtworkYear.getText().trim().isEmpty() || NewArtworkType.getText()==null || NewArtworkType.getText().trim().isEmpty() || NewArtworkPrice.getText()==null || NewArtworkPrice.getText().trim().isEmpty()))
        {
    		//System.out.println("Here1");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();

            String sql="select exists(select * from artist where id='"+NewArtworkArtistID.getText()+"');";
            Boolean artistExist= stmt.execute(sql);
            //System.out.println(artistExist);
            if(artistExist)
            {
            	//System.out.println("Here");
                String uniqueID="R";
                String labelText="Successfully added entry " + uniqueID;
                try {
                        BufferedReader inputFile = new BufferedReader(new FileReader("./src/application/ArtworkID.txt"));
                        String input = inputFile.readLine();
                        inputFile.close();
                        int nextID = Integer.parseInt(input) + 1;
                        uniqueID += nextID;
                        BufferedWriter outputFile = new BufferedWriter(new FileWriter("./src/application/ArtworkID.txt"));
                        outputFile.write(nextID+"");
                        outputFile.close();
                        conn = DriverManager.getConnection(DB_URL,USER,PASS);
    			stmt = conn.createStatement();
    			sql = "insert into artwork values ('" +
    			uniqueID + "','" +
    			NewArtworkTitle.getText() + "','" +
    			NewArtworkArtistID.getText() + "','" +
    			NewArtworkYear.getText() + "','" +
    			NewArtworkType.getText() + "','" +
                        NewArtworkPrice.getText() + "');" ;
    			stmt.executeUpdate(sql);
                        String str=NewArtworkGroups.getText();
                        if(!(str==null || str.trim().isEmpty()))
                    {
                        List<String> grpList = Arrays.asList(str.split(","));
                        for(int i = 0; i < grpList.size(); i++)
                    {
                        	/*
                        stmt = conn.createStatement();
                        sql = "select exists (select * from groups where n='" +grpList.get(i)+"');";
                        Boolean available=stmt.execute(sql);
                        System.out.println(available);
                        if(!available)
                        {   conn = DriverManager.getConnection(DB_URL,USER,PASS);
                            stmt = conn.createStatement();
                            sql="insert into groups values('"+grpList.get(i)+"');";
                            stmt.executeUpdate(sql);
                        }*/
                        conn = DriverManager.getConnection(DB_URL,USER,PASS);
                        stmt = conn.createStatement();
                        sql="insert into grouped values('"+uniqueID+"','"+grpList.get(i)+"');";
                        stmt.executeUpdate(sql);
                    }
                }
            }

            catch (Exception e)
            {
    			//e.printStackTrace();
            }
            LabelNewArtworkAdded.setText(labelText);

        }
        else
         {
             LabelNewArtworkAdded.setText("Artist not available");

         }
         NewArtworkArtistID.setText(null);
         NewArtworkPrice.setText(null);
         NewArtworkTitle.setText(null);
         NewArtworkType.setText(null);
         NewArtworkGroups.setText(null);
         NewArtworkYear.setText(null);
         LabelNewArtworkAdded.setVisible(true);

    	}
    	else
        {
            LabelNewArtworkAdded.setVisible(false);
        }

    }

    @FXML
    void NewArtworkMainMenu(ActionEvent event) throws IOException
    {
        Parent parent = FXMLLoader.load(getClass().getResource("FrontEnd.fxml"));
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
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
    private Button UpdateArtworkLastNameButton;

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
    private TextField UpdateArtworkArtistID;

    @FXML
    private TextField UpdateArtworkLastName;

    @FXML
    private Button UpdateArtworkMainMenu;

    @FXML
    private TextField UpdateArtworkUniqueID;

    @FXML
    private Text LabelUpdateArtworkMandatoryField;

    @FXML
    private Button UpdateArtworkTitleButton;

    @FXML
    private Button UpdateArtworkArtistIDButton;

    @FXML
    private TextField UpdateArtworkGroups;

    @FXML
    private Button UpdateArtworkPriceButton;

    @FXML
    private Button UpdateArtworkGroupButton;

    @FXML
    private Button UpdateArtworkPrizeButton;

    @FXML
    private Button UpdateArtworkPrize;


    @FXML
    void UpdateArtworkMainMenu(ActionEvent event) throws IOException
    {
    	Parent parent = FXMLLoader.load(getClass().getResource("FrontEnd.fxml"));
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    void UpdateArtworkType(ActionEvent event)
    {
    	UpdateArtworkType.setDisable(false);
        UpdateArtworkType.requestFocus();
    }

    @FXML
    void UpdateArtworkYear(ActionEvent event)
    {
    	UpdateArtworkYear.setDisable(false);
    	UpdateArtworkYear.requestFocus();
    }

    @FXML
    void UpdateArtworkArtistID(ActionEvent event)
    {
    	UpdateArtworkArtistID.setDisable(false);
    	UpdateArtworkArtistID.requestFocus();
    }



    @FXML
    void UpdateArtworkTitle(ActionEvent event)
    {
    	UpdateArtworkTitle.setDisable(false);
    	UpdateArtworkTitle.requestFocus();
    }

    @FXML
    void UpdateArtworkPrice(ActionEvent event)
    {
    	UpdateArtworkPrice.setDisable(false);
    	UpdateArtworkPrice.requestFocus();
    }

    @FXML
    void UpdateArtworkGroup(ActionEvent event)
    {

        UpdateArtworkGroups.setDisable(false);
    	UpdateArtworkGroups.requestFocus();
    }

    @FXML
    void ArtworkUpdateIDEnter(ActionEvent event) throws IOException
    {
        ResultSet rs=checkArtwork(UpdateArtworkUniqueID);
        try {
		if(rs.next())
			{
			     	UpdateArtworkArtistIDButton.setDisable(false);
			        UpdateArtworkArtistID.setText(rs.getString("aid"));
			        UpdateArtworkType.setDisable(false);
			        UpdateArtworkType.setText(rs.getString("ty"));
			        UpdateArtworkYearButton.setDisable(false);
			        UpdateArtworkYear.setText(rs.getString("y"));
			        UpdateArtworkTitle.setDisable(false);
			        UpdateArtworkTitle.setText(rs.getString("t"));
			        ArtworkUpdate.setDisable(false);
			        ArtworkDelete.setDisable(false);
			        LabelUpdateArtworkAdded.setVisible(false);
			}
			else {
				LabelUpdateArtworkAdded.setText("No record found!");
				UpdateArtworkArtistID.setText(null);
				UpdateArtworkGroups.setText(null);
		        UpdateArtworkPrice.setText(null);
		        UpdateArtworkTitle.setText(null);
		        UpdateArtworkType.setText(null);
		        UpdateArtworkUniqueID.setText(null);
				LabelUpdateArtworkAdded.setVisible(true);
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
    }

    @FXML
    void ArtworkDelete(ActionEvent event)
    {
        try {
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
		stmt = conn.createStatement();
		String sql = "delete from artwork where id='" +
		UpdateArtworkArtistID.getText() + "';" ;
		stmt.executeUpdate(sql);
		LabelUpdateArtworkAdded.setText("Record deleted!");
		LabelUpdateArtworkAdded.setFill(Color.RED);
		LabelUpdateArtworkAdded.setVisible(true);
                UpdateArtworkArtistID.setText(null);
		UpdateArtworkYear.setText(null);
	        UpdateArtworkType.setText(null);
	        UpdateArtworkPrize.setText(null);
	        UpdateArtworkTitle.setText(null);

	     }
	     catch (Exception e) {
	    	//e.printStackTrace();
	     }
    }

    @FXML
    void ArtworkUpdate(ActionEvent event)
    {
        try {
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
		stmt = conn.createStatement();
		String sql = "update artwork set t='" +
		UpdateArtworkTitle.getText() + "',aid='" +
		UpdateArtworkArtistID.getText() + "',y='" +
		UpdateArtworkYear.getText() + "',ty='" +
		UpdateArtworkType.getText() + "',p='" +
		UpdateArtworkPrize.getText() + "' where id='" +
		UpdateArtworkArtistID.getText() + "';" ;
                stmt.executeUpdate(sql);
		LabelUpdateArtworkAdded.setText("Record updated!");
		LabelUpdateArtworkAdded.setFill(Color.RED);
		LabelUpdateArtworkAdded.setVisible(true);
		UpdateArtworkArtistID.setText(null);
		UpdateArtworkYear.setText(null);
	        UpdateArtworkType.setText(null);
	        UpdateArtworkPrize.setText(null);
	        UpdateArtworkTitle.setText(null);
	     }
	     catch (Exception e) {
	    	//e.printStackTrace();
	     }
    }

    @FXML
    void UpdateArtworkDetails(ActionEvent event) throws IOException
    {
    	Parent parent = FXMLLoader.load(getClass().getResource("ArtworkUpdate.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

     @FXML
    private ResultSet checkArtwork(TextField UpdateArtworkUniqueID)
    {
    	String id = UpdateArtworkUniqueID.getText();
        ResultSet rs=null;
        try {
        	conn = DriverManager.getConnection(DB_URL,USER,PASS);
		stmt = conn.createStatement();
		String sql = "select * from  artwork where id='" + id +"';";
		rs = stmt.executeQuery(sql);
        }
        catch (Exception e) {
        	//e.printStackTrace();
        }
        return rs;
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
    private Text LabelNewCustomerAdded;

    @FXML
    private TextField NewCustomerLastName;

    @FXML
    private TextField NewCustomerPrefArtists;

    @FXML
    private TextField NewCustomerPrefGroups;

    @FXML
    private TextField NewCustomerFirstName;

    @FXML
    private TextField NewCustomerMoneySpent;



    @FXML
    void NewCustomerAdd(ActionEvent event)
    {
        if(NewCustomerFirstName.getText()==null || NewCustomerFirstName.getText().trim().isEmpty()|| NewCustomerLastName.getText()==null || NewCustomerLastName.getText().trim().isEmpty() || NewCustomerMoneySpent.getText()==null || NewCustomerMoneySpent.getText().trim().isEmpty())
        {
            LabelNewCustomerAdded.setText("Please fill all the mandatory fields");
            LabelNewCustomerAdded.setVisible(true);

        }
        else
        {
            String UniqueID=null;
            UniqueID="Successfully added entry with ID: "+UniqueID;
            LabelNewCustomerAdded.setText(UniqueID);
            LabelNewCustomerAdded.setVisible(true);
        }
    }

    @FXML
    void NewCustomerMainMenu(ActionEvent event) throws IOException
    {
        Parent parent = FXMLLoader.load(getClass().getResource("FrontEnd.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

     @FXML
    void AddNewCustomer(ActionEvent event) throws IOException
    {
        Parent parent = FXMLLoader.load(getClass().getResource("NewCustomer.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    /*************************************************************************/
	// UPDATE CUSTOMER
    /*************************************************************************/
    @FXML
    void UpdateCustomerDetails(ActionEvent event) throws IOException
    {
        Parent parent = FXMLLoader.load(getClass().getResource("CustomerUpdate.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private Button UpdateCustomerMainMenu;

    @FXML
    private TextField UpdateCustomerMoneySpent;

    @FXML
    private Button UpdateCustomerFirstNameButton;

    @FXML
    private Button UpdateCustomerPrefArtistsButton;

    @FXML
    private TextField UpdateCustomerUniqueID;

    @FXML
    private TextField UpdateCustomerFirstName;

    @FXML
    private TextField UpdateCustomerLastName;

    @FXML
    private Button UpdateCustomerLastNameButton;

    @FXML
    private Text LabelUpdateCustomerAdded;

    @FXML
    private Button UpdateCustomerMoneySpentButton;

    @FXML
    private TextField UpdateCustomerPrefArtists;

    @FXML
    private Button CustomerDelete;

    @FXML
    private Button CustomerUpdate;

    @FXML
    void CustomerUpdate(ActionEvent event)
    {

    }
    @FXML
    void UpdateCustomerMainMenu(ActionEvent event) throws IOException
    {
        Parent parent = FXMLLoader.load(getClass().getResource("CustomerUpdate.fxml"));
	Scene scene = new Scene(parent);
	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	stage.setScene(scene);
	stage.show();
    }

    @FXML
    void UpdateCustomerMoneySpent(ActionEvent event)
    {
        UpdateCustomerMoneySpent.setDisable(false);
    	UpdateCustomerMoneySpent.requestFocus();
    }

    @FXML
    void UpdateCustomerLastName(ActionEvent event)
    {
        UpdateCustomerLastName.setDisable(false);
    	UpdateCustomerLastName.requestFocus();
    }

    @FXML
    void UpdateCustomerFirstName(ActionEvent event)
    {
        UpdateCustomerLastName.setDisable(false);
    	UpdateCustomerLastName.requestFocus();
    }

    @FXML
    void CustomerDelete(ActionEvent event)
    {

    }

    @FXML
    void UpdateCustomerPrefArtists(ActionEvent event)
    {
        UpdateCustomerPrefArtists.setDisable(false);
    	UpdateCustomerPrefArtists.requestFocus();
    }

    @FXML
    void UpdateCustomerIDEnter(ActionEvent event)
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