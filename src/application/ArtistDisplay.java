package application;

import java.sql.Date;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ArtistDisplay {
	SimpleStringProperty ID, FN, LN, BP, S;
	SimpleObjectProperty<Date> DOB;
	public ArtistDisplay(String ID, String FN, String LN, String BP, Date DOB, String S) {
		this.ID = new SimpleStringProperty(ID);
		this.FN = new SimpleStringProperty(FN);
		this.LN = new SimpleStringProperty(LN);
		this.BP = new SimpleStringProperty(BP);
		this.S = new SimpleStringProperty(S);
		this.DOB = new SimpleObjectProperty<Date>(DOB);
	}
	public String getID() {
		return ID.get();
	}
	public String getFN() {
		return FN.get();
	}
	public String getLN() {
		return LN.get();
	}
	public String getBP() {
		return BP.get();
	}
	public String getS() {
		return S.get();
	}
	public Date getDOB() {
		return DOB.get();
	}
	public void setID(String ID) {
		this.ID.set(ID);
	}
	public void setFN(String FN) {
		this.FN.set(FN);
	}
	public void setLN(String LN) {
		this.LN.set(LN);
	}
	public void setBP(String BP) {
		this.BP.set(BP);
	}
	public void setS(String S) {
		this.S.set(S);
	}
	public void setDOB(Date DOB) {
		this.DOB.set(DOB);
	}
	public StringProperty IDproperty() {
		return ID;
	}
	public StringProperty FNproperty() {
		return FN;
	}
	public StringProperty LNproperty() {
		return LN;
	}
	public StringProperty BPproperty() {
		return BP;
	}
	public StringProperty Sproperty() {
		return S;
	}
	public SimpleObjectProperty<Date> DOBproperty() {
		return DOB;
	}
}
