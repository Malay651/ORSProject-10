package com.rays.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;
@Entity
@Table(name= "st_helpdesk")
public class HelpDeskDTO extends BaseDTO {

	@Column(name= "helpdesk_code", length = 50)
	private String helpDeskCode;
	
	@Column(name= "user_name", length = 50)
	private String userName;
	
	@Column(name= "query", length = 50)
	private String query;
	
	@Column(name= "status", length = 50)
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
		return "HelpDesk";
	}

	
}
