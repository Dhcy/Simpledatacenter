package com.good.plat.datacenter.datastat.entity.index;

import java.io.Serializable;
/**
 * 日志类型静态编码
 * @ClassName: LogTypeAtrrCodeValue
 * @Description: TODO
 * @author hwj
 * @date 2017-8-21 下午05:16:33
 */
public class LogTypeAtrrCodeValue  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1789229780759934914L;
	//
	private String attr_code;//编码
	private String attr_value;//日志类型返回值
	private String attr_desc;//日志类型描述
	public String getAttr_code() {
		return attr_code;
	}
	public void setAttr_code(String attrCode) {
		attr_code = attrCode;
	}
	public String getAttr_value() {
		return attr_value;
	}
	public void setAttr_value(String attrValue) {
		attr_value = attrValue;
	}
	public String getAttr_desc() {
		return attr_desc;
	}
	public void setAttr_desc(String attrDesc) {
		attr_desc = attrDesc;
	}
	

}
