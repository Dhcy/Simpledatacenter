package com.good.plat.datacenter.datastat.entity.buyplat;

import java.util.Date;

public class TBLogplatMlchnlActlvlStat extends GameCommonEntity{
    private Integer id;

    private Date statdate;

    private Integer gameid;

    private Integer termtype;

    private Integer adchannel;

    private String subchannel;

    private Integer actlvl;

    private Integer usercount;
    //
    private Integer actlvl_1_2;

	private Double actlvl_1_2_rate;
	private Integer actlvl_3_6;
	private Double actlvl_3_6_rate;
	private Integer actlvl_7_15;
	private Double actlvl_7_15_rate;
	private Integer actlvl_16_20;
	private Double actlvl_16_20_rate;
	private Integer actlvl_21_25;
	private Double actlvl_21_25_rate;
	private Integer actlvl_26_30;
	private Double actlvl_26_30_rate;
	private Integer actlvl_31_X;
	private Double actlvl_31_X_rate;
	//
	private Integer acticount;
	private Integer regcount;
	//
	private Integer actlvl_3_10;
	private Integer actlvl_11_20;
	private Integer actlvl_21_30;
	private Integer actlvl_31_40;
	private Integer actlvl_41_50;
	private Integer actlvl_51_X;
	private Double actlvl_3_10_rate;
	private Double actlvl_11_20_rate;
	private Double actlvl_21_30_rate;
	private Double actlvl_31_40_rate;
	private Double actlvl_41_50_rate;
	private Double actlvl_51_X_rate;
	//
    public Integer getActicount() {
		return acticount;
	}

	public void setActicount(Integer acticount) {
		this.acticount = acticount;
	}
	
	public Integer getActlvl_3_10() {
		return actlvl_3_10;
	}

	public void setActlvl_3_10(Integer actlvl_3_10) {
		this.actlvl_3_10 = actlvl_3_10;
	}

	public Integer getActlvl_11_20() {
		return actlvl_11_20;
	}

	public void setActlvl_11_20(Integer actlvl_11_20) {
		this.actlvl_11_20 = actlvl_11_20;
	}

	public Integer getActlvl_21_30() {
		return actlvl_21_30;
	}

	public void setActlvl_21_30(Integer actlvl_21_30) {
		this.actlvl_21_30 = actlvl_21_30;
	}

	public Integer getActlvl_31_40() {
		return actlvl_31_40;
	}

	public void setActlvl_31_40(Integer actlvl_31_40) {
		this.actlvl_31_40 = actlvl_31_40;
	}

	public Integer getActlvl_41_50() {
		return actlvl_41_50;
	}

	public void setActlvl_41_50(Integer actlvl_41_50) {
		this.actlvl_41_50 = actlvl_41_50;
	}

	public Integer getActlvl_51_X() {
		return actlvl_51_X;
	}

	public void setActlvl_51_X(Integer actlvl_51_X) {
		this.actlvl_51_X = actlvl_51_X;
	}

	public Integer getRegcount() {
		return regcount;
	}

	public void setRegcount(Integer regcount) {
		this.regcount = regcount;
	}

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

	public Integer getActlvl() {
        return actlvl;
    }

    public void setActlvl(Integer actlvl) {
        this.actlvl = actlvl;
    }

    public Integer getUsercount() {
        return usercount;
    }

    public void setUsercount(Integer usercount) {
        this.usercount = usercount;
    }

	public Integer getActlvl_1_2() {
		return actlvl_1_2;
	}

	public void setActlvl_1_2(Integer actlvl_1_2) {
		this.actlvl_1_2 = actlvl_1_2;
	}

	public Double getActlvl_1_2_rate() {
		return actlvl_1_2_rate;
	}

	public void setActlvl_1_2_rate(Double actlvl_1_2_rate) {
		this.actlvl_1_2_rate = actlvl_1_2_rate;
	}

	public Integer getActlvl_3_6() {
		return actlvl_3_6;
	}

	public void setActlvl_3_6(Integer actlvl_3_6) {
		this.actlvl_3_6 = actlvl_3_6;
	}

