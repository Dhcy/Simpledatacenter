package com.good.plat.datacenter.independ.service.sj3index;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.Diamond;

public interface DiamondService {

	/**
	 * 钻石统计
	 * @Title: selectDiamondList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<Diamond>
	 * @author hwj
	 * @date 2017-2-28 下午03:30:48
	 */
	List<Diamond> selectDiamondList(BaseSearchData searchData)throws Exception;
	
	/**
	 * 砖石统计详情
	 * @Title: selectDiamondDetailList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<Diamond>
	 * @author hwj
	 * @date 2017-3-7 下午07:54:02
	 */
	List<Diamond> selectDiamondDetailList(BaseSearchData searchData)throws Exception;
}
