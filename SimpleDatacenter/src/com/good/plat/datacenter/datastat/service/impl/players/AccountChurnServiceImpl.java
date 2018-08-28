package com.good.plat.datacenter.datastat.service.impl.players;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.datastat.entity.players.AccountChurn;
import com.good.plat.datacenter.datastat.mapper.players.AccountChurnMapper;
import com.good.plat.datacenter.datastat.service.players.AccountChurnService;
@Service(value="accountChurnService")
public class AccountChurnServiceImpl implements AccountChurnService {
	@Autowired
	private AccountChurnMapper accountChurnMapper;
	@Override
	public List<AccountChurn> selectLostUserAnalysNumList(
			BaseSearchData searchData) {
		// TODO Auto-generated method stub
		searchData = searchData.searchDataFilter(searchData);
		List<AccountChurn> rlist = new ArrayList<AccountChurn>();
		List<AccountChurn> listAc = accountChurnMapper.selectLostUserAnalysNumList(searchData);
		double totalAccounts = 0.0;
		Collections.sort(listAc,new Comparator<AccountChurn>() {
			public int compare(AccountChurn o1, AccountChurn o2) {
				return o1.getSubtype().compareTo(o2.getSubtype());
			}
		});
		for(AccountChurn ac:listAc){
			if(ac.getAccounts() == null){
				ac.setAccounts(0);
			}
			totalAccounts += ac.getAccounts();
		}
		
		for(AccountChurn ac : listAc){
			String typename="";
			
			//
			if(searchData.getChecktype3()!=null&&searchData.getChecktype3().equals("1")){
				//流失等级
				typename = ac.getSubtype() + "";
			}else if(searchData.getChecktype3()!=null&&searchData.getChecktype3().equals("2")){
				//2、用户生命
				if(ac.getSubtype()<=1){
					typename="新用户";
				}else if(ac.getSubtype()<=3){
					typename="2~3日";
				}else if(ac.getSubtype()<=7){
					typename="4~7日";
				}else if(ac.getSubtype()<=14){
					typename="8~14日";
				}else if(ac.getSubtype()<=30){
					typename="15~30日";
				}else if(ac.getSubtype()<=90){
					typename="31~90日";
				}else if(ac.getSubtype()<=180){
					typename="91~180日";
				}else if(ac.getSubtype()<=365){
					typename="181~365日";
				}else if(ac.getSubtype()>365){
					typename="1年+";
				}
			}else if(searchData.getChecktype3()!=null&&searchData.getChecktype3().equals("3")){
				//3、用户金额
				if(ac.getSubtype()<12){
					typename="<=11";
				}else if(ac.getSubtype()<=60){
					typename="<=60";
				}else if(ac.getSubtype()<=120){
					typename="<=120";
				}else if(ac.getSubtype()<=240){
					typename="<=240";
				}else if(ac.getSubtype()<=600){
					typename="<=600";
				}else if(ac.getSubtype()<=1200){
					typename="<=1200";
				}else if(ac.getSubtype()<=3600){
					typename="<=3600";
				}else if(ac.getSubtype()<=6000){
					typename="<=6000";
				}else if(ac.getSubtype()<=12000){
					typename="<=12000";
				}else if(ac.getSubtype()>12000){
					typename="<=12000";
				}
			}else if(searchData.getChecktype3()!=null&&searchData.getChecktype3().equals("4")){
				//4、付费次数
				if(ac.getSubtype()==0){
					typename="未付费";
				}else if(ac.getSubtype()<=5){
					typename=ac.getSubtype()+"次";
				}else if(ac.getSubtype()<=10){
					typename="6~10次";
				}else if(ac.getSubtype()<=20){
					typename="11~20次";
				}else if(ac.getSubtype()<=30){
					typename="21~30次";
				}else if(ac.getSubtype()<=40){
					typename="31~40次";
				}else if(ac.getSubtype()>40){
					typename="41+";
				}
			}
			ac.setStatdate(typename);
			boolean found = false;
			for(AccountChurn e: rlist){
				if(typename.equals(e.getStatdate())){
					found = true;
					//
					e.setAccounts(e.getAccounts() + ac.getAccounts());
					e.setRate(NumberUtil.mul(NumberUtil.div(e.getAccounts().doubleValue(), totalAccounts,2), 100.0,NumberUtil.DEFAULT_SCALE));
					break;
				}
			}
			if(!found){
				ac.setRate(NumberUtil.mul(NumberUtil.div((double)ac.getAccounts(), totalAccounts,2), 100.0,NumberUtil.DEFAULT_SCALE));
				rlist.add(ac);
			}
		}
		return rlist;
	}

	@Override
	public List<AccountChurn> selectPerDayLostNumAndRate(
			BaseSearchData searchData) {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<AccountChurn> list= accountChurnMapper.selectPerDayLostNumAndRate(searchData);
		if(list!=null&&list.size()>0){
			for(AccountChurn e:list){
				e.setRate(NumberUtil.mul(NumberUtil.div((double)e.getAccounts(), (double)e.getSumactvcnt(),2), 100.0,NumberUtil.DEFAULT_SCALE));
			}
		}
		return list;
	}

}
