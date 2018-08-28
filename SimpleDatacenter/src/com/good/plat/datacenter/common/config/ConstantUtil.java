package com.good.plat.datacenter.common.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConstantUtil {
	public static final String[] newProjectPayChannelList=new String[]{"支付宝","卡充值","银行汇款","mo9支付","银联TCL","19pay","PP钱包","PP钱包SDK版","微信支付","微软支付","googlepay","appstore","Facebook","steam","windows"};
	public static final String[] oldProjectPayChannelList=new String[]{"支付宝","卡充值","银行汇款","小额支付","其他卡","MO9","PP钱包","新支付宝充值","googlepay","appstore","Facebook"};
	public static final String signPayChannel="广州谷得:steam,支付宝,卡充值,银行汇款,mo9支付,银联TCL,19pay,PP钱包,PP钱包SDK版,微信支付,微软支付,googlepay,appstore,Facebook,小额支付,其他卡,MO9,新支付宝充值;谷得传播:Windows,appstore";
	public static Map<String,List<String>> companyChannelmap=new HashMap<String, List<String>>();
	static{
		String[] singPayChanelArr=ConstantUtil.signPayChannel.split(";");
		for(String singPayChanel:singPayChanelArr){
			String signCompany=singPayChanel.split(":")[0];//签约公司
			String payChannel=singPayChanel.split(":")[1];//支付渠道
			String[] payChannelArr=payChannel.split(",");//支付渠道数组
			companyChannelmap.put(signCompany, Arrays.asList(payChannelArr));
	}
 }
}
