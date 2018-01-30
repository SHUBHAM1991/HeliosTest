package com.staples.dashboard.app.reportbuilder;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.LocalizedResourceHelper;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.Locale;

import org.springframework.web.servlet.view.AbstractView;
import com.staples.dashboard.app.constants.ReportConstants;
import com.staples.dashboard.dto.SavingsDetailDTO;

 abstract class AbstractExcelView extends AbstractView  {

	/** The content type for an Excel response */	
	private static final String CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";	
	
	/** The extension to look for existing templates */	
	private static final String EXTENSION = ".xlsx";	
	private String url;	
	
	/**	 * Default Constructor.	 * Sets the content type of the view to "application/vnd.ms-excel".	 */	
	public AbstractExcelView() {		
		setContentType(CONTENT_TYPE);	
	}
	public void setUrl(String url) {
		this.url = url;
	}


	@Override
	protected boolean generatesDownloadContent() {
		return true;
	}

	/**
	 * Renders the Excel view, given the specified model.
	 */
	@Override
	protected final void renderMergedOutputModel(
			Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		XSSFWorkbook workbook;
		
		if (this.url != null) {
			workbook = getTemplateSource(this.url, request);
		}
		else {
			workbook = new XSSFWorkbook();
			logger.debug("Created Excel Workbook from scratch");
		}

		buildExcelDocument(model, workbook, request, response);
		// Set the content type.
				response.setContentType(getContentType());

				// Should we set the content length here?
				// response.setContentLength(workbook.getBytes().length);

				// Flush byte array to servlet output stream.
				ServletOutputStream out = response.getOutputStream();
				workbook.write(out);
				out.flush();
		// Set the content type.
		//response.setContentType(getContentType());

		// Should we set the content length here?
		// response.setContentLength(workbook.getBytes().length);

		// Flush byte array to servlet output stream.
		//ServletOutputStream out = response.getOutputStream();
		//workbook.write(baos);
		//writeToResponse(response, baos);
		//out.flush();
	}
	
	protected XSSFWorkbook getTemplateSource(String url, HttpServletRequest request) throws Exception {
		LocalizedResourceHelper helper = new LocalizedResourceHelper(getApplicationContext());
		Locale userLocale = RequestContextUtils.getLocale(request);
		Resource inputFile = helper.findLocalizedResource(url, EXTENSION, userLocale);

		// Create the Excel document from the source.
		if (logger.isDebugEnabled()) {
			logger.debug("Loading Excel workbook from " + inputFile);
		}
		//POIFSFileSystem fs = new POIFSFileSystem(inputFile.getInputStream());
		return new XSSFWorkbook(inputFile.getInputStream());
	}
	protected abstract void buildExcelDocument(
			Map<String, Object> model, XSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
			throws Exception;
	
	protected XSSFCell getCell(XSSFSheet sheet, int row, int col) {
		XSSFRow sheetRow = sheet.getRow(row);
		if (sheetRow == null) {
			sheetRow = sheet.createRow(row);
		}
		XSSFCell cell = sheetRow.getCell(col);
		if (cell == null) {
			cell = sheetRow.createCell(col);
		}
		return cell;
	}
	protected void setText(XSSFCell cell, String text) {
		cell.setCellType(XSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(text);
	}
}
public class SavingsReportLead extends AbstractExcelView {
	
	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			XSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		// get data model which is passed by the Spring container
        List<SavingsDetailDTO> savingsList = (List<SavingsDetailDTO>) model.get(ReportConstants.SAVINGS_LIST_RETURN_PARAM);
        
        String custNum = request.getParameter(ReportConstants.CUSTOMER_NUMBER);
        String fileName="Savings_Details_"+custNum; 
        response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+".xlsx\"");
        // create a new Excel sheet
        XSSFSheet sheet = workbook.createSheet(fileName);
        sheet.setDefaultColumnWidth(30);
         
        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName(HSSFFont.FONT_ARIAL);
        style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.BLACK.index);
        style.setFont(font);
         
        // create header row
        XSSFRow header = sheet.createRow(ReportConstants.ZERO);
         
        header.createCell(ReportConstants.ZERO).setCellValue(ReportConstants.EXCEL_BUILDER_CUSTOMER_NUMBER);
        header.getCell(ReportConstants.ZERO).setCellStyle(style);
        
        header.createCell(ReportConstants.ONE).setCellValue(ReportConstants.EXCEL_BUILDER_SUB_CUSTOMER_NUMBER);
        header.getCell(ReportConstants.ONE).setCellStyle(style);
        
        header.createCell(ReportConstants.TWO).setCellValue(ReportConstants.EXCEL_BUILDER_ORDER_DATE);
        header.getCell(ReportConstants.TWO).setCellStyle(style);
        
        header.createCell(ReportConstants.THREE).setCellValue(ReportConstants.EXCEL_BUILDER_ORDER_NUM);
        header.getCell(ReportConstants.THREE).setCellStyle(style);
         
        header.createCell(ReportConstants.FOUR).setCellValue(ReportConstants.EXCEL_BUILDER_SKU_NUM);
        header.getCell(ReportConstants.FOUR).setCellStyle(style);
        
        header.createCell(ReportConstants.FIVE).setCellValue(ReportConstants.EXCEL_BUILDER_QUANTITY);
        header.getCell(ReportConstants.FIVE).setCellStyle(style);
        
        header.createCell(ReportConstants.SIX).setCellValue(ReportConstants.EXCEL_BUILDER_UNITPRICE);
        header.getCell(ReportConstants.SIX).setCellStyle(style);
         
        header.createCell(ReportConstants.SEVEN).setCellValue(ReportConstants.EXCEL_BUILDER_PRODUCT_DESC);
        header.getCell(ReportConstants.SEVEN).setCellStyle(style);
         
        header.createCell(ReportConstants.EIGHT).setCellValue(ReportConstants.EXCEL_BUILDER_CHANNEL);
        header.getCell(ReportConstants.EIGHT).setCellStyle(style);
        
        header.createCell(ReportConstants.NINE).setCellValue(ReportConstants.EXCEL_BUILDER_AMOUNT);
        header.getCell(ReportConstants.NINE).setCellStyle(style);
        
        header.createCell(ReportConstants.TEN).setCellValue(ReportConstants.EXCEL_BUILDER_PROGRAM_PRICE);
        header.getCell(ReportConstants.TEN).setCellStyle(style);
        
        header.createCell(ReportConstants.ELEVEN).setCellValue(ReportConstants.EXCEL_BUILDER_PROJ_REBATE);
        header.getCell(ReportConstants.ELEVEN).setCellStyle(style);
         
        header.createCell(ReportConstants.TWELVE).setCellValue(ReportConstants.EXCEL_BUILDER_PROGRAM_SAVINGS);
        header.getCell(ReportConstants.TWELVE).setCellStyle(style);
         
        // create data rows
        int rowCount = 1;
        Double totalAmount=0.0;
        Double totalProgramPrice=0.0;
        Double totalRebate=0.0;
        Double totalsavings=0.0;
        CellStyle styleRow = workbook.createCellStyle();
        styleRow.setAlignment(CellStyle.ALIGN_LEFT);
        for (SavingsDetailDTO aList : savingsList) {
        	totalAmount+=Double.parseDouble(aList.getAmount());
        	totalProgramPrice+=Double.parseDouble(aList.getProgramPrice());
        	totalRebate+=Double.parseDouble(aList.getProjectedRebate());
        	totalsavings+=Double.parseDouble(aList.getProgramSavings());
            XSSFRow aRow = sheet.createRow(rowCount++);
            aRow.createCell(ReportConstants.ZERO).setCellValue(aList.getCustNumber());
            aRow.createCell(ReportConstants.ONE).setCellValue(aList.getChildNumber());
            aRow.createCell(ReportConstants.TWO).setCellValue(aList.getOrderDate());
            aRow.createCell(ReportConstants.THREE).setCellValue(aList.getOrderNumber());
            aRow.createCell(ReportConstants.FOUR).setCellValue(aList.getSkuNum());
            aRow.createCell(ReportConstants.FIVE).setCellValue(Double.parseDouble(aList.getOrderQuantity()));
            aRow.getCell(ReportConstants.FIVE).setCellStyle(styleRow);
            aRow.createCell(ReportConstants.SIX).setCellValue(Double.parseDouble(aList.getUnitPrice()));
            aRow.getCell(ReportConstants.SIX).setCellStyle(styleRow);
            aRow.createCell(ReportConstants.SEVEN).setCellValue(aList.getSkuName());
            aRow.createCell(ReportConstants.EIGHT).setCellValue(aList.getChannel());
            aRow.createCell(ReportConstants.NINE).setCellValue(Double.parseDouble(aList.getAmount()));
            aRow.getCell(ReportConstants.NINE).setCellStyle(styleRow);
            aRow.createCell(ReportConstants.TEN).setCellValue(Double.parseDouble(aList.getProgramPrice()));
            aRow.getCell(ReportConstants.TEN).setCellStyle(styleRow);
            aRow.createCell(ReportConstants.ELEVEN).setCellValue(Double.parseDouble(aList.getProjectedRebate()));
            aRow.getCell(ReportConstants.ELEVEN).setCellStyle(styleRow);
            aRow.createCell(ReportConstants.TWELVE).setCellValue(Double.parseDouble(aList.getProgramSavings()));
            aRow.getCell(ReportConstants.TWELVE).setCellStyle(styleRow);
        }
        
        // create style for Total cell
        CellStyle styleCell = workbook.createCellStyle();
        Font fontCell = workbook.createFont();
        fontCell.setFontName(HSSFFont.FONT_ARIAL);
        styleCell.setFillForegroundColor(HSSFColor.WHITE.index);
        styleCell.setFillPattern(CellStyle.SOLID_FOREGROUND);
        fontCell.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        fontCell.setColor(HSSFColor.BLACK.index);
        styleCell.setFont(fontCell);
        
        //Rounding to two decimal format
        DecimalFormat df = new DecimalFormat("#.##");      
        totalAmount = Double.valueOf(df.format(totalAmount));
        totalProgramPrice = Double.valueOf(df.format(totalProgramPrice));
        totalRebate = Double.valueOf(df.format(totalRebate));
        totalsavings = Double.valueOf(df.format(totalsavings));
        
        if(savingsList!=null && savingsList.size()>0){
        	XSSFRow aRow = sheet.createRow(++rowCount);
        	aRow.createCell(ReportConstants.EIGHT).setCellValue("Total");
        	aRow.getCell(ReportConstants.EIGHT).setCellStyle(styleCell);
        	 aRow.createCell(ReportConstants.NINE).setCellValue("$ "+totalAmount);
        	 aRow.getCell(ReportConstants.NINE).setCellStyle(styleCell);
             aRow.createCell(ReportConstants.TEN).setCellValue("$ "+totalProgramPrice);
             aRow.getCell(ReportConstants.TEN).setCellStyle(styleCell);
             aRow.createCell(ReportConstants.ELEVEN).setCellValue("$ "+totalRebate);
             aRow.getCell(ReportConstants.ELEVEN).setCellStyle(styleCell);
             aRow.createCell(ReportConstants.TWELVE).setCellValue("$ "+totalsavings);
             aRow.getCell(ReportConstants.TWELVE).setCellStyle(styleCell);
        	
        	
        }
		
	}

	

}
