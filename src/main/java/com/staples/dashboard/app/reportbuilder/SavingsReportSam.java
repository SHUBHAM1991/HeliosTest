package com.staples.dashboard.app.reportbuilder;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.staples.dashboard.app.constants.ReportConstants;
import com.staples.dashboard.dto.SavingsDetailDTO;

public class SavingsReportSam extends AbstractExcelView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		// get data model which is passed by the Spring container
        List<SavingsDetailDTO> savingsList = (List<SavingsDetailDTO>) model.get(ReportConstants.SAM_SAVINGS_LIST_RETURN_PARAM);
        
        String custNum = request.getParameter(ReportConstants.CUSTOMER_NUMBER);
        String fileName="Savings_Details_"+custNum; 
        response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+".xls\"");
        // create a new Excel sheet
        HSSFSheet sheet = workbook.createSheet(fileName);
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
        HSSFRow header = sheet.createRow(ReportConstants.ZERO);
         
        header.createCell(ReportConstants.ZERO).setCellValue(ReportConstants.EXCEL_BUILDER_CUSTOMER_NUMBER);
        header.getCell(ReportConstants.ZERO).setCellStyle(style);
        
        header.createCell(ReportConstants.ONE).setCellValue(ReportConstants.EXCEL_BUILDER_ORDER_NUM);
        header.getCell(ReportConstants.ONE).setCellStyle(style);
         
        header.createCell(ReportConstants.TWO).setCellValue(ReportConstants.EXCEL_BUILDER_SKU_NUM);
        header.getCell(ReportConstants.TWO).setCellStyle(style);
        
        header.createCell(ReportConstants.THREE).setCellValue(ReportConstants.EXCEL_BUILDER_QUANTITY);
        header.getCell(ReportConstants.THREE).setCellStyle(style);
         
        header.createCell(ReportConstants.FOUR).setCellValue(ReportConstants.EXCEL_BUILDER_PRODUCT_DESC);
        header.getCell(ReportConstants.FOUR).setCellStyle(style);
         
        header.createCell(ReportConstants.FIVE).setCellValue(ReportConstants.EXCEL_BUILDER_CHANNEL);
        header.getCell(ReportConstants.FIVE).setCellStyle(style);
        
        header.createCell(ReportConstants.SIX).setCellValue(ReportConstants.EXCEL_BUILDER_AMOUNT);
        header.getCell(ReportConstants.SIX).setCellStyle(style);
        
        header.createCell(ReportConstants.SEVEN).setCellValue(ReportConstants.EXCEL_BUILDER_PROGRAM_PRICE);
        header.getCell(ReportConstants.SEVEN).setCellStyle(style);
        
       // header.createCell(ReportConstants.EIGHT).setCellValue(ReportConstants.EXCEL_BUILDER_PROJ_REBATE);
      //  header.getCell(ReportConstants.EIGHT).setCellStyle(style);
         
        header.createCell(ReportConstants.EIGHT).setCellValue(ReportConstants.EXCEL_BUILDER_PROGRAM_SAVINGS);
        header.getCell(ReportConstants.EIGHT).setCellStyle(style);
         
        // create data rows
        int rowCount = 1;
        Double totalAmount=0.0;
        Double totalProgramPrice=0.0;
       // Double totalRebate=0.0;
        Double totalsavings=0.0;
         
        for (SavingsDetailDTO aList : savingsList) {
        	totalAmount+=Double.parseDouble(aList.getAmount());
        	totalProgramPrice+=Double.parseDouble(aList.getProgramPrice());
        	//totalRebate+=Double.parseDouble(aList.getProjectedRebate());
        	totalsavings+=Double.parseDouble(aList.getProgramSavings());
            HSSFRow aRow = sheet.createRow(rowCount++);
            aRow.createCell(ReportConstants.ZERO).setCellValue(aList.getCustNumber());
            aRow.createCell(ReportConstants.ONE).setCellValue(aList.getOrderNumber());
            aRow.createCell(ReportConstants.TWO).setCellValue(aList.getSkuNum());
            aRow.createCell(ReportConstants.THREE).setCellValue(aList.getOrderQuantity());
            aRow.createCell(ReportConstants.FOUR).setCellValue(aList.getSkuName());
            aRow.createCell(ReportConstants.FIVE).setCellValue(aList.getChannel());
            aRow.createCell(ReportConstants.SIX).setCellValue("$ "+aList.getAmount());
            aRow.createCell(ReportConstants.SEVEN).setCellValue("$ "+aList.getProgramPrice());
           // aRow.createCell(ReportConstants.EIGHT).setCellValue("$ "+aList.getProjectedRebate());
            aRow.createCell(ReportConstants.EIGHT).setCellValue("$ "+aList.getProgramSavings());
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
       // totalRebate = Double.valueOf(df.format(totalRebate));
        totalsavings = Double.valueOf(df.format(totalsavings));
        
        if(savingsList!=null && savingsList.size()>0){
        	HSSFRow aRow = sheet.createRow(++rowCount);
        	aRow.createCell(ReportConstants.TWO).setCellValue("Total");
        	aRow.getCell(ReportConstants.TWO).setCellStyle(styleCell);
        	 aRow.createCell(ReportConstants.SIX).setCellValue("$ "+totalAmount);
        	 aRow.getCell(ReportConstants.SIX).setCellStyle(styleCell);
             aRow.createCell(ReportConstants.SEVEN).setCellValue("$ "+totalProgramPrice);
             aRow.getCell(ReportConstants.SEVEN).setCellStyle(styleCell);
             //aRow.createCell(ReportConstants.EIGHT).setCellValue("$ "+totalRebate);
            // aRow.getCell(ReportConstants.EIGHT).setCellStyle(styleCell);
             aRow.createCell(ReportConstants.EIGHT).setCellValue("$ "+totalsavings);
             aRow.getCell(ReportConstants.EIGHT).setCellStyle(styleCell);
        	
        	
        }
		
	}

}
