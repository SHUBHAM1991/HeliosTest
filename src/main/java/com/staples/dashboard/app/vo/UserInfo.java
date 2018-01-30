package com.staples.dashboard.app.vo;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class UserInfo {

private Object userName;
private String rewardsNumber;
private Object directCustomerNumber;
private Object masterAccountNumber;
private Object billTo;
private Object shipTo;

private List<UserFlagsNameValuePair> userFlags = new ArrayList<UserFlagsNameValuePair>();

/**
* 
* @return
* The userName
*/
public Object getUserName() {
return userName;
}

/**
* 
* @param userName
* The userName
*/
public void setUserName(Object userName) {
this.userName = userName;
}

/**
* 
* @return
* The rewardsNumber
*/
public String getRewardsNumber() {
return rewardsNumber;
}

/**
* 
* @param rewardsNumber
* The rewardsNumber
*/
public void setRewardsNumber(String rewardsNumber) {
this.rewardsNumber = rewardsNumber;
}

/**
* 
* @return
* The directCustomerNumber
*/
public Object getDirectCustomerNumber() {
return directCustomerNumber;
}

/**
* 
* @param directCustomerNumber
* The directCustomerNumber
*/
public void setDirectCustomerNumber(Object directCustomerNumber) {
this.directCustomerNumber = directCustomerNumber;
}

/**
* 
* @return
* The masterAccountNumber
*/
public Object getMasterAccountNumber() {
return masterAccountNumber;
}

/**
* 
* @param masterAccountNumber
* The masterAccountNumber
*/
public void setMasterAccountNumber(Object masterAccountNumber) {
this.masterAccountNumber = masterAccountNumber;
}

/**
* 
* @return
* The billTo
*/
public Object getBillTo() {
return billTo;
}

/**
* 
* @param billTo
* The billTo
*/
public void setBillTo(Object billTo) {
this.billTo = billTo;
}

/**
* 
* @return
* The shipTo
*/
public Object getShipTo() {
return shipTo;
}

/**
* 
* @param shipTo
* The shipTo
*/
public void setShipTo(Object shipTo) {
this.shipTo = shipTo;
}

public List<UserFlagsNameValuePair> getUserFlags() {
	return userFlags;
}

public void setUserFlags(List<UserFlagsNameValuePair> userFlags) {
	this.userFlags = userFlags;
}

}
