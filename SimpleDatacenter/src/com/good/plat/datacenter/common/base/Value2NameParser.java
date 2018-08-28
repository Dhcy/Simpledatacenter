package com.good.plat.datacenter.common.base;

public class Value2NameParser {
	public static String parseCurrency(Integer currency){
		String text = null;
		if(currency == null){
			return null;
		}
		switch(currency){
		case 1:
			text = "人民币";
			break;
		case 2:
			text = "美元";
			break;
		case 3:
			text = "台币";
			break;
		case 4:
			text = "日元";
			break;
		case 5:
			text = "越南盾";
			break;
		case 6:
			text = "印尼盾";
			break;
		case 7:
			text = "韩元";
			break;
		case 8:
			text = "港币";
			break;
		case 9:
			text = "昆仑币";
			break;
		case 10:
			text = "马来币";
			break;
		case 11:
			text = "新加坡币";
			break;
		}
		return text;
	}
	
	public static String parseOS(Integer os){
		String text = null;
		if(os == null){
			return null;
		}
		switch(os){
		case 1:
			text = "IOS";
			break;
		case 2:
			text = "Android";
			break;
		case 3:
			text = "WP";
			break;
		}
		return text;
	}
}
