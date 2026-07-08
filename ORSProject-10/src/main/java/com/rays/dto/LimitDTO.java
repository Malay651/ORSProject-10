package com.rays.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;
@Entity
@Table(name = "st_limit")
public class LimitDTO extends BaseDTO {

	@Column(name = "limit_code")
	private String limitCode;
	
	@Column(name = "limit_name")
	private String limitName;
	
	@Column(name = "max_value")
	private String maxValue;
	
	@Column(name = "status")
	private String status;
	
	public String getLimitCode() {
		return limitCode;
	}
	public void setLimitCode(String limitCode) {
		this.limitCode = limitCode;
	}
	public String getLimitname() {
		return limitName;
	}
	public void setLimitname(String limitname) {
		this.limitName = limitname;
	}
	public String getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
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
		return "limit";
	}
	
	
}
