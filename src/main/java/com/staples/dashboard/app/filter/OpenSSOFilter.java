	package com.staples.dashboard.app.filter;


import com.staples.dashboard.app.service.UserAuthorizationService;
import com.staples.dashboard.app.service.UserRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * The Class OpenSSOFilter.
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
public class OpenSSOFilter  extends RequestHeaderAuthenticationFilter {

  @Autowired
  private UserAuthorizationService userService;

  @Autowired
  private UserRoleService userRoleSerivce;

  /**
   * Overriding the doFilter metgod from super class.
   * @param ServletRequest request
   * @param ServletResponse response
   * @param FilterChain chain
   */ 
  @SuppressWarnings("unchecked")
  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain chain) throws IOException, ServletException, NullPointerException {

    HttpServletRequest htrequest = (HttpServletRequest) request;
    
    if (SecurityContextHolder.getContext().getAuthentication() == null) {
      String role = "";
      String userName = 
    		  
          ((String) super.getPreAuthenticatedPrincipal(((HttpServletRequest)request)));
      
      role = userService.getRole(userName);
      
      Collection<SimpleGrantedAuthority> auth = new ArrayList<SimpleGrantedAuthority>();
      if (role != null) {
    	  
        auth.add(new SimpleGrantedAuthority(role));
      } else {
    	  
        auth.add(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
      }
      UserDetails userDetails = null;
      if (userName != null) {
    	  
        userDetails =  new User(userName, "", true, true, true, true, auth );
      }

      Map<String, String> additionalHeaders;
      additionalHeaders = new HashMap<String, String>();
      
      Enumeration<String> allHeaders = htrequest.getHeaderNames();
      while (allHeaders.hasMoreElements()) {
    	  
        String header = allHeaders.nextElement();
        if (header.toUpperCase().startsWith("SM_")) {
          additionalHeaders.put(header.toUpperCase(), htrequest.getHeader(header));
        }
      }
      
      Map<String, String> urls = new HashMap<String, String>();
      urls = userRoleSerivce.getScreenUrls();
      
      AuthenticationImpl authentication = new AuthenticationImpl();
      authentication.setAuthenticated(true);
      authentication.setAuthorities(auth);
      authentication.setPrincipal(userDetails);
      authentication.setDetails(urls);
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    super.doFilter(request, response, chain);
  }

  /**
   * Default constructor
   */
  public OpenSSOFilter() {
    super();        
  }

  /**
   * @param String principalRequestHeader
   */
  @Override
  public void setPrincipalRequestHeader(String principalRequestHeader) {
    super.setPrincipalRequestHeader(principalRequestHeader);
  }
  
}