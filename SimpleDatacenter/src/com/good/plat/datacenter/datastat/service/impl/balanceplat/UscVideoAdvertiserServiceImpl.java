package com.good.plat.datacenter.datastat.service.impl.balanceplat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.balanceplat.UscAdvertIncome;
import com.good.plat.datacenter.datastat.mapper.balanceplat.UscAdvertIncomeMapper;
import com.good.plat.datacenter.datastat.service.balanceplat.UscVideoAdvertiserService;
@Service(value="uscVideoAdvertiserService")
public class UscVideoAdvertiserServiceImpl implements UscVideoAdvertiserService {
	@Autowired
	private UscAdvertIncomeMapper uscAdvertIncomeMapper;
	@Override
	public BaseMessage deleteByPrimaryKey(Integer id) throws Exception {
		// TODO Auto-generated method stub
		BaseMessage msg=new BaseMessage();
		int result=uscAdvertIncomeMapper.deleteByPrimaryKey(id);
		if(result==1){
			msg.setStatus(1);
			msg.setMessage("删除成功");
		}else{
			msg.setStatus(0);
			msg.setMessage("删除失败");
		}
		return msg;
	}

	@Override
	public BaseMessage insertUscVideoAdvertiser(UscAdvertIncome searchData)
			throws Exception {
		// TODO Auto-generated method stub
		BaseMessage msg=new BaseMessage();
		UscAdvertIncome videoAdvertiser=uscAdvertIncomeMapper.getUscVideoAdvertiser(searchData);
		if(videoAdvertiser==null){
			int result=uscAdvertIncomeMapper.insertUscVideoAdvertiser(searchData);
			if(result==1){
				msg.setStatus(1);
				msg.setMessage("添加成功!");
			}else{
				msg.setStatus(0);
				msg.setMessage("添加失败!");
			}
		}else{
			msg.setStatus(0);
			msg.setMessage("添加失败!");
		}
		return msg;
	}

	@Override
	public List<UscAdvertIncome> selectUscVideoAdvertiserList(
			BaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		List<UscAdvertIncome> list=uscAdvertIncomeMapper.selectUscVideoAdvertiserList(searchData);
		return list;
	}

	@Override
	public BaseMessage updateByPrimaryKeySelective(UscAdvertIncome searchData)
			throws Exception {
		// TODO Auto-generated method stub
		BaseMessage msg=new BaseMessage();
		//广告商,签约公司是否存在
		UscAdvertIncome videoAdvertiser=uscAdvertIncomeMapper.getUscVideoAdvertiser(searchData);
		if(videoAdvertiser==null){
			int result=uscAdvertIncomeMapper.updateByPrimaryKeySelective(searchData);
			if(result==1){
				msg.setStatus(1);
				msg.setMessage("修改成功!");
			}else{
				msg.setStatus(0);
				msg.setMessage("修改记录ID不存在");
			}
		}else{
			msg.setStatus(0);
			msg.setMessage("广告商,签约公司已存在");
		}
		return msg;
	}

	@Override
	public UscAdvertIncome getVideoAdvertiser(Integer id) throws Exception {
		// TODO Auto-generated method stub
		UscAdvertIncome videoAdvertiser=uscAdvertIncomeMapper.getAdvertiserByPrimaryKey(id);
		return videoAdvertiser;
	}

	@Override
	public List<String> selectAdvertiserNameList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		List<String> list=uscAdvertIncomeMapper.selectAdvertiserNameList(searchData);
		return list;
	}

}
