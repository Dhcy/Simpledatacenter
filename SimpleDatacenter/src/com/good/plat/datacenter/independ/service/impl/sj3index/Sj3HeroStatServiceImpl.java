package com.good.plat.datacenter.independ.service.impl.sj3index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.independ.entity.sj3index.Sj3HeroStat;
import com.good.plat.datacenter.independ.mapper.sj3index.Sj3HeroStatMapper;
import com.good.plat.datacenter.independ.service.sj3index.Sj3HeroStatService;
@Service(value="sj3HeroStatService")
public class Sj3HeroStatServiceImpl implements Sj3HeroStatService {
	@Autowired
	private Sj3HeroStatMapper sj3HeroStatMapper;
	@Override
	public List<Sj3HeroStat> selectStageStatBoforePerday(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<Sj3HeroStat> list=sj3HeroStatMapper.selectStageStatBoforePerday(searchData);
		Map<String,Integer> areaOpenCntMap=new HashMap<String, Integer>();
		if(list!=null&&!list.isEmpty()){
			for(Sj3HeroStat e:list){
				if(e.getCount()==null){
					e.setCount(0);
				}
				//每个区服分母为阶级1,难度为简单的历史以来开启人数
				if(e.getOrder()==1&&e.getDifficult().equals("简单")){
					areaOpenCntMap.put(e.getStatdate()+"~"+e.getAreaid(), e.getOpencnt());
				}
			}
			System.out.println("分母="+areaOpenCntMap.toString());
			//占总阶级难度人数的百分比。
			for(Sj3HeroStat e:list){
					Integer openCnt=areaOpenCntMap.get(e.getStatdate()+"~"+e.getAreaid());
					if(openCnt==null){
						e.setRate(0.0);
					}else{
						Double rate=(openCnt==0)?0.0:NumberUtil.div(e.getCount().doubleValue(), openCnt.doubleValue(),4);
						e.setRate(NumberUtil.multi100(rate, 2));
					}
				}
		}
		return list;
	}
	@Override
	public List<Sj3HeroStat> selectStageStat(IndependBaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<Sj3HeroStat> list=sj3HeroStatMapper.selectStageStat(searchData);
		if(list!=null&&!list.isEmpty()){
			for(Sj3HeroStat e:list){
				StringBuffer sb=new StringBuffer();
				String stageName=sb.append(e.getOrder()).append("阶").append(e.getDifficult()).append("难度").toString();
				e.setLevelDifficultStr(stageName);
				if(e.getActsuccnt()==null){
					e.setActsuccnt(0);
				}
				//平均通关用时
				e.setAvgsuctime(e.getActsuccnt()==0?0.0:NumberUtil.div(e.getSuctime().doubleValue(), e.getActsuccnt().doubleValue(), 0));
				//平均通关星数
				e.setAvgsucstarcnt(e.getActsuccnt()==0?0.0:NumberUtil.div(e.getStarcnt().doubleValue(), e.getActsuccnt().doubleValue(), 0));
			}
		}
		return list;
	}

}
