package com.good.plat.datacenter.datastat.entity.levels;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

/**
 * @ClassName: NewPlayerProgress
 * @Description: 新玩家进度
 * @author dmw
 * @date 2016年3月14日 下午4:41:54
 */
public class NewPlayerProgress extends BaseEntity implements Serializable{
	private Integer level;
	private Integer count;
	
	private Integer day0;
	private Integer day1;
	private Integer day2;
	private Integer day3;
	private Integer day4;
	private Integer day5;
	private Integer day6;
	private static final long serialVersionUID = 1L;

	public NewPlayerProgress() {
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getDay0() {
		return day0;
	}

	public void setDay0(Integer day0) {
		this.day0 = day0;
	}

	public Integer getDay1() {
		return day1;
	}

	public void setDay1(Integer day1) {
		this.day1 = day1;
	}

	public Integer getDay2() {
		return day2;
	}

	public void setDay2(Integer day2) {
		this.day2 = day2;
	}

	public Integer getDay3() {
		return day3;
	}

	public void setDay3(Integer day3) {
		this.day3 = day3;
	}

	public Integer getDay4() {
		return day4;
	}

	public void setDay4(Integer day4) {
		this.day4 = day4;
	}

	public Integer getDay5() {
		return day5;
	}

	public void setDay5(Integer day5) {
		this.day5 = day5;
	}

	public Integer getDay6() {
		return day6;
	}

	public void setDay6(Integer day6) {
		this.day6 = day6;
	}
	
}
