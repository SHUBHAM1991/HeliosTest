/** 
 * Copyright 2012, Staples, Inc 
 * All Rights Reserved
 * FileName				:	LoggerUtil.java
 * Author				:	Staples
 * Date of Creation		:	07-FEB-2014
 * Description			:	This is generic logger class which will write logger entries
 * Version No			: 	1.0
 * Modification History	:	
 * Date				Version No				Who						Description
 * 
 */

package com.staples.dashboard.app.utilities;

import org.apache.log4j.Logger;

/**
 * The Class LoggerUtil.
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
public class LoggerUtil {

	/**
	 * Default constructor
	 */
	private LoggerUtil() {
	}

	/**
	 * This method will log Error level details
	 * 
	 * @param Error
	 *            Message
	 * @param Throwable
	 * @param logger
	 */
	public static void logError(final String message, final Throwable e,
			final Logger logger) {
		if (null != e) {
			logger.error(message, e);
		} else {
			logger.error(message);
		}

	}

	/**
	 * This method will log fatal level details
	 * 
	 * @param Error
	 *            Message
	 * @param Throwable
	 * @param logger
	 */
	public static void logAlarm(final String message, final Throwable e,
			final Logger logger) {
		if (null != e) {
			logger.fatal(message, e);
		} else {
			logger.fatal(message);
		}
	}

	/**
	 * This method will log info level details
	 * 
	 * @param Error
	 *            Message
	 * @param Throwable
	 * @param logger
	 */
	public static void logInfo(final String message, final Throwable e,
			final Logger logger) {
		if (logger.isInfoEnabled()) {
			if (null != e) {
				logger.info(message, e);
			} else {
				logger.info(message);
			}
		}
	}

	/**
	 * This method will log info level details
	 * 
	 * @param Error
	 *            Message
	 * @param Throwable
	 * @param logger
	 */
	public static void logInfo(final String message, final Logger logger) {
		if (logger.isInfoEnabled()) {
			logger.info(message);
		}
	}

	/**
	 * This method will log debug level details
	 * 
	 * @param Error
	 *            Message
	 * @param Throwable
	 * @param logger
	 */
	public static void logDebug(final String message, final Throwable e,
			final Logger logger) {
		if (logger.isDebugEnabled()) {
			if (null != e) {
				logger.debug(message, e);
			} else {
				logger.debug(message);
			}
		}
	}

	/**
	 * This method will log debug level details
	 * 
	 * @param Error
	 *            Message
	 * @param Throwable
	 * @param logger
	 */
	public static void logDebug(final String message, final Logger logger) {
		if (logger.isDebugEnabled()) {
			logger.debug(message);
		}
	}
}
