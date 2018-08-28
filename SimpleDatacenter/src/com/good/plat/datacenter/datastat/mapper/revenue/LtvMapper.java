package com.good.plat.datacenter.datastat.mapper.revenue;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.revenue.Ltv;

/**
 * 
 * @ClassName: LtvMapper
 * @Description: ltv值
 * @author hwj
 * @date 2017-2-8 下午02:20:28
 */
public interface LtvMapper {
	
	/**
	 * 
	 * @Title: selectRegCurrDate
	 * @Description: 获取当前日期
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<Ltv>
	 * @author hwj
	 * @date 2017-2-8 下午02:26:48
	 */
	List<Ltv> selectRegCurrDate(BaseSearchData searchData)throws Exception;
	
	/**
	 * 
	 * @Title: selectRegPayMoney
	 * @Description: 注册付费金额及账号数
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<Ltv>
	 * @author hwj
	 * @date 2017-2-8 下午02:28:39
	 */
	List<Ltv> selectRegPayMoneyAndCount(BaseSearchData searchData)throws Exception;
	
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
	Ltv selectLtvofCurrDate (BaseSearchData searchData)throws Exception;
}
