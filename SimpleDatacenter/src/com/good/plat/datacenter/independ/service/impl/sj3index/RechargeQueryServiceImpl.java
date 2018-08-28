package com.good.plat.datacenter.independ.service.impl.sj3index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.RechargeQuery;
import com.good.plat.datacenter.independ.mapper.sj3index.RechargeQueryMapper;
import com.good.plat.datacenter.independ.service.sj3index.RechargeQueryService;
@Service(value="rechargeQueryService")
public class RechargeQueryServiceImpl implements RechargeQueryService {
	@Autowired
	private  RechargeQueryMapper rechargeQueryMapper;
	@Override
	public List<RechargeQuery> selectRechargeList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<RechargeQuery> list=rechargeQueryMapper.selectRechargeList(searchData);
		if(list!=null&&list.size()!=0){
			for(RechargeQuery e:list){
				if(e!=null){
					if(e.getTermType().intValue()==1){
						e.setSourceSys("Ios");
					}else if(e.getTermType().intValue()==2){
						e.setSourceSys("Android");
					}else if(e.getTermType().intValue()==3){
						e.setSourceSys("Winphone");
					}else if(e.getTermType().intValue()==4){
						e.setSourceSys("BlackBerry");
					}else if(e.getTermType().intValue()==5){
						e.setSourceSys("Kjava");
					}else if(e.getTermType().intValue()==6){
						e.setSourceSys("Windows(PC)");
					}else if(e.getTermType().intValue()==7){
						e.setSourceSys("IOS越狱");
					}else {
						e.setSourceSys("系统出错");
					}
				}
			}
		}
		return list;
	}

}
