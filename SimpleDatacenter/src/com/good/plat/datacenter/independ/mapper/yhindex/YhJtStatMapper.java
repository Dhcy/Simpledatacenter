package com.good.plat.datacenter.independ.mapper.yhindex;

import java.util.List;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.yhindex.YhJtStat;

/**
 * 银河军团统计
 * @ClassName: YhJtStatMapper
 * @Description: TODO
 * @author hwj
 * @date 2018-5-21 上午11:07:13
 */
public interface YhJtStatMapper {
	/**
	 * 军团统计 
	 * @Title: selectJtStatList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhJtStat>
	 * @author hwj
	 * @date 2018-5-21 上午11:52:36
	 */
	public List<YhJtStat> selectJtStatList(IndependBaseSearchData searchData)throws Exception;
	
	/**
	 * 军团等级分布
	 * @Title: selectJtLvlDistributeList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhJtStat>
	 * @author hwj
	 * @date 2018-5-21 上午11:52:36
	 */
	public List<YhJtStat> selectJtLvlDistributeList(IndependBaseSearchData searchData)throws Exception;
	
	/**
	 * 军团捐赠统计
	 * @Title: selectJtDonateStatList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhJtStat>
	 * @author hwj
	 * @date 2018-5-21 上午11:52:36
	 */
	public List<YhJtStat> selectJtDonateStatList(IndependBaseSearchData searchData)throws Exception;
	
	/**
	 * 强敌入侵关卡统计
	 * @Title: selectJtQdrqStageStatList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhJtStat>
	 * @author hwj
	 * @date 2018-5-21 上午11:52:36
	 */
	public List<YhJtStat> selectJtQdrqStageStatList(IndependBaseSearchData searchData)throws Exception;
	
	/**
	 * 军团采购统计
	 * @Title: selectJtPurchaseList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhJtStat>
	 * @author hwj
	 * @date 2018-5-21 上午11:52:36
	 */
	public List<YhJtStat> selectJtPurchaseList(IndependBaseSearchData searchData)throws Exception;
	
	/**
	 * 军团采购商品统计
	 * @Title: selectJtPurchaseGoodList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhJtStat>
	 * @author hwj
	 * @date 2018-5-21 上午11:52:36
	 */
	public List<YhJtStat> selectJtPurchaseGoodList(IndependBaseSearchData searchData)throws Exception;
	
	/**
	 * 军团发布请求数据统计
	 * @Title: selectJtfbqqDataList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhJtStat>
	 * @author hwj
	 * @date 2018-5-21 上午11:52:36
	 */
	public List<YhJtStat> selectJtfbqqDataList(IndependBaseSearchData searchData)throws Exception;
	
	/**
	 * 军团赠送数据统计
	 * @Title: selectJtzsDataList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhJtStat>
	 * @author hwj
	 * @date 2018-5-21 上午11:52:36
	 */
	public List<YhJtStat> selectJtzsDataList(IndependBaseSearchData searchData)throws Exception;
	
	/**
	 * 军团总计数据统计
	 * @Title: selectJtTotalDataList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhJtStat>
	 * @author hwj
	 * @date 2018-5-21 上午11:52:36
	 */
	public List<YhJtStat> selectJtTotalDataList(IndependBaseSearchData searchData)throws Exception;

}
