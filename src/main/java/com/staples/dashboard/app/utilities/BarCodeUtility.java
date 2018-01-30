/**
 * Copyright 2014, Staples, Inc
 * All Rights Reserved
 * Author				:	Staples
 * Project ID			:	STA-0124
 * Project Name			:	Platform B - Rewards Integration
 * FileName				:	BarCodeUtility.java
 * Date of Creation		:	07-FEB-2014
 * Description			:	This is utility class to handle bar code generation.
 * Version No			:	1.0
 *
 * Modification History	:
 * Date			Version No	Modified By		Description
 * 07-Feb-2014	0.1			NTT DATA		Created the file to handle
 * 											barcode generation.
 * 14-Apr-2014	1.0			NTT DATA		SQA Reviewed version
 */
package com.staples.dashboard.app.utilities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

import com.staples.dashboard.app.utilities.exception.ServiceException;

/**
 * The Class BarCodeUtility.
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
public class BarCodeUtility
{
	private static final Logger LOGGER = Logger.getLogger(BarCodeUtility.class);
	
	private static final String BARCODE_FILE_LOCATION = "chartLocation";
	private static final String BARCODE_FILE_FORMAT = "barCodeFormat";
	private static final String BARCODE_FILE_EXNT = "barCodeFileExtn";

	private static final String BARCODE_FORMAT_CODE128 = "Code128";
	private static final String BARCODE_FORMAT_CODE29 = "Code29";

	/**
	 * Method to generate barcode image and save it to specified path
	 *
	 * @param strToGenBarcode		String value for which the barcode is to be
	 * 								generated
	 * @param mapBarProps			Properties for creating the barcode image
	 * @return barCode				Barcode attribute key to be used to retrieve
	 * 								the image
	 * @throws ServiceException		Exception in case of undesired behavior
	 */
	public String generateBarCode(String strToGenBarcode,
			Map<String, String> mapBarProps) throws ServiceException
	{
		final String methodName = ".generateBarCode()";

		final String logClassMethdName = "BarCodeUtility | generateBarCode | ";

		final int dpi = 160;
		final float floatVal = 2.8f;
		final int systemError = 6001;
		String barCodeGenFormat = "Code128";

		File outputFile = null;
		Code128Bean bean128 = null;
		Code39Bean bean39 = null;

		FileOutputStream out = null;
		BitmapCanvasProvider canvas = null;
		String strFileLocation = null;
		String strFileExtn = ".png";
		StringBuffer sbfFileName = new StringBuffer();
		StringBuffer sbfPattern = new StringBuffer();
		Long longStartTime = null;

		LoggerUtil.logDebug(methodName + " | Entry", LOGGER);

		longStartTime = System.currentTimeMillis();
		LoggerTimingUtil.logTiming(
				"BarCodeUtility | generateBarCode | Start |"
				+ strToGenBarcode, longStartTime);

		if (null != mapBarProps.get(BARCODE_FILE_LOCATION))
		{
			strFileLocation = mapBarProps.get(BARCODE_FILE_LOCATION);
		}

		if (null != mapBarProps.get(BARCODE_FILE_EXNT))
		{
			strFileExtn = mapBarProps.get(BARCODE_FILE_EXNT);
		}

		if (null != mapBarProps.get(BARCODE_FILE_FORMAT))
		{
			barCodeGenFormat = mapBarProps.get(BARCODE_FILE_FORMAT);
		}

		sbfFileName.append(barCodeGenFormat).append("_").
				append("BC").append(strToGenBarcode).append(
				strFileExtn);

		if (BARCODE_FORMAT_CODE128
				.equals(barCodeGenFormat))
		{
			bean128 = new Code128Bean();
			bean128.setModuleWidth(UnitConv.in2mm(floatVal / dpi));
			bean128.doQuietZone(false);

			try
			{
				outputFile = new File(strFileLocation+sbfFileName.toString());

				out = new FileOutputStream(outputFile);

				for (int i = 0, l = strToGenBarcode.length(); i < l; i++)
				{
					sbfPattern.append("#");
				}
				bean128.setPattern(sbfPattern.toString());
				canvas = new BitmapCanvasProvider(out, "image/x-png", dpi,
						BufferedImage.TYPE_BYTE_BINARY, false, 0);
				bean128.generateBarcode(canvas, strToGenBarcode);
				canvas.finish();
				out.flush();
			}
			catch (FileNotFoundException fnfExp)
			{
				LoggerUtil.logError(logClassMethdName
						+ strToGenBarcode, fnfExp, LOGGER);
				throw new ServiceException(systemError, fnfExp);
			}
			catch (IOException ioExp)
			{
				LoggerUtil.logError(logClassMethdName
						+ strToGenBarcode, ioExp, LOGGER);
				throw new ServiceException(systemError, ioExp);
			}
			finally
			{
				if (null != out)
				{
					try
					{
						out.close();
					}
					catch (IOException ioExp)
					{
						LoggerUtil.logError(logClassMethdName
								+ "Exception when trying to close resource",
								ioExp, LOGGER);
					}
				}
			}
		}
		else if (BARCODE_FORMAT_CODE29.equals(barCodeGenFormat))
		{
			bean39 = new Code39Bean();
			bean39.setModuleWidth(UnitConv.in2mm(floatVal / dpi));
			bean39.doQuietZone(false);

			try
			{
				outputFile = new File(strFileLocation+sbfFileName.toString());
				out = new FileOutputStream(outputFile);

				canvas = new BitmapCanvasProvider(out, "image/x-png", dpi,
						BufferedImage.TYPE_BYTE_BINARY, false, 0);
				bean39.generateBarcode(canvas, strToGenBarcode);
				canvas.finish();
				out.flush();
			}
			catch (FileNotFoundException fnfExp)
			{
				LoggerUtil.logError(logClassMethdName
						+ strToGenBarcode, fnfExp, LOGGER);
				throw new ServiceException(systemError, fnfExp);
			}
			catch (IOException ioExp)
			{
				LoggerUtil.logError(logClassMethdName
						+ strToGenBarcode, ioExp, LOGGER);
				throw new ServiceException(systemError, ioExp);
			}
			finally
			{
				if (null != out)
				{
					try
					{
						out.close();
					}
					catch (IOException ioExp)
					{
						LoggerUtil.logError(logClassMethdName
								+ "Exception when trying to close resource",
								ioExp, LOGGER);
					}
				}
			}
		}
		else
		{
			LoggerUtil.logError(logClassMethdName
					+ strToGenBarcode, new ServiceException(systemError,
					"Invalid BarCode generation Code format"), LOGGER);
			throw new ServiceException(systemError,
					"Invalid BarCode generation Code format");
		}

		longStartTime = System.currentTimeMillis();
		LoggerTimingUtil.logTiming(
				"BarCodeUtility | generateBarCode | End |"
				+ strToGenBarcode, longStartTime);

		LoggerUtil.logDebug(methodName + " | Exit", LOGGER);
		return sbfFileName.toString();
	}

}
