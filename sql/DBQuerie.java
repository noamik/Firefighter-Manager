package sql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

import datascheme.Ausbildung;
import datascheme.Dienstgrad;
import datascheme.IdStringMatcher;
import datascheme.Karriere;
import datascheme.Lehrgang;
import datascheme.Mitglied;


/**
 * @author noamik
 *
 */
public class DBQuerie {
	
	private final String DGD_DienstgradId    = "DienstgradId";
	private final String DGD_DienstgradDatum = "Dienstgraddatum";
	private final String DGD_MitgliedsId     = "MitgliedsId";
	
	private final String AUS_Ausbildung      = "Ausbildung";
	
	private final String MS_STATUS           = "Status";
	
	private final String MIT_NAME            = "Name";
	private final String MIT_VORNAME         = "Vorname";
	private final String MIT_GEBDATE         = "Geburtsdatum";
	private final String MIT_AUFNDATE        = "Aufnahmedatum";
	private final String MIT_DIEGRAD         = "DienstgradId";
	
	private final String MAS_MITGLID         = "MitgliedsId";
	private final String MAS_AUSBID          = "AusbildungsId";
	private final String MAS_DATE            = "Datum";
	
	private final String DIG_DIENSTGRAD      = "Dienstgrad";
	
	private final String ID                  = "id";
	
	private final String GET_MEMBER_LIST       = "SELECT * FROM Mitglieder;";
	private final String GET_MEMBERS_TRAININGS = "SELECT * FROM Mitgliederausbildung WHERE " + MAS_MITGLID + " = ?;";
	private final String GET_MEMBERS_KARR      = "SELECT * FROM Dienstgraddaten WHERE " + DGD_MitgliedsId + " = ?;";
	private final String GET_TRAININGS         = "SELECT * FROM Ausbildungen;";
	private final String GET_DIENSTGRADE       = "SELECT * FROM Dienstgrade;";
	private final String GET_MEMBERSTATUS      = "SELECT * FROM MemberStatus;";
	
	private final String ADD_MLIST_MEMBER      = "INSERT OR REPLACE INTO Mitglieder VALUES (?, ?, ?, ?, ?, ?, ?);";
	private final String ADD_MTLIST_TRAINING   = "INSERT OR REPLACE INTO Mitgliederausbildung VALUES (?, ?, ?, ?);";
	private final String ADD_AUSBILDUNG        = "INSERT OR REPLACE INTO Ausbildungen VALUES (?, ?);";
	private final String ADD_DIENSTGRAD        = "INSERT OR REPLACE INTO Dienstgrade VALUES (?, ?);";
	private final String ADD_MITGL_KARRIERE    = "INSERT OR REPLACE INTO Dienstgraddaten VALUES (?, ?, ?, ?);";
	private final String ADD_MEMBERSTATUS      = "INSERT OR REPLACE INTO MemberStatus VALUES (?, ?);";
	
	private final String DEL_MEMBER            = "DELETE FROM Mitglieder WHERE "+ID+" = ?;";
	private final String DEL_M_TRAINING        = "DELETE FROM Mitgliederausbildung WHERE "+ID+" = ?;";
	private final String DEL_AUSBILDUNG        = "DELETE FROM Ausbildungen WHERE "+ID+" = ?;";
	private final String DEL_DIENSTGRAD        = "DELETE FROM Dienstgrade WHERE "+ID+" = ?;";
	private final String DEL_M_DIENSTGRAD      = "DELETE FROM Dienstgraddaten WHERE "+ID+" = ?;";
	private final String DEL_MEMBERSTATUS      = "DELETE FROM MemberStatus WHERE "+ID+" = ?;";
	
	private DateFormat dfm  = util.util.DatumsFormat;
	
	private Connection cn;
	
	
	/**
	 * 
	 */
	public DBQuerie() {
		DBConnectionPool.getInstance();
		cn = DBConnectionPool.getConnection();
	}
	
	/**
	 * @return
	 * @throws SQLException
	 */
	public datascheme.IdStringMatcher getTrainings() throws SQLException {
		IdStringMatcher Lehrgänge = new IdStringMatcher();
		Statement  st = cn.createStatement();
        ResultSet  rs = st.executeQuery( GET_TRAININGS );
        while (rs.next()) {
        	Lehrgänge.addElement(rs.getInt(ID)
        			           , rs.getString(AUS_Ausbildung));
        }
        rs.close();
		return Lehrgänge;
	}
	
