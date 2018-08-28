package com.good.plat.datacenter.common.config;

import com.good.plat.datacenter.common.base.PropertiesUtil;

public class SysEvn {
	public static final String KEY = PropertiesUtil.getValue("platpublickey");
	public static final String log_data_url=PropertiesUtil.getValue("log_data_url");
}
