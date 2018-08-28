package com.good.plat.datacenter.datastat.entity.project;

import java.io.Serializable;
import java.util.List;

import com.good.plat.datacenter.common.base.BaseEntity;
/**
 * 项目游戏详情
 * @ClassName: ProjectGameDetail
 * @Description: TODO
 * @author hwj
 * @date 2017-3-31 上午09:44:38
 */
public class ProjectGameDetail extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//激活设备数
	private Integer actvCount;
	//注册账号数
	private Integer registerCount;
	//活跃用户数
	private Integer actUser;
	//付费用户数
	private Integer payUser;
	//收入
	private Double income=0.0;
	//arpu
	private Double arpu=0.0;
	//付费次数
	private Integer payTime;
	List<ProjectGameDetail> gameDataList;
	List<ProjectGameDetail> gamePayTimeList;
	//日期
	private String date;
	public Integer getActvCount() {
		return actvCount;
	}
	public void setActvCount(Integer actvCount) {
		this.actvCount = actvCount;
	}
	public Integer getRegisterCount() {
		return registerCount;
	}
	public void setRegisterCount(Integer registerCount) {
		this.registerCount = registerCount;
	}
	public Integer getActUser() {
		return actUser;
	}
	public void setActUser(Integer actUser) {
		this.actUser = actUser;
	}
	public Integer getPayUser() {
		return payUser;
	}
	public void setPayUser(Integer payUser) {
		this.payUser = payUser;
	}
	public Double getIncome() {
		return income;
	}
	public void setIncome(Double income) {
		this.income = income;
	}
	public Double getArpu() {
		return arpu;
	}
	public void setArpu(Double arpu) {
		this.arpu = arpu;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getPayTime() {
		return payTime;
	}
	public void setPayTime(Integer payTime) {
		this.payTime = payTime;
	}
	public List<ProjectGameDetail> getGameDataList() {
		return gameDataList;
	}
	public void setGameDataList(List<ProjectGameDetail> gameDataList) {
		this.gameDataList = gameDataList;
	}
	public List<ProjectGameDetail> getGamePayTimeList() {
		return gamePayTimeList;
	}
	public void setGamePayTimeList(List<ProjectGameDetail> gamePayTimeList) {
		this.gamePayTimeList = gamePayTimeList;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	
	
	
	

}
