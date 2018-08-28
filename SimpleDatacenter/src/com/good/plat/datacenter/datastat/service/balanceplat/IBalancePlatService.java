package com.good.plat.datacenter.datastat.service.balanceplat;

import java.util.List;
import java.util.Map;

import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.balanceplat.AppleBalance;
import com.good.plat.datacenter.datastat.entity.balanceplat.ChannelAccounting;
import com.good.plat.datacenter.datastat.entity.balanceplat.ContractFile;
import com.good.plat.datacenter.datastat.entity.balanceplat.DZPTChannelAccounting;
import com.good.plat.datacenter.datastat.entity.balanceplat.DZPTChannelCompanyInfo;
import com.good.plat.datacenter.datastat.entity.balanceplat.PayChannelAccounting;
import com.good.plat.datacenter.datastat.entity.balanceplat.UscActorRecharge;
import com.good.plat.datacenter.datastat.entity.balanceplat.UscAdvertIncome;
import com.good.plat.datacenter.datastat.entity.balanceplat.UscFinacialStatementMonthly;
import com.good.plat.datacenter.datastat.entity.balanceplat.UscFinacialStatementWeekly;

public interface IBalancePlatService {
	BaseMessage addChannelAccounting(DZPTChannelAccounting channelAccounting) throws Exception;

	List<PayChannelAccounting> selectPayChannelAccountingList(BaseSearchData searchData) throws Exception;

	List<ChannelAccounting> selectAccountingList(BaseSearchData searchData) throws Exception;

	List<DZPTChannelAccounting> selectChannelAccountingList(BaseSearchData searchData) throws Exception;

	BaseMessage updateChannelAccounting(DZPTChannelAccounting channelAccounting) throws Exception;

	DZPTChannelAccounting selectChannelAccounting(DZPTChannelAccounting channelAccounting) throws Exception;
	BaseMessage updateBillingAccounting(UscFinacialStatementMonthly channelAccounting) throws Exception;
	
	List<UscActorRecharge> selectSDKRechargeBillList(BaseSearchData searchData) throws Exception;

	List<ChannelAccounting> selectWeeklyAccountingList(BaseSearchData searchData) throws Exception;

	/**
	 * 苹果的财月规律为 5-4-4,即一个季度为三个财月,第一个财月为5周,第二个财月为4周...,以此类推
	 * checktype1 : 财年
	 * checktype2 : 财月
	 * @Title: selectAppleAutoBalance
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<AppleBalance>
	 * @author moxw
	 * @date 2016年8月25日 上午10:54:30
	 */
	List<AppleBalance> selectAppleAutoBalance(BaseSearchData searchData) throws Exception;

	BaseMessage updateWeeklyBillingAccounting(UscFinacialStatementWeekly channelAccounting) throws Exception;

	BaseMessage deleteChannelAccounting(DZPTChannelAccounting channelAccounting) throws Exception;
	Integer updateChannelAccountingBySelective(DZPTChannelAccounting ca) throws Exception;
	
	
	/**
	 * 查询渠道账号信息
	 * @Title: selectChannelAccountInfoLsit
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<DZPTChannelAccountInfo>
	 * @author hwj
	 * @date 2017-5-3 下午05:50:20
	 */
	List<DZPTChannelCompanyInfo> selectChannelCompanyInfoList(DZPTChannelCompanyInfo accountInfo)throws Exception;
	
	/**
	 * 添加渠道账号信息
	 * @Title: addChannelAccountInfo
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * BaseMessage
	 * @author hwj
	 * @date 2017-5-3 下午06:41:35
	 */
	BaseMessage addChannelCompanyInfo(DZPTChannelCompanyInfo accountInfo)throws Exception;
	
	/**
	 * 修改渠道账号信息
	 * @Title: updateChannelAccountInfo
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * BaseMessage
	 * @author hwj
	 * @date 2017-5-4 上午10:39:36
	 */
	BaseMessage updateChannelCompanyInfo(DZPTChannelCompanyInfo accountInfo)throws Exception;
	
