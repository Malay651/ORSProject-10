package com.rays.form;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.EnrollmentDTO;
import com.rays.dto.UserDTO;

public class EnrollmentForm extends BaseForm {

	@NotEmpty(message = "enrollmentcode is required")
	private String enrollmentCode;
    
	@NotEmpty(message = "studentname is required")
	private String studentName;
	
    @NotNull(message = "enrollmentdate is required")
	private Date enrollmentDate;
   
    @NotEmpty(message = "enrollmentstatus is required")
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
	public BaseDTO getDto() {
		
		EnrollmentDTO dto = initDTO(new EnrollmentDTO());
		dto.setEnrollmentCode(enrollmentCode);
		dto.setStudentName(studentName);
		dto.setEnrollmentDate(enrollmentDate);
		dto.setEnrollmentStatus(enrollmentStatus);
		
		return dto;
	}

	
}
