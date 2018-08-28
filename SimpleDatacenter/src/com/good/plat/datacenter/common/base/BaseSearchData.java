package com.good.plat.datacenter.common.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.good.plat.datacenter.game.entity.Channel;
import com.good.plat.datacenter.game.entity.GameLanguage;
import com.good.plat.datacenter.game.entity.SubChannel;
import com.good.plat.datacenter.game.entity.TBLogplatGameVersion;

/**
 * @ClassName: BaseSearchData
 * @Description: 搜索父类
 * @author dmw
 * @date 2016年3月8日 下午10:46:38
 */
public class BaseSearchData implements Serializable {

	private static final long serialVersionUID = 1L;

	public BaseSearchData() {
	}

	// 游戏ID
	private Integer gameid;
	
	// 开始日期
	private String startdate;
	
	// 结束日期
	private String enddate;
	
	// 终端类型
	private List<Integer> termtypes;
	
	// 渠道
	private List<Channel> channelList;
	
	// 子渠道
	private List<String> subchannels = new ArrayList<String>();
	
	// 区服
	private List<Integer> areas;
	
	// 查询类型
	private String checktype1;
	
	// 查询类型
	private String checktype2;
	
	// 查询类型
	private String checktype3;
	//查询类型
	private String checktype4;
	//查询类型
	private String checktype5;
	//
	private Integer userid;
	private Integer platformid;
	private Integer projectid;
	//买量平台 
	private Integer channelid;
	private String subChannelid;
	//
	//用户渠道权限
	private List<Channel> reChannels;
	
	private List<TBLogplatGameVersion> versionList;
	private List<GameLanguage> languageList;
	//当前日期
	private String currDate;
	//区服id
	private Integer areaid;
	
	private List<String> cityList;
	private List<String> filterList;
	//游戏名
	private String gameName;
	public BaseSearchData searchDataFilter(BaseSearchData searchData) {
		//坑爹的mybatis貌似不能判断list的size
		if (searchData.getChannelList() != null && searchData.getChannelList().size() == 0) {
			searchData.setChannelList(null);
			searchData.setSubchannels(null);
		}
		
		if (searchData.getTermtypes() != null && searchData.getTermtypes().size() == 0) {
			searchData.setTermtypes(null);
		}
		
		if (searchData.getAreas() != null && searchData.getAreas().size() == 0) {
			searchData.setAreas(null);
		}
		if(searchData.getChecktype1() != null && searchData.getChecktype1().trim().length() == 0){
			searchData.setChecktype1(null);
		}
		if(searchData.getLanguageList() != null && searchData.getLanguageList().size() == 0){
			searchData.setLanguageList(null);
		}
		if(searchData.getVersionList() != null && searchData.getVersionList().size() == 0){
			searchData.setVersionList(null);
		}
		if(searchData.getCityList() != null && searchData.getCityList().size() == 0){
			searchData.setCityList(null);
		}
		List<Channel> channels = searchData.getChannelList();
		if (channels != null && channels.size() > 0) {
			for (Channel channel : channels) {
				if (channel.getSubChannelList() != null) {
					for (SubChannel sub : channel.getSubChannelList()) {
						searchData.getSubchannels().add(sub.getSubId());
					}
				}
			}
		}
		if (searchData.getSubchannels() != null && searchData.getSubchannels().size() == 0) {
			searchData.setSubchannels(null);
		}
		
		return searchData;
	}

	public Integer getGameid() {
		return gameid;
	}

	public void setGameid(Integer gameid) {
		this.gameid = gameid;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getChecktype1() {
		return checktype1;
	}

	public void setChecktype1(String checktype1) {
		this.checktype1 = checktype1;
	}
	
	public String getChecktype2() {
		return checktype2;
	}

	public void setChecktype2(String checktype2) {
		this.checktype2 = checktype2;
	}

	public String getChecktype3() {
		return checktype3;
	}

	public void setChecktype3(String checktype3) {
		this.checktype3 = checktype3;
	}

	public List<Channel> getReChannels() {
		return reChannels;
	}

	public void setReChannels(List<Channel> reChannels) {
		this.reChannels = reChannels;
	}

	public List<Integer> getTermtypes() {
		return termtypes;
	}

	public void setTermtypes(List<Integer> termtypes) {
		this.termtypes = termtypes;
	}

	public List<Channel> getChannelList() {
		return channelList;
	}

	public void setChannelList(List<Channel> channelList) {
		this.channelList = channelList;
	}

	public List<String> getSubchannels() {
		return subchannels;
	}

	public void setSubchannels(List<String> subchannels) {
		this.subchannels = subchannels;
	}

	public List<Integer> getAreas() {
		return areas;
	}

	public void setAreas(List<Integer> areas) {
		this.areas = areas;
	}

	
	public Integer getProjectid() {
		return projectid;
	}

	public void setProjectid(Integer projectid) {
		this.projectid = projectid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getPlatformid() {
		return platformid;
	}

	public void setPlatformid(Integer platformid) {
		this.platformid = platformid;
	}

	@Override
	public String toString() {
		return "BaseSearchData [areaid=" + areaid + ", areas=" + areas
				+ ", channelList=" + channelList + ", channelid=" + channelid
				+ ", checktype1=" + checktype1 + ", checktype2=" + checktype2
				+ ", checktype3=" + checktype3 + ", checktype4=" + checktype4
				+ ", checktype5=" + checktype5 + ", cityList=" + cityList
				+ ", currDate=" + currDate + ", enddate=" + enddate
				+ ", filterList=" + filterList + ", gameName=" + gameName
				+ ", gameid=" + gameid + ", languageList=" + languageList
				+ ", platformid=" + platformid + ", projectid=" + projectid
				+ ", reChannels=" + reChannels + ", startdate=" + startdate
				+ ", subChannelid=" + subChannelid + ", subchannels="
				+ subchannels + ", termtypes=" + termtypes + ", userid="
				+ userid + ", versionList=" + versionList + "]";
	}

	public Integer getChannelid() {
		return channelid;
	}

	public void setChannelid(Integer channelid) {
		this.channelid = channelid;
	}

	public String getSubChannelid() {
		return subChannelid;
	}

	public void setSubChannelid(String subChannelid) {
		this.subChannelid = subChannelid;
	}

	public List<TBLogplatGameVersion> getVersionList() {
		return versionList;
	}

	public void setVersionList(List<TBLogplatGameVersion> versionList) {
		this.versionList = versionList;
	}

	public List<GameLanguage> getLanguageList() {
		return languageList;
	}

	public void setLanguageList(List<GameLanguage> languageList) {
		this.languageList = languageList;
	}

	public String getCurrDate() {
		return currDate;
	}

	public void setCurrDate(String currDate) {
		this.currDate = currDate;
	}

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public List<String> getCityList() {
		return cityList;
	}

	public void setCityList(List<String> cityList) {
		this.cityList = cityList;
	}

	public String getChecktype4() {
		return checktype4;
	}

	public void setChecktype4(String checktype4) {
		this.checktype4 = checktype4;
	}

	public List<String> getFilterList() {
		return filterList;
	}

	public void setFilterList(List<String> filterList) {
		this.filterList = filterList;
	}

	public String getChecktype5() {
		return checktype5;
	}

	public void setChecktype5(String checktype5) {
		this.checktype5 = checktype5;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	
	
	
	
	

	
}
