package com.good.plat.datacenter.datastat.entity.players;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.good.plat.datacenter.common.base.BaseEntity;
import com.good.plat.datacenter.datastat.entity.index.SummaryData;

/**
 * @ClassName: Conversion
 * @Description: 付费转化
 * @author dmw
 * @date 2016年3月14日 下午1:53:59
 */
public class Conversion extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public Conversion() {
	}
	
	//账号数（按角色）
	private Integer accounts;
	
	//sum
	private Integer accountSUM;
	
	//付费率
	private Double payRate;
	
	//标题
	//private String statType;
	
	private Integer AVG;
	
	private Integer MD;
	
	//百分比
	//private Double rate;

	private List<Conversion> newPaidPlayers;
	private Integer SUM;
	private Integer daycount=0;
	private String title;
	private double percentage;
	private Integer level;
	private List<Conversion> gamePlaytittle=new ArrayList<Conversion>();
	private List<Conversion> firstPayByLevel=new ArrayList<Conversion>();

	//额度在0-5用户数
	private Integer cnt0To5;
	//额度在5.01-10用户数
	private Integer cnt5To10;
	//额度在10.01-20用户数
	private Integer cnt10To20;
	//额度在20.01-30用户数
	private Integer cnt20To30;
	//额度在30.01-40用户数
	private Integer cnt30To40;
	//额度在40.01-50用户数
	private Integer cnt40To50;
	//额度在50.01-100用户数
	private Integer cnt50To100;
	//额度在100以上用户数
	private Integer cntHigh100;
	
	//等级1-10的用户数
	private Integer cntOfLevel1To10;
	//等级11-20的用户数
	private Integer cntOfLevel11To20;
	//等级21-30的用户数
	private Integer cntOfLevel21To30;
	//等级31-40的用户数
	private Integer cntOfLevel31To40;
	//等级41-50的用户数
	private Integer cntOfLevel41To50;
	//等级51-60的用户数
	private Integer cntOfLevel51To60;
	//等级61-70的用户数
	private Integer cntOfLevel61To70;
	//等级71以上的用户数
	private Integer cntOfLevelHigh71;
	
	//首付方式
	private String channelName;
	
	//累计充值账号数
	private Integer rechargeUser;
	//累计注册账号数
	private Integer totalUser;
	
	private Integer newPayAccounts;//新增付费玩家数(按账号)
	
	private Double newPayAccountsRate;//新增付费率（按账号）
	private Integer totalNewPayAccount;//总的付费玩家数(按账号)
	private Double totalnewPayAccountsRate;//新增付费率（按账号）
	public Integer getAccounts() {
		return accounts;
	}

	public void setAccounts(Integer accounts) {
		this.accounts = accounts;
	}

	public Integer getAccountSUM() {
		return accountSUM;
	}

	public void setAccountSUM(Integer accountSUM) {
		this.accountSUM = accountSUM;
	}

	public Double getPayRate() {
		return payRate;
	}

	public void setPayRate(Double payRate) {
		this.payRate = payRate;
	}

	public Integer getAVG() {
		return AVG;
	}

	public void setAVG(Integer aVG) {
		AVG = aVG;
	}

	public Integer getMD() {
		return MD;
	}

	public void setMD(Integer mD) {
		MD = mD;
	}
	

	public List<Conversion> getNewPaidPlayers() {
		return newPaidPlayers;
	}

	public void setNewPaidPlayers(List<Conversion> newPaidPlayers) {
		this.newPaidPlayers = newPaidPlayers;
	}

	public Integer getSUM() {
		return SUM;
	}

	public void setSUM(Integer sUM) {
		SUM = sUM;
	}

	public Integer getDaycount() {
		return daycount;
	}

	public void setDaycount(Integer daycount) {
		this.daycount = daycount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public List<Conversion> getGamePlaytittle() {
		return gamePlaytittle;
	}

	public void setGamePlaytittle(List<Conversion> gamePlaytittle) {
		this.gamePlaytittle = gamePlaytittle;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public List<Conversion> getFirstPayByLevel() {
		return firstPayByLevel;
	}

	public void setFirstPayByLevel(List<Conversion> firstPayByLevel) {
		this.firstPayByLevel = firstPayByLevel;
	}


	public Integer getCnt0To5() {
		return cnt0To5;
	}

	public void setCnt0To5(Integer cnt0To5) {
		this.cnt0To5 = cnt0To5;
	}

	public Integer getCnt5To10() {
		return cnt5To10;
	}

	public void setCnt5To10(Integer cnt5To10) {
		this.cnt5To10 = cnt5To10;
	}

	public Integer getCnt10To20() {
		return cnt10To20;
	}

	public void setCnt10To20(Integer cnt10To20) {
		this.cnt10To20 = cnt10To20;
	}

	public Integer getCnt20To30() {
		return cnt20To30;
	}

	public void setCnt20To30(Integer cnt20To30) {
		this.cnt20To30 = cnt20To30;
	}

	public Integer getCnt30To40() {
		return cnt30To40;
	}

	public void setCnt30To40(Integer cnt30To40) {
		this.cnt30To40 = cnt30To40;
	}

	public Integer getCnt40To50() {
		return cnt40To50;
	}

	public void setCnt40To50(Integer cnt40To50) {
		this.cnt40To50 = cnt40To50;
	}

	public Integer getCnt50To100() {
		return cnt50To100;
	}

	public void setCnt50To100(Integer cnt50To100) {
		this.cnt50To100 = cnt50To100;
	}

	public Integer getCntHigh100() {
		return cntHigh100;
	}

	public void setCntHigh100(Integer cntHigh100) {
		this.cntHigh100 = cntHigh100;
	}

	public Integer getCntOfLevel1To10() {
		return cntOfLevel1To10;
	}

	public void setCntOfLevel1To10(Integer cntOfLevel1To10) {
		this.cntOfLevel1To10 = cntOfLevel1To10;
	}

	public Integer getCntOfLevel11To20() {
		return cntOfLevel11To20;
	}

	public void setCntOfLevel11To20(Integer cntOfLevel11To20) {
		this.cntOfLevel11To20 = cntOfLevel11To20;
	}

	public Integer getCntOfLevel21To30() {
		return cntOfLevel21To30;
	}

	public void setCntOfLevel21To30(Integer cntOfLevel21To30) {
		this.cntOfLevel21To30 = cntOfLevel21To30;
	}

	public Integer getCntOfLevel31To40() {
		return cntOfLevel31To40;
	}

	public void setCntOfLevel31To40(Integer cntOfLevel31To40) {
		this.cntOfLevel31To40 = cntOfLevel31To40;
	}

	public Integer getCntOfLevel41To50() {
		return cntOfLevel41To50;
	}

	public void setCntOfLevel41To50(Integer cntOfLevel41To50) {
		this.cntOfLevel41To50 = cntOfLevel41To50;
	}

	public Integer getCntOfLevel51To60() {
		return cntOfLevel51To60;
	}

	public void setCntOfLevel51To60(Integer cntOfLevel51To60) {
		this.cntOfLevel51To60 = cntOfLevel51To60;
	}

	public Integer getCntOfLevel61To70() {
		return cntOfLevel61To70;
	}

	public void setCntOfLevel61To70(Integer cntOfLevel61To70) {
		this.cntOfLevel61To70 = cntOfLevel61To70;
	}

	public Integer getCntOfLevelHigh71() {
		return cntOfLevelHigh71;
	}

	public void setCntOfLevelHigh71(Integer cntOfLevelHigh71) {
		this.cntOfLevelHigh71 = cntOfLevelHigh71;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Integer getRechargeUser() {
		return rechargeUser;
	}

	public void setRechargeUser(Integer rechargeUser) {
		this.rechargeUser = rechargeUser;
	}

	public Integer getTotalUser() {
		return totalUser;
	}

	public void setTotalUser(Integer totalUser) {
		this.totalUser = totalUser;
	}

	public Integer getNewPayAccounts() {
		return newPayAccounts;
	}

	public void setNewPayAccounts(Integer newPayAccounts) {
		this.newPayAccounts = newPayAccounts;
	}

	public Double getNewPayAccountsRate() {
		return newPayAccountsRate;
	}

	public void setNewPayAccountsRate(Double newPayAccountsRate) {
		this.newPayAccountsRate = newPayAccountsRate;
	}

	public Integer getTotalNewPayAccount() {
		return totalNewPayAccount;
	}

	public void setTotalNewPayAccount(Integer totalNewPayAccount) {
		this.totalNewPayAccount = totalNewPayAccount;
	}

	public Double getTotalnewPayAccountsRate() {
		return totalnewPayAccountsRate;
	}

	public void setTotalnewPayAccountsRate(Double totalnewPayAccountsRate) {
		this.totalnewPayAccountsRate = totalnewPayAccountsRate;
	}
	
	
	
	
	
	
	

	

}
