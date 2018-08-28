package com.good.plat.datacenter.independ.mapper.sj3index;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.Diamond;



/**
 * 钻石
 * @ClassName: DiamondMapper
 * @Description: TODO
 * @author hwj
 * @date 2017-2-28 下午03:20:57
 */
public interface DiamondMapper {
	
	/**
	 * 钻石统计
	 * @Title: selectDiamondList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<Diamond>
	 * @author hwj
	 * @date 2017-2-28 下午03:28:41
	 */
	List<Diamond> selectDiamondList(BaseSearchData searchData)throws Exception;
	
	/**
	 * 砖石统计详情
	 * @Title: selectDiamondDetailList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<Diamond>
	 * @author hwj
	 * @date 2017-3-7 下午07:54:02
	 */
	List<Diamond> selectDiamondDetailList(BaseSearchData searchData)throws Exception;
	
}
