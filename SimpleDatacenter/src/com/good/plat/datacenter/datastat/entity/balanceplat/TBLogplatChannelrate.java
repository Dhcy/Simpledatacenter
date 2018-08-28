package com.good.plat.datacenter.datastat.entity.balanceplat;

import java.util.Date;

public class TBLogplatChannelrate {
    private Integer id;

    private Integer gameid;

    private String company;

    private Integer companyid;

    private String cooperationType;

    private Double payRate;

    private Byte isPayRate;

    private Double taxRate;

    private Double divideRate;

    private Byte isLadder;

    private String payType;

    private Date startdate;

    private Date enddate;

    private Integer validTime;

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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public String getCooperationType() {
        return cooperationType;
    }

    public void setCooperationType(String cooperationType) {
        this.cooperationType = cooperationType == null ? null : cooperationType.trim();
    }

    public Double getPayRate() {
        return payRate;
    }

    public void setPayRate(Double payRate) {
        this.payRate = payRate;
    }

    public Byte getIsPayRate() {
        return isPayRate;
    }

    public void setIsPayRate(Byte isPayRate) {
        this.isPayRate = isPayRate;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public Double getDivideRate() {
        return divideRate;
    }

    public void setDivideRate(Double divideRate) {
        this.divideRate = divideRate;
    }

    public Byte getIsLadder() {
        return isLadder;
    }

    public void setIsLadder(Byte isLadder) {
        this.isLadder = isLadder;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Integer getValidTime() {
        return validTime;
    }

    public void setValidTime(Integer validTime) {
        this.validTime = validTime;
    }
}