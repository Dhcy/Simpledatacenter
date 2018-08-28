package com.good.plat.datacenter.datastat.service.index;

import java.util.List;
import java.util.Map;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.index.Report;

/**
 * 运营报表
 * @ClassName: ReportService
 * @Description: TODO
 * @author hwj
 * @date 2017-2-10 下午03:26:06
 */
public interface ReportService {
	/**
	 * 日报
	 * @Title: selectDailyReport
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * Map<String,Report>
	 * @author hwj
	 * @date 2017-2-10 下午05:27:12
	 */
	Map<String,Report> selectDailyReport(BaseSearchData searchData)throws Exception;
	
	/**
	 * 付费数据
	 * @Title: selectpayDataList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<Report>
	 * @author hwj
	 * @date 2017-2-10 下午09:42:56
	 */
	List<Report> selectPayDataList(BaseSearchData searchData)throws Exception;
	/**
	 * 累计数据
	 * @Title: selectTotalDataList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<Report>
	 * @author hwj
	 * @date 2017-2-13 下午03:02:00
	 */
	List<Report> selectTotalDataList (BaseSearchData searchData)throws Exception;
	
	/**
	 * 周基础数据
	 * @Title: selectWeekBaseDataList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<Report>
	 * @author hwj
	 * @date 2017-2-14 下午01:12:09
	 */
	List<Report> selectWeekBaseDataList (BaseSearchData searchData)throws Exception;
	
	/**
	 * 月基础数据
	 * @Title: selectWeekBaseDataList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<Report>
	 * @author hwj
	 * @date 2017-2-14 下午01:12:09
	 */
	List<Report> selectMonthBaseDataList (BaseSearchData searchData)throws Exception;
	
}
