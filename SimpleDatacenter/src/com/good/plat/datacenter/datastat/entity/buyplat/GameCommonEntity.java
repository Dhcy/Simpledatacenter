package com.good.plat.datacenter.datastat.entity.buyplat;

import java.util.Date;

public class GameCommonEntity {
	public static byte STATE_DISABLED = 0;
	public static byte STATE_ENABLED = 1;
	
	private Date statdate;

	private Integer gameid;

	private Integer termtype;

	private Integer adchannel;

	private String subchannel;
	//
	protected String gameName;
	protected String termtypeName;
	protected String channelName;
	protected String startDataTxt;
	protected String endDataTxt;
    //
	protected String adTypeName;
	protected String pricingTypeName;

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getTermtypeName() {
		return termtypeName;
	}

	public void setTermtypeName(String termtypeName) {
		this.termtypeName = termtypeName;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getStartDataTxt() {
		return startDataTxt;
	}

	public void setStartDataTxt(String startDataTxt) {
		this.startDataTxt = startDataTxt;
	}

	public String getEndDataTxt() {
		return endDataTxt;
	}

	public void setEndDataTxt(String endDataTxt) {
		this.endDataTxt = endDataTxt;
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

	public String getAdTypeName() {
		return adTypeName;
	}

	public void setAdTypeName(String adTypeName) {
		this.adTypeName = adTypeName;
	}

	public String getPricingTypeName() {
		return pricingTypeName;
	}

	public void setPricingTypeName(String pricingTypeName) {
		this.pricingTypeName = pricingTypeName;
	}
	
}
