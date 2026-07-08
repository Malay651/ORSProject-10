package com.rays.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

@Entity
@Table(name = "st_securityalert")
public class SecurityAlertDTO extends BaseDTO {

	public String getThreatLevel() {
		return threatLevel;
	}
	public void setThreatLevel(String threatLevel) {
		this.threatLevel = threatLevel;
	}
	public String getSourceIp() {
		return sourceIp;
	}
	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}
	public Date getDetectedTime() {
		return detectedTime;
	}
	public void setDetectedTime(Date detectedTime) {
		this.detectedTime = detectedTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	private String threatLevel;
	private String sourceIp;
	private Date detectedTime;
	private String status;
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
		return null;
	}
	
}
