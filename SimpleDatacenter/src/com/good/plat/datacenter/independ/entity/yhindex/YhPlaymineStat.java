package com.good.plat.datacenter.independ.entity.yhindex;

import com.good.plat.datacenter.common.base.BaseEntity;
/**
 * 银河采矿数据监控
 * @ClassName: YhPlaymineStat
 * @Description: TODO
 * @author hwj
 * @date 2018-1-17 上午09:40:46
 */
public class YhPlaymineStat extends BaseEntity {
	private Integer keeptype;
	private Integer keepcount;
	private String keeptypeStr;
	private Integer count;
	private Integer succnt;
	private Integer suctimes;
	private Integer failcnt;
	private Integer failtimes;
	public Integer getKeeptype() {
		return keeptype;
	}
	public void setKeeptype(Integer keeptype) {
		this.keeptype = keeptype;
	}
	public Integer getKeepcount() {
		return keepcount;
	}
	public void setKeepcount(Integer keepcount) {
		this.keepcount = keepcount;
	}
	public String getKeeptypeStr() {
		return keeptypeStr;
	}
	public void setKeeptypeStr(String keeptypeStr) {
		this.keeptypeStr = keeptypeStr;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getSuccnt() {
		return succnt;
	}
	public void setSuccnt(Integer succnt) {
		this.succnt = succnt;
	}
	public Integer getSuctimes() {
		return suctimes;
	}
	public void setSuctimes(Integer suctimes) {
		this.suctimes = suctimes;
	}
	public Integer getFailcnt() {
		return failcnt;
	}
	public void setFailcnt(Integer failcnt) {
		this.failcnt = failcnt;
	}
	public Integer getFailtimes() {
		return failtimes;
	}
	public void setFailtimes(Integer failtimes) {
		this.failtimes = failtimes;
	}
	
}
