package com.good.plat.datacenter.independ.entity.yhindex;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;
/**
 * 银河头像/头框统计
 * @ClassName: YhIframeStat
 * @Description: TODO
 * @author hwj
 * @date 2018-5-21 下午06:24:33
 */
public class YhIframeStat extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer iframeid;//头像ID/头框ID
	private Integer wearActCnt;//佩戴次头像的玩家数
	private Integer unlockActCnt;//解锁头像的玩家数
	private Double wearActCntRate;//佩戴此头像的玩家数量/解锁了此头像的玩家数量
	private Integer count;//区服数量
	public Integer getIframeid() {
		return iframeid;
	}
	public void setIframeid(Integer iframeid) {
		this.iframeid = iframeid;
	}
	public Integer getWearActCnt() {
		return wearActCnt;
	}
	public void setWearActCnt(Integer wearActCnt) {
		this.wearActCnt = wearActCnt;
	}
	public Integer getUnlockActCnt() {
		return unlockActCnt;
	}
	public void setUnlockActCnt(Integer unlockActCnt) {
		this.unlockActCnt = unlockActCnt;
	}
	public Double getWearActCntRate() {
		return wearActCntRate;
	}
	public void setWearActCntRate(Double wearActCntRate) {
		this.wearActCntRate = wearActCntRate;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "YhIframeStat [count=" + count + ", iframeid=" + iframeid
				+ ", unlockActCnt=" + unlockActCnt + ", wearActCnt="
				+ wearActCnt + ", wearActCntRate=" + wearActCntRate + "]";
	}
	

}
