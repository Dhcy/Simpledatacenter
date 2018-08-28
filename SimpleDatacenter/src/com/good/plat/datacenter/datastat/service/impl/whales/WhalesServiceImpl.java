package com.good.plat.datacenter.datastat.service.impl.whales;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.whales.Whales;
import com.good.plat.datacenter.datastat.mapper.whales.WhalesMapper;
import com.good.plat.datacenter.datastat.service.whales.WhalesService;

/**
 * @ClassName: WhalesMapper
 * @Description: 鲸鱼用户
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
@Service(value="whalesService")
public class WhalesServiceImpl implements WhalesService{
	private final static Logger logger = LoggerFactory.getLogger(WhalesServiceImpl.class);

	@Autowired
	private WhalesMapper whalesMapper;
	
	
	@Override
	public List<Whales> selectWhaleUserList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		logger.info(searchData.toString());
		
		List<Whales> whaleList =  whalesMapper.selectWhaleUserList(searchData);
		if (whaleList != null&&whaleList.size()!=0) {
			for (int i=0; i<whaleList.size(); i++) {
				whaleList.get(i).setRanking(i+1);
				if(whaleList.get(i).getTermType()==1){
					whaleList.get(i).setChannelName("IOS");
				}
				if(whaleList.get(i).getTermType()==2){
					whaleList.get(i).setChannelName("Android");
				}
				if(whaleList.get(i).getTermType()==3){
					whaleList.get(i).setChannelName("Winphone");
				}
				if(whaleList.get(i).getTermType()==4){
					whaleList.get(i).setChannelName("BlackBerry");				
				}
				if(whaleList.get(i).getTermType()==5){
					whaleList.get(i).setChannelName("KJava");
				}
				if(whaleList.get(i).getTermType()==6){
					whaleList.get(i).setChannelName("Windows(pc)");
				}
				if(whaleList.get(i).getTermType()==7){
					whaleList.get(i).setChannelName("IOS越狱");
				}
				if(whaleList.get(i).getTermType()==8){
					whaleList.get(i).setChannelName("Web");		
				}
				//截取当前剩余氪金
				if(whaleList.get(i).getLeftCount()==null||whaleList.get(i).getLeftCount().equals("")){
					whaleList.get(i).setCurrLeftCount(0);
				}else{
					String [] str= whaleList.get(i).getLeftCount().split("\\|\\|");
					if("null".equals(str[1])) str[1] ="0";
					whaleList.get(i).setCurrLeftCount(Integer.parseInt(str[1]));
				}
				
				//截取当前剩余绑定氪金
				if(whaleList.get(i).getLeftBindCountStr()==null||whaleList.get(i).getLeftBindCountStr().equals("")){
					whaleList.get(i).setLeftBindCount(0);
				}else{
					String [] str= whaleList.get(i).getLeftBindCountStr().split("\\|\\|");
					if("null".equals(str[1])) str[1] ="0";
					whaleList.get(i).setLeftBindCount(Integer.parseInt(str[1]));
				}
			}
		}
		return whaleList;
	}

	@Override
	public List<Whales> exportWhaleUserList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		
		List<Whales> whaleList =  whalesMapper.exportWhaleUserList(searchData);
		if (whaleList != null&&whaleList.size()!=0) {
			for (int i=0; i<whaleList.size(); i++) {
				whaleList.get(i).setRanking(i+1);
				if(whaleList.get(i).getTermType()==1){
					whaleList.get(i).setChannelName("IOS");
				}
				if(whaleList.get(i).getTermType()==2){
					whaleList.get(i).setChannelName("Android");
				}
				if(whaleList.get(i).getTermType()==3){
					whaleList.get(i).setChannelName("Winphone");
				}
				if(whaleList.get(i).getTermType()==4){
					whaleList.get(i).setChannelName("BlackBerry");				
				}
				if(whaleList.get(i).getTermType()==5){
					whaleList.get(i).setChannelName("KJava");
				}
				if(whaleList.get(i).getTermType()==6){
					whaleList.get(i).setChannelName("Windows(pc)");
				}
				if(whaleList.get(i).getTermType()==7){
					whaleList.get(i).setChannelName("IOS越狱");
				}
				if(whaleList.get(i).getTermType()==8){
					whaleList.get(i).setChannelName("Web");		
				}
				//截取当前剩余氪金
				if(whaleList.get(i).getLeftCount()==null||whaleList.get(i).getLeftCount().equals("")){
					whaleList.get(i).setCurrLeftCount(0);
				}else{
					String [] str= whaleList.get(i).getLeftCount().split("\\|\\|");
					if("null".equals(str[1])) str[1] ="0";
					whaleList.get(i).setCurrLeftCount(Integer.parseInt(str[1]));
				}
				
				//截取当前剩余绑定氪金
				if(whaleList.get(i).getLeftBindCountStr()==null||whaleList.get(i).getLeftBindCountStr().equals("")){
					whaleList.get(i).setLeftBindCount(0);
				}else{
					String [] str= whaleList.get(i).getLeftBindCountStr().split("\\|\\|");
					if("null".equals(str[1])) str[1] ="0";
					whaleList.get(i).setLeftBindCount(Integer.parseInt(str[1]));
				}
			}
		}
		return whaleList;
	}

	@Override
	public List<Whales> selectChargeUserList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return whalesMapper.selectChargeUserList(searchData);
	}

	@Override
	public List<Whales> selectChargeInfoList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return whalesMapper.selectChargeInfoList(searchData);
	}
	
}
