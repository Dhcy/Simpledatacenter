package com.good.plat.datacenter.independ.entity.yhindex;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;
/**
 * 银河在线管理
 * @ClassName: YhOnlineStat
 * @Description: TODO
 * @author hwj
 * @date 2017-12-27 下午06:03:16
 */
public class YhOnlineStat extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//0~9min用户数
	private Integer minutes_0to9User;
	//10~19min用户数
	private Integer minutes_10to19User;
	//20~29min用户数
	private Integer minutes_20to29User;
	//30~59min用户数
	private Integer minutes_30to59User;
	//60~89min用户数
	private Integer minutes_60to89User;
	//90~119min用户数
	private Integer minutes_90to119User;
	//120~149min用户数
	private Integer minutes_120to149User;
	//150~179min用户数
	private Integer minutes_150to179User;
	//180~239min用户数
	private Integer minutes_180to239User;
	//240min以上用户数
	private Integer minutes_240toUser;
	//0~9min用户数占比
	private Double minutes_0to9UserRate;
	//10~19min用户数占比
	private Double minutes_10to19UserRate;
	//20~29min用户数占比
	private Double minutes_20to29UserRate;
	//30~59min用户数占比
	private Double minutes_30to59UserRate;
	//60~89min用户数占比
	private Double minutes_60to89UserRate;
	//90~119min用户数占比
	private Double minutes_90to119UserRate;
	//120~149min用户数占比
	private Double minutes_120to149UserRate;
	//150~179min用户数占比
	private Double minutes_150to179UserRate;
	//180~239min用户数占比
	private Double minutes_180to239UserRate;
	//240min以上用户数占比
	private Double minutes_240toUserRate;
	
	/**
	 * 登入次数
	 * */
	//0次用户数量
	private Integer loginTimes0User;
	//1~2次数用户数量
	private Integer loginTimes1to2User;
	//3~5次数用户数量
	private Integer loginTimes3to5User;
	//6~10次用户数量
	private Integer loginTimes6to10User;
	//11次以上
	private Integer loginTimes11toUser;
	//0次用户数占比
	private Double loginTimes0UserRate;
	//1~2次数用户数占比
	private Double loginTimes1to2UserRate;
	//3~5次数用户数占比
	private Double loginTimes3to5UserRate;
	//6~10次用户数占比
	private Double loginTimes6to10UserRate;
	//11次以上占比
	private Double loginTimes11toUserRate;
	public Integer getMinutes_0to9User() {
		return minutes_0to9User;
	}
	public void setMinutes_0to9User(Integer minutes_0to9User) {
		this.minutes_0to9User = minutes_0to9User;
	}
	public Integer getMinutes_10to19User() {
		return minutes_10to19User;
	}
	public void setMinutes_10to19User(Integer minutes_10to19User) {
		this.minutes_10to19User = minutes_10to19User;
	}
	public Integer getMinutes_20to29User() {
		return minutes_20to29User;
	}
	public void setMinutes_20to29User(Integer minutes_20to29User) {
		this.minutes_20to29User = minutes_20to29User;
	}
	public Integer getMinutes_30to59User() {
		return minutes_30to59User;
	}
	public void setMinutes_30to59User(Integer minutes_30to59User) {
		this.minutes_30to59User = minutes_30to59User;
	}
	public Integer getMinutes_60to89User() {
		return minutes_60to89User;
	}
	public void setMinutes_60to89User(Integer minutes_60to89User) {
		this.minutes_60to89User = minutes_60to89User;
	}
	public Integer getMinutes_90to119User() {
		return minutes_90to119User;
	}
	public void setMinutes_90to119User(Integer minutes_90to119User) {
		this.minutes_90to119User = minutes_90to119User;
	}
	public Integer getMinutes_120to149User() {
		return minutes_120to149User;
	}
	public void setMinutes_120to149User(Integer minutes_120to149User) {
		this.minutes_120to149User = minutes_120to149User;
	}
	public Integer getMinutes_150to179User() {
		return minutes_150to179User;
	}
	public void setMinutes_150to179User(Integer minutes_150to179User) {
		this.minutes_150to179User = minutes_150to179User;
	}
	public Integer getMinutes_180to239User() {
		return minutes_180to239User;
	}
	public void setMinutes_180to239User(Integer minutes_180to239User) {
		this.minutes_180to239User = minutes_180to239User;
	}
	public Integer getMinutes_240toUser() {
		return minutes_240toUser;
	}
	public void setMinutes_240toUser(Integer minutes_240toUser) {
		this.minutes_240toUser = minutes_240toUser;
	}
	public Double getMinutes_0to9UserRate() {
		return minutes_0to9UserRate;
	}
	public void setMinutes_0to9UserRate(Double minutes_0to9UserRate) {
		this.minutes_0to9UserRate = minutes_0to9UserRate;
	}
	public Double getMinutes_10to19UserRate() {
		return minutes_10to19UserRate;
	}
	public void setMinutes_10to19UserRate(Double minutes_10to19UserRate) {
		this.minutes_10to19UserRate = minutes_10to19UserRate;
	}
	public Double getMinutes_20to29UserRate() {
		return minutes_20to29UserRate;
	}
	public void setMinutes_20to29UserRate(Double minutes_20to29UserRate) {
		this.minutes_20to29UserRate = minutes_20to29UserRate;
	}
	public Double getMinutes_30to59UserRate() {
		return minutes_30to59UserRate;
	}
	public void setMinutes_30to59UserRate(Double minutes_30to59UserRate) {
		this.minutes_30to59UserRate = minutes_30to59UserRate;
	}
	public Double getMinutes_60to89UserRate() {
		return minutes_60to89UserRate;
	}
	public void setMinutes_60to89UserRate(Double minutes_60to89UserRate) {
		this.minutes_60to89UserRate = minutes_60to89UserRate;
	}
	public Double getMinutes_90to119UserRate() {
		return minutes_90to119UserRate;
	}
	public void setMinutes_90to119UserRate(Double minutes_90to119UserRate) {
		this.minutes_90to119UserRate = minutes_90to119UserRate;
	}
	public Double getMinutes_120to149UserRate() {
		return minutes_120to149UserRate;
	}
	public void setMinutes_120to149UserRate(Double minutes_120to149UserRate) {
		this.minutes_120to149UserRate = minutes_120to149UserRate;
	}
	public Double getMinutes_150to179UserRate() {
		return minutes_150to179UserRate;
	}
	public void setMinutes_150to179UserRate(Double minutes_150to179UserRate) {
		this.minutes_150to179UserRate = minutes_150to179UserRate;
	}
	public Double getMinutes_180to239UserRate() {
		return minutes_180to239UserRate;
	}
	public void setMinutes_180to239UserRate(Double minutes_180to239UserRate) {
		this.minutes_180to239UserRate = minutes_180to239UserRate;
	}
	public Double getMinutes_240toUserRate() {
		return minutes_240toUserRate;
	}
	public void setMinutes_240toUserRate(Double minutes_240toUserRate) {
		this.minutes_240toUserRate = minutes_240toUserRate;
	}
	public Integer getLoginTimes0User() {
		return loginTimes0User;
	}
	public void setLoginTimes0User(Integer loginTimes0User) {
		this.loginTimes0User = loginTimes0User;
	}
	public Integer getLoginTimes1to2User() {
		return loginTimes1to2User;
	}
	public void setLoginTimes1to2User(Integer loginTimes1to2User) {
		this.loginTimes1to2User = loginTimes1to2User;
	}
	public Integer getLoginTimes3to5User() {
		return loginTimes3to5User;
	}
	public void setLoginTimes3to5User(Integer loginTimes3to5User) {
		this.loginTimes3to5User = loginTimes3to5User;
	}
	public Integer getLoginTimes6to10User() {
		return loginTimes6to10User;
	}
	public void setLoginTimes6to10User(Integer loginTimes6to10User) {
		this.loginTimes6to10User = loginTimes6to10User;
	}
	public Integer getLoginTimes11toUser() {
		return loginTimes11toUser;
	}
	public void setLoginTimes11toUser(Integer loginTimes11toUser) {
		this.loginTimes11toUser = loginTimes11toUser;
	}
	public Double getLoginTimes0UserRate() {
		return loginTimes0UserRate;
	}
	public void setLoginTimes0UserRate(Double loginTimes0UserRate) {
		this.loginTimes0UserRate = loginTimes0UserRate;
	}
	public Double getLoginTimes1to2UserRate() {
		return loginTimes1to2UserRate;
	}
	public void setLoginTimes1to2UserRate(Double loginTimes1to2UserRate) {
		this.loginTimes1to2UserRate = loginTimes1to2UserRate;
	}
	public Double getLoginTimes3to5UserRate() {
		return loginTimes3to5UserRate;
	}
	public void setLoginTimes3to5UserRate(Double loginTimes3to5UserRate) {
		this.loginTimes3to5UserRate = loginTimes3to5UserRate;
	}
	public Double getLoginTimes6to10UserRate() {
		return loginTimes6to10UserRate;
	}
	public void setLoginTimes6to10UserRate(Double loginTimes6to10UserRate) {
		this.loginTimes6to10UserRate = loginTimes6to10UserRate;
	}
	public Double getLoginTimes11toUserRate() {
		return loginTimes11toUserRate;
	}
	public void setLoginTimes11toUserRate(Double loginTimes11toUserRate) {
		this.loginTimes11toUserRate = loginTimes11toUserRate;
	}
	
	
	

}
