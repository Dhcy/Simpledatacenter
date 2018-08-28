package com.good.plat.datacenter.datastat.entity.players;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

/**
 * @ClassName: PlayersChurn
 * @Description: 玩家流失
 * @author dmw
 * @date 2016年3月14日 下午1:53:29
 */
public class PlayersChurn extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public PlayersChurn() {
	}
	
	//日期
	//private String statdate;
	
	//账号数(流失总人数)
	private Integer accounts;
	
	//流失率,百分比
	//private Double rate;
	
	//标题,查询类型
	//private String statType;
	
	//活跃总人数
	private Integer sumactvcnt;
	private Integer subtype;

	public Integer getAccounts() {
		return accounts;
	}

	public void setAccounts(Integer accounts) {
		this.accounts = accounts;
	}


	public Integer getSumactvcnt() {
		return sumactvcnt;
	}

	public void setSumactvcnt(Integer sumactvcnt) {
		this.sumactvcnt = sumactvcnt;
	}

	public Integer getSubtype() {
		return subtype;
	}

	public void setSubtype(Integer subtype) {
		this.subtype = subtype;
	}
	
	

}
