package com.good.plat.datacenter.independ.entity.sj3index;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

/**
 * 金币
 * @ClassName: Achieve
 * @Description: TODO
 * @author hwj
 * @date 2017-2-23 上午09:59:31
 */
public class Gold extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 金币
	 */
	//角色名
	private String actorName;
	//等级
	private Integer lvl;
	//库存金库
	private Integer left_gold;
	
	public String getActorName() {
		return actorName;
	}
	public void setActorName(String actorName) {
		this.actorName = actorName;
	}
	public Integer getLvl() {
		return lvl;
	}
	public void setLvl(Integer lvl) {
		this.lvl = lvl;
	}
	public Integer getLeft_gold() {
		return left_gold;
	}
	public void setLeft_gold(Integer leftGold) {
		left_gold = leftGold;
	}
	
	

}
