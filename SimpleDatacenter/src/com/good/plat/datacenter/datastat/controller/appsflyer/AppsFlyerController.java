package com.good.plat.datacenter.datastat.controller.appsflyer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.good.plat.datacenter.common.base.BaseController;
import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.common.base.DateUtil;
import com.good.plat.datacenter.common.base.Validator;
import com.good.plat.datacenter.common.util.ExcelGenerator;
import com.good.plat.datacenter.common.util.GetRequestJsonUtils;
import com.good.plat.datacenter.common.util.HTTPUtil;
import com.good.plat.datacenter.common.util.JSONUtil;
import com.good.plat.datacenter.datastat.entity.appsflyer.AppsFlyer;
import com.good.plat.datacenter.datastat.entity.balanceplat.AppleBalance;
import com.good.plat.datacenter.datastat.service.appsflyer.AppsFlyerService;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

@Controller
@RequestMapping(value="/appsFlayer")
public class AppsFlyerController{
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private AppsFlyerService appsFlyerService;
	// log
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	private String[] ipList={"54.77.178.93","54.77.45.90","54.72.160.117","54.77.35.243","52.212.89.218","52.51.4.6","130.211.55.20","146.148.126.140","130.211.88.33"};
	/**
	 * 接收AF数据
	 * @Title: input
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @throws Exception
	 * void
	 * @author hwj
	 * @throws IOException 
	 * @throws  
	 * @date 2017-6-2 上午11:45:35
	 */
	@RequestMapping( value="/saveAFData",method={RequestMethod.POST,RequestMethod.GET})
	public  void saveAFData(HttpServletRequest request ,HttpServletResponse response){
 		String url = request.getScheme()+"://" + request.getServerName()+":"+request.getServerPort()+ request.getServletPath();
 		String requestParam=null;
 		try {
 			requestParam=GetRequestJsonUtils.getRequestJsonString(request);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String remoteIp=HTTPUtil.getIpAddr(request);
		String requestMethod=request.getMethod();
		logger.debug("完整的requestUrl:{}", url);
		logger.debug("requestMethod:{}", requestMethod);
		logger.debug("remoteIp:{}", remoteIp);
		logger.debug("接收到的数据:{}", requestParam);
		List<String> list=Arrays.asList(ipList);
		if(list!=null){
			if(!list.contains(remoteIp)){
				return;
			}
		}
		PrintWriter out;
		response.setCharacterEncoding("utf-8");
		if(Validator.str_isEmpty(requestParam)==true){
			try {
				out = response.getWriter();
				out.println("参数为空");
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return ;
		}
		BaseMessage msg=null;
		JSONObject jsonData=JSON.parseObject(requestParam);
		String installTime=jsonData.getString("install_time");
		String platform=jsonData.getString("platform");
		String eventName=jsonData.getString("event_name");
		String appId=jsonData.getString("app_id");
		logger.debug("事件类型:{}", eventName);
		try {
			//
			if(eventName!=null){
				if(!eventName.trim().equals("af_purchase")){
					logger.debug("事件类型不符合");
					return;
				}
			}else{
				logger.debug("事件类型为空");
				return;
			}
			Date currDate=new Date();
			AppsFlyer af=new AppsFlyer();
			//推送日期
			af.setDateTime(currDate);
			//
			af.setAfData(JSON.toJSONString(jsonData));
			//安装日期
			if(!Validator.str_isEmpty(installTime)){
				Date install_time=DateUtil.parse_yyyy_MM_dd_HH_mm_ss(installTime);
				af.setInstallTime(install_time);
			}
			//平台
			if(!Validator.str_isEmpty(platform)){
				af.setPlatform(platform.toLowerCase());
			}
			//事件类型
			if(!Validator.str_isEmpty(eventName)){
				af.setEventName(eventName);
			}
			//appid
			if(!Validator.str_isEmpty(appId)){
				af.setAppId(appId);
			}
			msg=appsFlyerService.addAppsFlyer(af);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
		if(msg!=null){
			if(msg.getStatus()==1){
				logger.debug("添加成功！");
			}else{
				logger.debug("添加失败！");
			}
		}
		try {
			out = response.getWriter();
			out.println("操作成功");
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 导出af——purchase推送数据
	 * @Title: exportAfPurchaseList
	 * @Description: TODO
	 * @param request
	 * @param response
	 * void
	 * @author hwj
	 * @date 2017-6-8 下午03:51:28
	 */
	@RequestMapping( value="/exportAfPurchaseList",method={RequestMethod.POST,RequestMethod.GET})
	public  void exportAfPurchaseList(HttpServletRequest request ,HttpServletResponse response,@RequestBody AppsFlyer searchData ){
		// log
		sysAccessLogService.log(request, getClass(), "exportAfPurchaseList", new Date(), searchData);
		try{
			String nextDay=DateUtil.addDay(searchData.getEnddate(), 1);//往后一天
			Date preSecond=DateUtil.add(DateUtil.parse_yyyy_MM_dd(nextDay), Calendar.SECOND, -1);//上一秒
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd_HH_mm_ss(preSecond));
			//
			List<List<Object[]>> list = new LinkedList();
			List<String> sheetNameList = new LinkedList();
			String filename = "af_purchase数据.xlsx";
			//afpurchaseList数据
			List<AppsFlyer> afList=appsFlyerService.selectAfPurchaseList(searchData);
			//
			if(afList != null){
				String[] title = new String[] {"app_id","app_name","install_time","country","ip","idfa","idfv","device_name","platform","event_time","event_name","event_value","event_revenue","event_revenue_usd","event_revenue_currency","advertiser_id_enabled","media_source"};
				List<Object[]> datalist = new LinkedList();
				datalist.add(title);
				for(AppsFlyer e : afList){
					JSONObject af_purchase_json=JSON.parseObject(e.getAfData());
					logger.debug("导出的data={}", af_purchase_json);
					Object[] items = new  Object[title.length];
					int count = 0;
					items[count++] =af_purchase_json.get("app_id");
					items[count++] =af_purchase_json.get("app_name");
					items[count++] =af_purchase_json.get("install_time");
					items[count++] =af_purchase_json.get("country");
					items[count++] =af_purchase_json.get("ip");
					items[count++] =af_purchase_json.get("idfa");
					items[count++] =af_purchase_json.get("idfv");
					items[count++] =af_purchase_json.get("device_name");
					items[count++] =af_purchase_json.get("platform");
					items[count++] =af_purchase_json.get("event_time");
					items[count++] =af_purchase_json.get("event_name");
					items[count++] =af_purchase_json.get("event_value");
					items[count++] =af_purchase_json.get("event_revenue");
					items[count++] =af_purchase_json.get("event_revenue_usd");
					items[count++] =af_purchase_json.get("event_revenue_currency");
					items[count++] =af_purchase_json.get("advertiser_id_enabled");
					items[count++] =af_purchase_json.get("media_source");
					datalist.add(items);
				}
				list.add(datalist);
			}

			//
			Workbook w = ExcelGenerator.createWorkBook(ExcelGenerator.XLSX);
			ExcelGenerator.fillWorkBook(w, list);
			
			//
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			w.write(baos);
			String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
			HTTPUtil.responseFile(response, request, contentType , filename, baos);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
