package com.good.plat.datacenter.datastat.mapper.balanceplat;

import java.util.List;

import com.good.plat.datacenter.datastat.entity.balanceplat.DZPTChannelCompanyInfo;

/**
 * 渠道公司信息
 * @ClassName: DZPTChannelAccountInfoMapper
 * @Description: TODO
 * @author hwj
 * @date 2017-5-3 下午05:37:32
 */
public interface DZPTChannelCompanyInfoMapper {
	
	/**
	 * 查询渠道账号信息
	 * @Title: selectChannelAccountInfoList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<DZPTChannelAccountInfo>
	 * @author hwj
	 * @date 2017-5-3 下午05:50:20
	 */
	List<DZPTChannelCompanyInfo> selectChannelCompanyInfoList(DZPTChannelCompanyInfo companyInfo)throws Exception;
	/**
	 * 添加渠道账号信息
	 * @Title: insert
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * int
	 * @author hwj
	 * @date 2017-5-3 下午06:18:13
	 */
	int insertChannelCompanyInfo(DZPTChannelCompanyInfo companyInfo)throws Exception;
	
	/**
	 * 根据主键获取渠道账号信息
	 * @Title: selectByPrimaryKey
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * DZPTChannelAccountInfo
	 * @author hwj
	 * @date 2017-5-4 上午10:26:50
	 */
	DZPTChannelCompanyInfo selectByPrimaryKey(Integer id)throws Exception;
	/**
	 * 修改渠道账号信息
	 * @Title: updateByPrimaryKey
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * int
	 * @author hwj
	 * @date 2017-5-4 上午10:28:32
	 */
	int updateByPrimaryKey(DZPTChannelCompanyInfo companyInfo)throws Exception;
	/**
	 * 删除渠道账号信息
	 * @Title: deleteByPrimaryKey
	 * @Description: TODO
	 * @param id
	 * @return
	 * @throws Exception
	 * int
	 * @author hwj
	 * @date 2017-5-4 上午10:45:00
	 */
	int deleteByPrimaryKey(Integer id)throws Exception;
	/**
	 * 通过渠道id查找是否存在公司
	 * @Title: selectCompanyByChannelId
	 * @Description: TODO
	 * @param id
	 * @return
	 * @throws Exception
	 * DZPTChannelCompanyInfo
	 * @author hwj
	 * @date 2017-5-4 下午03:53:55
	 */
	DZPTChannelCompanyInfo selectCompanyByChannelId(Integer id)throws Exception;
}
