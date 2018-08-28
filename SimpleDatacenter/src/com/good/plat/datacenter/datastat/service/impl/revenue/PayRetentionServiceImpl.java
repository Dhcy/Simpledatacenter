package com.good.plat.datacenter.datastat.service.impl.revenue;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.datastat.entity.revenue.PayRetention;
import com.good.plat.datacenter.datastat.mapper.revenue.PayRetentionMapper;
import com.good.plat.datacenter.datastat.service.revenue.PayRetentionService;
/**
 * 付费留存
 * @ClassName: PayRetentionServiceImpl
 * @Description: TODO
 * @author hwj
 * @date 2017-1-19 下午03:17:58
 */
@Service(value="payRetentionService")
public class PayRetentionServiceImpl implements PayRetentionService {
	@Autowired
	private PayRetentionMapper payRetentionMapper;
	@Override
	public List<PayRetention> selectPayRateOfFollowList(
			BaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData.setChecktype1("2");
		searchData=searchData.searchDataFilter(searchData);
		List<PayRetention> payRateOfFollowList=payRetentionMapper.selectRateOfRatateAndPay(searchData);
		if(payRateOfFollowList!=null&&payRateOfFollowList.size()!=0){
			for(PayRetention r:payRateOfFollowList){
				if(r.getDay1()==null){
					r.setDay1(0.0);
				}
				if(r.getDay2()==null){
					r.setDay2(0.0);
				}
				if(r.getDay3()==null){
					r.setDay3(0.0);
				}
				if(r.getDay4()==null){
					r.setDay4(0.0);
				}
				if(r.getDay5()==null){
					r.setDay5(0.0);
				}
				if(r.getDay6()==null){
					r.setDay6(0.0);
				}
				if(r.getDay7()==null){
					r.setDay7(0.0);
				}
				if(r.getDay14()==null){
					r.setDay14(0.0);
				}
				if(r.getDay30()==null){
					r.setDay30(0.0);
				}
				r.setDay1(r.getAccounts().intValue()==0?0.0:NumberUtil.multi100(r.getDay1()/r.getAccounts(), NumberUtil.DEFAULT_SCALE));
				r.setDay2(r.getAccounts().intValue()==0?0.0:NumberUtil.multi100(r.getDay2()/r.getAccounts(), NumberUtil.DEFAULT_SCALE));
				r.setDay3(r.getAccounts().intValue()==0?0.0:NumberUtil.multi100(r.getDay3()/r.getAccounts(), NumberUtil.DEFAULT_SCALE));
				r.setDay4(r.getAccounts().intValue()==0?0.0:NumberUtil.multi100(r.getDay4()/r.getAccounts(), NumberUtil.DEFAULT_SCALE));
				r.setDay5(r.getAccounts().intValue()==0?0.0:NumberUtil.multi100(r.getDay5()/r.getAccounts(), NumberUtil.DEFAULT_SCALE));
				r.setDay6(r.getAccounts().intValue()==0?0.0:NumberUtil.multi100(r.getDay6()/r.getAccounts(), NumberUtil.DEFAULT_SCALE));
				r.setDay7(r.getAccounts().intValue()==0?0.0:NumberUtil.multi100(r.getDay7()/r.getAccounts(), NumberUtil.DEFAULT_SCALE));
				r.setDay14(r.getAccounts().intValue()==0?0.0:NumberUtil.multi100(r.getDay14()/r.getAccounts(), NumberUtil.DEFAULT_SCALE));
				r.setDay30(r.getAccounts().intValue()==0?0.0:NumberUtil.multi100(r.getDay30()/r.getAccounts(), NumberUtil.DEFAULT_SCALE));
			}
		}
		return payRateOfFollowList;
	}




	@Override
	public List<PayRetention> selectRetationOfPayPlayerList(
			BaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData.setChecktype1("1");
		searchData=searchData.searchDataFilter(searchData);
		List<PayRetention> payRateOfFollowList=payRetentionMapper.selectRateOfRatateAndPay(searchData);
		if(payRateOfFollowList!=null&&payRateOfFollowList.size()!=0){
			for(PayRetention r:payRateOfFollowList){
				if(r.getDay1()==null){
					r.setDay1(0.0);
				}
				if(r.getDay2()==null){
					r.setDay2(0.0);
				}
				if(r.getDay3()==null){
					r.setDay3(0.0);
				}
				if(r.getDay4()==null){
					r.setDay4(0.0);
				}
				if(r.getDay5()==null){
					r.setDay5(0.0);
				}
				if(r.getDay6()==null){
					r.setDay6(0.0);
				}
				if(r.getDay7()==null){
					r.setDay7(0.0);
				}
				if(r.getDay14()==null){
					r.setDay14(0.0);
				}
				if(r.getDay30()==null){
					r.setDay30(0.0);
				}
				r.setDay1(r.getAccounts().intValue()==0?0.0:NumberUtil.multi100(r.getDay1()/r.getAccounts(), NumberUtil.DEFAULT_SCALE));
				r.setDay2(r.getAccounts().intValue()==0?0.0:NumberUtil.multi100(r.getDay2()/r.getAccounts(), NumberUtil.DEFAULT_SCALE));
				r.setDay3(r.getAccounts().intValue()==0?0.0:NumberUtil.multi100(r.getDay3()/r.getAccounts(), NumberUtil.DEFAULT_SCALE));
				r.setDay4(r.getAccounts().intValue()==0?0.0:NumberUtil.multi100(r.getDay4()/r.getAccounts(), NumberUtil.DEFAULT_SCALE));
				r.setDay5(r.getAccounts().intValue()==0?0.0:NumberUtil.multi100(r.getDay5()/r.getAccounts(), NumberUtil.DEFAULT_SCALE));
				r.setDay6(r.getAccounts().intValue()==0?0.0:NumberUtil.multi100(r.getDay6()/r.getAccounts(), NumberUtil.DEFAULT_SCALE));
				r.setDay7(r.getAccounts().intValue()==0?0.0:NumberUtil.multi100(r.getDay7()/r.getAccounts(), NumberUtil.DEFAULT_SCALE));
				r.setDay14(r.getAccounts().intValue()==0?0.0:NumberUtil.multi100(r.getDay14()/r.getAccounts(), NumberUtil.DEFAULT_SCALE));
				r.setDay30(r.getAccounts().intValue()==0?0.0:NumberUtil.multi100(r.getDay30()/r.getAccounts(), NumberUtil.DEFAULT_SCALE));
			}
		}
		return payRateOfFollowList;
	}


}
