package com.good.plat.datacenter.independ.service.impl.yhindex;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.independ.entity.yhindex.YhIframeStat;
import com.good.plat.datacenter.independ.mapper.yhindex.YhIframeStatMapper;
import com.good.plat.datacenter.independ.service.yhindex.YhIframeStatService;
@Service(value="yhIframeStatService")
public class YhIframeStatServiceImpl implements YhIframeStatService {
	@Autowired
	private YhIframeStatMapper yhIframeStatMapper;
	@Override
	public List<YhIframeStat> selectYhIframeStatList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<YhIframeStat> list=yhIframeStatMapper.selectYhIframeStatList(searchData);
		if(list!=null&&!list.isEmpty()){
			for(YhIframeStat e:list){
				if(e.getCount()==null){
					//当天区服数量为null设置为0
					e.setCount(0);
				}
				//头像id为0时，解锁了头像的数量为当天区服的玩家数
				if(e.getIframeid()==0){
					e.setUnlockActCnt(e.getCount());
				}
				//使用头像玩家/解锁了次头像玩家数量
				Double wearActCntRate=e.getUnlockActCnt()==0?0.0:e.getWearActCnt().doubleValue()/e.getUnlockActCnt();
				//设置使用占比
				e.setWearActCntRate(NumberUtil.multi100(wearActCntRate, 2));
			}
		}
		return list;
	}

}
