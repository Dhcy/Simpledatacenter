package com.good.plat.datacenter.common.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.good.plat.datacenter.game.entity.Channel;
import com.good.plat.datacenter.game.entity.GameLanguage;
import com.good.plat.datacenter.game.entity.SubChannel;
import com.good.plat.datacenter.game.entity.TBLogplatGameVersion;


public class BaseResult implements Serializable {

	private static final long serialVersionUID = 2L;

	public BaseResult() {
	}

	private String status;
	private String message;
	private Object data;

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
