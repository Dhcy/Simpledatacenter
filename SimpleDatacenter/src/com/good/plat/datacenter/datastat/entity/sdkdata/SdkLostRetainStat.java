package com.good.plat.datacenter.datastat.entity.sdkdata;

import java.util.Date;

public class SdkLostRetainStat {
    private Integer id;

    private Date statdate;

    private String os;

    private Integer sourceid;

    private String source;

    private Integer total_recharge;

    private Integer new_actor;

    private Integer lost;

    private Integer lost_return;

    private Integer day1;

    private Integer day3;

    private Integer day7;

    private Integer day30;

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

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os == null ? null : os.trim();
    }

    public Integer getSourceid() {
        return sourceid;
    }

    public void setSourceid(Integer sourceid) {
        this.sourceid = sourceid;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public Integer getTotal_recharge() {
        return total_recharge;
    }

    public void setTotal_recharge(Integer total_recharge) {
        this.total_recharge = total_recharge;
    }

    public Integer getNew_actor() {
        return new_actor;
    }

    public void setNew_actor(Integer new_actor) {
        this.new_actor = new_actor;
    }

    public Integer getLost() {
        return lost;
    }

    public void setLost(Integer lost) {
        this.lost = lost;
    }

    public Integer getLost_return() {
        return lost_return;
    }

    public void setLost_return(Integer lost_return) {
        this.lost_return = lost_return;
    }

    public Integer getDay1() {
        return day1;
    }

    public void setDay1(Integer day1) {
        this.day1 = day1;
    }

    public Integer getDay3() {
        return day3;
    }

    public void setDay3(Integer day3) {
        this.day3 = day3;
    }

    public Integer getDay7() {
        return day7;
    }

    public void setDay7(Integer day7) {
        this.day7 = day7;
    }

    public Integer getDay30() {
        return day30;
    }

    public void setDay30(Integer day30) {
        this.day30 = day30;
    }
}