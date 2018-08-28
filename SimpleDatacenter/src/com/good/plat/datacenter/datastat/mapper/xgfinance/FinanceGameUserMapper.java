package com.good.plat.datacenter.datastat.mapper.xgfinance;

import java.util.List;

import com.good.plat.datacenter.common.base.XgFinanceBaseSearchData;
import com.good.plat.datacenter.datastat.entity.xgfinance.FinanceGameUser;

/**
 * 游戏用户数
 * @ClassName: GameUserMapper
 * @Description: TODO
 * @author hwj
 * @date 2017-8-12 下午01:51:30
 */
public interface FinanceGameUserMapper {
	/**
	 * 游戏用户数
	 * @Title: selectGameUserList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<GameUser>
	 * @author hwj
	 * @date 2017-8-12 下午01:52:19
	 */
	List<FinanceGameUser> selectGameUserList(XgFinanceBaseSearchData searchData)throws Exception;
}
