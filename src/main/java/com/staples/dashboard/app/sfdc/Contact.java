
package com.staples.dashboard.app.sfdc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Contact {

	/** The account id. */
	@JsonProperty(value = "AccountId")
	private String accountId;
	
	@JsonProperty(value = "FirstName")
	private String firstName;
	
	@JsonProperty(value = "LastName")
	private String lastName;
	
	@JsonProperty(value = "Title")
	private String title;
	
	@JsonProperty(value = "Primary_Contact__c")
	private boolean isPrimaryContact;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isPrimaryContact() {
		return isPrimaryContact;
	}

	public void setPrimaryContact(boolean isPrimaryContact) {
		this.isPrimaryContact = isPrimaryContact;
	}
	
	
}
