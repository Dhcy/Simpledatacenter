package com.good.plat.datacenter.datastat.entity.balanceplat;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DZPTChannelAccounting {
	public static Integer ISINVALID = 0;
	public static Integer ISVALID = 1;
    private Integer id;

    private Integer gameid;

    private Integer channel;

    //private String coopmodel;
    private Integer coopmodel;
    private BigDecimal payrate;

    private BigDecimal finalpayrate;

    private BigDecimal taxrate;

    private BigDecimal sharingrate;

    private Byte gradient;

//    private String settlementtype;
    private Integer settlementtype;

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date contractstartdate;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date constractenddate;

    private Byte ispayrate;

    private Integer currency;
    
    private String coopModelName;
    private String gradientName;
    private String settlementtypeName;
    private String gameName;
    private String channelName;
    private String isFinalPayRate;
    private Integer isvalid;
    private Integer contract_status;
    private String msg;
    //项目名
    private String projectName;
    //签约公司
    private String signCompany;
    
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

    /*public String getCoopmodel() {
        return coopmodel;
    }

    public void setCoopmodel(String coopmodel) {
        this.coopmodel = coopmodel == null ? null : coopmodel.trim();
    }*/

    public BigDecimal getPayrate() {
        return payrate;
    }

    public void setPayrate(BigDecimal payrate) {
        this.payrate = payrate;
    }

    public BigDecimal getFinalpayrate() {
        return finalpayrate;
    }

    public void setFinalpayrate(BigDecimal finalpayrate) {
        this.finalpayrate = finalpayrate;
    }

    public BigDecimal getTaxrate() {
        return taxrate;
    }

    public void setTaxrate(BigDecimal taxrate) {
        this.taxrate = taxrate;
    }

    public BigDecimal getSharingrate() {
        return sharingrate;
    }

    public void setSharingrate(BigDecimal sharingrate) {
        this.sharingrate = sharingrate;
    }

    public Byte getGradient() {
        return gradient;
    }

    public void setGradient(Byte gradient) {
        this.gradient = gradient;
    }

   

    public Integer getCoopmodel() {
		return coopmodel;
	}

	public void setCoopmodel(Integer coopmodel) {
		this.coopmodel = coopmodel;
	}

	public Integer getSettlementtype() {
		return settlementtype;
	}

	public void setSettlementtype(Integer settlementtype) {
		this.settlementtype = settlementtype;
	}

	public Date getContractstartdate() {
        return contractstartdate;
    }

    public void setContractstartdate(Date contractstartdate) {
        this.contractstartdate = contractstartdate;
    }

    public Date getConstractenddate() {
        return constractenddate;
    }

    public void setConstractenddate(Date constractenddate) {
        this.constractenddate = constractenddate;
    }

    public Byte getIspayrate() {
        return ispayrate;
    }

    public void setIspayrate(Byte ispayrate) {
        this.ispayrate = ispayrate;
    }

    public Integer getCurrency() {
        return currency;
    }

    public void setCurrency(Integer currency) {
        this.currency = currency;
    }

	public String getCoopModelName() {
		return coopModelName;
	}

	public void setCoopModelName(String coopModelName) {
		this.coopModelName = coopModelName;
	}

	public String getGradientName() {
		return gradientName;
	}

	public void setGradientName(String gradientName) {
		this.gradientName = gradientName;
	}

	public String getSettlementtypeName() {
		return settlementtypeName;
	}

	public void setSettlementtypeName(String settlementtypeName) {
		this.settlementtypeName = settlementtypeName;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	


	@Override
	public String toString() {
		return "DZPTChannelAccounting [id=" + id + ", gameid=" + gameid + ", channel=" + channel + ", coopmodel="
				+ coopmodel + ", payrate=" + payrate + ", finalpayrate=" + finalpayrate + ", taxrate=" + taxrate
				+ ", sharingrate=" + sharingrate + ", gradient=" + gradient + ", settlementtype=" + settlementtype
				+ ", contractstartdate=" + contractstartdate + ", constractenddate=" + constractenddate + ", ispayrate="
				+ ispayrate + ", currency=" + currency + ", coopModelName=" + coopModelName + ", gradientName="
				+ gradientName + ", settlementtypeName=" + settlementtypeName + ", gameName=" + gameName
				+ ", channelName=" + channelName + ", isFinalPayRate=" + isFinalPayRate + ", isvalid=" + isvalid
				+ ", contract_status=" + contract_status + "]";
	}

	public String getIsFinalPayRate() {
		return isFinalPayRate;
	}

	public void setIsFinalPayRate(String isFinalPayRate) {
		this.isFinalPayRate = isFinalPayRate;
	}

	public Integer getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(Integer isvalid) {
		this.isvalid = isvalid;
	}

	public Integer getContract_status() {
		return contract_status;
	}

	public void setContract_status(Integer contract_status) {
		this.contract_status = contract_status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getSignCompany() {
		return signCompany;
	}

	public void setSignCompany(String signCompany) {
		this.signCompany = signCompany;
	}
	
	
	
}