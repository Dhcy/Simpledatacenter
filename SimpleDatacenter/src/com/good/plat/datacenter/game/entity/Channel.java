package com.good.plat.datacenter.game.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Channel implements Serializable{
	
	private static final long serialVersionUID = -281746716627797778L;

	@Min(-1)
	@Max(Integer.MAX_VALUE)
    private Integer id;

//    @NotNull(message="{id.isNull}")
//	@Min(-1)
//	@Max(Integer.MAX_VALUE)
//    private Integer channelid;

    @NotEmpty(message="{name.isNull}")
	@Size(min=1, max=32, message="{name.lengthError}")
    //公司简称
    private String channelSimName;

    @NotEmpty(message="{name.isNull}")
	@Size(min=1, max=64, message="{name.lengthError}")
    //公司全称
    private String channelName;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createdate;
    
    private List<SubChannel> subChannelList;
    
    //List<String> 
    
    private String[] subIDs;
    
    private String[] subChannels;
    
    @Min(-1)
	@Max(Integer.MAX_VALUE)
    private Integer gameid;
    
    private Integer userid;
    
    private String userName;
    
    public Channel() {}
    
	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChannelSimName() {
        return channelSimName;
    }

    public void setChannelSimName(String channelSimName) {
        this.channelSimName = channelSimName == null ? null : channelSimName.trim();
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

	public List<SubChannel> getSubChannelList() {
		return subChannelList;
	}

	public void setSubChannelList(List<SubChannel> subChannelList) {
		this.subChannelList = subChannelList;
	}

	public Integer getGameid() {
		return gameid;
	}

	public void setGameid(Integer gameid) {
		this.gameid = gameid;
	}
	
	public String[] getSubChannels() {
		return subChannels;
	}

	public void setSubChannels(String[] subChannels) {
		this.subChannels = subChannels;
	}
	
	public String[] getSubIDs() {
		return subIDs;
	}

	public void setSubIDs(String[] subIDs) {
		this.subIDs = subIDs;
	}
	
	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "Channel [id=" + id + ", channelSimName=" + channelSimName
				+ ", channelName=" + channelName + ", createdate=" + createdate
				+ ", subChannelList=" + subChannelList + ", gameid=" + gameid
				+ "]";
	}

}