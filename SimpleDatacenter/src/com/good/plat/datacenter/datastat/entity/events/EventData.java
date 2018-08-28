package com.good.plat.datacenter.datastat.entity.events;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

/**
 * @ClassName: EventData
 * @Description: 自定义事件
 * @author dmw
 * @date 2016年3月14日 下午4:40:03
 */
public class EventData extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public EventData() {
	}
	
	//账号数
	private Integer accounts;


	public Integer getAccounts() {
		return accounts;
	}

	public void setAccounts(Integer accounts) {
		this.accounts = accounts;
	}

}
