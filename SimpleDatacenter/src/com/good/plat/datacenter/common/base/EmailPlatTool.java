package com.good.plat.datacenter.common.base;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.good.plat.datacenter.common.util.DESUtil;
import com.good.plat.datacenter.common.util.HTTPUtil;

public class EmailPlatTool {
	static Logger logger = LoggerFactory.getLogger(EmailPlatTool.class);
	public static String API_URL = PropertiesUtil.getValue("email_plat_send_url");
	public static String API_KEY = PropertiesUtil.getValue("email_plat_key");

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * 发送电子邮件
	 * 
	 * @return
	 */
	public static String sendEmail(String title, String content, List<String> toadress,int connectTimeout,int readTimeout) {
		String json = null;
		if (toadress == null || toadress.size() == 0
				|| (Validator.isNullOrBlank(title) && Validator.isNullOrBlank(content))) {
			return null;
		}
		JSONArray ja = new JSONArray(toadress.size());
		for (String s : toadress) {
			ja.add(s);
		}

		JSONObject data = new JSONObject();
		data.put("title", title);
		data.put("content", content);
		data.put("toadress", ja);
		logger.info("参数:{}", data.toJSONString());
		String d;
		try {
			d = DESUtil.desedeEncoder(data.toJSONString(), API_KEY);
			json = HTTPUtil.post(API_URL, "data=" + d, connectTimeout, readTimeout);// .loadJSON(API_URL, "data=" + d);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
}
