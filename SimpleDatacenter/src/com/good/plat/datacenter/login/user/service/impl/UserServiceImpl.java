package com.good.plat.datacenter.login.user.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseMessage;
import com.good.plat.datacenter.common.redis.service.impl.RedisServiceImpl;
import com.good.plat.datacenter.common.util.MD5;
import com.good.plat.datacenter.datastat.entity.buyplat.TBLogplatMlchnlAdchannel;
import com.good.plat.datacenter.game.entity.Channel;
import com.good.plat.datacenter.game.entity.Game;
import com.good.plat.datacenter.game.entity.Project;
import com.good.plat.datacenter.game.entity.TBLogplatSysPlat;
import com.good.plat.datacenter.login.service.impl.LoginServiceImpl;
import com.good.plat.datacenter.login.user.entity.User;
import com.good.plat.datacenter.login.user.entity.UserResource;
import com.good.plat.datacenter.login.user.mapper.UserMapper;
import com.good.plat.datacenter.login.user.service.ResourceCommon;
import com.good.plat.datacenter.login.user.service.UserService;

@Service(value="userService")
public class UserServiceImpl implements UserService {

	public UserServiceImpl() {
	}
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private RedisServiceImpl redisService;

	@Autowired
	private LoginServiceImpl loginService;
	
	@Override
	public List<User> userList(User user) throws Exception {
		
		return userMapper.selectUserListByUser(user);
	}

	@Override
	public User findUser(User user) throws Exception {
		return userMapper.selectUserByUser(user);
	}

	@Override
	public BaseMessage addUser(User user) throws Exception {
		BaseMessage mess = new BaseMessage();
		User user2 = userMapper.selectUserByUser(user);
		if (user2 != null) {
			mess.setStatus(0);
			mess.setMessage("添加失败:已有相同名称的用户");
			return mess;
		}
		
		String password = MD5.toMD5(user.getPassword());
		user.setPassword(password);
		
		int status1 = userMapper.insertSelective(user);
		user.setId(user.getId());
		
		int status2 = userMapper.insertUserRole(user);
		
		if (status1 != 0 && status2 != 0) {
			mess.setStatus(1);
			mess.setMessage("添加成功");
		} else {
			mess.setStatus(0);
			mess.setMessage("添加失败");
		}
		
		return mess;
	}

	@Override
	public BaseMessage deleteUser(User user) throws Exception {
		BaseMessage mess = new BaseMessage();
		int status = userMapper.deleteByPrimaryKey(user.getId());
		if (status == 1) {
			mess.setStatus(status);
			mess.setMessage("删除成功");
		} else {
			mess.setStatus(0);
			mess.setMessage("删除失败");
		}
		return mess;
	}

	@Override
	public User editUserUI(User user) throws Exception {
		return userMapper.updateUserUI(user);
	}

	@Override
	public BaseMessage editUser(User user) throws Exception {
		if (!user.getPassword().equals("null")) {
			String password = MD5.toMD5(user.getPassword());
			user.setPassword(password);
		} else {
			user.setPassword(null);
		}
		
		int status1 = userMapper.updateByPrimaryKeySelective(user);
		userMapper.deleteUserRole(user);
		int status3 = userMapper.insertUserRole(user);
		
		BaseMessage mess = new BaseMessage();
		if (status1 != 0 && status3 != 0) {
			mess.setStatus(1);
			mess.setMessage("更新成功");
		} else {
			mess.setStatus(0);
			mess.setMessage("更新失败");
		}
		
		return mess;
	}
	
	@Override
	public BaseMessage updataUser(User user) throws Exception {
		int status = userMapper.updateByPrimaryKeySelective(user);
		BaseMessage mess = new BaseMessage();
		if (status != 0) {
			mess.setStatus(1);
			mess.setMessage("更新成功");
		} else {
			mess.setStatus(0);
			mess.setMessage("更新失败");
		}
		return mess;
	}
	

