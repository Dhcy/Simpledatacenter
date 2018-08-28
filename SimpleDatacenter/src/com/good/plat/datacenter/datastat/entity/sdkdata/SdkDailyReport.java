package com.good.plat.datacenter.datastat.entity.sdkdata;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SdkDailyReport {
    private Integer id;

    private Integer gameid;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date statdate;

    private Integer os;

    private Integer sourceid;

    private String source;

    private Integer activate;

    private Integer all_regist;

    private Integer game_regist;

    private Integer login;

    private Integer all_rechg;

    private Integer new_actor;

    private Integer new_rechg;

    private BigDecimal all_money;

    private BigDecimal new_money;

    private Integer rechg_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

   public Integer getGameid() {
        return gameid;
    }

    public void setGameid(Integer gameid) {
        this.gameid = gameid;
    }

    public Date getStatdate() {
        return statdate;
    }

    public void setStatdate(Date statdate) {
        this.statdate = statdate;
    }

    public Integer getOs() {
        return os;
    }

    public void setOs(Integer os) {
        this.os = os;
    }

    public Integer getSourceid() {
        return sourceid;
    }

    public void setSourceid(Integer sourceid) {
        this.sourceid = sourceid;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public Integer getActivate() {
        return activate;
    }

    public void setActivate(Integer activate) {
        this.activate = activate;
    }

    public Integer getAll_regist() {
        return all_regist;
    }

    public void setAll_regist(Integer all_regist) {
        this.all_regist = all_regist;
    }

    public Integer getGame_regist() {
        return game_regist;
    }

    public void setGame_regist(Integer game_regist) {
        this.game_regist = game_regist;
    }

    public Integer getLogin() {
        return login;
    }

    public void setLogin(Integer login) {
        this.login = login;
    }

    public Integer getAll_rechg() {
        return all_rechg;
    }

    public void setAll_rechg(Integer all_rechg) {
        this.all_rechg = all_rechg;
    }

    public Integer getNew_actor() {
        return new_actor;
    }

    public void setNew_actor(Integer new_actor) {
        this.new_actor = new_actor;
    }

    public Integer getNew_rechg() {
        return new_rechg;
    }

    public void setNew_rechg(Integer new_rechg) {
        this.new_rechg = new_rechg;
    }

    public BigDecimal getAll_money() {
        return all_money;
    }

    public void setAll_money(BigDecimal all_money) {
        this.all_money = all_money;
    }

    public BigDecimal getNew_money() {
        return new_money;
    }

    public void setNew_money(BigDecimal new_money) {
        this.new_money = new_money;
    }

    public Integer getRechg_time() {
        return rechg_time;
    }

    public void setRechg_time(Integer rechg_time) {
        this.rechg_time = rechg_time;
    }
}