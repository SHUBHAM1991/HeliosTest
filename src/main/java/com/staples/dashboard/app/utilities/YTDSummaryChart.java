package com.staples.dashboard.app.utilities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPosition;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.CategoryLabelWidthType;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PieLabelLinkStyle;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.text.TextBlockAnchor;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.TextAnchor;

import com.staples.dashboard.app.utilities.exception.ServiceException;

/**
 * The Class YTDSummaryChart.
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
public class YTDSummaryChart {

	private static final Logger LOGGER = Logger
			.getLogger(YTDSummaryChart.class);

	/**
	 * Method to generate 2D Pie Chart using the data set values
	 * 
	 * @param dataset
	 *            PieDataset object - containing the values for pie chart
	 * @param strChartTitle
	 *            String object - Title of the chart
	 * @param strRewardNumber
	 *            String object - Reward member for whom the Pie Chart is to be
	 *            generated
	 * @return filePath File path of the newly created file
	 * @throws ServiceException 
	 */
	@SuppressWarnings("deprecation")
	public void create2DPieChart(PieDataset dataset, String strChartTitle,
								String strRewardNumber, String fileLocation, 
								String fileName, String fileFormat)
								throws ServiceException {
		

		StringBuffer sbf = null;
		String labelFont = "Arial";

		ChartRenderingInfo info = null;
		JFreeChart chart = null;
		PiePlot piePlot = null;

		chart = ChartFactory.createPieChart(strChartTitle, dataset, true, false,
											false);

		chart.setBackgroundPaint(Color.white);
		chart.getTitle().setPaint(Color.black);
		chart.getTitle().setFont(new Font(labelFont, Font.BOLD, 12));
		chart.getLegend().setVisible(true);

		piePlot = (PiePlot) chart.getPlot();

		piePlot.setBackgroundPaint(Color.white);
		piePlot.setCircular(true);
		piePlot.setOutlinePaint(null);
		piePlot.setOutlineStroke(null);

		// Specify the colors here
		piePlot.setSectionPaint(0, new Color(247, 86, 5));
		piePlot.setSectionPaint(1, new Color(230, 125, 117));
		piePlot.setSectionPaint(2, new Color(5, 187, 247));
		piePlot.setSectionPaint(3, new Color(247, 211, 5));
		piePlot.setSectionPaint(4, new Color(2, 92, 59));
		piePlot.setSectionPaint(5, new Color(255, 141, 128));
		piePlot.setSectionPaint(6, new Color(133, 1, 1));
		piePlot.setSectionPaint(7, new Color(4, 58 , 105));
		piePlot.setSectionPaint(8, new Color(163, 163, 5));
		piePlot.setSectionPaint(9, new Color(102, 3, 71));

		piePlot.setLabelFont(new Font(labelFont, Font.PLAIN, 9));

		PieSectionLabelGenerator generator1 = new StandardPieSectionLabelGenerator(
	            "{0} {2}", new DecimalFormat("0"), new DecimalFormat("0.00%"));
		piePlot.setLabelGenerator(generator1);

		piePlot.setLabelPaint(Color.BLACK);
		piePlot.setLabelBackgroundPaint(Color.WHITE);
		piePlot.setLabelOutlinePaint(Color.WHITE);
		piePlot.setLabelShadowPaint(Color.WHITE);
		piePlot.setLabelLinkStyle(PieLabelLinkStyle.STANDARD);

		chart.removeLegend();

		try {
			/**
			 * This utility saves the JFreeChart as a JPEG First Parameter:
			 * FileName Second Parameter: Chart To Save Third Parameter: Height
			 * Of Picture Fourth Parameter: Width Of Picture
			 */
			info = new ChartRenderingInfo(new StandardEntityCollection());

			sbf = new StringBuffer();
			sbf.append(fileLocation).append(fileName).append(strRewardNumber)
					.append(fileFormat);
			ChartUtilities.saveChartAsPNG(new File(sbf.toString()), chart, 550,
					250, info);

		} catch (IOException exp) {
			LoggerUtil.logError("YTDSummaryChart | create2DPieChart | "
							+ strRewardNumber, exp, LOGGER);
			throw new ServiceException(6001, exp);
		}

	}
	
	/** 
	 * This method will generate Data set for PieDataset
	 * @param Map<String, Double> mapData
	 * @return DefaultPieDataset pieDataset
	 */
	public PieDataset createDataSetForDouble(Map<String, Double> mapData)
	{
		DefaultPieDataset pieDataset = new DefaultPieDataset();

		if (null != mapData)
		{
			for(String key : mapData.keySet()){
				pieDataset.setValue(key, mapData.get(key));
			}
		}
		
		return pieDataset;
	}
	
	/**
	 * Method to generate 2D Bar Graph using the data set values
	 * 
	 * @param dataset			CategoryDataset object - containing the values
	 * 							for bar graph
	 * @param strChartTitle		String object - Title of the chart

	 * @param strRewardNumber			String object - Reward member for whom the 
	 * 							Bar graph is to be generated
	 * @return filePath			File path of the newly created file
	 * @throws ServiceException 
	 */
	public void create2DBarGraph(CategoryDataset dataset, String strChartTitle,
	        				String strRewardNumber, String fileLocation,
	        				String fileName, String fileFormat) 
	        						throws ServiceException
	{
		StringBuffer sbf = null;
		String labelFont = "Arial";
		JFreeChart chart = null;
		CategoryPlot plot = null;

		CategoryAxis axis = null;
		CategoryLabelPositions catLblPos = null;
		CategoryLabelPosition left = null;
		BarRenderer rend = null;
		ValueAxis vaxis = null;


		chart = ChartFactory.createBarChart(strChartTitle, "Months",
		        "", dataset, PlotOrientation.VERTICAL, false, true,
		        false);

		chart.setBackgroundPaint(Color.white);
		chart.getTitle().setPaint(Color.black);
		chart.getTitle().setFont(new Font(labelFont, Font.BOLD, 12));

		chart.setBorderVisible(false);
		chart.setBorderPaint(Color.WHITE);

		plot = chart.getCategoryPlot();
		plot.setNoDataMessage("No data available");
		plot.setForegroundAlpha(1.0f);
		plot.setBackgroundPaint(Color.white);
		plot.setRangeGridlinePaint(Color.gray);

		axis = plot.getDomainAxis();
		catLblPos = axis.getCategoryLabelPositions();
		left = new CategoryLabelPosition(RectangleAnchor.LEFT,
		        TextBlockAnchor.CENTER_LEFT, TextAnchor.CENTER_LEFT, 0.0,
		        CategoryLabelWidthType.RANGE, 2.0f);

		axis.setLabelFont(new Font(labelFont, Font.PLAIN, 9));
		axis.setCategoryLabelPositions(CategoryLabelPositions
		        .replaceLeftPosition(catLblPos, left));
		axis.setMaximumCategoryLabelWidthRatio(0.75f);
		axis.setTickLabelFont(new Font(labelFont, Font.PLAIN, 9));

		vaxis = plot.getRangeAxis();
		vaxis.setLabelFont(new Font(labelFont, Font.PLAIN, 9));
		vaxis.setTickLabelFont(new Font(labelFont, Font.PLAIN, 9));
		plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);

		plot.setRenderer(new My2DSBRenderer());

		rend = (BarRenderer) plot.getRenderer();
		
		rend.setSeriesPaint(0, new Color(247, 86, 5));
		rend.setSeriesPaint(1, new Color(230, 125, 117));
		rend.setSeriesPaint(2, new Color(5, 187, 247));
		rend.setSeriesPaint(3, new Color(247, 211, 5));
		rend.setSeriesPaint(4, new Color(2, 92, 59));
		rend.setSeriesPaint(5, new Color(255, 141, 128));
		rend.setSeriesPaint(6, new Color(133, 1, 1));
		rend.setSeriesPaint(7, new Color(4, 58 , 105));
		rend.setSeriesPaint(8, new Color(163, 163, 5));
		rend.setSeriesPaint(9, new Color(102, 3, 71));

		rend.setMaximumBarWidth(0.1);
		rend.setBaseLegendTextFont(new Font(labelFont, Font.PLAIN, 9));

		// Inner label
		DecimalFormat decimalformat1 = new DecimalFormat("#,###,###,##0.00");
		rend.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator(
		        "${2}", decimalformat1));
		rend.setBaseItemLabelsVisible(true);

		rend.setBaseItemLabelFont(new Font(labelFont, Font.PLAIN, 9));

		rend.setBasePositiveItemLabelPosition(new ItemLabelPosition(
		        ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));
		rend.setBaseNegativeItemLabelPosition(new ItemLabelPosition(
		        ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));
		
		rend.setPositiveItemLabelPositionFallback(new ItemLabelPosition(
		        ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));
		rend.setNegativeItemLabelPositionFallback(new ItemLabelPosition(
		        ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));

		try
		{
			/**
			 * This utility saves the JFreeChart as a JPEG First Parameter:
			 * FileName Second Parameter: Chart To Save Third Parameter: Height
			 * Of Picture Fourth Parameter: Width Of Picture
			 */

			sbf = new StringBuffer();
			sbf.append(fileLocation).append(fileName).append(strRewardNumber)
			        .append(fileFormat);
			ChartUtilities.saveChartAsPNG(new File(sbf.toString()), chart, 450,
												250); 

		}
		catch (IOException exp)
		{
			LoggerUtil.logError("YTDSummaryChart | create2DBarGraph | "
					+ strRewardNumber, exp, LOGGER);
			throw new ServiceException(6001, exp);
		}

	}
	
	/**
	 * Method to create required data set for CategoryDataset
	 * @param Map<String, Double> mapData
	 * @return	CategoryDataset barDataset
	 */
	
	public CategoryDataset createBarDataSetForDouble(Map<String, Double> mapData)
	{
		DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
		
		if (null != mapData)
		{
			for(String key : mapData.keySet()){
				//barDataset.addValue(mapData.get(key), "$ Spent", key);
				barDataset.setValue(mapData.get(key), key, "$ Spent");
			}
		}

		return barDataset;
	}
	
	/**
	 * The Class My2DSBRenderer.
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
	private static class My2DSBRenderer extends BarRenderer
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * Method to get the item paint.
		 * @param int row
		 * @param int col
		 * @return the Paint
		 */
		@Override
		public Paint getItemPaint(int row, int col)
		{
			return super.getItemPaint(col, row);
		}
	}
}