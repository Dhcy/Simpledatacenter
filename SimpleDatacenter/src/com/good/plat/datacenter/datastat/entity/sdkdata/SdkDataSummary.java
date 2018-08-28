package com.good.plat.datacenter.datastat.entity.sdkdata;

import java.math.BigDecimal;
import java.util.List;

public class SdkDataSummary extends SdkDailyReport {
	//
	private String gameName;
	//
	private Integer total_recharge;

	private Integer lost;

	private Integer lost_return;

	///////////
	private Integer page_id;

	private String page_name;

	private Integer dl_count;

	private Integer dl_user_count;
	///////////
	/*
	 * private Integer dl_count;
	 * 
	 * private Integer dl_user_count;
	 */

	private Integer cross_login;

	private Integer new_actor;

	private BigDecimal new_money;

	private Integer new_recharge;

	private Integer day1;

	private Integer day3;

	private Integer day7;

	private Integer day30;
	///////////
	private String way;

	private Integer regsit;

	/*private Integer new_recharge;

	private BigDecimal new_money;*/

	private Integer total_login;

	private BigDecimal total_money;

	private Integer total_lost;

	private Integer total_return;
	///////////
	/*private Integer total_recharge;

    private Integer new_actor;

    private Integer lost;

    private Integer lost_return;

    private Integer day1;

    private Integer day3;

    private Integer day7;

    private Integer day30;*/
	///////////
	private String category_id;

    private String category_name;

   /* private Integer page_id;

    private String page_name;*/

    private Integer pv;

    private Integer uv;
	///////////
    private Integer vip_level;

    private Integer total_vip;

    private BigDecimal total_vip_money;

    private Integer new_vip;

    private BigDecimal new_vip_money;

    private Integer vip_recharge;

    private BigDecimal vip_money;

    private Integer vip_lost;

    private Integer vip_return;

	///////////
    
    private Double arrpu;
    private Double arpu;
    private Double payrate;
   
    private Double active_regist_rate;
    private Double regist_actor_rate;
    
    private Double day1rate;
    private Double day3rate;
    private Double day7rate;
    private Double day30rate;
    
    private Double pv_uv_rate;
    private Double cross_login_user_count_rate;
    private Double new_actor_new_regist_rate;
    private List<PageInfo> srcPages;
    private Double vip_avg_money;
    
    ///
    private Double vip_total_money_rate;
    private Double vip_total_avg_money;
    private Double vip_money_rate;
    /////////////////////
    ///
    private Integer lvl0;
    private Integer lvl1;
    private Integer lvl2;
    private Integer lvl3;
    private Integer lvl4;
    private Integer lvl5;
    private Integer lvl6;
    private Integer lvl7;
    private Integer lvl8;
    private Integer lvl9;
    private Integer lvl10;
    private Integer lvl11;
    private Integer lvl12;
    ///
    //浏览占比
    private Double pv_rate;
    //详情占比
    private Double uv_rate;
    //无账号下载次数
    private Integer dl_count2;
    
    public static class PageInfo{
    	private Integer page_id;
    	private String page_name;
		public Integer getPage_id() {
			return page_id;
		}
		public void setPage_id(Integer page_id) {
			this.page_id = page_id;
		}
		public String getPage_name() {
			return page_name;
		}
		public void setPage_name(String page_name) {
			this.page_name = page_name;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((page_id == null) ? 0 : page_id.hashCode());
			result = prime * result + ((page_name == null) ? 0 : page_name.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			PageInfo other = (PageInfo) obj;
			if (page_id == null) {
				if (other.page_id != null)
					return false;
			} else if (!page_id.equals(other.page_id))
				return false;
			if (page_name == null) {
				if (other.page_name != null)
					return false;
			} else if (!page_name.equals(other.page_name))
				return false;
			return true;
		}
    	
    }
    
	public Integer getTotal_recharge() {
		return total_recharge;
	}

	public void setTotal_recharge(Integer total_recharge) {
		this.total_recharge = total_recharge;
	}

	public Integer getLost() {
		return lost;
	}

	public void setLost(Integer lost) {
		this.lost = lost;
	}

	public Integer getLost_return() {
		return lost_return;
	}

	public void setLost_return(Integer lost_return) {
		this.lost_return = lost_return;
	}

	public Integer getPage_id() {
		return page_id;
	}

	public void setPage_id(Integer page_id) {
		this.page_id = page_id;
	}

	public String getPage_name() {
		return page_name;
	}

	public void setPage_name(String page_name) {
		this.page_name = page_name;
	}

	public Integer getDl_count() {
		return dl_count;
	}

	public void setDl_count(Integer dl_count) {
		this.dl_count = dl_count;
	}

	public Integer getDl_user_count() {
		return dl_user_count;
	}

