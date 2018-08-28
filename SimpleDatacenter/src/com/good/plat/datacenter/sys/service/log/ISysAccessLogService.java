package com.good.plat.datacenter.sys.service.log;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.good.plat.datacenter.sys.entity.log.SysAccessLog;

public interface ISysAccessLogService {
	void saveAccessLog(SysAccessLog record);
	void log(HttpServletRequest request,Class cla, String methodName, Date date, Object ...parameters);
}
