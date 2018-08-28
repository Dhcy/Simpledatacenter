package com.good.plat.datacenter.independ.entity.yhindex;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;
/**
 * 成就统计
 * @ClassName: YhAchieveStat
 * @Description: TODO
 * @author hwj
 * @date 2018-5-21 下午05:40:34
 */
public class YhAchieveStat extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer achieveid;//成就ID
	private Integer count;//完成人数
	public Integer getAchieveid() {
		return achieveid;
	}
	public void setAchieveid(Integer achieveid) {
		this.achieveid = achieveid;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "YhAchieveStat [achieveid=" + achieveid + ", count=" + count
				+ "]";
	}
	

}
