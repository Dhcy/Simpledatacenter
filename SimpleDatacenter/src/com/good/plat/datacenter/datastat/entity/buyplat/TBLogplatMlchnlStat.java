package com.good.plat.datacenter.datastat.entity.buyplat;

import java.math.BigDecimal;
import java.util.Date;

public class TBLogplatMlchnlStat {
    private Integer id;

    private Date statdate;

    private Integer gameid;

    private Integer termtype;

    private Integer acvcount;

    private Integer regcount;

    private Integer logcount;

    private Integer acticount;

    private Integer lvl3count;

    private Integer rechgcount;

    private BigDecimal rechgmoney;

    private Integer oldcount;

    private BigDecimal adcost;

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

    public Integer getLogcount() {
        return logcount;
    }

    public void setLogcount(Integer logcount) {
        this.logcount = logcount;
    }

    public Integer getActicount() {
        return acticount;
    }

    public void setActicount(Integer acticount) {
        this.acticount = acticount;
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

    public Integer getOldcount() {
        return oldcount;
    }

    public void setOldcount(Integer oldcount) {
        this.oldcount = oldcount;
    }

    public BigDecimal getAdcost() {
        return adcost;
    }

    public void setAdcost(BigDecimal adcost) {
        this.adcost = adcost;
    }
}