	/**
	 * 删除渠道账号信息
	 * @Title: deleteChannelAccountInfo
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * BaseMessage
	 * @author hwj
	 * @date 2017-5-4 上午10:50:06
	 */
	BaseMessage deleteChannelCompanyInfo(DZPTChannelCompanyInfo accountInfo)throws Exception;
	
	/**
	 * 根据主键查询渠道公司
	 * @Title: selectChannelCompany
	 * @Description: TODO
	 * @param accountInfo
	 * @return
	 * @throws Exception
	 * DZPTChannelAccountInfo
	 * @author hwj
	 * @date 2017-5-4 下午02:14:34
	 */
	DZPTChannelCompanyInfo selectChannelCompany(DZPTChannelCompanyInfo accountInfo)throws Exception;
	
	/**
	 * 渠道下各游戏结算
	 * @Title: selectChannelGameAccountingList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<Map<String,String>>
	 * @author hwj
	 * @date 2017-5-4 下午06:28:22
	 */
	List<Map<String,String>> selectChannelGameAccountingList(BaseSearchData searchData)throws Exception;
	
	/**
	 * 根据渠道id查询渠道公司
	 * @Title: selectCompanyByChannelId
	 * @Description: TODO
	 * @param accountInfo
	 * @return
	 * @throws Exception
	 * DZPTChannelCompanyInfo
	 * @author hwj
	 * @date 2017-5-5 下午05:25:55
	 */
	DZPTChannelCompanyInfo selectCompanyByChannelId(DZPTChannelCompanyInfo companyInfo)throws Exception;
	
	/**
	 * 根据文件路径查询是否存在
	 * @Title: selectByFilePath
	 * @Description: TODO
	 * @param contractFile
	 * @return
	 * @throws Exception
	 * ContractFile
	 * @author hwj
	 * @date 2017-5-11 下午01:48:29
	 */
	ContractFile selectByFilePath(ContractFile contractFile)throws Exception;
	
	/**
	 * 添加合同文件
	 * @Title: addContractFile
	 * @Description: TODO
	 * @param contractFile
	 * @return
	 * @throws Exception
	 * BaseMessage
	 * @author hwj
	 * @date 2017-5-11 下午01:50:10
	 */
	BaseMessage addContractFile(ContractFile contractFile)throws Exception;
	/**
	 * 查询合同文件列表
	 * @Title: selectContractFileList
	 * @Description: TODO
	 * @param contractFile
	 * @return
	 * @throws Exception
	 * List<ContractFile>
	 * @author hwj
	 * @date 2017-5-11 下午03:13:24
	 */
	List<ContractFile> selectContractFileList(ContractFile contractFile)throws Exception;
	
	/**
	 * 删除合同文件
	 * @Title: deleteByPrimaryKey
	 * @Description: TODO
	 * @param id
	 * @return
	 * @throws Exception
	 * BaseMessage
	 * @author hwj
	 * @date 2017-5-12 下午02:06:02
	 */
	BaseMessage deleteContractFile(Integer id)throws Exception;
	/**
	 * 将到期提前1个月发通知的合同
	 * @Title: selectWarningNoticeContractList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<ChannelAccounting>
	 * @author hwj
	 * @date 2017-12-6 上午09:02:14
	 */
	List<DZPTChannelAccounting> selectWarningNoticeContractList(BaseSearchData searchData)throws Exception;
	
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
	 * 批量添加
	 * @Title: batchAddVideoIncomeData
	 * @Description: TODO
	 * @param list
	 * @return
	 * BaseMessage
	 * @author hwj
	 * @date 2018-2-5 上午10:22:38
	 */
	public BaseMessage batchAddVideoIncomeData(List<UscAdvertIncome> list);
	
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
	
	
	
	
	
	
}
