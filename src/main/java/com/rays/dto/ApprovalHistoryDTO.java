package com.rays.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.rays.common.BaseDTO;

@Entity
@Table(name = "st_approvalhistory")
public class ApprovalHistoryDTO extends BaseDTO {

   @Column(name = "history_code")
   private String historyCode;

   @Column(name = "approved_by")
   private String approvedBy;

   @Column(name = "approved_date")
   private Date approvedDate;

   @Column(name = "status")
   private String	status;

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
	return null;
}

   

}
