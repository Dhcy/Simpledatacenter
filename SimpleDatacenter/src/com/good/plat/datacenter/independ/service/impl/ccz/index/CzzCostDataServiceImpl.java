package com.good.plat.datacenter.independ.service.impl.ccz.index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.independ.entity.czz.index.CzzDiamond;
import com.good.plat.datacenter.independ.entity.czz.index.CzzEctypeStat;
import com.good.plat.datacenter.independ.entity.hie.index.HieLottoStat;
import com.good.plat.datacenter.independ.entity.hie.index.HieMoneyStat;
import com.good.plat.datacenter.independ.entity.hie.index.HieShoppingStat;
import com.good.plat.datacenter.independ.entity.hie.index.HieThingStat;
import com.good.plat.datacenter.independ.mapper.ccz.index.CzzCostDataMapper;
import com.good.plat.datacenter.independ.service.ccz.index.CzzCostDataService;

@Service(value = "czzCostDataService")
public class CzzCostDataServiceImpl implements CzzCostDataService{
	@Autowired
	private CzzCostDataMapper czzCostDataMapper;
	
	@Override
	public List<CzzDiamond> queryMoneyStatList(BaseSearchData searchData) {
		
		List<CzzDiamond> resultList=czzCostDataMapper.queryMoneyStatList(searchData);
		if(resultList!=null&&!resultList.isEmpty()){
			for (CzzDiamond c : resultList) {
				if(!"0".equals(c.getYpay())){
					String[] s = c.getYpay().split(",");
					c.setYpay(s[0]);
					c.setYpercent(s[1]+"%");
				}else{
					c.setYpay("0");
					c.setYpercent("0");
				}
				if(!"0".equals(c.getNpay())){
					String[] s = c.getNpay().split(",");
					c.setNpay(s[0]);
					c.setNpercent(s[1]+"%");
				}else{
					c.setNpay("0");
					c.setNpercent("0");
				}
				if(!"0".equals(c.getOpay())){
					String[] s = c.getOpay().split(",");
					c.setOpay(s[0]);
					c.setOpercent(s[1]+"%");
					System.out.println(c.getOpay());
				}else{
					c.setOpay("0");
					c.setOpercent("0");
				}
			}
		}
		return resultList;
	}

	@Override
	public List<HieLottoStat> queryLottoStatList(BaseSearchData searchData) {
		
		List<HieLottoStat> resultList=czzCostDataMapper.queryLottoStatList(searchData);

		return resultList;
	}

	@Override
	public List<CzzEctypeStat> queryEctypeStatList(BaseSearchData searchData) {
		
		List<CzzEctypeStat> resultList=czzCostDataMapper.queryEctypeStatList(searchData);

		return resultList;
	}

	@Override
	public List<HieShoppingStat> queryShoppingStatList(BaseSearchData searchData) {
		
		List<HieShoppingStat> resultList=czzCostDataMapper.queryShoppingStatList(searchData);

		return resultList;
	}

	@Override
	public List<HieMoneyStat> queryGoldStatList(BaseSearchData searchData) {
		
		List<HieMoneyStat> resultList=czzCostDataMapper.queryGoldStatList(searchData);

		return resultList;
	}

	@Override
	public List<HieThingStat> queryThingStatList(BaseSearchData searchData) {
		
		List<HieThingStat> resultList=czzCostDataMapper.queryThingStatList(searchData);

		return resultList;
	}

}
