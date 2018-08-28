package com.good.plat.datacenter.independ.entity.yhindex;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

/**
 * 灯塔数据
 * @ClassName: YhLightHouceData
 * @Description: TODO
 * @author hwj
 * @date 2018-5-14 下午01:02:23
 */
public class YhLightHouceData extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer hour;//时间段
	private Integer type;//环节
	private Integer succnt;//成功次数
	private Double failRate;
	private String hourText;
	//启动游戏(打开游戏时)
	private Integer startGameSuccnt;
	private Integer startGameCnt;
	private Double startGameFailcntRate;
	//游戏初始化(游戏初始化完成)
	private Integer gameInitSuccnt;
	private Integer gameInitCnt;
	private Double gameInitFailcntRate;
	//解压数据(拷贝和解压包体内置资源)
	private Integer decompDataSuccnt;
	private Integer decompDataCnt;
	private Double decompDataFailcntRate;
	//检查更新(获取资源服地址和检查资源更新（检测是否全量更新）)
	private Integer checkUpdateSuccnt;
	private Integer checkUpdateCnt;
	private Double checkUpdateFailcntRate;
	//更新(更新游戏资源，包括下载和解压)
	private Integer updateResSuccnt;
	private Integer updateResCnt;
	private Double updateResFailcntRate;
	//登录鉴权(各种渠道SDK的鉴权)
	private Integer loginAuthSuccnt;
	private Integer loginAuthCnt;
	private Double loginAuthFailcntRate;
	//登录游戏服(服务端进入游戏)
	private Integer loginGameServerSuccnt;
	private Integer loginGameServerCnt;
	private Double loginGameServerFailcntRate;
	//进入大厅(客户端进入游戏)
	private Integer enterGameSuccnt;
	private Integer enterGameCnt;
	private Double enterGameFailcntRate;
	
	//FPS分布
	private String qjtype;//区间类型
	private Integer times;//fps次数
	private Double fpsRate;//fps占比
	//机型fps占比
	private String systemh;//手机机型
	private Double avgFps0_14Rate;
	private Double avgFps15_19Rate;
	private Double avgFps20_24Rate;
	private Double avgFps25_30Rate;
	private Double avgFps31_40Rate;
	private Double avgFps41_60Rate;
	
	//网络延迟时间段
	private Double avgDelayTime_3G;
	private Double avgDelayTime_4G;
	private Double avgDelayTime_WIFI;
	//平均延时分段占比
	private Integer count;//人数
	public Integer getHour() {
		return hour;
	}
	public void setHour(Integer hour) {
		this.hour = hour;
	}
	public String getHourText() {
		return hourText;
	}
	public void setHourText(String hourText) {
		this.hourText = hourText;
	}
	public Integer getStartGameSuccnt() {
		return startGameSuccnt;
	}
	public void setStartGameSuccnt(Integer startGameSuccnt) {
		this.startGameSuccnt = startGameSuccnt;
	}
	public Integer getStartGameCnt() {
		return startGameCnt;
	}
	public void setStartGameCnt(Integer startGameCnt) {
		this.startGameCnt = startGameCnt;
	}
	public Double getStartGameFailcntRate() {
		return startGameFailcntRate;
	}
	public void setStartGameFailcntRate(Double startGameFailcntRate) {
		this.startGameFailcntRate = startGameFailcntRate;
	}
	public Integer getGameInitSuccnt() {
		return gameInitSuccnt;
	}
	public void setGameInitSuccnt(Integer gameInitSuccnt) {
		this.gameInitSuccnt = gameInitSuccnt;
	}
	public Integer getGameInitCnt() {
		return gameInitCnt;
	}
	public void setGameInitCnt(Integer gameInitCnt) {
		this.gameInitCnt = gameInitCnt;
	}
	public Double getGameInitFailcntRate() {
		return gameInitFailcntRate;
	}
	public void setGameInitFailcntRate(Double gameInitFailcntRate) {
		this.gameInitFailcntRate = gameInitFailcntRate;
	}
	public Integer getDecompDataSuccnt() {
		return decompDataSuccnt;
	}
	public void setDecompDataSuccnt(Integer decompDataSuccnt) {
		this.decompDataSuccnt = decompDataSuccnt;
	}
	public Integer getDecompDataCnt() {
		return decompDataCnt;
	}
	public void setDecompDataCnt(Integer decompDataCnt) {
		this.decompDataCnt = decompDataCnt;
	}
	public Double getDecompDataFailcntRate() {
		return decompDataFailcntRate;
	}
	public void setDecompDataFailcntRate(Double decompDataFailcntRate) {
		this.decompDataFailcntRate = decompDataFailcntRate;
	}
	public Integer getCheckUpdateSuccnt() {
		return checkUpdateSuccnt;
	}
	public void setCheckUpdateSuccnt(Integer checkUpdateSuccnt) {
		this.checkUpdateSuccnt = checkUpdateSuccnt;
	}
	public Integer getCheckUpdateCnt() {
		return checkUpdateCnt;
	}
	public void setCheckUpdateCnt(Integer checkUpdateCnt) {
		this.checkUpdateCnt = checkUpdateCnt;
	}
	public Double getCheckUpdateFailcntRate() {
		return checkUpdateFailcntRate;
	}
	public void setCheckUpdateFailcntRate(Double checkUpdateFailcntRate) {
		this.checkUpdateFailcntRate = checkUpdateFailcntRate;
	}
	public Integer getUpdateResSuccnt() {
		return updateResSuccnt;
	}
	public void setUpdateResSuccnt(Integer updateResSuccnt) {
		this.updateResSuccnt = updateResSuccnt;
	}
	public Integer getUpdateResCnt() {
		return updateResCnt;
	}
	public void setUpdateResCnt(Integer updateResCnt) {
		this.updateResCnt = updateResCnt;
	}
	public Double getUpdateResFailcntRate() {
		return updateResFailcntRate;
	}
	public void setUpdateResFailcntRate(Double updateResFailcntRate) {
		this.updateResFailcntRate = updateResFailcntRate;
	}
	public Integer getLoginAuthSuccnt() {
		return loginAuthSuccnt;
	}
	public void setLoginAuthSuccnt(Integer loginAuthSuccnt) {
		this.loginAuthSuccnt = loginAuthSuccnt;
	}
	public Integer getLoginAuthCnt() {
		return loginAuthCnt;
	}
	public void setLoginAuthCnt(Integer loginAuthCnt) {
		this.loginAuthCnt = loginAuthCnt;
	}
	public Double getLoginAuthFailcntRate() {
		return loginAuthFailcntRate;
	}
	public void setLoginAuthFailcntRate(Double loginAuthFailcntRate) {
		this.loginAuthFailcntRate = loginAuthFailcntRate;
	}
	public Integer getLoginGameServerSuccnt() {
		return loginGameServerSuccnt;
	}
	public void setLoginGameServerSuccnt(Integer loginGameServerSuccnt) {
		this.loginGameServerSuccnt = loginGameServerSuccnt;
	}
	public Integer getLoginGameServerCnt() {
		return loginGameServerCnt;
	}
	public void setLoginGameServerCnt(Integer loginGameServerCnt) {
		this.loginGameServerCnt = loginGameServerCnt;
	}
	public Double getLoginGameServerFailcntRate() {
		return loginGameServerFailcntRate;
	}
	public void setLoginGameServerFailcntRate(Double loginGameServerFailcntRate) {
		this.loginGameServerFailcntRate = loginGameServerFailcntRate;
	}
	public Integer getEnterGameSuccnt() {
		return enterGameSuccnt;
	}
	public void setEnterGameSuccnt(Integer enterGameSuccnt) {
		this.enterGameSuccnt = enterGameSuccnt;
	}
	public Integer getEnterGameCnt() {
		return enterGameCnt;
	}
	public void setEnterGameCnt(Integer enterGameCnt) {
		this.enterGameCnt = enterGameCnt;
	}
	public Double getEnterGameFailcntRate() {
		return enterGameFailcntRate;
	}
	public void setEnterGameFailcntRate(Double enterGameFailcntRate) {
		this.enterGameFailcntRate = enterGameFailcntRate;
	}
	public String getQjtype() {
		return qjtype;
	}
	public void setQjtype(String qjtype) {
		this.qjtype = qjtype;
	}
	public Integer getTimes() {
		return times;
	}
	public void setTimes(Integer times) {
		this.times = times;
	}
	public Double getFpsRate() {
		return fpsRate;
	}
	public void setFpsRate(Double fpsRate) {
		this.fpsRate = fpsRate;
	}
	public String getSystemh() {
		return systemh;
	}
	public void setSystemh(String systemh) {
		this.systemh = systemh;
	}
	public Double getAvgFps0_14Rate() {
		return avgFps0_14Rate;
	}
	public void setAvgFps0_14Rate(Double avgFps0_14Rate) {
		this.avgFps0_14Rate = avgFps0_14Rate;
	}
	public Double getAvgFps15_19Rate() {
		return avgFps15_19Rate;
	}
	public void setAvgFps15_19Rate(Double avgFps15_19Rate) {
		this.avgFps15_19Rate = avgFps15_19Rate;
	}
	public Double getAvgFps20_24Rate() {
		return avgFps20_24Rate;
	}
	public void setAvgFps20_24Rate(Double avgFps20_24Rate) {
		this.avgFps20_24Rate = avgFps20_24Rate;
	}
	public Double getAvgFps25_30Rate() {
		return avgFps25_30Rate;
	}
	public void setAvgFps25_30Rate(Double avgFps25_30Rate) {
		this.avgFps25_30Rate = avgFps25_30Rate;
	}
	public Double getAvgFps31_40Rate() {
		return avgFps31_40Rate;
	}
	public void setAvgFps31_40Rate(Double avgFps31_40Rate) {
		this.avgFps31_40Rate = avgFps31_40Rate;
	}
	public Double getAvgFps41_60Rate() {
		return avgFps41_60Rate;
	}
	public void setAvgFps41_60Rate(Double avgFps41_60Rate) {
		this.avgFps41_60Rate = avgFps41_60Rate;
	}
	public Double getAvgDelayTime_3G() {
		return avgDelayTime_3G;
	}
	public void setAvgDelayTime_3G(Double avgDelayTime_3G) {
		this.avgDelayTime_3G = avgDelayTime_3G;
	}
	public Double getAvgDelayTime_4G() {
		return avgDelayTime_4G;
	}
	public void setAvgDelayTime_4G(Double avgDelayTime_4G) {
		this.avgDelayTime_4G = avgDelayTime_4G;
	}
	public Double getAvgDelayTime_WIFI() {
		return avgDelayTime_WIFI;
	}
	public void setAvgDelayTime_WIFI(Double avgDelayTimeWIFI) {
		avgDelayTime_WIFI = avgDelayTimeWIFI;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getSuccnt() {
		return succnt;
	}
	public void setSuccnt(Integer succnt) {
		this.succnt = succnt;
	}
	public Double getFailRate() {
		return failRate;
	}
	public void setFailRate(Double failRate) {
		this.failRate = failRate;
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
