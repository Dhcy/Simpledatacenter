package com.good.plat.datacenter.independ.entity.fwindex;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;
/**
 * 序章统计
 * @ClassName: FWPrologue
 * @Description: TODO
 * @author hwj
 * @date 2017-4-18 下午05:47:54
 */
public class FWPrologue extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//人数
	private Integer account;
	public Integer getAccount() {
		return account;
	}
	public void setAccount(Integer account) {
		this.account = account;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
