package com.good.plat.datacenter.independ.entity.sj3index;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

/**
 * 日常统计
 * @ClassName: DailySummary
 * @Description: TODO
 * @author hwj
 * @date 2017-3-6 下午01:43:09
 */
public class DailySummary extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//活跃度分数
	private Integer activeScore;
	//达到该分数的玩家数
	private Integer cnt;
	public Integer getActiveScore() {
		return activeScore;
	}
	public void setActiveScore(Integer activeScore) {
		this.activeScore = activeScore;
	}
	public Integer getCnt() {
		return cnt;
	}
	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}
	

}
