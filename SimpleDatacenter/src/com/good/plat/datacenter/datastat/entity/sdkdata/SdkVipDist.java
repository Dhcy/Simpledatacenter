package com.good.plat.datacenter.datastat.entity.sdkdata;

import java.util.Date;

public class SdkVipDist {
    private Integer id;

    private Date statdate;

    private String os;

    private Integer vip;

    private Integer count;

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

    public Integer getVip() {
        return vip;
    }

    public void setVip(Integer vip) {
        this.vip = vip;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}