	@Override
	public UserResource editUserResourceUI(User user, HttpSession session,HttpServletResponse response)
			throws Exception {
		UserResource userResource = new UserResource();
		List<Integer> projectList = new ArrayList<Integer>();
		List<Integer> gameList = new ArrayList<Integer>();
		List<Integer> channelList = new ArrayList<Integer>();
		List<Integer> platformList = new ArrayList<Integer>();
		List<Integer> adchannelList = new ArrayList<Integer>();
		if (user.getUsername().equals("GMAdmin")) {//超级用户获取全部权限，从缓存中获取
			List<Project> proList = loginService.getUserProjectList(session, response);
			List<Game> gaList = loginService.getUserGameList(session, response);
			List<Channel> chaList = loginService.getUserChannelList(session, response);
			List<TBLogplatSysPlat> platList = loginService.getUserPlatformList(session, response);
			List<TBLogplatMlchnlAdchannel> mlAdchannelList = loginService.getUserAdvertiseChannelList(session, response);
			if(proList != null){
				for (Project project : proList) {
					projectList.add(project.getId());
				}
			}
			
			if(gaList != null){
				for (Game game : gaList) {
					gameList.add(game.getId());
				}
			}
			
			if(chaList != null){
				for (Channel cha : chaList) {
					channelList.add(cha.getId());
				}
			}
			
			if(platList != null){
				for (TBLogplatSysPlat plat : platList) {
					platformList.add(plat.getId());
				}
			}
			
			if(mlAdchannelList != null){
				for(TBLogplatMlchnlAdchannel adchannel : mlAdchannelList){
					adchannelList.add(adchannel.getId());
				}
			}
			
		} else {
			List<UserResource> resources = userMapper.updateUserResourceUI(user);
			if (resources != null) {
				for (UserResource ur : resources) {
					if (ur.getRestype().equals(ResourceCommon.RESOURCE_TYPE_OF_PROJECT)) {
						projectList.add(Integer.parseInt(ur.getRelationid()));
					} else if (ur.getRestype().equals(ResourceCommon.RESOURCE_TYPE_OF_GAME)) {
						gameList.add(Integer.parseInt(ur.getRelationid()));
					} else if (ur.getRestype().equals(ResourceCommon.RESOURCE_TYPE_OF_CHANNEL)) {
						channelList.add(Integer.parseInt(ur.getRelationid()));
					}else if (ur.getRestype().equals(ResourceCommon.RESOURCE_TYPE_OF_PLATFORM)) {
						platformList.add(Integer.parseInt(ur.getRelationid()));
					}else if (ur.getRestype().equals(ResourceCommon.RESOURCE_TYPE_OF_ADVERTISE_CHANNEL)) {
						adchannelList.add(Integer.parseInt(ur.getRelationid()));
					}
				}
			}
		}
		
		userResource.setProjectList(projectList);
		userResource.setGameList(gameList);
		userResource.setChannelList(channelList);
		userResource.setPlatformList(platformList);
		userResource.setAdchannelList(adchannelList);
		return userResource;
	}

	@Override
	public BaseMessage editUserResource(UserResource userResource)
			throws Exception {
		userMapper.deleteUserResource(userResource);
		
		User user = new User();
		int userid = userResource.getUserid();
		
		List<UserResource> urList = new ArrayList<UserResource>();
		if (userResource.getProjectList().size()>0) {
			for (Integer pro : userResource.getProjectList()) {
				UserResource ur = new UserResource();
				ur.setUserid(userid);
				ur.setRestype("project");
				ur.setRelationid(pro.toString());
				urList.add(ur);
			}
		}
		if (userResource.getGameList().size()>0) {
			for (Integer game : userResource.getGameList()) {
				UserResource ur = new UserResource();
				ur.setUserid(userid);
				ur.setRestype("game");
				ur.setRelationid(game.toString());
				urList.add(ur);
			}
		}
		if (userResource.getChannelList().size()>0) {
			for (Integer cha : userResource.getChannelList()) {
				UserResource ur = new UserResource();
				ur.setUserid(userid);
				ur.setRestype("channel");
				ur.setRelationid(cha.toString());
				urList.add(ur);
			}
		}
		if (userResource.getPlatformList().size()>0) {
			for (Integer plat : userResource.getPlatformList()) {
				UserResource ur = new UserResource();
				ur.setUserid(userid);
				ur.setRestype("platform");
				ur.setRelationid(plat.toString());
				urList.add(ur);
			}
		}
		if (userResource.getAdchannelList().size() >0) {
			for (Integer adchannel : userResource.getAdchannelList()) {
				UserResource ur = new UserResource();
				ur.setUserid(userid);
				ur.setRestype("adchannel");
				ur.setRelationid(adchannel.toString());
				urList.add(ur);
			}
		}
		user.setResourceList(urList);
		
		int status2 = userMapper.updateUserResource(user);
		
		BaseMessage mess = new BaseMessage();
		if (status2 != 0) {
			mess.setStatus(1);
			mess.setMessage("更新成功");
		} else {
			mess.setStatus(0);
			mess.setMessage("更新失败");
		}
		
		return mess;
	}

	@Override
	public BaseMessage editPassword(User user) throws Exception {
		BaseMessage mess = new BaseMessage();
		User user2 = userMapper.selectByPrimaryKey(user.getId());
		if (!MD5.toMD5(user.getOldPassword()).equals(user2.getPassword())) {
			mess.setStatus(0);
			mess.setMessage("密码修改失败:旧密码不正确!");
			return mess;
		} else {
			if (!user.getPassword().equals(user.getSurePassword())) {
				mess.setStatus(0);
				mess.setMessage("密码修改失败:请确认新密码输入是否一致");
				return mess;
			} else {
				user.setPassword(MD5.toMD5(user.getPassword()));
				int status = userMapper.updateByPrimaryKeySelective(user);
				if (status != 0) {
					mess.setStatus(1);
					mess.setMessage("修改成功");
				} else {
					mess.setStatus(0);
					mess.setMessage("修改失败");
				}
				return mess;
			}
		}
		
	}
	
}
