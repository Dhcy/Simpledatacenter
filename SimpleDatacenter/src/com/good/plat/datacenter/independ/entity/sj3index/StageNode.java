package com.good.plat.datacenter.independ.entity.sj3index;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;
/**
 * 关卡节点
 * @ClassName: StageNode
 * @Description: TODO
 * @author hwj
 * @date 2017-6-16 下午02:10:31
 */
public class StageNode extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer stageId;
	private String stageName;
	private String nodeName;
	private Integer amount;
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
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	

}
