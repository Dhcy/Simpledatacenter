package com.good.plat.datacenter.independ.mapper.fwindex;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.independ.entity.fwindex.FWMechaStorm;


/**
 * 机甲风暴（末世突袭）
 * @ClassName: FWMechaStormMapper
 * @Description: TODO
 * @author hwj
 * @date 2017-6-12 下午02:20:08
 */
public interface FWMechaStormMapper {
	/**
	 * 查询机甲风暴列表
	 * @Title: selectMechaStormList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<FWMechaStorm>
	 * @author hwj
	 * @date 2017-6-12 下午02:30:33
	 */
	public List<FWMechaStorm> selectMechaStormList(BaseSearchData searchData)throws Exception;
}
