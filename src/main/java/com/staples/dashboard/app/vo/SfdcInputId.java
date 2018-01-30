package com.staples.dashboard.app.vo;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class SfdcInputId.
 */
public class SfdcInputId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -134204297518253424L;

	/** The customer num. */
	private String customerNum;
	
	/** The cust id. */
	private String custId;
	
	/** The contact id. */
	private String contactId;
	
	/** The account mgr id. */
	private String accountMgrId;
	
	/**
	 * Gets the customer num.
	 *
	 * @return the customer num
	 */
	public String getCustomerNum() {
		return customerNum;
	}
	
	/**
	 * Sets the customer num.
	 *
	 * @param customerNum the new customer num
	 */
	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}
	
	/**
	 * Gets the cust id.
	 *
	 * @return the cust id
	 */
	public String getCustId() {
		return custId;
	}
	
	/**
	 * Sets the cust id.
	 *
	 * @param custId the new cust id
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}
	
	/**
	 * Gets the contact id.
	 *
	 * @return the contact id
	 */
	public String getContactId() {
		return contactId;
	}
	
	/**
	 * Sets the contact id.
	 *
	 * @param contactId the new contact id
	 */
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	
	/**
	 * Gets the account mgr id.
	 *
	 * @return the account mgr id
	 */
	public String getAccountMgrId() {
		return accountMgrId;
	}
	
	/**
	 * Sets the account mgr id.
	 *
	 * @param accountMgrId the new account mgr id
	 */
	public void setAccountMgrId(String accountMgrId) {
		this.accountMgrId = accountMgrId;
	}
	

}
