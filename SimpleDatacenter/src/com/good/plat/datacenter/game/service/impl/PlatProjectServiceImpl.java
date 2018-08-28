package com.good.plat.datacenter.game.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.game.entity.Project;
import com.good.plat.datacenter.game.entity.TBLogplatSysPlat;
import com.good.plat.datacenter.game.mapper.PlatProjectMapper;
import com.good.plat.datacenter.game.mapper.TBLogplatSysPlatMapper;
import com.good.plat.datacenter.game.mapper.TBLogplatSysPlatProjectMapper;
import com.good.plat.datacenter.game.service.PlatProjectService;
import com.good.plat.datacenter.login.user.entity.FirstMenu;

@Service(value = "platProjectService")
public class PlatProjectServiceImpl implements PlatProjectService {
	
	public PlatProjectServiceImpl() {
	}
	
	@Autowired
	private TBLogplatSysPlatMapper tbPlatMapper;
	@Autowired
	private TBLogplatSysPlatProjectMapper tbPlatProjectMapper;
	
	@Autowired
	private PlatProjectMapper platProjectMapper;
	
	@Override
	public List<TBLogplatSysPlat> selectPlatList()  throws Exception{
		List<TBLogplatSysPlat> list = null;
		list = platProjectMapper.selectPlatList();
		return list;
	}
	@Override
	public List<Project> selectProjectByPlatID(Integer pid)  throws Exception{
		List<Project> list = null;
		list = platProjectMapper.selectProjectByPlatID(pid);
		return list;
	}
	
	@Override
	public BaseMessage addPlat(TBLogplatSysPlat plat)  throws Exception{
		BaseMessage bm = new BaseMessage();
		try{
			tbPlatMapper.insert(plat);
			bm.setStatus(1);
		}catch(Exception e){
			e.printStackTrace();
			bm.setStatus(0);
			bm.setMessage(e.getMessage());
			throw e;
		}
		return bm;
	}
	
	@Override
	public BaseMessage deletePlat(TBLogplatSysPlat plat) throws Exception {
		BaseMessage bm = new BaseMessage();
		try{
			tbPlatMapper.deleteByPrimaryKey(plat.getId());
			platProjectMapper.deleteByPlatId(plat.getId());
			bm.setStatus(1);
		}catch(Exception e){
			e.printStackTrace();
			bm.setStatus(0);
			bm.setMessage(e.getMessage());
			throw e;
		}
		return bm;
	}
	@Override
	public BaseMessage updatePlat(TBLogplatSysPlat plat) throws Exception{
		BaseMessage bm = new BaseMessage();
		try{
			tbPlatMapper.updateByPrimaryKeySelective(plat);
			bm.setStatus(1);
		}catch(Exception e){
			e.printStackTrace();
			bm.setStatus(0);
			bm.setMessage(e.getMessage());
			throw e;
		}
		return bm;
	}
	
	@Override
	public List<TBLogplatSysPlat> selectUserPlatProjectGameList(int userid) throws Exception {
		List<TBLogplatSysPlat> list = platProjectMapper.selectUserPlatProjectGameList(userid);
		return list;
	}
	
	@Override
	public List<FirstMenu> selectUserPlatProjectGameMenuList(BaseSearchData baseSearchData) throws Exception{
		baseSearchData.searchDataFilter(baseSearchData);
		List<FirstMenu> list = platProjectMapper.selectUserPlatProjectGameMenuList(baseSearchData);
		return list;
	}
	
	@Override
	public List<TBLogplatSysPlat> selectUserPlatList(Integer userid) throws Exception {
		List<TBLogplatSysPlat> list = platProjectMapper.selectUserPlatList(userid);
		return list;
	}
}
