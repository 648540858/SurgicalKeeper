package com.rjkx.sk.system.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

import com.rjkx.sk.system.utils.SpringBeanLoader;

/**
 * EXCEL工具包
 * @author Rally
 *
 */
public class ExcelHepler {

	private static Log log = LogFactory.getLog(ExcelHepler.class);
	
	private HSSFWorkbook workBook = null;
	/**
	 * 获取文件对象
	 * @param file
	 * @return
	 */
	private HSSFWorkbook getExcelInstance(File file)
	{
		try {
			FileInputStream fs = new FileInputStream(file);
			workBook = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
			log.error("读取EXCEL文件发生异常!请检查文件地址是否正确!");
		}
		return workBook;
	}
	/**
	 * 根据文件路径获取实例
	 * @param filePath
	 * @return
	 */
	private HSSFWorkbook getExcelInstance(String filePath)
	{
		try {
			FileInputStream fs = new FileInputStream(filePath);
			workBook = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
			log.error("读取EXCEL文件发生异常!请检查文件地址是否正确!");
		}
		return workBook;
	}
	/**
	 * 根据index与文件获取SHEET
	 * @param flie
	 * @param sheetIndex
	 * @return
	 */
	public HSSFSheet getWorkBookSheet(File file,int sheetIndex)
	{
		return this.getExcelInstance(file).getSheetAt(sheetIndex);
	}
	/**
	 * 根据index与文件获取SHEET
	 * @param filePath
	 * @param sheetIndex
	 * @return
	 */
	public HSSFSheet getWorkBookSheet(String filePath,int sheetIndex)
	{
		return this.getExcelInstance(filePath).getSheetAt(sheetIndex);
	}
	/**
	 * 获取SHEET
	 * @param file
	 * @param sheetName
	 * @return
	 */
	public HSSFSheet getWorkBookSheet(File file,String sheetName)
	{
		return this.getExcelInstance(file).getSheet(sheetName);
	}
	/**
	 * 根据文件路径获取SHEET
	 * @param filePath
	 * @param sheetName
	 * @return
	 */
	public HSSFSheet getWorkBookSheet(String filePath,String sheetName)
	{
		return this.getExcelInstance(filePath).getSheet(sheetName);
	}
	/**
	 * 获取所有值
	 * @param filePath
	 * @param sheetName
	 * @return
	 */
	public List<Map<Integer,Object>> getSheetData(String filePath,String sheetName,String dataFormat)
	{
		List<Map<Integer,Object>> data = new ArrayList<Map<Integer,Object>>();
		HSSFSheet sheet = this.getWorkBookSheet(filePath, sheetName);
		int rowCount = sheet.getLastRowNum();
		
		for(int i=0;i<=rowCount;i++)
		{
			int j = 0;
			HSSFRow row = sheet.getRow(i);
			int colCount = row.getPhysicalNumberOfCells();
			Map<Integer,Object> rowData = new HashMap<Integer,Object>();
			while (j < colCount) {
				rowData.put(j, this.getCellFormatValue(row.getCell(j),dataFormat));
				j++;
			}
			data.add(rowData);
		}
		return data;
	}
	/**
	 * 获取所有值
	 * @param filePath
	 * @param sheetName
	 * @return
	 */
	public List<Map<Integer,Object>> getSheetData(String filePath,int sheetIndex,String dataFormat)
	{
		List<Map<Integer,Object>> data = new ArrayList<Map<Integer,Object>>();
		HSSFSheet sheet = this.getWorkBookSheet(filePath, sheetIndex);
		int rowCount = sheet.getLastRowNum();
		
		for(int i=0;i<=rowCount;i++)
		{
			int j = 0;
			HSSFRow row = sheet.getRow(i);
			int colCount = row.getPhysicalNumberOfCells();
			Map<Integer,Object> rowData = new HashMap<Integer,Object>();
			while (j < colCount) {
				rowData.put(j, this.getCellFormatValue(row.getCell(j),dataFormat));
				j++;
			}
			data.add(rowData);
		}
		return data;
	}
	/**
	 * 获取所有值
	 * @param file
	 * @param sheetName
	 * @return
	 */
	public List<Map<Integer,Object>> getSheetData(File file,String sheetName,String dataFormat)
	{
		List<Map<Integer,Object>> data = new ArrayList<Map<Integer,Object>>();
		HSSFSheet sheet = this.getWorkBookSheet(file, sheetName);
		int rowCount = sheet.getLastRowNum();
		
		for(int i=0;i<=rowCount;i++)
		{
			int j = 0;
			HSSFRow row = sheet.getRow(i);
			int colCount = row.getPhysicalNumberOfCells();
			Map<Integer,Object> rowData = new HashMap<Integer,Object>();
			while (j < colCount) {
				rowData.put(j, this.getCellFormatValue(row.getCell(j),dataFormat));
				j++;
			}
			data.add(rowData);
		}
		return data;
	}
	/**
	 * 获取所有值
	 * @param file
	 * @param sheetName
	 * @return
	 */
	public List<Map<Integer,Object>> getSheetData(File file,int sheetIndex,String dataFormat)
	{
		List<Map<Integer,Object>> data = new ArrayList<Map<Integer,Object>>();
		HSSFSheet sheet = this.getWorkBookSheet(file, sheetIndex);
		int rowCount = sheet.getLastRowNum();
		
		for(int i=0;i<=rowCount;i++)
		{
			int j = 0;
			HSSFRow row = sheet.getRow(i);
			int colCount = row.getPhysicalNumberOfCells();
			Map<Integer,Object> rowData = new HashMap<Integer,Object>();
			while (j < colCount) {
				rowData.put(j, this.getCellFormatValue(row.getCell(j),dataFormat));
				j++;
			}
			data.add(rowData);
		}
		return data;
	}
	 /**     
	 * 获取单元格数据内容为字符串类型的数据     
	 * 
	 * @param cell Excel单元格     
	 * @return String 单元格数据内容     
	 */
	@SuppressWarnings("unused")
	private String getStringCellValue(HSSFCell cell) {
		String strCell = "";
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			strCell = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			strCell = String.valueOf(cell.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			strCell = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_BLANK:
			strCell = "";
			break;
		default:
			strCell = "";
			break;
		}
		if (strCell.equals("") || strCell == null) {
			return "";
		}
		if (cell == null) {
			return "";
		}
		return strCell;
	}

