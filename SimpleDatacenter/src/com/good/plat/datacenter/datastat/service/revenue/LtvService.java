package com.good.plat.datacenter.datastat.service.revenue;

import java.util.ArrayList;
import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.revenue.Ltv;

/**
 * 
 * @ClassName: LtvService
 * @Description: ltv
 * @author hwj
 * @date 2017-2-8 下午02:43:43
 */
public interface LtvService {
	
	/**
	 * 注册付费金额及比列
	 * @Title: selectRegPayMoneyAndCountAndRate
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<Ltv>
	 * @author hwj
	 * @date 2017-2-8 下午02:46:14
	 */
	List<Ltv> selectRegPayMoneyAndCountAndRate(BaseSearchData searchData)throws Exception;
	
	/**
	 * 
	 * @Title: selectLtvofCurrDate
	 * @Description: 查询当天日期的ltv
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<Ltv>
	 * @author 
	 * @date 2017-2-8 下午05:48:45
	 */
	List<Ltv> selectLtvofCurrDateList (BaseSearchData searchData)throws Exception;
	
	
}
