package com.good.plat.datacenter.independ.entity.sj3index;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

/**
 * 悬赏统计
 * @ClassName: AwardSummary
 * @Description: TODO
 * @author hwj
 * @date 2017-3-3 下午06:08:37
 */
public class AwardSummary extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String awardName;//悬赏名称
	private Integer getCnt;//领取数
	private Integer giveupCnt;//放弃数
	private Integer succCnt;//完成数
	public String getAwardName() {
		return awardName;
	}
	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}
	public Integer getGetCnt() {
		return getCnt;
	}
	public void setGetCnt(Integer getCnt) {
		this.getCnt = getCnt;
	}
	public Integer getGiveupCnt() {
		return giveupCnt;
	}
	public void setGiveupCnt(Integer giveupCnt) {
		this.giveupCnt = giveupCnt;
	}
	public Integer getSuccCnt() {
		return succCnt;
	}
	public void setSuccCnt(Integer succCnt) {
		this.succCnt = succCnt;
	}
	
	
	

}
