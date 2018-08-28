package com.good.plat.datacenter.independ.mapper.sj3index;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.CityStat;

/**
 * 城市争夺
 * @ClassName: CityStatMapper
 * @Description: TODO
 * @author hwj
 * @date 2017-3-6 下午03:11:55
 */
public interface CityStatMapper {

/**
 * 
 * @Title: selectCityCntDistributeList
 * @Description: TODO
 * @param searData
 * @return
 * @throws Exception
 * List<CityStat>
 * @author hwj
 * @date 2017-3-6 下午03:31:45
 */
	List<CityStat> selectCityCntDistributeList(BaseSearchData searData)throws Exception;
	
	/**
	 * 城市争夺细分
	 * @Title: selectCityFightDetailList
	 * @Description: TODO
	 * @param searData
	 * @return
	 * @throws Exception
	 * List<CityStat>
	 * @author hwj
	 * @date 2017-3-28 上午09:28:00
	 */
	List<CityStat> selectCityFightDetailList(BaseSearchData searchData)throws Exception;
	
	/**
	 * 城市争夺战局情况
	 * @Title: selectCityFightDetail2List
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<CityStat>
	 * @author hwj
	 * @date 2017-4-21 下午04:43:54
	 */
	List<CityStat> selectCityFightSetList(BaseSearchData searchData)throws Exception;
	
}
