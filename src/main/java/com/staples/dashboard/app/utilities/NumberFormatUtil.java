package com.staples.dashboard.app.utilities;

import java.text.DecimalFormat;

/**
 * The Class NumberFormatUtil.
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
public class NumberFormatUtil {

	/**
	 * Default constructor.
	 */
	private NumberFormatUtil () {}
	
	/** 
	 * This method will return value in currency format #,###,###,##0
	 * @param Float value
	 * @return String strFormatedValue 
	 */
	public static String getDoubleValue(Float value)
	{
		String strFormatedValue = "0.00";
		
		try 
		{
			DecimalFormat df2 = new DecimalFormat("#,###,###,##0.00" );
			strFormatedValue = ""+df2.format(value);
		} 
		catch (Exception e) 
		{
			strFormatedValue = ""+value;
		}
		
		return strFormatedValue;
	}
	
	/** 
	 * This method will return value in currency format #,###,###,##0
	 * @param Double value
	 * @return String strFormatedValue 
	 */
	public static String getFormatedDouble(Double value)
	{
		String strFormatedValue = "0.00";
		
		try 
		{
			DecimalFormat df2 = new DecimalFormat( "#,###,###,##0.00" );
			strFormatedValue = ""+df2.format(value);
		} 
		catch (Exception e) 
		{
			strFormatedValue = ""+value;
		}
		
		return strFormatedValue;
	}
}
