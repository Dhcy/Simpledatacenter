package com.good.plat.datacenter.independ.mapper.sj3index;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.Achieve;


/**
 * 成就
 * @ClassName: AchieveMapper
 * @Description: TODO
 * @author hwj
 * @date 2017-2-23 上午09:59:45
 */
public interface AchieveMapper {
	
	/**
	 * 成就完成人数
	 * @Title: selectAchieveFinishNumList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<Achieve>
	 * @author hwj
	 * @date 2017-2-23 上午10:17:42
	 */
	List<Achieve> selectAchieveFinishNumList(BaseSearchData searchData)throws Exception;
	
	/**
	 * 完成成就总数及人均完成成就数
	 * @Title: selectAchieveFinishTotalNumList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<Achieve>
	 * @author hwj
	 * @date 2017-2-23 上午11:49:07
	 */
	List<Achieve> selectAchieveFinishTotalNumList(BaseSearchData searchData)throws Exception;
	
	
	
	
}
