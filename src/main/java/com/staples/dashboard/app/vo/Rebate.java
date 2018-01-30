package com.staples.dashboard.app.vo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Rebate {

private Boolean complex;
private String documentUrl;

/**
* 
* @return
* The complex
*/
public Boolean getComplex() {
return complex;
}

/**
* 
* @param complex
* The complex
*/
public void setComplex(Boolean complex) {
this.complex = complex;
}

/**
* 
* @return
* The documentUrl
*/
public String getDocumentUrl() {
return documentUrl;
}

/**
* 
* @param documentUrl
* The documentUrl
*/
public void setDocumentUrl(String documentUrl) {
this.documentUrl = documentUrl;
}

}
