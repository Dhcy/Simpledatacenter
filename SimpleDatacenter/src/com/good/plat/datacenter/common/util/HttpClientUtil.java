package com.good.plat.datacenter.common.util;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class HttpClientUtil {
	
	public static void main(String[] args){
		StringBuffer str = new StringBuffer("<table  border=\"1\" cellpadding=\"10\"><tr><td>测试1</td><td>测试2</td></tr>");
		str.append("<tr><td>111</td><td>222</td></tr></table>");
//		try {
//			sendMail(str.toString());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	private static final String KEY = "GOODSENDMAILKEY*#%^)889";
	//private static final String LINK = "http://183.60.127.96:28090/goodmail/sendMail.html";
	private static final String LINK = "http://112.90.33.14:28090/goodmail/sendMail.html";
	public static void sendMail(String msg, String [] adds) throws Exception {
		JSONObject obj = new JSONObject();
		//String [] adds = {"503316044@qq.com"};
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date());
		obj.put("title", date + "诺兰报表");
		obj.put("content", msg);
		obj.put("toadress", adds);
		System.out.println("mail content:"+obj.toJSONString());
		String data = DESUtil.desedeEncoder(obj.toJSONString(), KEY);
		
		Map<String,String> param = new HashMap<String,String>();
		//System.out.println(data);
		param.put("data", data);
		String result = doPost(LINK, param);
		System.out.println("sending mail result:"+result);
	}
	
	public static String doPost(String link,Map<String,String> param){
		InputStream in = null;
		PrintWriter out = null;
		try {
			URL url = new URL(link);
			System.out.println(link);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestProperty("Accept", "application/json");
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput(true);
			// 获取URLConnection对象对应的输出流
            out = new PrintWriter(con.getOutputStream());
            // 发送请求参数
            for(String p : param.keySet()){
            	out.print(p+"="+param.get(p)+"&");
            }
            // flush输出流的缓冲
            out.flush();
            out.close();
			try{
				in = con.getInputStream();
			}catch(FileNotFoundException ex){
				return "";
			}
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int i = -1;
			while ((i = in.read()) != -1) {
				baos.write(i);
			}
			in.close();
			con.disconnect();
			String result = baos.toString();
			JSONObject obj = JSON.parseObject(result);
			return obj.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				if(out!=null){
                    out.close();
                }
				if(in != null){
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
