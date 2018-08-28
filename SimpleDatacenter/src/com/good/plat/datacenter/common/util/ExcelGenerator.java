package com.good.plat.datacenter.common.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class ExcelGenerator {
	/**
	 * excel 2003
	 */
	public static final int XLS = 1;
	/**
	 * excel 2007
	 */
	public static final int XLSX = 2;
	public ExcelGenerator() {
		
	}

	public static void main(String[] args) throws IOException {
		FileOutputStream out = new FileOutputStream("d:/sxssf.xlsx");
		try{
			Workbook workbook = createWorkBook(ExcelGenerator.XLSX);
			ArrayList<Object[]> sheetlist = new ArrayList<Object[]>();
			List<List<Object[]>> workbooklist = new ArrayList<List<Object[]>>();
			sheetlist.add(new Object[]{1,"dd"});
			workbooklist.add(sheetlist);
			fillWorkBook(workbook,workbooklist);
			workbook.write(out);
		}finally{
			out.close();
		}
	}
	
	public static void write(List<List<Object[]>> list, OutputStream out,int type) throws IOException{
		Workbook word = ExcelGenerator.createWorkBook(type);
		ExcelGenerator.fillWorkBook(word, list);
		word.write(out);
	}
	
	public static Workbook createWorkBook(int type){
		Workbook workbook = null;
		switch (type) {
		case XLS:
			workbook = new HSSFWorkbook();
			break;
		case XLSX:
			workbook = new SXSSFWorkbook(-1);// turn off auto-flushing and accumulate all rows in memory
			break;
		default:
			break;
		}
		return workbook;
	}
	
	public static void fillWorkBook(Workbook workbook,List<List<Object[]>> list){
		for(int i=0;i<list.size();i++){
			Sheet sheet = workbook.createSheet("Sheet"+(i+1));
			fillSheet(sheet,list.get(i));
		}
	}
	
	
	public static void fillWorkBook(Workbook workbook,List<String> sheetNameList,List<List<Object[]>> list){
		for(int i=0;i<sheetNameList.size();i++){
			Sheet sheet = workbook.createSheet(sheetNameList.get(i));
			fillSheet(sheet,list.get(i));
		}
	}
	
	public static void fillSheet(Sheet sheet,List<Object[]> list){
		for(int i=0;i<list.size();i++){
			Row row = sheet.createRow(i);
			fillRow(row,list.get(i));
		}
	}
	
	public static void fillRow(Row row,Object[] rd){
		if(rd == null){
			return ;
		}else{
			for(int i=0;i < rd.length;i++){
				Cell cell = row.createCell(i);
				fillCell(cell,rd[i]);
			}
		}
	}
	
	public static void fillCell(Cell cell,Object rd){
		
		//Cell cell = row.createCell(pos);
		if(rd == null){
			cell.setCellType(Cell.CELL_TYPE_BLANK);
		}if(rd instanceof Boolean){
			cell.setCellValue((Boolean)rd);
			cell.setCellType(Cell.CELL_TYPE_BOOLEAN);
		}else if(rd instanceof Date){
			cell.setCellValue((Date)rd);
			//cell.setCellType(Cell.CELL_TYPE_STRING);
		}else if(rd instanceof String){
			cell.setCellValue((String)rd);
			cell.setCellType(Cell.CELL_TYPE_STRING);
		}else if(rd instanceof Short){
			cell.setCellValue((Short)rd);
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
		}else if(rd instanceof Integer){
			cell.setCellValue((Integer)rd);
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
		}else if(rd instanceof Float){
			cell.setCellValue((Float)rd);
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
		}else if(rd instanceof Long){
			cell.setCellValue((Long)rd);
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
		}else if(rd instanceof Double){
			cell.setCellValue((Double)rd);
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
		}else if(rd instanceof Calendar){
			cell.setCellValue((Calendar)rd);
			//cell.setCellType(Cell.CELL_TYPE_STRING);
		}else if(rd instanceof Calendar){
			cell.setCellValue((Calendar)rd);
		}else if(rd instanceof RichTextString){
			cell.setCellValue((RichTextString)rd);
			//cell.setCellType(Cell.CELL_TYPE_STRING);
		}else{
			cell.setCellValue(rd == null ? "" : rd.toString());
			cell.setCellType(Cell.CELL_TYPE_STRING);
		}
	}
}
