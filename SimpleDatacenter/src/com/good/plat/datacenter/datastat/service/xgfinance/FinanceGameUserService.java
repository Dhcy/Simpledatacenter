package com.good.plat.datacenter.datastat.service.xgfinance;

import java.util.List;

import com.good.plat.datacenter.common.base.XgFinanceBaseSearchData;
import com.good.plat.datacenter.datastat.entity.xgfinance.FinanceGameUser;

/**
 * 香港财务游戏用户
 * @ClassName: GameUserService
 * @Description: TODO
 * @author hwj
 * @date 2017-8-12 下午02:04:23
 */
public interface FinanceGameUserService {
	/**
	 * 香港游戏用户统计
	 * @Title: selectGameUserList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<GameUser>
	 * @author hwj
	 * @date 2017-8-12 下午02:04:56
	 */
	List<FinanceGameUser> selectGameUserNumList(XgFinanceBaseSearchData searchData)throws Exception;
}
