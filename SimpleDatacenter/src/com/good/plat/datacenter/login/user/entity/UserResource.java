package com.good.plat.datacenter.login.user.entity;

import java.io.Serializable;
import java.util.List;

public class UserResource implements Serializable {

	private static final long serialVersionUID = -4866294603862290751L;

	//主键id
    private Integer id;
    //用户ID
    private Integer userid;
    //资源类型
    private String restype;
    //资源关联标识
    private String relationid;
    //所属用户
    private User user;
    //项目
    private List<Integer> projectList;
    
    //游戏
    private List<Integer> gameList;
    
    //渠道
    private List<Integer> channelList;
    //平台
    private List<Integer> platformList;
    //买了平台-广告渠道资源
    private List<Integer> adchannelList;
	public UserResource() {
	}

	public UserResource(Integer id, Integer userid, String restype,
			String relationid) {
		super();
		this.id = id;
		this.userid = userid;
		this.restype = restype;
		this.relationid = relationid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getRestype() {
		return restype;
	}

	public void setRestype(String restype) {
		this.restype = restype;
	}

	public String getRelationid() {
		return relationid;
	}

	public void setRelationid(String relationid) {
		this.relationid = relationid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public List<Integer> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<Integer> projectList) {
		this.projectList = projectList;
	}

	public List<Integer> getGameList() {
		return gameList;
	}

	public void setGameList(List<Integer> gameList) {
		this.gameList = gameList;
	}

	public List<Integer> getChannelList() {
		return channelList;
	}

	public void setChannelList(List<Integer> channelList) {
		this.channelList = channelList;
	}

	@Override
	public String toString() {
		return "UserResource [id=" + id + ", userid=" + userid + ", restype="
				+ restype + ", relationid=" + relationid + "]";
	}

	public List<Integer> getPlatformList() {
		return platformList;
	}

	public void setPlatformList(List<Integer> platformList) {
		this.platformList = platformList;
	}

	public List<Integer> getAdchannelList() {
		return adchannelList;
	}

	public void setAdchannelList(List<Integer> adchannelList) {
		this.adchannelList = adchannelList;
	}
	
}
