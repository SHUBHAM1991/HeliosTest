/**
 * Copyright 2014, Staples, Inc
 * All Rights Reserved
 * Author				:	Staples
 * Project ID			:	STA-0124
 * Project Name			:	Platform B - Rewards Integration
 * FileName				:	StringUtils.java
 * Date of Creation		:	04-Mar-2014
 * Description			:	Utility to handle string value comparisons
 * Version No			:	1.0
 *
 * Modification History	:
 * Date				Version No	Modified By		Description
 * 04-Mar-2014		0.1			NTT DATA		Created the initial version
 * 14-Apr-2014		1.0			NTT DATA		SQA Reviewed version
 */
package com.staples.dashboard.app.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


/**
 * The Class StringUtils.
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
public final class StringUtils
{
	/**
	 * Private constructor
	 */
	private StringUtils()
	{
		// Dummy constructor to prevent users from creating instance of
		// StringUtils class. Care should be taken non-static methods are not
		// added to the class.
	}

	/**
	 * Method to check if the string is blank string or is null.
	 * @param strVal				The source string to be checked
	 * @return boolean value		Based on whether the value is blank or null
	 */
	public static boolean isNullOrBlank(String strVal)
	{
		boolean isNullOrEmptyInd = false;

		if (null == strVal || "".equals(strVal)) {
			isNullOrEmptyInd = true;
		}

		return isNullOrEmptyInd;
	}

	/**
	 * Method to check if the values passed are equal. This method will return
	 * true if both the values are either null or equal
	 * @param val1			String literal 1
	 * @param val2			String literal 2
	 * @return booleanValue	true/false - based on whether the values are equal
	 */
	public static boolean checkEqualsIgnoreNull(String val1, String val2)
	{
		boolean areLiteralsEqual = false;

		if ((null == val1 && null == val2)
				|| (null != val1 && val1.equals(val2)))
		{
			areLiteralsEqual = true;
		}

		return areLiteralsEqual;
	}

	/**
	 * Method to check if the values passed are equal. This method will return
	 * true only if the values are not null and are equal
	 * @param val1			String literal 1
	 * @param val2			String literal 2
	 * @return booleanValue true/false - based on whether the values are equal
	 */
	public static boolean checkEquals(String val1, String val2)
	{
		boolean areLiteralsEqual = false;

		if (null != val1 && val1.equals(val2))
		{
			areLiteralsEqual = true;
		}

		return areLiteralsEqual;
	}
	
	/**
	 * Method to split the string to list.
	 * @param val
	 * @param del
	 * @return
	 */
	public static List<String> splitStringToList(String val,String del){
		if(val !=null & val!=""){
		List<String> returnList = new ArrayList<String>();
		StringTokenizer str = new StringTokenizer(val,del);
		while(str.hasMoreElements()){
			returnList.add(str.nextElement().toString());
		}
		
		if(returnList.isEmpty()){
			return null;
		}else{
			return returnList;
		}
		}else
			return null;
	
	}
}
