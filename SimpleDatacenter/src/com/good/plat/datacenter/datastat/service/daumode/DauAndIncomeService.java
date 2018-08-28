package com.good.plat.datacenter.datastat.service.daumode;

import java.util.Map;

import com.good.plat.datacenter.datastat.entity.daumode.DauMode;

/**
 * DAU模型及收入模型
 * @ClassName: DauAndIncomeService
 * @Description: TODO
 * @author hwj
 * @date 2017-3-21 下午05:50:19
 */
public interface DauAndIncomeService {
/**
 * 用户的dau及收入预测
 * @Title: dauAndIncomeOfSpread
 * @Description: TODO
 * @param dauMode
 * @return
 * @throws Exception
 * Map<String,Object>
 * @author hwj
 * @date 2017-3-22 下午04:37:14
 */
Map<String,Object> dauAndIncomeOfSpread(DauMode dauMode)throws Exception;

}
