package com.good.plat.datacenter.datastat.entity.players;

import java.io.Serializable;
import java.util.List;

import com.good.plat.datacenter.common.base.BaseEntity;

/**
 * @ClassName: ActivePlayers
 * @Description: 活跃玩家
 * @author dmw
 * @date 2016年3月14日 下午1:54:30
 */
public class ActivePlayers extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public ActivePlayers() {
	}
	
	private List<ActivePlayers> activePlayerList;
	
	
	//新玩家
	private Integer newUser;
	
	//老玩家
	private Integer oldUser;
	
	//总计
	private Integer count;
	
	
	//WAU
	private Integer wauAccount;
	
	//MAU
	private Integer mauAccount;
	
	//DAU/MAU
	private Double dmauAccount;
	
	//AVG
	private Integer AVG;
	
	//MD
	private Integer MD;
	
	//活跃玩家
	private Integer activeUser;
	
	//百分比
	//private Double rate;

	public Integer getNewUser() {
		return newUser;
	}

	public void setNewUser(Integer newUser) {
		this.newUser = newUser;
	}

	public Integer getOldUser() {
		return oldUser;
	}

	public void setOldUser(Integer oldUser) {
		this.oldUser = oldUser;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getWauAccount() {
		return wauAccount;
	}

	public void setWauAccount(Integer wauAccount) {
		this.wauAccount = wauAccount;
	}

	public Integer getMauAccount() {
		return mauAccount;
	}

	public void setMauAccount(Integer mauAccount) {
		this.mauAccount = mauAccount;
	}

	public Double getDmauAccount() {
		return dmauAccount;
	}

	public void setDmauAccount(Double dmauAccount) {
		this.dmauAccount = dmauAccount;
	}

	public Integer getAVG() {
		return AVG;
	}

	public void setAVG(Integer aVG) {
		AVG = aVG;
	}

	public Integer getMD() {
		return MD;
	}

	public void setMD(Integer mD) {
		MD = mD;
	}

	public Integer getActiveUser() {
		return activeUser;
	}

	public void setActiveUser(Integer activeUser) {
		this.activeUser = activeUser;
	}

	public List<ActivePlayers> getActivePlayerList() {
		return activePlayerList;
	}

	public void setActivePlayerList(List<ActivePlayers> activePlayerList) {
		this.activePlayerList = activePlayerList;
	}

}
