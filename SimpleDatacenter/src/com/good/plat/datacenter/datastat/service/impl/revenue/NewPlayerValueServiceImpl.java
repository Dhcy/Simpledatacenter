package com.good.plat.datacenter.datastat.service.impl.revenue;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.revenue.NewPlayerValue;
import com.good.plat.datacenter.datastat.mapper.revenue.NewPlayerValueMapper;
import com.good.plat.datacenter.datastat.service.revenue.NewPlayerValueService;

/**
 * @ClassName: NewPlayerValueMapper
 * @Description: 新玩家价值
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
@Service(value="newPlayerValueService")
public class NewPlayerValueServiceImpl implements NewPlayerValueService {
	@Autowired
	private NewPlayerValueMapper newPlayerValueMapper;
	
	@Override
	public List<NewPlayerValue> selectXdaysAvgContributionList(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return newPlayerValueMapper.selectXdaysAvgContributionList(searchData);
	}

	@Override
	public List<NewPlayerValue> exportXdaysAvgContributionList(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return newPlayerValueMapper.exportXdaysAvgContributionList(searchData);
	}

	@Override
	public List<NewPlayerValue> selectPerDayTotalPayMoneyList(
			BaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData = searchData.searchDataFilter(searchData);
		List<NewPlayerValue> list=newPlayerValueMapper.selectPerDayTotalPayMoneyList(searchData);
		return list;
	}

	@Override
	public List<NewPlayerValue> selectSimpleDayPayMoneyList(
			BaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		List<NewPlayerValue> list=null;
		searchData = searchData.searchDataFilter(searchData);
		list=newPlayerValueMapper.selectSimpleDayPayMoneyList(searchData);
		return list;
	}

	@Override
	public List<NewPlayerValue> selectRechargeCityList(BaseSearchData searchData) {
		// TODO Auto-generated method stub
		List<NewPlayerValue> list=null;
		searchData = searchData.searchDataFilter(searchData);
		list=newPlayerValueMapper.selectRechargeCityList(searchData);
		List<NewPlayerValue> result=new ArrayList<NewPlayerValue>();
		if(list!=null&&list.size()>0){
			for(NewPlayerValue e: list){
				if(e!=null){
					result.add(e);
				}
			}
		}
		return result;
	}




}
