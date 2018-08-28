package com.good.plat.datacenter.datastat.service.impl.revenue;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.base.DateUtil;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.datastat.entity.revenue.Ltv;
import com.good.plat.datacenter.datastat.mapper.revenue.LtvMapper;
import com.good.plat.datacenter.datastat.service.revenue.LtvService;
@Service(value="ltvService")
public class LtvServiceImpl implements LtvService {
	@Autowired
	private LtvMapper ltvMapper;
	@Override
	public List<Ltv> selectRegPayMoneyAndCountAndRate(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<Ltv> list=new ArrayList<Ltv>();
		list=ltvMapper.selectRegPayMoneyAndCount(searchData);
		if(list!=null){
			for(Ltv p:list){
				if(p.getNewact()==null){
					p.setNewact(0);
				}
				if(p.getNewact()==0){
					p.setDay0_pay_rate(0.0);
					p.setDay1_pay_rate(0.0);
					p.setDay2_pay_rate(0.0);
					p.setDay3_pay_rate(0.0);
					p.setDay4_pay_rate(0.0);
					p.setDay5_pay_rate(0.0);
					p.setDay6_pay_rate(0.0);
					p.setDay7_pay_rate(0.0);
					p.setDay8_pay_rate(0.0);
					p.setDay9_pay_rate(0.0);
					p.setDay10_pay_rate(0.0);
					p.setDay11_pay_rate(0.0);
					p.setDay12_pay_rate(0.0);
				}else{
					p.setDay0_pay_rate(NumberUtil.div(p.getDay0_pay(), p.getNewact().doubleValue(), 2));
					p.setDay1_pay_rate(NumberUtil.div(p.getDay1_pay(), p.getNewact().doubleValue(), 2));
					p.setDay2_pay_rate(NumberUtil.div(p.getDay2_pay(), p.getNewact().doubleValue(), 2));
					p.setDay3_pay_rate(NumberUtil.div(p.getDay3_pay(), p.getNewact().doubleValue(), 2));
					p.setDay4_pay_rate(NumberUtil.div(p.getDay4_pay(), p.getNewact().doubleValue(), 2));
					p.setDay5_pay_rate(NumberUtil.div(p.getDay5_pay(), p.getNewact().doubleValue(), 2));
					p.setDay6_pay_rate(NumberUtil.div(p.getDay6_pay(), p.getNewact().doubleValue(), 2));
					p.setDay7_pay_rate(NumberUtil.div(p.getDay7_pay(), p.getNewact().doubleValue(), 2));
					p.setDay8_pay_rate(NumberUtil.div(p.getDay8_pay(), p.getNewact().doubleValue(), 2));
					p.setDay9_pay_rate(NumberUtil.div(p.getDay9_pay(), p.getNewact().doubleValue(), 2));
					p.setDay10_pay_rate(NumberUtil.div(p.getDay10_pay(), p.getNewact().doubleValue(), 2));
					p.setDay11_pay_rate(NumberUtil.div(p.getDay11_pay(), p.getNewact().doubleValue(), 2));
					p.setDay12_pay_rate(NumberUtil.div(p.getDay12_pay(), p.getNewact().doubleValue(), 2));
				}
				
			}
		}
		return list;
	}
	@Override
	public List<Ltv> selectLtvofCurrDateList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		String startDate=searchData.getStartdate();
		String endDate=searchData.getEnddate();
		List<String> datelist=new ArrayList<String>();
		if(DateUtil.parse_yyyy_MM_dd(startDate).getTime()<DateUtil.parse_yyyy_MM_dd(endDate).getTime()){
			//datelist.add(startDate);
			while(DateUtil.parse_yyyy_MM_dd(startDate).getTime()-DateUtil.parse_yyyy_MM_dd(endDate).getTime()<=0){
				datelist.add(startDate);
				startDate=DateUtil.addDay(startDate, 1);
				
			}
		}else if(DateUtil.parse_yyyy_MM_dd(startDate).getTime()-DateUtil.parse_yyyy_MM_dd(endDate).getTime()==0){
			datelist.add(startDate);
		}else{
			
		}
		List<Ltv> ltvList=new ArrayList<Ltv>();
		if(datelist!=null){
			for(String date:datelist){
				searchData.setEnddate(date);
				Ltv ltv=ltvMapper.selectLtvofCurrDate(searchData);
				if(ltv.getStatdate()==null){
					ltv.setStatdate(date);
				}
				ltvList.add(ltv);
			}
		}
		if(ltvList!=null){
			for(Ltv v:ltvList ){
				if(v.getLtv3()==null){
					v.setLtv3(0.0);
				}
				if(v.getLtv7()==null){
					v.setLtv7(0.0);
				}
				if(v.getLtv30()==null){
					v.setLtv30(0.0);
				}
				if(v.getLtv60()==null){
					v.setLtv60(0.0);
				}
			}
		}
		return ltvList;
	}

}
