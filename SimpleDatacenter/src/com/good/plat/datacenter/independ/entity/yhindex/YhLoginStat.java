package com.good.plat.datacenter.independ.entity.yhindex;

import java.io.Serializable;
/**
 * 银河登录
 * @ClassName: YhLoginStat
 * @Description: TODO
 * @author hwj
 * @date 2018-5-14 下午03:33:01
 */
public class YhLoginStat implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer type;//环节
	private String typeName;//环节名
	private Integer succnt;//成功次数
	private Integer count;//总次数
	private Double succntRate;//成功率
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getSuccnt() {
		return succnt;
	}
	public void setSuccnt(Integer succnt) {
		this.succnt = succnt;
	}
	public Double getSuccntRate() {
		return succntRate;
	}
	public void setSuccntRate(Double succntRate) {
		this.succntRate = succntRate;
	}
	
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "YhLoginStat [count=" + count + ", succnt=" + succnt
				+ ", succntRate=" + succntRate + ", type=" + type
				+ ", typeName=" + typeName + "]";
	}
	
	
	

}
