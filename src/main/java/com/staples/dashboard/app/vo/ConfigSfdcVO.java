package com.staples.dashboard.app.vo;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class ConfigSfdcVO.
 */
public class ConfigSfdcVO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8109365668209896309L;

	/** The service name. */
	private String serviceName;
	/** The user name. */
	private String userName;
	
	/** The password. */
	private String password;
	
	/** The client id. */
	private String clientId;
	
	/** The client secret. */
	private String clientSecret;
	
	/** The force url. */
	private String forceUrl;
	
	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * Sets the user name.
	 *
	 * @param userName the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Gets the client id.
	 *
	 * @return the client id
	 */
	public String getClientId() {
		return clientId;
	}
	
	/**
	 * Sets the client id.
	 *
	 * @param clientId the new client id
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	/**
	 * Gets the client secret.
	 *
	 * @return the client secret
	 */
	public String getClientSecret() {
		return clientSecret;
	}
	
	/**
	 * Sets the client secret.
	 *
	 * @param clientSecret the new client secret
	 */
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	
	/**
	 * Gets the force url.
	 *
	 * @return the force url
	 */
	public String getForceUrl() {
		return forceUrl;
	}
	
	/**
	 * Sets the force url.
	 *
	 * @param forceUrl the new force url
	 */
	public void setForceUrl(String forceUrl) {
		this.forceUrl = forceUrl;
	}

	/**
	 * Gets the service name.
	 *
	 * @return the service name
	 */
	public String getServiceName() {
		return serviceName;
	}

	/**
	 * Sets the service name.
	 *
	 * @param serviceName the new service name
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	

}