	/**     
	 * 获取单元格数据内容为日期类型的数据     
	 *      
	 * @param cell     
	 *            
	Excel单元格     
	 * @return String 单元格数据内容     
	 */
	@SuppressWarnings({ "unused", "deprecation" })
	private String getDateCellValue(HSSFCell cell) {
		String result = "";
		try {
			int cellType = cell.getCellType();
			if (cellType == Cell.CELL_TYPE_NUMERIC) {
				Date date = cell.getDateCellValue();
				result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1)
						+ "-" + date.getDate();
			} else if (cellType == Cell.CELL_TYPE_STRING) {
				String date = getStringCellValue(cell);
				result = date.replaceAll("[年月]", "-").replace("日", "").trim();
			} else if (cellType == Cell.CELL_TYPE_BLANK) {
				result = "";
			}
		} catch (Exception e) {
			log.error("时间格式不正确!");
			e.printStackTrace();
		}
		return result;
	}

	/**     
	 * 根据HSSFCell类型设置数据     
	 * @param cell     
	 * @return     
	 */
	public String getCellFormatValue(HSSFCell cell,String dataFormat) {
		String cellvalue = "";
		if (cell != null) {         
			switch (cell.getCellType()) {          
			case Cell.CELL_TYPE_NUMERIC:
				DecimalFormat a = new DecimalFormat("##0.##");
				cellvalue = String.valueOf(a.format(cell.getNumericCellValue()));
				break;
			case Cell.CELL_TYPE_FORMULA: {
             
				if (DateUtil.isCellDateFormatted(cell)) {                   
					Date date = cell.getDateCellValue();
					SimpleDateFormat sdf = new SimpleDateFormat(dataFormat);
					cellvalue = sdf.format(date);
				}               
				else {                 
					cellvalue = String.valueOf(cell.getNumericCellValue());
				}
				break;
			}           
			case Cell.CELL_TYPE_STRING:         
				cellvalue = cell.getRichStringCellValue().getString();
				break;         
			default:
				cellvalue = " ";
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;
	}
	/**
	 * 获得表格工具类实例
	 * @return
	 */
	public static ExcelHepler getHelperInstance()
	{
		return (ExcelHepler) SpringBeanLoader.getSpringBean("excelHelper");
	}
}