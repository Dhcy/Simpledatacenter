package com.good.plat.datacenter.common.util;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;




import com.good.plat.datacenter.datastat.service.index.LogRealTimeDataService;

/**
 * 静态值管理
 * @author hwj
 *
 */

public class AttrValueUtil {
	public final static String SJ2_CURR_TYPE="SJ2_CURR_TYPE";
	public final static String SJ2_ITEM_CONS_TYPE="SJ2_ITEM_CONS_TYPE";
	public final static String SJ2_ITEM_GAIN_TYPE="SJ2_ITEM_GAIN_TYPE";
	public final static String NL_CURR_GAIN_TYPE="NL_CURR_GAIN_TYPE";
	public final static String NL_CURR_CONS_TYPE="NL_CURR_CONS_TYPE";
	public final static String NL_CURR_TYPE="NL_CURR_TYPE";
	public final static String HB_TABLE="HB_TABLE";
	public final static String SJ2_AD_TYPE="SJ2_AD_TYPE";
	public final static String SJ2_TRADE_TYPE="SJ2_TRADE_TYPE";
	public final static String SJ2_HERO_NAME="SJ2_HERO_NAME";
	public final static String SJ2_TASK_TYPE="SJ2_TASK_TYPE";
	public final static String SJ2_ACHV_TYPE="SJ2_ACHV_TYPE";
	public final static String LANGUAGE="LANGUAGE";
	public final static String GAME_LANGUAGE="GAME_LANGUAGE";
	public final static String LOGTYPE2="LOGTYPE2";
	public final static String SJ3_RESOURCE_TYPE="SJ3_RESOURCE_TYPE";
	
	private Map<String,Map<String,String>> staticMap = new HashMap<String,Map<String,String>>();
	//日志实时服务
	@Autowired
	private LogRealTimeDataService logRealTimeDataService;
	
	public String getValueDescByValue(String attrCode,String attrValue) throws Exception{
		String result = getValueFromCache(attrCode, attrValue);
		if(result == null){
			loadFromDB(attrCode);
			result = getValueFromCache(attrCode, attrValue);
		}
		return result;
	}
	
	public  Map<String,String> getAllValueDescByCode(String attrCode) throws Exception{
		Map<String,String> result = staticMap.get(attrCode);
		if(result == null){
			loadFromDB(attrCode);
			result = staticMap.get(attrCode);
		}
		return result;
	}
	
	public String getValueFromCache(String attrCode,String attrValue){
		String result = null;
		if(staticMap.get(attrCode) != null){
			result = staticMap.get(attrCode).get(attrValue);
		}
		return result;
	}
	
//	public static void loadFromDB(String attrCode){
//		String sql = "select b.attr_value,b.attr_value_desc from attr_def a,attr_value b"
//				+ " where a.id=b.attr_id"
//				+ " and a.attr_code=?";
//		
//		List<Map<String,String>> list = SqlUtil.query(sql, new String[]{attrCode});
//		Map<String,String> valueMap = new HashMap<String,String>();
//		for(Map<String,String> map : list){
//			valueMap.put(map.get("attr_value"), map.get("attr_value_desc"));
//		}
//		staticMap.put(attrCode, valueMap);
//	}
	
	public void loadFromDB(String attrCode) throws Exception{
		Map<String,String> valueMap=logRealTimeDataService.getAllValueDescByCode(attrCode);
		staticMap.put(attrCode, valueMap);
	}
		
	
}
