package com.good.plat.datacenter.datastat.service.impl.version;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.version.VersionAnalysis;
import com.good.plat.datacenter.datastat.mapper.version.VersionMapper;
import com.good.plat.datacenter.datastat.service.version.VersionService;

@Service(value="versionService")
public class VersionServiceImpl implements VersionService {

	private static final Logger logger = LoggerFactory.getLogger(VersionServiceImpl.class);
	
	public VersionServiceImpl() {
	}
	
	
	@Autowired
	private VersionMapper versionMapper;

	@Override
	public List<VersionAnalysis> selectNewUserList(BaseSearchData searchData)
			throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		logger.info("selectNewUserList:   "+searchData.toString());
		List<VersionAnalysis> list = versionMapper.selectNewUserList(searchData);
		return list;
	}
	
	@Override
	public List<VersionAnalysis> exportNewUserList(BaseSearchData searchData)
			throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		logger.info("exportNewUserList:   "+searchData.toString());
		List<VersionAnalysis> list = versionMapper.exportNewUserList(searchData);
		return list;
	}

	@Override
	public List<VersionAnalysis> selectActiveUserList(BaseSearchData searchData)
			throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		logger.info("selectActiveUserList:   "+searchData.toString());
		List<VersionAnalysis> list = versionMapper.selectActiveUserList(searchData);
		return list;
	}
	
	@Override
	public List<VersionAnalysis> exportActiveUserList(BaseSearchData searchData)
			throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		logger.info("exportActiveUserList:   "+searchData.toString());
		List<VersionAnalysis> list = versionMapper.exportActiveUserList(searchData);
		return list;
	}

	@Override
	public List<VersionAnalysis> selectLoginUserList(BaseSearchData searchData)
			throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		logger.info("selectLoginUserList:   "+searchData.toString());
		List<VersionAnalysis> list = versionMapper.selectLoginUserList(searchData);
		return list;
	}

	
	@Override
	public List<VersionAnalysis> exportLoginUserList(BaseSearchData searchData)
			throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		logger.info("selectLoginUserList:   "+searchData.toString());
		List<VersionAnalysis> list = versionMapper.selectLoginUserList(searchData);
		return list;
	}

}
