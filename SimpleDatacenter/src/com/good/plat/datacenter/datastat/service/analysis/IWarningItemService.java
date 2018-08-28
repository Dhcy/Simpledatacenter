package com.good.plat.datacenter.datastat.service.analysis;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.analysis.LogplatWarningItem;

public interface IWarningItemService {
	BaseMessage add(LogplatWarningItem item);
	BaseMessage update(LogplatWarningItem item);
	BaseMessage deleteByPrimaryKey(Integer id);
	List<LogplatWarningItem> selectItems(BaseSearchData searchData);
	List<LogplatWarningItem> selectAllItems(BaseSearchData searchData);
	List<LogplatWarningItem> selectItemsByEventID(Integer id);
	LogplatWarningItem selectItemsByPrimaryID(Integer id);
}
