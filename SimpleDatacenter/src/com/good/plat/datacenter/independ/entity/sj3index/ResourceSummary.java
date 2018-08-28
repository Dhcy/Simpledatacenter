package com.good.plat.datacenter.independ.entity.sj3index;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

/**
 * 资源统计
 * @ClassName: ResourceSummary
 * @Description: TODO
 * @author hwj
 * @date 2017-3-6 上午10:42:11
 */
public class ResourceSummary extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//类型名
	private String typeName;
	//种类名
	private String purposeName;
	//人数
	private Integer cnt;
	//次数
	private Integer time;
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getPurposeName() {
		return purposeName;
	}
	public void setPurposeName(String purposeName) {
		this.purposeName = purposeName;
	}
	public Integer getCnt() {
		return cnt;
	}
	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}
	public Integer getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	
	

}
