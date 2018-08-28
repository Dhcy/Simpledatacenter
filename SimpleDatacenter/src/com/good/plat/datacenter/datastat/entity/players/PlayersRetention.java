package com.good.plat.datacenter.datastat.entity.players;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

/**
 * @ClassName: PlayersRetention
 * @Description: 玩家留存
 * @author dmw
 * @date 2016年3月14日 下午1:57:33
 */
public class PlayersRetention extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public PlayersRetention() {
	}

	// 日期
	// private String statdate;
	// 留存率
	private Double day1;
	// 次日留存率
	private Double day2;
	// 留存率
	private Double day3;
	// 留存率
	private Double day4;
	// 留存率
	private Double day5;
	// 留存率
	private Double day6;

	// 7日留存率
	private Double day7;
	// 留存率
	private Double day14;
	// 30日留存率
	private Double day30;
	//60日留存率
	private Double day60;
	//90日留存率
	private Double day90;
	//120日留存率
	private Double day120;
	//150日留存率
	private Double day150;
	//180日留存率
	private Double day180;

	// 留存账户数
	private Integer accounts;

	// 百分比
	// private Double rate;
	
	//区域
	private String city;
	
	// 1日留存账户数
	private Integer day1RetNum;
	// 2日留存账户数
	private Integer day2RetNum;
	// 3日留存账户数
	private Integer day3RetNum;
	// 4日留存账户数
	private Integer day4RetNum;
	// 5日留存账户数
	private Integer day5RetNum;
	// 6日留存账户数
	private Integer day6RetNum;
	// 7日留存账户数
	private Integer day7RetNum;
	// 14日留存账户数
	private Integer day14RetNum;
	// 30日留存账户数
	private Integer day30RetNum;
	// 60日留存账户数
	private Integer day60RetNum;
	// 90日留存账户数
	private Integer day90RetNum;
	// 120日留存账户数
	private Integer day120RetNum;
	// 150日留存账户数
	private Integer day150RetNum;
	// 180日留存账户数
	private Integer day180RetNum;
	

	
	public Double getDay2() {
		return day2;
	}

	public void setDay2(Double day2) {
		this.day2 = day2;
	}

	public Double getDay7() {
		return day7;
	}

	public void setDay7(Double day7) {
		this.day7 = day7;
	}

	public Double getDay30() {
		return day30;
	}

	public void setDay30(Double day30) {
		this.day30 = day30;
	}

	public Integer getAccounts() {
		return accounts;
	}

	public void setAccounts(Integer accounts) {
		this.accounts = accounts;
	}

	public Double getDay1() {
		return day1;
	}

	public void setDay1(Double day1) {
		this.day1 = day1;
	}

	public Double getDay3() {
		return day3;
	}

	public void setDay3(Double day3) {
		this.day3 = day3;
	}

	public Double getDay4() {
		return day4;
	}

	public void setDay4(Double day4) {
		this.day4 = day4;
	}

	public Double getDay5() {
		return day5;
	}

	public void setDay5(Double day5) {
		this.day5 = day5;
	}

	public Double getDay6() {
		return day6;
	}

	public void setDay6(Double day6) {
		this.day6 = day6;
	}

	public Double getDay14() {
		return day14;
	}

	public void setDay14(Double day14) {
		this.day14 = day14;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getDay1RetNum() {
		return day1RetNum;
	}

	public void setDay1RetNum(Integer day1RetNum) {
		this.day1RetNum = day1RetNum;
	}

	public Integer getDay2RetNum() {
		return day2RetNum;
	}

	public void setDay2RetNum(Integer day2RetNum) {
		this.day2RetNum = day2RetNum;
	}

	public Integer getDay3RetNum() {
		return day3RetNum;
	}

	public void setDay3RetNum(Integer day3RetNum) {
		this.day3RetNum = day3RetNum;
	}

	public Integer getDay4RetNum() {
		return day4RetNum;
	}

	public void setDay4RetNum(Integer day4RetNum) {
		this.day4RetNum = day4RetNum;
	}

	public Integer getDay5RetNum() {
		return day5RetNum;
	}

	public void setDay5RetNum(Integer day5RetNum) {
		this.day5RetNum = day5RetNum;
	}

	public Integer getDay6RetNum() {
		return day6RetNum;
	}

	public void setDay6RetNum(Integer day6RetNum) {
		this.day6RetNum = day6RetNum;
	}

	public Integer getDay7RetNum() {
		return day7RetNum;
	}

	public void setDay7RetNum(Integer day7RetNum) {
		this.day7RetNum = day7RetNum;
	}

	public Integer getDay14RetNum() {
		return day14RetNum;
	}

	public void setDay14RetNum(Integer day14RetNum) {
		this.day14RetNum = day14RetNum;
	}

	public Integer getDay30RetNum() {
		return day30RetNum;
	}

	public void setDay30RetNum(Integer day30RetNum) {
		this.day30RetNum = day30RetNum;
	}

	public Double getDay60() {
		return day60;
	}

	public void setDay60(Double day60) {
		this.day60 = day60;
	}

	public Double getDay90() {
		return day90;
	}

	public void setDay90(Double day90) {
		this.day90 = day90;
	}

	public Double getDay120() {
		return day120;
	}

	public void setDay120(Double day120) {
		this.day120 = day120;
	}

	public Double getDay150() {
		return day150;
	}

	public void setDay150(Double day150) {
		this.day150 = day150;
	}

	public Double getDay180() {
		return day180;
	}

	public void setDay180(Double day180) {
		this.day180 = day180;
	}

	public Integer getDay60RetNum() {
		return day60RetNum;
	}

	public void setDay60RetNum(Integer day60RetNum) {
		this.day60RetNum = day60RetNum;
	}

	public Integer getDay90RetNum() {
		return day90RetNum;
	}

	public void setDay90RetNum(Integer day90RetNum) {
		this.day90RetNum = day90RetNum;
	}

	public Integer getDay120RetNum() {
		return day120RetNum;
	}

	public void setDay120RetNum(Integer day120RetNum) {
		this.day120RetNum = day120RetNum;
	}

	public Integer getDay150RetNum() {
		return day150RetNum;
	}

	public void setDay150RetNum(Integer day150RetNum) {
		this.day150RetNum = day150RetNum;
	}

	public Integer getDay180RetNum() {
		return day180RetNum;
	}

	public void setDay180RetNum(Integer day180RetNum) {
		this.day180RetNum = day180RetNum;
	}
	
	
	
	
}
