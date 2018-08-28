package com.good.plat.datacenter.independ.entity.hie.index;

import java.io.Serializable;

public class HieTaskStat implements Serializable {
	private Integer taskid;// 任务id
	private String tasktype;// 任务类型（1：主线，2：支线）
	private String taskname;// 任务名称
	private Integer tasklevel;// 任务等级
	private Integer accepttotal;// 接受数总数
	private Integer completetotal;// 完成数总数
	private double avgtime;// 平均花费时间
	private double completerate;// 任务完成率

	public Integer getTaskid() {
		return taskid;
	}

	public void setTaskid(Integer taskid) {
		this.taskid = taskid;
	}

	public String getTasktype() {
		return tasktype;
	}

	public void setTasktype(String tasktype) {
		this.tasktype = tasktype;
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public Integer getTasklevel() {
		return tasklevel;
	}

	public void setTasklevel(Integer tasklevel) {
		this.tasklevel = tasklevel;
	}

	public Integer getAccepttotal() {
		return accepttotal;
	}

	public void setAccepttotal(Integer accepttotal) {
		this.accepttotal = accepttotal;
	}

	public Integer getCompletetotal() {
		return completetotal;
	}

	public void setCompletetotal(Integer completetotal) {
		this.completetotal = completetotal;
	}

	public double getAvgtime() {
		return avgtime;
	}

	public void setAvgtime(double avgtime) {
		this.avgtime = avgtime;
	}

	public double getCompleterate() {
		return completerate;
	}

	public void setCompleterate(double completerate) {
		this.completerate = completerate;
	}

}
