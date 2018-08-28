package com.good.plat.datacenter.datastat.service.impl.xgfinance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.XgFinanceBaseSearchData;
import com.good.plat.datacenter.datastat.entity.xgfinance.FinanceCost;
import com.good.plat.datacenter.datastat.mapper.xgfinance.FinanceCostMapper;
import com.good.plat.datacenter.datastat.service.xgfinance.FinanceCostService;
@Service(value="financeCostServiceImpl")
public class FinanceCostServiceImpl implements FinanceCostService {
	@Autowired
	private FinanceCostMapper financeCostMapper;
	@Override
	public List<FinanceCost> selectFinanceCostList(
			XgFinanceBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		List<FinanceCost> list=financeCostMapper.selectFinanceCostList(searchData);
		if(list!=null&&list.size()>0){
			for(FinanceCost e:list){
				e.setCustomerName("媒介推广按用户量计算");
			}
		}
		return list;
	}

}
