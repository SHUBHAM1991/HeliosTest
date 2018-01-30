/** 
 * Copyright 2012, Staples, Inc 
 * All Rights Reserved
 * FileName				:	LoggerTimingUtil.java
 * Author				:	Staples
 * Date of Creation		:	07-FEB-2014
 * Description			:	This is generic logger class which will write logger entries
 * Version No			: 	1.0
 * Modification History	:	
 * Date				Version No				Who						Description
 * 
 */

package com.staples.dashboard.app.utilities;

import org.apache.log4j.Appender;
import org.apache.log4j.Logger;

/**
 * The Class LoggerTimingUtil.
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
public class LoggerTimingUtil 
{
	/**
	 * Default constructor
	 */
	private LoggerTimingUtil(){}
	
	/** 
	 * This method will return timing logger
	 * @return Logger
	 */
	public static Logger getTimingLogger()
	{
		Logger logTiming = Logger.getLogger("Timing");
		
		Logger root = Logger.getRootLogger();
		Appender appender = root.getAppender("TimingLogFile");
		logTiming.addAppender(appender);
		logTiming.setAdditivity(false);
		
		return logTiming;
	}
	
	/**
	 * This will log the processed timing
	 * @param message
	 * @param processStartTime
	 */
	public static void logTiming(String message, Long processStartTime)
	{
		Long processEndTime = System.currentTimeMillis();
		Long diffrence = processEndTime - processStartTime;
		
		LoggerUtil.logInfo(message + " | Difference | "+ diffrence, getTimingLogger());
		
	}
}
