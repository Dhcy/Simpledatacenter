package com.good.plat.datacenter.datastat.entity.analysis;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class LogplatWarningNotice {
    private Integer id;

    private Integer warning_item_id;

    private String title;
    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    private Date notice_datetime;
    
    private Integer status;

    private Integer projectid;

    private Integer gameid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWarning_item_id() {
        return warning_item_id;
    }

    public void setWarning_item_id(Integer warning_item_id) {
        this.warning_item_id = warning_item_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getNotice_datetime() {
        return notice_datetime;
    }

    public void setNotice_datetime(Date notice_datetime) {
        this.notice_datetime = notice_datetime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public Integer getGameid() {
        return gameid;
    }

    public void setGameid(Integer gameid) {
        this.gameid = gameid;
    }
}