	public Double getActlvl_3_6_rate() {
		return actlvl_3_6_rate;
	}

	public void setActlvl_3_6_rate(Double actlvl_3_6_rate) {
		this.actlvl_3_6_rate = actlvl_3_6_rate;
	}

	public Integer getActlvl_7_15() {
		return actlvl_7_15;
	}

	public void setActlvl_7_15(Integer actlvl_7_15) {
		this.actlvl_7_15 = actlvl_7_15;
	}

	public Double getActlvl_7_15_rate() {
		return actlvl_7_15_rate;
	}

	public void setActlvl_7_15_rate(Double actlvl_7_15_rate) {
		this.actlvl_7_15_rate = actlvl_7_15_rate;
	}

	public Integer getActlvl_16_20() {
		return actlvl_16_20;
	}

	public void setActlvl_16_20(Integer actlvl_16_20) {
		this.actlvl_16_20 = actlvl_16_20;
	}

	public Double getActlvl_16_20_rate() {
		return actlvl_16_20_rate;
	}

	public void setActlvl_16_20_rate(Double actlvl_16_20_rate) {
		this.actlvl_16_20_rate = actlvl_16_20_rate;
	}

	public Integer getActlvl_21_25() {
		return actlvl_21_25;
	}

	public void setActlvl_21_25(Integer actlvl_21_25) {
		this.actlvl_21_25 = actlvl_21_25;
	}

	public Double getActlvl_21_25_rate() {
		return actlvl_21_25_rate;
	}

	public void setActlvl_21_25_rate(Double actlvl_21_25_rate) {
		this.actlvl_21_25_rate = actlvl_21_25_rate;
	}

	public Integer getActlvl_26_30() {
		return actlvl_26_30;
	}

	public void setActlvl_26_30(Integer actlvl_26_30) {
		this.actlvl_26_30 = actlvl_26_30;
	}

	public Double getActlvl_26_30_rate() {
		return actlvl_26_30_rate;
	}

	public void setActlvl_26_30_rate(Double actlvl_26_30_rate) {
		this.actlvl_26_30_rate = actlvl_26_30_rate;
	}

	public Integer getActlvl_31_X() {
		return actlvl_31_X;
	}

	public void setActlvl_31_X(Integer actlvl_31_X) {
		this.actlvl_31_X = actlvl_31_X;
	}

	public Double getActlvl_31_X_rate() {
		return actlvl_31_X_rate;
	}

	public void setActlvl_31_X_rate(Double actlvl_31_X_rate) {

		this.actlvl_31_X_rate = actlvl_31_X_rate;
	}

	public Double getActlvl_3_10_rate() {
		return actlvl_3_10_rate;
	}

	public void setActlvl_3_10_rate(Double actlvl_3_10_rate) {
		this.actlvl_3_10_rate = actlvl_3_10_rate;
	}

	public Double getActlvl_11_20_rate() {
		return actlvl_11_20_rate;
	}

	public void setActlvl_11_20_rate(Double actlvl_11_20_rate) {
		this.actlvl_11_20_rate = actlvl_11_20_rate;
	}

	public Double getActlvl_21_30_rate() {
		return actlvl_21_30_rate;
	}

	public void setActlvl_21_30_rate(Double actlvl_21_30_rate) {
		this.actlvl_21_30_rate = actlvl_21_30_rate;
	}

	public Double getActlvl_31_40_rate() {
		return actlvl_31_40_rate;
	}

	public void setActlvl_31_40_rate(Double actlvl_31_40_rate) {
		this.actlvl_31_40_rate = actlvl_31_40_rate;
	}

	public Double getActlvl_41_50_rate() {
		return actlvl_41_50_rate;
	}

	public void setActlvl_41_50_rate(Double actlvl_41_50_rate) {
		this.actlvl_41_50_rate = actlvl_41_50_rate;
	}

	public Double getActlvl_51_X_rate() {
		return actlvl_51_X_rate;
	}

	public void setActlvl_51_X_rate(Double actlvl_51_X_rate) {
		this.actlvl_51_X_rate = actlvl_51_X_rate;
	}
    
}