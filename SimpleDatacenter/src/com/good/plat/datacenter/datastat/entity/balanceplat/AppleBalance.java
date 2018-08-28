package com.good.plat.datacenter.datastat.entity.balanceplat;

public class AppleBalance extends UscFinancialAppleReport {
	private Integer diffCount;
	private Integer appleCount;
	private Integer goodCount;
	private String gameName;
	private Integer gameid;
	
	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public Integer getGameid() {
		return gameid;
	}

	public void setGameid(Integer gameid) {
		this.gameid = gameid;
	}


	public Integer getDiffCount() {
		return diffCount;
	}

	public void setDiffCount(Integer diffCount) {
		this.diffCount = diffCount;
	}

	public Integer getAppleCount() {
		return appleCount;
	}

	public void setAppleCount(Integer appleCount) {
		this.appleCount = appleCount;
	}

	public Integer getGoodCount() {
		return goodCount;
	}

	public void setGoodCount(Integer goodCount) {
		this.goodCount = goodCount;
	}
	
}
