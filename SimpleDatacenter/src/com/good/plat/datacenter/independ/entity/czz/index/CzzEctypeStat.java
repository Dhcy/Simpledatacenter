package com.good.plat.datacenter.independ.entity.czz.index;

import java.io.Serializable;

public class CzzEctypeStat implements Serializable {
	private String ectypename; // 副本名称
	private Integer accessnum;// 进入次数
	private Integer passnum;// 通关次数
	private Double passrate;// 通关率
	private Integer accesspersonnum;// 参与人数
	private Integer passpersonnum;// 通关人数
	private Double avgtime;// 通关平均用时

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

	public Double getPassrate() {
		return passrate;
	}

	public void setPassrate(Double passrate) {
		this.passrate = passrate;
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

	public Double getAvgtime() {
		return avgtime;
	}

	public void setAvgtime(Double avgtime) {
		this.avgtime = avgtime;
	}

}
