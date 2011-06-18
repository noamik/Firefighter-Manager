package datascheme;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import data.GlobalData;

import util.util;


public class Karriere {
private HashMap<Integer, Date> Dienstgrade = null;
	
	public Karriere() {
		setDienstgrade(new HashMap<Integer, Date>());
	}
	
	public Karriere(HashMap<Integer, Date> Dienstgrade) {
		setDienstgrade(Dienstgrade);
	}

	public void setDienstgrade(HashMap<Integer, Date> dienstgrade) {
		Dienstgrade = dienstgrade;
	}

    public void addDienstgrad(Dienstgrad dienstgrad) {
        this.Dienstgrade.put(dienstgrad.getId(),dienstgrad.getDatum());
    }
	
	public HashMap<Integer, Date> getDienstgrade() {
		return Dienstgrade;
	}

    public String toString() {
        String result = null;
        Iterator<Integer> i = this.Dienstgrade.keySet().iterator();
        int counter = 0;
        while(i.hasNext()) {
            counter = (Integer)i.next();
            result = result + "Dienstgrad: " + GlobalData.getInstance().getDienstgrade().getElementString(counter)
                     + " befördert am: " + util.DateToString(this.Dienstgrade.get(counter))
                     + util.NewLine;
        }
        if(result == null)
            return "Kein Dienstgrad";
        return result;
    }

}
