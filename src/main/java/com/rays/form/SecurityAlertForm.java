package com.rays.form;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.BookDTO;
import com.rays.dto.SecurityAlertDTO;

public class SecurityAlertForm extends BaseForm {

	@NotEmpty(message = "threatlevel is required")
	private String threatLevel;
	@NotEmpty(message = "sourceip is required")
	private String sourceIp;
	@NotNull(message = "detectedtime is required")
	private Date detectedTime;
	@NotEmpty(message = "status is required")
	private String status;
	
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
	
	@Override
	public BaseDTO getDto() {
		SecurityAlertDTO dto = initDTO(new SecurityAlertDTO());
		dto.setThreatLevel(threatLevel);
		dto.setSourceIp(sourceIp);
		dto.setDetectedTime(detectedTime);
		dto.setStatus(status);
		return dto;

	}
	
}
