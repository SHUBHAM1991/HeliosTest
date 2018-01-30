package com.staples.dashboard.app.servlet;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.staples.dashboard.app.utilities.Constants;
import com.staples.dashboard.app.utilities.LoggerUtil;

/**
 * The Class RenderImage.
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
public class RenderImage extends javax.servlet.http.HttpServlet
							implements javax.servlet.Servlet {

	private static final Logger LOGGER = Logger.getLogger(RenderImage.class);

	static final long serialVersionUID = 1L;
	String image_name = Constants.EMPTY;
	String filePath = Constants.EMPTY;
	private static final int BUFSIZE = 100;
	private ServletContext servletContext;

	/**
	 * Default Constructor
	 */
	public RenderImage() {
		super();
	}

	/**
	 * Method to process request and response.
	 * @param HttpServletRequest
	 *            request
	 * @param HttpServletResponse
	 *            response
	 * @throws	ServletException
	 * @throws	IOException
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LoggerUtil.logDebug("Called RenderImage ", LOGGER);
		sendImage(getServletContext(), request, response);
	}

	/**
	 * This method will render image
	 * 
	 * @param ServletContext
	 *            servletContext
	 * @param HttpServletRequest
	 *            request
	 * @param HttpServletResponse
	 *            response
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void sendImage(ServletContext servletContext,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.servletContext = servletContext;
		String reqUrl = request.getRequestURL().toString();
		StringTokenizer tokens = new StringTokenizer(reqUrl, "/");
		int noOfTokens = tokens.countTokens();
		String tokensString[] = new String[noOfTokens];
		int count = 0;

		while (tokens.hasMoreElements()) {
			tokensString[count++] = (String) tokens.nextToken();
		}

		// Get chart setting bean from configuration file
		WebApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(getServletContext());
		Map<String, String> mapChartSetting = (Map<String, String>) context
				.getBean("chartSetting");

		// String folderName = Constants.CHARTFILELOCATION;
		image_name = tokensString[noOfTokens - 1];
		String fullFilePath = mapChartSetting.get(Constants.CHARTFILELOCATION)
				+ image_name;

		doDownload(fullFilePath, request, response);
	}

	/**
	 * This method will read content from file and write into response steam
	 * Additionally it will delete the original file
	 * 
	 * @param String
	 *            filePath
	 * @param HttpServletRequest
	 *            request
	 * @param HttpServletResponse
	 *            response
	 * @throws IOException
	 */
	private void doDownload(String filePath, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		File file = null;
		
		try {

			file = new File(filePath);
			if(file.length() > Constants.ZERO) {
			
				int length = 0;
				ServletOutputStream outputStream = response.getOutputStream();
				ServletContext context = servletContext;
				String mimetype = context.getMimeType(filePath);
				response.setContentType((mimetype != null) ? mimetype
						: "application/octet-stream");
				response.setContentLength((int) file.length());
				response.setHeader("Content-Disposition", "attachment; filename=\""
						+ image_name + "\"");

				byte[] bbuf = new byte[BUFSIZE];
				DataInputStream in = new DataInputStream(new FileInputStream(file));

				while ((in != null) && ((length = in.read(bbuf)) != -1)) {
					outputStream.write(bbuf, 0, length);
				}
				in.close();
				outputStream.flush();
				outputStream.close();
				
                LoggerUtil.logDebug("File Created: " + file.getAbsolutePath(), LOGGER);

				file.delete();
                LoggerUtil.logDebug("File Deleted: " + file.getName(), LOGGER);
			}
			else {
                LoggerUtil.logDebug("File Not Found: " + file.getName(), LOGGER);
			}

		} catch (Exception ex) {
			
			LoggerUtil.logError("Exception occured while reading the images | RenderImage | "+
					filePath, ex, LOGGER);
			
		}

	}
}
	
	/*private void delOldImgs() {
		WebApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(getServletContext());
		Map<String, String> mapChartSetting = (Map<String, String>) context
				.getBean("chartSetting");
		String fullDirPath = mapChartSetting.get(Constants.CHARTFILELOCATION);
		File dir = new File(fullDirPath);
		
		for (File file: dir.listFiles()) {
			
	        if (file.isFile() && file.getName().contains("png")) {
	        	
	        	file.delete();
	        }
	    }
	}*/

