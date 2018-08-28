package com.good.plat.datacenter.common.base;

import java.io.Serializable;

/**
 * 
 * @ClassName: XgFinanceBaseSearchData
 * @Description: TODO
 * @author hwj
 * @date 2017-8-12 上午10:48:10
 */
public class XgFinanceBaseSearchData implements Serializable {

	private static final long serialVersionUID = 2L;

	public XgFinanceBaseSearchData() {
	}

	
	// 开始日期
	private String startdate;
	
	// 结束日期
	private String enddate;
	//游戏
	private String gameName;

	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	
}
