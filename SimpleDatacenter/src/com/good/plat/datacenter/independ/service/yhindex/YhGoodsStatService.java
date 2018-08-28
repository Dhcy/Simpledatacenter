package com.good.plat.datacenter.independ.service.yhindex;

import java.util.List;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.yhindex.YhGoodsStat;

/**
 * 商品统计
 * @ClassName: YhGoodsStatService
 * @Description: TODO
 * @author hwj
 * @date 2017-12-29 上午10:03:38
 */
public interface YhGoodsStatService {
	/**
	 * 商品贩卖
	 * @Title: selectYhGoodsSoldList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhGoodsStat>
	 * @author hwj
	 * @date 2017-12-29 上午10:02:31
	 */
	public List<YhGoodsStat> selectYhGoodsSoldList(IndependBaseSearchData searchData)throws Exception;

}
