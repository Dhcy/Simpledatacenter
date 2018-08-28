package com.good.plat.datacenter.datastat.entity.analysis;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class LogplatWarningItem {
    private Integer id;

    private String name;

    private Date ctime;

    private Integer projectid;

    private Integer gameid;

    private Date launch_time;

    private Integer warning_event_id;

    private Integer cmptype;

    private String unit;

    private BigDecimal valve;

    private String subscriber;
    
    private String valve2;
    private Integer stat;
    
    //页面表单字段
    private List<String> emails;
    private Integer activationTime;
    private String warning_event;
    private Double value;
    //
    
    
    //getter & setter
    public Integer getId() {
        return id;
    }

    public LogplatWarningItem() {
		super();
		this.ctime = new Date();
	}

	public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public Integer getGameid() {
        return gameid;
    }

    public void setGameid(Integer gameid) {
        this.gameid = gameid;
    }

    public Date getLaunch_time() {
        return launch_time;
    }

    public void setLaunch_time(Date launch_time) {
        this.launch_time = launch_time;
    }

    public Integer getWarning_event_id() {
        return warning_event_id;
    }

    public void setWarning_event_id(Integer warning_event_id) {
        this.warning_event_id = warning_event_id;
    }

    public Integer getCmptype() {
        return cmptype;
    }

    public void setCmptype(Integer cmptype) {
        this.cmptype = cmptype;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public BigDecimal getValve() {
        return valve;
    }

    public void setValve(BigDecimal valve) {
        this.valve = valve;
    }

    public String getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(String subscriber) {
        this.subscriber = subscriber == null ? null : subscriber.trim();
    }

	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	public Integer getActivationTime() {
		return activationTime;
	}

	public void setActivationTime(Integer activationTime) {
		this.activationTime = activationTime;
	}

	public String getWarning_event() {
		return warning_event;
	}

	public void setWarning_event(String warning_event) {
		this.warning_event = warning_event;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getValve2() {
		return valve2;
	}

	public void setValve2(String valve2) {
		this.valve2 = valve2;
	}

	public Integer getStat() {
		return stat;
	}

	public void setStat(Integer stat) {
		this.stat = stat;
	}
	
}