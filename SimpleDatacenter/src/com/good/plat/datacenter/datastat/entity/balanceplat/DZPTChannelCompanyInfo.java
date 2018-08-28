package com.good.plat.datacenter.datastat.entity.balanceplat;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;
/**
 * 对账平台——渠道公司信息
 * @ClassName: DZPTChannelInfo
 * @Description: TODO
 * @author hwj
 * @date 2017-5-3 下午05:29:02
 */
public class DZPTChannelCompanyInfo extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;//id
	private Integer channelId;//渠道id
	private String channelName;//渠道名称
	private String taxId;//纳税人识别号
	private String address;//注册地址
	private String phone;//联系电话
	private String bank;//开户行
	private String bankAcount;//银行账号
	//渠道列表
	private String channelSimName;
	private String taxType;//纳税资质
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getTaxId() {
		return taxId;
	}
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBankAcount() {
		return bankAcount;
	}
	public void setBankAcount(String bankAcount) {
		this.bankAcount = bankAcount;
	}
	
	public Integer getChannelId() {
		return channelId;
	}
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	
	public String getChannelSimName() {
		return channelSimName;
	}
	public void setChannelSimName(String channelSimName) {
		this.channelSimName = channelSimName;
	}
	
	public String getTaxType() {
		return taxType;
	}
	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}
	@Override
	public String toString() {
		return "DZPTChannelInfo [address=" + address + ", bank=" + bank
				+ ", bankAcount=" + bankAcount + ", channelName=" + channelName
				+ ", id=" + id + ", phone=" + phone + ", taxId=" + taxId + "]";
	}
	
}
