package com.good.plat.datacenter.datastat.entity.buyplat;

import java.util.Date;

public class TBLogplatMlchnlRetention {
    private Integer id;

    private Date statdate;

    private Integer gameid;

    private Integer termtype;

    private Integer day2count;

    private Integer day3count;

    private Integer day7count;

    private Integer day15count;

    private Integer day30count;

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

    public Integer getTermtype() {
        return termtype;
    }

    public void setTermtype(Integer termtype) {
        this.termtype = termtype;
    }

    public Integer getDay2count() {
        return day2count;
    }

    public void setDay2count(Integer day2count) {
        this.day2count = day2count;
    }

    public Integer getDay3count() {
        return day3count;
    }

    public void setDay3count(Integer day3count) {
        this.day3count = day3count;
    }

    public Integer getDay7count() {
        return day7count;
    }

    public void setDay7count(Integer day7count) {
        this.day7count = day7count;
    }

    public Integer getDay15count() {
        return day15count;
    }

    public void setDay15count(Integer day15count) {
        this.day15count = day15count;
    }

    public Integer getDay30count() {
        return day30count;
    }

    public void setDay30count(Integer day30count) {
        this.day30count = day30count;
    }
}