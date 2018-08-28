package com.good.plat.datacenter.independ.service.sj3index;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.YuanLing;

/**
 * 元灵服务类
 * @ClassName: YuanLingService
 * @Description: TODO
 * @author hwj
 * @date 2017-2-28 下午04:12:23
 */
public interface YuanLingService {
	/**
	 * 元灵统计(元灵名称及玩家次数)
	 * @Title: selectYlNameAndUserCntList
	 * @Description: TODO
	 * @param searchDate
	 * @return
	 * @throws Exception
	 * List<YuanLing>
	 * @author hwj
	 * @date 2017-2-28 下午04:12:57
	 */
	List<YuanLing> selectYlNameAndUserCntList(BaseSearchData searchData)throws Exception;
	
	/**
	 * 元灵统计(二十连抽次数)
	 * @Title: selectYlslcCntList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YuanLing>
	 * @author hwj
	 * @date 2017-2-28 下午04:26:31
	 */
	List<YuanLing> selectYlslcCntList(BaseSearchData searchData)throws Exception;
	
	/**
	 * 元灵统计(元灵等级,对应玩家数量)
	 * @Title: selectYlLevelAndUserCntList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YuanLing>
	 * @author hwj
	 * @date 2017-2-28 下午04:39:30
	 */
	List<YuanLing> selectYlLevelAndUserCntList(BaseSearchData searchData)throws Exception;
	/**
	 * 元灵单独次数抽取
	 * @Title: selectYlSimExtractCntList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YuanLing>
	 * @author hwj
	 * @date 2017-5-15 下午04:37:31
	 */
	List<YuanLing> selectYlSimExtractCntList(BaseSearchData searchData)throws Exception;
	
	/**
	 * 元灵详情
	 * @Title: selectYlDetailList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YuanLing>
	 * @author hwj
	 * @date 2017-5-16 上午10:22:40
	 */
	List<YuanLing> selectYlDetailList(BaseSearchData searchData)throws Exception;
	
}
