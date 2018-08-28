package com.good.plat.datacenter.login.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.redis.service.impl.RedisServiceImpl;
import com.good.plat.datacenter.login.user.entity.FirstMenu;
import com.good.plat.datacenter.login.user.mapper.FirstMenuMapper;
import com.good.plat.datacenter.login.user.service.FirstMenuService;

@Service(value="firstMenuService")
public class FirstMenuServiceImpl implements FirstMenuService {

	public FirstMenuServiceImpl() {
	}
	
	@Autowired
	private FirstMenuMapper firstMenuMapper;
	
	@Autowired
	private RedisServiceImpl redisService;
	

	@Override
	public List<FirstMenu> findFirstMenuList() throws Exception {
		/*List<FirstMenu> firstMenuList = new ArrayList<FirstMenu>();
		
		if (redisService.getBytes("FIRST_MENU_LIST") != null) {
			firstMenuList = (List<FirstMenu>) redisService
					.byteArrayToObject(redisService.getBytes("FIRST_MENU_LIST"));
		} else {
			firstMenuList = firstMenuMapper.selectFirstMenuList();
			if (firstMenuList.size() > 0) {
				redisService.set("FIRST_MENU_LIST".getBytes(), 
						redisService.objectToByteArray(firstMenuList));
			}
		}*/
		
//		return firstMenuMapper.selectFirstMenuList();
		return firstMenuMapper.selectPlatFirstMenuList();
	}

	@Override
	public List<FirstMenu> findMenuListByUserId(Integer userId)
			throws Exception {
		//List<FirstMenu> menuList = new ArrayList<FirstMenu>();
		/**
		String key = "USERID_"+userId+"_MENU_LIST";
		
		if (redisService.getBytes(key) != null) {
			menuList = (List<FirstMenu>) redisService
					.byteArrayToObject(redisService.getBytes(key));
		} else {
			menuList = firstMenuMapper.selectMenuListByUserId(userId);
			if (menuList.size() > 0) {
				redisService.set(key.getBytes(), 
						redisService.objectToByteArray(menuList));
			}
		}
		*/
		
		return firstMenuMapper.selectMenuListByUserId(userId);
	}

	@Override
	public List<FirstMenu> findMenuListByRoles(List<Integer> roleIds)
			throws Exception {
		
		return firstMenuMapper.selectMenuListByRoles(roleIds);
	}

	@Override
	public FirstMenu findFirstMenuById(Integer id) throws Exception {
		return firstMenuMapper.selectByPrimaryKey(id);
	}

	@Override
	public FirstMenu findFirstMenuByName(String name) throws Exception {
		return firstMenuMapper.selectByMenuName(name);
	}

	@Override
	public BaseMessage addFirstMenu(FirstMenu menu) throws Exception {
		BaseMessage mess = new BaseMessage();
		
		FirstMenu me = firstMenuMapper.selectByMenuName(menu.getName());
		
		if (me == null) {
			int status = firstMenuMapper.insertSelective(menu);
			if (status != 0) {
				//添加成功的时候记得清空redis中数据
				//redisService.del("COMPANY_LIST","COMPANY_CHANNEL_LIST");
				mess.setStatus(1);
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
	public BaseMessage deleteFirstMenu(Integer id) throws Exception {
		BaseMessage mess = new BaseMessage();
		int status = firstMenuMapper.deleteByPrimaryKey(id);
		if (status != 0) {
			//删除成功的时候记得清空redis中数据
			//redisService.del("COMPANY_LIST","COMPANY_CHANNEL_LIST");
			mess.setStatus(1);
			mess.setMessage("删除成功");
		} else {
			mess.setStatus(0);
			mess.setMessage("删除失败");
		}
		return mess;
	}

	@Override
	public BaseMessage editFirstMenu(FirstMenu menu) throws Exception {
		BaseMessage mess = new BaseMessage();
		
		int status = firstMenuMapper.updateByPrimaryKeySelective(menu);
		if (status != 0) {
			mess.setStatus(1);
			mess.setMessage("修改成功");
		} else {
			mess.setStatus(0);
			mess.setMessage("修改失败");
		}
		
		return mess;
	}

	@Override
	public List<FirstMenu> firstUserMenuListByPlatId(BaseSearchData baseSearchData) throws Exception{
		baseSearchData.searchDataFilter(baseSearchData);
		return firstMenuMapper.firstUserMenuListByPlatId(baseSearchData);
	}

}
