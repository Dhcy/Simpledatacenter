package com.good.plat.datacenter.datastat.entity.players;

import java.io.Serializable;

import com.good.plat.datacenter.common.base.BaseEntity;

public class Device extends BaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String machineModel;
	private int count;
	private String operationSystem;
	
	public Device() {
		super();
	}

	public String getMachineModel() {
		return machineModel;
	}

	public void setMachineModel(String machineModel) {
		this.machineModel = machineModel;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getOperationSystem() {
		return operationSystem;
	}

	public void setOperationSystem(String operationSystem) {
		this.operationSystem = operationSystem;
	}
	
}
