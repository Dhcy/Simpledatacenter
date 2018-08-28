package com.good.plat.datacenter.datastat.entity.buyplat;

import java.math.BigDecimal;
import java.util.Date;

public class TBLogplatMlchnlDetailStat {
    private Integer id;

    private Date statdate;

    private Integer gameid;

    private Integer termtype;

    private Integer adchannel;

    private Integer subchannel;

    private Integer acvcount;

    private Integer regcount;

    private Integer actregcount;

    private Integer acticount;

    private Integer regucount;

    private Integer logucount;

    private Integer lvl3count;

    private Integer rechgcount;

    private BigDecimal rechgmoney;

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

    public Integer getAdchannel() {
        return adchannel;
    }

    public void setAdchannel(Integer adchannel) {
        this.adchannel = adchannel;
    }

    public Integer getSubchannel() {
        return subchannel;
    }

    public void setSubchannel(Integer subchannel) {
        this.subchannel = subchannel;
    }

    public Integer getAcvcount() {
        return acvcount;
    }

    public void setAcvcount(Integer acvcount) {
        this.acvcount = acvcount;
    }

    public Integer getRegcount() {
        return regcount;
    }

    public void setRegcount(Integer regcount) {
        this.regcount = regcount;
    }

    public Integer getActregcount() {
        return actregcount;
    }

    public void setActregcount(Integer actregcount) {
        this.actregcount = actregcount;
    }

    public Integer getActicount() {
        return acticount;
    }

    public void setActicount(Integer acticount) {
        this.acticount = acticount;
    }

    public Integer getRegucount() {
        return regucount;
    }

    public void setRegucount(Integer regucount) {
        this.regucount = regucount;
    }

    public Integer getLogucount() {
        return logucount;
    }

    public void setLogucount(Integer logucount) {
        this.logucount = logucount;
    }

    public Integer getLvl3count() {
        return lvl3count;
    }

    public void setLvl3count(Integer lvl3count) {
        this.lvl3count = lvl3count;
    }

    public Integer getRechgcount() {
        return rechgcount;
    }

    public void setRechgcount(Integer rechgcount) {
        this.rechgcount = rechgcount;
    }

    public BigDecimal getRechgmoney() {
        return rechgmoney;
    }

    public void setRechgmoney(BigDecimal rechgmoney) {
        this.rechgmoney = rechgmoney;
    }
}