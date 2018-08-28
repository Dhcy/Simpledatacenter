package com.good.plat.datacenter.independ.entity.yhindex;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;
/**
 * 银河等级分布
 * @ClassName: YhLevelStat
 * @Description: TODO
 * @author hwj
 * @date 2017-12-28 上午10:09:41
 */
public class YhLevelStat extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//level1~10用户数量
	private Integer lvl_1to10User;
	//level11~20用户数量
	private Integer lvl_11to20User;
	//level21~30用户数量
	private Integer lvl_21to30User;
	//level31~40用户数量
	private Integer lvl_31to40User;
	//level41~50用户数量
	private Integer lvl_41to50User;
	//level51~60用户数量
	private Integer lvl_51to60User;
	//level1~10占比
	private Double lvl_1to10UserRate;
	//level11~20占比
	private Double lvl_11to20UserRate;
	//level21~30占比
	private Double lvl_21to30UserRate;
	//level31~40占比
	private Double lvl_31to40UserRate;
	//level41~50占比
	private Double lvl_41to50UserRate;
	//level51~60占比
	private Double lvl_51to60UserRate;
	public Integer getLvl_1to10User() {
		return lvl_1to10User;
	}
	public void setLvl_1to10User(Integer lvl_1to10User) {
		this.lvl_1to10User = lvl_1to10User;
	}
	public Integer getLvl_11to20User() {
		return lvl_11to20User;
	}
	public void setLvl_11to20User(Integer lvl_11to20User) {
		this.lvl_11to20User = lvl_11to20User;
	}
	public Integer getLvl_21to30User() {
		return lvl_21to30User;
	}
	public void setLvl_21to30User(Integer lvl_21to30User) {
		this.lvl_21to30User = lvl_21to30User;
	}
	public Integer getLvl_31to40User() {
		return lvl_31to40User;
	}
	public void setLvl_31to40User(Integer lvl_31to40User) {
		this.lvl_31to40User = lvl_31to40User;
	}
	public Integer getLvl_51to60User() {
		return lvl_51to60User;
	}
	public void setLvl_51to60User(Integer lvl_51to60User) {
		this.lvl_51to60User = lvl_51to60User;
	}
	public Double getLvl_1to10UserRate() {
		return lvl_1to10UserRate;
	}
	public void setLvl_1to10UserRate(Double lvl_1to10UserRate) {
		this.lvl_1to10UserRate = lvl_1to10UserRate;
	}
	public Double getLvl_11to20UserRate() {
		return lvl_11to20UserRate;
	}
	public void setLvl_11to20UserRate(Double lvl_11to20UserRate) {
		this.lvl_11to20UserRate = lvl_11to20UserRate;
	}
	public Double getLvl_21to30UserRate() {
		return lvl_21to30UserRate;
	}
	public void setLvl_21to30UserRate(Double lvl_21to30UserRate) {
		this.lvl_21to30UserRate = lvl_21to30UserRate;
	}
	public Double getLvl_31to40UserRate() {
		return lvl_31to40UserRate;
	}
	public void setLvl_31to40UserRate(Double lvl_31to40UserRate) {
		this.lvl_31to40UserRate = lvl_31to40UserRate;
	}
	public Integer getLvl_41to50User() {
		return lvl_41to50User;
	}
	public void setLvl_41to50User(Integer lvl_41to50User) {
		this.lvl_41to50User = lvl_41to50User;
	}
	public Double getLvl_41to50UserRate() {
		return lvl_41to50UserRate;
	}
	public void setLvl_41to50UserRate(Double lvl_41to50UserRate) {
		this.lvl_41to50UserRate = lvl_41to50UserRate;
	}
	public Double getLvl_51to60UserRate() {
		return lvl_51to60UserRate;
	}
	public void setLvl_51to60UserRate(Double lvl_51to60UserRate) {
		this.lvl_51to60UserRate = lvl_51to60UserRate;
	}
	
	
	
}
