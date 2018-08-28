package com.good.plat.datacenter.independ.entity.yhindex;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;
/**
 * 商品统计
 * @ClassName: YhGoodsStat
 * @Description: TODO
 * @author hwj
 * @date 2017-12-29 上午09:50:12
 */
public class YhGoodsStat extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//商品id
	private Integer goodsid;
	//商店名称
	private  String shopname;
	//购买数量
	private Integer count;
	//商品名称
	private String goodsname;
	public Integer getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
	}
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	
	

}
