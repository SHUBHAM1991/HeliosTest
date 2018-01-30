package com.staples.dashboard.app.thread;

import java.io.File;

import org.apache.log4j.Logger;

import com.staples.dashboard.app.utilities.LoggerUtil;

/**
 * The Class RenderImageThread.
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
public class RenderImageThread extends Thread {

	private static final Logger LOGGER = Logger
			.getLogger(RenderImageThread.class);

	private File file = null;

	/**
	 * Public constructor
	 * 
	 * @param file
	 */
	public RenderImageThread(File file) {
		this.file = file;
	}

	/**
	 * Run method
	 */
	public void run() {

		try {

			if (null != file && file.delete()) {
				LoggerUtil.logDebug(file.getAbsolutePath()
								+ " is deleted", LOGGER);
			} else {
				LoggerUtil.logDebug(file.getAbsolutePath()
								+ " is not deleted", LOGGER);
			}
			
		} catch (Exception ex) {
			LoggerUtil.logError(ex.getMessage(), ex,
					LOGGER);
		}
	}
}
