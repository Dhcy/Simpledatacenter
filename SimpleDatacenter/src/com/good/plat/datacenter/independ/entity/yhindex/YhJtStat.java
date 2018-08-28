package com.good.plat.datacenter.independ.entity.yhindex;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;
/**
 * 银河军团统计
 * @ClassName: YhJtStat
 * @Description: TODO
 * @author hwj
 * @date 2018-5-21 上午09:57:28
 */
public class YhJtStat extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer jtCnt;//军团数量
	private Integer joinJtActCnt;//参加军团玩家数
	private Double joinJtActCntRate;//参加军团玩家数比率
	private Integer openQdrqJtCnt;//开启强敌入侵军团数量
	private Double openQdrqJtCntRate;//开启强敌入侵军团数占比
	private Integer joinPurchaseJtCnt;//参与采购军团数
	private Double joinPurchaseJtCntRate;//参与采购军团数占比
	private Integer joinJtFightJtCnt;//参与军团战军团数
	private Double joinJtFightJtCntRate;//参与军团战军团数占比
	private Integer joinJtFightActCnt;//参与军团战玩家数
	private Double joinJtFightActCntRate;//参与军团战玩家数占比
	
	/**
	 * 军团等级分布
	 */
	private Integer lvl;//等级
	private Integer lvlJtCnt;//在此等级军团数
	private Double lvlJtCntRate ;//在此等级军团数占比
	
	/**
	 * 军团捐赠统计
	 */
	private Integer jtjxActCnt;//军团捐献玩家数
	private Double jtjxActCntRate;//军团捐献玩家数占比
	private Integer jtjzMoney;//军团捐献资金
	private Double avgJtjzMoney;//平均捐献资金（每人）
	private Integer jbjxcnt;//金币捐献次数
	private Double jbjxCntRate;//金币捐献次数占比
	private Integer kjjxcnt;//氪金捐献次数
	private Double kjjxCntRate;//氪金捐献次数占比
	
	/**
	 * 强敌入侵关卡统计
	 */
	private Integer stageid;//关卡ID
	private Integer openStageJtCnt;//开启关卡军团数
	private Integer successJtCnt;//挑战关卡成功军团数
	private Double successJtCntRate;//挑战关卡成功军团数占比
	private Integer wcxsrwJtCnt;//达到限时任务的军团数
	private Double wcxsrwJtCntRate;//达到限时任务的军团数占比
	
	/**
	 * 军团采购统计
	 */
	private Integer times;//采购次数
	private Double jtCntRate; //采购此次数的军团数占比
	
	/**
	 * 军团采购商品统计
	 */
	private Integer goodsid;//商品Id
	private Integer bugCnt;//采购次数
	private Double bugCntRate;//采购次数占比
	
	/**
	 * 军团赞助统计
	 */
	//请求数据
	private Integer fbqqActCnt;//发布请求玩家数
	private Double fbqqActCntRate;//发布请求玩家数占比
	private Integer fbzjqqCnt;//发布战舰请求次数
	private Double fbzjqqCntRate;//发布战舰请求次数占比
	private Integer fbzzqqCnt;//发布装置请求次数
	private Double fbzzqqCntRate;//发布装置请求次数占比
	//赠送数据
	private Integer zsActCnt;//军团赠送玩家数
	private Double zsActCntRate;//赠送玩家数占比（赠送玩家数/参加军团玩家数）
	private Integer zszjCnt;//赠送战舰次数
	private Integer zszzCnt;//赠送装置次数
	private Double zszjCntRate;//赠送战舰次数占比
	private Double zszzCntRate;//赠送装置次数占比
	//总计数据
	private Integer qqCnt;//请求总数量
	private Integer ywcqqCnt;//已完成请求数量
	private Double ywcqqCntRate;//已完成请求占比
	private Integer ygxqqCnt;//已感谢请求数量
	private Integer kgxqqCnt;//可感谢请求数量
	private Double ygxqqCntRate;//已感谢请求数量占比
	public Integer getJtCnt() {
		return jtCnt;
	}
	public void setJtCnt(Integer jtCnt) {
		this.jtCnt = jtCnt;
	}
	public Integer getJoinJtActCnt() {
		return joinJtActCnt;
	}
	public void setJoinJtActCnt(Integer joinJtActCnt) {
		this.joinJtActCnt = joinJtActCnt;
	}
	public Double getJoinJtActCntRate() {
		return joinJtActCntRate;
	}
	public void setJoinJtActCntRate(Double joinJtActCntRate) {
		this.joinJtActCntRate = joinJtActCntRate;
	}
	public Integer getOpenQdrqJtCnt() {
		return openQdrqJtCnt;
	}
	public void setOpenQdrqJtCnt(Integer openQdrqJtCnt) {
		this.openQdrqJtCnt = openQdrqJtCnt;
	}
	public Double getOpenQdrqJtCntRate() {
		return openQdrqJtCntRate;
	}
	public void setOpenQdrqJtCntRate(Double openQdrqJtCntRate) {
		this.openQdrqJtCntRate = openQdrqJtCntRate;
	}
	public Integer getJoinPurchaseJtCnt() {
		return joinPurchaseJtCnt;
	}
	public void setJoinPurchaseJtCnt(Integer joinPurchaseJtCnt) {
		this.joinPurchaseJtCnt = joinPurchaseJtCnt;
	}
	public Double getJoinPurchaseJtCntRate() {
		return joinPurchaseJtCntRate;
	}
	public void setJoinPurchaseJtCntRate(Double joinPurchaseJtCntRate) {
		this.joinPurchaseJtCntRate = joinPurchaseJtCntRate;
	}
	public Integer getJoinJtFightJtCnt() {
		return joinJtFightJtCnt;
	}
	public void setJoinJtFightJtCnt(Integer joinJtFightJtCnt) {
		this.joinJtFightJtCnt = joinJtFightJtCnt;
	}
	public Double getJoinJtFightJtCntRate() {
		return joinJtFightJtCntRate;
	}
	public void setJoinJtFightJtCntRate(Double joinJtFightJtCntRate) {
		this.joinJtFightJtCntRate = joinJtFightJtCntRate;
	}
	public Integer getJoinJtFightActCnt() {
		return joinJtFightActCnt;
	}
	public void setJoinJtFightActCnt(Integer joinJtFightActCnt) {
		this.joinJtFightActCnt = joinJtFightActCnt;
	}
	public Double getJoinJtFightActCntRate() {
		return joinJtFightActCntRate;
	}
	public void setJoinJtFightActCntRate(Double joinJtFightActCntRate) {
		this.joinJtFightActCntRate = joinJtFightActCntRate;
	}
	public Integer getLvl() {
		return lvl;
	}
	public void setLvl(Integer lvl) {
		this.lvl = lvl;
	}
	public Integer getLvlJtCnt() {
		return lvlJtCnt;
	}
	public void setLvlJtCnt(Integer lvlJtCnt) {
		this.lvlJtCnt = lvlJtCnt;
	}
	public Double getLvlJtCntRate() {
		return lvlJtCntRate;
	}
	public void setLvlJtCntRate(Double lvlJtCntRate) {
		this.lvlJtCntRate = lvlJtCntRate;
	}
	public Integer getJtjxActCnt() {
		return jtjxActCnt;
	}
	public void setJtjxActCnt(Integer jtjxActCnt) {
		this.jtjxActCnt = jtjxActCnt;
	}
	public Double getJtjxActCntRate() {
		return jtjxActCntRate;
	}
	public void setJtjxActCntRate(Double jtjxActCntRate) {
		this.jtjxActCntRate = jtjxActCntRate;
	}
	public Integer getJtjzMoney() {
		return jtjzMoney;
	}
	public void setJtjzMoney(Integer jtjzMoney) {
		this.jtjzMoney = jtjzMoney;
	}
	public Double getAvgJtjzMoney() {
		return avgJtjzMoney;
	}
	public void setAvgJtjzMoney(Double avgJtjzMoney) {
		this.avgJtjzMoney = avgJtjzMoney;
	}
	public Integer getJbjxcnt() {
		return jbjxcnt;
	}
	public void setJbjxcnt(Integer jbjxcnt) {
		this.jbjxcnt = jbjxcnt;
	}
	public Double getJbjxCntRate() {
		return jbjxCntRate;
	}
	public void setJbjxCntRate(Double jbjxCntRate) {
		this.jbjxCntRate = jbjxCntRate;
	}
	public Integer getKjjxcnt() {
		return kjjxcnt;
	}
	public void setKjjxcnt(Integer kjjxcnt) {
		this.kjjxcnt = kjjxcnt;
	}
	public Double getKjjxCntRate() {
		return kjjxCntRate;
	}
	public void setKjjxCntRate(Double kjjxCntRate) {
		this.kjjxCntRate = kjjxCntRate;
	}
	public Integer getStageid() {
		return stageid;
	}
	public void setStageid(Integer stageid) {
		this.stageid = stageid;
	}
	public Integer getOpenStageJtCnt() {
		return openStageJtCnt;
	}
	public void setOpenStageJtCnt(Integer openStageJtCnt) {
		this.openStageJtCnt = openStageJtCnt;
	}
	public Integer getSuccessJtCnt() {
		return successJtCnt;
	}
	public void setSuccessJtCnt(Integer successJtCnt) {
		this.successJtCnt = successJtCnt;
	}
	public Double getSuccessJtCntRate() {
		return successJtCntRate;
	}
	public void setSuccessJtCntRate(Double successJtCntRate) {
		this.successJtCntRate = successJtCntRate;
	}
	public Integer getWcxsrwJtCnt() {
		return wcxsrwJtCnt;
	}
	public void setWcxsrwJtCnt(Integer wcxsrwJtCnt) {
		this.wcxsrwJtCnt = wcxsrwJtCnt;
	}
	public Double getWcxsrwJtCntRate() {
		return wcxsrwJtCntRate;
	}
	public void setWcxsrwJtCntRate(Double wcxsrwJtCntRate) {
		this.wcxsrwJtCntRate = wcxsrwJtCntRate;
	}
	public Integer getTimes() {
		return times;
	}
	public void setTimes(Integer times) {
		this.times = times;
	}
	public Double getJtCntRate() {
		return jtCntRate;
	}
	public void setJtCntRate(Double jtCntRate) {
		this.jtCntRate = jtCntRate;
	}
	public Integer getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
	}
	public Integer getBugCnt() {
		return bugCnt;
	}
	public void setBugCnt(Integer bugCnt) {
		this.bugCnt = bugCnt;
	}
	public Double getBugCntRate() {
		return bugCntRate;
	}
	public void setBugCntRate(Double bugCntRate) {
		this.bugCntRate = bugCntRate;
	}
	public Integer getFbqqActCnt() {
		return fbqqActCnt;
	}
	public void setFbqqActCnt(Integer fbqqActCnt) {
		this.fbqqActCnt = fbqqActCnt;
	}
	public Double getFbqqActCntRate() {
		return fbqqActCntRate;
	}
	public void setFbqqActCntRate(Double fbqqActCntRate) {
		this.fbqqActCntRate = fbqqActCntRate;
	}
	public Integer getFbzjqqCnt() {
		return fbzjqqCnt;
	}
	public void setFbzjqqCnt(Integer fbzjqqCnt) {
		this.fbzjqqCnt = fbzjqqCnt;
	}
	public Double getFbzjqqCntRate() {
		return fbzjqqCntRate;
	}
	public void setFbzjqqCntRate(Double fbzjqqCntRate) {
		this.fbzjqqCntRate = fbzjqqCntRate;
	}
	public Integer getFbzzqqCnt() {
		return fbzzqqCnt;
	}
	public void setFbzzqqCnt(Integer fbzzqqCnt) {
		this.fbzzqqCnt = fbzzqqCnt;
	}
	public Double getFbzzqqCntRate() {
		return fbzzqqCntRate;
	}
	public void setFbzzqqCntRate(Double fbzzqqCntRate) {
		this.fbzzqqCntRate = fbzzqqCntRate;
	}
	public Integer getZsActCnt() {
		return zsActCnt;
	}
	public void setZsActCnt(Integer zsActCnt) {
		this.zsActCnt = zsActCnt;
	}
	public Double getZsActCntRate() {
		return zsActCntRate;
	}
	public void setZsActCntRate(Double zsActCntRate) {
		this.zsActCntRate = zsActCntRate;
	}
	public Integer getZszjCnt() {
		return zszjCnt;
	}
	public void setZszjCnt(Integer zszjCnt) {
		this.zszjCnt = zszjCnt;
	}
	public Integer getZszzCnt() {
		return zszzCnt;
	}
	public void setZszzCnt(Integer zszzCnt) {
		this.zszzCnt = zszzCnt;
	}
	public Double getZszjCntRate() {
		return zszjCntRate;
	}
	public void setZszjCntRate(Double zszjCntRate) {
		this.zszjCntRate = zszjCntRate;
	}
	public Double getZszzCntRate() {
		return zszzCntRate;
	}
	public void setZszzCntRate(Double zszzCntRate) {
		this.zszzCntRate = zszzCntRate;
	}
	public Integer getQqCnt() {
		return qqCnt;
	}
	public void setQqCnt(Integer qqCnt) {
		this.qqCnt = qqCnt;
	}
	public Integer getYwcqqCnt() {
		return ywcqqCnt;
	}
	public void setYwcqqCnt(Integer ywcqqCnt) {
		this.ywcqqCnt = ywcqqCnt;
	}
	public Double getYwcqqCntRate() {
		return ywcqqCntRate;
	}
	public void setYwcqqCntRate(Double ywcqqCntRate) {
		this.ywcqqCntRate = ywcqqCntRate;
	}
	public Integer getYgxqqCnt() {
		return ygxqqCnt;
	}
	public void setYgxqqCnt(Integer ygxqqCnt) {
		this.ygxqqCnt = ygxqqCnt;
	}
	public Integer getKgxqqCnt() {
		return kgxqqCnt;
	}
	public void setKgxqqCnt(Integer kgxqqCnt) {
		this.kgxqqCnt = kgxqqCnt;
	}
	public Double getYgxqqCntRate() {
		return ygxqqCntRate;
	}
	public void setYgxqqCntRate(Double ygxqqCntRate) {
		this.ygxqqCntRate = ygxqqCntRate;
	}
	@Override
	public String toString() {
		return "YhJtStat [avgJtjzMoney=" + avgJtjzMoney + ", bugCnt=" + bugCnt
				+ ", bugCntRate=" + bugCntRate + ", fbqqActCnt=" + fbqqActCnt
				+ ", fbqqActCntRate=" + fbqqActCntRate + ", fbzjqqCnt="
				+ fbzjqqCnt + ", fbzjqqCntRate=" + fbzjqqCntRate
				+ ", fbzzqqCnt=" + fbzzqqCnt + ", fbzzqqCntRate="
				+ fbzzqqCntRate + ", goodsid=" + goodsid + ", jbjxCntRate="
				+ jbjxCntRate + ", jbjxcnt=" + jbjxcnt + ", joinJtActCnt="
				+ joinJtActCnt + ", joinJtActCntRate=" + joinJtActCntRate
				+ ", joinJtFightActCnt=" + joinJtFightActCnt
				+ ", joinJtFightActCntRate=" + joinJtFightActCntRate
				+ ", joinJtFightJtCnt=" + joinJtFightJtCnt
				+ ", joinJtFightJtCntRate=" + joinJtFightJtCntRate
				+ ", joinPurchaseJtCnt=" + joinPurchaseJtCnt
				+ ", joinPurchaseJtCntRate=" + joinPurchaseJtCntRate
				+ ", jtCnt=" + jtCnt + ", jtCntRate=" + jtCntRate
				+ ", jtjxActCnt=" + jtjxActCnt + ", jtjxActCntRate="
				+ jtjxActCntRate + ", jtjzMoney=" + jtjzMoney + ", kgxqqCnt="
				+ kgxqqCnt + ", kjjxCntRate=" + kjjxCntRate + ", kjjxcnt="
				+ kjjxcnt + ", lvl=" + lvl + ", lvlJtCnt=" + lvlJtCnt
				+ ", lvlJtCntRate=" + lvlJtCntRate + ", openQdrqJtCnt="
				+ openQdrqJtCnt + ", openQdrqJtCntRate=" + openQdrqJtCntRate
				+ ", openStageJtCnt=" + openStageJtCnt + ", qqCnt=" + qqCnt
				+ ", stageid=" + stageid + ", successJtCnt=" + successJtCnt
				+ ", successJtCntRate=" + successJtCntRate + ", times=" + times
				+ ", wcxsrwJtCnt=" + wcxsrwJtCnt + ", wcxsrwJtCntRate="
				+ wcxsrwJtCntRate + ", ygxqqCnt=" + ygxqqCnt
				+ ", ygxqqCntRate=" + ygxqqCntRate + ", ywcqqCnt=" + ywcqqCnt
				+ ", ywcqqCntRate=" + ywcqqCntRate + ", zsActCnt=" + zsActCnt
				+ ", zsActCntRate=" + zsActCntRate + ", zszjCnt=" + zszjCnt
				+ ", zszjCntRate=" + zszjCntRate + ", zszzCnt=" + zszzCnt
				+ ", zszzCntRate=" + zszzCntRate + "]";
	}
	
}	
