package com.good.plat.datacenter.datastat.entity.sdkdata;

import java.math.BigDecimal;
import java.util.Date;

public class SdkVipStat {
    private Integer id;

    private Date statdate;

    private String os;

    private Integer vip_level;

    private Integer total_vip;

    private BigDecimal total_vip_money;

    private Integer new_vip;

    private BigDecimal new_vip_money;

    private Integer vip_recharge;

    private BigDecimal vip_money;

    private Integer vip_lost;

    private Integer vip_return;

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

    public Integer getVip_level() {
        return vip_level;
    }

    public void setVip_level(Integer vip_level) {
        this.vip_level = vip_level;
    }

    public Integer getTotal_vip() {
        return total_vip;
    }

    public void setTotal_vip(Integer total_vip) {
        this.total_vip = total_vip;
    }

    public BigDecimal getTotal_vip_money() {
        return total_vip_money;
    }

    public void setTotal_vip_money(BigDecimal total_vip_money) {
        this.total_vip_money = total_vip_money;
    }

    public Integer getNew_vip() {
        return new_vip;
    }

    public void setNew_vip(Integer new_vip) {
        this.new_vip = new_vip;
    }

    public BigDecimal getNew_vip_money() {
        return new_vip_money;
    }

    public void setNew_vip_money(BigDecimal new_vip_money) {
        this.new_vip_money = new_vip_money;
    }

    public Integer getVip_recharge() {
        return vip_recharge;
    }

    public void setVip_recharge(Integer vip_recharge) {
        this.vip_recharge = vip_recharge;
    }

    public BigDecimal getVip_money() {
        return vip_money;
    }

    public void setVip_money(BigDecimal vip_money) {
        this.vip_money = vip_money;
    }

    public Integer getVip_lost() {
        return vip_lost;
    }

    public void setVip_lost(Integer vip_lost) {
        this.vip_lost = vip_lost;
    }

    public Integer getVip_return() {
        return vip_return;
    }

    public void setVip_return(Integer vip_return) {
        this.vip_return = vip_return;
    }
}