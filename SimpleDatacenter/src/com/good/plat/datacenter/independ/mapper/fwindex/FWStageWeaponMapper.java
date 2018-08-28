package com.good.plat.datacenter.independ.mapper.fwindex;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.independ.entity.fwindex.FWStageWeapon;

/**
 * 关卡武器(未来战争-末世突袭)
 * @ClassName: FWStageWeaponMapper
 * @Description: TODO
 * @author hwj
 * @date 2017-6-12 上午10:28:44
 */
public interface FWStageWeaponMapper {
	
	/**
	 * 关卡武器列表
	 * @Title: selectStageWeaponList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<FWStageWeapon>
	 * @author hwj
	 * @date 2017-6-12 上午10:55:54
	 */
	public List<FWStageWeapon> selectStageWeaponList(BaseSearchData searchData)throws Exception;
	
	
}
