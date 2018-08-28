package com.good.plat.datacenter.independ.service.sj3index;

import java.util.List;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.Sj3ModelStat;

/**
 * 世界3时装统计
 * @ClassName: Sj3ModelStatService
 * @Description: TODO
 * @author hwj
 * @date 2018-1-11 下午02:17:28
 */
public interface Sj3ModelStatService {
	/**
	 * 时装统计
	 * @Title: selectModelStatList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<Sj3ModelStat>
	 * @author hwj
	 * @date 2018-1-11 下午02:16:16
	 */
	public List<Sj3ModelStat> selectModelStatList(IndependBaseSearchData searchData)throws Exception;
}
