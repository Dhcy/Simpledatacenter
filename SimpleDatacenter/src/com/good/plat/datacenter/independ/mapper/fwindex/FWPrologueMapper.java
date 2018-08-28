package com.good.plat.datacenter.independ.mapper.fwindex;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.independ.entity.fwindex.FWPrologue;

/**
 * 序章统计
 * @ClassName: FWPrologueMapper
 * @Description: TODO
 * @author hwj
 * @date 2017-4-18 下午05:50:36
 */
public interface FWPrologueMapper {

	/**
	 * 序章统计
	 * @Title: selectPrologueList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<FWPrologue>
	 * @author hwj
	 * @date 2017-4-18 下午05:50:50
	 */
	List<FWPrologue> selectPrologueList(BaseSearchData searchData)throws Exception;
}
