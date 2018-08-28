package com.good.plat.datacenter.datastat.entity.appsflyer;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.good.plat.datacenter.common.base.BaseSearchData;
/**
 * appflyer推送数据
 * @ClassName: AppsFlyer
 * @Description: TODO
 * @author hwj
 * @date 2017-6-2 下午03:58:05
 */
public class AppsFlyer extends BaseSearchData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	//推送日期
    private Date dateTime;
	//安装日期
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date installTime;
	//平台
	private String platform;
	//事件名称
	private String eventName;
	//推送数据
	private String afData;
	//(appid 区分哪个游戏标识)
	private String appId;
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDateTime() {
		return dateTime;
	}
	public String getAfData() {
		return afData;
	}
	public void setAfData(String afData) {
		this.afData = afData;
	}
	public Date getInstallTime() {
		return installTime;
	}
	public void setInstallTime(Date installTime) {
		this.installTime = installTime;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	
	
	
	
	
}
