package com.good.plat.datacenter.datastat.service.impl.xgfinance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.XgFinanceBaseSearchData;
import com.good.plat.datacenter.datastat.entity.xgfinance.FinanceGameUser;
import com.good.plat.datacenter.datastat.mapper.xgfinance.FinanceGameUserMapper;
import com.good.plat.datacenter.datastat.service.xgfinance.FinanceGameUserService;
@Service(value="financeGameUserServiceImpl")
public class FinanceGameUserServiceImpl implements FinanceGameUserService {
	@Autowired
	private FinanceGameUserMapper financeGameUserMapper;
	@Override
	public List<FinanceGameUser> selectGameUserNumList(
			XgFinanceBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		List<FinanceGameUser> list=financeGameUserMapper.selectGameUserList(searchData);
		return list;
	}


}
