package com.rays.form;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.OtpDTO;
import com.rays.dto.UserDTO;

public class OtpForm extends BaseForm {

	@NotEmpty(message="otpcode is required")
	private String otpCode;
	
	@NotEmpty(message="mobileNumber is required")
	private String mobileNumber;
	
	@NotNull(message="expirytime is required")
	private Date expiryTime;
	
	@NotEmpty(message="otpstatus is required")
	private String otpStatus;

	public String getOtpCode() {
		return otpCode;
	}

	public void setOtpCode(String otpCode) {
		this.otpCode = otpCode;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Date getExpiryTime() {
		return expiryTime;
	}

	public void setExpiryTime(Date expiryTime) {
		this.expiryTime = expiryTime;
	}

	public String getOtpStatus() {
		return otpStatus;
	}

	public void setOtpStatus(String otpStatus) {
		this.otpStatus = otpStatus;
	}
	
	@Override
	public BaseDTO getDto() {
		
		OtpDTO dto = initDTO(new OtpDTO());
		dto.setOtpCode(otpCode);
		dto.setMobileNumber(mobileNumber);
		dto.setExpiryTime(expiryTime);
		dto.setOtpStatus(otpStatus);
		
		return dto;
		
	}
	
}
