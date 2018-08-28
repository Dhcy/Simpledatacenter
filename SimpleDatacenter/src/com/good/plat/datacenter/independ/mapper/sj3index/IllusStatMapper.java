package com.good.plat.datacenter.independ.mapper.sj3index;

import java.util.List;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.IllusStat;

/**
 * 图鉴
 * @ClassName: IllusStatMapper
 * @Description: TODO
 * @author hwj
 * @date 2018-1-11 上午10:12:03
 */
public interface IllusStatMapper {
	/**
	 * 图鉴激活缘分数量
	 * @Title: selectIllusLuckNumList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<IllusStat>
	 * @author hwj
	 * @date 2018-1-11 上午10:26:09
	 */
	public List<IllusStat> selectIllusLuckNumList(IndependBaseSearchData searchData)throws Exception;
}
