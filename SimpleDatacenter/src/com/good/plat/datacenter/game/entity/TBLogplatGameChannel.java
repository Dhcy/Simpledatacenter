package com.good.plat.datacenter.game.entity;

public class TBLogplatGameChannel {
    private Integer id;

    private Integer gameid;

    private Integer channelid;

    private String servertype;

    private String joinserver;

    private String sdkname;

    private String sdkversion;

    private String sdkdownloadaddr;

    private String clientplattype;

    private String icon;

    private String launchvideo;

    private String packagename;

    private String clientpacktype;

    private String updateaddr;

    private String access_options;

    private String remark;

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

    public Integer getChannelid() {
        return channelid;
    }

    public void setChannelid(Integer channelid) {
        this.channelid = channelid;
    }

    public String getServertype() {
        return servertype;
    }

    public void setServertype(String servertype) {
        this.servertype = servertype == null ? null : servertype.trim();
    }

    public String getJoinserver() {
        return joinserver;
    }

    public void setJoinserver(String joinserver) {
        this.joinserver = joinserver == null ? null : joinserver.trim();
    }

    public String getSdkname() {
        return sdkname;
    }

    public void setSdkname(String sdkname) {
        this.sdkname = sdkname == null ? null : sdkname.trim();
    }

    public String getSdkversion() {
        return sdkversion;
    }

    public void setSdkversion(String sdkversion) {
        this.sdkversion = sdkversion == null ? null : sdkversion.trim();
    }

    public String getSdkdownloadaddr() {
        return sdkdownloadaddr;
    }

    public void setSdkdownloadaddr(String sdkdownloadaddr) {
        this.sdkdownloadaddr = sdkdownloadaddr == null ? null : sdkdownloadaddr.trim();
    }

    public String getClientplattype() {
        return clientplattype;
    }

    public void setClientplattype(String clientplattype) {
        this.clientplattype = clientplattype == null ? null : clientplattype.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getLaunchvideo() {
        return launchvideo;
    }

    public void setLaunchvideo(String launchvideo) {
        this.launchvideo = launchvideo == null ? null : launchvideo.trim();
    }

    public String getPackagename() {
        return packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename == null ? null : packagename.trim();
    }

    public String getClientpacktype() {
        return clientpacktype;
    }

    public void setClientpacktype(String clientpacktype) {
        this.clientpacktype = clientpacktype == null ? null : clientpacktype.trim();
    }

    public String getUpdateaddr() {
        return updateaddr;
    }

    public void setUpdateaddr(String updateaddr) {
        this.updateaddr = updateaddr == null ? null : updateaddr.trim();
    }

    public String getAccess_options() {
        return access_options;
    }

    public void setAccess_options(String access_options) {
        this.access_options = access_options == null ? null : access_options.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}