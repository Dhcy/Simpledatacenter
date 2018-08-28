package com.good.plat.datacenter.independ.entity.sj3index;

import com.good.plat.datacenter.common.base.BaseEntity;
/**
 * 世界3月卡统计
 * @ClassName: Sj3MonthCardStat
 * @Description: TODO
 * @author hwj
 * @date 2018-4-3 上午11:45:32
 */
public class Sj3MonthCardStat extends BaseEntity{
	private Integer actCnt;//购买月卡角色数
	private Integer uidCnt;//购买月卡账号数
	private Integer count;//购买月卡次数
	private Double avgCount;//平均购买次数
	public Integer getActCnt() {
		return actCnt;
	}
	public void setActCnt(Integer actCnt) {
		this.actCnt = actCnt;
	}
	public Integer getUidCnt() {
		return uidCnt;
	}
	public void setUidCnt(Integer uidCnt) {
		this.uidCnt = uidCnt;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getAvgCount() {
		return avgCount;
	}
	public void setAvgCount(Double avgCount) {
		this.avgCount = avgCount;
	}
	

}
