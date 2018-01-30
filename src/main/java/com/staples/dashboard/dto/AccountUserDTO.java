package com.staples.dashboard.dto;

public class AccountUserDTO {
	private String number;
	private String fullname;
	private String designation;
	private String repRoleCd;
	private String level;
	private String companyName;
	private String customerNo;
	private String assignGrp;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
	    return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
	    this.number = number;
	}
	/**
	 * @return the fullname
	 */
	public String getFullname() {
	    return fullname;
	}
	/**
	 * @param fullname the fullname to set
	 */
	public void setFullname(String fullname) {
	    this.fullname = fullname;
	}
	/**
	 * @return the designation
	 */
	public String getDesignation() {
	    return designation;
	}
	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
	    this.designation = designation;
	}
	/**
	 * @return the repRoleCd
	 */
	public String getRepRoleCd() {
	    return repRoleCd;
	}
	/**
	 * @param repRoleCd the repRoleCd to set
	 */
	public void setRepRoleCd(String repRoleCd) {
	    this.repRoleCd = repRoleCd;
	}
	/**
	 * @return the level
	 */
	public String getLevel() {
	    return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
	    this.level = level;
	}

	/**
	 * @return the assignGrp
	 */
	public String getAssignGrp() {
		return assignGrp;
	}

	/**
	 * @param assignGrp the assignGrp to set
	 */
	public void setAssignGrp(String assignGrp) {
		this.assignGrp = assignGrp;
	}

	
}