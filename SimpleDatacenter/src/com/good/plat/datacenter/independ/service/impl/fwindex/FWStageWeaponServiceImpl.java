package com.good.plat.datacenter.independ.service.impl.fwindex;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.independ.entity.fwindex.FWStageWeapon;
import com.good.plat.datacenter.independ.mapper.fwindex.FWStageWeaponMapper;
import com.good.plat.datacenter.independ.service.fwindex.FWStageWeaponService;
@Service(value="fWStageWeaponServiceImpl")
public class FWStageWeaponServiceImpl implements FWStageWeaponService {
	@Autowired
private FWStageWeaponMapper fWStageWeaponMapper;
	@Override
	public List<FWStageWeapon> selectStageWeaponList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		List<FWStageWeapon> list=fWStageWeaponMapper.selectStageWeaponList(searchData);
		return list;
	}

}
