package com.staples.dashboard.app.vo;



/**
 * The Class RwdMember.
 *
 * @author KumBi002
 * @version 1.0
 *  Revision history
 *  <p>
 *  ------------------------------------------------------------
 *  </p>
 *  <p>
 * <table>
 * <tr>
 * 	<td>Version</td><td>Date</td><td>Author</td><td>Description</td>
 * </tr>
 * <tr>
 *  <td>1.0</td><td>Dec 4, 2015</td><td>KumBi002</td><td>Initial Draft</td>
 * </tr>
 * </table>
 *  </p>
 *  <p>
 *  ------------------------------------------------------------
 *  </p>
 */
public class RwdMember {

	/**
	 * The Enum rewardTier.
	 *
	 * @author KumBi002
	 * @version 1.0
	 *  Revision history
	 *  <p>
	 *  ------------------------------------------------------------
	 *  </p>
	 *  <p>
	 * <table>
	 * <tr>
	 * 	<td>Version</td><td>Date</td><td>Author</td><td>Description</td>
	 * </tr>
	 * <tr>
	 *  <td>1.0</td><td>Dec 4, 2015</td><td>KumBi002</td><td>Initial Draft</td>
	 * </tr>
	 * </table>
	 *  </p>
	 *  <p>
	 *  ------------------------------------------------------------
	 *  </p>
	 */
	public enum rewardTier {
		BSE, BFS, PMR, G10, PGD, PG5, PPL, P10, P15, PP5, ARW, TRP, TRW, PR5, PR6, PR7, ASC
	};

	private String rwdNum;
	private rewardTier tier;
	private String tierCode;
	private String firstname;
	private String lastname;
	private boolean csrFlag;
	private String emailId;

	/**
	 * Overriding the toString method.
	 * @return String
	 */
	@Override
	public String toString() {
		return "Reward Member [Number=" + this.rwdNum + ", Tier=" + this.tier
				+ ", tierCode=" + this.tierCode + "]";
	}

	/**
	 * @return the rwdNum
	 */
	public String getRwdNum() {
		return rwdNum;
	}

	/**
	 * @param rwdNum the rwdNum to set
	 */
	public void setRwdNum(String rwdNum) {
		this.rwdNum = rwdNum;
	}

	/**
	 * @return the tier
	 */
	public rewardTier getTier() {
		return tier;
	}

	/**
	 * @param tier the tier to set
	 */
	public void setTier(rewardTier tier) {
		this.tier = tier;
	}

	/**
	 * @return the tierCode
	 */
	public String getTierCode() {
		return tierCode;
	}

	/**
	 * @param tierCode the tierCode to set
	 */
	public void setTierCode(String tierCode) {
		this.tierCode = tierCode;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the csrFlag
	 */
	public boolean isCsrFlag() {
		return csrFlag;
	}

	/**
	 * @param csrFlag the csrFlag to set
	 */
	public void setCsrFlag(boolean csrFlag) {
		this.csrFlag = csrFlag;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
}
