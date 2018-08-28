package com.good.plat.datacenter.independ.mapper.sj3index;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.YuanLing;

/**
 * 元灵
 * @ClassName: YuanLingMapper
 * @Description: TODO
 * @author hwj
 * @date 2017-2-28 下午04:03:45
 */
public interface YuanLingMapper {
	
	/**
	 * 元灵统计(元灵名及元灵玩家次数)
	 * @Title: selectYlNameAndUserCntList
	 * @Description: TODO
	 * @param searchDate
	 * @return
	 * @throws Exception
	 * List<YuanLing>
	 * @author hwj
	 * @date 2017-2-28 下午04:10:21
	 */
	List<YuanLing> selectYlNameAndUserCntList(BaseSearchData searchData)throws Exception;
	
	/**
	 * 元灵统计(十连抽次数)
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
	 * 元灵单次抽取
	 * @Title: selectYlSimExtractCntList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YuanLing>
	 * @author hwj
	 * @date 2017-5-15 下午04:35:43
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
