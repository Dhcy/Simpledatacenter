package com.good.plat.datacenter.common.base;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * 独立平台基础数据查询
 * @ClassName: IndependBaseSearchData
 * @Description: TODOf
 * @author hwj
 * @date 2017-6-16 下午02:46:22
 */
public class IndependBaseSearchData extends BaseSearchData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer stageId;//关卡id
	private String stageName;//关卡名称
	private Integer jtId;//军团id
	private String country;//国家
	private String checktype4;//查找类型
	@Min(1)
	@Max(99)
    private Integer minLevel;
	@Min(1)
	@Max(99)
    private Integer maxLevel;
	
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
	public Integer getJtId() {
		return jtId;
	}
	public void setJtId(Integer jtId) {
		this.jtId = jtId;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getChecktype4() {
		return checktype4;
	}
	public void setChecktype4(String checktype4) {
		this.checktype4 = checktype4;
	}
	public Integer getMinLevel() {
		return minLevel;
	}
	public void setMinLevel(Integer minLevel) {
		this.minLevel = minLevel;
	}
	public Integer getMaxLevel() {
		return maxLevel;
	}
	public void setMaxLevel(Integer maxLevel) {
		this.maxLevel = maxLevel;
	}
	
	
	
	
	
	
}
