package com.good.plat.datacenter.datastat.mapper.sdkdata;

import java.util.List;

import com.good.plat.datacenter.common.base.SDKDataBaseSearchData;
import com.good.plat.datacenter.datastat.entity.sdkdata.SdkDataSummary;
import com.good.plat.datacenter.datastat.entity.sdkdata.SdkDataVipDistInfo;
import com.good.plat.datacenter.datastat.entity.sdkdata.SdkNewsStat;

public interface SDKDataAnalysisMapper {
	/**
	 * 数据总览
	 * @Title: selectSDKDataSummary
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<SdkDataSummary>
	 * @author moxw
	 * @date 2016年11月4日 下午7:07:48
	 */
	List<SdkDataSummary> selectSDKDataSummary(SDKDataBaseSearchData searchData);
	/**
	 * 数据总览明细
	 * @Title: selectSDKDataSummaryDetail
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<SdkDataSummary>
	 * @author moxw
	 * @date 2016年11月4日 下午7:07:39
	 */
	List<SdkDataSummary> selectSDKDataSummaryDetail(SDKDataBaseSearchData searchData);
	/**
	 * 新增数据
	 * @Title: selectSDKDataNewAddData
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<SdkDataSummary>
	 * @author moxw
	 * @date 2016年11月4日 下午7:07:33
	 */
	List<SdkDataSummary> selectSDKDataNewAddData(SDKDataBaseSearchData searchData);
	/**
	 * 新增数据明细
	 * @Title: selectSDKDataNewAddData
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<SdkDataSummary>
	 * @author moxw
	 * @date 2016年11月4日 下午7:07:33
	 */
	List<SdkDataSummary> selectSDKDataNewAddDataDetail(SDKDataBaseSearchData searchData);

	/**
	 * 登录分析
	 * @Title: selectSDKLoginAnalysis
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<SdkDataSummary>
	 * @author moxw
	 * @date 2016年11月4日 下午7:07:23
	 */
	List<SdkDataSummary> selectSDKLoginAnalysis(SDKDataBaseSearchData searchData);
	/**
	 * 浏览统计
	 * @Title: selectSDKBrowseSummary
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<SdkDataSummary>
	 * @author moxw
	 * @date 2016年11月4日 下午7:07:18
	 */
	List<SdkDataSummary> selectSDKBrowseSummary(SDKDataBaseSearchData searchData);
	/**
	 * 浏览统计明细
	 * @Title: selectSDKBrowseSummaryDetail
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<SdkDataSummary>
	 * @author moxw
	 * @date 2016年11月4日 下午7:07:10
	 */
	List<SdkDataSummary> selectSDKBrowseSummaryDetail(SDKDataBaseSearchData searchData);
	/**
	 * 下载统计
	 * @Title: selectSDKDownloadSummary
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<SdkDataSummary>
	 * @author moxw
	 * @date 2016年11月4日 下午7:07:02
	 */
	List<SdkDataSummary> selectSDKDownloadSummary(SDKDataBaseSearchData searchData);
	/**
	 * 下载来源统计
	 * @Title: selectSDKDownloasdSourceSummary
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<SdkDataSummary>
	 * @author moxw
	 * @date 2016年11月4日 下午7:08:24
	 */
	List<SdkDataSummary> selectSDKDownloasdSourceSummary(SDKDataBaseSearchData searchData);
	/**
	 * 下载来源明细
	 * @Title: selectSDKDownloasdSourceSummary
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<SdkDataSummary>
	 * @author moxw
	 * @date 2016年11月4日 下午7:08:36
	 */
	List<SdkDataSummary> selectSDKDownloasdSourceDetail(SDKDataBaseSearchData searchData);
	/**
	 * VIP新增
	 * @Title: selectSDKNewVip
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<SdkDataSummary>
	 * @author moxw
	 * @date 2016年11月4日 下午7:09:23
	 */
	List<SdkDataSummary> selectSDKNewVip(SDKDataBaseSearchData searchData);
	/**
	 * VIP新增明细
	 * @Title: selectSDKNewVipDetail
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<SdkDataSummary>
	 * @author moxw
	 * @date 2016年11月4日 下午7:09:44
	 */
	List<SdkDataSummary> selectSDKNewVipDetail(SDKDataBaseSearchData searchData);
	/**
	 * VIP分布
	 * @Title: selectSDKVIPDistribute
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<SdkDataSummary>
	 * @author moxw
	 * @date 2016年11月4日 下午7:10:19
	 */
	List<SdkDataVipDistInfo> selectSDKVIPDistribute(SDKDataBaseSearchData searchData);
	/**
	 * VIP分布明细
	 * @Title: selectSDKVIPDistributeDetail
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<SdkDataSummary>
	 * @author moxw
	 * @date 2016年11月4日 下午7:10:31
	 */
	List<SdkDataVipDistInfo> selectSDKVIPDistributeDetail(SDKDataBaseSearchData searchData);
	/**
	 * VIP统计
	 * @Title: selectSDKVIPSummary
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<SdkDataSummary>
	 * @author moxw
	 * @date 2016年11月4日 下午7:11:05
	 */
	List<SdkDataSummary> selectSDKVIPSummary(SDKDataBaseSearchData searchData);
	/**
	 * VIP统计明细
	 * @Title: selectSDKVIPSummaryDetail
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<SdkDataSummary>
	 * @author moxw
	 * @date 2016年11月4日 下午7:11:23
	 */
	List<SdkDataSummary> selectSDKVIPSummaryDetail(SDKDataBaseSearchData searchData);
	
	/**
	 * SDK 资讯
	 * @Title: selectSdkNewsList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<SdkNewsStat>
	 * @author moxw
	 * @date 2016年11月9日 上午11:11:57
	 */
	List<SdkNewsStat> selectSdkNewsList(SDKDataBaseSearchData searchData);
}