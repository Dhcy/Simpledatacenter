package com.good.plat.datacenter.datastat.entity.index;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *	日志实时数据
 * @ClassName: LogRealTimeData
 * @Description: TODO
 * @author hwj
 * @date 2017-8-21 下午04:41:23
 */
public class LogRealTimeData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8610752755912177403L;
	private Integer gameid;//游戏id
	private String actorid;//角色id
	private Integer limit;//限制条数
	private String attr_code;//编码
	private String logType;//日志类型
	private String logTypeDesc;//日志类型描述
	//
	private String logdate;//日期
	private Integer areaid;//区服
	private String content;//内容
	private String startDate;//起始日期
	private String endDate;//结束日期
	private String rowid;//行号
	private String previewContent;//预览
	
	//表格内容
	private List<LogTypeAtrrCodeValue> tableData;
	public Integer getGameid() {
		return gameid;
	}
	public void setGameid(Integer gameid) {
		this.gameid = gameid;
	}
	
	public String getActorid() {
		return actorid;
	}
	public void setActorid(String actorid) {
		this.actorid = actorid;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public String getAttr_code() {
		return attr_code;
	}
	public void setAttr_code(String attrCode) {
		attr_code = attrCode;
	}
	
	public String getLogdate() {
		return logdate;
	}
	public void setLogdate(String logdate) {
		this.logdate = logdate;
	}
	public Integer getAreaid() {
		return areaid;
	}
	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getLogType() {
		return logType;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
	public String getLogTypeDesc() {
		return logTypeDesc;
	}
	public void setLogTypeDesc(String logTypeDesc) {
		this.logTypeDesc = logTypeDesc;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getRowid() {
		return rowid;
	}
	public void setRowid(String rowid) {
		this.rowid = rowid;
	}
	public String getPreviewContent() {
		return previewContent;
	}
	public List<LogTypeAtrrCodeValue> getTableData() {
		return tableData;
	}
	public void setTableData(List<LogTypeAtrrCodeValue> tableData) {
		this.tableData = tableData;
	}
	public void setPreviewContent(String previewContent) {
		this.previewContent = previewContent;
	}
	
	
	
	
	
	
	
	
	
	

}
