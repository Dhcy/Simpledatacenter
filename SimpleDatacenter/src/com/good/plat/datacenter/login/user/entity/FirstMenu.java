package com.good.plat.datacenter.login.user.entity;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class FirstMenu implements Serializable{
    
	private static final long serialVersionUID = -1369196189235357171L;

	@Min(-1)
	@Max(Integer.MAX_VALUE)
	private Integer id;

	@NotEmpty(message="{name.isNull}")
	@Size(min=1, max=16, message="{name.lengthError}")
    private String key;

	@NotEmpty(message="{name.isNull}")
	@Size(min=1, max=32, message="{name.lengthError}")
    private String name;
	
	private Integer platid;
	private String platname;
    
    private List<SecondMenu> menuList;
    
    public FirstMenu(){
    	
    }
    
    private Integer gameid;
    private Integer projectid;
    public FirstMenu(Integer id, String key, String name) {
		super();
		this.id = id;
		this.key = key;
		this.name = name;
	}


	public FirstMenu(String key, String name) {
		super();
		this.key = key;
		this.name = name;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SecondMenu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<SecondMenu> menuList) {
		this.menuList = menuList;
	}
	

	public Integer getPlatid() {
		return platid;
	}


	public void setPlatid(Integer platid) {
		this.platid = platid;
	}


	@Override
	public String toString() {
		return "FirstMenu [id=" + id + ", key=" + key + ", name=" + name + ", platid=" + platid + ", menuList="
				+ menuList + "]";
	}


	

	public String getPlatname() {
		return platname;
	}


	public void setPlatname(String platname) {
		this.platname = platname;
	}


	public Integer getGameid() {
		return gameid;
	}


	public void setGameid(Integer gameid) {
		this.gameid = gameid;
	}


	public Integer getProjectid() {
		return projectid;
	}


	public void setProjectid(Integer projectid) {
		this.projectid = projectid;
	}
	

    
}