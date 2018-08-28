package com.good.plat.datacenter.independ.mapper.yhindex;

import java.util.List;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.yhindex.YhCurrencyStat;

/**
 * 货币统计
 * @ClassName: YhCurrencyStatMapper
 * @Description: TODO
 * @author hwj
 * @date 2017-12-28 下午04:46:32
 */
public interface YhCurrencyStatMapper {
	/**
	 * 体力统计
	 * @Title: selectYhPowerStatList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhCurrencyStat>
	 * @author hwj
	 * @date 2017-12-28 下午04:57:41
	 */
	public List<YhCurrencyStat> selectYhPowerStatList(IndependBaseSearchData searchData)throws Exception;
	/**
	 * 氪金消耗
	 * @Title: selectYhKrypGoldConsumpList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhCurrencyStat>
	 * @author hwj
	 * @date 2017-12-28 下午05:50:21
	 */
	public List<YhCurrencyStat> selectYhKrypGoldConsumpList(IndependBaseSearchData searchData)throws Exception;
	/**
	 * 氪金存量
	 * @Title: selectYhKrypGoldLeftList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhCurrencyStat>
	 * @author hwj
	 * @date 2017-12-28 下午06:55:48
	 */
	public List<YhCurrencyStat> selectYhKrypGoldLeftList(IndependBaseSearchData searchData)throws Exception;
	/**
	 * 货币消耗统计
	 * @Title: selectCurrencyConsStatList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhCurrencyStat>
	 * @author hwj
	 * @date 2017-12-29 下午04:05:16
	 */
	public List<YhCurrencyStat> selectCurrencyConsStatList(IndependBaseSearchData searchData)throws Exception;
	/**
	 * 货币产出与消耗统计
	 * @Title: selectCurOutAndConsList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhCurrencyStat>
	 * @author hwj
	 * @date 2017-12-29 下午04:22:56
	 */
	public List<YhCurrencyStat> selectCurOutAndConsList(IndependBaseSearchData searchData)throws Exception;
	/**
	 * 货币消费人数跟次数
	 * @Title: selectCurConsUserTimesList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhCurrencyStat>
	 * @author hwj
	 * @date 2017-12-29 下午05:23:20
	 */
	public List<YhCurrencyStat> selectCurConsUserTimesList(IndependBaseSearchData searchData)throws Exception;
	
	

}
