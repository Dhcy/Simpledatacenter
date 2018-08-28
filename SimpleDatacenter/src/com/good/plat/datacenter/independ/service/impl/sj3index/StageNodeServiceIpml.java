package com.good.plat.datacenter.independ.service.impl.sj3index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.StageNode;
import com.good.plat.datacenter.independ.mapper.sj3index.StageNodeMapper;
import com.good.plat.datacenter.independ.service.sj3index.StageNodeService;
@Service(value="stageNodeService")
public class StageNodeServiceIpml implements StageNodeService {
	@Autowired
	private StageNodeMapper stageNodeMapper;
	@Override
	public List<StageNode> selectStageNodeSuccessList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		List<StageNode> list= stageNodeMapper.selectStageNodeSuccessList(searchData);
		return list;
	}

	@Override
	public List<StageNode> selectTimeGameStageList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		List<StageNode> list =stageNodeMapper.selectTimeGameStageList(searchData);
		return list;
	}


}
