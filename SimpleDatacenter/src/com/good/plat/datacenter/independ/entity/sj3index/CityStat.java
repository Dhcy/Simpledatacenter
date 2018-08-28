package com.good.plat.datacenter.independ.entity.sj3index;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

/**
 * 城市争夺
 * @ClassName: CityStat
 * @Description: TODO
 * @author hwj
 * @date 2017-3-6 下午03:02:16
 */
public class CityStat extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer yymgCnt;
	private Integer ymzyCnt;
	private Integer lmshCnt;
	
	//战斗局数
	private Integer fightCnt;
	//参与玩家数
	private Integer actorCnt;
	//付费复活数
	private Integer reliveCnt;
	//战斗总时长/每局战斗时长
	private Integer fightTime;
	public Integer getYymgCnt() {
		return yymgCnt;
	}
	public void setYymgCnt(Integer yymgCnt) {
		this.yymgCnt = yymgCnt;
	}
	public Integer getYmzyCnt() {
		return ymzyCnt;
	}
	public void setYmzyCnt(Integer ymzyCnt) {
		this.ymzyCnt = ymzyCnt;
	}
	public Integer getLmshCnt() {
		return lmshCnt;
	}
	public void setLmshCnt(Integer lmshCnt) {
		this.lmshCnt = lmshCnt;
	}
	public Integer getFightCnt() {
		return fightCnt;
	}
	public void setFightCnt(Integer fightCnt) {
		this.fightCnt = fightCnt;
	}
	public Integer getActorCnt() {
		return actorCnt;
	}
	public void setActorCnt(Integer actorCnt) {
		this.actorCnt = actorCnt;
	}
	public Integer getReliveCnt() {
		return reliveCnt;
	}
	public void setReliveCnt(Integer reliveCnt) {
		this.reliveCnt = reliveCnt;
	}
	public Integer getFightTime() {
		return fightTime;
	}
	public void setFightTime(Integer fightTime) {
		this.fightTime = fightTime;
	}
	
	

}
