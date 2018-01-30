package com.staples.dashboard.app.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.expression.ExpressionUtils;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;

import java.util.Collection;
import java.util.Iterator;


/**
 * The Class PublicWebExpressionConfigAttribute.
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
public class PublicWebExpressionConfigAttribute extends WebExpressionVoter{
  private SecurityExpressionHandler<FilterInvocation> expressionHandler =
      new DefaultWebSecurityExpressionHandler();
    
  public SpelExpression expr;
  @Autowired
  public SecurityExpressionHandler<FilterInvocation> securityExprHandler;
   
  /**
   * Overriding the vote method from super class.
   * @param Authentication authentication
   * @param FilterInvocation fi
   * @param ollection<ConfigAttribute> attributes
   * @return int
   */
  @Override
  public int vote(Authentication authentication, FilterInvocation fi,
      Collection<ConfigAttribute> attributes) {
        assert authentication != null;
        assert fi != null;
        assert attributes != null;
    SecurityConfig weca = findConfigAttribute(attributes);
         
    Iterator<ConfigAttribute> itr = attributes.iterator();
    while (itr.hasNext()) {
      String authority = itr.next().toString();
      expr = (SpelExpression) securityExprHandler.getExpressionParser().parseExpression(authority);
      expr.getExpressionString();
    }
        
    if (weca == null) {
      return ACCESS_ABSTAIN;
    }

    EvaluationContext ctx = expressionHandler.createEvaluationContext(authentication, fi);
    return ExpressionUtils.evaluateAsBoolean(expr, ctx)
           ? ACCESS_GRANTED : ACCESS_DENIED;
  }

  private SecurityConfig findConfigAttribute(Collection<ConfigAttribute> attributes) {
    for (ConfigAttribute attribute : attributes) {
      if (attribute instanceof SecurityConfig) {
        return (SecurityConfig)attribute;
      }
    }
    return null;
  }

  /**
   * Overriding the supports method from super class.
   * @param ConfigAttribute attribute
   * @return attribute
   */
  @Override
  public boolean supports(ConfigAttribute attribute) {
    return attribute instanceof SecurityConfig;
  }

  /**
   * Overriding the supports method from super class.
   * @param Class<?> clazz
   * @return boolean
   */
  @Override
  public boolean supports(Class<?> clazz) {
    return FilterInvocation.class.isAssignableFrom(clazz);
  }

  /**
   * Overriding the setExpressionHandler method from super class.
   * @param SecurityExpressionHandler<FilterInvocation> expressionHandler
   */
  @Override
  public void setExpressionHandler(SecurityExpressionHandler<FilterInvocation> expressionHandler) {
    this.expressionHandler = expressionHandler;
  }
}
