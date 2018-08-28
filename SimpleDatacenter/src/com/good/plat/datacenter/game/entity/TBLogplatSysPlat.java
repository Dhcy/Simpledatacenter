package com.good.plat.datacenter.game.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.good.plat.datacenter.login.user.entity.FirstMenu;

public class TBLogplatSysPlat implements Serializable{
    private Integer id;

    private String platname;

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date ctime;

    private Byte state;

    //
    private List<FirstMenu> firstMenuList;
    private List<Project> projectList;
    
    //
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlatname() {
        return platname;
    }

    public void setPlatname(String platname) {
        this.platname = platname == null ? null : platname.trim();
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

	public List<FirstMenu> getFirstMenuList() {
		return firstMenuList;
	}

	public void setFirstMenuList(List<FirstMenu> firstMenuList) {
		this.firstMenuList = firstMenuList;
	}

	public List<Project> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}
}