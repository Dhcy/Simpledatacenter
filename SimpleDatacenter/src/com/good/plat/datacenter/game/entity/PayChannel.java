package com.good.plat.datacenter.game.entity;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class PayChannel implements Serializable{
	private static final long serialVersionUID = 1076335217576346458L;

	@NotNull(message="{id.isNull}")
	@Min(-1)
	@Max(Integer.MAX_VALUE)
	private Integer id;

	@NotNull(message="{id.isNull}")
	@Min(1)
	@Max(Integer.MAX_VALUE)
    private Integer channelid;

    @NotEmpty(message="{name.isNull}")
	@Size(min=1, max=16, message="{name.lengthError}")
    private String channelName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getChannelid() {
        return channelid;
    }

    public void setChannelid(Integer channelid) {
        this.channelid = channelid;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

	@Override
	public String toString() {
		return "PayChannel [id=" + id + ", channelid=" + channelid
				+ ", channelName=" + channelName + "]";
	}
    
}