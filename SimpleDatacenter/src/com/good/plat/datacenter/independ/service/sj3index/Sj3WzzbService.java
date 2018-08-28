package com.good.plat.datacenter.independ.service.sj3index;

import java.util.List;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.Sj3Wzzb;

public interface Sj3WzzbService {
	/**
	 * 王者争霸
	 * @Title: selectWzzbList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<Sj3Wzzb>
	 * @author hwj
	 * @date 2017-10-12 下午04:15:07
	 */
	public List<Sj3Wzzb> selectWzzbList(IndependBaseSearchData searchData)throws Exception;
	/**
	 * 王者争霸装备详情
	 * @Title: selectWzzbEquipDetailList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<Sj3Wzzb>
	 * @author hwj
	 * @date 2017-10-12 下午04:15:54
	 */
	public List<Sj3Wzzb> selectWzzbEquipDetailList(IndependBaseSearchData searchData)throws Exception;
	/**
	 * 王者争霸押注
	 * @Title: selectWzzbBetList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<Sj3Wzzb>
	 * @author hwj
	 * @date 2017-10-12 下午04:16:39
	 */
	public List<Sj3Wzzb> selectWzzbBetList(IndependBaseSearchData searchData)throws Exception;
	/**
	 * 王者争霸押注档次
	 * @Title: selectWzzbBetLevelList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<Sj3Wzzb>
	 * @author hwj
	 * @date 2017-10-12 下午04:17:15
	 */
	public List<Sj3Wzzb> selectWzzbBetLevelList(IndependBaseSearchData searchData)throws Exception;
	/**
	 * 外围赛战斗次数最多前20名详情
	 * @Title: selectMaxJoinActorDetailList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<Sj3Wzzb>
	 * @author hwj
	 * @date 2017-10-12 下午04:17:52
	 */
	public List<Sj3Wzzb> selectMaxJoinActorDetailList(IndependBaseSearchData searchData)throws Exception;
	/**
	 * 王者争霸连胜
	 * @Title: selectWzzbLsList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<Sj3Wzzb>
	 * @author hwj
	 * @date 2017-10-12 下午04:19:48
	 */
	public List<Sj3Wzzb> selectWzzbLsList(IndependBaseSearchData searchData)throws Exception;
}
