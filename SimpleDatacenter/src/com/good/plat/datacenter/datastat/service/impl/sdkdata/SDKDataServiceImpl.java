package com.good.plat.datacenter.datastat.service.impl.sdkdata;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.SDKDataBaseSearchData;
import com.good.plat.datacenter.common.base.Validator;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.datastat.entity.sdkdata.SdkDataSummary;
import com.good.plat.datacenter.datastat.entity.sdkdata.SdkDataSummary.PageInfo;
import com.good.plat.datacenter.datastat.entity.sdkdata.SdkDataVipDistInfo;
import com.good.plat.datacenter.datastat.entity.sdkdata.SdkNewsStat;
import com.good.plat.datacenter.datastat.mapper.sdkdata.SDKDataAnalysisMapper;
import com.good.plat.datacenter.datastat.service.sdkdata.SDKDataService;

@Service(value="sDKDataService")
public class SDKDataServiceImpl implements SDKDataService {
	@Autowired
	SDKDataAnalysisMapper sDKDataAnalysisMapper;

	public SDKDataAnalysisMapper getsDKDataAnalysisMapper() {
		return sDKDataAnalysisMapper;
	}

	public void setsDKDataAnalysisMapper(SDKDataAnalysisMapper sDKDataAnalysisMapper) {
		this.sDKDataAnalysisMapper = sDKDataAnalysisMapper;
	}

	@Override
	public List<SdkDataSummary> selectSDKDataSummary(SDKDataBaseSearchData searchData) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		List<SdkDataSummary> list = sDKDataAnalysisMapper.selectSDKDataSummary(searchData);
		return list;
	}
	
	@Override
	public List<SdkDataSummary> selectSDKDataSummaryDetail(SDKDataBaseSearchData searchData) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		List<SdkDataSummary> list = sDKDataAnalysisMapper.selectSDKDataSummaryDetail(searchData);
		return list;
	}

	@Override
	public List<SdkDataSummary> selectSDKDataNewAddData(SDKDataBaseSearchData searchData) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		List<SdkDataSummary> list = sDKDataAnalysisMapper.selectSDKDataNewAddData(searchData);
		return list;
	}

	@Override
	public List<SdkDataSummary> selectSDKDataNewAddDataDetail(SDKDataBaseSearchData searchData) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		List<SdkDataSummary> list = sDKDataAnalysisMapper.selectSDKDataNewAddDataDetail(searchData);
		return list;
	}

	@Override
	public List<SdkDataSummary> selectSDKLoginAnalysis(SDKDataBaseSearchData searchData) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		List<SdkDataSummary> list = sDKDataAnalysisMapper.selectSDKLoginAnalysis(searchData);
		
		return list;
	}

	@Override
	public List<SdkDataSummary> selectSDKBrowseSummary(SDKDataBaseSearchData searchData) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		List<SdkDataSummary> list = sDKDataAnalysisMapper.selectSDKBrowseSummary(searchData);
		
		return list;
	}

	@Override
	public List<SdkDataSummary> selectSDKBrowseSummaryDetail(SDKDataBaseSearchData searchData) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		List<SdkDataSummary> list = sDKDataAnalysisMapper.selectSDKBrowseSummaryDetail(searchData);
		return list;
	}

	@Override
	public List<SdkDataSummary> selectSDKDownloadSummary(SDKDataBaseSearchData searchData) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		List<SdkDataSummary> list = sDKDataAnalysisMapper.selectSDKDownloadSummary(searchData);
		return list;
	}

	@Override
	public List<SdkDataSummary> selectSDKDownloasdSourceSummary(SDKDataBaseSearchData searchData) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		List<SdkDataSummary> list = sDKDataAnalysisMapper.selectSDKDownloasdSourceSummary(searchData);
		return list;
	}

	@Override
	public List<SdkDataSummary> selectSDKDownloasdSourceDetail(SDKDataBaseSearchData searchData) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		List<SdkDataSummary> list = sDKDataAnalysisMapper.selectSDKDownloasdSourceDetail(searchData);
		return list;
	}

	@Override
	public List<SdkDataSummary> selectSDKNewVip(SDKDataBaseSearchData searchData) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		List<SdkDataSummary> list = sDKDataAnalysisMapper.selectSDKNewVip(searchData);
		return list;
	}

	@Override
	public List<SdkDataSummary> selectSDKNewVipDetail(SDKDataBaseSearchData searchData) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		List<SdkDataSummary> list = sDKDataAnalysisMapper.selectSDKNewVipDetail(searchData);
		return list;
	}

	@Override
	public List<SdkDataVipDistInfo> selectSDKVIPDistribute(SDKDataBaseSearchData searchData) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		List<SdkDataVipDistInfo> list = sDKDataAnalysisMapper.selectSDKVIPDistribute(searchData);
		return list;
	}

	@Override
	public List<SdkDataVipDistInfo> selectSDKVIPDistributeDetail(SDKDataBaseSearchData searchData) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		List<SdkDataVipDistInfo> list = sDKDataAnalysisMapper.selectSDKVIPDistributeDetail(searchData);

		return list;
	}

	@Override
	public List<SdkDataSummary> selectSDKVIPSummary(SDKDataBaseSearchData searchData) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		List<SdkDataSummary> list = sDKDataAnalysisMapper.selectSDKVIPSummary(searchData);
		return list;
	}

	@Override
	public List<SdkDataSummary> selectSDKVIPSummaryDetail(SDKDataBaseSearchData searchData) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		List<SdkDataSummary> list = sDKDataAnalysisMapper.selectSDKVIPSummaryDetail(searchData);
		return list;
	}

	@Override
	public List<SdkNewsStat> selectSdkNewsList(SDKDataBaseSearchData searchData) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		List<SdkNewsStat> list = sDKDataAnalysisMapper.selectSdkNewsList(searchData);
		return list;
	}
	
	
}
