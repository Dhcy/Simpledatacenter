package com.good.plat.datacenter.independ.service.impl.yhindex;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.yhindex.YhTaskManage;
import com.good.plat.datacenter.independ.mapper.yhindex.YhTaskManageMapper;
import com.good.plat.datacenter.independ.service.yhindex.YhTaskManageService;
@Service(value="yhTaskManageService")
public class YhTaskManageServiceImpl implements YhTaskManageService {
	@Autowired
	private YhTaskManageMapper yhCourseStatMapper;
	@Override
	public List<YhTaskManage> selectYhCourseStatList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<YhTaskManage> list=yhCourseStatMapper.selectYhCourseStatList(searchData);
		if(list!=null&&!list.isEmpty()){
			for(YhTaskManage e :list){
				if(e.getRate()==null){
					e.setRate(0.0);
				}
			}
		}
		return list;
	}
	@Override
	public List<YhTaskManage> selectYhSystemJoinList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<YhTaskManage> list=yhCourseStatMapper.selectYhSystemJoinList(searchData);
		if(list!=null&&!list.isEmpty()){
			for(YhTaskManage e :list){
				if(e.getJoinUserRate()==null){
					e.setJoinUserRate(0.0);
				}
			}
		}
		return list;
	}
	@Override
	public List<YhTaskManage> selectYhCourseDisStatList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<YhTaskManage> list=yhCourseStatMapper.selectYhCourseDisStatList(searchData);
		if(list!=null&&!list.isEmpty()){
			for(YhTaskManage e :list){
				if(e.getRate()==null){
					e.setRate(0.0);
				}
			}
		}
		return list;
	}

}
