package com.good.plat.datacenter.independ.entity.sj3index;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;
/**
 * 图鉴
 * @ClassName: IllusStat
 * @Description: TODO
 * @author hwj
 * @date 2018-1-11 上午10:03:46
 */
public class IllusStat extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//地区
	private String district;
	//平均激活缘分数量
	private Integer avgLuckCnt;
	//地区总的缘分数量
	private Integer areamaxluckcnt;
	//总的缘分数量
	private Integer sumluckcnt;
	//最高缘分数量
	private Integer maxluckcnt;
	//平均激活缘分完成率（平均激活缘分数量/地区总的缘分数量）
	private Double avgLuckFinishRate;
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public Integer getAvgLuckCnt() {
		return avgLuckCnt;
	}
	public void setAvgLuckCnt(Integer avgLuckCnt) {
		this.avgLuckCnt = avgLuckCnt;
	}
	public Integer getAreamaxluckcnt() {
		return areamaxluckcnt;
	}
	public void setAreamaxluckcnt(Integer areamaxluckcnt) {
		this.areamaxluckcnt = areamaxluckcnt;
	}
	public Integer getMaxluckcnt() {
		return maxluckcnt;
	}
	public void setMaxluckcnt(Integer maxluckcnt) {
		this.maxluckcnt = maxluckcnt;
	}
	public Double getAvgLuckFinishRate() {
		return avgLuckFinishRate;
	}
	public void setAvgLuckFinishRate(Double avgLuckFinishRate) {
		this.avgLuckFinishRate = avgLuckFinishRate;
	}
	public Integer getSumluckcnt() {
		return sumluckcnt;
	}
	public void setSumluckcnt(Integer sumluckcnt) {
		this.sumluckcnt = sumluckcnt;
	}
	
	
}
