package com.good.plat.datacenter.independ.entity.sj3index;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

/**
 * 钻石
 * @ClassName: Diamond
 * @Description: TODO
 * @author hwj
 * @date 2017-2-28 下午03:00:11
 */
public class Diamond extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//获得总钻石
	private Integer getCnt;
	//消耗总钻石
	private Integer comsumeCnt;
	//用途
	private String purpose;
	//消耗数
	private Integer purposeNum;
	//货币类型
	private Integer currtype;
	//货币类型文本
	private String currTypeText;
	public Integer getGetCnt() {
		return getCnt;
	}
	public void setGetCnt(Integer getCnt) {
		this.getCnt = getCnt;
	}
	public Integer getComsumeCnt() {
		return comsumeCnt;
	}
	public void setComsumeCnt(Integer comsumeCnt) {
		this.comsumeCnt = comsumeCnt;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public Integer getPurposeNum() {
		return purposeNum;
	}
	public void setPurposeNum(Integer purposeNum) {
		this.purposeNum = purposeNum;
	}
	public Integer getCurrtype() {
		return currtype;
	}
	public void setCurrtype(Integer currtype) {
		this.currtype = currtype;
	}
	public String getCurrTypeText() {
		return currTypeText;
	}
	public void setCurrTypeText(String currTypeText) {
		this.currTypeText = currTypeText;
	}
	
	
	
	

}
