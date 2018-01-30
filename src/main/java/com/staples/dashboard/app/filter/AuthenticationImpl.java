package com.staples.dashboard.app.filter;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * The Class AuthenticationImpl.
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
public class AuthenticationImpl implements Authentication {


  private static final long serialVersionUID = 1L;

  private String name;
  private Collection<? extends GrantedAuthority> authorities;
  private Object credentials;
  private Map<String, String> details = new HashMap<String, String>();
  private Object principal;
  private boolean authenticated;
  
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the authorities
	 */
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	/**
	 * @param authorities the authorities to set
	 */
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	/**
	 * @return the credentials
	 */
	public Object getCredentials() {
		return credentials;
	}
	/**
	 * @param credentials the credentials to set
	 */
	public void setCredentials(Object credentials) {
		this.credentials = credentials;
	}
	/**
	 * @return the details
	 */
	public Map<String, String> getDetails() {
		return details;
	}
	/**
	 * @param details the details to set
	 */
	@SuppressWarnings("unchecked")
	public void setDetails(Map<String, String> details) {
		this.details = details;
	}
	/**
	 * @return the principal
	 */
	public Object getPrincipal() {
		return principal;
	}
	/**
	 * @param principal the principal to set
	 */
	public void setPrincipal(Object principal) {
		this.principal = principal;
	}
	/**
	 * @return the authenticated
	 */
	public boolean isAuthenticated() {
		return authenticated;
	}
	/**
	 * @param authenticated the authenticated to set
	 */
	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}
}
