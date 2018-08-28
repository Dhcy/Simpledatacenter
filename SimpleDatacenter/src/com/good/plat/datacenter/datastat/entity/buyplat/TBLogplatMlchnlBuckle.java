package com.good.plat.datacenter.datastat.entity.buyplat;

import java.util.Date;
import java.util.List;

public class TBLogplatMlchnlBuckle extends GameCommonEntity{
    private Integer id;

    private Date startdate;

    private Date enddate;

    private Integer adchannel;

    private Integer gameid;

    private Integer termType;

    private Integer regCount;

    private Double buckleRate;

    private Integer buckleRegCount;

    private Byte cooperationType;

    private Byte state;
    
    private List<MLAdSpace> adspaces;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Integer getAdchannel() {
        return adchannel;
    }

    public void setAdchannel(Integer adchannel) {
        this.adchannel = adchannel;
    }

    public Integer getGameid() {
        return gameid;
    }

    public void setGameid(Integer gameid) {
        this.gameid = gameid;
    }

    public Integer getTermType() {
        return termType;
    }

    public void setTermType(Integer termType) {
        this.termType = termType;
    }

    public Integer getRegCount() {
        return regCount;
    }

    public void setRegCount(Integer regCount) {
        this.regCount = regCount;
    }

    public Double getBuckleRate() {
        return buckleRate;
    }

    public void setBuckleRate(Double buckleRate) {
        this.buckleRate = buckleRate;
    }

    public Integer getBuckleRegCount() {
        return buckleRegCount;
    }

    public void setBuckleRegCount(Integer buckleRegCount) {
        this.buckleRegCount = buckleRegCount;
    }

  

    public Byte getCooperationType() {
		return cooperationType;
	}

	public void setCooperationType(Byte cooperationType) {
		this.cooperationType = cooperationType;
	}

	public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

	public List<MLAdSpace> getAdspaces() {
		return adspaces;
	}

	public void setAdspaces(List<MLAdSpace> adspaces) {
		this.adspaces = adspaces;
	}

	
    
}