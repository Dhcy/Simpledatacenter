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
 * @author moxw
 * @date 2016年12月8日 下午10:46:38
 */
public class WarningBaseSearchData extends BaseSearchData {

	private static final long serialVersionUID = 1L;

	public WarningBaseSearchData() {
	}

	// 游戏ID
	private Integer offset;
	private Integer pageLength;
	
	public WarningBaseSearchData searchDataFilter(WarningBaseSearchData searchData) {
		//坑爹的mybatis貌似不能判断list的size
		searchData = (WarningBaseSearchData) super.searchDataFilter(searchData);
		
		if (searchData.getOffset() != null ) {
			
		}
		if (searchData.getPageLength() != null ) {
			
		}
		return searchData;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getPageLength() {
		return pageLength;
	}

	public void setPageLength(Integer pageLength) {
		this.pageLength = pageLength;
	}
}
