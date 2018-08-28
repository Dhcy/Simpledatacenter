package com.good.plat.datacenter.independ.service.impl.sj3index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.independ.entity.sj3index.Diamond;
import com.good.plat.datacenter.independ.mapper.sj3index.DiamondMapper;
import com.good.plat.datacenter.independ.service.sj3index.DiamondService;

@Service(value="diamondService")
public class DiamondServiceImpl implements DiamondService{
	@Autowired
	private DiamondMapper diamondMapper;
	@Override
	public List<Diamond> selectDiamondList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<Diamond> list=diamondMapper.selectDiamondList(searchData);
		return list;
	}
	@Override
	public List<Diamond> selectDiamondDetailList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<Diamond> list=diamondMapper.selectDiamondDetailList(searchData);
		Integer total=0;
		if(list!=null&&list.size()!=0){
			for(Diamond e:list){
				if(e!=null){
					total+=e.getPurposeNum();
				}
			}
			for(Diamond e:list){
				if(e!=null){
					e.setRate(total==0?0.0:NumberUtil.multi100(e.getPurposeNum().doubleValue()/total, NumberUtil.DEFAULT_SCALE));
				}
			}
		}
		return list;
	}

}
