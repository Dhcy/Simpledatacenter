package com.good.plat.datacenter.datastat.controller.index;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.good.plat.datacenter.common.base.BaseController;
import com.good.plat.datacenter.common.base.PropertiesUtil;
import com.good.plat.datacenter.common.base.Validator;
import com.good.plat.datacenter.common.config.SysEvn;
import com.good.plat.datacenter.common.tool.Tool;
import com.good.plat.datacenter.common.util.AttrValueUtil;
import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.datastat.entity.index.LogRealTimeData;
import com.good.plat.datacenter.datastat.entity.index.LogTypeAtrrCodeValue;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;
/**
 * 日志实时数据控制器
 * @ClassName: logRealTimeDataController
 * @Description: TODO
 * @author hwj
 * @date 2017-8-21 下午05:55:07
 */
@Controller
@RequestMapping(value="/logPlat")
public class LogRealTimeDataController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ISysAccessLogService sysAccessLogService;
	@Autowired
	private LoggerUtil loggerUtil;
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private AttrValueUtil attrValueUtil;
	/**
	 * 获取日志实时数据
	 * @Title: getlogRealTimeDataList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<LogRealTimeData>
	 * @author hwj
	 * @date 2017-8-22 上午11:17:37
	 */
	@RequestMapping(value="/getLogRealTimeDataList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<LogRealTimeData> getlogRealTimeDataList(@RequestBody LogRealTimeData searchData){
			List<LogRealTimeData> dataList=null;
			Map<String, String> valueMap=null;
		try {
			//log
			Date opDate = new Date();
			String methodName = "getLogRealTimeDataList";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//
			String actorId=searchData.getActorid();
			String startLogDate=searchData.getStartDate();
			String endLogDate=searchData.getEndDate();
			String logType=searchData.getLogType();
			//入参
			JSONObject sendData=new JSONObject();
			sendData.put("gameid", searchData.getGameid());
			if(!Validator.isNullOrBlank(actorId)){
				//tableid="2"
				sendData.put("tableid", "2");
				sendData.put("actorid", actorId);
			}else{
				//tableid="1"
				sendData.put("tableid", "1");
			}
			if(searchData.getLimit()!=null){
				sendData.put("limit", searchData.getLimit().intValue());
			}
			if(!Validator.isNullOrBlank(searchData.getRowid())){
				sendData.put("end", searchData.getRowid());
			}
			//过滤条件
			JSONObject filterData=new JSONObject();
			if(!Validator.isNullOrBlank(logType)){
				filterData.put("logtype",logType);
			}
			if(!Validator.isNullOrBlank(startLogDate)){
				filterData.put("start-logdate", startLogDate);
			}
			if(!Validator.isNullOrBlank(endLogDate)){
				filterData.put("stop-logdate",endLogDate);
			}
			sendData.put("filter", filterData);
			logger.debug("入参是={}", sendData.toJSONString());
			String logDataUrl=PropertiesUtil.getValue("log_data_url");
			if(!Validator.isNullOrBlank(logDataUrl)){
				//返回
				long preTime=System.currentTimeMillis();
				String returnData=Tool.loadJSONForLog(sendData, logDataUrl);
				long lastTime=System.currentTimeMillis();
				long execSecTime=(lastTime-preTime)/1000;//秒
				long msTime=(lastTime-preTime)%1000;
				logger.debug("请求执行时间={}秒",String.valueOf(execSecTime)+"."+String.valueOf(msTime));
				JSONObject returnJson=JSONObject.parseObject(returnData);
				//
				if(returnJson!=null)
				{
					if(returnJson.getBooleanValue("flag")){
						dataList=new ArrayList<LogRealTimeData>();
						valueMap=attrValueUtil.getAllValueDescByCode(AttrValueUtil.LOGTYPE2);
						JSONArray jsonArr=returnJson.getJSONArray("result");
						long parsePreTime=System.currentTimeMillis();
						for(Object ob:jsonArr){
							JSONObject json=(JSONObject)ob;
							String logdate=json.getString("logdate");//日期
							String actorid=json.getString("actorid");//角色id
							String areaid=json.getString("areaid");//区服id
							String logtype=json.getString("logtype");//日志类型
							String logTypeDesc=valueMap.get(logtype);//日志类型描述
							String rowid=json.getString("rowid");
							//移除
							json.remove("logdate");
							json.remove("actorid");
							json.remove("areaid");
							json.remove("logtype");
							List<LogTypeAtrrCodeValue> tableData=new LinkedList<LogTypeAtrrCodeValue>();
							List<String> keyList=new ArrayList<String>(json.keySet());
							//预览内容
							StringBuilder preview=new StringBuilder();
							if(keyList!=null&&keyList.size()>0){
								for(int i=0;i<keyList.size();i++){
									String key=keyList.get(i);
									String value=json.getString(key);
									//截取前5个键值对作为预览
									if(!key.equals("rowid")){
										if(i<5){
												preview.append(key);
												preview.append(":");
												preview.append(value);
												preview.append(',');
										}
									////组装键值对数组
										LogTypeAtrrCodeValue atrrValue=new LogTypeAtrrCodeValue();
										atrrValue.setAttr_desc(key);
										atrrValue.setAttr_value(value);
										tableData.add(atrrValue);
									}
								}
							}
							String previewContent=preview.deleteCharAt(preview.length()-1).append("....").toString();
							//
							LogRealTimeData logData=new LogRealTimeData();
							logData.setLogdate(logdate);
							logData.setRowid(rowid);
							if(!Validator.isNullOrBlank(actorid)){
								logData.setActorid(actorid);
							}
							if(!Validator.isNullOrBlank(areaid)){
								logData.setAreaid(Integer.parseInt(areaid));
							}
							logData.setLogType(logtype);
							logData.setLogTypeDesc(logTypeDesc);
							logData.setPreviewContent(previewContent);
							logData.setTableData(tableData);
							dataList.add(logData);
						}
						long parseLastTime=System.currentTimeMillis();
						long parseSecTime=(parseLastTime-parsePreTime)/1000;
						long parseMsTime=(parseLastTime-parsePreTime)%1000;
						logger.debug("json解析时间={}秒",String.valueOf(parseSecTime)+"."+String.valueOf(parseMsTime));
					}else{
						throw new Exception("请求数据失败！");
					}
					
				}else{
					throw new Exception("返回数据为null串");
				}
			}else{
				throw new Exception("请求地址为null");
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dataList;
	}
}
