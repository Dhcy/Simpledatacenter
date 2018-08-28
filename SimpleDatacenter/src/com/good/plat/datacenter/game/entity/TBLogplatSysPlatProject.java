package com.good.plat.datacenter.game.entity;

public class TBLogplatSysPlatProject {
    private Integer id;

    private Integer platid;

    private Integer projectid;

    private Byte state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlatid() {
        return platid;
    }

    public void setPlatid(Integer platid) {
        this.platid = platid;
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }
}