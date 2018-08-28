package com.good.plat.datacenter.independ.service.yhindex;

import java.util.List;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.yhindex.YhIframeStat;

public interface YhIframeStatService {
	/**
	 * 银河头框/头像统计
	 * @Title: selectYhIframeStatList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhIframeStat>
	 * @author hwj
	 * @date 2018-5-21 下午06:33:35
	 */
	public List<YhIframeStat> selectYhIframeStatList(IndependBaseSearchData searchData)throws Exception;
}
