package com.good.plat.datacenter.datastat.entity.players;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

/**
 * 账号流失
 * @ClassName: AccountChurn
 * @Description: TODO
 * @author hwj
 * @date 2017-4-26 上午11:07:17
 */
public class AccountChurn extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public AccountChurn() {
	}
	
	
	//账号数(流失总人数)
	private Integer accounts;
	
	//活跃总人数
	private Integer sumactvcnt;
	//流失前等级
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
