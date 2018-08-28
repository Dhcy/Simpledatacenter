package com.good.plat.datacenter.common.base;

/**
 * @ClassName: BaseMessage
 * @Description: 返回操作定义
 * <p>status:状态码.1:成功,0:失败</p>
 * <p>message:自定义返回信息</p>
 * @author dmw
 * @date 2016年1月12日 下午4:54:30
 */
public class BaseMessage {

	public BaseMessage() {
	}

	//状态：1：成功；0：失败
	private Integer status;
	
	//自定义信息
	private String message;
	
	public BaseMessage(Integer status, String message) {
		this.status = status;
		this.message = message;
	}


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	@Override
	public String toString() {
		return "BaseMessage [status=" + status + ", message=" + message + "]";
	}
	
}
