package com.good.plat.datacenter.game.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.game.entity.RechgGainWarn;
import com.good.plat.datacenter.game.mapper.RechgGainWarnMapper;
import com.good.plat.datacenter.game.service.RechgGainWarnService;
@Service(value="rechgGainWarnServiceImpl")
public class RechgGainWarnServiceImpl implements RechgGainWarnService {
	@Autowired
	private RechgGainWarnMapper rechgGainWarnMapper;
	@Override
	public BaseMessage addRechgGainWarn(RechgGainWarn warn) throws Exception {
		// TODO Auto-generated method stub
		int result=rechgGainWarnMapper.insertRechgGainWarn(warn);
		BaseMessage msg=new BaseMessage();
		if(result!=0){
			msg.setStatus(1);
			msg.setMessage("添加成功");
		}else{
			msg.setStatus(0);
			msg.setMessage("添加失败");
		}
		return msg;
	}

	@Override
	public BaseMessage deleteRechgGainWarn(Integer id) throws Exception {
		// TODO Auto-generated method stub
		int result=rechgGainWarnMapper.deleteByPrimaryKey(id);
		BaseMessage msg=new BaseMessage();
		if(result!=0){
			msg.setStatus(1);
			msg.setMessage("删除成功");
		}else{
			msg.setStatus(0);
			msg.setMessage("删除失败");
		}
		return msg;
	}

	@Override
	public BaseMessage editRechgGainWarn(RechgGainWarn warn) throws Exception {
		// TODO Auto-generated method stub
		int result=rechgGainWarnMapper.updateByPrimaryKey(warn);
		BaseMessage msg=new BaseMessage();
		if(result!=0){
			msg.setStatus(1);
			msg.setMessage("修改成功");
		}else{
			msg.setStatus(0);
			msg.setMessage("修改失败");
		}
		return msg;
	}

	@Override
	public List<RechgGainWarn> selectRechgGainWarnList(RechgGainWarn warn)
			throws Exception {
		// TODO Auto-generated method stub
		List<RechgGainWarn> list=rechgGainWarnMapper.selectRechgGainWarnList(warn);
		return list;
	}

	@Override
	public RechgGainWarn getRechgGainWarn(Integer id) throws Exception {
		// TODO Auto-generated method stub
		RechgGainWarn warn=null;
		warn=rechgGainWarnMapper.selectByPrimaryKey(id);
		return warn;
	}

	@Override
	public RechgGainWarn getRechgGainWarnByGameId(Integer id) throws Exception {
		// TODO Auto-generated method stub
		RechgGainWarn warn=null;
		warn=rechgGainWarnMapper.selectByGameId(id);
		return warn;
	}

}
