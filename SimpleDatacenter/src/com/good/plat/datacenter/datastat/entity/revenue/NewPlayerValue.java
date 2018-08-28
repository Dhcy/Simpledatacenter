package com.good.plat.datacenter.datastat.entity.revenue;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

/**
 * @ClassName: NewPlayerValue
 * @Description: 新玩家价值
 * @author dmw
 * @date 2016年3月14日 下午5:30:40
 */
public class NewPlayerValue extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public NewPlayerValue() {
	
	}
	
	//7日内平均贡献
	private Double day7Con;
	
	//14日内平均贡献
	private Double day14Con;
	
	//30日内平均贡献
	private Double day30Con;
	
	private Double data;
	
	private Double AVG;
	
	private Double MD;
	
	//新增账号数
	private Integer accounts;
	//1日付费金额（单日或者累计）
	private Double money1;
	//2日付费金额（单日或者累计）
	private Double money2;
	//3日付费金额（单日或者累计）
	private Double money3;
	//4日付费金额（单日或者累计）
	private Double money4;
	//5日付费金额（单日或者累计）
	private Double money5;
	//6日付费金额（单日或者累计）
	private Double money6;
	//7日付费金额（单日或者累计）
	private Double money7;
	//14日付费金额（8~14日或者累计）
	private Double money14;
	//30日付费金额（15~30日或者累计）
	private Double money30;
	//60日付费金额（31~61日或者累计）
	private Double money60;
	//90日付费金额（61~90日或者累计）
	private Double money90;
	
	private Double day1Con;
	private Double day2Con;
	private Double day3Con;
	private Double day4Con;
	private Double day5Con;
	private Double day6Con;
	private Double day60Con;
	private Double day90Con;
	
	//
	private String city;//城市值
	

	public Double getDay7Con() {
		return day7Con;
	}

	public void setDay7Con(Double day7Con) {
		this.day7Con = day7Con;
	}

	public Double getDay14Con() {
		return day14Con;
	}

	public void setDay14Con(Double day14Con) {
		this.day14Con = day14Con;
	}

	public Double getDay30Con() {
		return day30Con;
	}

	public void setDay30Con(Double day30Con) {
		this.day30Con = day30Con;
	}

	public Double getAVG() {
		return AVG;
	}

	public void setAVG(Double aVG) {
		AVG = aVG;
	}

	public Double getMD() {
		return MD;
	}

	public void setMD(Double mD) {
		MD = mD;
	}

	public Double getData() {
		return data;
	}

	public void setData(Double data) {
		this.data = data;
	}

	public Integer getAccounts() {
		return accounts;
	}

	public void setAccounts(Integer accounts) {
		this.accounts = accounts;
	}

	public Double getMoney1() {
		return money1;
	}

	public void setMoney1(Double money1) {
		this.money1 = money1;
	}

	public Double getMoney2() {
		return money2;
	}

	public void setMoney2(Double money2) {
		this.money2 = money2;
	}

	public Double getMoney3() {
		return money3;
	}

	public void setMoney3(Double money3) {
		this.money3 = money3;
	}

	public Double getMoney4() {
		return money4;
	}

	public void setMoney4(Double money4) {
		this.money4 = money4;
	}

	public Double getMoney5() {
		return money5;
	}

	public void setMoney5(Double money5) {
		this.money5 = money5;
	}

	public Double getMoney6() {
		return money6;
	}

	public void setMoney6(Double money6) {
		this.money6 = money6;
	}

	public Double getMoney7() {
		return money7;
	}

	public void setMoney7(Double money7) {
		this.money7 = money7;
	}

	public Double getMoney14() {
		return money14;
	}

	public void setMoney14(Double money14) {
		this.money14 = money14;
	}

	public Double getMoney30() {
		return money30;
	}

	public void setMoney30(Double money30) {
		this.money30 = money30;
	}

	public Double getMoney60() {
		return money60;
	}

	public void setMoney60(Double money60) {
		this.money60 = money60;
	}

	public Double getMoney90() {
		return money90;
	}

	public void setMoney90(Double money90) {
		this.money90 = money90;
	}

	public Double getDay1Con() {
		return day1Con;
	}

	public void setDay1Con(Double day1Con) {
		this.day1Con = day1Con;
	}

	public Double getDay2Con() {
		return day2Con;
	}

	public void setDay2Con(Double day2Con) {
		this.day2Con = day2Con;
	}

	public Double getDay3Con() {
		return day3Con;
	}

	public void setDay3Con(Double day3Con) {
		this.day3Con = day3Con;
	}

	public Double getDay4Con() {
		return day4Con;
	}

	public void setDay4Con(Double day4Con) {
		this.day4Con = day4Con;
	}

	public Double getDay5Con() {
		return day5Con;
	}

	public void setDay5Con(Double day5Con) {
		this.day5Con = day5Con;
	}

	public Double getDay6Con() {
		return day6Con;
	}

	public void setDay6Con(Double day6Con) {
		this.day6Con = day6Con;
	}

	public Double getDay60Con() {
		return day60Con;
	}

	public void setDay60Con(Double day60Con) {
		this.day60Con = day60Con;
	}

	public Double getDay90Con() {
		return day90Con;
	}

	public void setDay90Con(Double day90Con) {
		this.day90Con = day90Con;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	
	
	

	
}
