package com.good.plat.datacenter.login.user.entity;

public class Department {
    private Integer id;

    private String key;

    private String department;
    
    public Department(Integer id, String key, String department) {
		super();
		this.id = id;
		this.key = key;
		this.department = department;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

	@Override
	public String toString() {
		return "Department [id=" + id + ", key=" + key + ", department="
				+ department + "]";
	}
    
}