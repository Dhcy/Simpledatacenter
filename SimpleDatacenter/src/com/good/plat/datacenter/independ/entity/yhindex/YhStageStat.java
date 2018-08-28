package com.good.plat.datacenter.independ.entity.yhindex;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;
/**
 * 银河关卡
 * @ClassName: YhStageStat
 * @Description: TODO
 * @author hwj
 * @date 2017-12-27 上午10:30:01
 */
public class YhStageStat extends BaseEntity implements Serializable{

	/**
	 * 玩家情况
	 */
	private static final long serialVersionUID = 1L;
	//关卡
	private Integer stageid;
	//符合进入玩家数
	private Integer fitActUser;
	//进入玩家数
	private Integer enterActUser;
	//通关玩家数
	private Integer successActUser;
	//失败玩家
	private Integer failActUser;
	//通关玩家占比(通关人数/进入人数)
	private Double succActUserRate;
	//通关玩家总比(通关人数/本服务器人数)
	private Double succActUserTotalRate;
	// 总玩家数
	private Integer regUser;
	/**
	 * 挑战情况
	 */
	//挑战次数
	private Integer count;
	//通关次数
	private Integer successCount;
	//通关次数/挑战次数
	private Double successRate;
	//平均通关时间
	private Double avgSuccessTime;
	//平均失败时间 
	private Double avgFailTime;
	//平均战斗时间
	private Double avgFightTime;
	//首次通关平均等级
	private Double firstAvgSuccLvl;
	//首次通关最低等级
	private Integer firstMinLvl;
	//
	private String avgSuccessTimeStr;
	private String avgFailTimeStr;
	private String avgFightTimeStr;
	//关卡星数
	private Integer onestarcnt;
	private Integer twostarcnt;
	private Integer threestarcnt;
	private Double onestarcntRate;
	private Double twostarcntRate;
	private Double threestarcntRate;
	//(军团,好友,随机)助战次数
	//军团助战次数
	private Integer jtCnt;
	//好友助战次数
	private Integer friendCnt;
	//随机助战次数
	private Integer randomCnt;
	//军团助战比率
	private Double jtRate;
	//好友助战比率
	private Double friendRate;
	//随机助战比率
	private Double randomRate;
	public Integer getStageid() {
		return stageid;
	}
	public void setStageid(Integer stageid) {
		this.stageid = stageid;
	}
	public Integer getFitActUser() {
		return fitActUser;
	}
	public void setFitActUser(Integer fitActUser) {
		this.fitActUser = fitActUser;
	}
	public Integer getEnterActUser() {
		return enterActUser;
	}
	public void setEnterActUser(Integer enterActUser) {
		this.enterActUser = enterActUser;
	}
	public Integer getSuccessActUser() {
		return successActUser;
	}
	public void setSuccessActUser(Integer successActUser) {
		this.successActUser = successActUser;
	}
	public Integer getFailActUser() {
		return failActUser;
	}
	public void setFailActUser(Integer failActUser) {
		this.failActUser = failActUser;
	}
	public Double getSuccActUserRate() {
		return succActUserRate;
	}
	public void setSuccActUserRate(Double succActUserRate) {
		this.succActUserRate = succActUserRate;
	}
	public Double getSuccActUserTotalRate() {
		return succActUserTotalRate;
	}
	public void setSuccActUserTotalRate(Double succActUserTotalRate) {
		this.succActUserTotalRate = succActUserTotalRate;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	public Integer getRegUser() {
		return regUser;
	}
	public void setRegUser(Integer regUser) {
		this.regUser = regUser;
	}
	public Integer getSuccessCount() {
		return successCount;
	}
	public void setSuccessCount(Integer successCount) {
		this.successCount = successCount;
	}
	public Double getSuccessRate() {
		return successRate;
	}
	public void setSuccessRate(Double successRate) {
		this.successRate = successRate;
	}
	public Double getAvgSuccessTime() {
		return avgSuccessTime;
	}
	public void setAvgSuccessTime(Double avgSuccessTime) {
		this.avgSuccessTime = avgSuccessTime;
	}
	public Double getAvgFailTime() {
		return avgFailTime;
	}
	public void setAvgFailTime(Double avgFailTime) {
		this.avgFailTime = avgFailTime;
	}
	public Double getAvgFightTime() {
		return avgFightTime;
	}
	public void setAvgFightTime(Double avgFightTime) {
		this.avgFightTime = avgFightTime;
	}
	public Double getFirstAvgSuccLvl() {
		return firstAvgSuccLvl;
	}
	public void setFirstAvgSuccLvl(Double firstAvgSuccLvl) {
		this.firstAvgSuccLvl = firstAvgSuccLvl;
	}
	public Integer getFirstMinLvl() {
		return firstMinLvl;
	}
	public void setFirstMinLvl(Integer firstMinLvl) {
		this.firstMinLvl = firstMinLvl;
	}
	public String getAvgSuccessTimeStr() {
		return avgSuccessTimeStr;
	}
	public void setAvgSuccessTimeStr(String avgSuccessTimeStr) {
		this.avgSuccessTimeStr = avgSuccessTimeStr;
	}
	public String getAvgFailTimeStr() {
		return avgFailTimeStr;
	}
	public void setAvgFailTimeStr(String avgFailTimeStr) {
		this.avgFailTimeStr = avgFailTimeStr;
	}
	public String getAvgFightTimeStr() {
		return avgFightTimeStr;
	}
	public void setAvgFightTimeStr(String avgFightTimeStr) {
		this.avgFightTimeStr = avgFightTimeStr;
	}
	public Integer getOnestarcnt() {
		return onestarcnt;
	}
	public void setOnestarcnt(Integer onestarcnt) {
		this.onestarcnt = onestarcnt;
	}
	public Integer getTwostarcnt() {
		return twostarcnt;
	}
	public void setTwostarcnt(Integer twostarcnt) {
		this.twostarcnt = twostarcnt;
	}
	public Integer getThreestarcnt() {
		return threestarcnt;
	}
	public void setThreestarcnt(Integer threestarcnt) {
		this.threestarcnt = threestarcnt;
	}
	public Double getOnestarcntRate() {
		return onestarcntRate;
	}
	public void setOnestarcntRate(Double onestarcntRate) {
		this.onestarcntRate = onestarcntRate;
	}
	public Double getTwostarcntRate() {
		return twostarcntRate;
	}
	public void setTwostarcntRate(Double twostarcntRate) {
		this.twostarcntRate = twostarcntRate;
	}
	public Double getThreestarcntRate() {
		return threestarcntRate;
	}
	public void setThreestarcntRate(Double threestarcntRate) {
		this.threestarcntRate = threestarcntRate;
	}
	public Integer getJtCnt() {
		return jtCnt;
	}
	public void setJtCnt(Integer jtCnt) {
		this.jtCnt = jtCnt;
	}
	public Integer getFriendCnt() {
		return friendCnt;
	}
	public void setFriendCnt(Integer friendCnt) {
		this.friendCnt = friendCnt;
	}
	public Integer getRandomCnt() {
		return randomCnt;
	}
	public void setRandomCnt(Integer randomCnt) {
		this.randomCnt = randomCnt;
	}
	public Double getJtRate() {
		return jtRate;
	}
	public void setJtRate(Double jtRate) {
		this.jtRate = jtRate;
	}
	public Double getFriendRate() {
		return friendRate;
	}
	public void setFriendRate(Double friendRate) {
		this.friendRate = friendRate;
	}
	public Double getRandomRate() {
		return randomRate;
	}
	public void setRandomRate(Double randomRate) {
		this.randomRate = randomRate;
	}
	@Override
	public String toString() {
		return "YhStageStat [avgFailTime=" + avgFailTime + ", avgFailTimeStr="
				+ avgFailTimeStr + ", avgFightTime=" + avgFightTime
				+ ", avgFightTimeStr=" + avgFightTimeStr + ", avgSuccessTime="
				+ avgSuccessTime + ", avgSuccessTimeStr=" + avgSuccessTimeStr
				+ ", count=" + count + ", enterActUser=" + enterActUser
				+ ", failActUser=" + failActUser + ", firstAvgSuccLvl="
				+ firstAvgSuccLvl + ", firstMinLvl=" + firstMinLvl
				+ ", fitActUser=" + fitActUser + ", friendCnt=" + friendCnt
				+ ", friendRate=" + friendRate + ", jtCnt=" + jtCnt
				+ ", jtRate=" + jtRate + ", onestarcnt=" + onestarcnt
				+ ", onestarcntRate=" + onestarcntRate + ", randomCnt="
				+ randomCnt + ", randomRate=" + randomRate + ", regUser="
				+ regUser + ", stageid=" + stageid + ", succActUserRate="
				+ succActUserRate + ", succActUserTotalRate="
				+ succActUserTotalRate + ", successActUser=" + successActUser
				+ ", successCount=" + successCount + ", successRate="
				+ successRate + ", threestarcnt=" + threestarcnt
				+ ", threestarcntRate=" + threestarcntRate + ", twostarcnt="
				+ twostarcnt + ", twostarcntRate=" + twostarcntRate + "]";
	}
	
	
	
	
	
	
	
	
	
	

}
