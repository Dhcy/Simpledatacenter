package com.good.plat.datacenter.sys.service.impl.log;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.util.LoggerUtil;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;
import com.good.plat.datacenter.sys.mapper.log.SysAccessLogMapper;
import com.good.plat.datacenter.sys.service.log.ISysAccessLogService;

@Service(value="accessLogService")
public class SysAccessLogServiceImpl implements ISysAccessLogService {
	@Autowired
	private SysAccessLogMapper sysAccessLogMapper;
	@Autowired
	private LoggerUtil loggerUtil;

	@Override
	public void saveAccessLog(SysAccessLog record) {
		sysAccessLogMapper.insert(record);
	}

	@Override
	public void log(HttpServletRequest request,Class claz, String methodName, Date date, Object... parameters) {
		try{
			String op_desc = loggerUtil.getValue(LoggerUtil.formatMethod(claz, methodName));
			SysAccessLog accessLog = LoggerUtil.initNewInstance(request, claz, methodName,op_desc,date,null, parameters);
			sysAccessLogMapper.insert(accessLog);
		}catch(RuntimeException e){
			e.printStackTrace();
			throw e;
		}
	}
	
}
