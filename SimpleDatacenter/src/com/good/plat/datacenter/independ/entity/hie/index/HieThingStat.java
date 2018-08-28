package com.good.plat.datacenter.independ.entity.hie.index;

import java.io.Serializable;

public class HieThingStat implements Serializable {
	private String itemname;// 道具名
	private Integer itemcount;// 道具总数
	private Integer count;// 次数
	private Integer personnum;// 人数

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public Integer getItemcount() {
		return itemcount;
	}

	public void setItemcount(Integer itemcount) {
		this.itemcount = itemcount;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getPersonnum() {
		return personnum;
	}

	public void setPersonnum(Integer personnum) {
		this.personnum = personnum;
	}

}
