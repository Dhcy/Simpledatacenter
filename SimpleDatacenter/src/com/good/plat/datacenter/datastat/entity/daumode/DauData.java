package com.good.plat.datacenter.datastat.entity.daumode;

import java.io.Serializable;

/**
 * 推广用户数据
 * @ClassName: DauData
 * @Description: TODO
 * @author hwj
 * @date 2017-3-28 下午02:17:52
 */
public class DauData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//起始日期
	private String startDate;
	//结束日期
	private String endDate;
	//用户数
	private Integer userCnt;
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Integer getUserCnt() {
		return userCnt;
	}
	public void setUserCnt(Integer userCnt) {
		this.userCnt = userCnt;
	}
	
	

}
