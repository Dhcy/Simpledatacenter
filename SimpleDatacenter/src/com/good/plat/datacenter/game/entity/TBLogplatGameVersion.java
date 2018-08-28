package com.good.plat.datacenter.game.entity;

public class TBLogplatGameVersion {
    private Integer version_id;

    private Integer gameid;

    private String version_name;
    private String gameName;
    
    public Integer getVersion_id() {
        return version_id;
    }

    public void setVersion_id(Integer version_id) {
        this.version_id = version_id;
    }

    public Integer getGameid() {
        return gameid;
    }

    public void setGameid(Integer gameid) {
        this.gameid = gameid;
    }

    public String getVersion_name() {
        return version_name;
    }

    public void setVersion_name(String version_name) {
        this.version_name = version_name == null ? null : version_name.trim();
    }

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
    
}