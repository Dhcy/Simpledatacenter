package com.good.plat.datacenter.datastat.entity.balanceplat;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UscFinancialAppleReport {
    private Integer id;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date start;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date end;

    private String productid;

    private String appleid;

    private Integer quantity;

    private String title;

    private BigDecimal price;

    private String currency;

    private String country;

    private Integer fyear;

    private Integer fperiod;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid == null ? null : productid.trim();
    }

    public String getAppleid() {
        return appleid;
    }

    public void setAppleid(String appleid) {
        this.appleid = appleid == null ? null : appleid.trim();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public Integer getFyear() {
        return fyear;
    }

    public void setFyear(Integer fyear) {
        this.fyear = fyear;
    }

    public Integer getFperiod() {
        return fperiod;
    }

    public void setFperiod(Integer fperiod) {
        this.fperiod = fperiod;
    }
}