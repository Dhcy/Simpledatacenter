package com.good.plat.datacenter.common.base;

import java.io.Serializable;

/**
 * @ClassName: BaseSearchData
 * @Description: 搜索父类
 * @author hwj
 * @date 2016年3月8日 下午10:46:38
 */
public class CommunityStatisticsBaseSearchData implements Serializable {


	private static final long serialVersionUID = 1L;

	public CommunityStatisticsBaseSearchData() {
	}
	
	// 开始日期
	private String startdate;
	
	// 结束日期
	private String enddate;
	//页面url
	private String url;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	

	
}
