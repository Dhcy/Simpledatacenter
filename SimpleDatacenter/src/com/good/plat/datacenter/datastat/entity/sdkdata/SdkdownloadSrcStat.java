package com.good.plat.datacenter.datastat.entity.sdkdata;

import java.util.Date;

public class SdkdownloadSrcStat {
    private Integer id;

    private Date statdate;

    private Integer gameid;

    private String os;

    private Integer page_id;

    private String page_name;

    private Integer dl_count;

    private Integer dl_user_count;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStatdate() {
        return statdate;
    }

    public void setStatdate(Date statdate) {
        this.statdate = statdate;
    }

    public Integer getGameid() {
        return gameid;
    }

    public void setGameid(Integer gameid) {
        this.gameid = gameid;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os == null ? null : os.trim();
    }

    public Integer getPage_id() {
        return page_id;
    }

    public void setPage_id(Integer page_id) {
        this.page_id = page_id;
    }

    public String getPage_name() {
        return page_name;
    }

    public void setPage_name(String page_name) {
        this.page_name = page_name == null ? null : page_name.trim();
    }

    public Integer getDl_count() {
        return dl_count;
    }

    public void setDl_count(Integer dl_count) {
        this.dl_count = dl_count;
    }

    public Integer getDl_user_count() {
        return dl_user_count;
    }

    public void setDl_user_count(Integer dl_user_count) {
        this.dl_user_count = dl_user_count;
    }
}