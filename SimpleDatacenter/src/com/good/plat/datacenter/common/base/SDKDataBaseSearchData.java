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
public class SDKDataBaseSearchData implements Serializable {

	private static final long serialVersionUID = 2L;

	public SDKDataBaseSearchData() {
	}

	// 游戏ID
	private Integer gameid;
	
	// 开始日期
	private String startdate;
	
	// 结束日期
	private String enddate;
	
	// 终端类型
	private Integer systemid;
	//界面id
	private Integer pageid;
	//资讯id
	private List<Integer> news_ids;
	//主界面id
	private String categoryId;
	//浏览量
	private Integer pv;
	//账号数
	private Integer uv;
	
	public SDKDataBaseSearchData searchDataFilter(SDKDataBaseSearchData searchData) {
		//坑爹的mybatis貌似不能判断list的size
		/*if (searchData.getChannelList() != null && searchData.getChannelList().size() == 0) {
			searchData.setChannelList(null);
			searchData.setSubchannels(null);
		}*/
		
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


	public Integer getSystemid() {
		return systemid;
	}


	public void setSystemid(Integer systemid) {
		this.systemid = systemid;
	}


	public Integer getPageid() {
		return pageid;
	}


	public void setPageid(Integer pageid) {
		this.pageid = pageid;
	}


	public List<Integer> getNews_ids() {
		return news_ids;
	}


	public void setNews_ids(List<Integer> news_ids) {
		this.news_ids = news_ids;
	}
	

	public String getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}


	public Integer getPv() {
		return pv;
	}


	public void setPv(Integer pv) {
		this.pv = pv;
	}


	public Integer getUv() {
		return uv;
	}


	public void setUv(Integer uv) {
		this.uv = uv;
	}
	
	
	
}
