package com.good.plat.datacenter.datastat.entity.sdkdata;

import java.util.Date;

public class SdkPageView {
    private Integer id;

    private Integer gameid;

    private Date statdate;

    private Integer category_id;

    private String category_name;

    private Integer page_id;

    private String page_name;

    private Integer pv;

    private Integer uv;

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

    public Date getStatdate() {
        return statdate;
    }

    public void setStatdate(Date statdate) {
        this.statdate = statdate;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name == null ? null : category_name.trim();
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

    public Integer getPv() {
        return pv;
    }

    public void setPv(Integer pv) {
        this.pv = pv;
    }

    public Integer getUv() {
        return uv;
    }

    public void setUv(Integer uv) {
        this.uv = uv;
    }
}