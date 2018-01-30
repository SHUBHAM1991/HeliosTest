package com.staples.dashboard.app.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.staples.dashboard.app.service.UserRoleService;

/**
 * The Class MyFilterInvocationSecurityMetadataSource.
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
public class MyFilterInvocationSecurityMetadataSource implements
    FilterInvocationSecurityMetadataSource {

  @Autowired
  private UserRoleService userRoleService;

  @Autowired
  private SecurityExpressionHandler<FilterInvocation> securityExprHandler;

/**
 * Method written to get the list of attributes.
 * @param object
 * @return {@link List}
 */
  @SuppressWarnings("unchecked")
  public List<ConfigAttribute> getAttributes(Object object) {
    FilterInvocation fi = (FilterInvocation) object;
    String url = fi.getRequestUrl();
    
    Object principal = SecurityContextHolder.getContext()
        .getAuthentication().getPrincipal();
    Map<String, String> urlDtl = new HashMap<String, String>();

    urlDtl = (Map<String, String>) SecurityContextHolder.getContext()
        .getAuthentication().getDetails();
    
    List<String> links = new ArrayList<String>(urlDtl.keySet());
    
    List<ConfigAttribute> attributes = new ArrayList<ConfigAttribute>();
    
    
    if (principal instanceof org.springframework.security.core.userdetails.User) {
      org.springframework.security.core.userdetails.User user = 
          (org.springframework.security.core.userdetails.User) principal;
      
      if (links.contains(url)) {
    	  
        if (lookup(url, user, urlDtl)) {
        	
          for (GrantedAuthority grantedAuthority : user.getAuthorities()) {
            attributes.add(new SecurityConfig("hasRole('"
                + grantedAuthority.getAuthority() + "')"));
            
          }
        } else {
        	
          throw new AccessDeniedException("Access Denied to " + url);
        }
      } else {
    	  
    	  
        for (GrantedAuthority grantedAuthority : user.getAuthorities()) {
          attributes.add(new SecurityConfig("hasRole('" + grantedAuthority.getAuthority() + "')"));
          
        }
      }
    }
    
    if (attributes.isEmpty()) {
      attributes.add(new SecurityConfig("hasRole('')"));
    }
    return attributes;
  }

/**
 * Method written to check if user has access to the application or not.
 * @param String url
 * @param org.springframework.security.core.userdetails.User user
 * @param Map<String, String> urlDtl
 * @return
 */
  public boolean lookup(String url,org.springframework.security.core.userdetails.User user,
      Map<String, String> urlDtl) {
	  
    boolean access = false;
    String urlRole = urlDtl.get(url);
    
    List<String> roles = new ArrayList<String>(Arrays.asList(urlRole
        .split(",")));
    
    if (url != null) {
      Collection<GrantedAuthority> authorities = user.getAuthorities();
      
      for (GrantedAuthority grantedAuthority : authorities) {
    	  
        for (String requiredAuthority : roles) {
          if (grantedAuthority.getAuthority().equals(requiredAuthority)) {
        	  
            access = true;
            break;
          }
        }
      }
    } else {
      access = true;
    }
    
    return access;
  }

/**
 * @return
 */
  public Collection<ConfigAttribute> getAllConfigAttributes() {
    return null;
  }

/**
 * @return
 */
  public boolean supports(Class<?> clazz) {
    return FilterInvocation.class.isAssignableFrom(clazz);
  }
}