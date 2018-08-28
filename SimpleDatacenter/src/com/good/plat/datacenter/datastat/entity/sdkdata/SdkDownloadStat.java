package com.good.plat.datacenter.datastat.entity.sdkdata;

import java.math.BigDecimal;
import java.util.Date;

public class SdkDownloadStat {
    private Integer id;

    private Integer gameid;

    private Date statdate;

    private String os;

    private Integer dl_count;

    private Integer dl_user_count;

    private Integer cross_login;

    private Integer new_actor;

    private BigDecimal new_money;

    private Integer new_recharge;

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

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os == null ? null : os.trim();
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

    public Integer getCross_login() {
        return cross_login;
    }

    public void setCross_login(Integer cross_login) {
        this.cross_login = cross_login;
    }

    public Integer getNew_actor() {
        return new_actor;
    }

    public void setNew_actor(Integer new_actor) {
        this.new_actor = new_actor;
    }

    public BigDecimal getNew_money() {
        return new_money;
    }

    public void setNew_money(BigDecimal new_money) {
        this.new_money = new_money;
    }

    public Integer getNew_recharge() {
        return new_recharge;
    }

    public void setNew_recharge(Integer new_recharge) {
        this.new_recharge = new_recharge;
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