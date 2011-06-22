package datascheme;
import java.util.Date;


public class Lehrgang {
	private Integer id;
	private Date Datum;
	
	public Lehrgang(Integer Id, Date Date) {
		id = Id;
		Datum = Date;
	}
	
	public Integer getId() {
		return id;
	}
	public void setName(Integer id) {
		this.id = id;
	}
	public Date getDatum() {
		return Datum;
	}
	public void setDatum(Date datum) {
		Datum = datum;
	}
	
	public Object[] getDBObject(Integer uid) {
		return new Object[]{null,uid,id,Datum};
	}
}
