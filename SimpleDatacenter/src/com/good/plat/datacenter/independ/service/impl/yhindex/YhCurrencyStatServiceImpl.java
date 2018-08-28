package com.good.plat.datacenter.independ.service.impl.yhindex;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.independ.entity.yhindex.YhCurrencyStat;
import com.good.plat.datacenter.independ.mapper.yhindex.YhCurrencyStatMapper;
import com.good.plat.datacenter.independ.service.yhindex.YhCurrencyStatService;
@Service(value="yhCurrencyStatService")
public class YhCurrencyStatServiceImpl implements YhCurrencyStatService {
	@Autowired
	private YhCurrencyStatMapper yhCurrencyStatMapper;
	@Override
	public List<YhCurrencyStat> selectYhPowerStatList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<YhCurrencyStat> list=yhCurrencyStatMapper.selectYhPowerStatList(searchData);
		if(list!=null&&!list.isEmpty()){
			for(YhCurrencyStat e:list){
				if(e.getActUser()==null){
					e.setActUser(0);
				}
				//1到5次总用户数量
				Integer sum1to5ActUser=e.getBuyPower1TimesActUser()+e.getBuyPower2TimesActUser()+e.getBuyPower3TimesActUser()+e.getBuyPower4TimesActUser()+e.getBuyPowerHigh5TimesActUser();
				//0次的用户数量
				Integer	sum0ActUser=e.getActUser()-sum1to5ActUser;
				//0次用户数量占比
				double sum0ActUserRate =e.getActUser()==0?0.0:sum0ActUser.doubleValue()/e.getActUser();
				e.setBuyPower0TimesActUser(sum0ActUser);
				e.setBuyPower0TimesActUserRate(NumberUtil.multi100(sum0ActUserRate, 2));
				if(e.getBuyPower0TimesActUserRate()==null){
					e.setBuyPower0TimesActUserRate(0.0);
				}
				if(e.getBuyPower1TimesActUserRate()==null){
					e.setBuyPower1TimesActUserRate(0.0);
				}
				if(e.getBuyPower2TimesActUserRate()==null){
					e.setBuyPower2TimesActUserRate(0.0);
				}
				if(e.getBuyPower3TimesActUserRate()==null){
					e.setBuyPower3TimesActUserRate(0.0);
				}
				if(e.getBuyPower4TimesActUserRate()==null){
					e.setBuyPower4TimesActUserRate(0.0);
				}
				if(e.getBuyPowerHigh5TimesActUserRate()==null){
					e.setBuyPowerHigh5TimesActUserRate(0.0);
				}
			}
		}
		return list;
	}
	@Override
	public List<YhCurrencyStat> selectYhKrypGoldConsumpList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<YhCurrencyStat> list=yhCurrencyStatMapper.selectYhKrypGoldConsumpList(searchData);
		return list;
	}
	@Override
	public List<YhCurrencyStat> selectYhKrypGoldLeftList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<YhCurrencyStat> list=yhCurrencyStatMapper.selectYhKrypGoldLeftList(searchData);
		return list;
	}
	@Override
	public List<YhCurrencyStat> selectCurOutAndConsList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<YhCurrencyStat> list=yhCurrencyStatMapper.selectCurOutAndConsList(searchData);
		return list;
	}
	@Override
	public List<YhCurrencyStat> selectCurrencyConsStatList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<YhCurrencyStat> list=yhCurrencyStatMapper.selectCurrencyConsStatList(searchData);
		return list;
	}
	@Override
	public List<YhCurrencyStat> selectCurConsUserTimesList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<YhCurrencyStat> list=yhCurrencyStatMapper.selectCurConsUserTimesList(searchData);
		return list;
	}

}
