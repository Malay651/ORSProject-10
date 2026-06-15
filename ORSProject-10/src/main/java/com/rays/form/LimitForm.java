package com.rays.form;

import javax.validation.constraints.NotEmpty;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.BookDTO;
import com.rays.dto.LimitDTO;

public class LimitForm extends BaseForm {

	@NotEmpty (message = "limitcode is required")
	private String limitCode;
	@NotEmpty (message = "limitname is required")
	private String limitName;
	@NotEmpty (message = "maxvalue is required")
	private String maxValue;
	@NotEmpty (message = "status is required")
	private String status;

	public String getLimitCode() {
		return limitCode;
	}
	public void setLimitCode(String limitCode) {
		this.limitCode = limitCode;
	}
	public String getLimitName() {
		return limitName;
	}
	public void setLimitName(String limitName) {
		this.limitName = limitName;
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
public BaseDTO getDto() {
	LimitDTO dto = initDTO(new LimitDTO());
	dto.setLimitCode(limitCode);
	dto.setLimitname(limitName);
	dto.setMaxValue(maxValue);
	dto.setStatus(status);
	return dto;
	
}
}
