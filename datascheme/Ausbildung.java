package datascheme;

import java.util.*;

import data.GlobalData;

import util.util;


public class Ausbildung {
	private HashMap<Integer, Date> Lehrgänge = null;
	
	public Ausbildung() {
		setLehrgänge(new HashMap<Integer, Date>());
	}
	
	public Ausbildung(HashMap<Integer, Date> Lehrgänge) {
		setLehrgänge(Lehrgänge);
	}

	public void setLehrgänge(HashMap<Integer, Date> lehrgänge) {
		Lehrgänge = lehrgänge;
	}

    public void addLehrgang(Lehrgang lehrgang) {
        this.Lehrgänge.put(lehrgang.getId(),lehrgang.getDatum());
    }
	
	public HashMap<Integer, Date> getLehrgänge() {
		return Lehrgänge;
	}

    public String toString() {
        String result = null;
        if(Lehrgänge == null)
        	return "Keine Lehrgänge absolviert";
        Iterator<Integer> i = this.Lehrgänge.keySet().iterator();
        int counter = 0;
        while(i.hasNext()) {
        	Object o = i.next();
        	if(o == null)
        		break;
            counter = (Integer)o;
            result = result + "Lehrgang: " + GlobalData.getInstance().getLehrgänge().getElementString(counter)
                     + " absolviert: " + util.DateToString(this.Lehrgänge.get(counter))
                     + util.NewLine;
        }
        if(result == null)
            return "Keine Lehrgänge absolviert";
        return result;
    }

}