	public void setDl_user_count(Integer dl_user_count) {
		this.dl_user_count = dl_user_count;
	}

	public Integer getCross_login() {
		return cross_login;
	}

	public void setCross_login(Integer cross_login) {
		this.cross_login = cross_login;
	}

	public Integer getNew_actor() {
		return new_actor;
	}

	public void setNew_actor(Integer new_actor) {
		this.new_actor = new_actor;
	}

	public BigDecimal getNew_money() {
		return new_money;
	}

	public void setNew_money(BigDecimal new_money) {
		this.new_money = new_money;
	}

	public Integer getNew_recharge() {
		return new_recharge;
	}

	public void setNew_recharge(Integer new_recharge) {
		this.new_recharge = new_recharge;
	}

	public Integer getDay1() {
		return day1;
	}

	public void setDay1(Integer day1) {
		this.day1 = day1;
	}

	public Integer getDay3() {
		return day3;
	}

	public void setDay3(Integer day3) {
		this.day3 = day3;
	}

	public Integer getDay7() {
		return day7;
	}

	public void setDay7(Integer day7) {
		this.day7 = day7;
	}

	public Integer getDay30() {
		return day30;
	}

	public void setDay30(Integer day30) {
		this.day30 = day30;
	}

	public String getWay() {
		return way;
	}

	public void setWay(String way) {
		this.way = way;
	}

	public Integer getRegsit() {
		return regsit;
	}

	public void setRegsit(Integer regsit) {
		this.regsit = regsit;
	}

	public Integer getTotal_login() {
		return total_login;
	}

	public void setTotal_login(Integer total_login) {
		this.total_login = total_login;
	}

	public BigDecimal getTotal_money() {
		return total_money;
	}

	public void setTotal_money(BigDecimal total_money) {
		this.total_money = total_money;
	}

	public Integer getTotal_lost() {
		return total_lost;
	}

	public void setTotal_lost(Integer total_lost) {
		this.total_lost = total_lost;
	}

	public Integer getTotal_return() {
		return total_return;
	}

