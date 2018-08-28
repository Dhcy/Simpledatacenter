package com.good.plat.datacenter.datastat.entity.balanceplat;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UscActorRecharge {
    private Integer id;

    private Integer gameid;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date logdate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date localdate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date clientdate;

    private Integer ucgameid;

    private Integer ucplatid;

    private Integer channel;

    private Integer subchannel;

    private Integer paychannel;

    private BigDecimal money;

    private String serverip;

    private Integer currency;

    private String orderid;

    private String dstorderid;

    public UscActorRecharge() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	public UscActorRecharge(Integer id, Integer gameid, Date logdate, Date localdate, Date clientdate, Integer ucgameid,
			Integer ucplatid, Integer channel, Integer subchannel, Integer paychannel, BigDecimal money,
			String serverip, Integer currency, String orderid, String dstorderid) {
		super();
		this.id = id;
		this.gameid = gameid;
		this.logdate = logdate;
		this.localdate = localdate;
		this.clientdate = clientdate;
		this.ucgameid = ucgameid;
		this.ucplatid = ucplatid;
		this.channel = channel;
		this.subchannel = subchannel;
		this.paychannel = paychannel;
		this.money = money;
		this.serverip = serverip;
		this.currency = currency;
		this.orderid = orderid;
		this.dstorderid = dstorderid;
	}

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

    public Date getLogdate() {
        return logdate;
    }

    public void setLogdate(Date logdate) {
        this.logdate = logdate;
    }

    public Date getLocaldate() {
        return localdate;
    }

    public void setLocaldate(Date localdate) {
        this.localdate = localdate;
    }

    public Date getClientdate() {
        return clientdate;
    }

    public void setClientdate(Date clientdate) {
        this.clientdate = clientdate;
    }

    public Integer getUcgameid() {
        return ucgameid;
    }

    public void setUcgameid(Integer ucgameid) {
        this.ucgameid = ucgameid;
    }

    public Integer getUcplatid() {
        return ucplatid;
    }

    public void setUcplatid(Integer ucplatid) {
        this.ucplatid = ucplatid;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Integer getSubchannel() {
        return subchannel;
    }

    public void setSubchannel(Integer subchannel) {
        this.subchannel = subchannel;
    }

    public Integer getPaychannel() {
        return paychannel;
    }

    public void setPaychannel(Integer paychannel) {
        this.paychannel = paychannel;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getServerip() {
        return serverip;
    }

    public void setServerip(String serverip) {
        this.serverip = serverip == null ? null : serverip.trim();
    }

    public Integer getCurrency() {
        return currency;
    }

    public void setCurrency(Integer currency) {
        this.currency = currency;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public String getDstorderid() {
        return dstorderid;
    }

    public void setDstorderid(String dstorderid) {
        this.dstorderid = dstorderid == null ? null : dstorderid.trim();
    }
}