package com.good.plat.datacenter.independ.entity.hie.index;

import java.io.Serializable;

public class HieEctypeStat implements Serializable {
	private String ectypename;// 副本名称
	private Integer accessnum;// 进入次数
	private Integer passnum;// 通关次数
	private double passpro;// 通关率
	private Integer accesspersonnum;// 参与人数
	private Integer passpersonnum;// 通关人数
	private double avgtime;// 平均用时
	private Integer lxynum;// 蕾西亚
	private Integer hxnum;// 红霞
	private Integer walnum;// 维奥拉
	private Integer xhlnum;// 雪花莲
	private Integer mxdnum;// 梅忒黛
	private Integer mlyqnum;// 玛利亚裘

	public String getEctypename() {
		return ectypename;
	}

	public void setEctypename(String ectypename) {
		this.ectypename = ectypename;
	}

	public Integer getAccessnum() {
		return accessnum;
	}

	public void setAccessnum(Integer accessnum) {
		this.accessnum = accessnum;
	}

	public Integer getPassnum() {
		return passnum;
	}

	public void setPassnum(Integer passnum) {
		this.passnum = passnum;
	}

	public double getPasspro() {
		return passpro;
	}

	public void setPasspro(double passpro) {
		this.passpro = passpro;
	}

	public Integer getAccesspersonnum() {
		return accesspersonnum;
	}

	public void setAccesspersonnum(Integer accesspersonnum) {
		this.accesspersonnum = accesspersonnum;
	}

	public Integer getPasspersonnum() {
		return passpersonnum;
	}

	public void setPasspersonnum(Integer passpersonnum) {
		this.passpersonnum = passpersonnum;
	}

	public double getAvgtime() {
		return avgtime;
	}

	public void setAvgtime(double avgtime) {
		this.avgtime = avgtime;
	}

	public Integer getLxynum() {
		return lxynum;
	}

	public void setLxynum(Integer lxynum) {
		this.lxynum = lxynum;
	}

	public Integer getHxnum() {
		return hxnum;
	}

	public void setHxnum(Integer hxnum) {
		this.hxnum = hxnum;
	}

	public Integer getWalnum() {
		return walnum;
	}

	public void setWalnum(Integer walnum) {
		this.walnum = walnum;
	}

	public Integer getXhlnum() {
		return xhlnum;
	}

	public void setXhlnum(Integer xhlnum) {
		this.xhlnum = xhlnum;
	}

	public Integer getMxdnum() {
		return mxdnum;
	}

	public void setMxdnum(Integer mxdnum) {
		this.mxdnum = mxdnum;
	}

	public Integer getMlyqnum() {
		return mlyqnum;
	}

	public void setMlyqnum(Integer mlyqnum) {
		this.mlyqnum = mlyqnum;
	}

}
