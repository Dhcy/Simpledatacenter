package com.good.plat.datacenter.independ.mapper.yhindex;

import java.util.List;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.yhindex.YhGoodsStat;

/**
 * 商品统计
 * @ClassName: YhGoodsStatMapper
 * @Description: TODO
 * @author hwj
 * @date 2017-12-29 上午09:54:02
 */
public interface YhGoodsStatMapper {
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
