package com.good.plat.datacenter.datastat.service.impl.project;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.base.DateUtil;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.datastat.entity.project.ProjectGameDetail;
import com.good.plat.datacenter.datastat.mapper.project.ProjectGameDetailMapper;
import com.good.plat.datacenter.datastat.service.project.ProjectGameDetailsService;
@Service(value="projectGameDetailsService")
public class ProjectGameDetailsServiceImpl implements ProjectGameDetailsService {
	@Autowired
	private ProjectGameDetailMapper GameDetailMapper;
	@Override
	public List<ProjectGameDetail> selectGameDataListByProject(
			BaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		Date today=new Date();//今天
		Date preDay=DateUtil.add(today, Calendar.DAY_OF_MONTH, -1);//昨天
		searchData=searchData.searchDataFilter(searchData);
		searchData.setStartdate(DateUtil.format_yyyy_MM_dd(preDay));
		searchData.setEnddate(DateUtil.format_yyyy_MM_dd(today));
		//游戏列表
		List<ProjectGameDetail> gameList=GameDetailMapper.selectGameListByProjectId(searchData);
		List<ProjectGameDetail> payTimeList=null;
		List<ProjectGameDetail> gameDataList=null;
		//日期列表
		List<String> dateList=new ArrayList<String>();
		dateList.add(DateUtil.format_yyyy_MM_dd(preDay));
		dateList.add(DateUtil.format_yyyy_MM_dd(today));
		Map<String,Integer> timeMap=new HashMap<String, Integer>();
		if(gameList!=null&&gameList.size()>0){
			//获取游戏数据列表，及付费次数
			for(ProjectGameDetail e:gameList){
				searchData.setGameid(e.getGameid());
				payTimeList=GameDetailMapper.selectEveryGamePayTime(searchData);
				gameDataList=GameDetailMapper.selectEveryGameDataList(searchData);
				e.setGameDataList(gameDataList);
				e.setGamePayTimeList(payTimeList);
			}
			//遍历游戏列表
			for(ProjectGameDetail e:gameList){
				if(e.getGamePayTimeList()!=null&&e.getGamePayTimeList().size()>0){
					for(ProjectGameDetail f:e.getGamePayTimeList()){
						//付费次数存到map
						timeMap.put(f.getStatdate(), f.getPayTime());
					}
				}
				
				if(e.getGameDataList()!=null&&e.getGameDataList().size()>0){
					for(ProjectGameDetail f:e.getGameDataList()){
						//游戏数据设置付费次数
						if(timeMap.get(f.getStatdate())==null){
							f.setPayTime(0);
						}else{
							f.setPayTime(timeMap.get(f.getStatdate()));
						}
						//昨天
						if(f.getStatdate().equals(DateUtil.format_yyyy_MM_dd(preDay))){
							f.setDate("昨日");
						}else if(f.getStatdate().equals(DateUtil.format_yyyy_MM_dd(today))){
							f.setDate("今日");
						}
					}
					List<ProjectGameDetail> newGameDataList=new ArrayList<ProjectGameDetail>();;
					//只有一条记录，那没有的那条填充为0
					if(e.getGameDataList().size()==1){
						newGameDataList.add(e.getGameDataList().get(0));
						for(ProjectGameDetail f:e.getGameDataList()){
							//记录是昨天的数据,那就填充今天的数据为填充为0
							if(f.getStatdate().equals(DateUtil.format_yyyy_MM_dd(preDay))){
								ProjectGameDetail detail=new ProjectGameDetail();
								detail.setStatdate(DateUtil.format_yyyy_MM_dd(today));
								detail.setActvCount(0);
								detail.setRegisterCount(0);
								detail.setActUser(0);
								detail.setPayUser(0);
								detail.setPayTime(0);
								detail.setDate("今日");
								newGameDataList.add(1,detail);
							}else{
								//记录是今天的数据，那就填充昨天的数据填充为0
								ProjectGameDetail detail=new ProjectGameDetail();
								detail.setStatdate(DateUtil.format_yyyy_MM_dd(preDay));
								detail.setActvCount(0);
								detail.setRegisterCount(0);
								detail.setActUser(0);
								detail.setPayUser(0);
								detail.setPayTime(0);
								detail.setDate("昨日");
								newGameDataList.add(0, detail);
							}
						}
						e.setGameDataList(newGameDataList);
					}
				}else if(e.getGameDataList()!=null&&e.getGameDataList().size()==0){
					//没有今天昨天数据,全部填充为0
					gameDataList=new ArrayList<ProjectGameDetail>();
					for(String date:dateList){
						ProjectGameDetail detail=new ProjectGameDetail();
						detail.setActvCount(0);
						detail.setRegisterCount(0);
						detail.setActUser(0);
						detail.setPayUser(0);
						detail.setPayTime(0);
						detail.setStatdate(date);
						//昨日
						if(date.equals(DateUtil.format_yyyy_MM_dd(preDay))){
							detail.setDate("昨日");
						}else if(date.equals(DateUtil.format_yyyy_MM_dd(today))){
							detail.setDate("今日");
						}
						gameDataList.add(detail);
					}
					e.setGameDataList(gameDataList);
				}
			}
				}
		if(gameList!=null&&gameList.size()>0){
			for(ProjectGameDetail e:gameList){
				Integer total_actvCount=0;
				Integer total_registerCount=0;
				Integer total_actUser=0;
				Integer total_payUser=0;
				Integer total_payTime=0;
				Double total_income=0.0;
				Double total_arpu=0.0;
				List<ProjectGameDetail> list=e.getGameDataList();
				if(list!=null&&list.size()>0){
					for(ProjectGameDetail p:list){
						total_actvCount+=p.getActvCount();
						total_registerCount+=p.getRegisterCount();
						total_actUser+=p.getActUser();
						total_payUser+=p.getPayUser();
						total_payTime+=p.getPayTime();
						total_income+=p.getIncome();
						total_arpu+=p.getArpu();
					}
				}
				e.setActvCount(total_actvCount);
				e.setRegisterCount(total_registerCount);
				e.setActUser(total_actUser);
				e.setPayUser(total_payUser);
				e.setPayTime(total_payTime);
				e.setIncome(total_income);
				e.setArpu(NumberUtil.ajustScale(total_arpu, 2));
			}
		}
		
		return gameList;

	}
}
