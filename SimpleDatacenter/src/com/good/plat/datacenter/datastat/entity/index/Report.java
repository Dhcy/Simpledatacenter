package com.good.plat.datacenter.datastat.entity.index;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

/**
 * 运营报表
 * @ClassName: report
 * @Description: TODO
 * @author hwj
 * @date 2017-2-10 下午02:34:50
 */
public class Report extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	public Report() {
	}
	//设备激活
	public Integer deviceActivation = 0;
	//新增设备
	private Integer new_device=0;
	
	//新增账号
	private Integer newUser = 0;
	private Integer newActor=0;
	
	//玩家转化率
	private Double playerConv =0d;
	//活跃玩家
	private Integer actv_user=0;
	//acu
	private Integer acu=0;
	//pcu
	private Integer pcu=0;
	
	//游戏次数
	private Integer gameCounts=0;
	//平均游戏时长
	private Double avgGameTime = 0d;
	
	//平均游戏次数
	private Double avgGameCount = 0d;
	
	/**
	 * 收入数据
	 */
	//收入
	private Double money=0d;
	//新增付费玩家数量
	private Integer npayact=0;
	//付费玩家数量
	private Integer payact=0;
	//日付费率
	private String dPayRate;
	//活跃ARPU
	private Double act_arpu=0d;
	//活跃ARPPU
	private Double act_arppu=0d;
	//新增ARPU
	private Double new_arpu=0d;
	//新增ARPPU
	private Double new_arppu=0d;
	
	
	/**
	 * 新增账户留存
	 */
	//次日留存率
	private Double newuserDay2 = 0d;
	//3日留存率
	private Double newuserDay3 = 0d;
	//7日留存率
	private Double newuserDay7 = 0d;
	
	//次日留存率
	private Double actvuserDay2 = 0d;
	//3日留存率
	private Double actvuserDay3 = 0d;
	//7日留存率
	private Double actvuserDay7 = 0d;
	
	
	/**
	 * 激活设备留存
	 */
	//次日留存率
	private Double deviceDay2 = 0d;
	
	//7日留存率
	private Double deviceDay7 = 0d;
	
	//3日留存率
	private Double deviceDay3 = 0d;
	
	/**
	 * 周报跟月报
	 */
	//周总设备数
	private Integer week_total_device_Cnt;
	//周新增玩家数
	private Integer week_new_play_count;
	//周活跃玩家数
	private Integer week_act_play_count;
	//周平均游戏时长
	private Double week_avg_game_time;
	//周平均游戏次数
	private Double week_avg_game_count;
	//周平均每日游戏时长
	private Double week_avg_daily_game_time;
	//周arpu
	private Double week_arpu;
	//周收入
	private Double week_income;
	//周充值玩家数
	private Integer week_recharge_cnt;
	//周玩家付费率
	private Double week_pay_rate;
	//周流失玩家数
	private Integer week_lostcnt;
	//周流失率
	private Double week_lostrate;
	//周新增玩家次日留存
	private Double week_day2ret;
	//周回流玩家数
	private Integer week_returncnt;
	//设置显示对比日期的标识(0:周;1：月)
	private Integer flag;
	//周arppu
	private Double week_arppu;
	
	public Integer getDeviceActivation() {
		return deviceActivation;
	}

	public void setDeviceActivation(Integer deviceActivation) {
		this.deviceActivation = deviceActivation;
	}

	public Integer getNewUser() {
		return newUser;
	}

	public void setNewUser(Integer newUser) {
		this.newUser = newUser;
	}

	public Integer getNew_device() {
		return new_device;
	}

	public void setNew_device(Integer newDevice) {
		new_device = newDevice;
	}

	public Double getPlayerConv() {
		return playerConv;
	}

	public void setPlayerConv(Double playerConv) {
		this.playerConv = playerConv;
	}

	public Integer getActv_user() {
		return actv_user;
	}

	public void setActv_user(Integer actvUser) {
		actv_user = actvUser;
	}

	public Integer getAcu() {
		return acu;
	}

	public void setAcu(Integer acu) {
		this.acu = acu;
	}

	public Integer getPcu() {
		return pcu;
	}

	public void setPcu(Integer pcu) {
		this.pcu = pcu;
	}

	public Integer getGameCounts() {
		return gameCounts;
	}

	public void setGameCounts(Integer gameCounts) {
		this.gameCounts = gameCounts;
	}

	public Double getAvgGameTime() {
		return avgGameTime;
	}

	public void setAvgGameTime(Double avgGameTime) {
		this.avgGameTime = avgGameTime;
	}

	public Double getAvgGameCount() {
		return avgGameCount;
	}

	public void setAvgGameCount(Double avgGameCount) {
		this.avgGameCount = avgGameCount;
	}

	public Double getNewuserDay2() {
		return newuserDay2;
	}

	public void setNewuserDay2(Double newuserDay2) {
		this.newuserDay2 = newuserDay2;
	}

	public Double getNewuserDay3() {
		return newuserDay3;
	}

	public void setNewuserDay3(Double newuserDay3) {
		this.newuserDay3 = newuserDay3;
	}

	public Double getNewuserDay7() {
		return newuserDay7;
	}

	public void setNewuserDay7(Double newuserDay7) {
		this.newuserDay7 = newuserDay7;
	}

	public Double getActvuserDay2() {
		return actvuserDay2;
	}

	public void setActvuserDay2(Double actvuserDay2) {
		this.actvuserDay2 = actvuserDay2;
	}

	public Double getActvuserDay3() {
		return actvuserDay3;
	}

	public void setActvuserDay3(Double actvuserDay3) {
		this.actvuserDay3 = actvuserDay3;
	}

	public Double getActvuserDay7() {
		return actvuserDay7;
	}

	public void setActvuserDay7(Double actvuserDay7) {
		this.actvuserDay7 = actvuserDay7;
	}

	public Double getDeviceDay2() {
		return deviceDay2;
	}

	public void setDeviceDay2(Double deviceDay2) {
		this.deviceDay2 = deviceDay2;
	}

	public Double getDeviceDay7() {
		return deviceDay7;
	}

	public void setDeviceDay7(Double deviceDay7) {
		this.deviceDay7 = deviceDay7;
	}

	public Double getDeviceDay3() {
		return deviceDay3;
	}

	public void setDeviceDay3(Double deviceDay3) {
		this.deviceDay3 = deviceDay3;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Integer getNpayact() {
		return npayact;
	}

	public void setNpayact(Integer npayact) {
		this.npayact = npayact;
	}

	public Integer getPayact() {
		return payact;
	}

	public void setPayact(Integer payact) {
		this.payact = payact;
	}

	public String getdPayRate() {
		return dPayRate;
	}

	public void setdPayRate(String dPayRate) {
		this.dPayRate = dPayRate;
	}


	public Double getAct_arpu() {
		return act_arpu;
	}

	public void setAct_arpu(Double actArpu) {
		act_arpu = actArpu;
	}

	public Double getAct_arppu() {
		return act_arppu;
	}

	public void setAct_arppu(Double actArppu) {
		act_arppu = actArppu;
	}

	public Double getNew_arpu() {
		return new_arpu;
	}

	public void setNew_arpu(Double newArpu) {
		new_arpu = newArpu;
	}

	public Double getNew_arppu() {
		return new_arppu;
	}

	public void setNew_arppu(Double newArppu) {
		new_arppu = newArppu;
	}


	public Integer getWeek_total_device_Cnt() {
		return week_total_device_Cnt;
	}

	public void setWeek_total_device_Cnt(Integer weekTotalDeviceCnt) {
		week_total_device_Cnt = weekTotalDeviceCnt;
	}

	public Integer getWeek_new_play_count() {
		return week_new_play_count;
	}

	public void setWeek_new_play_count(Integer weekNewPlayCount) {
		week_new_play_count = weekNewPlayCount;
	}

	public Integer getWeek_act_play_count() {
		return week_act_play_count;
	}

	public void setWeek_act_play_count(Integer weekActPlayCount) {
		week_act_play_count = weekActPlayCount;
	}

	public Double getWeek_avg_game_time() {
		return week_avg_game_time;
	}

	public void setWeek_avg_game_time(Double weekAvgGameTime) {
		week_avg_game_time = weekAvgGameTime;
	}

	public Double getWeek_avg_game_count() {
		return week_avg_game_count;
	}

	public void setWeek_avg_game_count(Double weekAvgGameCount) {
		week_avg_game_count = weekAvgGameCount;
	}

	public Double getWeek_avg_daily_game_time() {
		return week_avg_daily_game_time;
	}

	public void setWeek_avg_daily_game_time(Double weekAvgDailyGameTime) {
		week_avg_daily_game_time = weekAvgDailyGameTime;
	}

	public Double getWeek_arpu() {
		return week_arpu;
	}

	public void setWeek_arpu(Double weekArpu) {
		week_arpu = weekArpu;
	}

	public Double getWeek_income() {
		return week_income;
	}

	public void setWeek_income(Double weekIncome) {
		week_income = weekIncome;
	}

	public Integer getWeek_recharge_cnt() {
		return week_recharge_cnt;
	}

	public void setWeek_recharge_cnt(Integer weekRechargeCnt) {
		week_recharge_cnt = weekRechargeCnt;
	}

	public Double getWeek_pay_rate() {
		return week_pay_rate;
	}

	public void setWeek_pay_rate(Double weekPayRate) {
		week_pay_rate = weekPayRate;
	}

	public Integer getWeek_lostcnt() {
		return week_lostcnt;
	}

	public void setWeek_lostcnt(Integer weekLostcnt) {
		week_lostcnt = weekLostcnt;
	}

	public Double getWeek_lostrate() {
		return week_lostrate;
	}

	public void setWeek_lostrate(Double weekLostrate) {
		week_lostrate = weekLostrate;
	}

	public Double getWeek_day2ret() {
		return week_day2ret;
	}

	public void setWeek_day2ret(Double weekDay2ret) {
		week_day2ret = weekDay2ret;
	}

	public Integer getWeek_returncnt() {
		return week_returncnt;
	}

	public void setWeek_returncnt(Integer weekReturncnt) {
		week_returncnt = weekReturncnt;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Double getWeek_arppu() {
		return week_arppu;
	}

	public void setWeek_arppu(Double weekArppu) {
		week_arppu = weekArppu;
	}

	public Integer getNewActor() {
		return newActor;
	}

	public void setNewActor(Integer newActor) {
		this.newActor = newActor;
	}
	
	
	
	
	

}
