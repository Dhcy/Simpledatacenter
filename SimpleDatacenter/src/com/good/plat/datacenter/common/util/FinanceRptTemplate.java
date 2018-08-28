package com.good.plat.datacenter.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSONObject;
import com.good.plat.datacenter.datastat.entity.balanceplat.DZPTChannelCompanyInfo;
import com.good.plat.datacenter.datastat.service.balanceplat.IBalancePlatService;

public class FinanceRptTemplate {

	String financeDate;//结算月份，格式：yyyy-MM-01
	private String url=null;
	private IBalancePlatService BalancePlatService1;
	public FinanceRptTemplate(String financeDate){
		this.financeDate=financeDate;
	}
	

	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}
	


	public IBalancePlatService getBalancePlatService1() {
		return BalancePlatService1;
	}


	public void setBalancePlatService1(IBalancePlatService balancePlatService1) {
		BalancePlatService1 = balancePlatService1;
	}


	public static void main(String[] args) throws Exception {
		File file = new File("test.xlsx");
		if(file.exists()){
			file.delete();
		}
		OutputStream out = new FileOutputStream(file);
		FinanceRptTemplate temp = new FinanceRptTemplate("2017-04-01");
//		temp.out(out);
	}
	
	public void out(OutputStream out,List<Map<String,String>> list) throws Exception{
//		String sql = "select c.name,b.channelsimname,b.channelname,a.currency,a.money,a.payrate,a.taxrate,ifnull(a.sharerate,1) sharerate,a.confcurr,a.exchrate,a.rmbrate"
//				+ " from usc_finacial_statement_monthly a , tb_logplat_channel b ,tb_logplat_game c, dzpt_channel_accounting d "
//				+ "where a.channel=b.id and a.gameid=c.id and a.gameid=d.gameid and d.channel=a.channel "
//				+ "and a.statdate=? and d.coopmodel=0 and d.isvalid=1 "
//				+ "order by b.id";
//		List<Map<String,String>> list = SqlUtil.queryStringMapList(sql,new String[]{this.financeDate});
		Map<String,List<Map<String,String>>>  records = new HashMap<String,List<Map<String,String>>>();
		//根据渠道分组，每个渠道对应该渠道的所有游戏的数据（同个渠道的数据在同个sheet页中）
		for(Map<String,String> map : list){
			String name = map.get("channelName");
			String simName = map.get("channelSimName");
			List<Map<String,String>> games = records.get(name+","+simName);
			if(games == null){
				games = new ArrayList<Map<String,String>>();
				records.put(name+","+simName, games);
			}
			games.add(map);
		}
		Workbook workbook = this.write(records,url);
		workbook.write(out);
	}
	
	public Workbook write(Map<String,List<Map<String,String>>> map,final String path) throws Exception {
		XSSFWorkbook wb = new XSSFWorkbook(new File(path+File.separator+"template.xlsx"));//TODO 改为用配置文件里的目录
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//
		boolean isNull=false;
		if(map.size()==0){
			isNull=true;
		}
		for(String name : map.keySet()){
			Integer channelId=null;
			boolean isNoOne=false;
			Sheet sheet = wb.cloneSheet(0);
			String company = name.split(",")[0];
			String simName = name.split(",")[1];
			wb.setSheetName(wb.getSheetIndex(sheet), company);
			//<--------------公司信息以及结算信息
			Cell cell = sheet.getRow(0).getCell(7);
			cell.setCellValue(Integer.parseInt(this.financeDate.substring(5, 7))+"月份");
			
			cell = sheet.getRow(1).getCell(2);
			cell.setCellValue(company);
			
			cell = sheet.getRow(1).getCell(6);
			cell.setCellValue("（"+simName+"）");
			cell = sheet.getRow(4).getCell(2);
			cell.setCellValue(sdf.parse(this.financeDate));
			//---------------->
			//<--------------各游戏结算信息
			List<Map<String,String>> list = map.get(name);
			int i=0;
			for(;i<list.size();i++){//每个游戏对应一行记录
				Row row = newRptRow(sheet, 9, 9+i);
				//填充数据到row
				Map<String,String> record = list.get(i);
				if(!isNoOne){
				channelId=Integer.valueOf(record.get("channelId"));	
				isNoOne=true;
					}
				String game = record.get("gameName");
				String channel = record.get("channelSimName");
				String money = record.get("money");
				String payrate = record.get("payrate");//通道费率
				String taxrate = record.get("taxrate");
				String sharerate = record.get("sharerate");//分成
				row.getCell(0).setCellValue(game);
				row.getCell(1).setCellValue(channel);
				row.getCell(2).setCellValue(Double.parseDouble(money));
				int index = 9+i+1;
				row.getCell(4).setCellValue(payrate);
				row.getCell(5).setCellFormula("1-E"+index);//=1-E10
				row.getCell(6).setCellFormula("C"+index+"-C"+index+"*E"+index);//=C10-C10*E10
				row.getCell(7).setCellValue(taxrate);
				row.getCell(8).setCellValue(sharerate);
				row.getCell(9).setCellFormula("(G"+index+"-H"+index+")*I"+index+"");//=(G10-H10)*I10
				
				row.getCell(10).setCellFormula("IF(J"+index+"=0,\"\",IF(SUMIF(A$"+index+":A"+index+",A"+index+",C$"+index+":C"+index+")<1000,\"不符合月结标准\",\" \"))");
			}
			i=9+i;
			Row row = sheet.getRow(i);
			row.getCell(7).setCellFormula("SUM(H10:H"+i+")");//=SUM(H10:H10)
			row.getCell(8).setCellFormula("SUM(J10:J"+i+")");//=SUM(J10:J10)
			//---------------->
			//TODO 发票信息&合作方账户信息
			DZPTChannelCompanyInfo  companyInfo=new DZPTChannelCompanyInfo();
			companyInfo.setChannelId(channelId);
			DZPTChannelCompanyInfo info=BalancePlatService1.selectCompanyByChannelId(companyInfo);
			if(info!=null){
				System.out.println("合作伙伴"+JSONObject.toJSONString(info));
				i=i+11;
				Row row1=sheet.getRow(i);
				Row row2=sheet.getRow(i+1);
				Row row3=sheet.getRow(i+2);
				row1.getCell(6).setCellValue(info.getBank());
				row2.getCell(6).setCellValue(info.getBankAcount());
				row3.getCell(6).setCellValue(info.getTaxType());
			}
			
		}
		XSSFFormulaEvaluator.evaluateAllFormulaCells(wb);
		wb.removeSheetAt(0);
		if(isNull){
			wb.createSheet("工作簿");
		}
		return wb;
	}
	
	private Row newRptRow(Sheet sheet,int first,int current){
		Row row;
		if(first == current){
			row = sheet.getRow(current);
		}else{
			sheet.shiftRows(current, sheet.getLastRowNum(), 1, true, false);
			row = sheet.createRow(current);
			Row firstRow = sheet.getRow(first);
			row.setRowStyle(firstRow.getRowStyle());
			row.setHeight(firstRow.getHeight());
			Iterator<Cell> it = firstRow.cellIterator();
			int i=0;
			while(it.hasNext()){
				Cell cell = it.next();
				Cell newCell = row.createCell(i++);
				newCell.setCellStyle(cell.getCellStyle());
//				newCell.setCellValue(i);
			}
		}
		
		return row;
	}

}
