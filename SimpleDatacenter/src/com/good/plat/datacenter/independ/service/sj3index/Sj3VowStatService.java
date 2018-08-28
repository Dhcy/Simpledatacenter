package com.good.plat.datacenter.independ.service.sj3index;

import java.util.List;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.Sj3VowStat;

/**
 * 许愿
 * @ClassName: Sj3VowStatService
 * @Description: TODO
 * @author hwj
 * @date 2018-1-11 下午01:48:28
 */
public interface Sj3VowStatService {
	/**
	 * 许愿统计
	 * @Title: selectVowStatList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<Sj3VowStat>
	 * @author hwj
	 * @date 2018-1-11 下午01:47:34
	 */
	public List<Sj3VowStat> selectVowStatList(IndependBaseSearchData searchData)throws Exception;
}
