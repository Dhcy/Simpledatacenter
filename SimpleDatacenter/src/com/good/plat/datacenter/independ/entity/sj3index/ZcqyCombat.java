package com.good.plat.datacenter.independ.entity.sj3index;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;
/**
 * 主城奇缘
 * @ClassName: ZcqyCombat
 * @Description: TODO
 * @author hwj
 * @date 2017-9-22 下午03:13:56
 */
public class ZcqyCombat extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String country;//国家
	private Integer openCombatCnt;//开启战斗数
	private Integer avgCombatTime;//平均战斗用时(s)
	private Integer gamerCnt;//参与玩家数
	private Integer successCnt;//胜利人数
	private Integer failCnt;//失败人数
	private Integer type;//-1：全部1：送信2:寻物3：开灯4：维修5：护送'
	private String playtype;//玩法类型
	private String avgCombatTimeStr;//平均战斗用时
	private String playName;//玩法名称
	//职业名称
	private String jobs;
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Integer getOpenCombatCnt() {
		return openCombatCnt;
	}
	public void setOpenCombatCnt(Integer openCombatCnt) {
		this.openCombatCnt = openCombatCnt;
	}
	public Integer getAvgCombatTime() {
		return avgCombatTime;
	}
	public void setAvgCombatTime(Integer avgCombatTime) {
		this.avgCombatTime = avgCombatTime;
	}
	public Integer getGamerCnt() {
		return gamerCnt;
	}
	public void setGamerCnt(Integer gamerCnt) {
		this.gamerCnt = gamerCnt;
	}
	public Integer getSuccessCnt() {
		return successCnt;
	}
	public void setSuccessCnt(Integer successCnt) {
		this.successCnt = successCnt;
	}
	public Integer getFailCnt() {
		return failCnt;
	}
	public void setFailCnt(Integer failCnt) {
		this.failCnt = failCnt;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getPlaytype() {
		return playtype;
	}
	public void setPlaytype(String playtype) {
		this.playtype = playtype;
	}
	public String getAvgCombatTimeStr() {
		return avgCombatTimeStr;
	}
	public void setAvgCombatTimeStr(String avgCombatTimeStr) {
		this.avgCombatTimeStr = avgCombatTimeStr;
	}
	public String getPlayName() {
		return playName;
	}
	public void setPlayName(String playName) {
		this.playName = playName;
	}
	public String getJobs() {
		return jobs;
	}
	public void setJobs(String jobs) {
		this.jobs = jobs;
	}
	
	
	
	
	

}
