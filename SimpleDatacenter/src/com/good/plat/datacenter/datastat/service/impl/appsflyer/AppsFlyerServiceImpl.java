package com.good.plat.datacenter.datastat.service.impl.appsflyer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.datastat.entity.appsflyer.AppsFlyer;
import com.good.plat.datacenter.datastat.mapper.appsflyer.AppsFlyerMapper;
import com.good.plat.datacenter.datastat.service.appsflyer.AppsFlyerService;
@Service(value="appsFlyerService")
public class AppsFlyerServiceImpl implements AppsFlyerService {
	@Autowired
	private AppsFlyerMapper appsFlyerMapper;
	@Override
	public BaseMessage addAppsFlyer(AppsFlyer af) throws Exception {
		// TODO Auto-generated method stub
		BaseMessage msg=new BaseMessage();
		int row=appsFlyerMapper.addAppsFlyer(af);
		if(row==1){
			msg.setStatus(1);
			msg.setMessage("添加成功");
		}else{
			msg.setStatus(0);
			msg.setMessage("添加失败");
		}
		return msg;
	}
	@Override
	public List<AppsFlyer> selectAfPurchaseList(AppsFlyer af) throws Exception {
		// TODO Auto-generated method stub
		List<AppsFlyer> list=appsFlyerMapper.selectAppsFlyerList(af);
		return list;
	}

}
