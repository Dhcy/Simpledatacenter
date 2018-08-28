package com.good.plat.datacenter.datastat.entity.balanceplat;

import java.math.BigDecimal;
import java.util.Date;

public class UscFinacialStatementMonthly {
    private Integer id;

    private Integer gameid;

    private Integer channel;

    private Date statdate;

    private Integer currency;

    private BigDecimal money;

    private BigDecimal payrate;

    private BigDecimal taxrate;

    private BigDecimal sharerate;

    private Integer confcurr;

    private BigDecimal exchrate;

    private BigDecimal rmbrate;

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

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Date getStatdate() {
        return statdate;
    }

    public void setStatdate(Date statdate) {
        this.statdate = statdate;
    }

    public Integer getCurrency() {
        return currency;
    }

    public void setCurrency(Integer currency) {
        this.currency = currency;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getPayrate() {
        return payrate;
    }

    public void setPayrate(BigDecimal payrate) {
        this.payrate = payrate;
    }

    public BigDecimal getTaxrate() {
        return taxrate;
    }

    public void setTaxrate(BigDecimal taxrate) {
        this.taxrate = taxrate;
    }

    public BigDecimal getSharerate() {
        return sharerate;
    }

    public void setSharerate(BigDecimal sharerate) {
        this.sharerate = sharerate;
    }

    public Integer getConfcurr() {
        return confcurr;
    }

    public void setConfcurr(Integer confcurr) {
        this.confcurr = confcurr;
    }

    public BigDecimal getExchrate() {
        return exchrate;
    }

    public void setExchrate(BigDecimal exchrate) {
        this.exchrate = exchrate;
    }

    public BigDecimal getRmbrate() {
        return rmbrate;
    }

    public void setRmbrate(BigDecimal rmbrate) {
        this.rmbrate = rmbrate;
    }
}