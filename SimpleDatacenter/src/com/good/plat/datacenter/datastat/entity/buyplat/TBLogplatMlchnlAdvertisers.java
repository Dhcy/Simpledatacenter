package com.good.plat.datacenter.datastat.entity.buyplat;

import java.util.Date;

public class TBLogplatMlchnlAdvertisers extends GameCommonEntity {
	  private Integer id;

	    private Integer gameid;

	    private Integer termType;

	    private Integer adchannel;

	    private String subchannel;

	    private String adSpaces;

	    private Byte adType;

	    private Double price;

	    private Byte pricingType;

	    private Date startdate;

	    private Date enddate;

	    private Integer adcost;

	    private Integer busicount;
	    private String priceTypeName;
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

	    public Integer getTermType() {
	        return termType;
	    }

	    public void setTermType(Integer termType) {
	        this.termType = termType;
	    }

	    public Integer getAdchannel() {
	        return adchannel;
	    }

	    public void setAdchannel(Integer adchannel) {
	        this.adchannel = adchannel;
	    }

	    public String getSubchannel() {
	        return subchannel;
	    }

	    public void setSubchannel(String subchannel) {
	        this.subchannel = subchannel == null ? null : subchannel.trim();
	    }

	    public String getAdSpaces() {
	        return adSpaces;
	    }

	    public void setAdSpaces(String adSpaces) {
	        this.adSpaces = adSpaces == null ? null : adSpaces.trim();
	    }

	  

	    public Double getPrice() {
	        return price;
	    }

	    public void setPrice(Double price) {
	        this.price = price;
	    }

	    

	    public Byte getAdType() {
			return adType;
		}

		public void setAdType(Byte adType) {
			this.adType = adType;
		}

		public Byte getPricingType() {
			return pricingType;
		}

		public void setPricingType(Byte pricingType) {
			this.pricingType = pricingType;
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

	    public Integer getAdcost() {
	        return adcost;
	    }

	    public void setAdcost(Integer adcost) {
	        this.adcost = adcost;
	    }

	    public Integer getBusicount() {
	        return busicount;
	    }

	    public void setBusicount(Integer busicount) {
	        this.busicount = busicount;
	    }

		public String getPriceTypeName() {
			return priceTypeName;
		}

		public void setPriceTypeName(String priceTypeName) {
			this.priceTypeName = priceTypeName;
		}
	    
}