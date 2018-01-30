
package com.staples.dashboard.app.vo;

import java.util.ArrayList;
import java.util.List;


public class OrderContactInfoVO {
	private String orderContact;
	private String recentOrderDate;
	
     private String recentVisitDate;
	
	private String totalSalesCurr;

	private String contactEmail;
	private String contactPhone;
	
	private String iamId;
	
	private boolean isTopSpender=false;
	private boolean isRecentVisitor=false;
	private boolean isRecentOrderer=false;
	private String title=" ";
	private boolean isPrimary;
	private String contactId;
	
	
	public String getOrderContact() {
		return orderContact;
	}
	public void setOrderContact(String orderContact) {
		this.orderContact = orderContact;
	}
	
	public String getRecentOrderDate() {
		return recentOrderDate;
	}
	public void setRecentOrderDate(String recentOrderDate) {
		this.recentOrderDate = recentOrderDate;
	}
	public String getRecentVisitDate() {
		return recentVisitDate;
	}
	public void setRecentVisitDate(String recentVisitDate) {
		this.recentVisitDate = recentVisitDate;
	}
	public String getTotalSalesCurr() {
		return totalSalesCurr;
	}
	public void setTotalSalesCurr(String totalSalesCurr) {
		this.totalSalesCurr = totalSalesCurr;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getIamId() {
		return iamId;
	}
	public void setIamId(String iamId) {
		this.iamId = iamId;
	}
	public boolean isTopSpender() {
		return isTopSpender;
	}
	public void setTopSpender(boolean isTopSpender) {
		this.isTopSpender = isTopSpender;
	}
	public boolean isRecentVisitor() {
		return isRecentVisitor;
	}
	public void setRecentVisitor(boolean isRecentVisitor) {
		this.isRecentVisitor = isRecentVisitor;
	}
	public boolean isRecentOrderer() {
		return isRecentOrderer;
	}
	public void setRecentOrderer(boolean isRecentOrderer) {
		this.isRecentOrderer = isRecentOrderer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isPrimary() {
		return isPrimary;
	}
	public void setPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
	}
	public String getContactId() {
		return contactId;
	}
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	
	
	
	
}

