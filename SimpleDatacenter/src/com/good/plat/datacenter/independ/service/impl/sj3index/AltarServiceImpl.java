package com.good.plat.datacenter.independ.service.impl.sj3index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.Altar;
import com.good.plat.datacenter.independ.mapper.sj3index.AltarMapper;
import com.good.plat.datacenter.independ.service.sj3index.AltarService;
@Service(value="altarService")
public class AltarServiceImpl implements AltarService{
	@Autowired
	private AltarMapper altarMapper;
	@Override
	public List<Altar> selectAltarList(IndependBaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData)searchData.searchDataFilter(searchData);
		List<Altar> list=altarMapper.selectAltarList(searchData);
		if(list!=null&&!list.isEmpty()){
			for(Altar e:list){
				e.setCurTypeName(e.getCurtype()==null?"":matchType(e.getCurtype().intValue()));
			}
		}
		return list;
	}
	private String matchType(int type){
		String result="";
		switch (type) {
		case 1:
			result="钻石";
			break;
		case 2:
			result="金币";
			break;
		default:
			result="未知货币";
			break;
		}
		return result;
	}
}
