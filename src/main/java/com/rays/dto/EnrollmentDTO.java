package com.rays.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

@Entity
@Table(name = "st_enrollment")
public class EnrollmentDTO extends BaseDTO{

	@Column(name = "enrollment_code")
	private String enrollmentCode;

	@Column(name = "student_name")
    private String studentName;
	
     @Column(name = "enrollment_date")
    private Date enrollmentDate;
     
     @Column(name = "enrollment_status")
    private String enrollmentStatus;
     
	public String getEnrollmentCode() {
		return enrollmentCode;
	}
	public void setEnrollmentCode(String enrollmentCode) {
		this.enrollmentCode = enrollmentCode;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public Date getEnrollmentDate() {
		return enrollmentDate;
	}
	public void setEnrollmentDate(Date enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}
	public String getEnrollmentStatus() {
		return enrollmentStatus;
	}
	public void setEnrollmentStatus(String enrollmentStatus) {
		this.enrollmentStatus = enrollmentStatus;
	}
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getUniqueKey() {
		// TODO Auto-generated method stub
		return "enrollmentCode";
	}
	@Override
	public String getUniqueValue() {
		// TODO Auto-generated method stub
		return  enrollmentCode;
	}
	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "enrollmentCode";
	}
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "enrollment";
	}

     
   
    
}
