package com.good.plat.datacenter.independ.service.sj3index;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.SySummary;

/**
 * 圣域统计
 * @ClassName: SySummaryService
 * @Description: TODO
 * @author hwj
 * @date 2017-3-7 上午10:11:24
 */
public interface SySummaryService {

	/**
	 * 圣域封魔团
	 * @Title: selectSyFengMoTuanList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<SySummary>
	 * @author hwj
	 * @date 2017-3-7 上午10:10:18
	 */
	List<SySummary> selectSyFengMoTuanList(BaseSearchData searchData)throws Exception;
	
	/**
	 * 
	 * 圣域爵位战
	 * @Title: selectSyJueWeiList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<SySummary>
	 * @author hwj
	 * @date 2017-3-7 上午10:28:56
	 */
	List<SySummary> selectSyJueWeiList(BaseSearchData searchData)throws Exception;
	
	/**
	 * 圣域猎魔记事
	 * @Title: selectSyLieMoList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<SySummary>
	 * @author hwj
	 * @date 2017-3-7 上午10:42:26
	 */
	List<SySummary> selectSyLieMoList(BaseSearchData searchData)throws Exception;
	
	/**
	 * 圣域魔神降临
	 * @Title: selectSyMoShenList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<SySummary>
	 * @author hwj
	 * @date 2017-3-7 下午02:05:30
	 */
	List<SySummary> selectSyMoShenList(BaseSearchData searchData)throws Exception;
}
