package com.good.plat.datacenter.datastat.service.analysis;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.base.WarningBaseSearchData;
import com.good.plat.datacenter.datastat.entity.analysis.LogplatWarningEvent;
import com.good.plat.datacenter.datastat.entity.analysis.LogplatWarningNotice;
import com.good.plat.datacenter.datastat.entity.analysis.LogplatWarningNoticeWithBLOBs;

public interface IWarningNoticeService {
	List<LogplatWarningNoticeWithBLOBs> selectWarningNotice(WarningBaseSearchData searchData);
	List<LogplatWarningNoticeWithBLOBs> selectAllWarningNotice(int topNum);
	BaseMessage updateReadWarningNotice(Integer id);

	BaseMessage saveOrUpdateWarningNotice(LogplatWarningNoticeWithBLOBs notice);
}