	/**
	 * @return
	 * @throws SQLException
	 */
	public datascheme.IdStringMatcher getMemberStatusHashMap() throws SQLException {
		IdStringMatcher MemberStatus = new IdStringMatcher();
		Statement  st = cn.createStatement();
        ResultSet  rs = st.executeQuery( GET_MEMBERSTATUS );
        while (rs.next()) {
        	MemberStatus.addElement(rs.getInt(ID)
        			              , rs.getString(MS_STATUS));
        }
        rs.close();
		return MemberStatus;
	}
	
	/**
	 * @return
	 * @throws SQLException
	 */
	public datascheme.IdStringMatcher getDienstgrade() throws SQLException {
		IdStringMatcher Dienstgrade = new IdStringMatcher();
		Statement  st = cn.createStatement();
        ResultSet  rs = st.executeQuery( GET_DIENSTGRADE );
        while (rs.next()) {
        	Dienstgrade.addElement(rs.getInt(ID)
        			             , rs.getString(DIG_DIENSTGRAD));
        }
        rs.close();
		return Dienstgrade;
	}
	
	/**
	 * @return
	 * @throws SQLException
	 */
	public HashMap<Integer,Mitglied> getMemberHashMap() throws SQLException {		
		Statement  st = cn.createStatement();
        ResultSet  rs = st.executeQuery( GET_MEMBER_LIST );
        HashMap<Integer,Mitglied> Mitglieder = new HashMap<Integer,Mitglied>();
        Mitglied Mitglied;
        Date geb        = new Date();
        Date aufn       = new Date();
        Ausbildung ausb = new Ausbildung();
        Karriere karr   = new Karriere();
        while (rs.next()) {
			try {
				geb = dfm.parse(rs.getString(MIT_GEBDATE));
			} catch (ParseException e) {
				e.printStackTrace();
			}
        	try {
				aufn = dfm.parse(rs.getString(MIT_AUFNDATE));
			} catch (ParseException e) {
				e.printStackTrace();
			}
        	ausb     = getMemberTrainings(rs.getString(ID));
        	karr     = getMemberKarriere(rs.getString(ID));
        	Mitglied = new Mitglied(rs.getInt(ID),
        			                rs.getString(MIT_NAME),
        			                rs.getString(MIT_VORNAME),
        			                rs.getInt(MIT_DIEGRAD),
        			                aufn,
        			                geb,
        			                ausb,
        			                karr,
        			                rs.getInt(MS_STATUS));
//        	System.out.println("New Member: " + Mitglied.toString());
            Mitglieder.put(rs.getInt(ID),Mitglied);
        }
        rs.close();
        return Mitglieder;
	}
	
	/**
	 * @param Id
	 * @return
	 * @throws SQLException
	 */
	public Ausbildung getMemberTrainings(String Id)  throws SQLException {
		Ausbildung ausb = new Ausbildung();
		Lehrgang lehrgang;
		Date date = new Date();
		ResultSet rs;
		PreparedStatement prep;
        prep = cn.prepareStatement(this.GET_MEMBERS_TRAININGS);
        prep.setString(1, Id);
        rs = prep.executeQuery();
        while (rs.next()) {
        	try {
				date = dfm.parse(rs.getString(MAS_DATE));
			} catch (ParseException e) {
				e.printStackTrace();
			}
        	lehrgang = new Lehrgang(Integer.getInteger(rs.getString(MAS_AUSBID)),date);
        	ausb.addLehrgang(lehrgang);
        }
        prep.close();
        rs.close();
		return ausb;
	}
	
	/**
	 * @param Id
	 * @return
	 * @throws SQLException
	 */
	public Karriere getMemberKarriere(String Id)  throws SQLException {
		Karriere karr = new Karriere();
		Dienstgrad dgrad;
		Date date = new Date();
		ResultSet rs;
		PreparedStatement prep;
		
		
        prep = cn.prepareStatement(this.GET_MEMBERS_KARR);
        prep.setString(1, Id);
        rs = prep.executeQuery();
        while (rs.next()) {
        	try {
				date = dfm.parse(rs.getString(DGD_DienstgradDatum));
			} catch (ParseException e) {
				e.printStackTrace();
			}
        	dgrad = new Dienstgrad(Integer.getInteger(rs.getString(DGD_DienstgradId)),date);
        	karr.addDienstgrad(dgrad);
        }
        prep.close();
        rs.close();
		return karr;
	}
	
