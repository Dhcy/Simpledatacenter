package com.good.plat.datacenter.datastat.entity.revenue;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

/**
 * 
 * @ClassName: Ltv
 * @Description: ltv
 * @author hwj
 * @date 2017-2-8 下午02:03:14
 */
public class Ltv extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//注册日期
	
	///注收比
	private String regdate;
	//新增账号数
	private Integer newact;
	//当日付费
	private Double day0_pay;
	//1日付费
	private Double day1_pay;
	//2日付费
	private Double day2_pay;
	//3日付费
	private Double day3_pay;
	//4日付费
	private Double day4_pay;
	//5日付费
	private Double day5_pay;
	//6日付费
	private Double day6_pay;
	//7日付费
	private Double day7_pay;
	//8日付费
	private Double day8_pay;
	//9日付费
	private Double day9_pay;
	//10日付费
	private Double day10_pay;
	//11日付费
	private Double day11_pay;
	//12日付费
	private Double day12_pay;
	//当日付费率
	private Double day0_pay_rate;
	//1日付费率
	private Double day1_pay_rate;
	//2日付费率
	private Double day2_pay_rate;
	//3日付费率
	private Double day3_pay_rate;
	//4日付费率
	private Double day4_pay_rate;
	//5日付费率
	private Double day5_pay_rate;
	//6日付费率
	private Double day6_pay_rate;
	//7日付费率
	private Double day7_pay_rate;
	//8日付费率
	private Double day8_pay_rate;
	//9日付费率
	private Double day9_pay_rate;
	//10日付费率
	private Double day10_pay_rate;
	//11日付费率
	private Double day11_pay_rate;
	//12日付费率
	private Double day12_pay_rate;
	
	
	///用户生命周期价值
	//ltv3
	private Double ltv3;
	//ltv7
	private Double ltv7;
	//ltv30
	private Double ltv30;
	//ltv60
	private Double ltv60;
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public Integer getNewact() {
		return newact;
	}
	public void setNewact(Integer newact) {
		this.newact = newact;
	}
	public Double getDay0_pay() {
		return day0_pay;
	}
	public void setDay0_pay(Double day0Pay) {
		day0_pay = day0Pay;
	}
	public Double getDay1_pay() {
		return day1_pay;
	}
	public void setDay1_pay(Double day1Pay) {
		day1_pay = day1Pay;
	}
	public Double getDay2_pay() {
		return day2_pay;
	}
	public void setDay2_pay(Double day2Pay) {
		day2_pay = day2Pay;
	}
	public Double getDay3_pay() {
		return day3_pay;
	}
	public void setDay3_pay(Double day3Pay) {
		day3_pay = day3Pay;
	}
	public Double getDay4_pay() {
		return day4_pay;
	}
	public void setDay4_pay(Double day4Pay) {
		day4_pay = day4Pay;
	}
	public Double getDay5_pay() {
		return day5_pay;
	}
	public void setDay5_pay(Double day5Pay) {
		day5_pay = day5Pay;
	}
	public Double getDay6_pay() {
		return day6_pay;
	}
	public void setDay6_pay(Double day6Pay) {
		day6_pay = day6Pay;
	}
	public Double getDay7_pay() {
		return day7_pay;
	}
	public void setDay7_pay(Double day7Pay) {
		day7_pay = day7Pay;
	}
	public Double getDay8_pay() {
		return day8_pay;
	}
	public void setDay8_pay(Double day8Pay) {
		day8_pay = day8Pay;
	}
	public Double getDay9_pay() {
		return day9_pay;
	}
	public void setDay9_pay(Double day9Pay) {
		day9_pay = day9Pay;
	}
	public Double getDay10_pay() {
		return day10_pay;
	}
	public void setDay10_pay(Double day10Pay) {
		day10_pay = day10Pay;
	}
	public Double getDay11_pay() {
		return day11_pay;
	}
	public void setDay11_pay(Double day11Pay) {
		day11_pay = day11Pay;
	}
	public Double getDay12_pay() {
		return day12_pay;
	}
	public void setDay12_pay(Double day12Pay) {
		day12_pay = day12Pay;
	}
	public Double getDay0_pay_rate() {
		return day0_pay_rate;
	}
	public void setDay0_pay_rate(Double day0PayRate) {
		day0_pay_rate = day0PayRate;
	}
	public Double getDay1_pay_rate() {
		return day1_pay_rate;
	}
	public void setDay1_pay_rate(Double day1PayRate) {
		day1_pay_rate = day1PayRate;
	}
	public Double getDay2_pay_rate() {
		return day2_pay_rate;
	}
	public void setDay2_pay_rate(Double day2PayRate) {
		day2_pay_rate = day2PayRate;
	}
	public Double getDay3_pay_rate() {
		return day3_pay_rate;
	}
	public void setDay3_pay_rate(Double day3PayRate) {
		day3_pay_rate = day3PayRate;
	}
	public Double getDay4_pay_rate() {
		return day4_pay_rate;
	}
	public void setDay4_pay_rate(Double day4PayRate) {
		day4_pay_rate = day4PayRate;
	}
	public Double getDay5_pay_rate() {
		return day5_pay_rate;
	}
	public void setDay5_pay_rate(Double day5PayRate) {
		day5_pay_rate = day5PayRate;
	}
	public Double getDay6_pay_rate() {
		return day6_pay_rate;
	}
	public void setDay6_pay_rate(Double day6PayRate) {
		day6_pay_rate = day6PayRate;
	}
	public Double getDay7_pay_rate() {
		return day7_pay_rate;
	}
	public void setDay7_pay_rate(Double day7PayRate) {
		day7_pay_rate = day7PayRate;
	}
	public Double getDay8_pay_rate() {
		return day8_pay_rate;
	}
	public void setDay8_pay_rate(Double day8PayRate) {
		day8_pay_rate = day8PayRate;
	}
	public Double getDay9_pay_rate() {
		return day9_pay_rate;
	}
	public void setDay9_pay_rate(Double day9PayRate) {
		day9_pay_rate = day9PayRate;
	}
	public Double getDay10_pay_rate() {
		return day10_pay_rate;
	}
	public void setDay10_pay_rate(Double day10PayRate) {
		day10_pay_rate = day10PayRate;
	}
	public Double getDay11_pay_rate() {
		return day11_pay_rate;
	}
	public void setDay11_pay_rate(Double day11PayRate) {
		day11_pay_rate = day11PayRate;
	}
	public Double getDay12_pay_rate() {
		return day12_pay_rate;
	}
	public void setDay12_pay_rate(Double day12PayRate) {
		day12_pay_rate = day12PayRate;
	}
	public Double getLtv3() {
		return ltv3;
	}
	public void setLtv3(Double ltv3) {
		this.ltv3 = ltv3;
	}
	public Double getLtv7() {
		return ltv7;
	}
	public void setLtv7(Double ltv7) {
		this.ltv7 = ltv7;
	}
	public Double getLtv30() {
		return ltv30;
	}
	public void setLtv30(Double ltv30) {
		this.ltv30 = ltv30;
	}
	public Double getLtv60() {
		return ltv60;
	}
	public void setLtv60(Double ltv60) {
		this.ltv60 = ltv60;
	}
	
	
	
	

}
