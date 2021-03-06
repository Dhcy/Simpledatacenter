package com.good.plat.datacenter.independ.service.sj3index;

import java.util.List;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.GoodsMaterials;

/**
 * 物资
 * @ClassName: GoodsMaterialsService
 * @Description: TODO
 * @author hwj
 * @date 2017-9-25 下午04:13:35
 */
public interface GoodsMaterialsService {
	
	/**
	 * 物资总览
	 * @Title: selectGoodsMaterialsList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<GoodsMaterials>
	 * @author hwj
	 * @date 2017-9-25 下午04:09:02
	 */
	public List<GoodsMaterials> selectGoodsSummaryList(IndependBaseSearchData searchData)throws Exception;
	
	/**
	 * 购买方式物资列表
	 * @Title: selectBugMethodGoodsList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<GoodsMaterials>
	 * @author hwj
	 * @date 2017-9-25 下午05:14:27
	 */
	public List<GoodsMaterials> selectBugMethodGoodsDetailList(IndependBaseSearchData searchData)throws Exception;

}
