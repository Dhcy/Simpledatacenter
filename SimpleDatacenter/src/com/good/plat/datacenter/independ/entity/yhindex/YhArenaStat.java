package com.good.plat.datacenter.independ.entity.yhindex;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;
/**
 * 竞技场统计
 * @ClassName: YhArenaStat
 * @Description: TODO
 * @author hwj
 * @date 2018-5-22 上午11:08:44
 */
public class YhArenaStat extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 段位统计
	 */
	private String dan;//段位
	private Integer count;//玩家数量
	/**
	 * 各分段匹配时长统计
	 */
	private String time;//时间区间段
	private Integer type;//类型（1:成功；2：取消匹配）
	private String typeDesc;//类型描述
	/**
	 * 各时段在线人数统计
	 */
	private Integer hour;//时段
	private String hourDesc;//时段描述
	private Integer jtzCnt;//军团战人数
	private Integer jjcCnt;//竞技场人数
	public String getDan() {
		return dan;
	}
	public void setDan(String dan) {
		this.dan = dan;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	public Integer getHour() {
		return hour;
	}
	public void setHour(Integer hour) {
		this.hour = hour;
	}
	public String getHourDesc() {
		return hourDesc;
	}
	public void setHourDesc(String hourDesc) {
		this.hourDesc = hourDesc;
	}
	public Integer getJtzCnt() {
		return jtzCnt;
	}
	public void setJtzCnt(Integer jtzCnt) {
		this.jtzCnt = jtzCnt;
	}
	public Integer getJjcCnt() {
		return jjcCnt;
	}
	public void setJjcCnt(Integer jjcCnt) {
		this.jjcCnt = jjcCnt;
	}
	@Override
	public String toString() {
		return "YhArenaStat [count=" + count + ", dan=" + dan + ", hour="
				+ hour + ", hourDesc=" + hourDesc + ", jjcCnt=" + jjcCnt
				+ ", jtzCnt=" + jtzCnt + ", time=" + time + ", type=" + type
				+ ", typeDesc=" + typeDesc + "]";
	}
	
	
	
}
