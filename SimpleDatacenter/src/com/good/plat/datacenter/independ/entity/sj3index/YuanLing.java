package com.good.plat.datacenter.independ.entity.sj3index;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

/**
 * 元灵
 * @ClassName: YuanLing
 * @Description: TODO
 * @author hwj
 * @date 2017-2-28 下午03:59:00
 */
public class YuanLing extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	//元灵名称
	private String ylName;
	//装备元灵玩家人数
	private Integer ylUserCnt;
	//二十连抽次数
	private Integer slcCnt;
	//元灵等级
	private String ylLevel;
	//玩家等级
	private Integer actorLevel;
	//元灵等级玩家数量
	private Integer ylLevelCnt;
	//第1个杯子的连抽次数
	private Integer cup1Cnt;
	//第2个杯子的连抽次数
	private Integer cup2Cnt;
	//第3个杯子的连抽次数
	private Integer cup3Cnt;
	//第4个杯子的连抽次数
	private Integer cup4Cnt;
	//第5个杯子的连抽次数
	private Integer cup5Cnt;
	public String getYlName() {
		return ylName;
	}
	public void setYlName(String ylName) {
		this.ylName = ylName;
	}
	public Integer getYlUserCnt() {
		return ylUserCnt;
	}
	public void setYlUserCnt(Integer ylUserCnt) {
		this.ylUserCnt = ylUserCnt;
	}
	public Integer getSlcCnt() {
		return slcCnt;
	}
	public void setSlcCnt(Integer slcCnt) {
		this.slcCnt = slcCnt;
	}
	public String getYlLevel() {
		return ylLevel;
	}
	public void setYlLevel(String ylLevel) {
		this.ylLevel = ylLevel;
	}
	public Integer getYlLevelCnt() {
		return ylLevelCnt;
	}
	public void setYlLevelCnt(Integer ylLevelCnt) {
		this.ylLevelCnt = ylLevelCnt;
	}
	public Integer getCup1Cnt() {
		return cup1Cnt;
	}
	public void setCup1Cnt(Integer cup1Cnt) {
		this.cup1Cnt = cup1Cnt;
	}
	public Integer getCup2Cnt() {
		return cup2Cnt;
	}
	public void setCup2Cnt(Integer cup2Cnt) {
		this.cup2Cnt = cup2Cnt;
	}
	public Integer getCup3Cnt() {
		return cup3Cnt;
	}
	public void setCup3Cnt(Integer cup3Cnt) {
		this.cup3Cnt = cup3Cnt;
	}
	public Integer getCup4Cnt() {
		return cup4Cnt;
	}
	public void setCup4Cnt(Integer cup4Cnt) {
		this.cup4Cnt = cup4Cnt;
	}
	public Integer getCup5Cnt() {
		return cup5Cnt;
	}
	public void setCup5Cnt(Integer cup5Cnt) {
		this.cup5Cnt = cup5Cnt;
	}
	public Integer getActorLevel() {
		return actorLevel;
	}
	public void setActorLevel(Integer actorLevel) {
		this.actorLevel = actorLevel;
	}
	
	
	
	

}
