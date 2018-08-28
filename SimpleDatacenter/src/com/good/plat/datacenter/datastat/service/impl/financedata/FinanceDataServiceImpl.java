package com.good.plat.datacenter.datastat.service.impl.financedata;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.datastat.entity.financedata.FinanceData;
import com.good.plat.datacenter.datastat.mapper.financedata.FinanceDataMapper;
import com.good.plat.datacenter.datastat.service.financedata.FinanceDataService;
@Service(value="financeDataService")
public class FinanceDataServiceImpl implements FinanceDataService {
	@Autowired
	private FinanceDataMapper financeDataMapper;
	@Override
	public List<FinanceData> selectfinanceGameUserList(
			BaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		 List<FinanceData> list=financeDataMapper.selectfinanceGameUserList(searchData);
		 double unitPrice=10.00;//单价(香港)10
		 if(list!=null&&!list.isEmpty()){
				for(FinanceData gameUser:list){
					gameUser.setPrice(unitPrice);
					Integer newUser=gameUser.getNewUser();
					Double money=newUser==null?0.00:NumberUtil.mul(newUser.doubleValue(), unitPrice,0);
					gameUser.setMoney(money);//推广金额(香港)
				}
			}
		return list;
	}

}
