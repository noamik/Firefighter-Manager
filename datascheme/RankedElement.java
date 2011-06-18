/**
 * 
 */
package datascheme;

/**
 * @author noamik
 *
 */
public class RankedElement {
	private String name = null;
	private Integer pos = null;
	
	/**
	 * @param tname
	 * @param tpos
	 */
	public RankedElement(String tname, int tpos) {
		name = tname;
		pos  = tpos;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the pos
	 */
	public Integer getPos() {
		return pos;
	}
	/**
	 * @param pos the pos to set
	 */
	public void setPos(Integer pos) {
		this.pos = pos;
	}
	
	public String toString() {
		return "Ausbildung: " + name + util.util.NewLine + "Position: " + pos;
	}
	public Object[] getDBObjectArray(Integer id) {
		Object[] dbitem = new Object[3];
		dbitem[0] = id;
		dbitem[1] = name;
		dbitem[2] = pos;
		// TODO Auto-generated method stub
		return dbitem;
	}

}
