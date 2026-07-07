package com.rays.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

@Entity
@Table(name = "st_healthcheck")
public class HealthCheckDTO extends BaseDTO {

	@Column(name= "health_code")
	private String healthCode;
	
	@Column(name= "service_name")
	private String servicename;
	
	@Column(name = "uptime")
	private String uptime;
	
	@Column(name = "status")
	private String status;
	
	public String getHealthCode() {
		return healthCode;
	}
	public void setHealthCode(String healthCode) {
		this.healthCode = healthCode;
	}
	public String getServicename() {
		return servicename;
	}
	public void setServicename(String servicename) {
		this.servicename = servicename;
	}
	public String getUptime() {
		return uptime;
	}
	public void setUptime(String uptime) {
		this.uptime = uptime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getUniqueKey() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getUniqueValue() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "HealthCheck";
	}
	
	
}
