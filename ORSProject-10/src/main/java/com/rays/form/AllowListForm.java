package com.rays.form;

import javax.validation.constraints.NotEmpty;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.AllowListDTO;
import com.rays.dto.ApprovalHistoryDTO;

public class AllowListForm extends BaseForm {

	@NotEmpty(message = "allowcode is required")
	private String allowCode;
	@NotEmpty(message = "username is required")
	private String username;
	@NotEmpty(message = "source is required")
	private String source;
	@NotEmpty(message = "status is required")
	private String status;

	public String getAllowCode() {
		return allowCode;
	}

	public void setAllowCode(String allowCode) {
		this.allowCode = allowCode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

@Override
public BaseDTO getDto() {
	AllowListDTO dto = initDTO(new AllowListDTO());
	dto.setAllowCode(allowCode);
	dto.setUsername(username);
	dto.setSource(source);
	dto.setStatus(status);
	return dto;
 }

}
