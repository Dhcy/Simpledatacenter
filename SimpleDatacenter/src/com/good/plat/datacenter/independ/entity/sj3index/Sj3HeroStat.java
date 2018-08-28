package com.good.plat.datacenter.independ.entity.sj3index;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;
/**
 * 世界3英雄冢
 * @ClassName: Sj3HeroStat
 * @Description: TODO
 * @author hwj
 * @date 2018-1-11 上午10:50:35
 */
public class Sj3HeroStat extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer order;//阶级
	private String difficult;//难度
	//////历史以来
	private Integer opencnt;//开启玩家数（历史以来）
	private Integer actonecnt;//首次通关玩家数（历史以来）
	private Integer count;//总人数
	
	///////按天
	private String levelDifficultStr;//阶级难度
	private Integer actincnt;//进入玩家数
	private Integer actsuccnt;//通关玩家数
	private Integer suctime;//通关总用时（s）
	private Integer starcnt;//通关星数
	private Integer failcnt;//失败人次
	private Integer exitcnt;//中途退出人次
	private Double avgsuctime;//平均通关用时
	private Double avgsucstarcnt;//平均通关星数
	
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public String getDifficult() {
		return difficult;
	}
	public void setDifficult(String difficult) {
		this.difficult = difficult;
	}
	public Integer getOpencnt() {
		return opencnt;
	}
	public void setOpencnt(Integer opencnt) {
		this.opencnt = opencnt;
	}
	public Integer getActonecnt() {
		return actonecnt;
	}
	public void setActonecnt(Integer actonecnt) {
		this.actonecnt = actonecnt;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getLevelDifficultStr() {
		return levelDifficultStr;
	}
	public void setLevelDifficultStr(String levelDifficultStr) {
		this.levelDifficultStr = levelDifficultStr;
	}
	public Integer getActincnt() {
		return actincnt;
	}
	public void setActincnt(Integer actincnt) {
		this.actincnt = actincnt;
	}
	public Integer getActsuccnt() {
		return actsuccnt;
	}
	public void setActsuccnt(Integer actsuccnt) {
		this.actsuccnt = actsuccnt;
	}
	public Integer getSuctime() {
		return suctime;
	}
	public void setSuctime(Integer suctime) {
		this.suctime = suctime;
	}
	public Integer getStarcnt() {
		return starcnt;
	}
	public void setStarcnt(Integer starcnt) {
		this.starcnt = starcnt;
	}
	public Integer getFailcnt() {
		return failcnt;
	}
	public void setFailcnt(Integer failcnt) {
		this.failcnt = failcnt;
	}
	public Integer getExitcnt() {
		return exitcnt;
	}
	public void setExitcnt(Integer exitcnt) {
		this.exitcnt = exitcnt;
	}
	public Double getAvgsuctime() {
		return avgsuctime;
	}
	public void setAvgsuctime(Double avgsuctime) {
		this.avgsuctime = avgsuctime;
	}
	public Double getAvgsucstarcnt() {
		return avgsucstarcnt;
	}
	public void setAvgsucstarcnt(Double avgsucstarcnt) {
		this.avgsucstarcnt = avgsucstarcnt;
	}
	
	

}
