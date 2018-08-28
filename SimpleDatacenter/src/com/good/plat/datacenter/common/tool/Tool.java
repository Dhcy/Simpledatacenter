package com.good.plat.datacenter.common.tool;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.good.plat.datacenter.common.base.Validator;
import com.good.plat.datacenter.common.config.SysEvn;

public class Tool {

	/**
	 * 拼接加密参数
	 * @param data
	 * @return
	 */
	public static JSONObject splicePrarm(JSONObject data,final String key){
		String sig = Md5Encrypt.md5(data.toJSONString()+key);
		data.put("sig", sig);
		return data;
	}
   
	/**
	 * 平台游戏接口
	 * 有参数的HTTP POST请求
	 * @Title: loadJSON
	 * @Description: TODO
	 * @param data 
	 * @param url 游戏接口地址
	 * @param key 公钥key
	 * @return
	 * String
	 * @author hwj
	 * @date 2017-3-1 下午06:12:16
	 */
	  public static String loadJSON(JSONObject data,final String url,final String key) {
		  return  JsonPostRequest.loadJSON(url, splicePrarm(data,key).toJSONString());
	}
		/**
		 * 验证MD5
		 * @param prarm
		 * @return
		 */
		public static boolean verifyMD5(JSONObject prarm,HttpServletRequest request){
			String sig = prarm.getString("sig");
			if(sig==null||sig.trim().equals("")){
				return false;
			}
			prarm.remove("sig");
			System.out.println("beforsig====="+prarm.toJSONString());
			String platsig = Md5Encrypt.md5(Prarm2StringOutWithSig(request)+SysEvn.KEY);
			System.out.println("platsig===="+platsig);
			if(sig.equals(platsig)){
				return true;
			}
			return false;
					
		}
		/**
		 * 获取json参数
		 * @param request
		 * @return
		 */
		public static String Prarm2StringOutWithSig(HttpServletRequest request ){
			Map prarmMap=request.getParameterMap();
			if (prarmMap!=null&&prarmMap.size()>0){
				String prarm=(String) prarmMap.keySet().toArray()[0];
				if(!Validator.str_isEmpty(prarm)){
					String index = ",\"sig\":\"";
					int indexs = prarm.indexOf(index);
					String singstring = prarm.substring(prarm.indexOf(index), indexs+41);
					return prarm.replaceAll(singstring, "");
				}
			}
				return null;
		}
		/**
		 * 日志数据接口
		 * 有参数的HTTP POST请求
		 * @Title: loadJSON
		 * @Description: TODO
		 * @param data 参数
		 * @param url 请求地址
		 * @return
		 * String 返回值
		 * @author hwj
		 * @date 2017-8-22 上午09:58:39
		 */
		 public static String loadJSONForLog(JSONObject data,final String url) {
			  return  JsonPostRequest.loadJSON(url,"in="+data.toString());
		}
}
