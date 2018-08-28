package com.good.plat.datacenter.independ.entity.sj3index;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;
/**
 * 世界3时装
 * @ClassName: Sj3ModelStat
 * @Description: TODO
 * @author hwj
 * @date 2018-1-11 下午02:07:49
 */
public class Sj3ModelStat extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer modelid;//时装id
	private String period;//有效期
	private Integer actorcnt;//玩家数
	public Integer getModelid() {
		return modelid;
	}
	public void setModelid(Integer modelid) {
		this.modelid = modelid;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public Integer getActorcnt() {
		return actorcnt;
	}
	public void setActorcnt(Integer actorcnt) {
		this.actorcnt = actorcnt;
	}

}
