package com.staples.dashboard.app.vo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class PromotionMessage {

private String promoMessageType;
private String name;
private String description;

/**
* 
* @return
* The promoMessageType
*/
public String getPromoMessageType() {
return promoMessageType;
}

/**
* 
* @param promoMessageType
* The promoMessageType
*/
public void setPromoMessageType(String promoMessageType) {
this.promoMessageType = promoMessageType;
}

/**
* 
* @return
* The name
*/
public String getName() {
return name;
}

/**
* 
* @param name
* The name
*/
public void setName(String name) {
this.name = name;
}

/**
* 
* @return
* The description
*/
public String getDescription() {
return description;
}

/**
* 
* @param description
* The description
*/
public void setDescription(String description) {
this.description = description;
}

}