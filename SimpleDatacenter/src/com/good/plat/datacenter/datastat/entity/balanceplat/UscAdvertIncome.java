package com.good.plat.datacenter.datastat.entity.balanceplat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;


import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 视频广告商收入
 * @ClassName: UscAdvertIncome
 * @Description: TODO
 * @author hwj
 * @date 2018-2-3 上午09:43:28
 */
public class UscAdvertIncome implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer gameid;
	private String gameName;
	@NotBlank(message="{name.isNull}")
	private String name;//广告商名称
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date statdate;

	private BigDecimal money;//收入
	private String date;
	private List<ChannelAccounting> videoChannelIncomes=new ArrayList<ChannelAccounting>();
	@NotBlank(message="{name.isNull}")
	private String signCompany;//签约公司
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createdate;//创建日期
	public Integer getGameid() {
		return gameid;
	}

	public void setGameid(Integer gameid) {
		this.gameid = gameid;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStatdate() {
		return statdate;
	}

	public void setStatdate(Date statdate) {
		this.statdate = statdate;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<ChannelAccounting> getVideoChannelIncomes() {
		return videoChannelIncomes;
	}

	public void setVideoChannelIncomes(List<ChannelAccounting> videoChannelIncomes) {
		this.videoChannelIncomes = videoChannelIncomes;
	}

	public String getSignCompany() {
		return signCompany;
	}

	public void setSignCompany(String signCompany) {
		this.signCompany = signCompany;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	
	
	
	

}
