package com.good.plat.datacenter.independ.entity.yhindex;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

/**
 * 银河社交情况
 * @ClassName: YhSocialContact
 * @Description: TODO
 * @author hwj
 * @date 2018-5-16 下午02:34:50
 */
public class YhSocialContact extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//拥有好友数量
	private Integer fcount;
	//玩家数
	private Integer count;
	public Integer getFcount() {
		return fcount;
	}
	public void setFcount(Integer fcount) {
		this.fcount = fcount;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "YhSocialContact [count=" + count + ", fcount=" + fcount + "]";
	}
	
	
	
}
