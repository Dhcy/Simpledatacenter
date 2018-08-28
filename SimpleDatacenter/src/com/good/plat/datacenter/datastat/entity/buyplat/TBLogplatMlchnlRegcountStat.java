package com.good.plat.datacenter.datastat.entity.buyplat;

import java.util.Date;

public class TBLogplatMlchnlRegcountStat extends GameCommonEntity{
    private Integer id;

    private Date statdate;

    private Integer gameid;

    private Integer termtype;

    private Integer adchannel;

    private String subchannel;

    private Integer type;

    private String subtype;

    private Integer count;
    //
    private String registerTime;
    private String createActorTime;
    private String networkingWay;
    private String registerIP;
    private String city;
    private String deviceType;
    private String sysVersion;
    private String IMEI;
    private String deviceid;
    private String macAddress;
    
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

   

    public String getSubchannel() {
		return subchannel;
	}

	public void setSubchannel(String subchannel) {
		this.subchannel = subchannel;
	}

	public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype == null ? null : subtype.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public String getCreateActorTime() {
		return createActorTime;
	}

	public void setCreateActorTime(String createActorTime) {
		this.createActorTime = createActorTime;
	}

	public String getNetworkingWay() {
		return networkingWay;
	}

	public void setNetworkingWay(String networkingWay) {
		this.networkingWay = networkingWay;
	}

	public String getRegisterIP() {
		return registerIP;
	}

	public void setRegisterIP(String registerIP) {
		this.registerIP = registerIP;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getSysVersion() {
		return sysVersion;
	}

	public void setSysVersion(String sysVersion) {
		this.sysVersion = sysVersion;
	}

	public String getIMEI() {
		return IMEI;
	}

	public void setIMEI(String iMEI) {
		IMEI = iMEI;
	}

	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
    
}