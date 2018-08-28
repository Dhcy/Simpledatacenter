package com.good.plat.datacenter.independ.controller.yhindex;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.good.plat.datacenter.common.base.BaseController;
import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.common.util.ExcelGenerator;
import com.good.plat.datacenter.common.util.HTTPUtil;
import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.independ.entity.yhindex.YhAchieveStat;
import com.good.plat.datacenter.independ.service.yhindex.YhAchieveStatService;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;
@Controller
@RequestMapping(value="/yhAchieveStat")
public class YhAchieveStatController extends BaseController {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private YhAchieveStatService yhAchieveStatService;
	
	/**
	 * 成就统计
	 * @Title: selectAchieveStatList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhAchieveStat>
	 * @author hwj
	 * @date 2018-5-21 下午06:00:27
	 */
	@RequestMapping(value="/selectAchieveStatList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<YhAchieveStat> selectAchieveStatList(@RequestBody IndependBaseSearchData searchData) throws Exception{
		List<YhAchieveStat> list=new ArrayList<YhAchieveStat>();
		try {
			//log
			Date opDate = new Date();
			String methodName = "selectAchieveStatList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list=yhAchieveStatService.selectyhAchieveStatList(searchData);
		} catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return list;
	}
	/**
	 * 导出成就统计
	 * @Title: exportAchieveStatList
	 * @Description: TODO
	 * @param searchData
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2018-5-21 下午06:06:49
	 */
	@RequestMapping(value = "/exportAchieveStatList", method = {
			RequestMethod.POST})
	public void exportAchieveStatList(
			@RequestBody IndependBaseSearchData searchData,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportAchieveStatList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<String> sheetNameList = new LinkedList<String>();
		String filename = "成就统计.xlsx";
		List<YhAchieveStat> achieveList =yhAchieveStatService.selectyhAchieveStatList(searchData);
		//导出list
		if(achieveList != null){
			String[] title = new String[] {"日期","区服","成就ID","完成人数","完成率"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(YhAchieveStat e : achieveList){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getAreaid();
				items[count++] =e.getAchieveid();
				items[count++] =e.getCount();
				items[count++] =e.getRate()+"%";
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("成就统计");
		}
		//
		Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
		ExcelGenerator.fillWorkBook(w,sheetNameList, list);
		
		//
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		w.write(baos);
		String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
		HTTPUtil.responseFile(response, request, contentType , filename, baos);
	}
}
