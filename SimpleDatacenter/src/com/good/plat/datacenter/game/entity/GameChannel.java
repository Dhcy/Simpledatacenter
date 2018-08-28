package com.good.plat.datacenter.game.entity;

import java.io.Serializable;
import java.util.List;

public class GameChannel implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public GameChannel() {
		// TODO Auto-generated constructor stub
	}
	
	private Integer gameid;
	
	private List<Channel> channelList;

	public GameChannel(Integer gameid, List<Channel> channelList) {
		super();
		this.gameid = gameid;
		this.channelList = channelList;
	}

	public Integer getGameid() {
		return gameid;
	}

	public void setGameid(Integer gameid) {
		this.gameid = gameid;
	}

	public List<Channel> getChannelList() {
		return channelList;
	}

	public void setChannelList(List<Channel> channelList) {
		this.channelList = channelList;
	}

	@Override
	public String toString() {
		return "GameChannel [gameid=" + gameid + ", channelList=" + channelList
				+ "]";
	}
	
}
