package com.good.plat.datacenter.independ.entity.hie.index;

import java.io.Serializable;

public class HieLottoStat implements Serializable{

	private Integer channel;// 渠道id
	private String channelSimName;// 渠道名
	private Integer areaid;// 区服id
	private String name;// 区服名
	private String itemname;// 内容
	private String lotterytype;// 类型
	private Integer itemcount;// 数量

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public String getChannelSimName() {
		return channelSimName;
	}

	public void setChannelSimName(String channelSimName) {
		this.channelSimName = channelSimName;
	}

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public String getLotterytype() {
		return lotterytype;
	}

	public void setLotterytype(String lotterytype) {
		this.lotterytype = lotterytype;
	}

	public Integer getItemcount() {
		return itemcount;
	}

	public void setItemcount(Integer itemcount) {
		this.itemcount = itemcount;
	}

}
