package com.good.plat.datacenter.datastat.controller.daumode;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.good.plat.datacenter.datastat.entity.daumode.DauMode;
import com.good.plat.datacenter.datastat.service.daumode.DauAndIncomeService;

/**
 * dau模型控制器
 * @ClassName: DauModeController
 * @Description: TODO
 * @author hwj
 * @date 2017-3-22 下午03:47:29
 */
@Controller
@RequestMapping(value="/dauMode")
public class DauModeController {
	Logger logger=Logger.getLogger(getClass());
	@Autowired
	private DauAndIncomeService dauAndIncomeService;
	/**
	 * DAU跟收入预测
	 * @Title: calculateDauAndIncome
	 * @Description: TODO
	 * @param dauMode
	 * @param request
	 * @return
	 * Map<String,Object>
	 * @author hwj
	 * @throws Exception 
	 * @date 2017-3-22 下午07:29:16
	 */
	@RequestMapping(value="/calculDauAndIncome", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Map<String,Object> calculateDauAndIncome(@RequestBody DauMode dauMode,HttpServletRequest request) throws Exception{
		Map<String,Object> map=new HashMap<String,Object>();
		System.out.println("参数=====："+JSONObject.toJSONString(dauMode));
		map=dauAndIncomeService.dauAndIncomeOfSpread(dauMode);
		return map;
	}
}
