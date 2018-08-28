package com.good.plat.datacenter.game.entity;

public class TBLogplatAttrValue {
    private Integer id;

    private Integer attr_id;

    private String attr_value;

    private String attr_desc;

    private String descr;
    private Integer gameid;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAttr_id() {
        return attr_id;
    }

    public void setAttr_id(Integer attr_id) {
        this.attr_id = attr_id;
    }

    public String getAttr_value() {
        return attr_value;
    }

    public void setAttr_value(String attr_value) {
        this.attr_value = attr_value == null ? null : attr_value.trim();
    }

    public String getAttr_desc() {
        return attr_desc;
    }

    public void setAttr_desc(String attr_desc) {
        this.attr_desc = attr_desc == null ? null : attr_desc.trim();
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr == null ? null : descr.trim();
    }

	public Integer getGameid() {
		return gameid;
	}

	public void setGameid(Integer gameid) {
		this.gameid = gameid;
	}
    
}