package com.good.plat.datacenter.datastat.entity.sdkdata;

import java.math.BigDecimal;
import java.util.Date;

public class SdkLoginStat {
    private Integer id;

    private Integer gameid;

    private Date statdate;

    private String way;

    private Integer regsit;

    private Integer new_recharge;

    private BigDecimal new_money;

    private Integer total_login;

    private BigDecimal total_money;

    private Integer total_lost;

    private Integer total_return;

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

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way == null ? null : way.trim();
    }

    public Integer getRegsit() {
        return regsit;
    }

    public void setRegsit(Integer regsit) {
        this.regsit = regsit;
    }

    public Integer getNew_recharge() {
        return new_recharge;
    }

    public void setNew_recharge(Integer new_recharge) {
        this.new_recharge = new_recharge;
    }

    public BigDecimal getNew_money() {
        return new_money;
    }

    public void setNew_money(BigDecimal new_money) {
        this.new_money = new_money;
    }

    public Integer getTotal_login() {
        return total_login;
    }

    public void setTotal_login(Integer total_login) {
        this.total_login = total_login;
    }

    public BigDecimal getTotal_money() {
        return total_money;
    }

    public void setTotal_money(BigDecimal total_money) {
        this.total_money = total_money;
    }

    public Integer getTotal_lost() {
        return total_lost;
    }

    public void setTotal_lost(Integer total_lost) {
        this.total_lost = total_lost;
    }

    public Integer getTotal_return() {
        return total_return;
    }

    public void setTotal_return(Integer total_return) {
        this.total_return = total_return;
    }
}