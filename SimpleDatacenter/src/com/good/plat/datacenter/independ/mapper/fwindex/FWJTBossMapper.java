package com.good.plat.datacenter.independ.mapper.fwindex;

import java.util.List;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.fwindex.FWJTBoss;


/**
 * 军团boss
 * @ClassName: FWJTBossMapper
 * @Description: TODO
 * @author hwj
 * @date 2017-7-27 下午04:16:44
 */
public interface FWJTBossMapper {
	/**
	 * 击杀boss的军团信息列表
	 * @Title: selectKillBossJTInfoList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<FWJTBoss>
	 * @author hwj
	 * @date 2017-7-27 下午04:19:02
	 */
	public List<FWJTBoss> selectKillBossJTInfoList(IndependBaseSearchData searchData)throws Exception;
	/**
	 * 军团击杀boss详情
	 * @Title: selectKillBossDetailList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<FWJTBoss>
	 * @author hwj
	 * @date 2017-7-27 下午04:38:19
	 */
	public List<FWJTBoss> selectKillBossDetailList(IndependBaseSearchData searchData)throws Exception;
	/**
	 * 每天军团的击杀情况
	 * @Title: selectPerDayJtKillInfoList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<FWJTBoss>
	 * @author hwj
	 * @date 2017-7-27 下午04:43:30
	 */
	public List<FWJTBoss> selectPerDayJtKillInfoList(IndependBaseSearchData searchData)throws Exception;
	
	
}
