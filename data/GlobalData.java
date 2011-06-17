package data;

import gui.MainApplicationWindow;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;

import datascheme.IdStringMatcher;
import datascheme.Mitglied;


/**
 * Created by noamik
 */

public class GlobalData {
    /* Global Data needs to be a singleton, that's why it's Constructor is private
    * for more in-depth reasoning: http://www.theserverside.de/singleton-pattern-in-java/ */
    private static GlobalData instance = new GlobalData();

    private IdStringMatcher Lehrgänge = new IdStringMatcher();
    private IdStringMatcher Dienstgrade = new IdStringMatcher();
    private IdStringMatcher MemberStatus = new IdStringMatcher();
    private HashMap<Integer,Mitglied> Mitglieder = new HashMap<Integer,Mitglied>();
    
	/**
	 * 
	 */
	private GlobalData() {
		sql.DBQuerie dbq 	= new sql.DBQuerie();
		try {
			Dienstgrade 		= dbq.getDienstgrade();
			Lehrgänge 			= dbq.getTrainings();
			MemberStatus 		= dbq.getMemberStatusHashMap();
			Mitglieder 			= dbq.getMemberHashMap();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbq.returnConnection();
//		System.out.println("ToString: " +this.toString());
	}
	
	

    /** 
     * returns the global data instance,
     * global data is a singleton
     */
    public static GlobalData getInstance() {
        return instance;
    }

    /**
     * @return
     */
    public IdStringMatcher getDienstgrade() {
        return getInstance().Dienstgrade;
    }

    /**
     * @return
     */
    public IdStringMatcher getLehrgänge() {
        return getInstance().Lehrgänge;
    }
    
    /**
     * @param lehrgänge
     */
    public void setLehrgänge(IdStringMatcher lehrgänge) {
		this.Lehrgänge = lehrgänge;
	}

    /**
     * @param dienstgrade
     */
    public void setDienstgrade(IdStringMatcher dienstgrade) {
    	GlobalData.getInstance().Dienstgrade = dienstgrade;
	}

	/**
	 * @param mitglieder the mitglieder to set
	 */
	public void setMitglieder(HashMap<Integer,Mitglied> mitglieder) {
		this.Mitglieder = mitglieder;
	}

	/**
	 * @return the mitglieder
	 */
	public HashMap<Integer,Mitglied> getMitglieder() {
		return getInstance().Mitglieder;
	}
	
	public void addMember(Mitglied mitglied) {
		this.Mitglieder.put(mitglied.getId(),mitglied);
		sql.DBQuerie dbq 	= new sql.DBQuerie();
		try {
			dbq.addMember(mitglied.getDBObjectArray());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbq.returnConnection();
		MainApplicationWindow.getMainWindow().getMemberTable().updateMemberTableData(this.Mitglieder);
		System.out.println("Added member!");
	}
	
	public void removeMember(Integer Id) {
		this.Mitglieder.remove(Id);
		sql.DBQuerie dbq 	= new sql.DBQuerie();
		try {
			dbq.deleteMember(Id);
			dbq.deleteMTraining(Id);
			dbq.deleteMRank(Id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbq.returnConnection();
		MainApplicationWindow.getMainWindow().getMemberTable().updateMemberTableData(this.Mitglieder);
		System.out.println("Removed member!");
	}

	/**
	 * @param memberStatus the memberStatus to set
	 */
	public void setMemberStatus(IdStringMatcher memberStatus) {
		getInstance().MemberStatus = memberStatus;
	}

	/**
	 * @return the memberStatus
	 */
	public IdStringMatcher getMemberStatus() {
		return this.MemberStatus;
	}
	
	public String toString() {
		String out = "D-L-M:" + this.Dienstgrade.toString() + this.Lehrgänge.toString() + this.MemberStatus.toString();
		out += "Mitglieder: " + this.Mitglieder.size();
		return out;
	}
	
	public Integer getMaxMemberId() {
		Iterator<Integer> it = this.getMitglieder().keySet().iterator();
		Integer i = 0;
		Integer t = 0;
		while(it.hasNext()) {
			t = it.next();
			if(i<t)
				i = t;
		}
		return i;
	}

}
