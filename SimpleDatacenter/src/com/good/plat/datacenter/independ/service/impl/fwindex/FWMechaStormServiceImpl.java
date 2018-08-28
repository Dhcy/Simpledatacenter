package com.good.plat.datacenter.independ.service.impl.fwindex;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.independ.entity.fwindex.FWMechaStorm;
import com.good.plat.datacenter.independ.mapper.fwindex.FWMechaStormMapper;
import com.good.plat.datacenter.independ.service.fwindex.FWMechaStormService;
@Service(value="fWMechaStormServiceImpl")
public class FWMechaStormServiceImpl implements FWMechaStormService{
	@Autowired
	private FWMechaStormMapper fWMechaStormMapper;
	@Override
	public List<FWMechaStorm> selectMechaStormList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		List<FWMechaStorm> list=fWMechaStormMapper.selectMechaStormList(searchData);
		if(list!=null&&!list.isEmpty()){
			for(FWMechaStorm e:list){
				Double rate=e.getInCnt().intValue()==0?0.0:NumberUtil.div(e.getFinishCnt().doubleValue(), e.getInCnt().doubleValue());
				e.setRate(NumberUtil.multi100(rate, NumberUtil.DEFAULT_SCALE));//占比
			}
		}
		return list;
	}

}
