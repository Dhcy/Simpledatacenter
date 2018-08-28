package com.good.plat.datacenter.common.base;

/**
 * @ClassName: BaseEntity
 * @Description: 返回结果父类
 * @author dmw
 * @date 2016年3月14日 下午6:00:54
 */
public class BaseEntity {

	public BaseEntity() {
	}
	
	//游戏
	private Integer gameid;
	private String gameName;
	
	//渠道
	private Integer channelid;
	private String channelName;
	
	//子渠道
	private String subchannelid;
	
	//区服
	private Integer areaid;
	private String areaName;
	
	//日期
	private String statdate;
	
	//查询类型
	private String statType;
	
	//百分比
	private Double rate;
	
	/**
	 * 游戏的url,key,cmdId
	 * */
	private String url;
	private String key;
	private Integer cmdId;
	//操作系统类型
	private Integer osType;
	private String sourceSys;
	//区域
	private String city;
	public Integer getGameid() {
		return gameid;
	}

	public void setGameid(Integer gameid) {
		this.gameid = gameid;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public Integer getChannelid() {
		return channelid;
	}

	public void setChannelid(Integer channelid) {
		this.channelid = channelid;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getSubchannelid() {
		return subchannelid;
	}

	public void setSubchannelid(String subchannelid) {
		this.subchannelid = subchannelid;
	}

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getStatdate() {
		return statdate;
	}

	public void setStatdate(String statdate) {
		this.statdate = statdate;
	}

	public String getStatType() {
		return statType;
	}

	public void setStatType(String statType) {
		this.statType = statType;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getCmdId() {
		return cmdId;
	}

	public void setCmdId(Integer cmdId) {
		this.cmdId = cmdId;
	}
	public Integer getOsType() {
		return osType;
	}

	public void setOsType(Integer osType) {
		this.osType = osType;
	}

	public String getSourceSys() {
		return sourceSys;
	}

	public void setSourceSys(String sourceSys) {
		this.sourceSys = sourceSys;
	}

	public void handleChineseForOs(BaseEntity entity){
		if(entity.getOsType()==1){
			entity.setSourceSys("Ios");
		}else if(entity.getOsType()==2){
			entity.setSourceSys("Android");
		}else if(entity.getOsType()==3){
			entity.setSourceSys("Winphone");
		}else if(entity.getOsType()==4){
			entity.setSourceSys("BlackBerry");
		}else if(entity.getOsType()==5){
			entity.setSourceSys("Kjava");
		}else if(entity.getOsType()==6){
			entity.setSourceSys("Windows(PC)");
		}else if(entity.getOsType()==7){
			entity.setSourceSys("IOS越狱");
		}else {
			entity.setSourceSys("系统id有误");
		}
		entity.setOsType(null);
		
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	
}
