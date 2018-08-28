package com.good.plat.datacenter.datastat.service.analysis;

import java.util.List;

import com.good.plat.datacenter.datastat.entity.analysis.LogplatWarningEvent;

public interface IWarningEventService {
	List<LogplatWarningEvent> findAll();
	LogplatWarningEvent findByPrimaryId(Integer id);
}
