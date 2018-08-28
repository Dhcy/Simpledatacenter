package com.good.plat.datacenter.datastat.service.revenue;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.revenue.NewPlayerValue;

/**
 * @ClassName: NewPlayerValueMapper
 * @Description: 新玩家价值
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
public interface NewPlayerValueService {
	/**
	 * @Title: selectXdaysAvgContributionList
	 * @Description: 新玩家收入贡献
	 * @param searchData
	 * @return List<NewPlayerValue>
	 * @author dmw
	 * @date 2016年3月16日 下午3:11:00
	 */
	List<NewPlayerValue> selectXdaysAvgContributionList(
			BaseSearchData searchData, HttpSession session) throws Exception;

	/**
	 * @Title: exportXdaysAvgContributionList
	 * @Description: 导出
	 * @param searchData
	 * @return List<NewPlayerValue>
	 * @author dmw
	 * @date 2016年3月16日 下午3:16:20
	 */
	List<NewPlayerValue> exportXdaysAvgContributionList(
			BaseSearchData searchData, HttpSession session) throws Exception;
	
	/**
	 * 单日付费金额
	 * @Title: selectSimpleDayPayMoneyList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<NewPlayerValue>
	 * @author hwj
	 * @date 2017-6-29 下午01:44:26
	 */
	List<NewPlayerValue> selectSimpleDayPayMoneyList(BaseSearchData searchData)throws Exception;
	
	/**
	 * 累计付费金额
	 * @Title: selectPerDayTotalPayMoneyList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<NewPlayerValue>
	 * @author hwj
	 * @date 2017-6-29 下午01:45:40
	 */
	List<NewPlayerValue> selectPerDayTotalPayMoneyList(BaseSearchData searchData)throws Exception;
	
	/**
	 * 充值区域列表
	 * @Title: selectRechargeCityList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<NewPlayerValue>
	 * @author hwj
	 * @date 2017-8-25 下午12:28:53
	 */
	List<NewPlayerValue> selectRechargeCityList(BaseSearchData searchData);

}
