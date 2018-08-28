package com.good.plat.datacenter.datastat.service.financedata;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.financedata.FinanceData;

/**
 * 香港财务游戏用户
 * @ClassName: GameUserService
 * @Description: TODO
 * @author hwj
 * @date 2017-8-12 下午02:04:23
 */
public interface FinanceDataService {
	/**
	 * 财务游戏用户
	 * @Title: selectfinanceGameUserList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<FinanceGameUser>
	 * @author hwj
	 * @date 2018-3-2 下午04:59:45
	 */
	List<FinanceData> selectfinanceGameUserList(BaseSearchData searchData)throws Exception;
}
