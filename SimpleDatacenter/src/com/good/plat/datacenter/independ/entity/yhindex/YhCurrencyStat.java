package com.good.plat.datacenter.independ.entity.yhindex;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;
/**
 * 银河货币统计
 * @ClassName: YhCurrencyStat
 * @Description: TODO
 * @author hwj
 * @date 2017-12-28 下午04:39:12
 */
public class YhCurrencyStat extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 体力
	 * */
	//活跃玩家数
	private Integer actUser;
	//平均消耗体力值
	private Double avgPowerValue;
	/*购买n次体力的活跃玩家数*/
	private Integer buyPower0TimesActUser;
	private Integer buyPower1TimesActUser;
	private Integer buyPower2TimesActUser;
	private Integer buyPower3TimesActUser;
	private Integer buyPower4TimesActUser;
	private Integer buyPowerHigh5TimesActUser;
	/*购买n次体力的活跃玩家数占比*/
	private Double buyPower0TimesActUserRate;
	private Double buyPower1TimesActUserRate;
	private Double buyPower2TimesActUserRate;
	private Double buyPower3TimesActUserRate;
	private Double buyPower4TimesActUserRate;
	private Double buyPowerHigh5TimesActUserRate;
	
	/**氪金消耗**/
	private Integer count;
	//人均消耗
	private Double avgConsumRate;
	
	/**氪金存量*/
	//等级区间
	private Integer lvlsection;
	//区间0代表0~100氪金
	private Double sect0CoutRate;
	//101代表101~1000氪金
	private Double sect101CoutRate;
	//1001代表1001~3000氪金
	private Double sect1001CoutRate;
	//3001代表3001~10000氪金
	private Double sect3001CoutRate;
	//10001代表10001氪金以上
	private Double sect10001CoutRate;
	private String lvlSectionStr;
	
	/**
	 * 货币消耗统计
	 * */
	//货币类型
	private String currencyType;
	//消耗方式
	private String consumeType;
	//消耗次数
	private Integer consumeCnt;
	//消耗货币数
	private Integer currencyCount;
	//平均每次消耗货币
	private Double avgPerTimeCount;
	//人均消耗次数
	private Double avgPerUserConsumeCnt;
	//人均消耗货币
	private Double avgPerUserCount;
	
	//货币类型
	private Integer curType;
	//获取货币数
	private Integer gainCnt;
	//结余货币数
	private Integer leftCount;
	public Integer getActUser() {
		return actUser;
	}
	public void setActUser(Integer actUser) {
		this.actUser = actUser;
	}
	public Double getAvgPowerValue() {
		return avgPowerValue;
	}
	public void setAvgPowerValue(Double avgPowerValue) {
		this.avgPowerValue = avgPowerValue;
	}
	public Integer getBuyPower0TimesActUser() {
		return buyPower0TimesActUser;
	}
	public void setBuyPower0TimesActUser(Integer buyPower0TimesActUser) {
		this.buyPower0TimesActUser = buyPower0TimesActUser;
	}
	public Integer getBuyPower1TimesActUser() {
		return buyPower1TimesActUser;
	}
	public void setBuyPower1TimesActUser(Integer buyPower1TimesActUser) {
		this.buyPower1TimesActUser = buyPower1TimesActUser;
	}
	public Integer getBuyPower2TimesActUser() {
		return buyPower2TimesActUser;
	}
	public void setBuyPower2TimesActUser(Integer buyPower2TimesActUser) {
		this.buyPower2TimesActUser = buyPower2TimesActUser;
	}
	public Integer getBuyPower3TimesActUser() {
		return buyPower3TimesActUser;
	}
	public void setBuyPower3TimesActUser(Integer buyPower3TimesActUser) {
		this.buyPower3TimesActUser = buyPower3TimesActUser;
	}
	public Integer getBuyPower4TimesActUser() {
		return buyPower4TimesActUser;
	}
	public void setBuyPower4TimesActUser(Integer buyPower4TimesActUser) {
		this.buyPower4TimesActUser = buyPower4TimesActUser;
	}
	public Integer getBuyPowerHigh5TimesActUser() {
		return buyPowerHigh5TimesActUser;
	}
	public void setBuyPowerHigh5TimesActUser(Integer buyPowerHigh5TimesActUser) {
		this.buyPowerHigh5TimesActUser = buyPowerHigh5TimesActUser;
	}
	public Double getBuyPower0TimesActUserRate() {
		return buyPower0TimesActUserRate;
	}
	public void setBuyPower0TimesActUserRate(Double buyPower0TimesActUserRate) {
		this.buyPower0TimesActUserRate = buyPower0TimesActUserRate;
	}
	public Double getBuyPower1TimesActUserRate() {
		return buyPower1TimesActUserRate;
	}
	public void setBuyPower1TimesActUserRate(Double buyPower1TimesActUserRate) {
		this.buyPower1TimesActUserRate = buyPower1TimesActUserRate;
	}
	public Double getBuyPower2TimesActUserRate() {
		return buyPower2TimesActUserRate;
	}
	public void setBuyPower2TimesActUserRate(Double buyPower2TimesActUserRate) {
		this.buyPower2TimesActUserRate = buyPower2TimesActUserRate;
	}
	public Double getBuyPower3TimesActUserRate() {
		return buyPower3TimesActUserRate;
	}
	public void setBuyPower3TimesActUserRate(Double buyPower3TimesActUserRate) {
		this.buyPower3TimesActUserRate = buyPower3TimesActUserRate;
	}
	public Double getBuyPower4TimesActUserRate() {
		return buyPower4TimesActUserRate;
	}
	public void setBuyPower4TimesActUserRate(Double buyPower4TimesActUserRate) {
		this.buyPower4TimesActUserRate = buyPower4TimesActUserRate;
	}
	public Double getBuyPowerHigh5TimesActUserRate() {
		return buyPowerHigh5TimesActUserRate;
	}
	public void setBuyPowerHigh5TimesActUserRate(
			Double buyPowerHigh5TimesActUserRate) {
		this.buyPowerHigh5TimesActUserRate = buyPowerHigh5TimesActUserRate;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getAvgConsumRate() {
		return avgConsumRate;
	}
	public void setAvgConsumRate(Double avgConsumRate) {
		this.avgConsumRate = avgConsumRate;
	}
	public Integer getLvlsection() {
		return lvlsection;
	}
	public void setLvlsection(Integer lvlsection) {
		this.lvlsection = lvlsection;
	}
	public Double getSect0CoutRate() {
		return sect0CoutRate;
	}
	public void setSect0CoutRate(Double sect0CoutRate) {
		this.sect0CoutRate = sect0CoutRate;
	}
	public Double getSect101CoutRate() {
		return sect101CoutRate;
	}
	public void setSect101CoutRate(Double sect101CoutRate) {
		this.sect101CoutRate = sect101CoutRate;
	}
	public Double getSect1001CoutRate() {
		return sect1001CoutRate;
	}
	public void setSect1001CoutRate(Double sect1001CoutRate) {
		this.sect1001CoutRate = sect1001CoutRate;
	}
	public Double getSect3001CoutRate() {
		return sect3001CoutRate;
	}
	public void setSect3001CoutRate(Double sect3001CoutRate) {
		this.sect3001CoutRate = sect3001CoutRate;
	}
	public Double getSect10001CoutRate() {
		return sect10001CoutRate;
	}
	public void setSect10001CoutRate(Double sect10001CoutRate) {
		this.sect10001CoutRate = sect10001CoutRate;
	}
	public String getLvlSectionStr() {
		return lvlSectionStr;
	}
	public void setLvlSectionStr(String lvlSectionStr) {
		this.lvlSectionStr = lvlSectionStr;
	}
	public String getConsumeType() {
		return consumeType;
	}
	public void setConsumeType(String consumeType) {
		this.consumeType = consumeType;
	}
	public Integer getConsumeCnt() {
		return consumeCnt;
	}
	public void setConsumeCnt(Integer consumeCnt) {
		this.consumeCnt = consumeCnt;
	}
	public Integer getCurrencyCount() {
		return currencyCount;
	}
	public void setCurrencyCount(Integer currencyCount) {
		this.currencyCount = currencyCount;
	}
	public Double getAvgPerTimeCount() {
		return avgPerTimeCount;
	}
	public void setAvgPerTimeCount(Double avgPerTimeCount) {
		this.avgPerTimeCount = avgPerTimeCount;
	}
	public Double getAvgPerUserConsumeCnt() {
		return avgPerUserConsumeCnt;
	}
	public void setAvgPerUserConsumeCnt(Double avgPerUserConsumeCnt) {
		this.avgPerUserConsumeCnt = avgPerUserConsumeCnt;
	}
	public Double getAvgPerUserCount() {
		return avgPerUserCount;
	}
	public void setAvgPerUserCount(Double avgPerUserCount) {
		this.avgPerUserCount = avgPerUserCount;
	}
	public Integer getCurType() {
		return curType;
	}
	public void setCurType(Integer curType) {
		this.curType = curType;
	}
	
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public Integer getGainCnt() {
		return gainCnt;
	}
	public void setGainCnt(Integer gainCnt) {
		this.gainCnt = gainCnt;
	}
	public Integer getLeftCount() {
		return leftCount;
	}
	public void setLeftCount(Integer leftCount) {
		this.leftCount = leftCount;
	}
	
	
	
	
	
	

}
