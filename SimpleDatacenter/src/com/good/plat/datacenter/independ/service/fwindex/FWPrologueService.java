package com.good.plat.datacenter.independ.service.fwindex;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.independ.entity.fwindex.FWPrologue;

/**
 * 序章
 * @ClassName: FWPrologueService
 * @Description: TODO
 * @author hwj
 * @date 2017-4-18 下午05:56:18
 */
public interface FWPrologueService {

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
