package com.staples.dashboard.app.sdc.vo;
import java.util.ArrayList;
import java.util.List;
import com.staples.dashboard.app.vo.UserInfo;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class NephosResponseVO {

private String zipCode;
private String channelId;
private UserInfo userInfo;
private List<Item> item = new ArrayList<Item>();
private Integer responseCode;
private String responseMessage;
private List<ErrorItem>  errorItem=new ArrayList<ErrorItem>();

/**
* 
* @return
* The zipCode
*/
public String getZipCode() {
return zipCode;
}

/**
* 
* @param zipCode
* The zipCode
*/
public void setZipCode(String zipCode) {
this.zipCode = zipCode;
}

/**
* 
* @return
* The channelId
*/
public String getChannelId() {
return channelId;
}

/**
* 
* @param channelId
* The channelId
*/
public void setChannelId(String channelId) {
this.channelId = channelId;
}

/**
* 
* @return
* The userInfo
*/
public UserInfo getUserInfo() {
return userInfo;
}

/**
* 
* @param userInfo
* The userInfo
*/
public void setUserInfo(UserInfo userInfo) {
this.userInfo = userInfo;
}

/**
* 
* @return
* The item
*/
public List<Item> getItem() {
return item;
}

/**
* 
* @param item
* The item
*/
public void setItem(List<Item> item) {
this.item = item;
}

/**
 * @return the responseCode
 */
public Integer getResponseCode() {
	return responseCode;
}

/**
 * @param responseCode the responseCode to set
 */
public void setResponseCode(Integer responseCode) {
	this.responseCode = responseCode;
}

/**
 * @return the responseMessage
 */
public String getResponseMessage() {
	return responseMessage;
}

/**
 * @param responseMessage the responseMessage to set
 */
public void setResponseMessage(String responseMessage) {
	this.responseMessage = responseMessage;
}

public List<ErrorItem> getErrorItem() {
	return errorItem;
}

public void setErrorItem(List<ErrorItem> errorItem) {
	this.errorItem = errorItem;
}


}