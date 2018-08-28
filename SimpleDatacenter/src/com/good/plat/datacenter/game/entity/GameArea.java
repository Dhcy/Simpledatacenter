package com.good.plat.datacenter.game.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

public class GameArea implements Serializable{
	private static final long serialVersionUID = -3245572062378412599L;

	@Min(-1)
	@Max(Integer.MAX_VALUE)
	private Integer id;

	@NotNull(message="{id.isNull}")
	@Min(1)
	@Max(Integer.MAX_VALUE)
    private Integer gameid;

	@NotNull(message="{id.isNull}")
	@Min(-1)
	@Max(Integer.MAX_VALUE)
    private Integer areaid;

	@NotEmpty(message="{name.isNull}")
	@Size(min=1, max=16, message="{name.lengthError}")
    private String name;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date createdate;
    
    private Game game;
    
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
        this.name = name == null ? null : name.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	@Override
	public String toString() {
		return "GameArea [id=" + id + ", gameid=" + gameid + ", areaid="
				+ areaid + ", name=" + name + ", createdate=" + createdate
				+ ", gameName=" + gameName + "]";
	}
	
}