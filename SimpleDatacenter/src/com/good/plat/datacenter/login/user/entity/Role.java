package com.good.plat.datacenter.login.user.entity;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class Role implements Serializable{
	private static final long serialVersionUID = -9136811377792701220L;

	@Min(-1)
	@Max(Integer.MAX_VALUE)
    private Integer id;

	@NotEmpty(message="{name.isNull}")
	@Size(min=1, max=16, message="{name.lengthError}")
    private String rolename;

    private Byte isvalid;
    
    private List<SecondMenu> menuList;
    
    public Role() {}
    
    public Role(Integer id, String rolename, Byte isvalid) {
		super();
		this.id = id;
		this.rolename = rolename;
		this.isvalid = isvalid;
	}

    public Role(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    public Byte getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Byte isvalid) {
        this.isvalid = isvalid;
    }

	public List<SecondMenu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<SecondMenu> menuList) {
		this.menuList = menuList;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", rolename=" + rolename + ", isvalid="
				+ isvalid + ", menuList=" + menuList + "]";
	}
    
}