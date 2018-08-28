package com.good.plat.datacenter.common.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.base.PropertiesUtil;
import com.good.plat.datacenter.common.base.Validator;
import com.good.plat.datacenter.common.util.AttrValueUtil;
import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.datastat.entity.index.LogTypeAtrrCodeValue;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;
/**
 * 静态值控制器
 * @ClassName: AttrValueController
 * @Description: TODO
 * @author hwj
 * @date 2017-12-7 上午09:05:56
 */
@Controller
@RequestMapping(value="/attrValue")
public class AttrValueController {
	
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
	 * 根据静态编码获取静态值
	 * @Title: selectAllValueListByCode
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<String,Object>
	 * @author hwj
	 * @date 2017-12-6 下午04:22:04
	 */
	@RequestMapping(value="/selectAttrValueListByCode",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> selectAllValueListByCode(@RequestBody BaseSearchData searchData){
		Map<String,String> cacheAttrValues=null;
		List<LogTypeAtrrCodeValue> attrValues=new ArrayList<LogTypeAtrrCodeValue>();
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			//log
			Date opDate = new Date();
			String methodName = "selectAttrValueListByCode";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//attrcode
			String attrCode=searchData.getChecktype1();
			//静态数据
			if(!Validator.isNullOrBlank(attrCode)){
				cacheAttrValues=attrValueUtil.getAllValueDescByCode(attrCode);
				if(!cacheAttrValues.isEmpty()){
					Set<Entry<String,String>> set=cacheAttrValues.entrySet();
					Iterator<Entry<String, String>> its= set.iterator();
					while(its.hasNext()){
						Entry<String,String> entry=its.next();
						String key=entry.getKey();
						String value=entry.getValue();
						LogTypeAtrrCodeValue attrValue=new LogTypeAtrrCodeValue();
						attrValue.setAttr_code(attrCode);
						attrValue.setAttr_value(key);
						attrValue.setAttr_desc(value);
						attrValues.add(attrValue);
					}
				}
			}
			result.put("attrCode", attrCode);
			result.put("attrValues", attrValues);
		} catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 刷新静态值
	 * @Title: refreshAttrValue
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * BaseMessage
	 * @author hwj
	 * @date 2017-12-6 下午05:41:56
	 */
	@RequestMapping(value="/refreshAttrValue",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public BaseMessage refreshAttrValue(@RequestBody BaseSearchData searchData){
		BaseMessage msg=new BaseMessage();
		try {
			//log
			Date opDate = new Date();
			String methodName = "refreshAttrValue";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//静态编码
			String attrCode=searchData.getChecktype1();
			
			if(Validator.isNullOrBlank(attrCode)){
				throw new Exception("参数不能为空");
			}else{
				attrValueUtil.loadFromDB(attrCode);
			}
			msg.setMessage("刷新成功!");
			msg.setStatus(1);
		} catch (Exception e) {
			// TODO: handle exception
			msg.setStatus(0);
			msg.setMessage("刷新失败,请检查参数再刷新！");
		}
		return msg;
	}
	/**
	 * 获取属性值从配置文件
	 * @Title: getAttrValueFromConfig
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * LogTypeAtrrCodeValue
	 * @author hwj
	 * @date 2017-12-6 下午05:54:03
	 */
	@RequestMapping(value="/getAttrValueFromConfig",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<LogTypeAtrrCodeValue> getAttrValueFromConfig(@RequestBody BaseSearchData searchData){
		//log
		Date opDate = new Date();
		String methodName = "getAttrValueFromConfig";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		List<LogTypeAtrrCodeValue> list=new ArrayList<LogTypeAtrrCodeValue>();
		try {
			String key=searchData.getChecktype2();
			if(!Validator.isNullOrBlank(key)){
				String value=PropertiesUtil.getValue(key);
				LogTypeAtrrCodeValue attrValue=new LogTypeAtrrCodeValue();
				attrValue.setAttr_code(key);
				attrValue.setAttr_value(value);
				list.add(attrValue);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 刷新配置数据
	 * @Title: refreshConfigData
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * BaseMessage
	 * @author hwj
	 * @date 2017-12-6 下午06:30:07
	 */
	@RequestMapping(value="/refreshConfigData",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public BaseMessage refreshConfigData(@RequestBody BaseSearchData searchData){
		//log
		Date opDate = new Date();
		String methodName = "refreshConfigData";
		String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
		SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
		sysAccessLogService.saveAccessLog(accessLog);
		//
		BaseMessage msg=new BaseMessage();
		try {
			PropertiesUtil.reLoad();
			msg.setStatus(1);
			msg.setMessage("刷新成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setStatus(0);
			msg.setMessage("刷新失败!");
		}
		return msg;
	}
	/**
	 * 根据静态编码获取静态值列表
	 * @Title: getAttrValueListByCode
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * Map<String,Object>
	 * @author hwj
	 * @date 2018-2-28 上午11:32:30
	 */
	@RequestMapping(value="/getAttrValueListByCode",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> getAttrValueListByCode(@RequestBody LogTypeAtrrCodeValue searchData){
		Map<String,String> cacheAttrValues=null;
		List<LogTypeAtrrCodeValue> attrValues=new ArrayList<LogTypeAtrrCodeValue>();
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			//log
			Date opDate = new Date();
			String methodName = "getAttrValueListByCode";
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(getClass(), methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, getClass(), methodName,op_desc,opDate,null, searchData);
			sysAccessLogService.saveAccessLog(accessLog);
			//attrcode
			String attrCode=searchData.getAttr_code();
			//静态数据
			if(!Validator.isNullOrBlank(attrCode)){
				cacheAttrValues=attrValueUtil.getAllValueDescByCode(attrCode);
				if(!cacheAttrValues.isEmpty()){
					Set<Entry<String,String>> set=cacheAttrValues.entrySet();
					Iterator<Entry<String, String>> its= set.iterator();
					while(its.hasNext()){
						Entry<String,String> entry=its.next();
						String key=entry.getKey();
						String value=entry.getValue();
						LogTypeAtrrCodeValue attrValue=new LogTypeAtrrCodeValue();
						attrValue.setAttr_value(key);
						attrValue.setAttr_desc(value);
						attrValues.add(attrValue);
					}
				}
				result.put("code", 1);//成功
				result.put("attrValues", attrValues);//dataList
			}else{
				result.put("code", 0);//失败
				result.put("attrValues", attrValues);//dataList
			}
		} catch (Exception e){
			e.printStackTrace();
			result.put("code", 0);
			result.put("attrValues", attrValues);
		}
		return result;
	}
	
}
