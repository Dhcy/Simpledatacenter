package com.good.plat.datacenter.login.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.login.user.entity.SecondMenu;
import com.good.plat.datacenter.login.user.mapper.SecondMenuMapper;
import com.good.plat.datacenter.login.user.service.SecondMenuService;

@Service(value="secondMenuService")
public class SecondMenuServiceImpl implements SecondMenuService {

	public SecondMenuServiceImpl() {
	}
	
	@Autowired
	private SecondMenuMapper secondMenuMapper;

	@Override
	public List<SecondMenu> findSecondMenuList(SecondMenu menu)
			throws Exception {
		
		return secondMenuMapper.selectSecondMenuList(menu);
	}

	@Override
	public SecondMenu findSecondMenuById(Integer id) throws Exception {
		return secondMenuMapper.selectByPrimaryKey(id);
	}

	@Override
	public SecondMenu findSecondMenuByMenu(SecondMenu menu) throws Exception {
		return secondMenuMapper.selectBySecondMenu(menu);
	}

	@Override
	public BaseMessage addSecondMenu(SecondMenu menu) throws Exception {
		BaseMessage mess = new BaseMessage();
		
		SecondMenu second = secondMenuMapper.selectBySecondMenu(menu);
		
		if (second == null) {
			int status = secondMenuMapper.insertSelective(menu);
			if (status == 1) {
				//添加成功的时候记得清空redis中数据
				mess.setStatus(status);
				mess.setMessage("添加成功");
			} else {
				mess.setStatus(0);
				mess.setMessage("添加失败");
			}
		} else {
			mess.setStatus(0);
			mess.setMessage("添加失败:已有相同名称的Menu");
		}
		return mess;
	}

	@Override
	public BaseMessage deleteSecondMenu(Integer id) throws Exception {
		BaseMessage mess = new BaseMessage();
		int status = secondMenuMapper.deleteByPrimaryKey(id);
		if (status == 1) {
			//删除成功的时候记得清空redis中数据
			mess.setStatus(status);
			mess.setMessage("删除成功");
		} else {
			mess.setStatus(0);
			mess.setMessage("删除失败");
		}
		return mess;
	}

	@Override
	public BaseMessage editSecondMenu(SecondMenu menu) throws Exception {
		BaseMessage mess = new BaseMessage();
		
		int status = secondMenuMapper.updateByPrimaryKeySelective(menu);
		if (status != 0) {
			//添加成功的时候记得清空redis中数据
			mess.setStatus(status);
			mess.setMessage("修改成功");
		} else {
			mess.setStatus(0);
			mess.setMessage("修改失败");
		}
		
		return mess;
	}

	@Override
	public List<SecondMenu> findSecondMenuListByUserID(Integer userid)
			throws Exception {
		
		return secondMenuMapper.selectSecondMenuListByUser(userid);
	}

	@Override
	public List<SecondMenu> findPermission(SecondMenu menu) throws Exception {
		
		return secondMenuMapper.selectPermission(menu);
	}

}
