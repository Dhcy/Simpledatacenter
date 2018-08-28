package com.good.plat.datacenter.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFDataValidationConstraint;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	public static final String filename = "C:\\Users\\john\\Downloads\\vipgoods.xlsx";// "E:\\document\\诺兰\\上传模板\\vipgoods.xlsx";
	public static void main(String[] args) throws IOException, InvalidFormatException {
		main_readDocument(args);
		
	}
	
	public static void main_1(String args[]) throws IOException{
		Workbook wb = null;
		try {
			wb = ExcelReader.readWorkbook(ExcelReader.filename);
			File ff = new File(ExcelReader.filename);
			InputStream is = new FileInputStream(ff);
			wb = ExcelReader.readWorkbook(is);
			List<String[]> dlist = ExcelReader.readSheet(wb,0, 1, (short)21, (short) 0, (short)21);
			
			File f = new File("d:\\test.txt");
			FileWriter  fw = null;
			try{
				fw = new FileWriter(f);
				if(dlist != null){
					for(String s[] : dlist){
						for(String st: s){
							fw.append(st+"\t");
						}
						fw.append("\r\n");
					}
				}
			}finally{
				if(fw != null){
					fw.close();
				}
			}
			
			
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 支持 xls 和 xlsx
	 * @param filename
	 * @return
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	public static Workbook readWorkbook(String filename) throws IOException, InvalidFormatException{
		File file = new File(filename);
		Workbook wb = null;
		wb = WorkbookFactory.create(file);
		return wb;
	}
	
	/**
	 * 支持 xls 和 xlsx
	 * @param filename
	 * @return
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	public static Workbook readWorkbook(InputStream is) throws IOException, InvalidFormatException{
		Workbook wb = null;
		//wb = WorkbookFactory.create(new POIFSFileSystem(is));
		wb = WorkbookFactory.create(is);
		
		return wb;
	}
	
	/**
	 * 支持 xls 和 xlsx
	 * @param wkb
	 * @param isheet
	 * @param brow
	 * @param rlength
	 * @param bcol
	 * @param clength
	 * @return
	 */
	public static List<String[]> readSheet(Workbook wkb,int isheet,final int brow,final short rlength
			,final short bcol,final short clength){
		List<String[]> rowList = new ArrayList<String[]>();
		if(wkb.getNumberOfSheets()-1 < isheet){
			return null;
		}
		System.out.println("Data dump : \n");
		Sheet sheet = wkb.getSheetAt(isheet);
		try{
			int rows = sheet.getPhysicalNumberOfRows();
			System.out.println("sheet "+ isheet+" has "+sheet.getPhysicalNumberOfRows()+" row(s).");
			for(int r=brow;r<rows;++r){
				System.out.println("reading row: "+r);
				Row row = sheet.getRow(r);
				if(row == null){
					System.out.println("row : "+ r+" is null,continue next row");
					continue;
				}
				String arow[] = readRow(row,bcol,clength);
				if(arow != null){
					rowList.add(arow);
				}else{
					System.out.println("row : "+ r+" is null,continue next row");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		return rowList;
	}
	
	/**
	 * 支持 xls 和 xlsx
	 * @param row
	 * @param bcol
	 * @param length
	 * @return
	 */
	public static String[] readRow(Row row,final short bcol,final short length){
		String rowData[] = null;
		if(row == null || length <= 0 || bcol <0){
			return null;
		}
		rowData = new String[length];
		for(int i=0;i<rowData.length;i++){
			rowData[i] = null;
		}
		int cols = row.getLastCellNum();   //row.getPhysicalNumberOfCells();
		for(short i=row.getFirstCellNum();i<cols && i>= bcol && i<length;++i){
			Cell cell = row.getCell((short) i);
			
			String cellValue = null; 
			//System.out.println("reading cell : "+i);
			if(cell == null){
				cellValue = null;
			}else{
				cellValue = readCellValue(cell);
			}
			rowData[i] = cellValue;
		}
		return rowData;
	}
	
	/**
	 * 支持 xls 和 xlsx
	 * @param cell
	 * @return
	 */
	public static String readCellValue(Cell cell){
		if(cell == null){
			System.out.println("cell is null");
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		SimpleDateFormat sdf_yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");
		
		String value = null;
		switch(cell.getCellType()){
		case HSSFCell.CELL_TYPE_FORMULA:
			value = cell.getCellFormula();
			System.out.println("FORMULA cell : "+value);
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			if(DateUtil.isCellDateFormatted(cell)){
				//date
				//2015/10/8(cell=08-十月-2015)
				//14:50(cell=31-十二月-1899)
				Calendar cal = Calendar.getInstance();
				Date date = cell.getDateCellValue();
				if("1899-12-31".equals(sdf_yyyy_MM_dd.format(cell.getDateCellValue()))){
					//System.out.println("convert DATE cell : "+sdf.format(cell.getDateCellValue()));
					cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));//yyyy, MM-1 ,dd(day of month) 
				}else{
					cal.setTime(cell.getDateCellValue());
				}
				value = sdf.format(cal.getTime());
				//System.out.println("DATE cell : "+value);
			}else{
				//1-->1.0
				if(Math.floor(cell.getNumericCellValue()) == cell.getNumericCellValue())
				{
					value = ""+  (long) cell.getNumericCellValue();
					//System.out.println("Integer cell : "+ value);
				}else{
					//System.out.println("Double cell : "+ value);
					value = "" + cell.getNumericCellValue();
				}
			}
			break;
		case HSSFCell.CELL_TYPE_STRING:
			value = cell.getStringCellValue();
			//System.out.println("STRING cell : "+value);
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			value = "";
			//System.out.println("BLANK cell : "+value);
			break;
		case HSSFCell.CELL_TYPE_ERROR:
			value = "";
			//System.out.println("ERROR cell : "+value);
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			value = cell.getBooleanCellValue()?"true":"false";
			//System.out.println("BOOLEAN cell : "+value);
			break;
		default:
		}
		
		return value;
	}
	
	/**
	 * 
	 * @Title: readDocument
	 * @Description: TODO
	 * @param doc
	 * @param sheetIndex
	 * based-0
	 * @return
	 * List<Object[]>
	 * @author moxw
	 * @param removeTitle 
	 * @date 2016年7月12日 上午10:35:47
	 */
	public static List<String[]> readDocument(Workbook doc,int sheetIndex, boolean removeTitle){
		if(doc == null || doc.getSheetAt(sheetIndex) == null) return null;
		List<String[]> list = new LinkedList<String[]>();
		Sheet sheet = doc.getSheetAt(sheetIndex);
		Iterator<Row> it = sheet.iterator();
		while(it.hasNext()){
			Row row = it.next();
			String[] rowVal = new String[row.getLastCellNum() - row.getFirstCellNum() + 1];
			int cellIndex = 0;
			Iterator<Cell> cit = row.cellIterator();
			while(cit.hasNext()){
				rowVal[cellIndex++] = readCellValue(cit.next());
			}
			list.add(rowVal);
		}
		if(!list.isEmpty() && removeTitle){
			list.remove(0);
		}
		return list;
	}

	 /**
	  * 设置excel下拉框作用域
	  * @Title: setXSSFValidation
	  * @Description: TODO
	  * @param wb 
	  * @param sheet 
	  * @param textlist 填充内容
	  * @param sheetName 隐藏表名
	  * @param index   隐藏下标
	  * @param firstRow 起始行
	  * @param endRow   结束行
	  * @param firstCol 起始列
	  * @param endCol   结束列
	  * @return
	  * XSSFSheet
	  * @author moxw
	  * @date 2018-3-1 下午02:04:37
	  */
   public static XSSFSheet setXSSFValidation(XSSFWorkbook wb,XSSFSheet sheet,  
           String[] textlist,String sheetName,int index, int firstRow, int endRow, int firstCol,  
           int endCol) {
   	// 创建一个隐藏域，textlist是下拉列表数组
	   XSSFSheet sheet_hidden = wb.createSheet(sheetName);
	   wb.setSheetHidden(index, true);
   	for (int i = 0; i < textlist.length; i++){
   		sheet_hidden.createRow(i)
           .createCell(0)
           .setCellValue(textlist[i]);
   	}
   	// 然后用公式去引用，隐藏域中的 A1:A user.length 就是上面设置的格子
   	String formula = sheetName + "!$A$1:$A$" + textlist.length;
   	  XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);
//   		 XSSFDataValidationConstraint dvConstraint = 
//   		     (XSSFDataValidationConstraint) dvHelper.createExplicitListConstraint(textlist);
   	  XSSFDataValidationConstraint dvConstraint_agent = 
   		    (XSSFDataValidationConstraint) dvHelper.createFormulaListConstraint(formula);
   		 // 设置为下拉框的范围
   		 CellRangeAddressList addressList = 
   		     new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
   		 // 创建 DataValidation 对象
   		 XSSFDataValidation validation = 
   		     (XSSFDataValidation)dvHelper.createValidation(dvConstraint_agent, addressList);
   		 validation.setShowErrorBox(true);
   		 // 作用于指定工作表
   		 sheet.addValidationData(validation);
       return sheet;  
   }  
	/**
	 * 读取工作表
	 * @Title: readSheet
	 * @Description: TODO
	 * @param wkb 工作簿
	 * @param isheet 表下标
	 * @param brow 起始行
	 * @param bcol 起始列
	 * @param clength 列长度
	 * @param removeTitle 是否移除标题
	 * @return
	 * List<String[]>
	 * @author hwj
	 * @date 2018-2-5 上午09:42:49
	 */
	public static List<String[]> readSheet(Workbook wkb,int isheet,final int brow,final short bcol,final short clength,boolean removeTitle){
		List<String[]> rowList = new ArrayList<String[]>();
		if(wkb.getNumberOfSheets()-1 < isheet){
			return null;
		}
		System.out.println("Data dump : \n");
		Sheet sheet = wkb.getSheetAt(isheet);
		Iterator<Row> it=sheet.iterator();
		try{
			while (it.hasNext()) {
				Row row = it.next();
				if(row == null){
					System.out.println("row is null,continue next row");
					continue;
				}
				String arow[] = readRow(row,bcol,clength);
				if(arow != null){
					rowList.add(arow);
				}else{
					System.out.println("row is null,continue next row");
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		if(!rowList.isEmpty() && removeTitle){
			rowList.remove(0);
		}
		return rowList;
	}
	
	public static void main_readDocument(String args[]) throws InvalidFormatException, IOException{
		FileInputStream in = new FileInputStream("C:\\Users\\john\\Desktop\\买量后台数据需求v1.2.xls");
		Workbook doc = readWorkbook(in);
		List<String[]> list = readDocument(doc, 0,true);
		System.out.println(list);
		for(String s[]: list	){
			for(String ss : s){
				System.out.println(ss);
			}
		}
	}
}
