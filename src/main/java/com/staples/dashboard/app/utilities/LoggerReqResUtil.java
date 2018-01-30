/** 
 * Copyright 2012, Staples, Inc 
 * All Rights Reserved
 * FileName				:	LoggerReqResUtil.java
 * Author				:	Staples
 * Date of Creation		:	07-FEB-2014
 * Description			:	This is generic logger class which will return request response logger
 * Version No			: 	1.0
 * Modification History	:	
 * Date				Version No				Who						Description
 * 
 */

package com.staples.dashboard.app.utilities;

import org.apache.log4j.Appender;
import org.apache.log4j.Logger;

/**
 * The Class LoggerReqResUtil.
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
public class LoggerReqResUtil 
{
	/**
	 * Default constructor.
	 */
	private LoggerReqResUtil() {
		
	}
	
	/** 
	 * This method will return request response logger
	 * @return Logger
	 */
	public static Logger getReqResLogger()
	{
		Logger logTiming = Logger.getLogger("ReqRes");
		
		Logger root = Logger.getRootLogger();
		Appender appender = root.getAppender("ReqResLogFile");
		logTiming.addAppender(appender);
		logTiming.setAdditivity(false);
		
		return logTiming;
	}
}