	/**
	 * @param id
	 * @throws SQLException
	 */
	public void deleteMember(Integer id) throws SQLException {
		deleteEntrie(0,id);
	}
	
	/**
	 * @param id
	 * @throws SQLException
	 */
	public void deleteMTraining(Integer id) throws SQLException {
		deleteEntrie(1,id);
	}
	
	/**
	 * @param id
	 * @throws SQLException
	 */
	public void deleteRank(Integer id) throws SQLException {
		deleteEntrie(3,id);
	}
	
	/**
	 * @param id
	 * @throws SQLException
	 */
	public void deleteTraining(Integer id) throws SQLException {
		deleteEntrie(2,id);
	}
	
	/**
	 * @param id
	 * @throws SQLException
	 */
	public void deleteMRank(Integer id) throws SQLException {
		deleteEntrie(4,id);
	}
	
	/**
	 * @param id
	 * @throws SQLException
	 */
	public void deleteMemberStatus(Integer id) throws SQLException {
		deleteEntrie(5,id);
	}
	
	/**
	 * @param db_number
	 * @param id
	 * @throws SQLException
	 */
	private void deleteEntrie(int db_number, Integer id) throws SQLException {
		PreparedStatement prep = null;
		switch(db_number) {
			case 0: prep = cn.prepareStatement(DEL_MEMBER);
					break;
			case 1: prep = cn.prepareStatement(DEL_M_TRAINING);
					break;
			case 2: prep = cn.prepareStatement(DEL_AUSBILDUNG);
					break;
			case 3: prep = cn.prepareStatement(DEL_DIENSTGRAD);
					break;
			case 4: prep = cn.prepareStatement(DEL_M_DIENSTGRAD);
					break;
			case 5: prep = cn.prepareStatement(DEL_MEMBERSTATUS);
					break;
			default: return;
		}
		prep.setString(1, id.toString());
        prep.execute();
        prep.close();
	}
	
	/**
	 * @param Entries - Have to be either String or Integer
	 * @throws SQLException
	 */
	public void addMember(Object[] Entries) throws SQLException {
		addEntry(0,Entries);
	}
	
	/**
	 * @param Entries - Have to be either String or Integer
	 * @throws SQLException
	 */
	public void addMTraining(Object[] Entries) throws SQLException {
		addEntry(1,Entries);
	}
	
	/**
	 * @param Entries - Have to be either String or Integer
	 * @throws SQLException
	 */
	public void addRank(Object[] Entries) throws SQLException {
		addEntry(3,Entries);
	}
	
	/**
	 * @param Entries - Have to be either String or Integer
	 * @throws SQLException
	 */
	public void addTraining(Object[] Entries) throws SQLException {
		addEntry(2,Entries);
	}
	
	/**
	 * @param Entries - Have to be either String or Integer
	 * @throws SQLException
	 */
	public void addMRank(Object[] Entries) throws SQLException {
		addEntry(4,Entries);
	}
	
	/**
	 * @param Entries - Have to be either String or Integer
	 * @throws SQLException
	 */
	public void addMemberStatus(Object[] Entries) throws SQLException {
		addEntry(5,Entries);
	}
	
	/**
	 * @param db_number
	 * @param Entries
	 * @throws SQLException
	 */
	private void addEntry(int db_number, Object[] Entries) throws SQLException {
		PreparedStatement prep = null;
		switch(db_number) {
			case 0: prep = cn.prepareStatement(ADD_MLIST_MEMBER);
					break;
			case 1: prep = cn.prepareStatement(ADD_MTLIST_TRAINING);
					break;
			case 2: prep = cn.prepareStatement(ADD_AUSBILDUNG);
					break;
			case 3: prep = cn.prepareStatement(ADD_DIENSTGRAD);
					break;
			case 4: prep = cn.prepareStatement(ADD_MITGL_KARRIERE);
					break;
			case 5: prep = cn.prepareStatement(ADD_MEMBERSTATUS);
					break;
			default: return;
		}
		for(int i=1; i<=Entries.length;i++) {
			if(Entries[i-1].getClass().isInstance(new String()))
				prep.setString(i, (String)Entries[i-1]);
			else
				prep.setInt(i, (Integer)Entries[i-1]);
		}
        prep.execute();
        prep.close();
	}
	
	/**
	 * 
	 */
	public void returnConnection() {
		DBConnectionPool.returnConnection(cn);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#finalize()
	 */
	protected void finalize() {
		DBConnectionPool.returnConnection(cn);
	}

}
