package com.good.plat.datacenter.independ.entity.sj3index;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;
/**
 * 世界3许愿
 * @ClassName: Sj3VowStat
 * @Description: TODO
 * @author hwj
 * @date 2018-1-11 下午01:37:35
 */
public class Sj3VowStat extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String resource;
	private Integer actorcnt;
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public Integer getActorcnt() {
		return actorcnt;
	}
	public void setActorcnt(Integer actorcnt) {
		this.actorcnt = actorcnt;
	}
	

}
