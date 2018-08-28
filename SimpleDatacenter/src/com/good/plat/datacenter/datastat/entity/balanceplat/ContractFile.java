package com.good.plat.datacenter.datastat.entity.balanceplat;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.good.plat.datacenter.common.base.BaseEntity;
/**
 * 合同文件
 * @ClassName: ContractFile
 * @Description: TODO
 * @author hwj
 * @date 2017-5-11 上午10:42:13
 */
public class ContractFile extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5629387250555450237L;
	private Integer id;//主键
	private Integer channelId;//渠道id
	private String filePath;//路径
	private String description;//描述
	@JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
	private Date uploadDate;//上传日期
	private String fileName;//文件名称
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getChannelId() {
		return channelId;
	}
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	

}
