package com.good.plat.datacenter.independ.service.sj3index;

import java.util.List;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.IllusStat;

/**
 * 图鉴业务
 * @ClassName: IllusStatService
 * @Description: TODO
 * @author hwj
 * @date 2018-1-11 上午10:27:51
 */
public interface IllusStatService {
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
