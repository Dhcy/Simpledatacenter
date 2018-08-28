package com.good.plat.datacenter.game.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Project implements Serializable{
	
	private static final long serialVersionUID = -38617963684075307L;

	@NotNull(message="{id.isNull}")
	@Min(1)
	@Max(Integer.MAX_VALUE)
	private Integer id;

	@NotEmpty(message="{name.isNull}")
	@Size(min=1, max=16, message="{name.lengthError}")
    private String name;

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date createdate;
    
	@Min(1)
	@Max(Integer.MAX_VALUE)
    private Integer platformid;
    private List<Integer> platformids;
    private String platname;
    private List<Game> gameList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /*@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")*/
    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

	public List<Game> getGameList() {
		return gameList;
	}

	public void setGameList(List<Game> gameList) {
		this.gameList = gameList;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", createdate="
				+ createdate + "]";
	}

	public Integer getPlatformid() {
		return platformid;
	}
	
	public void setPlatformid(Integer platformid) {
		this.platformid = platformid;
	}

	public String getPlatname() {
		return platname;
	}

	public void setPlatname(String platname) {
		this.platname = platname;
	}

	public List<Integer> getPlatformids() {
		return platformids;
	}

	public void setPlatformids(List<Integer> platformids) {
		this.platformids = platformids;
	}
	
}