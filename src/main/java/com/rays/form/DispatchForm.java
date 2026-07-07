package com.rays.form;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.DispatchDTO;

public class DispatchForm extends BaseForm {

	@NotNull(message = "dispatchdate is required")
	private Date dispatchDate;
	@NotEmpty(message = "status is required")
	private String status;
	@NotEmpty(message = "couriername is required")
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
	public BaseDTO getDto() {
	DispatchDTO dto = initDTO(new DispatchDTO());
	
	dto.setCourierName(courierName);
	dto.setDispatchDate(dispatchDate);
	dto.setStatus(status);
		return dto;
	}
	
}
