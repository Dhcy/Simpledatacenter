package com.good.plat.datacenter.datastat.mapper.balanceplat;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.balanceplat.UscAdvertIncome;

/**
 * 视频广告商收入
 * @ClassName: UscAdvertIncomeMapper
 * @Description: TODO
 * @author hwj
 * @date 2018-2-3 上午09:51:57
 */
public interface UscAdvertIncomeMapper {
	/**
	 * 广告收入
	 * @Title: selectUscAdvertIncomeList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<UscAdvertIncome>
	 * @author hwj
	 * @date 2018-2-3 上午10:05:16
	 */
	public List<UscAdvertIncome> selectUscAdvertIncomeList(BaseSearchData searchData)throws Exception;
	/**
	 * 添加
	 * @Title: insert
	 * @Description: TODO
	 * @param Income
	 * @return
	 * @throws Exception
	 * int
	 * @author hwj
	 * @date 2018-2-5 上午11:43:48
	 */
	public int insert(UscAdvertIncome Income)throws Exception;
	/**
	 * 视频收入-渠道列表
	 * @Title: selectUscAdvertChannelList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<UscAdvertIncome>
	 * @author hwj
	 * @date 2018-2-24 上午11:09:44
	 */
	public List<UscAdvertIncome> selectUscAdvertChannelList(BaseSearchData searchData)throws Exception;
	/**
	 * 视频广告商列表
	 * @Title: selectUscVideoAdvertiserList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<UscAdvertIncome>
	 * @author hwj
	 * @date 2018-2-28 下午04:56:52
	 */
	public List<UscAdvertIncome> selectUscVideoAdvertiserList(BaseSearchData searchData)throws Exception;
	/**
	 * 获取视频广告商
	 * @Title: getUscVideoAdvertiser
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * UscAdvertIncome
	 * @author hwj
	 * @date 2018-2-28 下午04:58:30
	 */
	public UscAdvertIncome getUscVideoAdvertiser(UscAdvertIncome searchData)throws Exception;
	/**
	 * 添加视频广告商
	 * @Title: insertUscVideoAdvertiser
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * int
	 * @author hwj
	 * @date 2018-2-28 下午04:59:15
	 */
	public int insertUscVideoAdvertiser(UscAdvertIncome searchData)throws Exception;
	/**
	 * 修改视频广告商
	 * @Title: updateByPrimaryKeySelective
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * int
	 * @author hwj
	 * @date 2018-2-28 下午05:00:39
	 */
	public int updateByPrimaryKeySelective(UscAdvertIncome searchData)throws Exception;
	/**
	 * 删除视频广告商
	 * @Title: deleteByPrimaryKey
	 * @Description: TODO
	 * @param id
	 * @return
	 * @throws Exception
	 * int
	 * @author hwj
	 * @date 2018-2-28 下午05:01:56
	 */
	public int deleteByPrimaryKey(Integer id)throws Exception;
	/**
	 * 获取广告商通过主键
	 * @Title: getAdvertiserByPrimaryKey
	 * @Description: TODO
	 * @param id
	 * @return
	 * @throws Exception
	 * UscAdvertIncome
	 * @author hwj
	 * @date 2018-3-1 上午09:46:12
	 */
	public UscAdvertIncome getAdvertiserByPrimaryKey(Integer id)throws Exception;
	/**
	 * 查询广告商名称
	 * @Title: selectAdvertiserNameList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<String>
	 * @author hwj
	 * @date 2018-3-1 下午01:09:25
	 */
	public List<String> selectAdvertiserNameList(BaseSearchData searchData)throws Exception;
	
	

}
