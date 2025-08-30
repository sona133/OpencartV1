package utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility
{
	public FileInputStream fis;
	public FileOutputStream fos;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;
	
	public ExcelUtility(String path)
	{
		this.path = path;
	}
	
	public int getRowCount(String sheetName) throws IOException
	{
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		workbook.close();
		fis.close();
		return rowCount;
	}
	
	public int getCellCount(String sheetName, int rownum) throws IOException
	{
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		int cellcount = sheet.getLastRowNum();
		workbook.close();
		fis.close();
		return cellcount;
	}
	
	public String getCellData(String sheetName, int rownum, int colnum) throws IOException
	{
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);
		
		DataFormatter formatter = new DataFormatter();
		String data;
		try {
	    data = formatter.formatCellValue(cell);
		} catch (Exception e) {
		    data = "";
		}

			return data;
			
		}
	public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException 
	{
		File xlsFile = new File(path);
		if (!xlsFile.exists()) 
		{
	    workbook = new XSSFWorkbook();
	    fos = new FileOutputStream(path);
	    workbook.write(fos);
		}

		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		
		if(workbook.getSheetIndex(sheetName) == -1) 
		{
			workbook.createSheet(sheetName);
			sheet = workbook.getSheet(sheetName);
			
			if(sheet.getRow(rownum) == null) 
			{
				sheet.createRow(rownum);
				row = sheet.getRow(rownum);
				
				cell= row.createCell(colnum);
				cell.setCellValue(data);
				fos= new FileOutputStream(path);
				workbook.write(fos);
				workbook.close();
				fis.close();
				fos.close();
			}
		}
		

	}

		public void fillGreenColor(String sheetName, int rownum, int colnum) throws IOException 
		{
		    fis = new FileInputStream(path);
		    workbook = new XSSFWorkbook(fis);
		    sheet = workbook.getSheet(sheetName);
		    row = sheet.getRow(rownum);
		    cell = row.getCell(colnum);

		    style = workbook.createCellStyle();
		    style.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
		    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		    cell.setCellStyle(style);

		    fis.close();
		    fos = new FileOutputStream(path);
		    workbook.write(fos);
		    workbook.close();
		    fos.close();
		}
		
		public void fillRedColor(String sheetName, int rownum, int colnum) throws IOException 
		{
		    fis = new FileInputStream(path);
		    workbook = new XSSFWorkbook(fis);
		    sheet = workbook.getSheet(sheetName);
		    row = sheet.getRow(rownum);
		    cell = row.getCell(colnum);

		    style = workbook.createCellStyle();
		    
		    style.setFillForegroundColor(IndexedColors.RED.getIndex());
		    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		    
		    cell.setCellStyle(style);
		    workbook.write(fos);
		    workbook.close();
		    fis.close();
		    fos.close();
		    
		}

}
