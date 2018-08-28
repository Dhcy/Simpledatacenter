package com.good.plat.datacenter.independ.controller.yhindex;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.common.util.ExcelGenerator;
import com.good.plat.datacenter.common.util.HTTPUtil;
import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.independ.entity.yhindex.YhLightHouceData;
import com.good.plat.datacenter.independ.entity.yhindex.YhSocialContact;
import com.good.plat.datacenter.independ.entity.yhindex.YhStageStat;
import com.good.plat.datacenter.independ.service.yhindex.YhSocialContactService;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;
@Controller
@RequestMapping(value="/yhSocialContract")
public class YhSocialContractController extends BaseController {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private YhSocialContactService yhSocialContactService;
	
	/**
	 * 查询拥有好友数量的玩家数量
	 * @Title: selectActUserNumOfFriendsList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhSocialContact>
	 * @author hwj
	 * @date 2018-5-16 下午03:05:40
	 */
	@RequestMapping(value="/selectActUserNumOfFriendsList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<YhSocialContact> selectActUserNumOfFriendsList(@RequestBody IndependBaseSearchData searchData) throws Exception{
		List<YhSocialContact> list=new ArrayList<YhSocialContact>();
		try {
			//log
			Date opDate = new Date();
			String methodName = "selectActUserNumOfFriendsList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			list=yhSocialContactService.selectActUserNumOfFriendsList(searchData);
		} catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return list;
	}
	/**
	 * 导出好友数量玩家数量
	 * @Title: exportActUserNumOfFriendsList
	 * @Description: TODO
	 * @param searchData
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @date 2018-5-16 下午03:42:15
	 */
	@RequestMapping(value = "/exportActUserNumOfFriendsList", method = {
			RequestMethod.POST})
	public void exportActUserNumOfFriendsList(
			@RequestBody IndependBaseSearchData searchData,HttpServletResponse response)
			throws Exception {
		//log
		sysAccessLogService.log(request, getClass(), "exportActUserNumOfFriendsList", new Date(), searchData);
		//
		List<List<Object[]>> list = new LinkedList<List<Object[]>>();
		List<String> sheetNameList = new LinkedList<String>();
		String filename = "社交情况.xlsx";
		List<YhSocialContact> actUserNumlist =yhSocialContactService.selectActUserNumOfFriendsList(searchData);
		//导出list
		if(actUserNumlist != null){
			String[] title = new String[] {"日期","拥有好友数量","玩家人数"};
			List<Object[]> datalist = new LinkedList<Object[]>();
			datalist.add(title);
			for(YhSocialContact e : actUserNumlist){
				Object[] items = new  Object[title.length];
				int count = 0;
				items[count++] =e.getStatdate();
				items[count++] =e.getFcount();
				items[count++] =e.getCount();
				datalist.add(items);
			}
			list.add(datalist);
			sheetNameList.add("社交情况统计");
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
