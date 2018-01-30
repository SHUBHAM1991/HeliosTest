package com.staples.dashboard.app.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedGrantedAuthoritiesUserDetailsService;

import java.util.Collection;

/**
 * The Class OpenSSOUserDetailsService.
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
public class OpenSSOUserDetailsService extends
		PreAuthenticatedGrantedAuthoritiesUserDetailsService implements
		UserDetailsService {

	/**
	 * This method is used to load user by user name. 
	 * @param String userName
	 * @return UserDetails userDetails
	 */
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		UserDetails userDetails = new User(
				userName, 
				"",
				true,
				true,
				true,
				true,
				null );

		return userDetails;
	}

	/**
	 * This method is used to create user details
	 * @param Authentication token
	 * @param Collection<? extends GrantedAuthority> authorities
	 * @return UserDeatils userDetails
	 */
	protected UserDetails createUserDetails(Authentication token,
			Collection<? extends GrantedAuthority> authorities) {
		return createUserDetails(token, authorities);
	}
}