package com.good.plat.datacenter.datastat.entity.concurrent;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

public class ConcurrentData extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 平均同时在线人数
	 */
	private Integer acu;
	/**
	 * 最高同时在线人数
	 */
	private Integer pcu;
	
	//描述
	private String description;
	//启动人数
	private Integer start_count;
	//当前时段的均值
	private Double avg_time_count;
	
	private Integer hour;
	private String time;
	/**
	 * 时间段凌晨0点至23点在线acu人数
	 */
	private Integer hour0_acu;
	private Integer hour1_acu;
	private Integer hour2_acu;
	private Integer hour3_acu;
	private Integer hour4_acu;
	private Integer hour5_acu;
	private Integer hour6_acu;
	private Integer hour7_acu;
	private Integer hour8_acu;
	private Integer hour9_acu;
	private Integer hour10_acu;
	private Integer hour11_acu;
	private Integer hour12_acu;
	private Integer hour13_acu;
	private Integer hour14_acu;
	private Integer hour15_acu;
	private Integer hour16_acu;
	private Integer hour17_acu;
	private Integer hour18_acu;
	private Integer hour19_acu;
	private Integer hour20_acu;
	private Integer hour21_acu;
	private Integer hour22_acu;
	private Integer hour23_acu;
	
	
	public Integer getAcu() {
		return acu;
	}

	public void setAcu(Integer acu) {
		this.acu = acu;
	}

	public Integer getPcu() {
		return pcu;
	}

	public void setPcu(Integer pcu) {
		this.pcu = pcu;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStart_count() {
		return start_count;
	}

	public void setStart_count(Integer startCount) {
		start_count = startCount;
	}

	public Double getAvg_time_count() {
		return avg_time_count;
	}

	public void setAvg_time_count(Double avgTimeCount) {
		avg_time_count = avgTimeCount;
	}

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getHour0_acu() {
		return hour0_acu;
	}

	public void setHour0_acu(Integer hour0Acu) {
		hour0_acu = hour0Acu;
	}

	public Integer getHour1_acu() {
		return hour1_acu;
	}

	public void setHour1_acu(Integer hour1Acu) {
		hour1_acu = hour1Acu;
	}

	public Integer getHour2_acu() {
		return hour2_acu;
	}

	public void setHour2_acu(Integer hour2Acu) {
		hour2_acu = hour2Acu;
	}

	public Integer getHour3_acu() {
		return hour3_acu;
	}

	public void setHour3_acu(Integer hour3Acu) {
		hour3_acu = hour3Acu;
	}

	public Integer getHour4_acu() {
		return hour4_acu;
	}

	public void setHour4_acu(Integer hour4Acu) {
		hour4_acu = hour4Acu;
	}

	public Integer getHour5_acu() {
		return hour5_acu;
	}

	public void setHour5_acu(Integer hour5Acu) {
		hour5_acu = hour5Acu;
	}

	public Integer getHour6_acu() {
		return hour6_acu;
	}

	public void setHour6_acu(Integer hour6Acu) {
		hour6_acu = hour6Acu;
	}

	public Integer getHour7_acu() {
		return hour7_acu;
	}

	public void setHour7_acu(Integer hour7Acu) {
		hour7_acu = hour7Acu;
	}

	public Integer getHour8_acu() {
		return hour8_acu;
	}

	public void setHour8_acu(Integer hour8Acu) {
		hour8_acu = hour8Acu;
	}

	public Integer getHour9_acu() {
		return hour9_acu;
	}

	public void setHour9_acu(Integer hour9Acu) {
		hour9_acu = hour9Acu;
	}

	public Integer getHour10_acu() {
		return hour10_acu;
	}

	public void setHour10_acu(Integer hour10Acu) {
		hour10_acu = hour10Acu;
	}

	public Integer getHour11_acu() {
		return hour11_acu;
	}

	public void setHour11_acu(Integer hour11Acu) {
		hour11_acu = hour11Acu;
	}

	public Integer getHour12_acu() {
		return hour12_acu;
	}

	public void setHour12_acu(Integer hour12Acu) {
		hour12_acu = hour12Acu;
	}

	public Integer getHour13_acu() {
		return hour13_acu;
	}

	public void setHour13_acu(Integer hour13Acu) {
		hour13_acu = hour13Acu;
	}

	public Integer getHour14_acu() {
		return hour14_acu;
	}

	public void setHour14_acu(Integer hour14Acu) {
		hour14_acu = hour14Acu;
	}

	public Integer getHour15_acu() {
		return hour15_acu;
	}

	public void setHour15_acu(Integer hour15Acu) {
		hour15_acu = hour15Acu;
	}

	public Integer getHour16_acu() {
		return hour16_acu;
	}

	public void setHour16_acu(Integer hour16Acu) {
		hour16_acu = hour16Acu;
	}

	public Integer getHour17_acu() {
		return hour17_acu;
	}

	public void setHour17_acu(Integer hour17Acu) {
		hour17_acu = hour17Acu;
	}

	public Integer getHour18_acu() {
		return hour18_acu;
	}

	public void setHour18_acu(Integer hour18Acu) {
		hour18_acu = hour18Acu;
	}

	public Integer getHour19_acu() {
		return hour19_acu;
	}

	public void setHour19_acu(Integer hour19Acu) {
		hour19_acu = hour19Acu;
	}

	public Integer getHour20_acu() {
		return hour20_acu;
	}

	public void setHour20_acu(Integer hour20Acu) {
		hour20_acu = hour20Acu;
	}

	public Integer getHour21_acu() {
		return hour21_acu;
	}

	public void setHour21_acu(Integer hour21Acu) {
		hour21_acu = hour21Acu;
	}

	public Integer getHour22_acu() {
		return hour22_acu;
	}

	public void setHour22_acu(Integer hour22Acu) {
		hour22_acu = hour22Acu;
	}

	public Integer getHour23_acu() {
		return hour23_acu;
	}

	public void setHour23_acu(Integer hour23Acu) {
		hour23_acu = hour23Acu;
	}
	
	
	

	
	
	
	

}
