package com.rays.form;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.ApprovalHistoryDTO;
import com.rays.dto.BookDTO;

public class ApprovalHistoryForm extends BaseForm {

	@NotEmpty(message = "historycode is required")
	private String historyCode;
	@NotEmpty(message = "approved is required")
	private String approvedBy;
	@NotNull(message = "approvedby is required")
	private Date approvedDate;
	@NotEmpty(message = "status is required")
	private String status;
	
	public String getHistoryCode() {
		return historyCode;
	}
	public void setHistoryCode(String historyCode) {
		this.historyCode = historyCode;
	}
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	public Date getApprovedDate() {
		return approvedDate;
	}
	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public BaseDTO getDto() {
		// TODO Auto-generated method stub
		ApprovalHistoryDTO dto = initDTO(new ApprovalHistoryDTO());
		dto.setHistoryCode(historyCode);
		dto.setApprovedBy(approvedBy);
		dto.setApprovedDate(approvedDate);
		dto.setStatus(status);
		return dto;
		
	}
		
	
}
