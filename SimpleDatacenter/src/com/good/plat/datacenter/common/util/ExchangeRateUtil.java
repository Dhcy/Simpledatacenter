package com.good.plat.datacenter.common.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.good.plat.datacenter.common.base.SystemConfig;

public class ExchangeRateUtil {
	
	public static Map<String, Map<Integer, Double>> rateMap = new HashMap<String, Map<Integer,Double>>();
	
	public static double getRateByDate(String date, int currType){
		double result = getRateFromCache(date, currType);
		if(result == 0){
			loadFromNet();
			result = getRateFromCache(date, currType);
		}
		return result;
	}
	
	public static Map<Integer, Double> getAllRateByDate(String date){
		Map<Integer, Double> result = rateMap.get(date);
		if(result == null){
			loadFromNet();
			result = rateMap.get(date);
		}
		return result;
	}
	
	public static double getRateFromCache(String date, int currType){
		double result = 0;
		if(rateMap.get(date) != null){
			result = rateMap.get(date).get(currType);
		}
		return result;
	}
	
	/**
	public static void loadFromNet(){
		/*
		1	人民币	CNY
		2	美元		USD
		3	台币		TWD
		4	日元		JPY
		5	越南盾	VND
		6	印尼盾	IDR
		7	韩币		KRW
		
		rateMap.clear();
		
		Map<Integer, Double> map = new HashMap<Integer, Double>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String [] currencys = {"USD", "TWD", "JPY", "VND", "IDR", "KRW"};
		
		for (int i = 0; i < currencys.length; i++) {
			try{
				Parser parser = new Parser("http://www.freecurrencyrates.com/zh-hans/convert-"+currencys[i]+"-CNY");
				
				NodeFilter filter1 = new TagNameFilter ("div");
				NodeFilter filter2 = new HasAttributeFilter("class", "micro-conversion-calc-source");
				NodeFilter filter = new AndFilter(filter1, filter2);
				NodeList nodes = parser.extractAllNodesThatMatch(filter);
				//System.out.println(nodes.toHtml());
				
				if(nodes!=null) {
					Node textnode = (Node) nodes.elementAt(0);
					double currency = Double.parseDouble(textnode.getNextSibling().getNextSibling().getNextSibling().getText());
					
					map.put(i+2, currency);
					System.out.println(currencys[i]+"对CNY的汇率为:"+currency);
				} else {
					map.put(i+2, 0.0);
				}
           		
				map.put(i+2, 0.0);
			}
			catch( Exception e ) {
				map.put(i+2, 0.0);
				e.printStackTrace();
			}
		}
		
		rateMap.put(sdf.format(new Date()), map);
			
	}
	*/
	
	
	public static void loadFromNet(){
		/*
		1	人民币	CNY
		2	美元		USD
		3	台币		TWD
		4	日元		JPY
		5	越南盾	VND
		6	印尼盾	IDR
		7	韩币		KRW
		*/
		rateMap.clear();
		
		Map<Integer, Double> map = new HashMap<Integer, Double>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String [] currencys = {"USD", "TWD", "JPY", "VND", "IDR", "KRW"};
		//http://apis.baidu.com/apistore/currencyservice/currency?fromCurrency="++"&toCurrency=CNY&amount=1
		//{"errNum":0,"errMsg":"success",
		//"retData":{"date":"09\/06\/2015","time":"01:51pm","fromCurrency":"USD","amount":1,"toCurrency":"CNY","currency":"6.2056","convertedamount":6.2056}}


		for (int i = 0; i < currencys.length; i++) {
			try{
				String httpUrl = "http://apis.baidu.com/apistore/currencyservice/currency?"
						+ "fromCurrency="+currencys[i]+"&toCurrency=CNY&amount=1";
				String result = request(httpUrl);
				System.out.println(result);
				JSONObject json = JSON.parseObject(result);
				if(json.getInteger("errNum") == 300209){//{"errNum":300209,"errMsg":"Service provider response status error"}
					//从配置文件里读取
					System.out.println("从配置文件[currencyRate.properties]中获取汇率");
					
					Double currency = 0.0;
					try{
						currency = Double.valueOf(SystemConfig.getProValue("currencyRate.properties", currencys[i]+"_CNY"));
					}catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println(currencys[i]+"对CNY的汇率为:"+currency);
					map.put(i+2, currency);
				} else if (json.getInteger("errNum") != 0){
					System.err.println("汇率接口调用出错: " + json);
					JSONObject jdata = json.getJSONObject("retData");
					double currency = jdata.getDoubleValue("currency");
					System.out.println(currencys[i]+"对CNY的汇率为:"+currency);
					map.put(i+2, currency);
				} else{
					map.put(i+2, 0.0);
				}
				
			}
			catch( Exception e ) {
				map.put(i+2, 0.0);
				e.printStackTrace();
			}
		}
		
		rateMap.put(sdf.format(new Date()), map);
			
	}
	
	
	public static String request(String httpUrl) {
	    BufferedReader reader = null;
	    String result = null;
	    StringBuffer sbf = new StringBuffer();

	    try {
	        URL url = new URL(httpUrl);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("GET");
	        // 填入apikey到HTTP header
	        connection.setRequestProperty("apikey",  "0845655611da0b41cd013b5a2f4b16e1");
	        connection.connect();
	        InputStream is = connection.getInputStream();
	        reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        String strRead = null;
	        while ((strRead = reader.readLine()) != null) {
	            sbf.append(strRead);
	            sbf.append("\r\n");
	        }
	        reader.close();
	        result = sbf.toString();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    //System.out.println(result);
	    return result;
	}
	
	public static void test1(){
		String httpUrl = "http://apis.baidu.com/apistore/currencyservice/type?";
		String result = request(httpUrl);
		System.out.println(result);
	}
	
	public static void main(String[] args) {
		//loadFromNet();
		test1();
	}
}
