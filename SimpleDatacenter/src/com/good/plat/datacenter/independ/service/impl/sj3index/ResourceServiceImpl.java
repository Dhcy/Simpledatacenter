package com.good.plat.datacenter.independ.service.impl.sj3index;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.util.AttrValueUtil;
import com.good.plat.datacenter.independ.entity.sj3index.ResourceSummary;
import com.good.plat.datacenter.independ.mapper.sj3index.ResourceSummaryMapper;
import com.good.plat.datacenter.independ.service.sj3index.ResourceService;
@Service(value="resourceService")
public class ResourceServiceImpl implements ResourceService {
	@Autowired
	private ResourceSummaryMapper resourceMapper;
	@Autowired
	private AttrValueUtil attrValueUtil;
	@Override
	public List<ResourceSummary> selectResourceSummaryList(
			BaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		String resourceType=searchData.getChecktype1();
		//checktype1='-1'时,查询全部过滤配置要去掉的资源类型的
		if(resourceType.equals("-1")){
			Map<String,String> resValueMap=attrValueUtil.getAllValueDescByCode(AttrValueUtil.SJ3_RESOURCE_TYPE);
			List<String> filterValues=new ArrayList<String>(resValueMap.keySet());
			searchData.setFilterList(filterValues);
		}
		List<ResourceSummary> list= resourceMapper.selectResourceSummaryList(searchData);
		return list;
	}
	@Override
	public List<ResourceSummary> selectResourceTypeList(
			BaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<ResourceSummary> list=resourceMapper.selectResourceTypeList(searchData);
		List<ResourceSummary> newList=new LinkedList<ResourceSummary>();
		if(list!=null&&list.size()>0){
			Map<String,String> resValueMap=attrValueUtil.getAllValueDescByCode(AttrValueUtil.SJ3_RESOURCE_TYPE);
			for(int i=0;i<list.size();i++){
				ResourceSummary e=list.get(i);
				if(e!=null){
					String typeName=e.getTypeName();
					if(typeName!=null&&!resValueMap.containsKey(typeName)){
						//金币放首位
						if(typeName.equals("金币")){
							e.setStatType(e.getTypeName());
							newList.add(0, e);
						}else{
							e.setStatType(e.getTypeName());
							newList.add(e);
						}
					}
				}
			}
		}
		return newList;
	}
	@Override
	public List<ResourceSummary> selectResourceWayList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<ResourceSummary> list=resourceMapper.selectResourceWayList(searchData);
		if(list!=null&&list.size()!=0){
			for(ResourceSummary e:list){
				e.setStatType(e.getPurposeName());
			}
		}
		return list;
	}

}
