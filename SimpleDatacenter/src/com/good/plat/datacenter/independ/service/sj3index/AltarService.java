package com.good.plat.datacenter.independ.service.sj3index;

import java.util.List;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.Altar;

/**
 * 祭坛业务
 * @ClassName: AltarService
 * @Description: TODO
 * @author hwj
 * @date 2017-9-25 下午02:20:59
 */
public interface AltarService {
	
	/**
	 * 查找祭坛
	 * @Title: selectAltarList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<Altar>
	 * @author hwj
	 * @date 2017-9-25 下午02:20:07
	 */
	 public List<Altar> selectAltarList(IndependBaseSearchData searchData)throws Exception;

}
