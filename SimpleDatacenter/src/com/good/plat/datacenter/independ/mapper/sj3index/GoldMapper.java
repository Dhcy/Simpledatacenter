package com.good.plat.datacenter.independ.mapper.sj3index;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.Gold;

/**
 * 金币
 * @ClassName: GoldMapper
 * @Description: TODO
 * @author hwj
 * @date 2017-2-23 下午03:33:57
 */
public interface GoldMapper {
	/**
	 * 各等级金币
	 * @Title: selectGoldNumList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<Achieve>
	 * @author hwj
	 * @date 2017-2-23 下午02:03:13
	 */
	List<Gold> selectLvlAndGoldNumList(BaseSearchData searchData)throws Exception;
}
