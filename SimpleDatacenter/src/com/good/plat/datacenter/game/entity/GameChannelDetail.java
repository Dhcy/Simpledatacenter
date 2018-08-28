package com.good.plat.datacenter.game.entity;

import java.util.List;

public class GameChannelDetail extends TBLogplatGameChannel {
	private String gameName;
	private String channelName;
	private String channelSimName;
	private List<SubChannel> subChannelList;
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public List<SubChannel> getSubChannelList() {
		return subChannelList;
	}
	public void setSubChannelList(List<SubChannel> subChannelList) {
		this.subChannelList = subChannelList;
	}
	public String getChannelSimName() {
		return channelSimName;
	}
	public void setChannelSimName(String channelSimName) {
		this.channelSimName = channelSimName;
	}
	
	
}
