package com.good.plat.datacenter.datastat.entity.daumode;

import java.io.Serializable;
import java.util.List;

import com.good.plat.datacenter.common.base.BaseEntity;

/***
 * dau模型
 * @ClassName: DauMode
 * @Description: TODO
 * @author hwj
 * @date 2017-3-21 下午05:27:18
 */
public class DauMode extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//注册用户数
	private Integer userCnt;
	//付费额
	private Double arpu;
	//付费率
	
	private Double payRate;
	/**预测最大留存率*/
	//1日留存
	private Double maxDay1Rate;
	//2日留存
	private Double maxDay2Rate;
	//3日留存
	private Double maxDay3Rate;
	//4日留存
	private Double maxDay4Rate;
	//5日留存
	private Double maxDay5Rate;
	//6日留存
	private Double maxDay6Rate;
	//7日留存
	private Double maxDay7Rate;
	//14日留存
	private Double maxDay14Rate;
	//30日留存
	private Double maxDay30Rate;

	/**预测最小留存率*/
	//1日留存
	private Double minDay1Rate;
	//2日留存
	private Double minDay2Rate;
	//3日留存
	private Double minDay3Rate;
	//4日留存
	private Double minDay4Rate;
	//5日留存
	private Double minDay5Rate;
	//6日留存
	private Double minDay6Rate;
	//7日留存
	private Double minDay7Rate;
	//14日留存
	private Double minDay14Rate;
	//30日留存
	private Double minDay30Rate;
	//起始日期
	private String startDate;
	//结束日期
	private String endDate;
	//当前日期
	private String currDate;
	/**
	 * DAU预测*/
	//预测最小dau
	private Double min_dau;
	//预测最大dau
	private Double max_dau;
	/**
	 * 预测收入*/
	//预测最小收入
	private Double min_income;
	//预测最大收入
	private Double max_income;
	//衰减值
	private Double decayValue;
	//flag=1:日然用户数据；flag=2：推广用户数据
	private Integer flag;
	private List<DauData> mutilParamList;
	public Integer getUserCnt() {
		return userCnt;
	}
	public void setUserCnt(Integer userCnt) {
		this.userCnt = userCnt;
	}
	public Double getArpu() {
		return arpu;
	}
	public void setArpu(Double arpu) {
		this.arpu = arpu;
	}
	public Double getPayRate() {
		return payRate;
	}
	public void setPayRate(Double payRate) {
		this.payRate = payRate;
	}
	public Double getMaxDay1Rate() {
		return maxDay1Rate;
	}
	public void setMaxDay1Rate(Double maxDay1Rate) {
		this.maxDay1Rate = maxDay1Rate;
	}
	public Double getMaxDay2Rate() {
		return maxDay2Rate;
	}
	public void setMaxDay2Rate(Double maxDay2Rate) {
		this.maxDay2Rate = maxDay2Rate;
	}
	public Double getMaxDay3Rate() {
		return maxDay3Rate;
	}
	public void setMaxDay3Rate(Double maxDay3Rate) {
		this.maxDay3Rate = maxDay3Rate;
	}
	public Double getMaxDay4Rate() {
		return maxDay4Rate;
	}
	public void setMaxDay4Rate(Double maxDay4Rate) {
		this.maxDay4Rate = maxDay4Rate;
	}
	public Double getMaxDay5Rate() {
		return maxDay5Rate;
	}
	public void setMaxDay5Rate(Double maxDay5Rate) {
		this.maxDay5Rate = maxDay5Rate;
	}
	public Double getMaxDay6Rate() {
		return maxDay6Rate;
	}
	public void setMaxDay6Rate(Double maxDay6Rate) {
		this.maxDay6Rate = maxDay6Rate;
	}
	public Double getMaxDay7Rate() {
		return maxDay7Rate;
	}
	public void setMaxDay7Rate(Double maxDay7Rate) {
		this.maxDay7Rate = maxDay7Rate;
	}
	public Double getMaxDay14Rate() {
		return maxDay14Rate;
	}
	public void setMaxDay14Rate(Double maxDay14Rate) {
		this.maxDay14Rate = maxDay14Rate;
	}
	public Double getMaxDay30Rate() {
		return maxDay30Rate;
	}
	public void setMaxDay30Rate(Double maxDay30Rate) {
		this.maxDay30Rate = maxDay30Rate;
	}
	public Double getMinDay1Rate() {
		return minDay1Rate;
	}
	public void setMinDay1Rate(Double minDay1Rate) {
		this.minDay1Rate = minDay1Rate;
	}
	public Double getMinDay2Rate() {
		return minDay2Rate;
	}
	public void setMinDay2Rate(Double minDay2Rate) {
		this.minDay2Rate = minDay2Rate;
	}
	public Double getMinDay3Rate() {
		return minDay3Rate;
	}
	public void setMinDay3Rate(Double minDay3Rate) {
		this.minDay3Rate = minDay3Rate;
	}
	public Double getMinDay4Rate() {
		return minDay4Rate;
	}
	public void setMinDay4Rate(Double minDay4Rate) {
		this.minDay4Rate = minDay4Rate;
	}
	public Double getMinDay5Rate() {
		return minDay5Rate;
	}
	public void setMinDay5Rate(Double minDay5Rate) {
		this.minDay5Rate = minDay5Rate;
	}
	public Double getMinDay6Rate() {
		return minDay6Rate;
	}
	public void setMinDay6Rate(Double minDay6Rate) {
		this.minDay6Rate = minDay6Rate;
	}
	public Double getMinDay7Rate() {
		return minDay7Rate;
	}
	public void setMinDay7Rate(Double minDay7Rate) {
		this.minDay7Rate = minDay7Rate;
	}
	public Double getMinDay14Rate() {
		return minDay14Rate;
	}
	public void setMinDay14Rate(Double minDay14Rate) {
		this.minDay14Rate = minDay14Rate;
	}
	public Double getMinDay30Rate() {
		return minDay30Rate;
	}
	public void setMinDay30Rate(Double minDay30Rate) {
		this.minDay30Rate = minDay30Rate;
	}
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
	public String getCurrDate() {
		return currDate;
	}
	public void setCurrDate(String currDate) {
		this.currDate = currDate;
	}
	
	public Double getMin_dau() {
		return min_dau;
	}
	public void setMin_dau(Double minDau) {
		min_dau = minDau;
	}
	public Double getMax_dau() {
		return max_dau;
	}
	public void setMax_dau(Double maxDau) {
		max_dau = maxDau;
	}
	
	public Double getMin_income() {
		return min_income;
	}
	public void setMin_income(Double minIncome) {
		min_income = minIncome;
	}
	public Double getMax_income() {
		return max_income;
	}
	public void setMax_income(Double maxIncome) {
		max_income = maxIncome;
	}
	public Double getDecayValue() {
		return decayValue;
	}
	public void setDecayValue(Double decayValue) {
		this.decayValue = decayValue;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public List<DauData> getMutilParamList() {
		return mutilParamList;
	}
	public void setMutilParamList(List<DauData> mutilParamList) {
		this.mutilParamList = mutilParamList;
	}
	
	
	
	
	
	
	
}
