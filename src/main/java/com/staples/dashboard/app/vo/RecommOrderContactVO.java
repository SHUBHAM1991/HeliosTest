package com.staples.dashboard.app.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Class RecommOrderContactVO.
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
public class RecommOrderContactVO implements Comparable<RecommOrderContactVO> {
	
	private String orderContact;
	private String orderDate;
	
	/**
	 * Overriding the compareTo method.
	 * @param RecommOrderContactVO o
	 * @return int
	 */
	@Override
	public int compareTo(RecommOrderContactVO o) {
		// TODO Auto-generated method stub
		Date dt1 = new Date();
	    Date dt2=null;
		try{
		SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");
		 dt1 = formatter.parse(this.orderDate);
		 dt2 = formatter.parse(o.orderDate);
		
		}catch(Exception e){
			
		}
       		
		 return dt1.compareTo(dt2) * (-1);
	}

	/**
	 * @return the orderContact
	 */
	public String getOrderContact() {
		return orderContact;
	}

	/**
	 * @param orderContact the orderContact to set
	 */
	public void setOrderContact(String orderContact) {
		this.orderContact = orderContact;
	}

	/**
	 * @return the orderDate
	 */
	public String getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

}
