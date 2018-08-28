package com.good.plat.datacenter.independ.entity.fwindex;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;
/**
 * 关卡（未来战争）
 * @ClassName: FutureWarStage
 * @Description: TODO
 * @author hwj
 * @date 2017-4-17 下午03:49:33
 */
public class FWStage extends BaseEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//关卡id
	private Integer stageId;
	//关卡名称
	private String stageName;
	//适合玩家
	private Integer eligbCnt;
	//参与玩家
	private Integer enterCnt;
	//完成玩家数
	private Integer succdCnt;
	//完成率
	private Double succdRate;
	//参与次数
	private Integer enterTime;
	//完成次数
	private Integer succdTime;
	public Integer getStageId() {
		return stageId;
	}
	public void setStageId(Integer stageId) {
		this.stageId = stageId;
	}
	public String getStageName() {
		return stageName;
	}
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
	public Integer getEligbCnt() {
		return eligbCnt;
	}
	public void setEligbCnt(Integer eligbCnt) {
		this.eligbCnt = eligbCnt;
	}
	public Integer getEnterCnt() {
		return enterCnt;
	}
	public void setEnterCnt(Integer enterCnt) {
		this.enterCnt = enterCnt;
	}
	public Integer getSuccdCnt() {
		return succdCnt;
	}
	public void setSuccdCnt(Integer succdCnt) {
		this.succdCnt = succdCnt;
	}
	public Double getSuccdRate() {
		return succdRate;
	}
	public void setSuccdRate(Double succdRate) {
		this.succdRate = succdRate;
	}
	public Integer getEnterTime() {
		return enterTime;
	}
	public void setEnterTime(Integer enterTime) {
		this.enterTime = enterTime;
	}
	public Integer getSuccdTime() {
		return succdTime;
	}
	public void setSuccdTime(Integer succdTime) {
		this.succdTime = succdTime;
	}
	

}
