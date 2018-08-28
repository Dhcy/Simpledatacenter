package com.good.plat.datacenter.common.util;

import java.util.List;
import java.util.Map;
public class ResultUtils {
	public static <T> Map<String, Object> OK(Map<String, Object> map,List<T> list) {
		map.put("status", 1);
		map.put("msg", "成功");
		map.put("result", list);
		return map;
	}
	public static Map<String, Object> Error(Map<String, Object> map) {
		map.put("status", 0);
		map.put("msg", "失败");
		return map;
	}

}
