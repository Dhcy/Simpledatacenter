package com.good.plat.datacenter.game.entity;

import java.io.Serializable;
import java.util.List;

public class GameChannels implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String channelSimName;
	private String channelName;
	private String channelid;
	private List<SubChannel> subChannels;
	public String getChannelSimName() {
		return channelSimName;
	}
	public void setChannelSimName(String channelSimName) {
		this.channelSimName = channelSimName;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public List<SubChannel> getSubChannels() {
		return subChannels;
	}
	public void setSubChannels(List<SubChannel> subChannels) {
		this.subChannels = subChannels;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getChannelid() {
		return channelid;
	}
	public void setChannelid(String channelid) {
		this.channelid = channelid;
	}
	
}
