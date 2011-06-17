package datascheme;

public class MemberStatus {
	private Integer status;
	
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @param id
	 */
	public MemberStatus(Integer id) {
		status = id;
	}
	
	public String toString() {
		return status.toString();
	}

	
}
