package com.good.plat.datacenter.login.user.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

public class User implements Serializable {
	private static final long serialVersionUID = -1768963578513506319L;

	@Min(-1)
	@Max(Integer.MAX_VALUE)
	private Integer id;

	@NotEmpty(message="{name.isNull}")
	@Size(min=1, max=16, message="{name.lengthError}")
    private String realname;

	@NotEmpty(message="{name.isNull}")
	@Size(min=1, max=16, message="{name.lengthError}")
    private String username;

	@NotEmpty
	@Size(min=1, max=16)
    private String password;

    private String phone;

	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    private Date createdate;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    private Date logindate;

    //部门
    private String vocation;
    
    //旧密码
    @NotEmpty
	@Size(min=1, max=16)
    private String oldPassword;
    
    //确认密码
    @NotEmpty
	@Size(min=1, max=16)
    private String surePassword;
    
    //角色ID，用于查询
    private Integer roleId;
    
    //角色名称
    private String roleName;
    
    //
    private int[] roles;
    
    //角色
    private List<Role> roleList;
    
    //用户资源
    private List<UserResource> resourceList;
    
    public User() {
    	
    }
    
    public User(String username) {
    	super();
		this.username = username;
    }

    public User(Integer id, String realname, String username, String password,
			String phone, Date createdate, Date logindate, String vocation) {
		super();
		this.id = id;
		this.realname = realname;
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.createdate = createdate;
		this.logindate = logindate;
		this.vocation = vocation;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getLogindate() {
        return logindate;
    }

    public void setLogindate(Date logindate) {
        this.logindate = logindate;
    }

    public String getVocation() {
        return vocation;
    }

    public void setVocation(String vocation) {
        this.vocation = vocation;
    }

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public List<UserResource> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<UserResource> resourceList) {
		this.resourceList = resourceList;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getSurePassword() {
		return surePassword;
	}

	public void setSurePassword(String surePassword) {
		this.surePassword = surePassword;
	}
	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	public int[] getRoles() {
		return roles;
	}

	public void setRoles(int[] roles) {
		this.roles = roles;
	}
	
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", realname=" + realname + ", username="
				+ username + ", phone=" + phone + ", createdate=" + createdate
				+ ", logindate=" + logindate + ", vocation=" + vocation
				+ ", roleId=" + roleId + ", roleName=" + roleName + "]";
	}

}