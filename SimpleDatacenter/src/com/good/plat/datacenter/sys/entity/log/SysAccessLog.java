package com.good.plat.datacenter.sys.entity.log;

import java.util.Date;

public class SysAccessLog {
	
    public SysAccessLog() {
//    	Date d = new Date();
//		this.op_time = d;
//		this.return_time = d;
//		this.ctime = d;
	}

	private Long id;

    private String user_id;

    private Date op_time;

    private String op_desc;

    private String operator_ip;

    private String request_url;

    private String process_method;

    private Date return_time;

    private Date ctime;

    private String process_method_params;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id == null ? null : user_id.trim();
    }

    public Date getOp_time() {
        return op_time;
    }

    public void setOp_time(Date op_time) {
        this.op_time = op_time;
    }

    public String getOp_desc() {
        return op_desc;
    }

    public void setOp_desc(String op_desc) {
        this.op_desc = op_desc == null ? null : op_desc.trim();
    }

    public String getOperator_ip() {
        return operator_ip;
    }

    public void setOperator_ip(String operator_ip) {
        this.operator_ip = operator_ip == null ? null : operator_ip.trim();
    }

    public String getRequest_url() {
        return request_url;
    }

    public void setRequest_url(String request_url) {
        this.request_url = request_url == null ? null : request_url.trim();
    }

    public String getProcess_method() {
        return process_method;
    }

    public void setProcess_method(String process_method) {
        this.process_method = process_method == null ? null : process_method.trim();
    }

    public Date getReturn_time() {
        return return_time;
    }

    public void setReturn_time(Date return_time) {
        this.return_time = return_time;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getProcess_method_params() {
        return process_method_params;
    }

    public void setProcess_method_params(String process_method_params) {
        this.process_method_params = process_method_params == null ? null : process_method_params.trim();
    }
}