	public void setTotal_return(Integer total_return) {
		this.total_return = total_return;
	}


	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String categoryId) {
		category_id = categoryId;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public Integer getPv() {
		return pv;
	}

	public void setPv(Integer pv) {
		this.pv = pv;
	}

	public Integer getUv() {
		return uv;
	}

	public void setUv(Integer uv) {
		this.uv = uv;
	}

	public Integer getVip_level() {
		return vip_level;
	}

	public void setVip_level(Integer vip_level) {
		this.vip_level = vip_level;
	}

	public Integer getTotal_vip() {
		return total_vip;
	}

	public void setTotal_vip(Integer total_vip) {
		this.total_vip = total_vip;
	}

	public BigDecimal getTotal_vip_money() {
		return total_vip_money;
	}

	public void setTotal_vip_money(BigDecimal total_vip_money) {
		this.total_vip_money = total_vip_money;
	}

	public Integer getNew_vip() {
		return new_vip;
	}

	public void setNew_vip(Integer new_vip) {
		this.new_vip = new_vip;
	}

	public BigDecimal getNew_vip_money() {
		return new_vip_money;
	}

	public void setNew_vip_money(BigDecimal new_vip_money) {
		this.new_vip_money = new_vip_money;
	}

	public Integer getVip_recharge() {
		return vip_recharge;
	}

	public void setVip_recharge(Integer vip_recharge) {
		this.vip_recharge = vip_recharge;
	}

	public BigDecimal getVip_money() {
		return vip_money;
	}

	public void setVip_money(BigDecimal vip_money) {
		this.vip_money = vip_money;
	}

	public Integer getVip_lost() {
		return vip_lost;
	}

	public void setVip_lost(Integer vip_lost) {
		this.vip_lost = vip_lost;
	}

	public Integer getVip_return() {
		return vip_return;
	}

	public void setVip_return(Integer vip_return) {
		this.vip_return = vip_return;
	}

	public Double getArrpu() {
		return arrpu;
	}

	public void setArrpu(Double arrpu) {
		this.arrpu = arrpu;
	}

	public Double getArpu() {
		return arpu;
	}

	public void setArpu(Double arpu) {
		this.arpu = arpu;
	}

	public Double getPayrate() {
		return payrate;
	}

	public void setPayrate(Double payrate) {
		this.payrate = payrate;
	}

	public Double getActive_regist_rate() {
		return active_regist_rate;
	}

	public void setActive_regist_rate(Double active_regist_rate) {
		this.active_regist_rate = active_regist_rate;
	}

	public Double getRegist_actor_rate() {
		return regist_actor_rate;
	}

	public void setRegist_actor_rate(Double regist_actor_rate) {
		this.regist_actor_rate = regist_actor_rate;
	}

	public Double getDay1rate() {
		return day1rate;
	}

	public void setDay1rate(Double day1rate) {
		this.day1rate = day1rate;
	}

	public Double getDay3rate() {
		return day3rate;
	}

	public void setDay3rate(Double day3rate) {
		this.day3rate = day3rate;
	}

	public Double getDay7rate() {
		return day7rate;
	}

	public void setDay7rate(Double day7rate) {
		this.day7rate = day7rate;
	}

	public Double getDay30rate() {
		return day30rate;
	}

	public void setDay30rate(Double day30rate) {
		this.day30rate = day30rate;
	}

	public Double getPv_uv_rate() {
		return pv_uv_rate;
	}

	public void setPv_uv_rate(Double pv_uv_rate) {
		this.pv_uv_rate = pv_uv_rate;
	}

	public Double getCross_login_user_count_rate() {
		return cross_login_user_count_rate;
	}

	public void setCross_login_user_count_rate(Double cross_login_user_count_rate) {
		this.cross_login_user_count_rate = cross_login_user_count_rate;
	}

	public Double getNew_actor_new_regist_rate() {
		return new_actor_new_regist_rate;
	}

	public void setNew_actor_new_regist_rate(Double new_actor_new_regist_rate) {
		this.new_actor_new_regist_rate = new_actor_new_regist_rate;
	}

	public List<PageInfo> getSrcPages() {
		return srcPages;
	}

	public void setSrcPages(List<PageInfo> srcPages) {
		this.srcPages = srcPages;
	}

	public Double getVip_avg_money() {
		return vip_avg_money;
	}

	public void setVip_avg_money(Double vip_avg_money) {
		this.vip_avg_money = vip_avg_money;
	}

	public Integer getLvl0() {
		return lvl0;
	}

	public void setLvl0(Integer lvl0) {
		this.lvl0 = lvl0;
	}

	public Integer getLvl1() {
		return lvl1;
	}

	public void setLvl1(Integer lvl1) {
		this.lvl1 = lvl1;
	}

	public Integer getLvl2() {
		return lvl2;
	}

	public void setLvl2(Integer lvl2) {
		this.lvl2 = lvl2;
	}

	public Integer getLvl3() {
		return lvl3;
	}

	public void setLvl3(Integer lvl3) {
		this.lvl3 = lvl3;
	}

	public Integer getLvl4() {
		return lvl4;
	}

	public void setLvl4(Integer lvl4) {
		this.lvl4 = lvl4;
	}

	public Integer getLvl5() {
		return lvl5;
	}

	public void setLvl5(Integer lvl5) {
		this.lvl5 = lvl5;
	}

	public Integer getLvl6() {
		return lvl6;
	}

	public void setLvl6(Integer lvl6) {
		this.lvl6 = lvl6;
	}

	public Integer getLvl7() {
		return lvl7;
	}

	public void setLvl7(Integer lvl7) {
		this.lvl7 = lvl7;
	}

	public Integer getLvl8() {
		return lvl8;
	}

	public void setLvl8(Integer lvl8) {
		this.lvl8 = lvl8;
	}

	public Integer getLvl9() {
		return lvl9;
	}

	public void setLvl9(Integer lvl9) {
		this.lvl9 = lvl9;
	}

	public Integer getLvl10() {
		return lvl10;
	}

	public void setLvl10(Integer lvl10) {
		this.lvl10 = lvl10;
	}

	public Integer getLvl11() {
		return lvl11;
	}

	public void setLvl11(Integer lvl11) {
		this.lvl11 = lvl11;
	}

	public Integer getLvl12() {
		return lvl12;
	}

	public void setLvl12(Integer lvl12) {
		this.lvl12 = lvl12;
	}

	public Double getVip_total_money_rate() {
		return vip_total_money_rate;
	}

	public void setVip_total_money_rate(Double vip_total_money_rate) {
		this.vip_total_money_rate = vip_total_money_rate;
	}

	public Double getVip_total_avg_money() {
		return vip_total_avg_money;
	}

	public void setVip_total_avg_money(Double vip_total_avg_money) {
		this.vip_total_avg_money = vip_total_avg_money;
	}

	public Double getVip_money_rate() {
		return vip_money_rate;
	}

	public void setVip_money_rate(Double vip_money_rate) {
		this.vip_money_rate = vip_money_rate;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public Double getPv_rate() {
		return pv_rate;
	}

	public void setPv_rate(Double pvRate) {
		pv_rate = pvRate;
	}

	public Double getUv_rate() {
		return uv_rate;
	}

	public void setUv_rate(Double uvRate) {
		uv_rate = uvRate;
	}

	public Integer getDl_count2() {
		return dl_count2;
	}

	public void setDl_count2(Integer dlCount2) {
		dl_count2 = dlCount2;
	}
	

	
	
}

