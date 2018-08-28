package com.good.plat.datacenter.datastat.mapper.financedata;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.financedata.FinanceData;


/**
 * 游戏用户数
 * @ClassName: GameUserMapper
 * @Description: TODO
 * @author hwj
 * @date 2017-8-12 下午01:51:30
 */
public interface FinanceDataMapper {
	/**
	 * 财务游戏用户
	 * @Title: selectfinanceGameUserList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<FinanceGameUser>
	 * @author hwj
	 * @date 2018-3-2 下午04:57:57
	 */
	List<FinanceData> selectfinanceGameUserList(BaseSearchData searchData)throws Exception;
}
