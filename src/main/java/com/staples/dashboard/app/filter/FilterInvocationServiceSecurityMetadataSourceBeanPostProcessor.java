package com.staples.dashboard.app.filter;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

/**
 * The Class FilterInvocationServiceSecurityMetadataSourceBeanPostProcessor.
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
public class FilterInvocationServiceSecurityMetadataSourceBeanPostProcessor
     implements BeanPostProcessor{

  private FilterInvocationSecurityMetadataSource metadataSource;

  /**
   * 
   * @param metadataSource the metsdataSource to set
   */
  public void setMetadataSource(FilterInvocationSecurityMetadataSource metadataSource) {
    this.metadataSource = metadataSource;
  }

  /**
   *@return 
   */
  public Object postProcessBeforeInitialization(Object bean, String beanName) { 
    if (bean instanceof FilterInvocationSecurityMetadataSource) { 
      return metadataSource; 
    }
    return bean; 
  } 
  
  /**
   * @return the bean
   */
  public Object postProcessAfterInitialization(Object bean, String beanName) { 
    return bean; 
  } 
}
