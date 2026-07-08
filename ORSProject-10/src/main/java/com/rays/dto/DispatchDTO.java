package com.rays.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

@Entity
@Table (name ="st_dispatch")
public class DispatchDTO extends BaseDTO {

	@Column(name = "dispatch_date")
	private Date dispatchDate;
	
	@Column(name ="status" )
	private String status;
	
	@Column(name = "courier_name")
	private String courierName;
	
	public Date getDispatchDate() {
		return dispatchDate;
	}
	public void setDispatchDate(Date dispatchDate) {
		this.dispatchDate = dispatchDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCourierName() {
		return courierName;
	}
	public void setCourierName(String courierName) {
		this.courierName = courierName;
	}
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return "courierName";
	}
	@Override
	public String getUniqueKey() {
		// TODO Auto-generated method stub
		return "courierName";
	}
	@Override
	public String getUniqueValue() {
		// TODO Auto-generated method stub
		return courierName;
	}
	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "courierName";
	}
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "Dispatch";
	}
	
	
}
