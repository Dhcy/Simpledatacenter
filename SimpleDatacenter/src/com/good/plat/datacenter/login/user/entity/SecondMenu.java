package com.good.plat.datacenter.login.user.entity;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class SecondMenu implements Serializable{
  
	private static final long serialVersionUID = -6233392450740815599L;

	@Min(-1)
	@Max(Integer.MAX_VALUE)
	private Integer id;

	@NotNull
	@Max(Integer.MAX_VALUE)
    private Integer firstId;

	@NotEmpty(message="{key.isNull}")
	@Size(min=1, max=32, message="{key.lengthError}")
    private String key;

	@NotEmpty(message="{name.isNull}")
	@Size(min=1, max=32, message="{name.lengthError}")
    private String name;

    private String actkey;

	/*@NotEmpty(message="{name.isNull}")
	@Size(min=1, max=128, message="{name.lengthError}")*/
    private String url;

	/*@NotEmpty(message="{name.isNull}")
	@Size(min=1, max=128, message="{name.lengthError}")*/
    private String permission;
    
    private FirstMenu firstMenu;
    
    private Integer menuType;
    
    public SecondMenu() {}
    
    
    public SecondMenu(Integer id, Integer firstId, String key,
			String name, String actkey, String url, String permission) {
		super();
		this.id = id;
		this.firstId = firstId;
		this.key = key;
		this.name = name;
		this.actkey = actkey;
		this.url = url;
		this.permission = permission;
	}



	public SecondMenu(Integer firstId, String key, String name,
			String actkey, String url, String permission) {
		super();
		this.firstId = firstId;
		this.key = key;
		this.name = name;
		this.actkey = actkey;
		this.url = url;
		this.permission = permission;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFirstId() {
        return firstId;
    }

    public void setFirstId(Integer firstId) {
        this.firstId = firstId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getActkey() {
        return actkey;
    }

    public void setActkey(String actkey) {
        this.actkey = actkey == null ? null : actkey.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }

	public FirstMenu getFirstMenu() {
		return firstMenu;
	}

	public void setFirstMenu(FirstMenu firstMenu) {
		this.firstMenu = firstMenu;
	}


	public Integer getMenuType() {
		return menuType;
	}


	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}


	@Override
	public String toString() {
		return "SecondMenu [id=" + id + ", firstId=" + firstId + ", key=" + key
				+ ", name=" + name + ", actkey=" + actkey + ", url=" + url
				+ ", permission=" + permission + ", firstMenu=" + firstMenu
				+ ", menuType=" + menuType + "]";
	}


	
	
}