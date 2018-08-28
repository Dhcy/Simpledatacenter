package com.good.plat.datacenter.game.entity;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class SubChannel implements Serializable{
	
	private static final long serialVersionUID = 1647388168875805284L;
	
	public SubChannel(){}
	
	public SubChannel(Integer gameid, Integer channelId, String subchannelId,
			String subId) {
		super();
		this.gameid = gameid;
		this.channelId = channelId;
		this.subchannelId = subchannelId;
		this.subId = subId;
	}

	@Min(-1)
	@Max(Integer.MAX_VALUE)
    private Integer id;

	@NotNull(message="{id.isNull}")
	@Min(1)
	@Max(Integer.MAX_VALUE)
    private Integer gameid;

	@NotNull(message="{id.isNull}")
	@Min(1)
	@Max(Integer.MAX_VALUE)
    private Integer channelId;

	@NotEmpty(message="{id.isNull}")
	@Size(min=1, max=11)
    private String subchannelId;

	@Size(min=1, max=11)
    private String subId;
    
    private Channel channel;
    
    private String channelName;
    private String gameName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGameid() {
        return gameid;
    }

    public void setGameid(Integer gameid) {
        this.gameid = gameid;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getSubchannelId() {
        return subchannelId;
    }

    public void setSubchannelId(String subchannelId) {
        this.subchannelId = subchannelId == null ? null : subchannelId.trim();
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId == null ? null : subId.trim();
    }

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	@Override
	public String toString() {
		return "SubChannel [id=" + id + ", gameid=" + gameid + ", ChannelId="
				+ channelId + ", subchannelId=" + subchannelId + ", subId="
				+ subId + ", Channel=" + channel + ", ChannelName="
				+ channelName + "]";
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	
}