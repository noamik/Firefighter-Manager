package datascheme;
import java.util.Date;

import data.GlobalData;
import util.util;


/**
 * @author noamik
 * This class holds all data related to a member
 */
public class Mitglied {
	/**
	 * 
	 */
	private Integer Id;
	private String Name;
	private String Vorname;
	private Integer Dienstgrad;
	private Date Aufnahmedatum;
	private Date Geburtsdatum;
	private Ausbildung Ausbildung;
	private Karriere Karriere;
	private Integer Status;
	
	/**
	 * @param Id
	 * @param Name
	 * @param Vorname
	 * @param Dienstgrad
	 * @param Aufnahmedatum
	 * @param Geburtsdatum
	 * @param Ausbildung
	 * @param Karriere
	 * @param MemberStatus
	 */
	public Mitglied(Integer Id, String name, String vorname, Integer Dienstgrad, Date Aufnahmedatum, Date Geburtsdatum, Ausbildung Ausbildung, Karriere Karriere, Integer status) {
		this.Name          = name;
		this.Vorname       = vorname;
		this.Id            = Id;
		this.Dienstgrad    = Dienstgrad;
		this.Aufnahmedatum = Aufnahmedatum;
		this.Geburtsdatum  = Geburtsdatum;
		this.Ausbildung    = Ausbildung;
		this.Karriere      = Karriere;
		this.Status        = status;
	}
	
	/**
	 * @return
	 */
	public String getName() {
		return Name;
	}
	/**
	 * @param name
	 */
	public void setName(String name) {
		Name = name;
	}
	/**
	 * @return
	 */
	public String getVorname() {
		return Vorname;
	}
	/**
	 * @param vorname
	 */
	public void setVorname(String vorname) {
		Vorname = vorname;
	}
	/**
	 * @return
	 */
	public String getDienstgrad() {
		return GlobalData.getInstance().getDienstgrade().getElement(Dienstgrad);
	}
	/**
	 * @return
	 */
	public Integer getDienstgradId() {
		return Dienstgrad;
	}
	/**
	 * @param dienstgrad
	 */
	public void setDienstgrad(Integer dienstgrad) {
		Dienstgrad = dienstgrad;
	}
	/**
	 * @return
	 */
	public Date getAufnahmedatum() {
		return Aufnahmedatum;
	}
	/**
	 * @param aufnahmedatum
	 */
	public void setAufnahmedatum(Date aufnahmedatum) {
		Aufnahmedatum = aufnahmedatum;
	}
	/**
	 * @return
	 */
	public Date getGeburtsdatum() {
		return Geburtsdatum;
	}
	/**
	 * @param geburtsdatum
	 */
	public void setGeburtsdatum(Date geburtsdatum) {
		Geburtsdatum = geburtsdatum;
	}
	/**
	 * @return
	 */
	public Ausbildung getAusbildung() {
		return Ausbildung;
	}
	/**
	 * @param ausbildung
	 */
	public void setAusbildung(Ausbildung ausbildung) {
		Ausbildung = ausbildung;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String returnme = "Name: " + this.Name + util.NewLine;
		returnme += "Vorname: " + this.Vorname + util.NewLine;
		returnme += "Dienstgrad: " + this.Dienstgrad + util.NewLine;
		returnme += "Geburtsdatum: " + util.DateToString(this.Geburtsdatum) + util.NewLine;
		returnme += "Aufnahmedatum: " + util.DateToString(this.Aufnahmedatum) + util.NewLine;
		if(Ausbildung != null)
			returnme += "Ausbildung: " + this.Ausbildung.toString() + util.NewLine;
		else
			returnme += "Keine Ausbildung" + util.NewLine;
		returnme += "Status: " + this.Status + util.NewLine;
		return returnme;
	}

	/**
	 * @param id
	 */
	public void setId(Integer id) {
		Id = id;
	}

	/**
	 * @return
	 */
	public Integer getId() {
		return Id;
	}

	/**
	 * @param karriere the karriere to set
	 */
	public void setKarriere(Karriere karriere) {
		Karriere = karriere;
	}

	/**
	 * @return the karriere
	 */
	public Karriere getKarriere() {
		return Karriere;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		Status = status;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return Status;
	}
	
	public Object[] getDBObjectArray() {
		Object[] dbEntry = new Object[7];
		dbEntry[0] = this.Id;
		dbEntry[1] = this.Name;
		dbEntry[2] = this.Vorname;
		if(this.Geburtsdatum != null)
			dbEntry[3] = util.DateToString(this.Geburtsdatum);
		else
			dbEntry[3] = "Kein Datum gesetzt";
		if(this.Aufnahmedatum != null)
			dbEntry[4] = util.DateToString(this.Aufnahmedatum);
		else
			dbEntry[4] = "Kein Datum gesetzt";
		dbEntry[5] = this.Dienstgrad;
		dbEntry[6] = this.Status;
		return dbEntry;
	}
}
