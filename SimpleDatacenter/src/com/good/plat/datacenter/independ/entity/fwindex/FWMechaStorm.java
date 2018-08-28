package com.good.plat.datacenter.independ.entity.fwindex;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

/**
 * 机甲风暴（未来战争-末世突袭）
 * @ClassName: FWMechaStorm
 * @Description: TODO
 * @author hwj
 * @date 2017-6-12 下午02:14:25
 */
public class FWMechaStorm extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer stageId;//关卡id
	private String stageName;//关卡名称
	private Integer finishCnt;//完成关卡数量
	private Integer inCnt;//进入关卡数量
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
	
	public Integer getFinishCnt() {
		return finishCnt;
	}
	public void setFinishCnt(Integer finishCnt) {
		this.finishCnt = finishCnt;
	}
	public Integer getInCnt() {
		return inCnt;
	}
	public void setInCnt(Integer inCnt) {
		this.inCnt = inCnt;
	}
	
}
