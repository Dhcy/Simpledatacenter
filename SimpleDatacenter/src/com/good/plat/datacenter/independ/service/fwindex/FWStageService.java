package com.good.plat.datacenter.independ.service.fwindex;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.independ.entity.fwindex.FWStage;

/**
 * 关卡(未来战争)
 * @ClassName: FWStageService
 * @Description: TODO
 * @author hwj
 * @date 2017-4-17 下午04:23:10
 */
public interface FWStageService {
	/**
	 * 查询关卡列表
	 * @Title: selectStageList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<FWStage>
	 * @author hwj
	 * @date 2017-4-17 下午04:20:57
	 */
	List<FWStage> selectStageList(BaseSearchData searchData)throws Exception;
	/**
	 * 关卡统计(末世突袭，末日突袭taptap)
	 * @Title: selectFtwStageList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<FWStage>
	 * @author hwj
	 * @date 2017-6-16 上午10:41:59
	 */
	List<FWStage> selectFtwStageList(BaseSearchData searchData)throws Exception;

}
