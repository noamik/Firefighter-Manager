package datascheme;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import util.util;


public class IdStringMatcher {
	private HashMap<Integer,String> Elements;

    public IdStringMatcher() {
        this.Elements = new HashMap<Integer,String>();
    }

    public IdStringMatcher(HashMap<Integer,String> Elements) {
		this.Elements = Elements;
	}

    public String getElement(Integer Id) {
        return this.Elements.get(Id);
    }

	public void setElements(HashMap<Integer,String> Elements) {
		this.Elements = Elements;
	}

	public HashMap<Integer,String> getElements() {
		return Elements;
	}

    public void addElement(Integer Id, String Element) {
        this.Elements.put(Id,Element);
    }

    public Integer addElement(String Element) {
        Random generator = new Random();
        int newId = generator.nextInt();
        while(this.Elements.containsKey(newId))
            newId = generator.nextInt();
        this.Elements.put(newId,Element);
        return newId;
    }

    public void removeElement(Integer id) {
         this.Elements.remove(this.Elements.get(id));
    }

    public void removeElement(String Element) {
         this.Elements.remove(Element);
    }

    /** Returns -1 if Element not found */
    public Integer getId(String Element) {
        Iterator<Integer> i = this.Elements.keySet().iterator();
        int counter = 0;
        while(i.hasNext()) {
            counter = (Integer)i.next();
            if(this.Elements.get(counter).equals(Element))
                return counter;
        }
        return -1;
    }

	public Boolean isElement(String lg) {
		return this.Elements.containsValue(lg);
	}

    public Boolean isId(Integer id) {
		return this.Elements.containsValue(id);
	}

    public String toString() {
        String result = null;
        Iterator<Integer> i = this.Elements.keySet().iterator();
        int counter = 0;
        while(i.hasNext()) {
            counter = (Integer)i.next();
            result = result + "Key: " + counter + " Element: " + this.Elements.get(counter) + util.NewLine;
        }
        if(result == null)
            return "No Elements in this IdStringMatcher";
        return result;
    }
}

