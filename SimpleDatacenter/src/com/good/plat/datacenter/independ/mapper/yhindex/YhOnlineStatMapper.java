package com.good.plat.datacenter.independ.mapper.yhindex;

import java.util.List;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.yhindex.YhOnlineStat;

/**
 * 银河在线时长
 * @ClassName: YhOnlineStatMapper
 * @Description: TODO
 * @author hwj
 * @date 2017-12-27 下午06:24:15
 */
public interface YhOnlineStatMapper {
	/**
	 * 在线时长
	 * @Title: selectYhOnlineStatList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhOnlineStat>
	 * @author hwj
	 * @date 2017-12-27 下午06:35:36
	 */
	public List<YhOnlineStat> selectYhOnlineStatList(IndependBaseSearchData searchData)throws Exception;
	/**
	 * 登入次数
	 * @Title: selectYhLoginTimesList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhOnlineStat>
	 * @author hwj
	 * @date 2017-12-28 下午02:37:33
	 */
	public List<YhOnlineStat> selectYhLoginTimesList(IndependBaseSearchData searchData)throws Exception;
	
}
