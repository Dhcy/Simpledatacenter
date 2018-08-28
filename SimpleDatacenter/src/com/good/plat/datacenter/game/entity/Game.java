package com.good.plat.datacenter.game.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Game implements Serializable{
	
	private static final long serialVersionUID = 7528317728843754005L;

	@NotNull(message="{id.isNull}")
	@Min(-1)
	@Max(Integer.MAX_VALUE)
	private Integer id;

	@NotNull(message="{id.isNull}")
	@Min(1)
	@Max(Integer.MAX_VALUE)
    private Integer projectid;

	@NotEmpty(message="{name.isNull}")
	@Size(min=1, max=16, message="{name.lengthError}")
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createdate;
    
    private Project project;
    
    private String projectName;
    
    private List<GameArea> areaList;

    private String billrate;
    
    @Min(0)
    @Max(Integer.MAX_VALUE)
    private Integer gameType;
    
    @Min(0)
    @Max(Integer.MAX_VALUE)
    private Integer region;
    
    @Min(0)
    @Max(Integer.MAX_VALUE)
    private Integer areatype;
    
    @Min(0)
    @Max(Integer.MAX_VALUE)
    private Integer hasad;
    
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date onlinedate;
    
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date  offlinedate;
    
   
    
    public Game() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getBillrate() {
        return billrate;
    }

    public void setBillrate(String billrate) {
        this.billrate = billrate;
    }

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<GameArea> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<GameArea> areaList) {
		this.areaList = areaList;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Integer getGameType() {
		return gameType;
	}

	public void setGameType(Integer gameType) {
		this.gameType = gameType;
	}
	

	public Integer getRegion() {
		return region;
	}

	public void setRegion(Integer region) {
		this.region = region;
	}

	public Integer getAreatype() {
		return areatype;
	}

	public void setAreatype(Integer areatype) {
		this.areatype = areatype;
	}

	public Integer getHasad() {
		return hasad;
	}

	public void setHasad(Integer hasad) {
		this.hasad = hasad;
	}

	public Date getOnlinedate() {
		return onlinedate;
	}

	public void setOnlinedate(Date onlinedate) {
		this.onlinedate = onlinedate;
	}

	public Date getOfflinedate() {
		return offlinedate;
	}

	public void setOfflinedate(Date offlinedate) {
		this.offlinedate = offlinedate;
	}

	@Override
	public String toString() {
		return "Game [areaList=" + areaList + ", areatype=" + areatype
				+ ", billrate=" + billrate + ", createdate=" + createdate
				+ ", gameType=" + gameType + ", hasad=" + hasad + ", id=" + id
				+ ", name=" + name + ", offlinedate=" + offlinedate
				+ ", onlinedate=" + onlinedate + ", project=" + project
				+ ", projectName=" + projectName + ", projectid=" + projectid
				+ ", region=" + region + "]";
	}

	

	
	
}