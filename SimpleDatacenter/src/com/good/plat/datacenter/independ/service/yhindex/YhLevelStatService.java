package com.good.plat.datacenter.independ.service.yhindex;

import java.util.List;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.yhindex.YhLevelStat;
/**
 * 银河等级
 * @ClassName: YhLevelStatService
 * @Description: TODO
 * @author hwj
 * @date 2017-12-28 上午10:27:02
 */
public interface YhLevelStatService {
	/**
	 * 等级分布
	 * @Title: selectYhLevelDistributeList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhLevelStat>
	 * @author hwj
	 * @date 2017-12-28 上午10:26:06
	 */
	public List<YhLevelStat> selectYhLevelDistributeList(IndependBaseSearchData searchData)throws Exception;
}
