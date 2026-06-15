package com.rays.form;

import javax.validation.constraints.NotEmpty;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.BookDTO;
import com.rays.dto.HelpDeskDTO;

public class HelpDeskForm extends BaseForm {

	@NotEmpty(message= "helpdeskcode is required")
	private String helpDeskCode;
	
	@NotEmpty(message= "username is required")
	private String userName;
	
	@NotEmpty(message= "query is required")
	private String query;
	
	@NotEmpty(message= "status is required")
	private String status;
	
	public String getHelpDeskCode() {
		return helpDeskCode;
	}
	public void setHelpDeskCode(String helpDeskCode) {
		this.helpDeskCode = helpDeskCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public BaseDTO getDto() {
		HelpDeskDTO dto = initDTO(new HelpDeskDTO());
		dto.setHelpDeskCode(helpDeskCode);
		dto.setUserName(userName);
		dto.setQuery(query);
		dto.setStatus(status);
		return dto;

		
	}
}
