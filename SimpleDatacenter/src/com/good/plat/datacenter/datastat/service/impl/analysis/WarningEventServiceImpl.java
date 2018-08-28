package com.good.plat.datacenter.datastat.service.impl.analysis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.datastat.entity.analysis.LogplatWarningEvent;
import com.good.plat.datacenter.datastat.mapper.analysis.LogplatWarningEventMapper;
import com.good.plat.datacenter.datastat.service.analysis.IWarningEventService;

@Service(value="warningEventService")
public class WarningEventServiceImpl implements IWarningEventService{
	
	@Autowired
	LogplatWarningEventMapper logplatWarningEventMapper;
	@Override
	public List<LogplatWarningEvent> findAll() {
		return logplatWarningEventMapper.selectAll();
	}
	@Override
	public LogplatWarningEvent findByPrimaryId(Integer id) {
		return logplatWarningEventMapper.selectByPrimaryKey(id);
	}
	
}
